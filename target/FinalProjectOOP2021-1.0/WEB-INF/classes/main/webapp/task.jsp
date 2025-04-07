<%@ page import="com.tasky.app.model.Task" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: lenninsabogal
  Date: 6/04/25
  Time: 10:34 AM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="common/header.jsp"%>
<h2>Create a new task</h2>
<form action="create-task" method="post">
    <label>Title:</label>
    <input type="text" name="title" required><br>
    <label>Description:</label>
    <input type="text" name="description"><br>
    <label>Due date:</label>
    <input type="date" name="dueDate"><br>
    <button type="submit">Create Task</button>
</form>
<form action="task-list" method="get">
    <button type="submit">Task List</button>
</form>
<%@include file="common/footer.jsp"%>
