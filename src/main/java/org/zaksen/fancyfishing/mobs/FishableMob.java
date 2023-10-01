package org.zaksen.fancyfishing.mobs;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.zaksen.fancyfishing.FancyFishing;

public class FishableMob {

    public static Entity getEntity(Location location, int level, String name) {
        return setupEntity(location, level, name);
    }

    private static Entity setupEntity(Location location, int level, String configPath) {
        Entity entity = location.getWorld().spawnEntity(location, EntityType.valueOf(configPath.toUpperCase()));
        entity.setCustomNameVisible(true);
        entity.setCustomName(FancyFishing.getMainConfig().getString("mobs." + level + "." + configPath + ".name"));
        ((LivingEntity) entity).setHealth(FancyFishing.getMainConfig().getInt("mobs." + level + "." + configPath + ".health"));
        return entity;
    }
}