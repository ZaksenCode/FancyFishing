package org.zaksen.fancyfishing.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.zaksen.fancyfishing.gui.ProfileMenu;

import java.util.Collections;
import java.util.List;

public class SkillsCMD implements TabExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(sender instanceof Player && args.length > 0) {
            switch (args[0]) {
                case "fishing": {
                    ProfileMenu menu = new ProfileMenu((Player) sender);
                    menu.openMenu();
                }
            }
        }
        return true;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(args.length == 1) {
            return Collections.singletonList("fishing");
        }
        return null;
    }
}