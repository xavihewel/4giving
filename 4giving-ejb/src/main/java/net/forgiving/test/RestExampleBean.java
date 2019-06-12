/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.forgiving.test;

import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author gabalca
 */
@Stateless
public class RestExampleBean implements RestExampleInterface{
    
    
    public String getExample(@PathParam("exemple1") String exemple){
        return "exemple: "+exemple;
    }
    
    public String getExample2(){
        return "exemple2";
    }
    
    public String metodeNoExposat(){
        return "no exposat";
    }
}
