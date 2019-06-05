/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.forgiving.user.persistence;

import java.util.List;
import net.forgiving.common.user.User;

/**
 *
 * @author gabalca
 */
public interface UserDao {
    
    public List<User> getAllUsers();
    public User getUser(long userid);
    public void storeUser(User us);
    
}
