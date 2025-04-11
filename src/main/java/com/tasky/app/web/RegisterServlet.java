package com.tasky.app.web;

import com.tasky.app.dao.UserDAO;
import com.tasky.app.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.function.Supplier;
import java.util.logging.Logger;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(RegisterServlet.class.getName());
    @Override
    protected void doPost (HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = new User(username, email, password);
        logger.info("new user " + user);
        UserDAO.createUser(user);
        User fetchedUser = UserDAO.getUserById(user.getId());
        logger.info("Fetched user: " + fetchedUser);

        request.getSession().setAttribute("flash", "✅ Registration successful");
        request.getSession().setAttribute("flashType", "success");
        response.sendRedirect("register.jsp");
    }
}
