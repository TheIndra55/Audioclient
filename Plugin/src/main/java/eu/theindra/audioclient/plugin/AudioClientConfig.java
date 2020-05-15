package eu.theindra.audioclient.plugin;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;

public class AudioClientConfig {

    private final int port;

    public AudioClientConfig(int port) {
        this.port = port;
    }

    public int getPort() {
        return port;
    }

    public static AudioClientConfig load(AudioClientPlugin plugin){
        if(!plugin.getDataFolder().exists()){
            plugin.getDataFolder().mkdirs();
        }
        File configFile = new File(plugin.getDataFolder(),"config.json");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        if(!configFile.exists()){
            try(Writer writer = new FileWriter(configFile)){
                writer.write(gson.toJson(new AudioClientConfig(1898)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        AudioClientConfig result = new AudioClientConfig(1898);
        try(Reader reader = new FileReader(configFile)){
            result = gson.fromJson(reader, AudioClientConfig.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
