/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.forgiving.donation.persistence;

/**
 *
 * @author gabalca
 */
public interface DonationsLogDao {
    void logDonationAction(long donationId, String action);
}
