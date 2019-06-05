/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.forgiving.common.donation;

import java.time.Instant;
import net.forgiving.common.user.User;

/**
 *
 * @author gabalca
 */
public class Petition {
    
    private Long id;
    
    private User petitioner;
    private Donation donation;
    private Instant created; 
    private int maxKarmaCost;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getPetitioner() {
        return petitioner;
    }

    public void setPetitioner(User petitioner) {
        this.petitioner = petitioner;
    }

    public Donation getDonation() {
        return donation;
    }

    public void setDonation(Donation donation) {
        this.donation = donation;
    }

    public Instant getCreated() {
        return created;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    public int getMaxKarmaCost() {
        return maxKarmaCost;
    }

    public void setMaxKarmaCost(int maxKarmaCost) {
        this.maxKarmaCost = maxKarmaCost;
    }

    @Override
    public String toString() {
        return "Petition{" + "id=" + id + ", petitioner=" + petitioner + ", donation=" + donation + ", created=" + created + ", maxKarmaCost=" + maxKarmaCost + '}';
    }
    
    
    
}
