package webubb.controller;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import webubb.domain.Asset;
import webubb.model.DBManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


/**
 * Created by forest on 5/17/2018.
 */

public class AssetsController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ((action != null) && action.equals("update")) {
            // We update an asset
            Asset asset = new Asset(Integer.parseInt(request.getParameter("id")),
                    Integer.parseInt(request.getParameter("userid")),
                    request.getParameter("description"),
                    Integer.parseInt(request.getParameter("value")));
            DBManager dbmanager = new DBManager();
            Boolean result = dbmanager.updateAsset(asset);
            PrintWriter out = new PrintWriter(response.getOutputStream());
            if (result == true) {
                out.println("Update asset successfully.");
            } else {
                out.println("Error updating asset!");
            }
            out.flush();
        } else if ((action != null) && action.equals("add")) {
            //We add an asset
            Asset asset = new Asset(Integer.parseInt(request.getParameter("id")),
                    Integer.parseInt(request.getParameter("userid")),
                    request.getParameter("description"),
                    Integer.parseInt(request.getParameter("value")));
            DBManager dbManager = new DBManager();
            Boolean result = dbManager.addAsset(asset);
            PrintWriter out = new PrintWriter(response.getOutputStream());
            if (result == true) {
                out.println("Add asset successfully.");
            } else {
                out.println("Error adding asset!");
            }
            out.flush();
        } else if ((action != null) && action.equals("delete")) {
            //We delete an asset
            int id = Integer.parseInt(request.getParameter("id"));
            int userid = Integer.parseInt(request.getParameter("userid"));
            DBManager dbManager = new DBManager();
            Boolean result = dbManager.deleteAsset(id, userid);
            PrintWriter out = new PrintWriter(response.getOutputStream());
            if (result == true) {
                out.println("Delete asset successfully.");
            } else {
                out.println("Error deleting asset!");
            }
            out.flush();
        } else if ((action != null) && action.equals("getAll")) {
            int userid = Integer.parseInt(request.getParameter("userid"));

            response.setContentType("application/json");
            DBManager dbmanager = new DBManager();
            ArrayList<Asset> assets = dbmanager.getUserAssets(userid);
            JSONArray jsonAssets = new JSONArray();
            for (int i = 0; i < assets.size(); i++) {
                JSONObject jObj = new JSONObject();
                jObj.put("id", assets.get(i).getId());
                jObj.put("userid", assets.get(i).getUserid());
                jObj.put("description", assets.get(i).getDescription());
                jObj.put("value", assets.get(i).getValue());
                jsonAssets.add(jObj);
            }
            PrintWriter out = new PrintWriter(response.getOutputStream());
            out.println(jsonAssets.toJSONString());
            out.flush();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
