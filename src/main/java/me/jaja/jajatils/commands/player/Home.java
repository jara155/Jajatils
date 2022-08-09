package me.jaja.jajatils.commands.player;

import me.jaja.jajatils.handlers.Command;
import org.bukkit.command.CommandSender;

public class Home {
    public Home(){
        new Command("home", 1, true){

            @Override
            public boolean onCommand(CommandSender sender, String[] args) {



                return true;
            }

            @Override
            public String getUsage() {
                return "/home <Název>";
            }
        };
    }
}
