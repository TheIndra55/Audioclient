package eu.theindra.audioclient.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.msgpack.jackson.dataformat.MessagePackFactory;

public class ObjectMapperUtil {

    private static ObjectMapper mapper;

    public static ObjectMapper getMapper() {
        if(mapper == null){
            mapper = new ObjectMapper(new MessagePackFactory());
        }
        return mapper;
    }
}
