<%@ page import="es.ubu.util.Chat, java.lang.String"%>
<jsp:useBean id="server" scope="application" class="es.ubu.util.Chat" />
<jsp:useBean id="client" scope="session" class="java.lang.String" />

<%
	if (request.getParameter("nickName") != null) {
		client = request.getParameter("nickName");
		server.registerClient(client);
		session.setAttribute("client", client);
	} else if (client.equals("")) {
%>
		<jsp:forward page="index.html" />
<%
	} else {
		client = (String) session.getAttribute("client");
		server = (Chat) application.getAttribute("server");
	}
%>

<html>
<head>
<title>Chat Room</title>
<script>
	//Forzar el refresco del Iframe que muestra los mensajes
	setInterval(refreshIframe, 5000); // establece el tiempo a 5 seg.
	function refreshIframe() { // recarga el iframe de la página
		frames[0].location.reload(true);
	}
</script>
</head>
<body>

	<h1>
		Bienvenido,
		<%
		out.print(client);
	%>
	</h1>

	<form method="GET" action="sendMessage.jsp">

		<div>
			<p>Mensaje:</p>
			<textarea rows="4" cols="50" name="messageInput"></textarea>

			<p>Usuario:</p>
			<input type="text" name="nicknameToBan"><br> <input
				type="radio" name="banAcction" value="ban">Bloquear<br>
			<input type="radio" name="banAcction" value="unban">Desbloquear<br>
			<input type="submit" value="Enviar">
			<%
				String message = request.getParameter("messageInput");
				String nicknameToBan = request.getParameter("nicknameToBan");
				String banUnban = request.getParameter("banAcction");

				if (nicknameToBan != null && banUnban != null) {
					if (banUnban.equals("ban")) {
						//Accion de banear
						server.ban(client, nicknameToBan);
					} else {
						//Accion de unban
						server.unban(client, nicknameToBan);
					}
				} else if (message != null && !message.equals("")) {
					System.out.println("enviando mensaje");
					server.sendMessage(client + "> " + message);
				}
				application.setAttribute("servidor", server);
			%>
			<input type="reset" value="Borrar">
			<a href="logout.jsp">Logout</a>
		</div>

		<p>Chat:</p>
		<iframe id="messageOutput" width="500" height="350" src="messages.jsp"></iframe>

	</form>


</body>
</html>
