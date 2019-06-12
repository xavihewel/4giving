/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.forgiving.user;

import java.util.List;
import javax.ejb.Remote;
import net.forgiving.common.user.User;

/**
 *
 * @author gabalca
 */
@Remote
public interface UserRemote {
    public List<User> getAllUsers();
}
