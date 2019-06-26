/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.forgiving.donation;

import javax.ejb.Stateless;
import javax.inject.Inject;
import net.forgiving.common.donation.Donation;
import net.forgiving.donation.persistence.DonationsLogDao;

/**
 *
 * @author gabalca
 */
@Stateless
public class DonationLogBean {
    
    @Inject
    private DonationsLogDao logDao;
    
    public void donationAdded(Donation d){
        logDao.logDonationAction(d.getId(), "CREATED");
    }
    
    public void donationCanceled(Donation d){
        logDao.logDonationAction(d.getId(), "CANCELED");
    }
    
}
