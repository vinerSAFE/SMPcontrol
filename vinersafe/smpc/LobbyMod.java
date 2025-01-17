package vinersafe.smpc;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.block.EntityBlockFormEvent;
import org.bukkit.event.entity.EntityBreakDoorEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;
import org.bukkit.event.hanging.HangingBreakEvent;
import org.bukkit.event.hanging.HangingBreakEvent.RemoveCause;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.vehicle.VehicleDestroyEvent;

public class LobbyMod implements Listener {
	@EventHandler
    public void onEntityChangeBlock(EntityChangeBlockEvent e) {
        e.setCancelled(true);
    }
	@EventHandler
	public void onEntityBreakDoor(EntityBreakDoorEvent e) {
		e.setCancelled(true);
	}
	@EventHandler
	public void onEntityInteract(EntityInteractEvent e) {
	    e.setCancelled(true);
	}
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
	    e.setCancelled(!e.getPlayer().hasPermission("smpcontrol.lobbymod"));
	}
	@EventHandler
	  public void onBlazeFireballLightBlock(BlockIgniteEvent e) {
		if ((e.getCause() != null && e.getIgnitingEntity() == null)||(
		e.getIgnitingEntity() != null && !e.getIgnitingEntity().getType().equals(EntityType.PLAYER))) {
		    	e.setCancelled(true);
		}
	}
	@EventHandler
	  public void onExplode(EntityExplodeEvent e) {
		e.blockList().clear();
    }
	@EventHandler
	  public void onExplode2(BlockExplodeEvent e) {
		e.blockList().clear();
  }
	@EventHandler
	public void onhangingbreak(HangingBreakEvent e) {
		if(!e.getCause().equals(RemoveCause.ENTITY)){
			e.setCancelled(true);
		}
	}
	@EventHandler
	public void paintAndItemf(HangingBreakByEntityEvent e) {
		if(!e.getCause().equals(RemoveCause.ENTITY)&&!e.getRemover().hasPermission("smpcontrol.lobbymod")||(
		e.getRemover().getType().equals(EntityType.ARROW)||e.getRemover().getType().equals(EntityType.SPECTRAL_ARROW)
		||e.getRemover().getType().equals(EntityType.TRIDENT))) {
			e.setCancelled(true);
		}
	}
	@EventHandler
	public void onSnowGolemStep(EntityBlockFormEvent e) {
	    e.setCancelled(true);
	}
	@EventHandler
	  public void onPickupItem(EntityPickupItemEvent e) {
	    e.setCancelled(!e.getEntity().hasPermission("smpcontrol.lobbymod"));
	}
	@EventHandler
	  public void onVoid(EntityDamageEvent e) {
	    if (e.getEntityType().equals(EntityType.PLAYER) && e.getCause() == EntityDamageEvent.DamageCause.VOID) {
	      Player p = (Player)e.getEntity();
	      p.setHealth(0.0D);
	    }
	  }
	@EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent e) {
		e.setCancelled(!e.getDamager().hasPermission("smpcontrol.lobbymod"));
	}
	@EventHandler
	public void onFoodLevelChange(FoodLevelChangeEvent e) {
	    e.setCancelled(true);
	}
	@EventHandler
	public void onPlayerDropItem(PlayerDropItemEvent e) {
	    e.setCancelled(!e.getPlayer().hasPermission("smpcontrol.lobbymod"));
	}
	@EventHandler
	public void onEntityTarget(EntityTargetEvent e) {
	    e.setCancelled(true);
	}
	@EventHandler
	public void onVehicleDestroy(VehicleDestroyEvent e) {
		if(e.getAttacker() == null || !e.getAttacker().hasPermission("smpcontrol.lobbymod")) {
			e.setCancelled(true);
		}
	}
	@EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
		e.setDeathMessage(null);
        e.setKeepInventory(true);
        e.setKeepLevel(true);
        e.setDroppedExp(0);
        e.getDrops().clear();
        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), () -> e.getEntity().spigot().respawn(), 2);
	}
	@EventHandler
	public void onBlockBurn(BlockBurnEvent e) {
		e.setCancelled(true);
	}
	@EventHandler
	public void onInventoryCange(InventoryClickEvent e) {
		e.setCancelled(!e.getWhoClicked().hasPermission("smpcontrol.lobbymod"));
	}
}
