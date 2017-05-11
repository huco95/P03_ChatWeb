
<%@page import="java.util.List"%>
<%@page import="es.ubu.util.Chat"%>
<%
	Chat server = (Chat) application.getAttribute("server");
	String client = (String) session.getAttribute("client");
	List<String> messages = server.getMessages(client);
	for(String message : messages){
		out.print("<p>" + message + "</p>");
	}
%>