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
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.enterprise.context.Dependent;
import javax.sql.DataSource;
import net.forgiving.common.user.Address;
import net.forgiving.common.user.User;
import net.forgiving.user.UsersManager;

/**
 *
 * @author gabalca
 */
@Dependent
public class JDBCUserDao implements UserDao{
    
    public final static String SELECT_ALL="SELECT * FROM USERS";
    public final static String SELECT_ID="SELECT * FROM USERS WHERE id = ?";
    public final static String INSERT="INSERT INTO USERS (username,password,"
            + "email,address_id,validated,created,karma) VALUES (?,?,?,?,?,?,?)";
    
    @Resource(name  = "jdbc/forgivingDS")
    private DataSource dataSource;

    @Override
    public List<User> getAllUsers() {
        List<User> result=new ArrayList<>();
        try(Connection connection=dataSource.getConnection();
                Statement st=connection.createStatement();
                ResultSet rs= st.executeQuery(SELECT_ALL)){
            
            while(rs.next()){
                result.add(getUserFromResultSet(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(JDBCUserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public User getUser(long id) {
        try(
            Connection connection = dataSource.getConnection();
            PreparedStatement ps = 
                connection.prepareStatement(
                        SELECT_ID)){
            
            ps.setLong(1, id);
            
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                return getUserFromResultSet(rs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsersManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    private User getUserFromResultSet(ResultSet rs) throws SQLException {
        User result=new User();
        result.setId(rs.getLong("id"));
        result.setAccountVerified(rs.getBoolean("validated"));
        result.setCreated(rs.getTimestamp("created").toInstant());
        result.setEmail(rs.getString("email"));
        
        long addressId = rs.getLong("address_id");
        Address address=new Address();
        address.setId(addressId);
        result.setAddress(address);
        
        result.setUsername(rs.getString("username"));
        result.setPassword(rs.getString("password"));
        result.setKarma(rs.getInt("karma"));

        return result;
    }

    @Override
    public void storeUser(User us) {
        try(Connection con=dataSource.getConnection();
                PreparedStatement ps =con.prepareStatement(INSERT, 
                        Statement.RETURN_GENERATED_KEYS)){
            ps.setString(1,us.getUsername());
            ps.setString(2,us.getPassword());
            ps.setString(3,us.getEmail());
            ps.setLong(4,us.getAddress().getId());
            ps.setBoolean(5, us.isAccountVerified());
            ps.setTimestamp(6, Timestamp.from(us.getCreated()));
            ps.setInt(7, us.getKarma());
            
            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys();
            
            if(rs.next()){
                us.setId(rs.getLong(1));
            }else{
                //TODO llençar error
                System.out.println("No ha retornat l'id insertat");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(JDBCUserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
