package eu.theindra.audioclient;

import eu.theindra.audioclient.auth.Client;
import eu.theindra.audioclient.handler.WebsocketHandler;
import org.webbitserver.WebServer;
import org.webbitserver.WebServers;
import org.webbitserver.WebSocketConnection;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class Audioclient {

	private static Audioclient instance;

	public static Audioclient getInstance() {
		return instance;
	}

	private final int port;
	private WebServer webServer;
	
	private final List<Client> clients = new ArrayList<>();
	
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
	
	public Optional<Client> getClientByUUID(UUID uuid){
		return getClients().stream().filter(client -> client.getUniqueId().equals(uuid)).findFirst();
	}
	
	public void removeClient(WebSocketConnection connection){
		clients.remove(connection);
	}
	
	public boolean isConnected(WebSocketConnection connection){
		return clients.stream().anyMatch(c -> c.getConnection().equals(connection));
	}
	
	public void addClient(UUID uuid, WebSocketConnection connection){
		if(!isConnected(connection)){
			clients.add(new Client(uuid, connection));
		}
	}

	public List<Client> getClients() {
		return clients;
	}
}
