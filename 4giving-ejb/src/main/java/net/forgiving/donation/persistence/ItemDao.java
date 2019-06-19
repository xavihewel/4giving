/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.forgiving.donation.persistence;

import net.forgiving.common.donation.Item;

/**
 *
 * @author gabalca
 */
public interface ItemDao {
    void storeItem(Item i) throws ItemStorageException;
    Item getItemById(long id) throws ItemStorageException;
}
