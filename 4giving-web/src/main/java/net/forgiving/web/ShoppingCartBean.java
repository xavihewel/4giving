/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.forgiving.web;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import net.forgiving.common.donation.Item;

/**
 *
 * @author gabalca
 */
@Stateful
public class ShoppingCartBean {
    
    List<Item> items;
    
    @PostConstruct
    public void init(){
        items=new ArrayList<>();
    }
    
    public void addItem(Item i){
        items.add(i);
    }
    
    @Remove
    public void cancelCart(){
        items.clear();
    }
    @Remove
    public void orderItems(){
        //atacar a altres EJB que processin els items
    }
    
}
