
<%@page import="es.ubu.util.Chat"%>

<%
	Chat server = (Chat) application.getAttribute("server");
	String client = (String) session.getAttribute("client");
	session.invalidate();
	server.logout(client);
	response.sendRedirect("index.html");
%>
<jsp:include page="index.html"/>