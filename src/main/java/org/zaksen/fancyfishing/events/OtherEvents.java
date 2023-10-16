package org.zaksen.fancyfishing.events;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Barrel;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.zaksen.fancyfishing.FancyFishing;
import org.zaksen.fancyfishing.items.Crates;
import org.zaksen.fancyfishing.items.FishingRods;
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

                    if(FancyFishing.getMainConfig().getString(selectedLootFrame + ".type") != null) {
                        if (FancyFishing.getMainConfig().getString(selectedLootFrame + ".type").equals("BRAKED")) {
                            if(!(generatedItemMeta instanceof Damageable)) {
                                return;
                            }
                            int minDur = FancyFishing.getMainConfig().getInt(selectedLootFrame + "dur_amount.min");
                            int maxDur = FancyFishing.getMainConfig().getInt(selectedLootFrame + "dur_amount.max");
                            int random = (int) (Math.random() * maxDur + minDur);
                            ((Damageable) generatedItemMeta).setDamage(random);
                        } else if(FancyFishing.getMainConfig().getString(selectedLootFrame + ".type").equals("ENCHANTED")) {
                            if(!(generatedItemMeta instanceof EnchantmentStorageMeta)) {
                                return;
                            }
                            EnchantmentStorageMeta enchMeta = (EnchantmentStorageMeta) generatedItemMeta;
                            String finalSelectedLootFrame = selectedLootFrame;
                            FancyFishing.getMainConfig().getStringList(selectedLootFrame + ".enchants").forEach((enchName) -> {
                                int minLvl = FancyFishing.getMainConfig().getInt(finalSelectedLootFrame + "enchants." + enchName + ".min");
                                int maxLvl = FancyFishing.getMainConfig().getInt(finalSelectedLootFrame + "enchants." + enchName + ".max");
                                int random = (int) (Math.random() * minLvl + maxLvl);
                                enchMeta.addStoredEnchant(Enchantment.getByName(enchName), random, false);
                            });
                            generatedItem.setItemMeta(enchMeta);
                            return;
                        } else {
                            FancyFishing.getInstance().getLogger().warning("Unknown item sub type: " + FancyFishing.getMainConfig().getString(selectedLootFrame + ".type"));
                        }
                    }

                    generatedItem.setItemMeta(generatedItemMeta);
                }

                barrel.getInventory().setItem(i, generatedItem);
            }
        }
    }
}