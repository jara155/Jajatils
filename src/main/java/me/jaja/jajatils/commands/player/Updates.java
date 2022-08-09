package me.jaja.jajatils.commands.player;

import me.jaja.jajatils.handlers.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Updates {
    public Updates(){
        new Command("updatelog", true){
            @Override
            public boolean onCommand(CommandSender sender, String[] args) {
                Player p = (Player) sender;

                new me.jaja.jajatils.menus.UpdateLog().open((Player) sender);

                return true;
            }

            @Override
            public String getUsage() {
                return "/updatelog";
            }
        }.enableDelay(2);
    }
}
