<%@ page import="com.tasky.app.model.Task" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: lenninsabogal
  Date: 6/04/25
  Time: 10:34 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Task</title>
</head>
<body>
<h2>Create a new task</h2>
<form action="create-task" method="post">
    <label>Title:</label>
    <input type="text" name="title" required><br>
    <label>Description:</label>
    <input type="text" name="description"><br>
    <button type="submit">Create Task</button>
</form>
<form action="task-list" method="get">
    <button type="submit">Task List</button>
</form>
</body>
</html>
