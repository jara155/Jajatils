package me.jaja.jajatils.commands.player;

import me.jaja.jajatils.handlers.Command;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Speed {
    public Speed(){
        new Command("speed", 1, 2, true){

            @Override
            public boolean onCommand(CommandSender sender, String[] args) {
                Player p = (Player) sender;

                p.setWalkSpeed(Float.parseFloat(args[0]));

                if(args.length == 2){
                    Player target = Bukkit.getPlayer(args[0]);

                    if(sender.getName().equals(target.getName())){
                        sendMessage(sender, "TargetIsSelf");
                        return true;
                    }

                    if(target == null) {
                        sendMessage(sender, "PlayerNotExist");
                        return true;
                    }

                    try {
                        target.setWalkSpeed(Float.parseFloat(args[0]));
                        sendMessage(target, "SpeedSetted", "%SPEED%", args[0]);
                        sendMessage(sender, "SpeedSettedOther", "%TARGET%, %SPEED%", target.getDisplayName() + ", " + args[0]);
                    } catch (IllegalArgumentException e){
                        sendMessage(sender, "SpeedToMuch");
                    }

                }

                return true;
            }

            @Override
            public String getUsage() {
                return "/speed <Číslo> | /speed <Hráč> <Číslo>";
            }
        }.enableDelay(2).setPermission("jaja.speed");

        new Command("flyspeed", 1, 2, true){

            @Override
            public boolean onCommand(CommandSender sender, String[] args) {
                Player p = (Player) sender;

                p.setFlySpeed(Float.parseFloat(args[0]));

                if(args.length == 2){
                    Player target = Bukkit.getPlayer(args[0]);

                    if(sender.getName().equals(target.getName())){
                        sendMessage(sender, "TargetIsSelf");
                        return true;
                    }

                    if(target == null) {
                        sendMessage(sender, "PlayerNotExist");
                        return true;
                    }

                    try {
                        target.setFlySpeed(Float.parseFloat(args[0]));
                        sendMessage(target, "SpeedSetted", "%SPEED%", args[0]);
                        sendMessage(sender, "SpeedSettedOther", "%TARGET%, %SPEED%", target.getDisplayName() + ", " + args[0]);
                    } catch (IllegalArgumentException e){
                        sendMessage(sender, "SpeedToMuch");
                    }
                }

                return true;
            }

            @Override
            public String getUsage() {
                return "/flyspeed <Číslo> | /flyspeed <Hráč> <Číslo>";
            }
        }.enableDelay(2).setPermission("jaja.flyspeed");
    }

}
