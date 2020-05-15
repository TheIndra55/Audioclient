package eu.theindra.audioclient.audio;

import eu.theindra.audioclient.Audioclient;
import eu.theindra.audioclient.auth.Client;
import eu.theindra.audioclient.protocol.Message;

import java.util.List;
import java.util.UUID;

public class AudioManager {

	/**
	 * Plays an audio file at the specified client
	 * 
	 * @param uuid the players unique id
	 * @param url the url of the audio file
	 */
	public void play(UUID uuid, String url){
		List<Client> clients = getClient().getClientsByUUID(uuid);
		
		for(Client client : clients){
			new Message("play", url)
				.send(client);
		}
	}
	
	/**
	 * Plays an audio file at every connected client
	 * 
	 * @param url the url of the audio file
	 */
	public void play(String url){
		List<Client> clients = getClient().getAllClients();
		
		for(Client client : clients){
			new Message("play", url)
				.send(client);
		}
	}
	
	/**
	 * Stop playing at an specified client
	 * 
	 * @param uuid the players unique id
	 */
	public void stop(UUID uuid){
		List<Client> clients = getClient().getClientsByUUID(uuid);

		for(Client client : clients){
			new Message("stop", "hammertime")
				.send(client);
		}
	}
	
	/**
	 * Stop playing at every connected client
	 * 
	 */
	public void stop(){
		List<Client> clients = getClient().getAllClients();
		
		for(Client client : clients){
			new Message("stop", "hammertime")
				.send(client);
		}
	}
	
	private Audioclient getClient(){
		return Audioclient.getInstance();
	}

	/**
	 * Broadcasts a custom message at every connected client
	 * @param type
	 * @param message
	 */
	public void broadcast(String type, String message) {
		List<Client> clients = getClient().getAllClients();
		
		for(Client client : clients){
			new Message(type, message)
			.send(client);
		}
	}
	
}
