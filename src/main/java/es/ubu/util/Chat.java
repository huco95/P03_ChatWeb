package es.ubu.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * Clase Chat que implementa los metodos necesarios del chat.
 * 
 * @author Felix Nogal
 * @author Mario Santamaria
 */
public class Chat {

	/**
	 * Mapa con los usuarios su correspondiente lista de mensajes.
	 */
	private HashMap<String, List<String>> messages = new HashMap<String, List<String>>();

	/**
	 * Mapa con los usuarios y su correspondiente lista de baneados.
	 */
	private HashMap<String, HashSet<String>> baneados = new HashMap<String, HashSet<String>>();

	/**
	 * Constructor de la clase.
	 */
	public Chat() {
	}

	/**
	 * Registra a un cliente.
	 * 
	 * @param nickName
	 *            Nombre del cliente a registrar
	 */
	public void registerClient(String nickName) {
		messages.put(nickName, new ArrayList<String>());
		baneados.put(nickName, new HashSet<String>());
		sendMessage("admin > El usuario \"" + nickName + "\" se ha conectado.");
		System.out.println("Usuario \"" + nickName + "\" registrado");
	}

	/**
	 * Cierra la sesión del cliente.
	 * 
	 * @param client
	 *            Cliente del cual cerrar la sesión
	 */
	public void logout(String client) {
		sendMessage("admin > El usuario \"" + client + "\" se ha desconectado.");
		messages.remove(client);
		System.out.println("Usuario \"" + client + "\" desconectado");
	}

	/**
	 * Añade un mensaje a todas la lista de mensajes de todos los usuarios, a no
	 * ser que alguno este baneado.
	 * 
	 * @param message
	 *            Mensaje a añadir en las listas de mensajes
	 */
	public void sendMessage(String message) {
		List<String> messageList;
		String emisor;
		String msg;

		for (String client : messages.keySet()) {
			emisor = message.split(" > ")[0];
			msg = message.split(" > ")[1];

			if (client.equals(emisor)) {
				messageList = messages.get(client);
				messageList.add("me > " + msg);
				messages.put(client, messageList);
			} else if (!(baneados.get(client).contains(emisor))) {
				messageList = messages.get(client);
				messageList.add(message);
				messages.put(client, messageList);
			}

		}
	}

	/**
	 * Devuelve los mensajes pertenecientes a un cliente.
	 * 
	 * @param client
	 *            Cliente del cual obtener los mensajes
	 * @return Lista de mensajes correspondiente
	 */
	public List<String> getMessages(String client) {
		return messages.get(client);
	}

	/**
	 * Añade un usuario a la lista de baneados.
	 * 
	 * @param nickname
	 *            Usuario que esta baneando
	 * @param userToBan
	 *            Usuario a banear
	 */
	public void ban(String nickname, String userToBan) {
		HashSet<String> banList = new HashSet<String>();
		List<String> messageList;
			if (baneados.containsKey(nickname)) {
				banList = baneados.get(nickname);
				banList.add(userToBan);
				baneados.put(nickname, banList);
			} else {
				banList.add(userToBan);
				baneados.put(nickname, banList);
			}

			messageList = messages.get(nickname);
			messageList.add("admin > El usuario \"" + userToBan + "\" ha sido añadido a la lista de baneados.");
			messages.put(nickname, messageList);	
	}
	
	/**
	 * Elimina a un usuario de la lista de baneados.
	 * 
	 * @param nickname
	 *            Usuario que esta unbaneando
	 * @param userToBan
	 *            Usuario a unbanear
	 */
	public void unban(String nickname, String userToBan) {
		List<String> messageList;
		messageList = messages.get(nickname);

		if (baneados.get(nickname).contains(userToBan)) {
			HashSet<String> banList = baneados.get(nickname);
			banList.remove(userToBan);
			baneados.put(nickname, banList);
			messageList.add("admin > El usuario \"" + userToBan + "\" ha sido eliminado de la lista de baneados.");
			messages.put(nickname, messageList);
		} else {
			messageList.add("admin > El usuario \"" + userToBan + "\" no pertenece a la lista de baneados.");
			messages.put(nickname, messageList);
		}

	}
}
