package com.snark.saturalanx.recipes;

import com.dunk.tfc.api.Crafting.CraftingManagerTFC;
import com.dunk.tfc.api.TFCItems;
import com.snark.saturalanx.SaturaLanx;
import com.snark.saturalanx.core.ItemSetup;
import net.minecraft.item.ItemStack;

public class KnappingRecipes {
    public KnappingRecipes(){

    }

    public static void registerKnapping(){

        SaturaLanx.log.info("Registering knapping recipes...");

        //for(int i = 0;i<TFCItems.flatRock.getMaxDamage();i++)
            CraftingManagerTFC.getInstance().addRecipe(new ItemStack(ItemSetup.bulletSL,8,0), new Object[]{"## ##", "## ##", "     ", "## ##", "## ##",'#', new ItemStack(TFCItems.flatRock, 1, 32767)});

        CraftingManagerTFC.getInstance().addRecipe(new ItemStack(ItemSetup.floorTile, 4, 0), new Object[]{"  X  ", "  X  ", "XXXXX", "  X  ", "  X  ",'X', new ItemStack(TFCItems.flatClay, 1, 1)});

        SaturaLanx.log.info("Done registering knapping recipes.");
    }
}
