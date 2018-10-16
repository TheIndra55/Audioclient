package eu.theindra.audioclient.audio;

import java.util.List;

import org.webbitserver.WebSocketConnection;

import eu.theindra.audioclient.Audioclient;
import eu.theindra.audioclient.Main;
import eu.theindra.audioclient.protocol.MessageBuilder;

public class AudioManager {

	/**
	 * Plays an audio file at the specified client
	 * 
	 * @param username the players username
	 * @param url the url of the audio file
	 */
	public void play(String username, String url){
		List<WebSocketConnection> clients = getClient().getClientsByUsername(username);
		
		for(WebSocketConnection client : clients){
			new MessageBuilder("play", url)
				.send(client);
		}
	}
	
	/**
	 * Plays an audio file at every connected client
	 * 
	 * @param url the url of the audio file
	 */
	public void play(String url){
		List<WebSocketConnection> clients = getClient().getAllClients();
		
		for(WebSocketConnection client : clients){
			new MessageBuilder("play", url)
				.send(client);
		}
	}
	
	/**
	 * Stop playing at an specified client
	 * 
	 * @param username the players username
	 */
	public void stop(String username){
		List<WebSocketConnection> clients = getClient().getClientsByUsername(username);
		
		for(WebSocketConnection client : clients){
			new MessageBuilder("stop", "hammertime")
				.send(client);
		}
	}
	
	/**
	 * Stop playing at every connected client
	 * 
	 */
	public void stop(){
		List<WebSocketConnection> clients = getClient().getAllClients();
		
		for(WebSocketConnection client : clients){
			new MessageBuilder("stop", "hammertime")
				.send(client);
		}
	}
	
	private Audioclient getClient(){
		return Main.client;
	}

	/**
	 * Broadcasts a custom message at every connected client
	 * @param type
	 * @param message
	 */
	public void broadcast(String type, String message) {
		List<WebSocketConnection> clients = getClient().getAllClients();
		
		for(WebSocketConnection client : clients){
			new MessageBuilder(type, message)
			.send(client);
		}
	}
	
}
