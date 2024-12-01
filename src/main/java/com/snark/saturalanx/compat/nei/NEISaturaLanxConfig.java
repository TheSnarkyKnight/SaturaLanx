package com.snark.saturalanx.compat.nei;

import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;
import com.snark.saturalanx.SaturaLanx;
import com.snark.saturalanx.blocks.building.TilesBlock;
import com.snark.saturalanx.core.BlockSetup;
import com.snark.saturalanx.core.Config;
import com.snark.saturalanx.core.ItemSetup;
import com.snark.saturalanx.items.ItemSatura;
import cpw.mods.fml.common.Optional;
import net.minecraft.item.ItemStack;


@Optional.Interface(iface = "codechicken.nei.api.API", modid = "NotEnoughItems")
public class NEISaturaLanxConfig implements IConfigureNEI {

    @Optional.Method(modid="NotEnoughItems")
    @Override
    public String getName() {
        return SaturaLanx.MODNAME;
    }

    @Optional.Method(modid="NotEnoughItems")
    @Override
    public String getVersion() {
        return SaturaLanx.VERSION;
    }

    @Optional.Method(modid="NotEnoughItems")
    @Override
    public void loadConfig() {
        if(Config.enableIncendiaryPot)
            API.hideItem(new ItemStack(BlockSetup.flamingBlock));
        if(Config.enableHandgonne||Config.enableArquebus||Config.enableMBlunderbuss) {
            for(int i = 0; i < ((ItemSatura) ItemSetup.dummyBullet).getIcons().length; i++)
                API.hideItem(new ItemStack(ItemSetup.dummyBullet, 1, i));
        }
        if(Config.enableSpikes) {
            API.hideItem(new ItemStack(BlockSetup.spikeBlock, 1, 0));
            API.hideItem(new ItemStack(BlockSetup.spikeBlock, 1, 1));
        }
        if(Config.enableExtraMaces){
            API.hideItem(new ItemStack(ItemSetup.saturaMaceMold,1,0));
        }
        if(Config.enableFloorTiles){
            for(int i = 0;i < ((TilesBlock)BlockSetup.floorTiles).getNames().length;i++)
             API.hideItem(new ItemStack(BlockSetup.floorTiles,1,i));
        }
    }
}

