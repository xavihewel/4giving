/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.forgiving.donation.persistence;

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
import net.forgiving.common.donation.Item;

/**
 *
 * @author gabalca
 */
@Dependent
public class JDBCItemDao implements ItemDao{
    
    public static final String SELECT_ID="SELECT * FROM ITEM WHERE ID = ?";
    public static final String SELECT_CATEG="SELECT * FROM ITEM_CATEGORY WHERE ITEM_ID = ?";
    public static final String INSERT="INSERT INTO ITEM (description,picUrl)"
            + " VALUES (?,?)";
    public static final String INSERT_CATEG="INSERT INTO ITEM_CATEGORY "
            + "(item_id,categ_id)"
            + " VALUES (?,?)";
    
    @Resource(name  = "jdbc/forgivingDS")
    private DataSource dataSource;

    @Override
    public void storeItem(Item i) throws ItemStorageException {
        
        try(Connection con=dataSource.getConnection();
                PreparedStatement ps1=con.prepareStatement(INSERT,
                        Statement.RETURN_GENERATED_KEYS);
                PreparedStatement ps2 = con.prepareStatement(INSERT_CATEG)){
            
            ps1.setString(1, i.getDescription());
            ps1.setString(2, i.getPicUrl());
            
            ps1.executeUpdate();
            
            ResultSet rs= ps1.getGeneratedKeys();
            
            if(rs.next()){
                i.setId(rs.getLong(1));
                //guardem les categories
                if(i.getCategory()!=null){
                    for(Category c:i.getCategory()){
                        ps2.setLong(1, i.getId());
                        ps2.setLong(2, c.getId());
                        ps2.executeUpdate();
                    }
                }
            }else{
                Logger.getLogger(JDBCItemDao.class.getName()).log(Level.SEVERE, 
                        "Item saved but no id returned");
                throw new ItemStorageException(true, "Error inserting new item:"
                        + " no id generated");
            }
                        
        } catch (SQLException ex) {
            Logger.getLogger(JDBCItemDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new ItemStorageException(true, "Error inserting new item", ex);
        }
    }

    @Override
    public Item getItemById(long id) throws ItemStorageException {
        try(Connection con=dataSource.getConnection();
                PreparedStatement ps1=con.prepareStatement(SELECT_ID);
                PreparedStatement ps2 = con.prepareStatement(SELECT_CATEG)){
            ps1.setLong(1, id);
            
            ResultSet rs = ps1.executeQuery();
            
            if(rs.next()){
                Item result = getItemFromResultSet(rs);
                ps2.setLong(1, id);
                ResultSet rs2=ps2.executeQuery();
                List<Category> categories = new ArrayList<>();
                while(rs2.next()){
                    Category cat = new Category();
                    cat.setId(rs2.getLong("categ_id"));
                    categories.add(cat);
                }
                result.setCategory(categories);
                return result;
            }
            
            throw new ItemStorageException(false, "Could not find item with id "+id);
            
        } catch (SQLException ex) {
            Logger.getLogger(JDBCItemDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new ItemStorageException(false, "Error loading item", ex);
        }
    }

    private Item getItemFromResultSet(ResultSet rs) throws SQLException{
        Item it=new Item();
        it.setId(rs.getLong("id"));
        it.setDescription(rs.getString("description"));
        it.setPicUrl(rs.getString("picUrl"));
        return it;
    }
    
}
