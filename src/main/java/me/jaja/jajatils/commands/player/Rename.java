package me.jaja.jajatils.commands.player;

import me.jaja.jajatils.handlers.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Rename {
    public Rename(){
        new Command("rename", 1, true){

            @Override
            public boolean onCommand(CommandSender sender, String[] args) {

                Player p = (Player) sender;
                System.out.println(p.getInventory().getItemInMainHand());

                return true;
            }

            @Override
            public String getUsage() {
                return "/rename <Název>";
            }
        }.enableDelay(1).setPermission("jaja.repair");
    }
}
