<%--
  Created by IntelliJ IDEA.
  User: lenninsabogal
  Date: 1/04/25
  Time: 9:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="common/header.jsp"%>
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
<%@include file="common/footer.jsp"%>

