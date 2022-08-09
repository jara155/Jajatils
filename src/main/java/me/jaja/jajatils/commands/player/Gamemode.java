package me.jaja.jajatils.commands.player;

import me.jaja.jajatils.handlers.Command;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Gamemode {
    public Gamemode(){
        new Command("gmc", 0, 1, true){

            @Override
            public boolean onCommand(CommandSender sender, String[] args) {

                if(args.length == 0){
                    Player p = (Player) sender;
                    p.setGameMode(GameMode.CREATIVE);
                    sendMessage(p, "Gamemode", "%GAMEMODE%", "Creative");
                } else {
                    Player target = Bukkit.getPlayer(args[0]);

                    if(sender.getName().equals(target.getName())){
                        sendMessage(sender, "TargetIsSelf");
                        return true;
                    }

                    if(target == null) {
                        sendMessage(sender, "PlayerNotExist");
                        return true;
                    }

                    target.setGameMode(GameMode.CREATIVE);
                    sendMessage(target, "GamemodeOther", "%GAMEMODE%, %TARGET%","Creative, " + target.getDisplayName());
                    sendMessage(sender, "Gamemode", "%GAMEMODE%", "Creative");
                }

                return true;
            }

            @Override
            public String getUsage() {
                return "/gmc [Hráč]";
            }
        }.enableDelay(1).setPermission("jaja.gmc");

        new Command("gms", 0, 1, true){

            @Override
            public boolean onCommand(CommandSender sender, String[] args) {

                if(args.length == 0){
                    Player p = (Player) sender;
                    p.setGameMode(GameMode.SURVIVAL);
                    sendMessage(p, "Gamemode", "%GAMEMODE%", "Survival");
                } else {
                    Player target = Bukkit.getPlayer(args[0]);

                    if(sender.getName().equals(target.getName())){
                        sendMessage(sender, "TargetIsSelf");
                        return true;
                    }

                    if(target == null) {
                        sendMessage(sender, "PlayerNotExist");
                        return true;
                    }

                    target.setGameMode(GameMode.SURVIVAL);
                    sendMessage(target, "GamemodeOther", "%GAMEMODE%, %TARGET%","Survival, " + target.getDisplayName());
                    sendMessage(sender, "Gamemode", "%GAMEMODE%", "Survival");
                }

                return true;
            }

            @Override
            public String getUsage() {
                return "/gms [Hráč]";
            }
        }.enableDelay(1).setPermission("jaja.gms");

        new Command("gma", 0, 1, true){

            @Override
            public boolean onCommand(CommandSender sender, String[] args) {

                if(args.length == 0){
                    Player p = (Player) sender;
                    p.setGameMode(GameMode.ADVENTURE);
                    sendMessage(p, "Adventure", "%GAMEMODE%", "Adventure");
                } else {
                    Player target = Bukkit.getPlayer(args[0]);

                    if(sender.getName().equals(target.getName())){
                        sendMessage(sender, "TargetIsSelf");
                        return true;
                    }

                    if(target == null) {
                        sendMessage(sender, "PlayerNotExist");
                        return true;
                    }

                    target.setGameMode(GameMode.ADVENTURE);
                    sendMessage(target, "GamemodeOther", "%GAMEMODE%, %TARGET%","Adventure, " + target.getDisplayName());
                    sendMessage(sender, "Gamemode", "%GAMEMODE%", "Adventure");
                }

                return true;
            }

            @Override
            public String getUsage() {
                return "/gma [Hráč]";
            }
        }.enableDelay(1).setPermission("jaja.gma");
    }

}
