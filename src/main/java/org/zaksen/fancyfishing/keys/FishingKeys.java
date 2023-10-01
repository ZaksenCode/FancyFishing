package org.zaksen.fancyfishing.keys;

import org.bukkit.NamespacedKey;
import org.zaksen.fancyfishing.FancyFishing;

public class FishingKeys {

    public static FishingKeys instance = new FishingKeys();

    public final NamespacedKey barrelLoot = new NamespacedKey(FancyFishing.getInstance(), "loot_barrel");

    private FishingKeys() {}
}