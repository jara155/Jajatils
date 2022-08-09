package me.jaja.jajatils.commands.player;

import me.jaja.jajatils.handlers.Command;
import org.bukkit.command.CommandSender;

public class Ram {
    public Ram() {
        new Command("ram", false) {

            @Override
            public boolean onCommand(CommandSender sender, String[] args) {
                Runtime r = Runtime.getRuntime();
                long used = (r.totalMemory() - r.freeMemory()) / 1048576;
                long max = r.maxMemory() / 1048576;

                sendMessage(sender, "Ram", "%RAM_USED%, %RAM_MAX%", used + "GB" + ", " + max + "GB");

                return true;
            }

            @Override
            public String getUsage() {
                return "/ram";
            }
        }.enableDelay(1).setPermission("jaja.ram");


    }
}