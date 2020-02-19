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
import model.Suppliers;



import util.DAOResources;

/**
 *
 * @author Tieuphieu
 */
public class SupplierDAO extends DAOResources {

    public SupplierDAO() {
    }

   
    
    
      public List<Suppliers> viewAll() {
        List<Suppliers> get = new ArrayList<Suppliers>();
        try {
            String sql = "select * from suppliers";
            Connection connection = this.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Suppliers suppliers = new Suppliers();
                suppliers.setSup_id(rs.getInt("SUP_ID"));
                suppliers.setSup_name(rs.getString("SUP_Name"));
                suppliers.setSup_address(rs.getString("SUP_Address"));
       
                get.add(suppliers);
            }
            rs.close();
            stmt.close();
            this.closeConnection();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return get;
    }

    public void deleteASupplier(String id, String url) {

        try {
            int intId = Integer.parseInt(id);
            Suppliers oldSupplier = findExactlySupplier(intId);
     
            String sql = "delete from suppliers where SUP_ID = " + id;
            String sql1 = "delete from games where SUP_ID = " + id;
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

    public boolean addNewSupllier(String name, String address) {
        boolean result = true;
        try {
            Connection connection = this.getConnection();
            String sql = " insert into suppliers (SUP_Name, SUP_Address) values (?, ?) ";
            PreparedStatement psta = (PreparedStatement) connection.prepareStatement(sql);
            psta.setString(1, name);
            psta.setString(2, address);
       
            psta.execute();
            psta.close();
            this.closeConnection();
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());
            result = false;
        }
        return result;
    }

    public Suppliers findExactlySupplier(int id) {
        Suppliers result = new Suppliers();
        for (Suppliers each : viewAll()) {
            if (each.getSup_id() == id) {
                result = each;
            }
        }
        return result;
    }

    public boolean updateSupplier(String name,String add, String oldId) {
        boolean result = true;
        try {
            Connection connection = this.getConnection();
            String sql = "update suppliers set SUP_Name = ?, SUP_Address = ? where SUP_ID = " + oldId;
            PreparedStatement psta = (PreparedStatement) connection.prepareStatement(sql);
            psta.setString(1, name);
            psta.setString(2, add);
            
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
