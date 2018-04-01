package eu.theindra.audioclient.handler;

import org.webbitserver.BaseWebSocketHandler;
import org.webbitserver.WebSocketConnection;

import eu.theindra.audioclient.Main;
import eu.theindra.audioclient.protocol.MessageDecoder;

public class WebsocketHandler extends BaseWebSocketHandler {

    public void onOpen(WebSocketConnection connection) { }

    public void onClose(WebSocketConnection connection) { 
    	Main.client.removeClient(connection);
    }

    public void onMessage(WebSocketConnection connection, String message) {
    	MessageDecoder decoded = null;
    	
    	try {
    		decoded = new MessageDecoder(message).decode();
    	}catch(Exception e){
    		connection.close();
    		
    		return;
    	}
    
    	// server should only receive handshake nothing else
    	if(decoded.getType().equals("handshake")){
    		Main.client.addClient(connection, decoded.getMessage());
    	}	
    }
	
}
