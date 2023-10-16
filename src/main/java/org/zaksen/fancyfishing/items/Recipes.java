package org.zaksen.fancyfishing.items;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.zaksen.fancyfishing.FancyFishing;

import java.util.ArrayList;
import java.util.Collection;

public class Recipes {

    static Collection<NamespacedKey> recipes = new ArrayList<>();

    public static void initialize() {
        // Strings
        // Iron string
        NamespacedKey ironStringKey = new NamespacedKey(FancyFishing.getInstance(), "iron_string");
        ShapedRecipe ironStringRecipe = new ShapedRecipe(ironStringKey, Strings.IRON_STRING.getString());
        ironStringRecipe.shape(
                "SIS",
                "ISI",
                "SIS"
        );
        ironStringRecipe.setIngredient('S', Material.STRING);
        ironStringRecipe.setIngredient('I', Material.IRON_INGOT);
        Bukkit.addRecipe(ironStringRecipe);
        recipes.add(ironStringKey);

        // Gold string
        NamespacedKey goldStringKey = new NamespacedKey(FancyFishing.getInstance(), "gold_string");
        ShapedRecipe goldStringRecipe = new ShapedRecipe(goldStringKey, Strings.GOLD_STRING.getString());
        goldStringRecipe.shape(
                "SIS",
                "ISI",
                "SIS"
        );
        goldStringRecipe.setIngredient('S', Material.STRING);
        goldStringRecipe.setIngredient('I', Material.GOLD_INGOT);
        Bukkit.addRecipe(goldStringRecipe);
        recipes.add(goldStringKey);

        // Diamond string
        NamespacedKey diamondStringKey = new NamespacedKey(FancyFishing.getInstance(), "diamond_string");
        ShapedRecipe diamondStringRecipe = new ShapedRecipe(diamondStringKey, Strings.DIAMOND_STRING.getString());
        diamondStringRecipe.shape(
                "SDS",
                "DSD",
                "SDS"
        );
        diamondStringRecipe.setIngredient('S', Material.STRING);
        diamondStringRecipe.setIngredient('D', Material.DIAMOND);
        Bukkit.addRecipe(diamondStringRecipe);
        recipes.add(diamondStringKey);

        // Netherite string
        NamespacedKey netheriteStringKey = new NamespacedKey(FancyFishing.getInstance(), "netherite_string");
        ShapedRecipe netheriteStringRecipe = new ShapedRecipe(netheriteStringKey, Strings.NETHERITE_STRING.getString());
        netheriteStringRecipe.shape(
                " S ",
                "SNS",
                " S "
        );
        netheriteStringRecipe.setIngredient('S', Material.STRING);
        netheriteStringRecipe.setIngredient('N', Material.NETHERITE_INGOT);
        Bukkit.addRecipe(netheriteStringRecipe);
        recipes.add(netheriteStringKey);

        // Rods
        // Iron rod
        NamespacedKey ironRodKey = new NamespacedKey(FancyFishing.getInstance(), "iron_rod");
        ShapedRecipe ironRodRecipe = new ShapedRecipe(ironRodKey, FishingRods.IRON_ROD.getRod());
        ironRodRecipe.shape(
                "  I",
                " IN",
                "I N"
        );
        ironRodRecipe.setIngredient('I', Material.IRON_INGOT);
        ironRodRecipe.setIngredient('N', new RecipeChoice.ExactChoice(Strings.IRON_STRING.getString()));
        Bukkit.addRecipe(ironRodRecipe);
        recipes.add(ironRodKey);
        
        // Diamond rod
        NamespacedKey diamondRodKey = new NamespacedKey(FancyFishing.getInstance(), "advanced_rod");
        ShapedRecipe diamondRodRecipe = new ShapedRecipe(diamondRodKey, FishingRods.DIAMOND_ROD.getRod());
        diamondRodRecipe.shape(
                "  D",
                " DN",
                "D N"
        );
        diamondRodRecipe.setIngredient('D', Material.DIAMOND);
        diamondRodRecipe.setIngredient('N', new RecipeChoice.ExactChoice(Strings.DIAMOND_STRING.getString()));
        Bukkit.addRecipe(diamondRodRecipe);
        recipes.add(diamondRodKey);

        // Fancy rod
        NamespacedKey fancyRodKey = new NamespacedKey(FancyFishing.getInstance(), "fancy_rod");
        ShapedRecipe fancyRodRecipe = new ShapedRecipe(fancyRodKey, FishingRods.FANCY_ROD.getRod());
        fancyRodRecipe.shape(
                "  B",
                " SN",
                "S N"
        );
        fancyRodRecipe.setIngredient('S', Material.STICK);
        fancyRodRecipe.setIngredient('B', Material.SLIME_BALL);
        fancyRodRecipe.setIngredient('N', Material.STRING);
        Bukkit.addRecipe(fancyRodRecipe);
        recipes.add(fancyRodKey);

        // Experience rod
        NamespacedKey experienceRodKey = new NamespacedKey(FancyFishing.getInstance(), "experience_rod");
        ShapedRecipe experienceRodRecipe = new ShapedRecipe(experienceRodKey, FishingRods.EXPERIENCE_ROD.getRod());
        experienceRodRecipe.shape(
                "  E",
                " SN",
                "S N"
        );
        experienceRodRecipe.setIngredient('S', Material.STICK);
        experienceRodRecipe.setIngredient('E', Material.EXPERIENCE_BOTTLE);
        experienceRodRecipe.setIngredient('N', Material.STRING);
        Bukkit.addRecipe(experienceRodRecipe);
        recipes.add(experienceRodKey);

        // Realistic rod
        NamespacedKey realisticRodKey = new NamespacedKey(FancyFishing.getInstance(), "realistic_rod");
        ShapedRecipe realisticRodRecipe = new ShapedRecipe(realisticRodKey, FishingRods.REALISTIC_ROD.getRod());
        realisticRodRecipe.shape(
                "  R",
                " IN",
                "B N"
        );
        realisticRodRecipe.setIngredient('B', Material.BLUE_DYE);
        realisticRodRecipe.setIngredient('I', Material.IRON_INGOT);
        realisticRodRecipe.setIngredient('R', Material.RED_DYE);
        realisticRodRecipe.setIngredient('N', Material.STRING);
        Bukkit.addRecipe(realisticRodRecipe);
        recipes.add(realisticRodKey);

        // Pufferfish rod
        NamespacedKey pufferfishRodKey = new NamespacedKey(FancyFishing.getInstance(), "pufferfish_rod");
        ShapedRecipe pufferfishRodRecipe = new ShapedRecipe(pufferfishRodKey, FishingRods.PUFFERFISH_ROD.getRod());
        pufferfishRodRecipe.shape(
                "  P",
                " PN",
                "P N"
        );
        pufferfishRodRecipe.setIngredient('P', Material.PUFFERFISH);
        pufferfishRodRecipe.setIngredient('N', Material.STRING);
        Bukkit.addRecipe(pufferfishRodRecipe);
        recipes.add(pufferfishRodKey);

        // Nether rod
        NamespacedKey netherRodKey = new NamespacedKey(FancyFishing.getInstance(), "nether_rod");
        ShapedRecipe netherRodRecipe = new ShapedRecipe(netherRodKey, FishingRods.NETHER_ROD.getRod());
        netherRodRecipe.shape(
                "  B",
                " RN",
                "B N"
        );
        netherRodRecipe.setIngredient('R', Material.BLAZE_ROD);
        netherRodRecipe.setIngredient('B', Material.NETHER_BRICK);
        netherRodRecipe.setIngredient('N', new RecipeChoice.ExactChoice(Strings.IRON_STRING.getString()));
        Bukkit.addRecipe(netherRodRecipe);
        recipes.add(netherRodKey);

        // Mining rod
        NamespacedKey miningRodKey = new NamespacedKey(FancyFishing.getInstance(), "mining_rod");
        ShapedRecipe miningRodRecipe = new ShapedRecipe(miningRodKey, FishingRods.MINING_ROD.getRod());
        miningRodRecipe.shape(
                "  C",
                " SN",
                "I N"
        );
        miningRodRecipe.setIngredient('I', Material.IRON_ORE);
        miningRodRecipe.setIngredient('S', Material.STONE);
        miningRodRecipe.setIngredient('C', Material.COAL_ORE);
        miningRodRecipe.setIngredient('N', new RecipeChoice.ExactChoice(Strings.IRON_STRING.getString()));
        Bukkit.addRecipe(miningRodRecipe);
        recipes.add(miningRodKey);

        // Pirate rod
        NamespacedKey pirateRodKey = new NamespacedKey(FancyFishing.getInstance(), "pirate_rod");
        ShapedRecipe pirateRodRecipe = new ShapedRecipe(pirateRodKey, FishingRods.PIRATE_ROD.getRod());
        pirateRodRecipe.shape(
                "  I",
                " GN",
                "F N"
        );
        pirateRodRecipe.setIngredient('F', Material.FLINT);
        pirateRodRecipe.setIngredient('G', Material.GOLD_BLOCK);
        pirateRodRecipe.setIngredient('I', Material.GOLD_INGOT);
        pirateRodRecipe.setIngredient('N', new RecipeChoice.ExactChoice(Strings.GOLD_STRING.getString()));
        Bukkit.addRecipe(pirateRodRecipe);
        recipes.add(pirateRodKey);

        // Meat rod
        NamespacedKey meatRodKey = new NamespacedKey(FancyFishing.getInstance(), "meat_rod");
        ShapedRecipe meatRodRecipe = new ShapedRecipe(meatRodKey, FishingRods.MEAT_ROD.getRod());
        meatRodRecipe.shape(
                "  C",
                " SN",
                "P N"
        );
        meatRodRecipe.setIngredient('P', Material.BEEF);
        meatRodRecipe.setIngredient('S', Material.MUTTON);
        meatRodRecipe.setIngredient('C', Material.PORKCHOP);
        meatRodRecipe.setIngredient('N', new RecipeChoice.ExactChoice(Strings.IRON_STRING.getString()));
        Bukkit.addRecipe(meatRodRecipe);
        recipes.add(meatRodKey);

        // Grass rod
        NamespacedKey grassRodKey = new NamespacedKey(FancyFishing.getInstance(), "grass_rod");
        ShapedRecipe grassRodRecipe = new ShapedRecipe(grassRodKey, FishingRods.GRASS_ROD.getRod());
        grassRodRecipe.shape(
                "  G",
                " DN",
                "D N"
        );
        grassRodRecipe.setIngredient('D', Material.DIRT);
        grassRodRecipe.setIngredient('G', Material.GRASS_BLOCK);
        grassRodRecipe.setIngredient('N', new RecipeChoice.ExactChoice(Strings.IRON_STRING.getString()));
        Bukkit.addRecipe(grassRodRecipe);
        recipes.add(grassRodKey);

        // Axe rod
        NamespacedKey axeRodKey = new NamespacedKey(FancyFishing.getInstance(), "axe_rod");
        ShapedRecipe axeRodRecipe = new ShapedRecipe(axeRodKey, FishingRods.AXE_ROD.getRod());
        axeRodRecipe.shape(
                "  A",
                " SN",
                "S N"
        );
        axeRodRecipe.setIngredient('A', Material.IRON_AXE);
        axeRodRecipe.setIngredient('S', Material.STICK);
        axeRodRecipe.setIngredient('N', new RecipeChoice.ExactChoice(Strings.IRON_STRING.getString()));
        Bukkit.addRecipe(axeRodRecipe);
        recipes.add(axeRodKey);
    }

    public static void addRecipesFor(Player player) {
        player.discoverRecipes(recipes);
    }
}