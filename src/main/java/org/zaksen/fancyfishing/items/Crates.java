package org.zaksen.fancyfishing.items;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.zaksen.fancyfishing.FancyFishing;
import org.zaksen.fancyfishing.keys.FishingKeys;

public enum Crates {
    COMMON_CRATE(
            "common_crate"
    ),
    UNCOMMON_CRATE(
            "uncommon_crate"
    ),
    RARE_CRATE(
            "rare_crate"
    ),
    EPIC_CRATE(
            "epic_crate"
    ),
    LEGENDARY_CRATE(
            "legendary_crate"
    );

    private final String name;
    private final ItemStack crate;

    Crates(String name) {
        this.name = name;
        this.crate = Crate.getItem(name);
    }

    public String getName() {
        return name;
    }

    public ItemStack getCrate() {
        return crate;
    }

    public static Crates getCrate(ItemStack itemStack) {
        for(Crates crate : Crates.values()) {
            if(itemStack.getItemMeta().getPersistentDataContainer().get(FishingKeys.instance.barrelLoot, PersistentDataType.STRING).equals(crate.name)) {
                if(itemStack.getType() == Material.BARREL) {
                    return crate;
                }
            }
        }
        return COMMON_CRATE;
    }
}