package eu.theindra.audioclient.auth;

import eu.theindra.audioclient.Audioclient;
import eu.theindra.audioclient.protocol.Message;
import eu.theindra.audioclient.utils.StringUtils;
import org.webbitserver.WebSocketConnection;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class Auth {

    private static final List<TokenHolder> HOLDERS = new ArrayList<>();

    public static String generate(UUID uuid){
        Optional<TokenHolder> optHolder = getHolderByUUID(uuid);
        String token;
        if(optHolder.isPresent()){
            token = optHolder.get().getToken();
        } else {
            TokenHolder holder = new TokenHolder(uuid);
            token = holder.getToken();
            HOLDERS.add(holder);
        }
        return token;
    }

    private static Optional<TokenHolder> getHolderByUUID(UUID uuid) {
        return HOLDERS.stream().filter(th -> th.getUniqueId().equals(uuid)).findFirst();
    }

    public static void validate(String token, WebSocketConnection connection){
        Optional<TokenHolder> optHolder = HOLDERS.stream().filter(th -> th.getToken().equals(token)).findFirst();
        if(optHolder.isPresent()){
            TokenHolder tokenHolder = optHolder.get();
            new Message("handshake","Shaked hands").send(connection);
            Audioclient.getInstance().addClient(tokenHolder.getUniqueId(), connection);
            HOLDERS.remove(tokenHolder);
        }
    }

    private static class TokenHolder {
        private final UUID uuid;
        private final String token;

        public TokenHolder(UUID uuid) {
            this.uuid = uuid;
            this.token = StringUtils.randomAlphaNumeric(8);
        }

        public UUID getUniqueId() {
            return uuid;
        }

        public String getToken() {
            return token;
        }
    }
}
