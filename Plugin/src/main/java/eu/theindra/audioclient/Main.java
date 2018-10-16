package eu.theindra.audioclient;

import org.bukkit.plugin.java.JavaPlugin;

import eu.theindra.audioclient.commands.AudioclientCommand;

public class Main extends JavaPlugin {
	
	public static Audioclient client;
	
	// debugging purposes
    public static void main(String[] args) {
		client = new Audioclient(8003);
		
		client.init();
		client.startServer();
		
		while(true){
			
		}
    }
	
	public void onEnable(){
		// TODO: Load config and the port from config
		client = new Audioclient(8003);
		
		client.init();
		client.startServer();
		
		getCommand("audioclient").setExecutor(new AudioclientCommand());
		
	}
	
	public void onDisable(){
		client.stopServer();
	}
}
