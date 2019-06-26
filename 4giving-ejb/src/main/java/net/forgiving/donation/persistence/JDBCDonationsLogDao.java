/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.forgiving.donation.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.enterprise.context.Dependent;
import javax.sql.DataSource;

/**
 *
 * @author gabalca
 */
@Dependent
public class JDBCDonationsLogDao implements DonationsLogDao{
    
    @Resource(name  = "jdbc/forgivingDSAlternate")
    private DataSource dataSourceLog;
    
    @Resource
    SessionContext context;

    @Override
    public void logDonationAction(long donationId, String action) {
        try(Connection con=dataSourceLog.getConnection();
                PreparedStatement ps = con.prepareStatement("INSERT "
                    + "INTO DONATIONS_LOG (donation_id,action_done) VALUES"
                    + "(?,?)")){
            
            ps.setLong(1, donationId);
            ps.setString(2, action);
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(JDBCDonationsLogDao.class.getName()).log(Level.SEVERE, null, ex);
            context.setRollbackOnly();
            throw new RuntimeException("SQL exception saving the log"+ex);
        }
    }
    
}
