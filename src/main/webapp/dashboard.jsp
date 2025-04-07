<%@ page import="java.util.List" %>
<%@ page import="com.tasky.app.model.Task" %>
<%@ page import="com.tasky.app.dao.TaskDAO" %><%--
  Created by IntelliJ IDEA.
  User: lenninsabogal
  Date: 6/04/25
  Time: 1:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="common/header.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>

<%
    Integer userId = (Integer) session.getAttribute("userId");
    String username = (String) session.getAttribute("username");
    if (userId == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    List<Task> tasks = TaskDAO.getTasksByUserId(userId, null, null);
    int total = tasks.size();
    int completed = 0;
    for (Task t : tasks) {
        if (t.isCompleted()) completed++;
    }
%>
<h2>Welcome back!<strong><%=username%></strong></h2>
<p>You have <strong><%= total %>
</strong> tasks.</p>
<p><strong><%= completed %>
</strong> completed ✔️, <%= total - completed%> pending ⏱️</p>
<a href="task.jsp">Create a new task</a>
<a href="task-list">View all tasks</a>
<%@include file="common/footer.jsp"%>
