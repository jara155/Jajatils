package me.jaja.jajatils.configs;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class SpawnConfig {

    private static File file;
    private static FileConfiguration spawnFile;

    public static void setup(){
        file = new File(Bukkit.getPluginManager().getPlugin("Jajatils").getDataFolder(), "spawn.yml");

        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e){

            }
        }
        spawnFile = YamlConfiguration.loadConfiguration(file);
    }

    public static FileConfiguration getConfig(){
        return spawnFile;
    }

    public static void saveConfig(){
        try {
            spawnFile.save(file);
        } catch (IOException e){

        }
    }

    public static void reloadConfig(){
        spawnFile = YamlConfiguration.loadConfiguration(file);
    }

}
