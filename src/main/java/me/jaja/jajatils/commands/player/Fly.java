package me.jaja.jajatils.commands.player;

import me.jaja.jajatils.handlers.Command;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Fly {
    public Fly(){
        new Command("fly", 0, 1, true){

            @Override
            public boolean onCommand(CommandSender sender, String[] args) {

                if(args.length == 0){
                    Player p = (Player) sender;

                    if(!p.getAllowFlight()){
                        p.setAllowFlight(true);
                        p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_ELYTRA, 1f, 1f);
                        sendMessage(sender, "Fly", "%STATUS%", getConfig().getString("Enable"));
                    } else {
                        p.setAllowFlight(false);
                        sendMessage(sender, "Fly", "%STATUS%", getConfig().getString("Disable"));
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

                    if(!target.getAllowFlight()){
                        target.setAllowFlight(true);
                        target.playSound(target.getLocation(), Sound.ITEM_ARMOR_EQUIP_ELYTRA, 1f, 1f);

                        sendMessage(sender, "FlyOther", "%STATUS%, %TARGET%", getConfig().getString("Enable") + target.getDisplayName());
                        sendMessage(target, "Fly", "%STATUS%", getConfig().getString("Enable"));
                    } else {
                        target.setAllowFlight(false);
                        sendMessage(sender, "FlyOther", "%STATUS%, %TARGET%", getConfig().getString("Disable") + target.getDisplayName());
                        sendMessage(target, "Fly", "%STATUS%", getConfig().getString("Disable"));
                    }

                }

                return true;
            }

            @Override
            public String getUsage() {
                return "/fly [Hráč]";
            }
        }.enableDelay(2).setPermission("jaja.fly");
    }
}
