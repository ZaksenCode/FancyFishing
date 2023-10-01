package org.zaksen.fancyfishing.config;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.zaksen.fancyfishing.FancyFishing;

import java.io.File;
import java.io.IOException;

public class CustomConfig {
    private final String name;
    private FileConfiguration customConfig;

    public CustomConfig(String name) {
        this.name = name;
    }

    public FileConfiguration getCustomConfig() {
        return this.customConfig;
    }

    private void createConfig() {
        File customConfigFile = new File(FancyFishing.getInstance().getDataFolder(), name + ".yml");
        if (!customConfigFile.exists()) {
            customConfigFile.getParentFile().mkdirs();
            FancyFishing.getInstance().saveResource(name + ".yml", false);
        }

        customConfig = new YamlConfiguration();
        try {
            customConfig.load(customConfigFile);
        } catch (IOException | InvalidConfigurationException e) {
            FancyFishing.getInstance().getLogger().warning("Can't create config '" + name + "': " + e);
        }
    }
}