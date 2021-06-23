package webubb.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import webubb.domain.User;
import webubb.model.DBManager;


import java.io.IOException;

public class LoginController extends HttpServlet {

    public LoginController() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String mother = request.getParameter("mother");
        String father = request.getParameter("father");
        RequestDispatcher rd;

        DBManager dbmanager = new DBManager();
        User user = dbmanager.authenticate(username, mother, father);
        if (user != null) {
            rd = request.getRequestDispatcher("/success.jsp");
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            if (user == null || user.equals("")) {
                return;
            }
        } else {
            rd = request.getRequestDispatcher("/error.jsp");
        }
        rd.forward(request, response);
    }
}
