package es.ubu.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Chat {

	public HashMap<String, List<String>> messages = new HashMap<String, List<String>>();

	public Chat() {
	}

	public void registerClient(String nickName) {
		messages.put(nickName, new ArrayList<String>());
		System.out.println("Usuario Registrado");
	}

	public void sendMessage(String message) {
		List<String> messageList;
		for (String client : messages.keySet()) {
			messageList = messages.get(client);
			messageList.add(message);
			messages.put(client, messageList);
		}
	}

	public String showMessages(String client) {
		StringBuffer messagesFinal = new StringBuffer();

		List<String> messageList;
		messageList = messages.get(client);
		for (String message : messageList) {
			messagesFinal.append(message);
		}
		return messagesFinal.toString();
	}
}
