package me.jaja.jajatils.commands.player;

import me.jaja.jajatils.Jajatils;
import me.jaja.jajatils.handlers.Command;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class God {
    public God(){
        new Command("god", 0, 1, true){

            Jajatils jt = Jajatils.getPlugin(Jajatils.class);

            @Override
            public boolean onCommand(CommandSender sender, String[] args) {

                if(args.length == 0){
                    Player p = (Player) sender;

                    if(!p.isInvulnerable()){
                        p.setInvulnerable(true);
                        p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_HURT, 1f, 1f);
                        sendMessage(sender, "God", "%STATUS%", jt.getConfig().getString("Enable"));
                    } else {
                        p.setInvulnerable(false);
                        sendMessage(sender, "God", "%STATUS%", jt.getConfig().getString("Disable"));
                    }


                } else {
                    Player target = Bukkit.getPlayer(args[0]);

                    if(sender.getName().equals(target.getName())){
                        sendMessage(sender, "TargetIsSelf");
                        return true;
                    }

                    if(target == null) {
                        sendMessage(sender, "PlayerNotExist");
                        return true;
                    }

                    if(!target.isInvulnerable()){
                        target.setInvulnerable(true);
                        target.playSound(target.getLocation(), Sound.ENTITY_PLAYER_HURT, 1f, 1f);
                        sendMessage(sender, "GodOther", "%STATUS%, %TARGET%", jt.getConfig().getString("Enable") + ", " + target.getDisplayName());
                        sendMessage(target, "God", "%STATUS%", jt.getConfig().getString("Enable"));
                    } else {
                        target.setInvulnerable(false);
                        sendMessage(sender, "GodOther", "%STATUS%, %TARGET%", jt.getConfig().getString("Disable") + ", " + target.getDisplayName());
                        sendMessage(target, "God", "%STATUS%", jt.getConfig().getString("Disable"));
                    }
                }

                return true;
            }

            @Override
            public String getUsage() {
                return "/god [Hráč]";
            }
        }.enableDelay(2).setPermission("jaja.god");

    }
}
