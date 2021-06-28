package webubb.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import webubb.domain.Team;
import webubb.model.DBManager;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class TeamController extends HttpServlet {

    public TeamController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/json");
        DBManager dbmanager = new DBManager();
        List<Team> products = dbmanager.list();
        JSONArray jsonAssets = new JSONArray();
        for (int i = 0; i < products.size(); i++) {
            JSONObject jObj = new JSONObject();
            jObj.put("id", products.get(i).getId());
            jObj.put("capitanId", products.get(i).getCapitanId());
            jObj.put("name", products.get(i).getName());
            jObj.put("description", products.get(i).getDescription());
            jObj.put("members", products.get(i).getMembers());
            jsonAssets.add(jObj);
        }
        PrintWriter out = new PrintWriter(response.getOutputStream());
        out.println(jsonAssets.toJSONString());
        out.flush();
    }

}
