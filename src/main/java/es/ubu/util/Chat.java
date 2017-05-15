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
		sendMessage("admin > El usuario \"" + nickName + "\" se ha conectado.");
		System.out.println("Usuario \"" + nickName + "\" registrado");
	}

	public void logout(String client) {
		sendMessage("admin > El usuario \"" + client + "\" se ha desconectado.");
		messages.remove(client);
		System.out.println("Usuario \"" + client + "\" desconectado");
	}

	public void sendMessage(String message) {
		List<String> messageList;
		String emisor;
		String msg;

		for (String client : messages.keySet()) {
			emisor = message.split(" > ")[0];
			msg = message.split(" > ")[1];

			if(client.equals(emisor)){
				messageList = messages.get(client);
				messageList.add("me > " + msg);
				messages.put(client, messageList);
			}else if(!(baneados.get(client).contains(emisor))) {
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
		HashSet<String> banList = new HashSet<String>();
		List<String> messageList;

		if(baneados.containsKey(nickname)){
			banList = baneados.get(nickname);
			banList.add(userToBan);
			baneados.put(nickname, banList);
		}else{
			banList.add(userToBan);
			baneados.put(nickname, banList);
		}
		
		messageList = messages.get(nickname);
		messageList.add("admin > El usuario \""+ userToBan + "\" ha sido añadido a la lista de baneados.");
		messages.put(nickname, messageList);
		
	}

	public void unban(String nickname, String userToBan) {	
		List<String> messageList;
		messageList = messages.get(nickname);

		if(baneados.get(nickname).contains(userToBan)){
			HashSet<String> banList = baneados.get(nickname);
			banList.remove(userToBan);
			baneados.put(nickname, banList);
			messageList.add("admin > El usuario \""+ userToBan + "\" ha sido eliminado de la lista de baneados.");
			messages.put(nickname, messageList);
		}else{
			messageList.add("admin > El usuario \""+ userToBan + "\" no pertenece a la lista de baneados.");
			messages.put(nickname, messageList);
		}
		
	}
}
