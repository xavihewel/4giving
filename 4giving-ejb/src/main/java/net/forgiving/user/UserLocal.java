/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.forgiving.user;

import java.util.List;
import javax.ejb.Local;
import net.forgiving.common.user.User;

/**
 *
 * @author gabalca
 */
@Local
public interface UserLocal {
    public List<User> getAllUsers();
    public User getUser(long id);
}
