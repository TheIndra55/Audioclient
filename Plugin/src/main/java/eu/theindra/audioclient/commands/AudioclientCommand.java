package eu.theindra.audioclient.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import eu.theindra.audioclient.audio.AudioManager;

public class AudioclientCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) {
		//TODO: Fix an awesome command
		//For now its just 'kut'
		if(args[0].equalsIgnoreCase("broadcast")) {
			//TODO: Collections (Java 8).
			StringBuilder sb = new StringBuilder();
			for (int i = 2; i < args.length; i++) {
				sb.append(args[i]).append(" ");
			}
			new AudioManager().broadcast(args[1],sb.toString());
		}
		return false;
	}

}
