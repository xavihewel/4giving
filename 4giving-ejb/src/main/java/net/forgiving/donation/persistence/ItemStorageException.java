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
public class ItemStorageException extends Exception{
    
    boolean wasNew;
    long itemId;

    public ItemStorageException(boolean wasNew, String message) {
        super(message);
        this.wasNew = wasNew;
    }

    public ItemStorageException(boolean wasNew, String message, Throwable cause) {
        super(message, cause);
        this.wasNew = wasNew;
    }

    public boolean isWasNew() {
        return wasNew;
    }

    public void setWasNew(boolean wasNew) {
        this.wasNew = wasNew;
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }
    
    
    
}
