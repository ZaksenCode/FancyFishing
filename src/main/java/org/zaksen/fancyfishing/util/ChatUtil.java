package org.zaksen.fancyfishing.util;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.zaksen.fancyfishing.FancyFishing;

public class ChatUtil {

    public static void sendMessage(Player player, String message) {
        sendMessage(player, message, false);
    }

    public static void sendMessage(Player player, String message, boolean actionBar) {
        if(actionBar) {
            player.spigot().sendMessage(
                    ChatMessageType.ACTION_BAR,
                    TextComponent.fromLegacyText(
                            ChatColor.translateAlternateColorCodes('&', message)
                    )
            );
            return;
        }
        player.sendMessage(
                ChatColor.translateAlternateColorCodes('&', message)
        );
    }

    public static void sendMessageC(Player player, String configPath) {
        sendMessageC(player, configPath, false);
    }

    public static void sendMessageC(Player player, String configPath, boolean actionBar) {
        sendMessage(
                player,
                FancyFishing.getMainConfig().getString(configPath),
                actionBar
        );
    }
}