package org.zaksen.fancyfishing.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.zaksen.fancyfishing.data.FishingPlayer;
import org.zaksen.fancyfishing.database.DatabaseManager;

public class FishingXpCMD implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(sender instanceof Player && sender.isOp() && args.length >= 2) {
            FishingPlayer player = DatabaseManager.instance.getPlayerData((Player) sender);
            if(player == null) {
                DatabaseManager.instance.addNewPlayer((Player) sender);
                player = DatabaseManager.instance.getPlayerData((Player) sender);
            }
            String subcommand = args[0];
            int xp = Integer.parseInt(args[1]);
            switch (subcommand) {
                case "add": {
                    player.addXp(xp);
                    break;
                }
                case "remove": {
                    player.addXp(-xp);
                    break;
                }
            }
        }
        return true;
    }
}
