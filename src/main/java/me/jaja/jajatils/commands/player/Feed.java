package me.jaja.jajatils.commands.player;

import me.jaja.jajatils.handlers.Command;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Feed{

    public Feed(){
        new Command("feed", 0, 1, true){
            @Override
            public boolean onCommand(CommandSender sender, String[] args){
                Player p = (Player) sender;
                if(args.length == 0){
                    p.setFoodLevel(20);
                    p.playSound(p.getLocation(), Sound.ENTITY_GENERIC_EAT, 1f, 1f);
                    sendMessage(sender, "Feed");
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

                    target.setFoodLevel(20);
                    sendMessage(p, "FeedOther", "%TARGET%", target.getDisplayName());
                    p.playSound(p.getLocation(), Sound.ENTITY_GENERIC_EAT, 1f, 1f);
                }
                return true;
            }

            @Override
            public String getUsage(){
                return "/feed [Hráč]";
            }
        }.enableDelay(2).setPermission("jaja.feed");
    }

}
