/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.forgiving.test;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import net.forgiving.donation.category.CategoryLocal;

/**
 *
 * @author gabalca
 */
@Stateless
@Dependent
public class TestBean {
    
    public String getTest(){
        return "Testing! ";
    }
}
