package org.zaksen.fancyfishing.items;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.zaksen.fancyfishing.FancyFishing;

public class Crate {
    public static ItemStack getItem(String name) {
        ItemStack crateItem = setupCrate(new ItemStack(Material.BARREL), name);
        return crateItem;
    }

    private static ItemStack setupCrate(ItemStack crateItem, String configPath) {
        ItemMeta fishingRodMeta = crateItem.getItemMeta();
        fishingRodMeta.getPersistentDataContainer().set(new NamespacedKey(FancyFishing.getInstance(), "loot_barrel"), PersistentDataType.STRING, configPath);
        crateItem.setItemMeta(fishingRodMeta);
        return crateItem;
    }
}