package org.zaksen.fancyfishing.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.zaksen.fancyfishing.FancyFishing;

import java.util.List;

public class CustomString {

    public static ItemStack getItem(String name) {
        ItemStack rodItem = setupString(new ItemStack(Material.STRING), name);
        return rodItem;
    }

    private static ItemStack setupString(ItemStack stringItem, String configPath) {
        List<String> itemLore = FancyFishing.getMainConfig().getStringList("items." + configPath + ".description");
        itemLore.replaceAll(textToTranslate -> ChatColor.translateAlternateColorCodes('&', textToTranslate));
        ItemMeta fishingRodMeta = stringItem.getItemMeta();
        fishingRodMeta.setDisplayName(
                ChatColor.translateAlternateColorCodes('&', FancyFishing.getMainConfig().getString("items." + configPath + ".name"))
        );
        fishingRodMeta.setLore(itemLore);
        fishingRodMeta.setCustomModelData(FancyFishing.getMainConfig().getInt("items." + configPath + ".modeldata"));

        stringItem.setItemMeta(fishingRodMeta);
        return stringItem;
    }
}