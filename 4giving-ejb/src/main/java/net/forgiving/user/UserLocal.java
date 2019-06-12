/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.forgiving.user;

import java.util.List;
import javax.ejb.Local;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import net.forgiving.common.user.User;

/**
 *
 * @author gabalca
 */
@Local
@Path("/users")
public interface UserLocal {
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getAllUsers();
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{userId}")
    public User getUser(long id);
}
