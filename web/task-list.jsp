<%@ page import="java.util.List" %>
<%@ page import="com.tasky.app.model.Task" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Task List</title>
</head>
<body>
<h2>My Tasks</h2>
<%
  List<Task> tasks = (List<Task>) request.getAttribute("tasks");
  if (tasks != null && !tasks.isEmpty()) {
%>
<table border="1">
  <tr>
    <th>ID</th>
    <th>Title</th>
    <th>Description</th>
    <th>Completed</th>
    <th>User ID</th>
  </tr>
  <%
    for (Task task : tasks) {
  %>
  <tr>
    <td><%= task.getId() %></td>
    <td><%= task.getTitle() %></td>
    <td><%= task.getDescription() %></td>
    <td><%= task.isCompleted() %></td>
    <td><%= task.getUserId() %></td>
  </tr>
  <%
    }
  %>
</table>
<%
} else {
%>
<p>No tasks found.</p>
<%
  }
%>
</body>
</html>