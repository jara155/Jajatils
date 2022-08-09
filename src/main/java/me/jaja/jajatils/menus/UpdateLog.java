package me.jaja.jajatils.menus;

import me.jaja.jajatils.handlers.Item;
import me.jaja.jajatils.handlers.Menu;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class UpdateLog extends Menu {
    public UpdateLog(){
        super(9, "Updates", "updatelog");
        setOpenAction(p -> p.playSound(p.getLocation(), Sound.ENTITY_SHULKER_OPEN, 1f, 1f));

        setItem(0, Item.create(Material.ELYTRA, "1.0"));

        setItem(8, Item.create(Material.REDSTONE_BLOCK, "Neklikej na mně"), (p, e) -> {
            ItemStack item = e.getCurrentItem();
            if(item.getType() == Material.REDSTONE_BLOCK) item = Item.create(Material.LAPIS_BLOCK);
            else item = Item.create(Material.REDSTONE_BLOCK);

            for (Player viewer : getViewers()){
                Menu m = Menu.getMenu(p);
                if(viewer.equals(p)){
                    continue;
                }
                m.setItem(e.getRawSlot(), item);
            }
        });
    }
}
