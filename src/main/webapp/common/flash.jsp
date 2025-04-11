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
    String type = (String) session.getAttribute("flashType");
    if (flash != null) {
        if ( type == null) type = "success";
%>
<div class="alert alert-<%= type %> alert-dismissible fade show" role="alert">
    <%= flash %>
    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
</div>

<%
        session.removeAttribute("flash");
        session.removeAttribute("flashType");
    }
%>