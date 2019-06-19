/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.forgiving.donation.persistence;

import net.forgiving.common.donation.Donation;

/**
 *
 * @author gabalca
 */
public interface DonationsDao {
    void storeDonation(Donation d) throws DonationStorageException;
    Donation getDonationById(long id) throws DonationStorageException;
}
