package me.jaja.jajatils.commands.player;

import me.jaja.jajatils.Jajatils;
import me.jaja.jajatils.handlers.Command;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Warps {
    private static FileConfiguration warpConfigFile;

    Jajatils jt = Jajatils.getPlugin(Jajatils.class);

    File warpsFolder = new File(jt.getDataFolder(), "warps");

    public Warps() {
        createWarpsFolder();

        new Command("setwarp", 1, true) {

            @Override
            public boolean onCommand(CommandSender sender, String[] args) {
                Player p = (Player) sender;
                Location pos = p.getLocation();

                File warpFile = createWarpFile(args[0], sender);
                addPositionToWarpFile(warpFile, pos);

                return true;
            }

            @Override
            public String getUsage() {
                return "/setwarp <Název>";
            }
        }.enableDelay(2).setPermission("jaja.warp");

        new Command("rmwarp", 1, true) {

            @Override
            public boolean onCommand(CommandSender sender, String[] args) {
                Player p = (Player) sender;
                Location pos = p.getLocation();

                String warps = getWarps();

                if(warps.contains(args[0])){
                    removeWarpFile(args[0]);
                    sendMessage(sender, "WarpRem", "%WARP_NAME%", args[0].replace(".yml", ""));
                } else {
                    sendMessage(sender, "WarpNotExist", "%WARP_NAME%", args[0]);
                }

                return true;
            }

            @Override
            public List<String> tabComplete(CommandSender sender, String alias, String[] args) throws IllegalArgumentException {
                return new ArrayList<String>(){{
                    for (int i = 0; i < warpsFolder.listFiles().length; i++) {
                        add(warpsFolder.listFiles()[i].getName().replace(".yml", ""));
                    };
                }};
            };

            @Override
            public String getUsage() {
                return "/rmwarp <Název>";
            }
        }.enableDelay(2).setPermission("jaja.warp");

        new Command("warp", 1, true) {

            @Override
            public boolean onCommand(CommandSender sender, String[] args) {
                String warps = getWarps();


                if(warps.contains(args[0])){
                    File warpFile = new File(warpsFolder, args[0] + ".yml");
                    Player p = (Player) sender;
                    warpConfigFile = YamlConfiguration.loadConfiguration(warpFile);
                    p.teleport(warpConfigFile.getLocation("Position"));
                    effect(p.getWorld(), p.getLocation());
                    sendMessage(sender, "WarpTeleport", "%WARP_NAME%", warpFile.getName().replace(".yml", ""));
                } else {
                    sendMessage(sender, "WarpNotExist", "%WARP_NAME%", args[0]);
                }
                return true;
            }

            @Override
            public List<String> tabComplete(CommandSender sender, String alias, String[] args) throws IllegalArgumentException {
                return new ArrayList<String>(){{
                    for (int i = 0; i < warpsFolder.listFiles().length; i++) {
                        add(warpsFolder.listFiles()[i].getName().replace(".yml", ""));
                    };
                }};
            };

            @Override
            public String getUsage() {
                return "/warp <Název> | /warps pro seznam listů";
            }
        }.enableDelay(2);

        new Command("warps", true) {

            @Override
            public boolean onCommand(CommandSender sender, String[] args) {
                String warps = getWarps();

                sendMessage(sender, "Warps", "%WARPS%", warps);
                return true;
            }

            @Override
            public String getUsage() {
                return "/warps";
            }
        }.enableDelay(2);

    };


    public void effect(World world, Location pos){
        world.playEffect(pos, Effect.ENDER_SIGNAL, 0);
        world.playEffect(pos, Effect.ENDER_SIGNAL, 0);
        world.playEffect(pos, Effect.ENDER_SIGNAL, 0);
        world.playEffect(pos, Effect.ENDER_SIGNAL, 0);
    }



    public File createWarpsFolder(){
        if (!warpsFolder.exists()) {
            warpsFolder.mkdirs();
        }
        return warpsFolder;
    }

    public File createWarpFile(String name, CommandSender sender){
        File warpFile = new File(warpsFolder, name + ".yml");
        if(!warpFile.exists()){
            try{
                warpFile.createNewFile();
                sender.sendMessage(jt.format("WarpAdd", "%WARP_NAME%", warpFile.getName().replace(".yml", "")));
            } catch (IOException e){

            }
        } else {
            sender.sendMessage(jt.format("WarpExist", "%WARP_NAME%", warpFile.getName().replace(".yml", "")));
        }

        return warpFile;
    }

    public void removeWarpFile(String fileName){
        File warpFile = new File(warpsFolder, fileName + ".yml");
        if(warpFile.exists()){
            warpFile.delete();
        }
    }

    public String getWarps(){
        List<String> warps = new ArrayList<>();
        for (int i = 0; i < warpsFolder.listFiles().length; i++) {
            warps.add(warpsFolder.listFiles()[i].getName());
        }

        StringBuilder warpsList = new StringBuilder();

        for (int i = 0; i < warps.size(); i++) {
            String warp = warps.get(i).replace(".yml", "");
            warpsList.append(warp);

            if(!(warps.size()-1 == i)){
                warpsList.append(", ");
            };

        }

        return warpsList.toString();
    }

    public void addPositionToWarpFile(File warpFile, Location pos){
        warpConfigFile = YamlConfiguration.loadConfiguration(warpFile);
        warpConfigFile.addDefault("Position", pos);
        warpConfigFile.options().copyDefaults(true);
        saveConfig(warpFile);
    }

    public void saveConfig(File warpFile){
        try {
            warpConfigFile.save(warpFile);
        }catch (IOException e){

        }
    }
}
