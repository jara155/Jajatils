package me.jaja.jajatils.configs;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Welcome {

    private static File file;
    private static FileConfiguration welcomeFile;

    public static void setup(){
        file = new File(Bukkit.getPluginManager().getPlugin("Jajatils").getDataFolder(), "welcome.yml");

        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e){

            }
        }
        welcomeFile = YamlConfiguration.loadConfiguration(file);
    }

    public static FileConfiguration getConfig(){
        return welcomeFile;
    }

    public static void saveConfig(){
        try {
            welcomeFile.save(file);
        } catch (IOException e){

        }
    }

    public static void reloadConfig(){
        welcomeFile = YamlConfiguration.loadConfiguration(file);
    }

}
