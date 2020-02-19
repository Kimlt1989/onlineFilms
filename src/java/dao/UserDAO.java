package dao;

import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.User;
import util.DAOResources;

public class UserDAO extends DAOResources {
   public UserDAO() {
    }
    public List<User> findAll(){
        String sql = "select * from users where typeuser_id = 1"; 
        List<User> users = new ArrayList<User>();
        try{
            Connection connection = this.getConnection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                User user = new User();                
                
                user.setId(rs.getInt("user_id"));
                user.setAddress(rs.getString("address"));
                user.setDOB(rs.getString("dob"));
                user.setEmail(rs.getString("email"));
                user.setFirstName(rs.getString("firstname"));
                user.setGender(rs.getInt("gender"));
                user.setLastName(rs.getString("lastname"));
                user.setPassword(rs.getString("password"));
                user.setPhone(rs.getString("phone"));
                user.setUserName(rs.getString("username"));
                user.setUserType(rs.getInt("typeuser_id"));
                
                users.add(user);
            }
            rs.close();
            st.close();
            this.closeConnection();
        }
        catch (Exception ex) {
            System.out.print("Failed !" + ex.getMessage());
        }
        return users;
    }
    
    public String addNew(User user) {
        String message = "Done";
        try {
            Connection connection = this.getConnection();
            String sql = "insert into users (typeuser_id, username, password, firstname, lastname, "
                    + "dob, gender, phone, address, email) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
            PreparedStatement psta = (PreparedStatement) connection.prepareStatement(sql);
            psta.setInt(1, user.getUserType());
            psta.setString(2, user.getUserName());
            psta.setString(3, user.getPassword());
            psta.setString(4, user.getFirstName());
            psta.setString(5, user.getLastName());
            psta.setString(6, user.getDOB());
            psta.setInt(7, user.getGender());
            psta.setString(8, user.getPhone());
            psta.setString(9, user.getAddress());
            psta.setString(10, user.getEmail());
            psta.execute();
            psta.close();
            this.closeConnection();
        } catch (Exception ex) {
            message = "Failed !" + ex.getMessage();
        }

        return message;
    }

    public User findUserByNameAndPassword(String username, String password) {
        User user = null;
        try {
            Connection connection = this.getConnection();
            String sql = "select * from users where username = "
                    + "'" + username + "'" + " and password = " + "'" + password + "'";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("user_id"));
                user.setAddress(rs.getString("address"));
                user.setDOB(rs.getString("dob"));
                user.setEmail(rs.getString("email"));
                user.setFirstName(rs.getString("firstname"));
                user.setGender(rs.getInt("gender"));
                user.setLastName(rs.getString("lastname"));
                user.setPassword(rs.getString("password"));
                user.setPhone(rs.getString("phone"));
                user.setUserName(rs.getString("username"));
                user.setUserType(rs.getInt("typeuser_id"));
            }
            rs.close();
            statement.close();
            this.closeConnection();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return user;
    }

    public User findUserById(String id) {
        User user = new User();
        try {
            Connection connection = this.getConnection();
            String sql = "select * from users where user_id = " + id;
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
                user.setId(rs.getInt("user_id"));
                user.setAddress(rs.getString("address"));
                user.setDOB(rs.getString("dob"));
                user.setEmail(rs.getString("email"));
                user.setFirstName(rs.getString("firstname"));
                user.setGender(rs.getInt("gender"));
                user.setLastName(rs.getString("lastname"));
                user.setPassword(rs.getString("password"));
                user.setPhone(rs.getString("phone"));
                user.setUserName(rs.getString("username"));
                user.setUserType(rs.getInt("typeuser_id"));
            }
            rs.close();
            statement.close();
            this.closeConnection();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return user;
    }

    public boolean findAdminAccount(String id) {
        boolean result = true;
        User u = findUserById(id);
        if (u.getUserType() == 2) {
            result = true;
        } else {
            result = false;
        }
        return result;
    }
    public void deleteUser(User user){
        String sql = "delete from users where user_id=" + user.getId();
        try{
            Connection connection = this.getConnection();
            Statement st = connection.createStatement();
            st.execute(sql);
            st.close();
            this.closeConnection();
        }
        catch(SQLException ex){
            System.out.println(ex);
        }
    }
    public void updateUserInfo(User user){
        String sql= "update users set email ='"+ user.getEmail()+ "', phone= '"+ user.getPhone()
                + "', address = '"+ user.getAddress()+ "' where user_id = "+ user.getId();
        try{
            Connection connection = this.getConnection();
            Statement st = connection.createStatement();
            st.executeUpdate(sql);
            st.close();
            this.closeConnection();
        }
        catch(SQLException ex){
            System.out.println(ex);
        }
    }
    public void changePassword(User user){
        String sql= "update users set password ='"+ user.getPassword()+"' where user_id = "+ user.getId();
        try{
            Connection connection = this.getConnection();
            Statement st = connection.createStatement();
            st.executeUpdate(sql);
            st.close();
            this.closeConnection();
        }
        catch(SQLException ex){
            System.out.println(ex);
        }
    }
}
