package com.tasky.app.web;

import com.tasky.app.dao.TaskDAO;
import com.tasky.app.model.Task;
import com.tasky.app.util.Session;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/edit-task")
public class EditTaskServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Integer userId = Session.getLoggedUserId(request, response);
        if (userId == null) return;

        int taskId = Integer.parseInt(request.getParameter("taskId"));
        Task task = TaskDAO.getTaskById(taskId);
        System.out.println("attention " + task);

        if (task != null && task.getUserId() == userId) {
            request.setAttribute("task", task);
            request.getRequestDispatcher("edit-task.jsp").forward(request, response);
        } else {
            request.getSession().setAttribute("flash", "❌ Task not found or unauthorized.");
            response.sendRedirect("task-list");
        }
    }
}
