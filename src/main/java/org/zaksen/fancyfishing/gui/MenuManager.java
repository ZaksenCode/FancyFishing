package org.zaksen.fancyfishing.gui;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MenuManager {
    private static final List<ProfileMenu> menus = new ArrayList<>();

    @Nullable
    public static ProfileMenu getBy(Player player) {
        for(ProfileMenu menu : menus) {
            if(menu.getPlayer() == player) {
                return menu;
            }
        }
        return null;
    }

    public static void addMenu(ProfileMenu menu) {
        menus.add(menu);
    }

    public static void removeMenu(ProfileMenu menu) {
        menus.remove(menu);
    }
}
