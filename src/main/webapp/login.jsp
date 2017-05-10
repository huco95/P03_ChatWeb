<jsp:useBean id="prueba" scope="session" class="java.lang.String" />
<%
  if (request.getParameter("nickName").isEmpty() == false) {
	  prueba = request.getParameter("nickName");
%>

<jsp:forward page="ChatRoom.jsp" />

<% } else { %>

<%@ include file="index.html"%>

<% } %>