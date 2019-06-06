/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.forgiving.user;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;
import net.forgiving.common.user.User;
import net.forgiving.user.persistence.AddressDao;
import net.forgiving.user.persistence.UserDao;

/**
 *
 * @author gabalca
 */
@Stateless
public class UsersManager {
    
    @Inject
    private UserDao userDao;
    
    @Inject
    private AddressDao addressDao;
    
    @PostConstruct
    public void init(){
        System.out.println("Initializing User manager");
    }
    
    public User getUser(long id){
        User result = userDao.getUser(id);
        System.out.println("Setting address");
        result.setAddress(
                addressDao.getAddress(
                        result.getAddress().getId()));
        return result;
    }
    
    public List<User> getAllUsers(){
        
        return userDao.getAllUsers();
    }
    
}
