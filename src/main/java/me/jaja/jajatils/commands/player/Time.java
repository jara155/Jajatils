package me.jaja.jajatils.commands.player;

import me.jaja.jajatils.handlers.Command;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Time {
    public Time(){
        new Command("day", true){
            @Override
            public boolean onCommand(CommandSender sender, String[] args) {
                Player p = (Player) sender;
                World world = p.getWorld();

                world.setTime(1000);
                world.setStorm(false);
                return true;
            }

            @Override
            public String getUsage() {
                return "&7Napiš to takhle: /day";
            }
        }.enableDelay(1).setPermission("jaja.day");

        new Command("night", true){
            @Override
            public boolean onCommand(CommandSender sender, String[] args) {
                Player p = (Player) sender;
                World world = p.getWorld();

                world.setTime(15000);
                world.setStorm(false);
                return true;
            }

            @Override
            public String getUsage() {
                return "/night";
            }
        }.enableDelay(1).setPermission("jaja.night");
    }
}
