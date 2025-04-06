<%--
  Created by IntelliJ IDEA.
  User: lenninsabogal
  Date: 1/04/25
  Time: 9:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
<%@include file="navbar.jsp" %>
<h2>Register user</h2>
<form action="register" method="post">
    <label>Username:</label>
    <input type="text" name="username"><br>
    <label>Email:</label>
    <input type="text" name="email"><br>
    <label>Password:</label>
    <input type="password" name="password">
    <button type="submit">Submit</button>
</form>

</body>
</html>
