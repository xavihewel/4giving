/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.forgiving.user;

import java.time.Instant;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.inject.Inject;
import net.forgiving.common.user.Address;
import net.forgiving.common.user.User;
import net.forgiving.mail.MailManager;
import net.forgiving.user.persistence.AddressDao;
import net.forgiving.user.persistence.UserDao;

/**
 *
 * @author gabalca
 */
@Stateful
public class CreationUserBean {
    
    @EJB
    private MailManager mailManager;
    
    private User user;
    private Address address;
    
    @Inject
    private UserDao userDao;
    @Inject
    private AddressDao addressDao;
    
    public void setUser(User us){
        this.user=us;
        //comprovar que no hi hagi un altre amb el mateix username
    }

    public void setAddress(Address address) {
        this.address = address;
    }
    
    @Remove
    public void createUser(){
        
        Future<Boolean> futureMail=mailManager.sendMail(user, 
                "Benvingut al nostre servei!", "Benvingut a 4giving");
        try{
            user.setCreated(Instant.now());
            user.setAccountVerified(false);
            user.setKarma(100);

            addressDao.storeAddress(address);
            user.setAddress(address);
            userDao.storeUser(user);
            System.out.println("He acabat de fer el missatge, espero el mail");
            try {
                Boolean result=futureMail.get(5,TimeUnit.SECONDS);
                System.out.println("El mail ha anat be? "+result);
            } catch (InterruptedException ex) {
                Logger.getLogger(CreationUserBean.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ExecutionException ex) {
                Logger.getLogger(CreationUserBean.class.getName()).log(Level.SEVERE, null, ex);
            } catch (TimeoutException ex) {
                Logger.getLogger(CreationUserBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }catch(Exception e){
            if(!futureMail.isDone()){
                futureMail.cancel(true);
            }
        }
    }
    
    @Remove
    public void cancelCreation(){
        user=null;
        address=null;
        System.out.println("cancelling creation of user");
    }
    
}
