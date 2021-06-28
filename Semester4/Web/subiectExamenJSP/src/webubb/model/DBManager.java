package webubb.model;

import webubb.domain.Player;
import webubb.domain.Team;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//POST - ADD / UPDATE / DELETE
//GET - LIST

public class DBManager {

    Statement statement;

    public DBManager() {
        connect();
    }

    public void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/exam", "root", "");
            statement = con.createStatement();
        } catch (Exception ex) {
            System.err.println("Connection error:" + ex.getMessage());
            ex.printStackTrace();
        }
    }

    //    ------------ADD FUNCTION-----------------
//
    public boolean add(Player player) {
        int i = 0;
        try {
            i = statement.executeUpdate("insert into player values(" + player.getId() + ", '" +
                    player.getName() + "'," + player.getAge() + ")");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return (i > 0);
    }

    //     ------------UPDATE FUNCTION-----------------
//
    public boolean updateMembers(Team team) {
        int i = 0;
        try {
            i = statement.executeUpdate("update team set members='" + team.getMembers() + "' where id="
                    + team.getId() + " and capitanId=" + team.getCapitanId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return (i > 0);
    }

    //    -----------------------GET USER ID FOR --------------------
    public int getUserId(String name, int age) {
        int id = 0;
        try {
            ResultSet resultSet = this.statement.executeQuery("select * from player where name='" + name + "' and age=" + age);
            if (resultSet.next()) {
                id = resultSet.getInt("id") + 1;
            }
            resultSet.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return id;
    }

//      --------------LIST OF RECORDS--------------

    public List<Team> list() {
        List<Team> teams = new ArrayList<>();
        ResultSet rs;
        try {
            rs = statement.executeQuery("select * from team");
            while (rs.next()) {
                //contains() if we want to check in the whole string
                //no constraint if we have to list just the records
                //rs.getString("description").startsWith(toStart)
                teams.add(new Team(
                        rs.getInt("id"),
                        rs.getInt("capitanId"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getString("members")
                ));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teams;
    }

    public List<String> listPlayersTeam(String name) {
        List<String> teams = new ArrayList<>();
        ResultSet rs;
        try {
            rs = statement.executeQuery("select * from team");
            while (rs.next()) {
                //contains() if we want to check in the whole string
                //no constraint if we have to list just the records
                if (rs.getString("members").contains(name)) {
                    teams.add(rs.getString("name"));
                }
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teams;
    }

    public Team verifyIfTeamExist(String name) {
        List<Team> teams = this.list();
        for (int i = 0; i < teams.size(); i++) {
            if (teams.get(i).getName().equals(name)) {
                return teams.get(i);
            }
        }
        return null;
    }

    public void addToTeam(Player player, String name) {
        Team team = verifyIfTeamExist(name);
        if (team != null) {//facem update
            String members = team.getMembers();
            members += " " + player.getName();
            Team teamToUpdate = new Team(team.getId(), team.getCapitanId(), name, team.getDescription(), members);
            this.updateMembers(teamToUpdate);
        } else {
            //adaugam nou team
            try {
                statement.executeUpdate("insert into team values (" + 0 + "," + 0 + ",'" + name + "'," + "''"
                        + ",'" + player.getName() + "')");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
