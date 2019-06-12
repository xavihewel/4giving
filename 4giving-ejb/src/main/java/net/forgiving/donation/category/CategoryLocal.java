/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.forgiving.donation.category;

import java.util.Set;
import javax.ejb.Local;
import net.forgiving.common.donation.Category;

/**
 *
 * @author gabalca
 */
@Local
public interface CategoryLocal {
    public Set allCategories();
    public Category getCategory(Long id);
    public Category addCategory(Category cat);
}
