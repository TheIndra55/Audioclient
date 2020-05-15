package eu.theindra.audioclient.utils;

import eu.theindra.audioclient.protocol.Message;

import java.io.IOException;

public class MessageDecoder {

	private final byte[] packedMessage;
	
	public MessageDecoder(byte[] packedmsg){
		this.packedMessage = packedmsg;
	}
	
	public Message decode() throws IOException {
		return ObjectMapperUtil.getMapper().readValue(packedMessage, Message.class);
	}
}
