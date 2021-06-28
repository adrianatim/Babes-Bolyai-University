package webubb.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import webubb.domain.Player;
import webubb.domain.Team;
import webubb.model.DBManager;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpRetryException;
import java.util.List;

public class PlayerController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getSession().getAttribute("user").toString();

        response.setContentType("application/json");
        DBManager dbmanager = new DBManager();
        List<String> products = dbmanager.listPlayersTeam(name);
        JSONArray jsonAssets = new JSONArray();
        for (int i = 0; i < products.size(); i++) {
            JSONObject jObj = new JSONObject();
            jObj.put("name", products.get(i));
            jsonAssets.add(jObj);
        }
        PrintWriter out = new PrintWriter(response.getOutputStream());
        out.println(jsonAssets.toJSONString());
        out.flush();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        ADD RECORD TO DATABASE
        DBManager dbManager = new DBManager();

        String pname = request.getSession().getAttribute("user").toString();
        int age = Integer.parseInt(request.getParameter("age"));
        String tname = request.getParameter("tname");
        int id = dbManager.getUserId(pname, age);
        Player player = new Player(id, pname, age);

        //verify if the player is in players table
        if (id == 0) {
            dbManager.add(player);
        } else {
            dbManager.addToTeam(player, tname);
        }
    }
}
