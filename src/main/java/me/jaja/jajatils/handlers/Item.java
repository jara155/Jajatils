package me.jaja.jajatils.handlers;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class Item {

    public static ItemStack create(Material mat) { return new ItemStack(mat); };

    public static ItemStack create(Material mat, String name){
        ItemStack item = new ItemStack(mat);
        ItemMeta itemMeta = item.getItemMeta();

        itemMeta.setDisplayName(name);
        item.setItemMeta(itemMeta);

        return item;
    }

    public static ItemStack create(Material mat, String name, List<String> lore){
        ItemStack item = new ItemStack(mat);
        ItemMeta itemMeta = item.getItemMeta();

        itemMeta.setDisplayName(name);
        if(lore != null) itemMeta.setLore(lore);

        item.setItemMeta(itemMeta);
        return item;
    }

}
