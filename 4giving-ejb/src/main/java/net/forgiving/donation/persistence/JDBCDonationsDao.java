/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.forgiving.donation.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.enterprise.context.Dependent;
import javax.sql.DataSource;
import net.forgiving.common.donation.Donation;
import net.forgiving.common.donation.DonationStatus;
import net.forgiving.common.donation.Item;
import net.forgiving.common.user.User;

/**
 *
 * @author gabalca
 */
@Dependent
public class JDBCDonationsDao implements DonationsDao{
    
    public static final String SELECT_ID="SELECT * FROM DONATION WHERE ID = ?";
    public static final String INSERT="INSERT INTO DONATION (user_id,"
            + "item_id,created,karma,status,resolving_time)"
            + " VALUES (?,?,?,?,?,?)";
    
    @Resource(name  = "jdbc/forgivingDSAlternate")
    private DataSource dataSource;

    @Override
    public void storeDonation(Donation d) throws DonationStorageException {
        try(Connection con=dataSource.getConnection();
                PreparedStatement ps1=con.prepareStatement(INSERT,
                        Statement.RETURN_GENERATED_KEYS)){
            
            ps1.setLong(1, d.getDonator().getId());
            ps1.setLong(2, d.getItem().getId());
            ps1.setTimestamp(3, Timestamp.from(d.getCreated()));
            ps1.setInt(4, d.getKarmaCost());
            ps1.setString(5, d.getStatus().name());
            ps1.setTimestamp(6, Timestamp.from(d.getResolvingDeadline()));
            
            ps1.executeUpdate();
            
            ResultSet rs= ps1.getGeneratedKeys();
            
            if(rs.next()){
                d.setId(rs.getLong(1));
                //guardem les categories
                
            }else{
                Logger.getLogger(JDBCItemDao.class.getName()).log(Level.SEVERE, 
                        "Donation saved but no id returned");
                throw new DonationStorageException("Error inserting new item:"
                        + " no id generated");
            }
                        
        } catch (SQLException ex) {
            Logger.getLogger(JDBCItemDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new DonationStorageException( "Error inserting new item", ex);
        }
    }

    @Override
    public Donation getDonationById(long id) throws DonationStorageException {
        try(Connection con=dataSource.getConnection();
                PreparedStatement ps1=con.prepareStatement(SELECT_ID)){
            ps1.setLong(1, id);
            
            ResultSet rs = ps1.executeQuery();
            
            if(rs.next()){
                Donation result = getDonationFromResultSet(rs);
                
                return result;
            }
            
            throw new DonationStorageException("Could not find item with id "+id);
            
        } catch (SQLException ex) {
            Logger.getLogger(JDBCItemDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new DonationStorageException("Error loading item", ex);
        }
    }
    
    private Donation getDonationFromResultSet(ResultSet rs) throws SQLException{
        Donation result=new Donation();
        result.setId(rs.getLong("id"));
        Item i = new Item();
        i.setId(rs.getLong("item_id"));
        result.setItem(i);
        
        User u=new User();
        u.setId(rs.getLong("user_id"));
        result.setDonator(u);
        
        result.setStatus(DonationStatus.valueOf(rs.getString("status")));
        
        //todo llegir els altres camps, karma, i dates
        
        return result;
    }
    
}
