package eu.theindra.audioclient.auth;

import org.webbitserver.WebSocketConnection;

import java.util.UUID;

public class Client {

    private final UUID uuid;
    private final WebSocketConnection connection;

    public Client(UUID uuid, WebSocketConnection connection) {
        this.uuid = uuid;
        this.connection = connection;
    }

    public UUID getUniqueId() {
        return uuid;
    }

    public WebSocketConnection getConnection() {
        return connection;
    }
}
