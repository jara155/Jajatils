package me.jaja.jajatils.commands.player;

import me.jaja.jajatils.Jajatils;
import me.jaja.jajatils.handlers.Command;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Vanish {
    public Vanish(){
        new Command("vanish", 0, 1, true){

            Jajatils jt = Jajatils.getPlugin(Jajatils.class);

            @Override
            public boolean onCommand(CommandSender sender, String[] args) {
                Player p;

                if(args.length == 0) {
                    p = (Player) sender;
                } else {
                    p = Bukkit.getPlayer(args[0]);

                    if(sender.getName().equals(p.getName())){
                        sendMessage(sender, "TargetIsSelf");
                        return true;
                    }

                    if(p == null) {
                        sendMessage(sender, "PlayerNotExist");
                        return true;
                    }

                }

                if(!(Jajatils.vanished_players.contains(p))){
                    Jajatils.vanished_players.add(p);
                    p.setInvulnerable(true);
                    p.sendMessage(jt.format("Vanished", "%STATUS%", jt.getConfig().getString("Enable")));

                    for(Player people : Bukkit.getOnlinePlayers()) {
                        if(!people.hasPermission("jaja.vanish")){
                            people.hidePlayer(jt, p);
                            people.sendMessage(jt.format("QuitMessage", "%TARGET%", p.getDisplayName()));
                        }
                    }
                } else {
                    Jajatils.vanished_players.remove(p);
                    p.setInvulnerable(false);
                    p.sendMessage(jt.format("Vanished", "%STATUS%", jt.getConfig().getString("Disable")));

                    for(Player people : Bukkit.getOnlinePlayers()){
                        if(!people.hasPermission("jaja.vanish")){
                            people.showPlayer(jt, p);
                            people.sendMessage(jt.format("JoinMessage", "%TARGET%", p.getDisplayName()));
                        }
                    }
                }

                return true;
            }

            @Override
            public String getUsage() {
                return "/vanish [Hráč]";
            }
        }.enableDelay(1).setPermission("jaja.vanish");

    }
}
