package org.zaksen.fancyfishing.gui;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.zaksen.fancyfishing.FancyFishing;
import org.zaksen.fancyfishing.data.FishingPlayer;
import org.zaksen.fancyfishing.database.DatabaseManager;

import java.util.List;

public class ProfileMenu {

    private Inventory inventory;
    private Player player;

    public ProfileMenu(Player player) {
        this.player = player;
        FishingPlayer fishingData = DatabaseManager.instance.getPlayerData(player);
        if(fishingData == null) {
            DatabaseManager.instance.addNewPlayer(player);
            fishingData = DatabaseManager.instance.getPlayerData(player);
        }
        this.inventory = Bukkit.createInventory(player, 27, FancyFishing.getMainConfig().getString("profile.title"));

        MenuManager.addMenu(this);

        ItemStack fillPanel = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
        ItemMeta fillPanelMeta = fillPanel.getItemMeta();
        fillPanelMeta.setDisplayName(" ");
        fillPanel.setItemMeta(fillPanelMeta);

        for(int i = 0; i < inventory.getSize(); i++) {
            inventory.setItem(i, fillPanel);
        }

        // Profile icon
        String[] nextLevel = fishingData.getNextLevel();
        List<String> iconLore = FancyFishing.getMainConfig().getStringList("profile.icon_lore");
        for(int i = 0; i < iconLore.size(); i++) {
            iconLore.set(i, ChatColor.translateAlternateColorCodes('&', iconLore.get(i)
                    .replaceAll("%level%", String.valueOf(fishingData.getLevel()))
                    .replaceAll("%exp%", String.valueOf(fishingData.getTotalXp()))
                    .replaceAll("%n_level%", nextLevel[0])
                    .replaceAll("%n_exp%", nextLevel[1])
            ));
        }
        ItemStack playerIcon = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta playerIconMeta = (SkullMeta) playerIcon.getItemMeta();
        playerIconMeta.setDisplayName(player.getName());
        playerIconMeta.setOwningPlayer(player);
        playerIconMeta.setLore(iconLore);
        playerIcon.setItemMeta(playerIconMeta);

        
        // Coughed stat icon
        List<String> statsLore = FancyFishing.getMainConfig().getStringList("profile.coughed_stat.lore");
        for(int i = 0; i < statsLore.size(); i++) {
            statsLore.set(i, ChatColor.translateAlternateColorCodes('&', statsLore.get(i)
                    .replaceAll("%catch_c%", String.valueOf(fishingData.getCaught()))
                    .replaceAll("%catch_m%", String.valueOf(fishingData.getMobsCaught()))
                    .replaceAll("%catch_i%", String.valueOf(fishingData.getItemCaught()))
            ));
        }

        ItemStack statsItem = new ItemStack(Material.FISHING_ROD);
        ItemMeta statsMeta = statsItem.getItemMeta();
        statsMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', FancyFishing.getMainConfig().getString("profile.coughed_stat.name")));
        statsMeta.setLore(statsLore);
        statsItem.setItemMeta(statsMeta);

        inventory.setItem(10, playerIcon);
        inventory.setItem(16, statsItem);
    }

    public void openMenu() {
        player.openInventory(inventory);
    }

    public Player getPlayer() {
        return player;
    }

    public void onClose() {
        MenuManager.removeMenu(this);
    }
}