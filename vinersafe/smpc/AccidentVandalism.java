package vinersafe.smpc;

import org.bukkit.event.EventHandler;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityInteractEvent;

public class AccidentVandalism implements Listener
{
	@EventHandler
	  public void onPlayerInteract(PlayerInteractEvent event) {
	    if (event.getAction() == Action.PHYSICAL) {
	      Block block = event.getClickedBlock();
	      if (block.getType() == Material.FARMLAND || block.getType() == Material.TURTLE_EGG)
	        event.setCancelled(true); 
	    }
	  }
	  
	  @EventHandler
	  public void onEntityInteract(EntityInteractEvent event) {
		  Material b = event.getBlock().getType();
	    if (b.equals(Material.FARMLAND)||b.equals(Material.TURTLE_EGG))
	    	event.setCancelled(true);
	    }
}