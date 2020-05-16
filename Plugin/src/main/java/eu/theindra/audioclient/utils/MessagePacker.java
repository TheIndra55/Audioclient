package eu.theindra.audioclient.utils;

import eu.theindra.audioclient.protocol.Message;

import java.io.IOException;

public class MessagePacker {

    public static byte[] pack(Message message){
        byte[] result = new byte[0];
        try {
            result = ObjectMapperUtil.getMapper().writeValueAsBytes(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


}
