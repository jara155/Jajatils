package me.jaja.jajatils.commands.player;

import me.jaja.jajatils.handlers.Command;
import me.jaja.jajatils.configs.SpawnConfig;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Spawn {
    public Spawn(){
        new Command("setspawn", true){

            @Override
            public boolean onCommand(CommandSender sender, String[] args) {
                Player p = (Player) sender;
                Location pos = p.getLocation();

                SpawnConfig.getConfig().set("Position", pos);
                SpawnConfig.saveConfig();
                SpawnConfig.reloadConfig();

                sendMessage(sender, "SpawnSetted");

                return true;
            }

            @Override
            public String getUsage() {
                return "/setspawn";
            }
        }.setPermission("jaja.setspawn");

        new Command("spawn", true){

            @Override
            public boolean onCommand(CommandSender sender, String[] args) {
                Player p = (Player) sender;
                Location pos = SpawnConfig.getConfig().getLocation("Position");

                if(pos != null){
                    p.teleport(pos);
                    effect(p.getWorld(), p.getLocation());
                    sendMessage(sender, "TpToSpawn");
                } else {
                    sendMessage(sender, "SpawnNotExist");
                }

                return true;
            }

            @Override
            public String getUsage() {
                return "/spawn";
            }
        }.enableDelay(1);
    }

    public void effect(World world, Location pos){
        world.playEffect(pos, Effect.ENDER_SIGNAL, 0);
        world.playEffect(pos, Effect.ENDER_SIGNAL, 0);
        world.playEffect(pos, Effect.ENDER_SIGNAL, 0);
        world.playEffect(pos, Effect.ENDER_SIGNAL, 0);
    }
}
