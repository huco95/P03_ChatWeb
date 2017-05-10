
<%@page import="java.util.List"%>
<%@page import="es.ubu.util.Chat"%>
<%
	Chat server = (Chat) application.getAttribute("server");
	String client = (String) session.getAttribute("client");
	out.print(server.showMessages(client));
%>