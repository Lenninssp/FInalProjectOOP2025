package com.tasky.app.web;

import com.tasky.app.dao.UserDAO;
import com.tasky.app.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = UserDAO.getUserByUsernameAndPassword(username, password);
        System.out.println(user);
        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("userId", user.getId());
            session.setAttribute("username", user.getUsername());
            request.getSession().setAttribute("flash", "✅ Login successful");
            request.getSession().setAttribute("flashType", "success");
            response.sendRedirect("dashboard.jsp");
        } else {
            request.getSession().setAttribute("flash", "❌ Invalid credentials");
            request.getSession().setAttribute("flashType", "danger");
            response.sendRedirect("login.jsp");
        }
    }
}
