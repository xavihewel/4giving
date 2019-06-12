/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.forgiving.donation.category;

import java.util.Set;
import javax.ejb.Local;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import net.forgiving.common.donation.Category;

/**
 *
 * @author gabalca
 */
@Local
@Path("/categories")
public interface CategoryLocal {
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Set allCategories();
    
    @GET
    @Path("/{catId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Category getCategory(@PathParam("catId") Long id);
    public Category addCategory(Category cat);
}
