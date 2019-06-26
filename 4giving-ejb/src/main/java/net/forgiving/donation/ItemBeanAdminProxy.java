/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.forgiving.donation;

import javax.annotation.security.RunAs;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.PathParam;
import net.forgiving.donation.persistence.ItemStorageException;

/**
 *
 * @author gabalca
 */
@Stateless
@RunAs("Admin")
public class ItemBeanAdminProxy {
    @EJB
    private ItemBean itemBean;
    
    public void deleteItem(long id) throws ItemStorageException{
        itemBean.deleteItem(id);
    }
    
}
