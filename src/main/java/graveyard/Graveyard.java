package graveyard;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public final class Graveyard extends JavaPlugin {

    @Override
    public void onEnable() {
        new Config();
        getServer().getPluginManager().registerEvents(new EventHandlers(), this);
        Bukkit.getLogger().log(Level.INFO, "[Graveyard]: Installed");
    }

    @Override
    public void onDisable() {
    }
}
