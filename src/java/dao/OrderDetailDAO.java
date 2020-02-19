/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import model.OrderDetail;
import util.DAOResources;

/**
 *
 * @author Thanh
 */
public class OrderDetailDAO extends DAOResources{

    public OrderDetailDAO() {
    }
    
     public void addNewOrderDetail(OrderDetail detail) {
        String sql = "insert into order_details (DELIVERY, PRICE, user_order_id) "
                + "values (?, ?, ?)";
        Connection connection = this.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, detail.getDelivery());
            ps.setDouble(2, detail.getPrice());
            ps.setInt(3, detail.getUser_order_id());
            ps.execute();
            ps.close();
            this.closeConnection();
        } catch (Exception ex) {
        }
     }
}