package webubb.model;

import webubb.domain.FamilyRelation;
import webubb.domain.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBManager {

    Statement statement;

    public DBManager() {
        connect();
    }

    public void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/familyrelations", "root", "");
            statement = con.createStatement();
        } catch (Exception ex) {
            System.err.println("Connection error:" + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public User authenticate(String username, String mother, String father) {
        User user = null;
        try {
            ResultSet resultSet = this.statement.executeQuery("select * from family where mother='" + mother
                    + "' and father='" + father + "'");
            if (resultSet.next()) {
                user = new User(resultSet.getInt("id"), username);
            }
            resultSet.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    public boolean addMotherAndFather(FamilyRelation family) {
        int r = 0;
        try {
            r = statement.executeUpdate("insert into family values (" + family.getId() + ",'" + family.getUsername()
                    + "', '" + family.getMother() + "', '" + family.getFather() + "')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (r > 0);
    }

    public int getUserId(String username) {
        int id = 0;
        try {
            ResultSet resultSet = this.statement.executeQuery("select * from user where username='" + username + "'");
            if (resultSet.next()) {
                id = resultSet.getInt("id");
            }
            resultSet.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return id;
    }

    public List<User> seeDescendents(User user) {
        List<User> users = new ArrayList<>();
        ResultSet resultSet;

        try {
            resultSet = statement.executeQuery("select * from family where id=" + user.getId());
            while (resultSet.next()) {
                String father = resultSet.getString("father");
                int id = getUserId(father);
                if (id != 0) {
                    users.add(new User(id, father));
                    resultSet = statement.executeQuery("select * from family where id=" + id);
                } else {
                    break;
                }
            }
            resultSet.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return users;
    }
}
