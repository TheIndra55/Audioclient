package eu.theindra.audioclient.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import eu.theindra.audioclient.audio.AudioManager;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AudioclientCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) {
		//TODO: Fix an awesome command
		//For now its just 'kut'
		if(args[0].equalsIgnoreCase("broadcast")) {
			String sb = IntStream.range(2, args.length).mapToObj(i -> args[i] + " ").collect(Collectors.joining());
			new AudioManager().broadcast(args[1], sb);
		}
		return false;
	}

}
