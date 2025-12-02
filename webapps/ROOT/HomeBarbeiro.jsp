<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.urbancut.core.Response"%>
<%@page import="com.urbancut.services.AuthService"%>
<%@page import="com.urbancut.models.Barbeiro"%>
<%@page import="com.urbancut.repositories.BarbeiroRepository"%>
<%
	AuthService service = new AuthService();
	boolean isLogged = service.isLogged(session);

    if (!isLogged) {
        response.sendRedirect("Barbeiro.jsp");
        return;
    }

    boolean isThatRule = service.isThatRule(session,"barbeiro");

    if (!isThatRule) {
        service.logout(session);
        response.sendRedirect("Barbeiro.jsp");
        return;
    }

    if (!((boolean) session.getAttribute("hasBarbearia"))) {
        response.sendRedirect("Estabelecimento.jsp");
    }
%>