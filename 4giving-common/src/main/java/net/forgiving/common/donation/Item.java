/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.forgiving.common.donation;

import java.util.List;

/**
 *
 * @author gabalca
 */
public class Item {
    
    private Long id;
    
    private String description;
    private String picUrl;
    private List<Category> category;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Category> getCategory() {
        return category;
    }

    public void setCategory(List<Category> category) {
        this.category = category;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    @Override
    public String toString() {
        return "Item{" + "id=" + id + ", description=" + description + ", picUrl=" + picUrl + ", category=" + category + '}';
    }
    
    
    
}
