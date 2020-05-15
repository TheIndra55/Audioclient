package eu.theindra.audioclient.handler;

import eu.theindra.audioclient.Audioclient;
import eu.theindra.audioclient.auth.Auth;
import eu.theindra.audioclient.protocol.Message;
import eu.theindra.audioclient.utils.MessageDecoder;
import org.webbitserver.BaseWebSocketHandler;
import org.webbitserver.WebSocketConnection;

public class WebsocketHandler extends BaseWebSocketHandler {

    public void onOpen(WebSocketConnection connection) { }

    public void onClose(WebSocketConnection connection) { 
    	Audioclient.getInstance().removeClient(connection);
    }

    public void onMessage(WebSocketConnection connection, byte[] message) {
    	Message decoded = null;
    	
    	try {
    		decoded = new MessageDecoder(message).decode();
    	}catch(Exception e){
    		e.printStackTrace();
    		connection.close();
    		
    		return;
    	}
    
    	// server should only receive handshake nothing else
    	if(decoded.getType().equals("handshake")){
    		//Check if decoded message is a token of length 8
    		if(decoded.getMessage().length() == 8){
				Auth.validate(decoded.getMessage(), connection);
			}
    	}	
    }
	
}
