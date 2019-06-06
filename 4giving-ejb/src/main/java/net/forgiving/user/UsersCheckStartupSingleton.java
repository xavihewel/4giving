/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.forgiving.user;

import java.time.Instant;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import net.forgiving.common.user.Address;
import net.forgiving.common.user.User;
import net.forgiving.user.persistence.AddressDao;
import net.forgiving.user.persistence.UserDao;

/**
 *
 * @author gabalca
 */
@Singleton(name = "userStartup")
//@Startup
public class UsersCheckStartupSingleton {
    
    @Inject 
    private UserDao userDao;
    @Inject
    private AddressDao addressDao;
    
    @PostConstruct
    public void init(){
        System.out.println("Comprovant que hi hagi usuaris 33...");
        
        List<User> users=userDao.getAllUsers();
        if(users.size()<1){
            System.out.println("Cal crear un usuari ");
            User user=new User();
            user.setUsername("defaultUser");
            user.setPassword("defaultPass");
            user.setEmail("default@test.net");
            Address address  = new Address();
            address.setStreet("carrer prova");
            address.setNumber("32, 1r 3a");
            address.setProvince("BCN");
            address.setState("ES");
            address.setZip("08345");
            user.setAddress(address);
            user.setKarma(100);
            user.setAccountVerified(true);
            user.setCreated(Instant.now());
            
            addressDao.storeAddress(address);
            userDao.storeUser(user);
        }
    }
    
}
