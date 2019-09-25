/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.forgiving.donation.shipping;

import java.io.Serializable;
import net.forgiving.common.donation.Donation;

/**
 *
 * @author xavier.verges
 */
public class ShippingInfo implements Serializable {

    private long donationId;
    private long userId;

    public ShippingInfo(Donation d) {
        this.donationId = d.getId();
        this.userId = d.getDonator().getId();
    }

    public long getDonationId() {
        return donationId;
    }

    public void setDonationId(long donationId) {
        this.donationId = donationId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

}
