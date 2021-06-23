package webubb.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import webubb.domain.FamilyRelation;
import webubb.domain.User;
import webubb.model.DBManager;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class FamilyController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String mother = request.getParameter("mother");
        String father = request.getParameter("father");

        DBManager dbManager = new DBManager();
        int id = dbManager.getUserId(username);
        if (id > 0) {
            FamilyRelation family = new FamilyRelation(id, username, mother, father);
            dbManager.addMotherAndFather(family);
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("/registrationError.jsp");
            rd.forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ((action != null) && action.equals("getAll")) {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");

            response.setContentType("application/json");
            DBManager dbmanager = new DBManager();
            List<User> users = dbmanager.seeDescendents(user);
            JSONArray jsonAssets = new JSONArray();
            for (int i = 0; i < users.size(); i++) {
                JSONObject jObj = new JSONObject();
                jObj.put("id", users.get(i).getId());
                jObj.put("username", users.get(i).getUsername());
                jsonAssets.add(jObj);
            }
            PrintWriter out = new PrintWriter(response.getOutputStream());
            out.println(jsonAssets.toJSONString());
            out.flush();
        }
    }
}
