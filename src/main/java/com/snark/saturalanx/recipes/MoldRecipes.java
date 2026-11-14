package com.snark.saturalanx.recipes;

import com.dunk.tfc.Items.Pottery.ItemPotteryMoldBase;
import com.dunk.tfc.api.Constant.Global;
import com.dunk.tfc.api.Crafting.CraftingManagerTFC;
import com.dunk.tfc.api.Metal;
import com.dunk.tfc.api.TFCItems;
import com.snark.saturalanx.SaturaLanx;
import com.snark.saturalanx.core.ItemSetup;
import com.snark.saturalanx.core.Config;
import net.minecraft.item.ItemStack;

import static com.dunk.tfc.Core.Recipes.getStackTemp;
import static com.dunk.tfc.api.TFCItems.clayMoldMace;

public class MoldRecipes {

    public MoldRecipes(){

    }

    public static void registerPartialMolds(){

        SaturaLanx.log.info("Registering casting recipes...");

        if(Config.enableExtraMaces){

            Global.LEAD.addValidPartialMold(ItemSetup.saturaMaceMold, 2, ItemSetup.saturaMaceMold, 2, 2);
            Global.BRASS.addValidPartialMold(ItemSetup.saturaMaceMold, 3, ItemSetup.saturaMaceMold, 2, 2);

            Global.LEAD.addValidPartialMold(ItemSetup.saturaBulletMold,2,ItemSetup.saturaBulletMold,1,2);
            Global.SILVER.addValidPartialMold(ItemSetup.saturaBulletMold,3,ItemSetup.saturaBulletMold,1,3);
            Global.GOLD.addValidPartialMold(ItemSetup.saturaBulletMold,4,ItemSetup.saturaBulletMold,1,4);

        }

    }
}
