package eu.theindra.audioclient.protocol;

import eu.theindra.audioclient.auth.Client;
import eu.theindra.audioclient.utils.MessagePacker;
import eu.theindra.audioclient.utils.TimestampUtils;
import org.webbitserver.WebSocketConnection;

public class Message {

    public String type;
    public String message;
    public String now;

    public Message(){}

    public Message(String type, String message) {
        this.type = type;
        this.message = message;
        this.now = TimestampUtils.getISO8601Now();
    }

    public byte[] pack() {
        return MessagePacker.pack(this);
    }

    public void send(WebSocketConnection client){
        client.send(pack());
    }

    public String getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    public String getNow() {
        return now;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setNow(String now) {
        this.now = now;
    }

    @Override
    public String toString() {
        return "Message{" +
                "type='" + type + '\'' +
                ", message='" + message + '\'' +
                ", now='" + TimestampUtils.getISO8601Now() + '\'' +
                '}';
    }

    public void send(Client client) {
        send(client.getConnection());
    }
}
