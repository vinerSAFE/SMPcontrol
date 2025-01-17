package vinersafe.smpc;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
  private static Main inctance;
  
  public void onEnable() {
    inctance = this;
    saveDefaultConfig();
    FileConfiguration fileConfiguration = getConfig();
    if (fileConfiguration.getBoolean("optional.lobby-mod")) {
      getServer().getPluginManager().registerEvents(new LobbyMod(), (Plugin)this);
    } else {
      getServer().getPluginManager().registerEvents(new OnPlayerDeath(), (Plugin)this);
      if (!fileConfiguration.getBoolean("protections.deliberately"))
        getServer().getPluginManager().registerEvents(new DeliberateVandalism(), (Plugin)this); 
      if (fileConfiguration.getBoolean("protections.accident"))
        getServer().getPluginManager().registerEvents(new AccidentVandalism(), (Plugin)this); 
      if (fileConfiguration.getBoolean("protections.fire"))
        getServer().getPluginManager().registerEvents(new FireVandalism(), (Plugin)this); 
      if (fileConfiguration.getBoolean("protections.mobs"))
        getServer().getPluginManager().registerEvents(new MobsVandalism(), (Plugin)this); 
      if (fileConfiguration.getBoolean("protections.explosions"))
        getServer().getPluginManager().registerEvents(new ExplosionsVandalism(), (Plugin)this); 
    } 
    if (getServer().getPluginManager().getPlugin("ViaVersion") != null && getServer().getPluginManager().getPlugin("ViaVersion").isEnabled()) {
      getServer().getPluginManager().registerEvents(new ViaVersion(), (Plugin)this);
    } else {
      getLogger().info(ChatColor.RED + "ViaVersion is not available or not enabled. Skipping optional functionality.");
    } 
    getLogger().info("Enabled  " + getDescription().getName() + " v" + getDescription().getVersion() + " on Server " + getServer().getVersion() + 
        " with " + HandlerList.getRegisteredListeners((Plugin)this).size() + " listener's");
    getCommand("smpcontrol").setExecutor(new Commands());
  }
  
  public static Main getInstance() {
    return inctance;
  }
}
