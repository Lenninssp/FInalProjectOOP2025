<%--
  Created by IntelliJ IDEA.
  User: lenninsabogal
  Date: 6/04/25
  Time: 1:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="common/header.jsp"%>
<h2>Login</h2>
<form action="login" method="post">
  <label>Username:</label>
  <input type="text" name="username" required><br>
  <label>Password:</label>
  <input type="password" name="password" required><br>
  <button type="submit">Login</button>
</form>

<%@include file="common/footer.jsp"%>
