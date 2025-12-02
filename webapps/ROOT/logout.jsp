<%@page import="com.urbancut.core.Response"%>
<%@page import="com.urbancut.services.AuthService"%>
<%
	AuthService service = new AuthService();
	Response<Boolean> resposta = service.logout(session);
    response.sendRedirect("home.html");
%>