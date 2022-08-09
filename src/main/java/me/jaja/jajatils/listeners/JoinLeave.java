package me.jaja.jajatils.listeners;

import me.jaja.jajatils.Jajatils;
import me.jaja.jajatils.configs.Welcome;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.List;

public class JoinLeave implements Listener {
    Jajatils jt = Jajatils.getPlugin(Jajatils.class);

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();

        e.setJoinMessage(jt.format("JoinMessage", "%TARGET%", p.getDisplayName()));

        if(Jajatils.vanished_players.contains(p)){
            for(Player people : Bukkit.getOnlinePlayers()){
                if(!people.hasPermission("jaja.vanish")){
                    people.hidePlayer(jt, p);
                }
            }
        }

        if(Welcome.getConfig().getBoolean("welcome")){
            String msg = Welcome.getConfig().getString("message");
            List<String> msgList = Welcome.getConfig().getStringList("message");

            if(msg.length() == 0){
                p.sendMessage(jt.format(msg, "%TARGET%", p.getDisplayName()));
            } else {
                for (int i = 0; i < msgList.size(); i++) {
                    String message = msgList.get(i);
                    p.sendMessage(jt.format(message, "%TARGET%", p.getDisplayName()));
                }
            }

        }

    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e){
        Player p = e.getPlayer();

        e.setQuitMessage(jt.format("QuitMessage", "%TARGET%", p.getDisplayName()));
    }
}
