package webubb.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import webubb.domain.Product;
import webubb.model.DBManager;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ProductController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        ADD RECORD TO DATABASE
//        int id = Integer.parseInt(request.getParameter("id"));
//        String name = request.getParameter("name");
//        String description = request.getParameter("description");
//
//        Product product = new Product(id, name, description);
//        DBManager dbManager = new DBManager();
//        boolean v = dbManager.add(product);
//        response.setContentType("application/json");
//        PrintWriter out = new PrintWriter(response.getOutputStream());
//        if (v) {
//            out.println("Added successfully!");
//        } else {
//            out.println("Added unsuccessfully!");
//        }
//        out.flush();

//        DELETE RECORD FROM DATABASE
//        int id = Integer.parseInt(request.getParameter("id"));
//        DBManager dbManager = new DBManager();
//        boolean v = dbManager.delete(id);
//        response.setContentType("application/json");
//        PrintWriter out = new PrintWriter(response.getOutputStream());
//        if (v) {
//            out.println("Deleted successfully!");
//        } else {
//            out.println("Deleted unsuccessfully!");
//        }
//        out.flush();

//      UPDATE RECORD FROM DATABASE
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String description = request.getParameter("description");

        Product product = new Product(id, name, description);
        DBManager dbManager = new DBManager();
        boolean v = dbManager.update(product);
        response.setContentType("application/json");
        PrintWriter out = new PrintWriter(response.getOutputStream());
        if (v) {
            out.println("Updated successfully!");
        } else {
            out.println("Updated unsuccessfully!");
        }
        out.flush();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String toSearch = request.getParameter("user");
        response.setContentType("application/json");
        DBManager dbmanager = new DBManager();
        List<Product> products = dbmanager.list(toSearch);
        JSONArray jsonAssets = new JSONArray();
        for (int i = 0; i < products.size(); i++) {
            JSONObject jObj = new JSONObject();
            jObj.put("id", products.get(i).getId());
            jObj.put("name", products.get(i).getName());
            jObj.put("description", products.get(i).getDescription());
            jsonAssets.add(jObj);
        }
        PrintWriter out = new PrintWriter(response.getOutputStream());
        out.println(jsonAssets.toJSONString());
        out.flush();
    }
}
