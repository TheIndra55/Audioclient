package eu.theindra.audioclient;

import eu.theindra.audioclient.auth.Client;
import eu.theindra.audioclient.handler.WebsocketHandler;
import org.webbitserver.WebServer;
import org.webbitserver.WebServers;
import org.webbitserver.WebSocketConnection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class Audioclient {

	private static Audioclient instance;

	public static Audioclient getInstance() {
		return instance;
	}

	private final int port;
	private WebServer webServer;
	
	private final HashMap<Client, UUID> clients = new HashMap<>();
	
	public Audioclient(int port){
		this.port = port;

		instance = this;
	}
	
	public void init(){
		webServer = WebServers.createWebServer(this.port)
			.add("/", new WebsocketHandler());
	}
	
	public void startServer(){
		webServer.start();
	}
	
	public void stopServer(){
		webServer.stop();
	}

	public int getPort() {
		return port;
	}
	
	public List<Client> getClientsByUUID(UUID uuid){
		return getClients().keySet().stream().filter(client -> getClients().get(client).equals(uuid)).collect(Collectors.toList());
	}
	
	public List<Client> getAllClients(){
		return new ArrayList<>(getClients().keySet());
	}
	
	public void removeClient(WebSocketConnection connection){
		clients.remove(connection);
	}
	
	public boolean checkClient(WebSocketConnection connection){
		return clients.containsKey(connection);
	}
	
	public void addClient(UUID uuid, WebSocketConnection connection){
		clients.put(new Client(uuid, connection), uuid);
	}

	public HashMap<Client, UUID> getClients() {
		return clients;
	}
}
