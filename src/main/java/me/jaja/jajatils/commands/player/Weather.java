package me.jaja.jajatils.commands.player;

import me.jaja.jajatils.handlers.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Weather {
    public Weather(){
        new Command("sun", true){

            @Override
            public boolean onCommand(CommandSender sender, String[] args) {
                Player p = (Player) sender;
                p.getWorld().setStorm(false);

                return true;
            }

            @Override
            public String getUsage() {
                return "/sun";
            }
        }.enableDelay(2).setPermission("jaja.sun");

        new Command("rain", true){

            @Override
            public boolean onCommand(CommandSender sender, String[] args) {

                Player p = (Player) sender;
                p.getWorld().setStorm(true);

                return true;
            }

            @Override
            public String getUsage() {
                return "/rain";
            }
        }.enableDelay(2).setPermission("jaja.rain");

    }
}
