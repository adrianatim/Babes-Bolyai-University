package webubb.model;

import webubb.domain.Asset;
import webubb.domain.User;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by forest.
 */
public class DBManager {
    private Statement stmt;

    public DBManager() {
        connect();
    }

    public void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/exForest", "root", "");
            stmt = con.createStatement();
        } catch (Exception ex) {
            System.out.println("eroare la connect:" + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public User authenticate(String username, String password) {
        ResultSet rs;
        User u = null;
        System.out.println(username + " " + password);
        try {
            rs = stmt.executeQuery("select * from users where user='" + username + "' and password='" + password + "'");
            if (rs.next()) {
                u = new User(rs.getInt("id"), rs.getString("user"), rs.getString("password"));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return u;
    }

    public ArrayList<Asset> getUserAssets(int userid) {
        ArrayList<Asset> assets = new ArrayList<>();
        ResultSet rs;
        try {
            rs = stmt.executeQuery("select * from asset where userid=" + userid);
            while (rs.next()) {
                assets.add(new Asset(
                        rs.getInt("id"),
                        rs.getInt("userid"),
                        rs.getString("description"),
                        rs.getInt("value")
                ));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return assets;
    }

    public boolean updateAsset(Asset asset) {
        int r = 0;
        try {
            r = stmt.executeUpdate("update asset set description='" + asset.getDescription() + "', value=" + asset.getValue() +
                    " where id=" + asset.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (r > 0) return true;
        else return false;
    }

    public boolean addAsset(Asset asset) {
        int r = 0;
        try {
            r = stmt.executeUpdate("insert into asset values (" + asset.getId() + "," + asset.getUserid() + ", '"
                    + asset.getDescription() + "', " + asset.getValue() + ")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (r > 0) return true;
        else return false;
    }

    public boolean deleteAsset(int id, int userid) {
        int r = 0;
        try {
            r = stmt.executeUpdate("delete from asset where id=" + id + " and userid=" + userid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (r > 0) return true;
        else return false;
    }
}