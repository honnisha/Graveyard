package graveyard;

import net.Indyuce.mmocore.MMOCore;
import net.Indyuce.mmocore.api.player.PlayerData;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.jetbrains.annotations.NotNull;
import net.Indyuce.mmocore.api.Waypoint;

import java.util.List;
import java.util.Set;

public class EventHandlers implements @NotNull Listener {

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent e)
    {
        Player player = e.getPlayer();

        Config config = Config.getInstance();
        List<String> allowedWorlds = config.pluginConfig.getStringList("worlds");
        if (allowedWorlds.contains(player.getWorld())) return;

        PlayerData playerData = PlayerData.get(player);

        Waypoint closestWaypoint = null;
        double closestDistance = 0.0;
        for (Waypoint waypoint: MMOCore.plugin.waypointManager.getAll()) {
            if (waypoint.getLocation().getWorld() != player.getLocation().getWorld()) continue;
            if (!waypoint.isDefault() && !playerData.hasWaypoint(waypoint)) continue;

            double distance = waypoint.getLocation().distance(player.getLocation());
            if (closestWaypoint == null || distance < closestDistance)
            {
                closestDistance = distance;
                closestWaypoint = waypoint;
            }
        }

        if (closestWaypoint != null)
        {
            String spawnMessage = config.pluginConfig.getString("spawn-message");
            if (spawnMessage != null && !spawnMessage.equals(""))
                player.sendMessage(spawnMessage.replace("%location-name%", closestWaypoint.getName()));

            e.setRespawnLocation(closestWaypoint.getLocation());
        }
    }
}
