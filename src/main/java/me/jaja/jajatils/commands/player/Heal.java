package me.jaja.jajatils.commands.player;

import me.jaja.jajatils.handlers.Command;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Heal {
    public Heal(){
        new Command("heal", 0, 1){
            @Override
            public boolean onCommand(CommandSender sender, String[] args) {
                Player p = (Player) sender;

                if(args.length == 0){
                    p.setHealth(20.0);
                    sendMessage(p, "Heal");
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

                    target.setHealth(20.0);
                    sendMessage(p, "HealOther", "%TARGET%", target.getDisplayName());
                }

                return true;
            }

            @Override
            public String getUsage() {
                return "/heal [Hráč]";
            }
        }.enableDelay(2).setPermission("jaja.heal");
    }
}
