package vinersafe.smpc;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.viaversion.viaversion.api.Via;
import com.viaversion.viaversion.api.protocol.version.ProtocolVersion;

public class ViaVersion implements Listener{
	boolean viaVersion=Main.getInstance().getConfig().getBoolean("security.join-global");
	@EventHandler
	public void onjoin(PlayerJoinEvent e) {
		if(viaVersion) {
			e.setJoinMessage(null);
			Bukkit.broadcastMessage(ChatColor.YELLOW+e.getPlayer().getName()+" joined with version : "+ChatColor.GREEN+
			ProtocolVersion.getProtocol(Via.getAPI().getPlayerVersion(e.getPlayer().getUniqueId())).getName());
		}else
			Main.getInstance().getLogger().info(ChatColor.YELLOW+e.getPlayer().getName()+" joined with version : "+ChatColor.GREEN+
			ProtocolVersion.getProtocol(Via.getAPI().getPlayerVersion(e.getPlayer().getUniqueId())).getName());
	}
}