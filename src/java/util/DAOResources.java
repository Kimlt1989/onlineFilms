/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

/**
 *
 * @author Administrator
 */
public class DAOResources {
    private static final String USER_NAME="root";
    private static final String PASSWORD ="123";
    private static final String URL ="jdbc:mysql://localhost:3306/game_store?user=;password=";
    private Connection connection;

    public DAOResources() {
    }

    public Connection getConnection() {
        this.initConnection();
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    public void initConnection(){
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            this.connection = DriverManager.getConnection(URL,USER_NAME,PASSWORD);
            System.out.println("Database connection established");
            
        }catch(Exception e){
            System.out.println(e);
            System.out.println("Cannot connect to MySQL Server");
        }
    }
    public void closeConnection(){
        try{
            if(!this.connection.isClosed()){
                   this.connection.close();
                   System.out.println("Database connection terminated");
            }
        }catch(SQLException ex){
        
        }
    }
}
