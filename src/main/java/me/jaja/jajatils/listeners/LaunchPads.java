package me.jaja.jajatils.listeners;

import me.jaja.jajatils.Jajatils;
import me.jaja.jajatils.commands.player.Fly;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public class LaunchPads implements Listener {

    private final ArrayList<Player> jumping_players = new ArrayList<>();
    Jajatils jt = Jajatils.getPlugin(Jajatils.class);
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        if (jt.getConfig().getBoolean("LaunchPads")) {
            Player p = e.getPlayer();
            Location blockUnder = p.getLocation();

            Material blockTop = Material.getMaterial("");

            if (jt.getConfig().getString("LaunchTopBlock").length() > 0) {
                blockTop = Material.valueOf(jt.getConfig().getString("LaunchTopBlock"));
            }
            Material blockDown = Material.valueOf(jt.getConfig().getString("LaunchDownBlock"));

            blockUnder.setY(blockUnder.getY() - 1);

            Material type = p.getLocation().getBlock().getType();

            Location pos = p.getLocation();
            pos.setY(pos.getY() + 1);

            if (blockUnder.getBlock().getType().equals(blockDown) && blockTop == null) {
                p.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, p.getLocation(), 0);
                p.setVelocity(p.getLocation().getDirection().multiply(1.5).setY(1));
                FlyEffect(p);
            } else if (type.equals(blockTop) && blockUnder.getBlock().getType().equals(blockDown)) {
                p.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, p.getLocation(), 0);
                p.setVelocity(p.getLocation().getDirection().multiply(1.5).setY(1));
                FlyEffect(p);
            }

        }


    }


    private void FlyEffect(Player p) {
        jumping_players.add(p);
        new BukkitRunnable() {
            @Override
            public void run() {
                p.getWorld().spawnParticle(Particle.FLAME, p.getLocation(), 0, 0, 0, 0);
                if (p.isOnGround())
                    this.cancel();
            }
        }.runTaskTimer(jt, 2, 1);
    }
}
