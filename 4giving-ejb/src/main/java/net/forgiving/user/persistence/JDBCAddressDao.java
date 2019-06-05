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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.enterprise.context.Dependent;
import javax.sql.DataSource;
import net.forgiving.common.user.Address;

/**
 *
 * @author gabalca
 */
@Dependent
public class JDBCAddressDao implements AddressDao{
    
    @Resource(name  = "jdbc/forgivingDS")
    private DataSource dataSource;

    @Override
    public Address getAddress(long id) {
        try(
            Connection connection = dataSource.getConnection();
            PreparedStatement ps = 
                connection.prepareStatement(
                        "SELECT * FROM ADDRESS WHERE ID = ?")){
            
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
