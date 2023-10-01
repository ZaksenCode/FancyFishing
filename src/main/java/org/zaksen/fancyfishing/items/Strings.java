package org.zaksen.fancyfishing.items;

import org.bukkit.inventory.ItemStack;

public enum Strings {
    IRON_STRING(
            "iron_string"
    ),
    GOLD_STRING(
            "gold_string"
    ),
    DIAMOND_STRING(
            "diamond_string"
    ),
    NETHERITE_STRING(
            "netherite_string"
    );

    private final String name;
    private final ItemStack string;

    Strings(String name) {
        this.name = name;
        this.string = CustomString.getItem(name);
    }

    public String getName() {
        return name;
    }

    public ItemStack getString() {
        return string;
    }
}