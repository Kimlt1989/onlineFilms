/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.mysql.jdbc.PreparedStatement;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Categories;
import model.Games;

import util.DAOResources;

/**
 *
 * @author Tieuphieu
 */
public class CategoryDAO extends DAOResources {

    public CategoryDAO() {
    }

   
    
    
      public List<Categories> viewAll() {
        List<Categories> get = new ArrayList<Categories>();
        try {
            String sql = "select * from categories";
            Connection connection = this.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Categories categories = new Categories();
                categories.setCat_id(rs.getInt("CAT_ID"));
                categories.setCat_name(rs.getString("CAT_Name"));
                categories.setCat_image(rs.getString("CAT_Image"));
                categories.setCat_description(rs.getString("CAT_Description"));
                get.add(categories);
            }
            rs.close();
            stmt.close();
            this.closeConnection();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return get;
    }

    public void deleteACategories(String id, String url) {

        try {
            int intId = Integer.parseInt(id);
            Categories oldCategory = findExactlyCategory(intId);
            String oldFileUrl1 = url + "/" + oldCategory.getCat_image();
            File oldFile1 = new File(oldFileUrl1);
            System.out.println(oldFileUrl1);
            oldFile1.delete();
            String sql = "delete from categories where CAT_ID = " + id;
            String sql1 = "delete from games where CAT_ID = " + id;
            Connection connection = this.getConnection();
            Statement statement = connection.createStatement();
            statement.execute(sql1);
            statement.execute(sql);
            statement.close();
            this.closeConnection();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public boolean addNewCategory(String name, String des, String image) {
        boolean result = true;
        try {
            Connection connection = this.getConnection();
            String sql = " insert into categories (CAT_Name, CAT_Description, CAT_Image) values (?, ?, ?) ";
            PreparedStatement psta = (PreparedStatement) connection.prepareStatement(sql);
            psta.setString(1, name);
            psta.setString(2, des);
            psta.setString(3, image);
            psta.execute();
            psta.close();
            this.closeConnection();
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());
            result = false;
        }
        return result;
    }

    public Categories findExactlyCategory(int id) {
        Categories result = new Categories();
        for (Categories each : viewAll()) {
            if (each.getCat_id() == id) {
                result = each;
            }
        }
        return result;
    }

    public boolean updateCategory(String name,String des,String image, String oldId) {
        boolean result = true;
        try {
            Connection connection = this.getConnection();
            String sql = "update categories set CAT_Name = ?, CAT_Description = ?, CAT_Image = ? where CAT_ID = " + oldId;
            PreparedStatement psta = (PreparedStatement) connection.prepareStatement(sql);
            psta.setString(1, name);
            psta.setString(2, des);
            psta.setString(3, image);
            psta.execute();
            psta.close();
            this.closeConnection();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            result = false;
        }
        return result;

    }
    
    
}
