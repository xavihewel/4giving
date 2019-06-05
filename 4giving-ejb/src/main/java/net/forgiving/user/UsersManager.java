/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.forgiving.user;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import net.forgiving.common.user.User;
import net.forgiving.user.persistence.UserDao;

/**
 *
 * @author gabalca
 */
@Stateless
public class UsersManager {
    
    @Inject
    private UserDao userDao;
    
    
    public User getUser(long id){
        User result = userDao.getUser(id);
        
        return result;
    }
    
    public List<User> getAllUsers(){
        
        return userDao.getAllUsers();
    }
    
}
