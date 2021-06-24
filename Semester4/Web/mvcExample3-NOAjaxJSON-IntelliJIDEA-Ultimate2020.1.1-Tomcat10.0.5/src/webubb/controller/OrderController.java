package webubb.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import webubb.domain.Order;
import webubb.model.DBManager;

import java.io.IOException;
import java.io.PrintWriter;

public class OrderController extends HttpServlet {
    public DBManager dbManager = new DBManager();
    public int id = dbManager.getMaxIdFromOrders() + 1;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getSession().getAttribute("user").toString();
        int productId = Integer.parseInt(request.getParameter("productId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        Order order = new Order(id, username, productId, quantity);
        id += 1;
        dbManager.buyProduct(order);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command = request.getSession().getAttribute("command").toString();
        response.setContentType("application/json");
        PrintWriter out = new PrintWriter(response.getOutputStream());
        if (command.equals("finalize")) {
            boolean v = dbManager.finalizeCommand();
            if (v) {
                out.println("Finalize command executed successfully!");
            } else {
                out.println("Finalize command executed unsuccessfully!");
            }
        } else {
            dbManager.cancelCommand();
            out.println("Cancel command executed successfully!");
        }
        out.flush();
    }
}
