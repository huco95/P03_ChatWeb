package es.ubu.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Chat {

	private HashMap<String, List<String>> messages = new HashMap<String, List<String>>();
	private HashMap<String, HashSet<String>> baneados = new HashMap<String, HashSet<String>>();

	public Chat() {
	}

	public void registerClient(String nickName) {
		messages.put(nickName, new ArrayList<String>());
		baneados.put(nickName, new HashSet<String>());
		sendMessage("admin>El usuario " + nickName + " se ha conectado.");
		System.out.println("Usuario " + nickName + " registrado");
	}

	public void logout(String client) {
		sendMessage("admin>El usuario " + client + " se ha desconectado.");
		messages.remove(client);
		System.out.println("Usuario " + client + " desconectado");
	}

	public void sendMessage(String message) {
		List<String> messageList;
		String emisor;
		for (String client : messages.keySet()) {
			emisor = message.split(">")[0];
			if (!(client.equals(emisor)) && !(baneados.get(client).contains(emisor))) {
				messageList = messages.get(client);
				messageList.add(message);
				messages.put(client, messageList);
			}
		}
	}

	public List<String> getMessages(String client) {
		return messages.get(client);
	}

	public void ban(String nickname, String userToBan) {
		HashSet<String> banList = baneados.get(nickname);
		banList.add(userToBan);
		baneados.put(nickname, banList);
	}

	public void unban(String nickname, String userToBan) {
		HashSet<String> banList = baneados.get(nickname);
		banList.remove(userToBan);
		baneados.put(nickname, banList);
	}
}
