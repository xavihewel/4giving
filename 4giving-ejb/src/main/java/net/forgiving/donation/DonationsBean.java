/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.forgiving.donation;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import net.forgiving.common.donation.Donation;
import net.forgiving.common.donation.DonationStatus;
import net.forgiving.donation.persistence.DonationStorageException;
import net.forgiving.donation.persistence.DonationsDao;
import net.forgiving.donation.persistence.ItemStorageException;

/**
 *
 * @author gabalca
 */
@Stateless
@Path("/donations")
public class DonationsBean {
    @Inject
    private DonationsDao donationsDao;
    
    @EJB
    private ItemBean itemBean;
    
    @Resource
    private SessionContext sessionCtx;
    
    @GET
    @Path("/{id}")
    public Donation getDonation(@PathParam("id") long id) 
            throws DonationStorageException{
        
        return donationsDao.getDonationById(id);
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void saveDonation(Donation d) throws DonationStorageException{
        d.setCreated(Instant.now());
        d.setStatus(DonationStatus.OPENED);
        d.setKarmaCost(2);
        if(d.getResolvingDeadline()==null){
            d.setResolvingDeadline(Instant.now().plus(7,ChronoUnit.DAYS));
        }
        
        if(d.getItem()!=null){
            try{
                itemBean.createItem(d.getItem());
                if(!sessionCtx.getRollbackOnly()){
                    donationsDao.storeDonation(d);
                }
            }catch(ItemStorageException ex){
                System.out.println("Exception saving Item"); 
            }
            
        }else{
            //no item, abort
            System.out.println("No item, aborting creation of item");
            throw new DonationStorageException("Can "
                    + "not create Donation without Item");
        }
        
    }
    
    
}
