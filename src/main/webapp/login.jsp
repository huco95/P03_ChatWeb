<%
	if (request.getParameter("nickName").isEmpty() == false) {
%>

<jsp:forward page="ChatRoom.jsp" />

<%
	} else {
%>

<%@ include file="index.html"%>

<%
	}
%>