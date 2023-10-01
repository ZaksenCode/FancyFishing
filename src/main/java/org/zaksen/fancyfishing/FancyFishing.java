package org.zaksen.fancyfishing;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.zaksen.fancyfishing.commands.FishingXpCMD;
import org.zaksen.fancyfishing.commands.SkillsCMD;
import org.zaksen.fancyfishing.events.FishingEvents;
import org.zaksen.fancyfishing.events.MenuEvents;
import org.zaksen.fancyfishing.events.OtherEvents;
import org.zaksen.fancyfishing.items.Recipes;

public final class FancyFishing extends JavaPlugin {

    private static FancyFishing instance;
    private static FileConfiguration config;

    public static FancyFishing getInstance() {
        return instance;
    }

    public static FileConfiguration getMainConfig() {
        return config;
    }

    @Override
    public void onEnable() {
        instance = this;
        // Save config
        saveDefaultConfig();
        config = getConfig();
        // Plugin startup logic
        Bukkit.getPluginManager().registerEvents(new FishingEvents(), this);
        Bukkit.getPluginManager().registerEvents(new MenuEvents(), this);
        Bukkit.getPluginManager().registerEvents(new OtherEvents(), this);
        // Commands
        getCommand("skills").setExecutor(new SkillsCMD());
        getCommand("skills").setTabCompleter(new SkillsCMD());
        getCommand("fishing").setExecutor(new FishingXpCMD());
        // Initialize recipes
        Recipes.initialize();
        for(Player player : Bukkit.getOnlinePlayers()) {
            Recipes.addRecipesFor(player);
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
