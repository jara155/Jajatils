package me.jaja.jajatils.listeners;

import me.jaja.jajatils.handlers.Menu;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;

public class Inventory implements Listener {

    @EventHandler
    private void invDrag(InventoryDragEvent e){
        Player p = (Player) e.getWhoClicked();
        Menu menu = Menu.getMenu(p);
        if(menu != null) {
            e.setCancelled(true);
            if (menu.getGeneralDragAction() != null) menu.getGeneralDragAction().drag(p, e);
        }
    }

    @EventHandler
    private void invClick(InventoryClickEvent e){
        Player p = (Player) e.getWhoClicked();
        Menu menu = Menu.getMenu(p);
        if(menu != null){
            e.setCancelled(true);
            if(e.getClickedInventory() != null){
                // click in custom inv
                if(e.getRawSlot() >= p.getOpenInventory().getTopInventory().getSize()){
                    if(menu.getGeneralInvClickAction() != null) menu.getGeneralClickAction().click(p, e);
                } else if(menu.getGeneralClickAction() != null) menu.getGeneralClickAction().click(p, e);
            }

            Menu.MenuClick menuClick = menu.getAction(e.getRawSlot());
            if(menuClick != null) menuClick.click(p, e);
        }
    }

    @EventHandler
    private void invClose(InventoryCloseEvent e){
        Player p = (Player) e.getPlayer();
        Menu menu = Menu.getMenu(p);
        if(menu != null) menu.remove();
    }
}
