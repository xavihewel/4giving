/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.forgiving.donation.category.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.enterprise.context.Dependent;
import javax.sql.DataSource;
import net.forgiving.common.donation.Category;
import net.forgiving.user.persistence.JDBCUserDao;

/**
 *
 * @author gabalca
 */
@Dependent
public class JDBCCategoryDao implements CategoryDao{
    
    @Resource(name  = "jdbc/forgivingDS")
    private DataSource dataSource;

    @Override
    public List<Category> getAllCategories() {
        List<Category> result= new ArrayList<>();
        try(
            Connection connection = dataSource.getConnection();
            PreparedStatement ps = 
                connection.prepareStatement(
                        "SELECT * FROM CATEGORY")){
            
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                result.add(getCategoryFromResultSet(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(JDBCUserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    private Category getCategoryFromResultSet(ResultSet rs) throws SQLException{
        Category cat = new Category(rs.getLong("id"), rs.getString("name"));
        return cat;
    }

    @Override
    public void storeCategory(Category cat) {
        
        boolean insert=true;
        String sql="INSERT INTO CATEGORY (name) VALUES (?)";
        if(cat.getId()!=null){
            insert=false;
            sql="UPDATE CATEGORY SET name = ? WHERE id = ?";
        }
        try(
            Connection connection = dataSource.getConnection();
            PreparedStatement ps = 
                connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            
            ps.setString(1, cat.getName());
            if(!insert){
                ps.setLong(2, cat.getId());
            }
            ps.executeUpdate();
            
            if(insert){
                //recuperem el id per posar-lo a la categoria
                ResultSet rs = ps.getGeneratedKeys();
                if(rs.next()){
                    cat.setId(rs.getLong(1));
                    System.out.println("Afegida nova categoria "+cat);
                }else{
                    //TODO llençar excepció
                    System.out.println("No he obtingut l'ID al crear "+cat);
                    
                }
            }else{
                System.out.println("modificada la categoria "+cat);
            }
        
        } catch (SQLException ex) {
            Logger.getLogger(JDBCUserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Category getCategory(long id) {
        try(
            Connection connection = dataSource.getConnection();
            PreparedStatement ps = 
                connection.prepareStatement(
                        "SELECT * FROM CATEGORY WHERE ID = ?")){
            ps.setLong(1, id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                return getCategoryFromResultSet(rs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(JDBCUserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
