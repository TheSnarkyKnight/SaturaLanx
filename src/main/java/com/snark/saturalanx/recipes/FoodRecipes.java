package com.snark.saturalanx.recipes;

import com.dunk.tfc.Core.Recipes;
import com.dunk.tfc.Food.ItemFoodTFC;
import com.dunk.tfc.api.TFCBlocks;
import com.snark.saturalanx.SaturaLanx;
import com.snark.saturalanx.core.FoodSetup;
import com.snark.saturalanx.core.Config;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class FoodRecipes {

    public FoodRecipes(){

    }

    public static void registerFoodRecipes(){

        SaturaLanx.log.info("Registering food recipes...");

        if(Config.enableDandelionFood)
            GameRegistry.addRecipe(new ShapelessOreRecipe(ItemFoodTFC.createTag(new ItemStack(FoodSetup.dandelionTop,1), 4.0F), TFCBlocks.flowers, "itemKnife"));
        if(Config.enableGoldenrodFood)
            GameRegistry.addRecipe(new ShapelessOreRecipe(ItemFoodTFC.createTag(new ItemStack(FoodSetup.goldenrodLeaves,1), 4.0F), TFCBlocks.flora, "itemKnife"));
        if(Config.enableMilkweedFood)
            for(int i = 2; i<=4;i++)
                GameRegistry.addRecipe(new ShapelessOreRecipe(ItemFoodTFC.createTag(new ItemStack(FoodSetup.milkweedPod,1), 8.0F), new ItemStack(TFCBlocks.flowers,1,i), "itemKnife"));
        if(Config.enablePoppyFood)
            GameRegistry.addRecipe(new ShapelessOreRecipe(ItemFoodTFC.createTag(new ItemStack(FoodSetup.poppyseed,1), 4.0F), TFCBlocks.flowers2, "itemKnife"));
        if(Config.enableDaisyFood)
            GameRegistry.addRecipe(new ShapelessOreRecipe(ItemFoodTFC.createTag(new ItemStack(FoodSetup.daisyFlower,1), 4.0F), new ItemStack(TFCBlocks.flowers2,1,8), "itemKnife"));
        if(Config.enableNasturtiumFood)
            GameRegistry.addRecipe(new ShapelessOreRecipe(ItemFoodTFC.createTag(new ItemStack(FoodSetup.preparedNasturtium,1), 8.0F), new ItemStack(TFCBlocks.flowers,1,1), "itemKnife"));
        if(Config.enableCalendulaFood)
            GameRegistry.addRecipe(new ShapelessOreRecipe(ItemFoodTFC.createTag(new ItemStack(FoodSetup.calendulaPetals,1), 4.0F), new ItemStack(TFCBlocks.flowers,1,5), "itemKnife"));
        if(Config.enableTulipFood){
            GameRegistry.addRecipe(new ShapelessOreRecipe(ItemFoodTFC.createTag(new ItemStack(FoodSetup.tulipPetalsRed,1), 4.0F), new ItemStack(TFCBlocks.flowers2,1,4), "itemKnife"));
            GameRegistry.addRecipe(new ShapelessOreRecipe(ItemFoodTFC.createTag(new ItemStack(FoodSetup.tulipPetalsOrange,1), 4.0F), new ItemStack(TFCBlocks.flowers2,1,5), "itemKnife"));
            GameRegistry.addRecipe(new ShapelessOreRecipe(ItemFoodTFC.createTag(new ItemStack(FoodSetup.tulipPetalsWhite,1), 4.0F), new ItemStack(TFCBlocks.flowers2,1,6), "itemKnife"));
            GameRegistry.addRecipe(new ShapelessOreRecipe(ItemFoodTFC.createTag(new ItemStack(FoodSetup.tulipPetalsPink,1), 4.0F), new ItemStack(TFCBlocks.flowers2,1,7), "itemKnife"));

        }
    }
}
