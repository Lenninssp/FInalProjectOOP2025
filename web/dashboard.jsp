<%@ page import="java.util.List" %>
<%@ page import="com.tasky.app.model.Task" %>
<%@ page import="com.tasky.app.dao.TaskDAO" %><%--
  Created by IntelliJ IDEA.
  User: lenninsabogal
  Date: 6/04/25
  Time: 1:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dashboard</title>
</head>
<body>
<%@include file="navbar.jsp"%>
<%
    Integer userId = (Integer) session.getAttribute("userId");
    if (userId == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    List<Task> tasks = TaskDAO.getTasksByUserId(userId);
    int total = tasks.size();
    int completed = 0;
    for (Task t : tasks) {
        if (t.isCompleted()) completed++;
    }
%>
<h2>Welcome back!</h2>
<p>You have <strong><%= total %>
</strong> tasks.</p>
<p><strong><%= completed %>
</strong> completed ✔️, <%= total - completed%> pending ⏱️</p>
<a href="task.jsp">Create a new task</a>
<a href="task-list">View all tasks</a>
</body>
</html>
