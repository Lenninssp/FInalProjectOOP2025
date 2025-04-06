<%@ page import="java.util.List" %>
<%@ page import="com.tasky.app.model.Task" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Task List</title>
</head>
<body>
<form action="task.jsp" method="get">
  <button type="submit">Create task</button>
</form>
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
    <td>
      <form action="toggle-task" method="post" style="display:inline;">
        <input type="hidden" name="taskId" value="<%= task.getId() %>">
        <input type="hidden" name="completed" value="<%= task.isCompleted() %>">
        <button type="submit">
          <%= task.isCompleted() ? "↩️ Uncomplete" : "✅ Complete" %>
        </button>
      </form>
      <form action="delete-task" method="post" style="display: inline" onsubmit="return confirm('Are you sure you want to delete this task?');">
        <input type="hidden" name="taskId" value="<%= task.getId() %>">
        <button type="submit">🗑️ Delete</button>
      </form>
    </td>
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