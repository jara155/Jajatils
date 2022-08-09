package me.jaja.jajatils.commands.player;

import me.jaja.jajatils.handlers.Command;
import org.bukkit.*;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TP {
    public TP(){
        new Command("tp", 1, 4, true){

            @Override
            public boolean onCommand(CommandSender sender, String[] args) {

                Player p = (Player) sender;

                if(args.length == 1){
                    Player target = Bukkit.getPlayer(args[0]);
                    Location targetLocation = target.getLocation();

                    if(p != target){
                        p.teleport(targetLocation);
                        effect(targetLocation.getWorld(), targetLocation);
                        sendMessage(p, "TpSender", "%TARGET%", target.getDisplayName());
                        sendMessage(target, "TpTarget", "%TARGET%", p.getDisplayName());
                    } else {
                        sendMessage(p, "TargetIsSelf");
                    }
                } else if (args.length == 3) {
                    double x = Double.parseDouble(args[0]);
                    double y = Double.parseDouble(args[1]);
                    double z = Double.parseDouble(args[2]);
                    Location tpLoc = new Location(p.getWorld(), x, y, z);
                    p.teleport(tpLoc);
                    effect(tpLoc.getWorld(), tpLoc);
                } else if (args.length == 4){
                    double x = Double.parseDouble(args[1]);
                    double y = Double.parseDouble(args[2]);
                    double z = Double.parseDouble(args[3]);
                    Player target = Bukkit.getPlayer(args[0]);
                    Location tpLoc = new Location(p.getWorld(), x, y, z);
                    if(target != null){
                        target.teleport(tpLoc);
                        effect(tpLoc.getWorld(), tpLoc);
                    }
                } else {
                    sendUsage(sender);
                }

                return true;
            }

            @Override
            public String getUsage() {
                return "/tp <Hráč> | /tp [Hráč] <x> <y> <z>";
            }
        }.enableDelay(2).setPermission("jaja.tp");

    }

    public void effect(World world, Location pos){
        world.playEffect(pos, Effect.ENDER_SIGNAL, 0);
        world.playEffect(pos, Effect.ENDER_SIGNAL, 0);
        world.playEffect(pos, Effect.ENDER_SIGNAL, 0);
        world.playEffect(pos, Effect.ENDER_SIGNAL, 0);
        world.playSound(pos, Sound.ENTITY_ENDERMAN_TELEPORT, .5f, 1f);
    }
}
