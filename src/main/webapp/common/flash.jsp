<%--
  Created by IntelliJ IDEA.
  User: lenninsabogal
  Date: 6/04/25
  Time: 2:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page session="true" %>
<%
    String flash = (String) session.getAttribute("flash");
    if (flash != null) {
%>
<div class="alert alert-success alert-dismissible fade show" role="alert">
    <%= flash %>
    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
</div>

<%
        session.removeAttribute("flash");
    }
%>