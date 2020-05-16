package eu.theindra.audioclient;

import eu.theindra.audioclient.auth.Auth;

import java.util.UUID;

/**
 * Bukkit wont let me create a debug program
 * if class extends JavaPlugin
 * ¯\_(ツ)_/¯
 */
public class Main {

	// debugging purposes
    public static void main(String[] args) {
    	Audioclient client = new Audioclient(80);

    	//debug token
		UUID uuid = UUID.randomUUID();
		String token = Auth.generate(uuid);
		System.out.println(token);

		client.init();
		client.startServer();

		while(true){

		}
    }
}
