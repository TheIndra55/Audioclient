package eu.theindra.audioclient.protocol;

import org.webbitserver.WebSocketConnection;

import com.google.gson.JsonObject;

import eu.theindra.audioclient.utils.TimestampUtils;

public class MessageBuilder {

	private String type;
	private String message;
	
	public MessageBuilder(String type, String message){
		this.setType(type);
		this.setMessage(message);
	}
	
	public void send(WebSocketConnection connection){
		JsonObject message = new JsonObject();
		
		message.addProperty("type", this.type);
		message.addProperty("message", this.message);
		
		// add 'now' string to check latency at client
		message.addProperty("now", TimestampUtils.getISO8601Now());
		
		connection.send(message.toString());
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
