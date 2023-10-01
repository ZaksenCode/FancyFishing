package org.zaksen.fancyfishing.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.zaksen.fancyfishing.FancyFishing;
import org.zaksen.fancyfishing.data.FishingPlayer;
import org.zaksen.fancyfishing.database.DatabaseManager;
import org.zaksen.fancyfishing.items.Crates;
import org.zaksen.fancyfishing.items.FishingRods;
import org.zaksen.fancyfishing.keys.FishingKeys;
import org.zaksen.fancyfishing.util.ChatUtil;
import org.zaksen.fancyfishing.util.WeightedRandomBag;

import java.util.*;

public class FishingEvents implements Listener {

    @EventHandler
    public void onCatch(PlayerFishEvent event) {
        FishingPlayer player = DatabaseManager.instance.getPlayerData(event.getPlayer());
        if(player == null) {
            DatabaseManager.instance.addNewPlayer(event.getPlayer());
            player = DatabaseManager.instance.getPlayerData(event.getPlayer());
        }
        if(player.getLevel() < FishingRods.getRod(event.getPlayer().getInventory().getItemInMainHand()).getReqLevel()) {
            ChatUtil.sendMessage(player.getPlayer(), FancyFishing.getMainConfig().getString("messages.low_level").replaceAll("%level%", String.valueOf(FishingRods.getRod(event.getPlayer().getInventory().getItemInMainHand()).getReqLevel())), true);
            event.setCancelled(true);
            return;
        }
        if(event.getExpToDrop() > 0) {
            int minExp = FancyFishing.getMainConfig().getInt("exp.on_catch.min");
            int maxExp = FancyFishing.getMainConfig().getInt("exp.on_catch.max");
            WeightedRandomBag<String> configLootTable = new WeightedRandomBag<>();
            String selectedLootFrame;
            int random = (int) ((Math.random() * (maxExp * FishingRods.getRod(event.getPlayer().getInventory().getItemInMainHand()).getXpBoost()) + (minExp * FishingRods.getRod(event.getPlayer().getInventory().getItemInMainHand()).getXpBoost())));
            player.addXp(random);
            ChatUtil.sendMessage(player.getPlayer(), FancyFishing.getMainConfig().getString("messages.get_exp").replaceAll("%exp%", String.valueOf(random)), true);

            for (String lootFrame : FancyFishing.getMainConfig().getConfigurationSection("loot_table." + FishingRods.getRod(event.getPlayer().getInventory().getItemInMainHand()).getName()).getKeys(false)) {
                double spawnChance = FancyFishing.getMainConfig().getDouble("loot_table." + FishingRods.getRod(event.getPlayer().getInventory().getItemInMainHand()).getName() + "." + lootFrame + ".chance");
                configLootTable.addEntry("loot_table." + FishingRods.getRod(event.getPlayer().getInventory().getItemInMainHand()).getName() + "." + lootFrame, spawnChance);
            }

            selectedLootFrame = configLootTable.getRandom();

            if(FancyFishing.getMainConfig().getString(selectedLootFrame + ".type") != null ) {

                // Спавн мобов или предметов
                if(FancyFishing.getMainConfig().getString(selectedLootFrame + ".type").equals("ITEM")) {
                    player.addCatchItem();
                    ItemStack caughtItem = new ItemStack(Material.valueOf(FancyFishing.getMainConfig().getString(selectedLootFrame + ".material")));
                    ItemMeta caughtItemMeta = caughtItem.getItemMeta();
                    caughtItemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', FancyFishing.getMainConfig().getString(selectedLootFrame + ".name")));
                    List<String> caughtItemLore = FancyFishing.getMainConfig().getStringList(selectedLootFrame + ".lore");
                    caughtItemLore.replaceAll(textToTranslate -> ChatColor.translateAlternateColorCodes('&', textToTranslate
                            .replaceAll("%player%", event.getPlayer().getName())
                    ));
                    caughtItemMeta.setLore(caughtItemLore);

                    if(FancyFishing.getMainConfig().getString(selectedLootFrame + ".sub_type") != null) {
                        if((FancyFishing.getMainConfig().getString(selectedLootFrame + ".sub_type").equals("CRATE"))) {
                            caughtItemMeta.getPersistentDataContainer().set(FishingKeys.instance.barrelLoot, PersistentDataType.STRING, FancyFishing.getMainConfig().getString(selectedLootFrame + ".crate_name"));
                        } else {
                            FancyFishing.getInstance().getLogger().warning("Unknown item sub type: " + FancyFishing.getMainConfig().getString(selectedLootFrame + ".sub_type"));
                        }
                    }

                    caughtItem.setItemMeta(caughtItemMeta);

                    Item item = (Item) event.getCaught();
                    assert item != null;
                    item.setItemStack(caughtItem);
                }
                else if (FancyFishing.getMainConfig().getString(selectedLootFrame + ".type").equals("MOB")) {
                    Objects.requireNonNull(event.getCaught()).remove();

                    player.addCatchMob();
                    Entity caughtMob = event.getHook().getLocation().getWorld().spawnEntity(event.getHook().getLocation(), EntityType.valueOf(FancyFishing.getMainConfig().getString(selectedLootFrame + ".entity_type")));
                    caughtMob.setCustomName(FancyFishing.getMainConfig().getString(selectedLootFrame + ".name"));
                    caughtMob.setCustomNameVisible(true);
                    ((LivingEntity) caughtMob).setHealth(FancyFishing.getMainConfig().getDouble(selectedLootFrame + ".health"));
                }
                else
                {
                    FancyFishing.getInstance().getLogger().warning("Unknown loot_table container type: " + FancyFishing.getMainConfig().getString(selectedLootFrame + ".type"));
                }
            }
        }
    }
}