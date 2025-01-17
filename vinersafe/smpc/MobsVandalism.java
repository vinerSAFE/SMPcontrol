package vinersafe.smpc;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.entity.EntityBreakDoorEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;

public class MobsVandalism implements Listener{
	@EventHandler
    public void onEntityChangeBlock(EntityChangeBlockEvent e) {
        EntityType ET = e.getEntityType();
        if (ET == EntityType.RABBIT||ET == EntityType.SHEEP||ET == EntityType.SILVERFISH||
        	ET == EntityType.ENDERMAN||ET == EntityType.RAVAGER||ET == EntityType.WITHER) {
            e.setCancelled(true);}
    }
	@EventHandler
	public void onEntityInteract(EntityBreakDoorEvent e) { //summon zombie ~ ~ ~ {CanBreakDoors:1}
		
			e.setCancelled(true);
	}
	@EventHandler
	  public void onBlazeFireballLightBlock(BlockIgniteEvent e) {
	    if (e.getIgnitingEntity() != null && (e.getIgnitingEntity().getType() != EntityType.PLAYER)) {
	    	e.setCancelled(true);
	    }
	  }
	@EventHandler
	public void paintAndItemf(HangingBreakByEntityEvent e) {
		if(e.getRemover().getType().equals(EntityType.ARROW)||e.getRemover().getType().equals(EntityType.SPECTRAL_ARROW)
		||e.getRemover().getType().equals(EntityType.TRIDENT)||e.getRemover().getType().equals(EntityType.SMALL_FIREBALL)
		||e.getRemover().getType().equals(EntityType.ENDER_PEARL)) {
			e.setCancelled(true);
		}
	}
	@EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent e) {
		if((e.getEntityType().equals(EntityType.ARMOR_STAND)||e.getEntityType().equals(EntityType.GLOW_ITEM_FRAME)||
				e.getEntityType().equals(EntityType.ITEM_FRAME))&&(e.getCause() == EntityDamageEvent.DamageCause.PROJECTILE)){
				e.setCancelled(true);
				if(e.getDamager().getType().equals(EntityType.ARROW)||e.getDamager().getType().equals(EntityType.SPECTRAL_ARROW)) {
					e.getDamager().remove();
				}
			}
		}
	@EventHandler
	public void onPiglinBarter(EntityPickupItemEvent e) {
	    if ((e.getEntityType().equals(EntityType.PIGLIN)&&e.getItem().getItemStack().getType().equals(Material.GOLD_INGOT))||
	    (e.getEntityType().equals(EntityType.FOX)&&(e.getItem().getItemStack().getType().equals(Material.COD)||
	    e.getItem().getItemStack().getType().equals(Material.SALMON)||e.getItem().getItemStack().getType().equals(Material.SWEET_BERRIES)||
	    e.getItem().getItemStack().getType().equals(Material.CHICKEN)||e.getItem().getItemStack().getType().equals(Material.TROPICAL_FISH)))
	    ||e.getEntityType().equals(EntityType.VILLAGER)||e.getEntityType().equals(EntityType.PLAYER))
	      return; 
	    e.setCancelled(true);
	  }
}