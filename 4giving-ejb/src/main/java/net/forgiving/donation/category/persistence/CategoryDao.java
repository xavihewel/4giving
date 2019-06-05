/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.forgiving.donation.category.persistence;

import java.util.List;
import net.forgiving.common.donation.Category;

/**
 *
 * @author gabalca
 */
public interface CategoryDao {
    public List<Category> getAllCategories();
    public void storeCategory(Category cat);
    public Category getCategory(long id);
}
