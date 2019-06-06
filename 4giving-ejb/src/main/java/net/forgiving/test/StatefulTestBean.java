/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.forgiving.test;

import javax.ejb.Remove;
import javax.ejb.Stateful;

/**
 *
 * @author gabalca
 */
@Stateful
public class StatefulTestBean {
    private String test;
    private int step;
    
    public String getTest(){
        return test+": step "+step;
    }
    
    public void setTest(String test){
        step++;
        this.test=test;
    }
    
    @Remove
    public void finish(){
        System.out.println("Finishing");
    }
}
