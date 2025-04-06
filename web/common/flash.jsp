<%--
  Created by IntelliJ IDEA.
  User: lenninsabogal
  Date: 6/04/25
  Time: 2:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>
<%
    String flash = (String) session.getAttribute("flash");
    if (flash != null) {
%>
<div style="background-color: #d4edda; padding: 10px; margin-bottom: 10px; border: 1px solid #c3e6cb; color: #155724;">
    <%= flash %>
</div>

<%
        session.removeAttribute(flash);
    }
%>