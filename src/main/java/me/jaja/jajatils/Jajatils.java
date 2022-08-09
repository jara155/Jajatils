package me.jaja.jajatils;

import me.jaja.jajatils.commands.*;
import me.jaja.jajatils.commands.player.*;
import me.jaja.jajatils.configs.*;
import me.jaja.jajatils.listeners.*;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class Jajatils extends JavaPlugin{

    public static ArrayList<Player> vanished_players = new ArrayList<>();
    @Override
    public void onEnable() {
        // Plugin startup logic

        loadConfig();
        loadWelcomeConfig();
        loadSpawnConfig();

        getServer().getPluginManager().registerEvents(new JoinLeave(), this);
        getServer().getPluginManager().registerEvents(new sendMessage(), this);
        getServer().getPluginManager().registerEvents(new LaunchPads(), this);
        getServer().getPluginManager().registerEvents(new Death(), this);
        getServer().getPluginManager().registerEvents(new Inventory(), this);


        new Reload();
        new Spawn();
        new Ram();

        new Rename();

        new Updates();

        new Vanish();
        new God();

        new Remote();
        new Invsee();

        new Warps();
        new TP();

        new Time();
        new Weather();

        new Repair();
        new Speed();

        new Feed();
        new Heal();

        new Fly();
        new Gamemode();
    }

    public void loadConfig(){
        getConfig().options().copyDefaults();
        saveDefaultConfig();
    }

    public void loadWelcomeConfig(){
        Welcome.setup();
        Welcome.getConfig().addDefault("welcome", true);
        Welcome.getConfig().addDefault("message", "&dVítej %TARGET%.");
        Welcome.getConfig().options().copyDefaults(true);
        Welcome.saveConfig();
    }

    public void loadSpawnConfig(){
        SpawnConfig.setup();
        SpawnConfig.getConfig().options().copyDefaults(true);
        SpawnConfig.saveConfig();
    }

    public String format(String text){
        String string = getConfig().getString(text);
        String prefix = getConfig().getString("Prefix") + " &f";

        String msg = prefix + string;

        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    public String format(String text, String placeholder, String replace) {
        String string = getConfig().getString(text);
        String prefix = "";

        if(!(text.equals("JoinMessage") || text.equals("QuitMessage"))) {
            prefix = getConfig().getString("Prefix") + " &f";
        }

        if(text.equals("Warps")){
            string = string.replace(placeholder, replace);

            String msg = prefix + string;

            return ChatColor.translateAlternateColorCodes('&', msg);
        }

        List<String> placeholders = Arrays.asList(placeholder.split("\\s*,\\s*"));
        List<String> replaces = Arrays.asList(replace.split("\\s*,\\s*"));

        if(placeholders.size() == 0){
            string.replace(placeholders.get(0), replaces.get(0));
        }

        if(placeholders.size() == replaces.size()){
            for (int i = 0; i < placeholders.size(); i++) {
                string = string.replace(placeholders.get(i), replaces.get(i));
            }
        } else {
            System.out.println(getConfig().getString("NotEnoughArgs") + " | " + placeholders + " " + replaces);
        }

        String msg = prefix + string;

        return ChatColor.translateAlternateColorCodes('&', msg);
    }

}
