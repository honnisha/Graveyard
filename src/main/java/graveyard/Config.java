package graveyard;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class Config {

    public FileConfiguration pluginConfig;
    private static Config instance;

    public static Config getInstance() {
        if (instance == null) instance = new Config();
        return instance;
    }

    public Config() {
        Graveyard instance = (Graveyard) Bukkit.getPluginManager().getPlugin("Graveyard");

        File configFile = new File(instance.getDataFolder(), "config.yml");
        if (!configFile.exists()) instance.saveResource("config.yml", false);
        pluginConfig = YamlConfiguration.loadConfiguration(configFile);
    }
}
