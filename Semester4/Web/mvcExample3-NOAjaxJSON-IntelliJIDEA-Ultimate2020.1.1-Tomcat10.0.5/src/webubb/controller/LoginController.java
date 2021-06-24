package webubb.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import webubb.model.DBManager;


import java.io.IOException;

public class LoginController extends HttpServlet {

    public LoginController() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd;
        try {
            String username = request.getParameter("username");

            DBManager dbManager = new DBManager();
            rd = request.getRequestDispatcher("/success.jsp");
            HttpSession session = request.getSession();
            session.setAttribute("user", username);
        } catch (Exception e) {
            rd = request.getRequestDispatcher("/error.jsp");
            System.err.println(e.getMessage());
        }
        rd.forward(request, response);
    }
}
