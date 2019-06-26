/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.forgiving.donation;

import javax.ejb.Local;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import net.forgiving.common.donation.Item;
import net.forgiving.donation.persistence.ItemStorageException;

/**
 *
 * @author gabalca
 */
//@Path("/items")
//@Local

public interface ItemLocal {
    /*
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void createItem(Item i) throws ItemStorageException;
    
    @GET
    @Path("/{itemId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Item getItemById(@PathParam("itemId") long id) throws ItemStorageException;
    
    @DELETE
    @Path("/{itemId}")
    //@RolesAllowed("Admin")
    public void deleteItem(@PathParam("itemId") long id) throws ItemStorageException;

*/
}
