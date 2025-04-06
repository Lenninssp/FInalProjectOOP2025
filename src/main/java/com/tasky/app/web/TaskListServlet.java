package com.tasky.app.web;

import com.tasky.app.dao.TaskDAO;
import com.tasky.app.model.Task;
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

        int userId = 1; // hardcoded for now
        List<Task> tasks = TaskDAO.getTasksByUserId(userId);
        System.out.println("Tasks" + tasks);

        request.setAttribute("tasks", tasks);
        request.getRequestDispatcher("task-list.jsp").forward(request, response);
    }
}
