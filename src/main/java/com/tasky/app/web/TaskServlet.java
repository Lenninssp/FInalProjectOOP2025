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
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.logging.Logger;

@WebServlet("/create-task")
public class TaskServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(TaskServlet.class.getName());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String dueDateStr = request.getParameter("dueDate");
        LocalDate dueDate = dueDateStr != null && !dueDateStr.isEmpty()
                ? LocalDate.parse(dueDateStr)
                : null;

        Integer userId = Session.getLoggedUserId(request, response);
        if(userId == null) return;

        Task task = new Task(title, description, false, userId, dueDate);
        TaskDAO.createTaskTable();
        TaskDAO.createTask(task);

        logger.info("New task created: " + task);

        response.setContentType("text/html");
        request.getSession().setAttribute("flash", "✅ Task created successfully!");
        response.sendRedirect("task.jsp");
    }
}