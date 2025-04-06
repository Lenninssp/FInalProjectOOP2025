<%--
  Created by IntelliJ IDEA.
  User: lenninsabogal
  Date: 6/04/25
  Time: 1:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>



<nav class="navbar navbar-expand-lg navbar-light bg-light mb-4">
    <div class="container-fluid">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/dashboard.jsp">🏠 Home</a>
        <div class="collapse navbar-collapse">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/task.jsp">📝 New
                    Task</a></li>
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/task-list">📋 My
                    Tasks</a></li>
            </ul>
            <ul class="navbar-nav">
                <% if (session.getAttribute("userId") == null) {%>
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/register.jsp">👤
                    Register</a></li>
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/login.jsp">🔐 Login</a>
                </li>
                <% } else { %>
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/logout">🚪 Logout</a>
                </li>
                <% } %>
            </ul>
        </div>
    </div>
</nav>