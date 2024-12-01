package com.snark.saturalanx;

import com.snark.saturalanx.core.BlockSetup;
import com.snark.saturalanx.core.EntitySetup;
import com.snark.saturalanx.core.FoodSetup;
import com.snark.saturalanx.core.ItemSetup;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ClientProxy extends CommonProxy{

    @Override
    @SideOnly(Side.CLIENT)
    public void preInit(FMLPreInitializationEvent event) {

        super.preInit(event);

    }

    @Override
    @SideOnly(Side.CLIENT)
    public void init(FMLInitializationEvent event) {

        super.init(event);

    }

    @Override
    @SideOnly(Side.CLIENT)
    public void postInit(FMLPostInitializationEvent event) {

        super.postInit(event);
        FoodSetup.registerRenders();
        ItemSetup.registerRenders();
        EntitySetup.registerEntityRenders();
        BlockSetup.registerBlockRenders();
        
    }

}
