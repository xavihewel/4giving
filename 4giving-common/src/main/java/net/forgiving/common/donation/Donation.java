/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.forgiving.common.donation;

import java.time.Instant;
import java.util.List;
import net.forgiving.common.user.User;

/**
 *
 * @author gabalca
 */
public class Donation {

    private Long id;
    private User donator;
    private Item item;
    private Conditions conditions;
    private int karmaCost;
    private Instant created;
    private DonationStatus status;
    

    private List<Petition> petitons;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getDonator() {
        return donator;
    }

    public void setDonator(User donator) {
        this.donator = donator;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Conditions getConditions() {
        return conditions;
    }

    public void setConditions(Conditions conditions) {
        this.conditions = conditions;
    }

    public int getKarmaCost() {
        return karmaCost;
    }

    public void setKarmaCost(int karmaCost) {
        this.karmaCost = karmaCost;
    }

    public Instant getCreated() {
        return created;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    public List<Petition> getPetitons() {
        return petitons;
    }

    public void setPetitons(List<Petition> petitons) {
        this.petitons = petitons;
    }

    @Override
    public String toString() {
        return "Donation{" + "id=" + id + ", donator=" + donator + ", item=" + item + ", conditions=" + conditions + ", karmaCost=" + karmaCost + ", created=" + created + ", status=" + status + ", petitons=" + petitons + '}';
    }
    
    
    
}
