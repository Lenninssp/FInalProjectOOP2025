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
import java.util.List;

@WebServlet("/task-list")
public class TaskListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Integer userId = Session.getLoggedUserId(request, response);
        if(userId == null) return;
        String query = request.getParameter("query");
        String status = request.getParameter("status");
        List<Task> tasks = TaskDAO.getTasksByUserId(userId, query, status);
        request.setAttribute("tasks", tasks);
        request.getRequestDispatcher("task-list.jsp").forward(request, response);
    }
}
