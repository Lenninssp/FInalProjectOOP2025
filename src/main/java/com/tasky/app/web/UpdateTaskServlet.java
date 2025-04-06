package com.tasky.app.web;

import com.tasky.app.dao.TaskDAO;
import com.tasky.app.model.Task;
import com.tasky.app.util.Session;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/update-task")
public class UpdateTaskServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        Integer userId = Session.getLoggedUserId(request, response);
        if (userId == null) return;

        int taskId = Integer.parseInt(request.getParameter("taskId"));
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        boolean completed = request.getParameter("completed") != null;
        String dueDateStr = request.getParameter("dueDate");
        LocalDate dueDate = dueDateStr != null && !dueDateStr.isEmpty()
                ? LocalDate.parse(dueDateStr)
                : null;
        Task updatedTask = new Task(taskId, title, description, completed, userId, dueDate);
        TaskDAO.updateTask(updatedTask);

        request.getSession().setAttribute("flash", "✅ Task updated!");
        response.sendRedirect("task-list");
    }
}
