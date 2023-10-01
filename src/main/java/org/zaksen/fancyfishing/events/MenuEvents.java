package org.zaksen.fancyfishing.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.zaksen.fancyfishing.gui.MenuManager;
import org.zaksen.fancyfishing.gui.ProfileMenu;
import org.zaksen.fancyfishing.items.Recipes;

public class MenuEvents implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        ProfileMenu menu = MenuManager.getBy(player);
        if(menu != null) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        ProfileMenu menu = MenuManager.getBy(player);
        if(menu != null) {
            menu.onClose();
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Recipes.addRecipesFor(event.getPlayer());
    }
}