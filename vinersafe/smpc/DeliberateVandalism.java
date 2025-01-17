package vinersafe.smpc;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.type.Bed;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class DeliberateVandalism implements Listener{
	Location b;
	@EventHandler
	  public void onCreeperExplode(EntityExplodeEvent e) {
	    if (e.getEntityType().equals(EntityType.MINECART_TNT)||e.getEntityType().equals(EntityType.PRIMED_TNT)) {
	    	e.blockList().clear();
	    }
  }
	@EventHandler
	public void onBlockExplode(BlockExplodeEvent e) {
		if (b != null&&e.getBlock().getType().equals(Material.AIR)&&b.equals(e.getBlock().getLocation())) {
			e.blockList().clear();
		}
	}
	@EventHandler
	public void pleyeri(PlayerInteractEvent e) {
		if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK)&&e.getClickedBlock().getBlockData() instanceof Bed&&
		(e.getPlayer().getLocation().getWorld().getEnvironment().equals(World.Environment.NETHER)||
		e.getPlayer().getLocation().getWorld().getEnvironment().equals(World.Environment.THE_END))) {
			
			Bed bedData = (Bed) e.getClickedBlock().getBlockData();
			if(bedData.getPart().equals(Bed.Part.HEAD)) {
				b = e.getClickedBlock().getLocation();} else {
					
				if(bedData.getFacing().equals(BlockFace.NORTH)) {
					b = e.getClickedBlock().getRelative(0, 0, -1).getLocation();
				}
				
				if(bedData.getFacing().equals(BlockFace.SOUTH)) {
					b = e.getClickedBlock().getRelative(0, 0, 1).getLocation();
				}
				
				if(bedData.getFacing().equals(BlockFace.EAST)) {
					b = e.getClickedBlock().getRelative(1, 0, 0).getLocation();
				}
				
				if(bedData.getFacing().equals(BlockFace.WEST)) {
					b = e.getClickedBlock().getRelative(-1, 0, 0).getLocation();
				}
			}
		}
	}
	
}
