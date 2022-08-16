<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% if ("No".equals(request.getAttribute("miLabel"))) { %>
correo ya ocupado
<% } else { %>
correo permitido
<%}%>
