package vinersafe.smpc;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Commands implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
	  if (command.getName().equalsIgnoreCase("smpcontrol") && args.length >= 1 && args[0].equalsIgnoreCase("reload")) {
		if (sender.hasPermission("smpcontrol.reload")) {
			Main.getInstance().saveDefaultConfig();
			Main.getInstance().reloadConfig();
            sender.sendMessage("Config file reloaded successfully!");
        } else {
            sender.sendMessage("You do not have permission to reload the config file.");
        }
	  }
		return true;
	}

}
