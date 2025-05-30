<%@ page import="com.tasky.app.model.Task" %><%--
  Created by IntelliJ IDEA.
  User: lenninsabogal
  Date: 6/04/25
  Time: 2:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.tasky.app.model.Task" %>
<%@include file="common/header.jsp"%>

<%
  Task task = (Task) request.getAttribute("task");
  if (task == null) {
    response.sendRedirect("task-list");
    return;
  }
%>

<h2>Edit Task</h2>
<form action="update-task" method="post">
  <input type="hidden" name="taskId" value="<%= task.getId() %>">
  <label>Title:</label>
  <input type="text" name="title" value="<%= task.getTitle() %>" required><br>

  <label>Description:</label>
  <input type="text" name="description" value="<%= task.getDescription() %>"><br>

  <label>Completed:</label>
  <input type="checkbox" name="completed" <%= task.isCompleted() ? "checked" : "" %>><br>

  <label>Due date:</label>
  <input type="date" name="dueDate" value="<%= task.getDueDate() != null ? task.getDueDate() : ""%>"><br>

  <button type="submit">💾 Update Task</button>
</form>
<%@include file="common/footer.jsp"%>
