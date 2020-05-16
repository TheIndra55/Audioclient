package eu.theindra.audioclient.audio;

import eu.theindra.audioclient.Audioclient;
import eu.theindra.audioclient.auth.Client;
import eu.theindra.audioclient.protocol.Message;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class AudioManager {

	/**
	 * Plays an audio file at the specified client
	 * 
	 * @param uuid the players unique id
	 * @param url the url of the audio file
	 */
	public void play(UUID uuid, String url){
		Optional<Client> optClient = getServer().getClientByUUID(uuid);
		optClient.ifPresent(client -> new Message("play", url).send(client));
	}
	
	/**
	 * Plays an audio file at every connected client
	 * 
	 * @param url the url of the audio file
	 */
	public void play(String url){
		List<Client> clients = getServer().getClients();

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
		Optional<Client> optClient = getServer().getClientByUUID(uuid);
		optClient.ifPresent(client -> new Message("stop", "hammertime").send(client));
	}
	
	/**
	 * Stop playing at every connected client
	 * 
	 */
	public void stop(){
		List<Client> clients = getServer().getClients();
		
		for(Client client : clients){
			new Message("stop", "hammertime")
				.send(client);
		}
	}
	
	private Audioclient getServer(){
		return Audioclient.getInstance();
	}

	/**
	 * Broadcasts a custom message at every connected client
	 * @param type the type of the message
	 * @param message the message
	 */
	public void broadcast(String type, String message) {
		List<Client> clients = getServer().getClients();
		
		for(Client client : clients){
			new Message(type, message)
			.send(client);
		}
	}
	
}
