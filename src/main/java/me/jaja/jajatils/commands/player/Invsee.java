package me.jaja.jajatils.commands.player;

import me.jaja.jajatils.handlers.Command;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Invsee {
    public Invsee(){
        new Command("invsee", 1, true){

            @Override
            public boolean onCommand(CommandSender sender, String[] args) {
                Player p = (Player) sender;
                Player target = Bukkit.getPlayer(args[0]);

                if(sender.getName().equals(target.getName())){
                    sendMessage(sender, "TargetIsSelf");
                    return true;
                }

                if(target == null) {
                    sendMessage(sender, "PlayerNotExist");
                    return true;
                }

                p.openInventory(target.getInventory());

                return true;
            }

            @Override
            public String getUsage() {
                return "/invsee <Hráč>";
            }
        }.enableDelay(1).setPermission("jaja.invsee");

    }
}
