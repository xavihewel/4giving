/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.forgiving.donation.category;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.DependsOn;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import net.forgiving.common.donation.Category;
import net.forgiving.donation.category.persistence.CategoryDao;

/**
 *
 * @author gabalca
 */
@Singleton
//@Startup
//@DependsOn({"un altre singleton"," el segon"})
//@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
public class CategoryManager {
    
    private Set<Category> categories;
    
    @Inject
    private CategoryDao categoryDao;
    
    @PostConstruct
    public void init(){
        System.out.println("Inicialitzant categories");
        categories=new HashSet<>(categoryDao.getAllCategories());
    }
    
    @Lock(LockType.READ)
    public Category getCategory(Long id){
        Optional<Category> result= 
                categories.stream()
                .filter(cat -> cat.getId().equals(id))
                .findAny();
        return result.orElse(null);                
    }
    
    @Lock(LockType.READ)
    public Set allCategories(){
        return categories;
    }
    
    @Lock(LockType.WRITE)
    public void addCategory(Category cat){
        categoryDao.storeCategory(cat);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException ex) {
            Logger.getLogger(CategoryManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        categories=new HashSet<>(categoryDao.getAllCategories());
    }
    
}
