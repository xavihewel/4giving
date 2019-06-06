/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.forgiving.user.persistence;

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
import net.forgiving.common.user.Address;
import static net.forgiving.user.persistence.JDBCUserDao.INSERT;

/**
 *
 * @author gabalca
 */
@Dependent
public class JDBCAddressDao implements AddressDao{
    
    public static final String SELECT_ID="SELECT * FROM ADDRESS WHERE ID = ?";
    public static final String INSERT="INSERT INTO ADDRESS (street,num,"
            + "zip,province,country,lat,lon) VALUES (?,?,?,?,?,?,?)";
    
    @Resource(name  = "jdbc/forgivingDS")
    private DataSource dataSource;

    @Override
    public Address getAddress(long id) {
        try(
            Connection connection = dataSource.getConnection();
            PreparedStatement ps = 
                connection.prepareStatement(SELECT_ID)){
            
            ps.setLong(1, id);
            
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                return getAddressFromResultSet(rs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(JDBCUserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    private Address getAddressFromResultSet(ResultSet rs) throws SQLException{
        Address address=new Address();
        address.setId(rs.getLong("id"));
        address.setStreet(rs.getString("STREET"));
        address.setState(rs.getString("COUNTRY"));
        address.setProvince(rs.getString("PROVINCE"));
        address.setNumber(rs.getString("NUM"));
        address.setZip(rs.getString("ZIP"));
        address.setLat(rs.getDouble("LAT"));
        address.setLon(rs.getDouble("LON"));
        
        return address;
    }

    @Override
    public void storeAddress(Address adresss) {
        try(Connection con=dataSource.getConnection();
                PreparedStatement ps =con.prepareStatement(INSERT, 
                        Statement.RETURN_GENERATED_KEYS)){
            ps.setString(1,adresss.getStreet());
            ps.setString(2,adresss.getNumber());
            ps.setString(3,adresss.getZip());
            ps.setString(4,adresss.getProvince());
            ps.setString(5,adresss.getState());
            ps.setDouble(6, adresss.getLat());
            ps.setDouble(7, adresss.getLon());
            
            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys();
            
            if(rs.next()){
                adresss.setId(rs.getLong(1));
            }else{
                //TODO llençar error
                System.out.println("No ha retornat l'id insertat");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(JDBCUserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
