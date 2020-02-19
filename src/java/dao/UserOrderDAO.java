/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.UserOrder;
import util.DAOResources;


public class UserOrderDAO extends DAOResources {

    public UserOrderDAO() {
    }

    public void addNewOrder(UserOrder userOrder) {
        String sql = "insert into user_order (USERORDER_ID, USER_ID, NAME, PHONE, ADDRESS, CONFIRM, GAMEIDS) "
                + "values (?, ?, ?, ?, ?, ?, ?)";
        Connection connection = this.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, userOrder.getOrderId());
            ps.setInt(2, userOrder.getUserId());
            ps.setString(3, userOrder.getName());
            ps.setString(4, userOrder.getPhone());
            ps.setString(5, userOrder.getAddress());
            ps.setInt(6, userOrder.getConfirm());
            ps.setString(7, userOrder.getGameId());
            ps.execute();
            ps.close();
            this.closeConnection();
        } catch (Exception ex) {
        }
    }
    
    public List<UserOrder> getUserOrder() {
        List<UserOrder> getAll = new ArrayList<UserOrder>();
        try {
            String sql = "select * from user_order order by NAME";
            Connection connection = this.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                UserOrder userOrder = new UserOrder();
                userOrder.setOrderId(rs.getInt("USERORDER_ID"));
                userOrder.setUserId(rs.getInt("USER_ID"));
                userOrder.setName(rs.getString("NAME"));
                userOrder.setPhone(rs.getString("PHONE"));
                userOrder.setAddress(rs.getString("ADDRESS"));
                userOrder.setConfirm(rs.getInt("CONFIRM"));
                userOrder.setGameId(rs.getString("GAMEIDs"));
                getAll.add(userOrder);
            }
            rs.close();
            statement.close();
            this.closeConnection();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return getAll;
    }

    public UserOrder findExactlyUserOrder(int id) {
        UserOrder userOrder = new UserOrder();
        for (UserOrder each : getUserOrder()) {
            if (each.getOrderId() == id) {
                userOrder = each;
            }
        }
        return userOrder;
    }
    
     public List<UserOrder> findExactlyUserOrderByOneUser(int userId) {
        List<UserOrder> list = new ArrayList<UserOrder>();
        for (UserOrder each : getUserOrder()) {
            if (each.getUserId()== userId) {
                list.add(each);
            }
        }
        return list;
    }
     
      public void update(UserOrder userOrder, String id) {
        String sql = "update user_order set confirm = ? where userorder_id = " + id;
        Connection connection = this.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, userOrder.getConfirm());
            ps.execute();
            ps.close();
            this.closeConnection();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
