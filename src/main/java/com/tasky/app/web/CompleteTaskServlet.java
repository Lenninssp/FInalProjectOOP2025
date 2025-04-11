package com.tasky.app.web;

import com.tasky.app.dao.TaskDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet("/toggle-task")
public class CompleteTaskServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int taskId = Integer.parseInt(request.getParameter("taskId"));
        boolean completed = Boolean.parseBoolean(request.getParameter("completed"));

        TaskDAO.toggleTaskCompletion(taskId, completed);

        request.getSession().setAttribute("flash", "✅ Task state changed successfully!");
        request.getSession().setAttribute("flashType", "success");

        response.sendRedirect("task-list");
    }
}