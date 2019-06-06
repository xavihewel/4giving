/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.forgiving.user;

import java.time.Instant;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.inject.Inject;
import net.forgiving.common.user.Address;
import net.forgiving.common.user.User;
import net.forgiving.user.persistence.AddressDao;
import net.forgiving.user.persistence.UserDao;

/**
 *
 * @author gabalca
 */
@Stateful
public class CreationUserBean {
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
        user.setCreated(Instant.now());
        user.setAccountVerified(false);
        user.setKarma(100);
        
        addressDao.storeAddress(address);
        user.setAddress(address);
        userDao.storeUser(user);
    }
    
    @Remove
    public void cancelCreation(){
        user=null;
        address=null;
        System.out.println("cancelling creation of user");
    }
    
}
