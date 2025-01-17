package vinersafe.smpc;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.Configuration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import net.md_5.bungee.api.ChatColor;

public class OnPlayerDeath implements Listener {
	@EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
		Configuration config = Main.getInstance().getConfig();
		
		if(config.getBoolean("protections.inventory")||e.getEntity().getPlayer().hasPermission("smpcontrol.keepinventory")) {
			e.setKeepInventory(true);
			e.getDrops().clear();
		}
		if(config.getBoolean("protections.xp")||e.getEntity().getPlayer().hasPermission("smpcontrol.keepxp")) {
			e.setKeepLevel(true);
			e.setDroppedExp(0);
		}
		if(config.getBoolean("optional.fast-spawn")||e.getEntity().getPlayer().hasPermission("smpcontrol.respawn")){
			Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), () -> e.getEntity().spigot().respawn(), 2);
		}
		if(config.getBoolean("optional.location")||e.getEntity().getPlayer().hasPermission("smpcontrol.location")) {
			Location loc = e.getEntity().getLocation();
			e.getEntity().sendMessage(ChatColor.translateAlternateColorCodes('&',"&6You died at &a" +loc.getBlockX()+"&f, &a"
			+ loc.getBlockY() +"&f, &a" +loc.getBlockZ()));
			
		}
	}
}
