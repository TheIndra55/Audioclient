package eu.theindra.audioclient;

import org.bukkit.plugin.java.JavaPlugin;

import eu.theindra.audioclient.commands.AudioclientCommand;

public class Main extends JavaPlugin {
	
	public static Audioclient client;
	
	public void onEnable(){
		client = new Audioclient(80);
		
		client.init();
		client.startServer();
		
		getCommand("audioclient").setExecutor(new AudioclientCommand());
		
	}
	
	public void onDisable(){
		client.stopServer();
	}
}
