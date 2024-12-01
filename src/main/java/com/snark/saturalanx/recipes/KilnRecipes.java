package com.snark.saturalanx.recipes;

import com.dunk.tfc.api.Crafting.KilnCraftingManager;
import com.dunk.tfc.api.Crafting.KilnRecipe;
import com.snark.saturalanx.SaturaLanx;
import com.snark.saturalanx.core.Config;
import net.minecraft.item.ItemStack;

import static com.snark.saturalanx.core.ItemSetup.*;

public class KilnRecipes {

    public KilnRecipes(){

    }

    public static void registerRecipes(){

        SaturaLanx.log.info("Registering kiln recipes...");

        KilnCraftingManager km = KilnCraftingManager.getInstance();

        if(Config.enableColouredBricks) {
            km.addRecipe((new KilnRecipe(new ItemStack(lapisBrick, 1, 0), 0, new ItemStack(lapisBrick, 1, 1))));
            km.addRecipe((new KilnRecipe(new ItemStack(hematiteBrick, 1, 0), 0, new ItemStack(hematiteBrick, 1, 1))));
            km.addRecipe((new KilnRecipe(new ItemStack(limoniteBrick, 1, 0), 0, new ItemStack(limoniteBrick, 1, 1))));
            km.addRecipe((new KilnRecipe(new ItemStack(malachiteBrick, 1, 0), 0, new ItemStack(malachiteBrick, 1, 1))));
        }
        if(Config.enableFloorTiles){
            km.addRecipe((new KilnRecipe(new ItemStack(floorTile, 1, 0), 0, new ItemStack(floorTile, 1, 1))));
            km.addRecipe((new KilnRecipe(new ItemStack(floorTileLapis, 1, 0), 0, new ItemStack(floorTileLapis, 1, 1))));
            km.addRecipe((new KilnRecipe(new ItemStack(floorTileHematite, 1, 0), 0, new ItemStack(floorTileHematite, 1, 1))));
            km.addRecipe((new KilnRecipe(new ItemStack(floorTileLimonite, 1, 0), 0, new ItemStack(floorTileLimonite, 1, 1))));
            km.addRecipe((new KilnRecipe(new ItemStack(floorTileMalachite, 1, 0), 0, new ItemStack(floorTileMalachite, 1, 1))));
        }

    }
}
