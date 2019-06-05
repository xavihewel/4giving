/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.forgiving.user.persistence;

import net.forgiving.common.user.Address;

/**
 *
 * @author gabalca
 */
public interface AddressDao {
    public Address getAddress(long id);
    public void storeAddress(Address adresss);
}
