package me.jaja.jajatils.commands.player;

import me.jaja.jajatils.handlers.Command;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Repair {
    public Repair(){
        new Command("repair", true){

            @Override
            public boolean onCommand(CommandSender sender, String[] args) {
                Player p = (Player) sender;
                ItemStack item = p.getInventory().getItemInMainHand();

                if(item.getDurability() > 0){
                    item.setDurability((short) 0);
                    p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_USE, 1f, 1f);
                    sendMessage(sender, "Repair");
                } else {
                    sendMessage(sender, "CantRepair");
                }

                return true;
            }

            @Override
            public String getUsage() {
                return "/repair";
            }
        }.enableDelay(2).setPermission("jaja.repair");
    }

}
