package me.jaja.jajatils.commands.player;

import me.jaja.jajatils.handlers.Command;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryView;

public class Remote {
    public Remote(){
        new Command("craft", true){

            @Override
            public boolean onCommand(CommandSender sender, String[] args) {
                Player p = (Player) sender;

                p.openWorkbench(null, true);
                p.playSound(p.getLocation(), Sound.BLOCK_WOOD_PLACE, 1f, 1f);

                return true;
            }

            @Override
            public String getUsage() {
                return "/craft";
            }
        }.setPermission("jaja.craft");
    }
}
