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
import model.Games;
import util.DAOResources;

/**
 *
 * @author Tieuphieu
 */
public class GameDAO extends DAOResources {

    public GameDAO() {
    }

    public List<Games> getAllGames() {
        List<Games> getAll = new ArrayList<Games>();
        try {
            String sql = "select * from games ORDER BY name";
            Connection connection = this.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Games game = new Games();
                game.setId(rs.getInt("Id"));
                game.setCat_id(rs.getInt("CAT_ID"));
                game.setSup_id(rs.getInt("SUP_ID"));
                game.setName(rs.getString("Name"));
                game.setPrice(rs.getDouble("Price"));
                game.setQuantity(rs.getInt("Quantity"));
                game.setIssuedate(rs.getString("IssueDate"));
                game.setDescription(rs.getString("Description"));
                game.setImage(rs.getString("Image"));
                game.setCover(rs.getString("Cover"));
                game.setStatus(rs.getInt("Status"));
                game.setCoverthump(rs.getString("CoverThump"));
                game.setTrailer(rs.getString("Trailer"));
               
                getAll.add(game);
            }
            rs.close();
            statement.close();
            this.closeConnection();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return getAll;
    }

    public List<Games> getNewestGames() {
        
        List<Games> get = new ArrayList<Games>();
        try {
            String sql = "select * from games where status = 4 ORDER BY name" ;
            Connection connection = this.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                
              Games game = new Games();
                
                game.setId(rs.getInt("Id"));
                game.setCat_id(rs.getInt("CAT_ID"));
                game.setSup_id(rs.getInt("SUP_ID"));
                game.setName(rs.getString("Name"));
                game.setPrice(rs.getDouble("Price"));
                game.setQuantity(rs.getInt("Quantity"));
                game.setIssuedate(rs.getString("IssueDate"));
                game.setDescription(rs.getString("Description"));
                game.setImage(rs.getString("Image"));
                game.setCover(rs.getString("Cover"));
                game.setStatus(rs.getInt("Status"));
                game.setCoverthump(rs.getString("CoverThump"));
                game.setTrailer(rs.getString("Trailer"));
                get.add(game);
            }
            rs.close();
            statement.close();
            this.closeConnection();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return get;
    }

    public List<Games> searchByCategories(int gameId) {
        List<Games> result = new ArrayList<Games>();
        try {
            String sql = " select g.* FROM games g, categories c "
                    + "WHERE g.CAT_ID = c.CAT_ID and c.CAT_ID = "+ gameId ;
            Connection connection = this.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Games game = new Games();
                
                game.setId(rs.getInt("Id"));
                game.setCat_id(rs.getInt("CAT_ID"));
                game.setSup_id(rs.getInt("SUP_ID"));
                game.setName(rs.getString("Name"));
                game.setPrice(rs.getDouble("Price"));
                game.setQuantity(rs.getInt("Quantity"));
                game.setIssuedate(rs.getString("IssueDate"));
                game.setDescription(rs.getString("Description"));
                game.setImage(rs.getString("Image"));
                game.setCover(rs.getString("Cover"));
                game.setStatus(rs.getInt("Status"));
                game.setCoverthump(rs.getString("CoverThump"));
                game.setTrailer(rs.getString("Trailer"));;
                result.add(game);
            }
            rs.close();
            statement.close();
            this.closeConnection();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return result;
    }

    public Games findExactlyGames(int id) {
        Games result = new Games();
        for (Games each : getAllGames()) {
            if (each.getId() == id) {
                result = each;
            }
        }
        return result;
    }

    public void deleteAFilm(String id, String url) {
        try {
            int intId = Integer.parseInt(id);
            Games oldGame = findExactlyGames(intId);
            String oldFileUrl1 = url + "/" + oldGame.getCover();
            String oldFileUrl2 = url + "/" + oldGame.getCoverthump();
            File oldFile1 = new File(oldFileUrl1);
            File oldFile2 = new File(oldFileUrl2);
            oldFile1.delete();
            oldFile2.delete();
            String sql = "delete from games where Id = " + id;
//            String sql1 = "delete from films_genres where filmid = " + id;
            Connection connection = this.getConnection();
            Statement statement = connection.createStatement();
//            statement.execute(sql1);
            statement.execute(sql);
            statement.close();
            this.closeConnection();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public boolean addNewGame(Games game) {
        boolean result = true;
        try {
            Connection connection = this.getConnection();
            String sql = "insert into games (CAT_ID, SUP_ID, Name, Price, Quantity, IssueDate,Description,"
                    + " Image, Cover, Status, CoverThump, Trailer) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement psta = (PreparedStatement) connection.prepareStatement(sql);
            psta.setInt(1, game.getCat_id());
            psta.setInt(2, game.getSup_id());
            psta.setString(3, game.getName());
            psta.setDouble(4, game.getPrice());
            psta.setInt(5, game.getQuantity());
            psta.setString(6, game.getIssuedate());
            psta.setString(7, game.getDescription());
            psta.setString(8, game.getImage());
            psta.setString(9, game.getCover());
            psta.setInt(10, game.getStatus());
            psta.setString(11, game.getCoverthump());
            psta.setString(12, game.getTrailer());
            
            psta.execute();
            psta.close();
            this.closeConnection();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            result = false;
        }
        return result;
    }

    public Games getNewestAddedGame() {
        Games get = new Games();
        try {
            String sql = "select * from games ORDER BY Id DESC limit 1";
            Connection connection = this.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Games games = new Games();
                games.setId(rs.getInt("id"));
                games.setCover(rs.getString("cover"));
                games.setDescription(rs.getString("description"));
                games.setName(rs.getString("name"));
                games.setPrice(rs.getDouble("price"));
                games.setCoverthump(rs.getString("cover_thumb"));
                games.setTrailer(rs.getString("trailer_link"));
                get = games;
            }
            rs.close();
            statement.close();
            this.closeConnection();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return get;
    }

    public boolean updateGames(Games game, String id) {
        boolean result = true;
        try {
            Connection connection = this.getConnection();
            String sql = "update games set CAT_ID = ?, SUP_ID = ?, Name = ?, Price = ?, Quantity = ?, IssueDate = ? , "
                    + "Description = ?, Image = ?, Cover = ?, Status = ?, CoverThump = ?, Trailer = ? where Id = " + id;
            PreparedStatement psta = (PreparedStatement) connection.prepareStatement(sql);
            psta.setInt(1, game.getCat_id());
            psta.setInt(2, game.getSup_id());
            psta.setString(3, game.getName());
            psta.setDouble(4, game.getPrice());
            psta.setInt(5, game.getQuantity());
            psta.setString(6, game.getIssuedate());
            psta.setString(7, game.getDescription());
            psta.setString(8, game.getImage());
            psta.setString(9, game.getCover());
            psta.setInt(10, game.getStatus());
            psta.setString(11, game.getCoverthump());
            psta.setString(12, game.getTrailer());
           
            psta.execute();
            psta.close();
            this.closeConnection();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            result = false;
        }
        return result;
    }
    
    public List<Games> searchKeyWord(String keyword) {
        List<Games> result = new ArrayList<Games>();
        try {
            String sql = " select * from games where name like '%" + keyword + "%' ";
            Connection connection = this.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Games game = new Games();
                
                game.setId(rs.getInt("Id"));
                game.setCat_id(rs.getInt("CAT_ID"));
                game.setSup_id(rs.getInt("SUP_ID"));
                game.setName(rs.getString("Name"));
                game.setPrice(rs.getDouble("Price"));
                game.setIssuedate(rs.getString("IssueDate"));
                game.setDescription(rs.getString("Description"));
                game.setImage(rs.getString("Image"));
                game.setCover(rs.getString("Cover"));
                game.setStatus(rs.getInt("Status"));
                game.setCoverthump(rs.getString("CoverThump"));
                game.setTrailer(rs.getString("Trailer"));
                result.add(game);
            }
            rs.close();
            statement.close();
            this.closeConnection();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return result;
    }
    
        public List<Games> getTopSellerGames() {
        
        List<Games> get = new ArrayList<Games>();
        try {
            String sql = "select * from games where status = 2 ORDER BY name " ;
            Connection connection = this.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                
              Games game = new Games();
                
                game.setId(rs.getInt("Id"));
                game.setCat_id(rs.getInt("CAT_ID"));
                game.setSup_id(rs.getInt("SUP_ID"));
                game.setName(rs.getString("Name"));
                game.setPrice(rs.getDouble("Price"));
                game.setQuantity(rs.getInt("Quantity"));
                game.setIssuedate(rs.getString("IssueDate"));
                game.setDescription(rs.getString("Description"));
                game.setImage(rs.getString("Image"));
                game.setCover(rs.getString("Cover"));
                game.setStatus(rs.getInt("Status"));
                game.setCoverthump(rs.getString("CoverThump"));
                game.setTrailer(rs.getString("Trailer"));
                get.add(game);
            }
            rs.close();
            statement.close();
            this.closeConnection();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return get;
    }
    
}
