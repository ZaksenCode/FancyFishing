package org.zaksen.fancyfishing.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.zaksen.fancyfishing.FancyFishing;

public enum FishingRods {
    NULL_ROD(
            "fishing_rod"
    ),
    DIAMOND_ROD(
            "diamond_rod"
    ),
    IRON_ROD(
            "iron_rod"
    ),
    FANCY_ROD(
            "fancy_rod"
    ),
    EXPERIENCE_ROD(
            "experience_rod"
    ),
    REALISTIC_ROD(
            "realistic_rod"
    ),
    PUFFERFISH_ROD(
            "pufferfish_rod"
    ),
    NETHER_ROD(
            "nether_rod"
    ),
    MINING_ROD(
            "mining_rod"
    ),
    PIRATE_ROD(
            "pirate_rod"
    ),
    MEAT_ROD(
            "meat_rod"
    ),
    GRASS_ROD(
            "grass_rod"
    ),
    AXE_ROD(
            "axe_rod"
    );

    private final String name;
    private final int id;
    private final ItemStack rod;
    private final double xpBoost;
    private final int reqLevel;

    FishingRods(String name) {
        this.name = name;
        this.id = FancyFishing.getMainConfig().getInt("items." + name + ".modeldata");
        this.rod = FishingRod.getItem(name);
        this.xpBoost = FancyFishing.getMainConfig().getDouble("items." + name + ".xp_boost");
        this.reqLevel = FancyFishing.getMainConfig().getInt("items." + name + ".req_level");
    }

    public int getId() {
        return id;
    }

    public ItemStack getRod() {
        return rod;
    }

    public double getXpBoost() {
        return xpBoost;
    }

    public int getReqLevel() {
        return reqLevel;
    }

    public String getName() {
        return name;
    }

    public static FishingRods getRod(ItemStack itemStack) {
        for(FishingRods rod : FishingRods.values()) {
            if(itemStack.getItemMeta().hasCustomModelData() && rod.getId() == itemStack.getItemMeta().getCustomModelData()) {
                if(itemStack.getType() == Material.FISHING_ROD) {
                    return rod;
                }
            }
        }
        return NULL_ROD;
    }
}