package me.jaja.jajatils.listeners;

import me.jaja.jajatils.Jajatils;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.ArrayList;
import java.util.List;

public class sendMessage implements Listener {
    private final List<String> delayedPlayers = new ArrayList<>();

    Jajatils jt = Jajatils.getPlugin(Jajatils.class);

    @EventHandler
    public void onSendMessage(AsyncPlayerChatEvent e){
        Player p = e.getPlayer();

        int delay = jt.getConfig().getInt("ChatDelay");

        e.setMessage(ChatColor.translateAlternateColorCodes('&', e.getMessage()));

        if(p.hasPermission("jaja.*") || p.hasPermission("jaja.bypass")) return;

        if(delayedPlayers.contains(p.getName())){
            p.sendMessage(jt.format("ChatDelayMsg"));
            e.setCancelled(true);
            return;
        }

        delayedPlayers.add(p.getName());
        Bukkit.getScheduler().scheduleSyncDelayedTask(jt, () -> {
            delayedPlayers.remove(p.getName());
        }, 20L * delay);
    }

}
