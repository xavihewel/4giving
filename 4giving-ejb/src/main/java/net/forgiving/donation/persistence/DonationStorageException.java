/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.forgiving.donation.persistence;

import javax.ejb.ApplicationException;

/**
 *
 * @author gabalca
 */
@ApplicationException(rollback=true)
public class DonationStorageException extends Exception {

    public DonationStorageException(String message) {
        super(message);
    }

    public DonationStorageException(String message, Throwable cause) {
        super(message, cause);
    }

}
