package me.jaja.jajatils.listeners;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.Skull;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class Death implements Listener {

    private final Map<Location, ItemStack[]> deaths = new HashMap<>();
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        Player p = e.getEntity();
        Location pos = p.getLocation();

        e.getDrops().clear();

        if (pos.getBlock().isLiquid()){
            do {
                pos.setY(pos.getY() + 1);
            } while (pos.getBlock().isLiquid());
        }

        Block fence = pos.getBlock();
        fence.setType(Material.OAK_FENCE);

        Location skullPos = fence.getLocation();
        skullPos.setY(skullPos.getY() + 1);
        Block skull = skullPos.getBlock();
        skull.setType(Material.PLAYER_HEAD);

        Skull head = (Skull) skull.getState();
        head.setOwningPlayer(p);
        head.update();

        Location blockUnderPos = fence.getLocation();
        blockUnderPos.setY(blockUnderPos.getY() - 1);
        blockUnderPos.getBlock().setType(Material.SOUL_SAND);

        ItemStack[] items = p.getInventory().getContents();
        deaths.put(skullPos, items);
    }

    @EventHandler
    public void onPlayerUse(PlayerInteractEvent e){
        Player p = e.getPlayer();

        if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK)){

            if(e.getClickedBlock().getType().equals(Material.PLAYER_HEAD)){
                Location skull = e.getClickedBlock().getLocation();

                if(deaths.containsKey(skull)){
                    e.getClickedBlock().setType(Material.AIR);

                    Location fencePos = e.getClickedBlock().getLocation();
                    fencePos.setY(fencePos.getY() - 1);
                    fencePos.getBlock().setType(Material.AIR);

                    Location blockUnderPos = e.getClickedBlock().getLocation();
                    blockUnderPos.setY(blockUnderPos.getY() - 3);

                    if(blockUnderPos.getBlock().isLiquid()){
                        blockUnderPos.setY(blockUnderPos.getY() + 1);
                        blockUnderPos.getBlock().setType(Material.WATER);
                    } else {
                        blockUnderPos.setY(blockUnderPos.getY() + 1);
                        blockUnderPos.getBlock().setType(Material.COBBLESTONE);
                    }

                    p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_GENERIC, 1f, 1f);
                    p.getWorld().spawnParticle(Particle.CRIT_MAGIC, skull, 10);
                    p.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, skull, 0);

                    p.getInventory().setContents(deaths.get(skull));
                }
            }

        }
    }
}
