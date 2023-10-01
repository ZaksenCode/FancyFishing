package org.zaksen.fancyfishing.data;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;
import org.zaksen.fancyfishing.FancyFishing;
import org.zaksen.fancyfishing.database.DatabaseManager;
import org.zaksen.fancyfishing.util.ChatUtil;

import java.util.Map;
import java.util.UUID;

public class FishingPlayer {
    private final Player player;
    private int totalXp, level, mobsCaught, itemCaught;
    private boolean firstTime = true;

    public FishingPlayer(Player player, int initXp, int catchMobs, int catchItems) {
        this.player = player;
        this.totalXp = initXp;
        this.mobsCaught = catchMobs;
        this.itemCaught = catchItems;
        recountLevel();
    }

    public FishingPlayer(String uuid, int initXp, int catchMobs, int catchItems) {
        this(Bukkit.getPlayer(UUID.fromString(uuid)), initXp, catchMobs, catchItems);
    }

    public void addXp(int amount) {
        totalXp += amount;
        DatabaseManager.instance.setPlayerXp(player, totalXp);
        recountLevel();
    }

    public void addCatchItem() {
        itemCaught++;
        DatabaseManager.instance.setPlayerData(player, "caughtItem", itemCaught);
    }

    public void addCatchMob() {
        mobsCaught++;
        DatabaseManager.instance.setPlayerData(player, "caughtMobs", mobsCaught);
    }

    private void recountLevel() {
        Map<String, Object> levels = FancyFishing.getMainConfig().getConfigurationSection("levels").getValues(false);
        levels.forEach((cfgLevel, cfgXp) -> {
            if(cfgXp instanceof Integer) {
                int levelXp = (Integer) cfgXp;
                if(totalXp >= levelXp) {
                    int newLevel = Integer.parseInt(cfgLevel);
                    if(level < newLevel) {
                        level = newLevel;
                        if(!firstTime) {
                            FishingPlayer data = DatabaseManager.instance.getPlayerData(player);
                            if(data != null) {
                                DatabaseManager.instance.setPlayerXp(player, data.getTotalXp());
                            }
                            ChatUtil.sendMessage(player, FancyFishing.getMainConfig().getString("messages.level_up").replaceAll("%level%", String.valueOf(level)));
                        }
                    }
                }
            }
        });
        firstTime = false;
    }


    @Nullable
    public String[] getNextLevel() {
        final String[] result = {null, null};
        int nextLevel = level + 1;
        Map<String, Object> levels = FancyFishing.getMainConfig().getConfigurationSection("levels").getValues(false);
        for(Map.Entry<String, Object> level : levels.entrySet()) {
            if(level.getValue() instanceof Integer) {
                int levelXp = (Integer) level.getValue();
                int newLevel = Integer.parseInt(level.getKey());
                if(nextLevel == newLevel) {
                    result[0] = String.valueOf(nextLevel);
                    result[1] = String.valueOf(levelXp);
                } else if(this.level == newLevel) {
                    result[0] = String.valueOf(this.level);
                    result[1] = String.valueOf(levelXp);
                }
            }
        }
        return result;
    }

    public Player getPlayer() {
        return player;
    }

    public String getUuid() {
        return player.getUniqueId().toString();
    }

    public int getTotalXp() {
        return totalXp;
    }

    public int getLevel() {
        return level;
    }

    public int getCaught() {
        return mobsCaught + itemCaught;
    }

    public int getMobsCaught() {
        return mobsCaught;
    }

    public int getItemCaught() {
        return itemCaught;
    }
}