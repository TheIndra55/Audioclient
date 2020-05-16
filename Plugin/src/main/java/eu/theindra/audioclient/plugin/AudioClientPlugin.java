package eu.theindra.audioclient.plugin;

import eu.theindra.audioclient.Audioclient;
import eu.theindra.audioclient.commands.AudioclientCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class AudioClientPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        AudioClientConfig config = AudioClientConfig.load(this);
        Audioclient audioclient = new Audioclient(config.getPort());
        audioclient.init();
        audioclient.startServer();

        Objects.requireNonNull(getCommand("audioclient")).setExecutor(new AudioclientCommand());
    }

    @Override
    public void onDisable() {
        Audioclient.getInstance().stopServer();
    }
}
