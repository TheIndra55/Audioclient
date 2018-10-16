package eu.theindra.audioclient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.webbitserver.WebServer;
import org.webbitserver.WebServers;
import org.webbitserver.WebSocketConnection;

import eu.theindra.audioclient.handler.WebsocketHandler;

public class Audioclient {

	private final int port;
	private WebServer webServer;
	
	private HashMap<WebSocketConnection, String> clients = new HashMap<WebSocketConnection, String>();
	
	public Audioclient(int port){
		this.port = port;
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
	
	public List<WebSocketConnection> getClientsByUsername(String username){
		List<WebSocketConnection> clients = new ArrayList<WebSocketConnection>();
		
		for(WebSocketConnection client : getClients().keySet()){
			if(getClients().get(client).equals(username)){
				clients.add(client);
			}
		}
		
		return clients;
	}
	
	public List<WebSocketConnection> getAllClients(){
		List<WebSocketConnection> clients = new ArrayList<WebSocketConnection>();
		
		for(WebSocketConnection client : getClients().keySet()){
			clients.add(client);
		}
		
		return clients;
	}
	
	public void removeClient(WebSocketConnection connection){
		clients.remove(connection);
	}
	
	public boolean checkClient(WebSocketConnection connection){
		return clients.containsKey(connection);
	}
	
	public void addClient(WebSocketConnection connection, String name){
		clients.put(connection, name);
	}
	
	public HashMap<WebSocketConnection, String> getClients(){
		return clients;
	}
}
