package org.zaksen.fancyfishing.events;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Barrel;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.zaksen.fancyfishing.FancyFishing;
import org.zaksen.fancyfishing.items.Crates;
import org.zaksen.fancyfishing.keys.FishingKeys;
import org.zaksen.fancyfishing.util.WeightedRandomBag;

import java.util.List;
import java.util.Objects;

public class OtherEvents implements Listener {

    @EventHandler
    public void onPlaceBarrel(BlockPlaceEvent event) {
        ItemStack stack = event.getItemInHand();
        if(Objects.requireNonNull(stack.getItemMeta()).getPersistentDataContainer().has(FishingKeys.instance.barrelLoot, PersistentDataType.STRING)) {
            Block block = event.getBlockPlaced();
            Barrel barrel = (Barrel) block.getState();
            String barrelType = stack.getItemMeta().getPersistentDataContainer().get(FishingKeys.instance.barrelLoot, PersistentDataType.STRING);
            WeightedRandomBag<String> configLootTable = new WeightedRandomBag<>();
            String selectedLootFrame;

            for (String lootFrame : FancyFishing.getMainConfig().getConfigurationSection("barrel_loot." + barrelType).getKeys(false)) {
                double spawnChance = FancyFishing.getMainConfig().getDouble("barrel_loot." + barrelType + "." + lootFrame + ".chance");
                configLootTable.addEntry("barrel_loot." + Crates.getCrate(stack).getName() + "." + lootFrame, spawnChance);
            }

            for(int i = 0; i < barrel.getInventory().getSize(); i++) {
                selectedLootFrame = configLootTable.getRandom();

                ItemStack generatedItem = new ItemStack(Material.valueOf(FancyFishing.getMainConfig().getString(selectedLootFrame + ".material")));
                if(!generatedItem.getType().isAir()) {

                    ItemMeta generatedItemMeta = generatedItem.getItemMeta();
                    generatedItemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', FancyFishing.getMainConfig().getString(selectedLootFrame + ".name")));
                    List<String> caughtItemLore = FancyFishing.getMainConfig().getStringList(selectedLootFrame + ".lore");
                    caughtItemLore.replaceAll(textToTranslate -> ChatColor.translateAlternateColorCodes('&', textToTranslate
                            .replaceAll("%player%", event.getPlayer().getName())
                    ));
                    generatedItemMeta.setLore(caughtItemLore);
                    generatedItem.setItemMeta(generatedItemMeta);
                }

                barrel.getInventory().setItem(i, generatedItem);
            }
        }
    }
}