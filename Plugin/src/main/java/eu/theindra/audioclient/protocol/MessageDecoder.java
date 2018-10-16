package eu.theindra.audioclient.protocol;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class MessageDecoder {

	private final String encodedmsg;
	
	private String type;
	private String message;
	
	public MessageDecoder(String encodedmsg){
		this.encodedmsg = encodedmsg;
	}
	
	public MessageDecoder decode() throws Exception{
		JsonObject json = new Gson().fromJson(encodedmsg, JsonObject.class);
		
		if(json.get("type") == null || json.get("message") == null){
			throw new Exception("Invalid message");
		}
		
		this.type = json.get("type").getAsString();
		this.message = json.get("message").getAsString();
		
		return this;
	}	

	public String getEncodedmsg() {
		return encodedmsg;
	}

	public String getMessage() {
		return message;
	}

	public String getType() {
		return type;
	}
	
	public String toString(){
		return "type: " + getType() + "\nmessage: " + getMessage();
	}
	
}
