package me.jaja.jajatils.commands;

import me.jaja.jajatils.configs.SpawnConfig;
import me.jaja.jajatils.configs.Welcome;
import me.jaja.jajatils.handlers.Command;
import org.bukkit.command.CommandSender;

public class Reload {
    public Reload(){
        new Command("jajareload"){
            @Override
            public boolean onCommand(CommandSender sender, String[] args){
                reloadConfig();
                SpawnConfig.setup();
                SpawnConfig.reloadConfig();
                Welcome.setup();
                Welcome.reloadConfig();
                sendMessage(sender, "Reloaded");
                return true;
            }

            @Override
            public String getUsage() {
                return "/reload";
            }
        }.setPermission("jaja.reload");
    }
}
