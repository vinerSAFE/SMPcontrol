package vinersafe.smpc;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockIgniteEvent;

public class FireVandalism implements Listener {
	
	@EventHandler
	public void onBlockBurn(BlockBurnEvent e) {
		e.setCancelled(true);
		Block block = e.getBlock();
		if (block.getRelative(0, 1, 0).getType() == Material.FIRE) {
			block.getRelative(0, 1, 0).setType(Material.AIR);
        } if(isFireOnBlock(block.getRelative(0, -1, 0))) {
        	block.getRelative(0, -1, 0).setType(Material.AIR);
        } if(isFireOnBlock(block.getRelative(1, 0, 0))) {
        	block.getRelative(1, 0, 0).setType(Material.AIR);
        } if(isFireOnBlock(block.getRelative(-1, 0, 0))) {
        	block.getRelative(-1, 0, 0).setType(Material.AIR);
        } if(isFireOnBlock(block.getRelative(0, 0, 1))) {
        	block.getRelative(0, 0, 1).setType(Material.AIR);
        } if(isFireOnBlock(block.getRelative(0, 0, -1))) {
        	block.getRelative(0, 0, -1).setType(Material.AIR);
        }
	}
	
	@EventHandler
	  public void onBlazeFireballLightBlock(BlockIgniteEvent e) {
		if ((e.getCause() != BlockIgniteEvent.IgniteCause.FLINT_AND_STEEL && e.getCause() != BlockIgniteEvent.IgniteCause.FIREBALL)||
	    	(e.getIgnitingEntity() != null && e.getIgnitingEntity().getType() != EntityType.PLAYER)){
	    	e.setCancelled(true);
	    }
	  }
	
	private boolean isFireOnBlock(Block block) {
        if(block.getType() == Material.FIRE &&!(block.getRelative(0, -1, 0).getType() == Material.NETHERRACK||
        block.getRelative(0, -1, 0).getType() == Material.SOUL_SAND||block.getRelative(0, -1, 0).getType() == Material.SOUL_SOIL)) {
        	return true;
        }
        return false;
	}
}
