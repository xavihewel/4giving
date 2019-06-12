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
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import net.forgiving.common.donation.Category;
import net.forgiving.donation.category.persistence.CategoryDao;

/**
 *
 * @author gabalca
 */
@Stateless
@Remote(CategoryRemote.class)
public class CategoryManager  implements CategoryLocal, CategoryRemote{
    
    private Set<Category> categories;
    
    @Inject
    private CategoryDao categoryDao;
    
    @PostConstruct
    public void init(){
        System.out.println("Inicialitzant categories");
        categories=new HashSet<>(
                categoryDao.getAllCategories()
                //Arrays.asList(new Category(1L, "prova 1"),new Category(2L, "prova 2"))
        );
        //throw new RuntimeException("Error inicialitzant categories");
        
    }
    
    public Category getCategory(Long id){
        Optional<Category> result= 
                categories.stream()
                .filter(cat -> cat.getId().equals(id))
                .findAny();
        return result.orElse(null);                
    }
    //salta en el metode que s'espera, no en el que s'executa
    public Set allCategories(){
        return categories;
    }

    public Category addCategory(Category cat){
        
        categoryDao.storeCategory(cat);
        System.out.println("Guardada la categoria");
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            Logger.getLogger(CategoryManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        categories=new HashSet<>(categoryDao.getAllCategories());
        return cat;
    }
    
}
