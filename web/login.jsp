<%--
  Created by IntelliJ IDEA.
  User: lenninsabogal
  Date: 6/04/25
  Time: 1:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<%@include file="navbar.jsp"%>
<h2>Login</h2>
<form action="login" method="post">
  <label>Username:</label>
  <input type="text" name="username" required><br>
  <label>Password:</label>
  <input type="password" name="password" required><br>
  <button type="submit">Login</button>
</form>

</body>
</html>
