package vinersafe.smpc;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.data.type.RespawnAnchor;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;
import org.bukkit.event.hanging.HangingBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;


public class ExplosionsVandalism implements Listener{
	Location a;
	@EventHandler
	  public void onEntityExplode(EntityExplodeEvent e) {
		EntityType ET = e.getEntityType();
	    if (ET == EntityType.ENDER_DRAGON||ET == EntityType.CREEPER||ET == EntityType.WITHER||ET == EntityType.WITHER_SKULL||
	    	ET == EntityType.FIREBALL||ET == EntityType.ENDER_CRYSTAL) {
	    	e.blockList().clear();
	    	
	    }
    }
	@EventHandler
	public void onBlockExplode(BlockExplodeEvent e) {
		if (a != null && e.getBlock().getType() == Material.AIR&&a.equals(e.getBlock().getLocation())) {
			e.blockList().clear();
		}
	}
	@EventHandler
	public void pleyeri(PlayerInteractEvent e) {
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK&&e.getClickedBlock().getType() == Material.RESPAWN_ANCHOR
		&&!(((RespawnAnchor) e.getClickedBlock().getBlockData()).getCharges() == 0)) {
			a= e.getClickedBlock().getLocation();
		}
	}
	@EventHandler //fireball shoted by player
	public void paintAndItemf(HangingBreakByEntityEvent e) {
		if(e.getRemover().getType() == EntityType.FIREBALL) {
			e.setCancelled(true);
		}
	}
	@EventHandler //cancel explosions to item freames and paints
	public void paintAndItemf2(HangingBreakEvent e) {
		if(e.getCause() == HangingBreakEvent.RemoveCause.EXPLOSION) {
			e.setCancelled(true);
		}
	}
	@EventHandler
    public void protectEntity(EntityDamageEvent e) {
		if((e.getEntityType() == EntityType.DROPPED_ITEM||e.getEntityType() == EntityType.ARMOR_STAND
		)&&(e.getCause() == EntityDamageEvent.DamageCause.BLOCK_EXPLOSION||e.getCause() == EntityDamageEvent.DamageCause.ENTITY_EXPLOSION)){
			
			e.setCancelled(true);
		}
    }

}
