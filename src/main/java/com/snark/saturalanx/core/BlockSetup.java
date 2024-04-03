package com.snark.saturalanx.core;


import com.snark.saturalanx.SaturaLanx;
import com.snark.saturalanx.blocks.building.ColouredBricks;
import com.snark.saturalanx.blocks.building.Gabion;
import com.snark.saturalanx.blocks.traps.FlamingBlock;
import com.snark.saturalanx.blocks.building.MortaredCobble;
import com.snark.saturalanx.blocks.traps.LeafCover;
import com.snark.saturalanx.blocks.traps.SpikeBlock;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;

import static com.snark.saturalanx.SaturaLanx.MODID;

public class BlockSetup {

    public static String BLOCKPATH = "saturalanx:blocks/";
    public static Block colouredBricks, mortaredCobble1, mortaredCobble2;
    public static Block flamingBlock, spikeBlock, leafCover, gabion1, gabion2;

    public BlockSetup() {

    }

    public static void initializeBlocks(){

        SaturaLanx.log.info("Initializing blocks...");
        colouredBricks = new ColouredBricks();

        mortaredCobble1 = new MortaredCobble().setBlockName("MortaredCobble1");
        mortaredCobble2 = new MortaredCobble().setBlockName("MortaredCobble2");
        ((MortaredCobble)mortaredCobble1).setNames(new String[]{"Granite","Diorite","Gabbro","Rhyolite","Basalt","Andesite","Dacite","Shale","Claystone","Rock Salt","Limestone","Sandstone","Dolomite","Chert","Quartzite","Slate"});
        ((MortaredCobble)mortaredCobble2).setNames(new String[]{"Phyllite","Schist","Gneiss","Marble","Chalk"});

        gabion1 = new Gabion().setBlockName("Gabion1");
        ((Gabion)gabion1).setNames(new String[]{"Granite","Diorite","Gabbro","Rhyolite","Basalt","Andesite","Dacite","Shale","Claystone","Rock Salt","Limestone","Sandstone","Dolomite","Chert","Quartzite","Slate"});
        ((Gabion)gabion1).setNum(0);
        gabion2 = new Gabion().setBlockName("Gabion2");
        ((Gabion)gabion2).setNames(new String[]{"Phyllite","Schist","Gneiss","Marble","Chalk"});
        ((Gabion)gabion2).setNum(1);

        flamingBlock = ((Block)new FlamingBlock()).setBlockName("FlamingBlock").setBlockTextureName(MODID+"FlamingBlock");

        spikeBlock = new SpikeBlock();

        leafCover = new LeafCover();
    }

    public static void registerBlocks(){

        SaturaLanx.log.info("Registering blocks...");

        if(Config.enableColouredBricks) {
            GameRegistry.registerBlock(colouredBricks, MetaItemBlock.class, colouredBricks.getUnlocalizedName());
        }

        if(Config.enableMortaredCobble) {
            GameRegistry.registerBlock(mortaredCobble1, MetaItemBlock.class, mortaredCobble1.getUnlocalizedName());
            GameRegistry.registerBlock(mortaredCobble2, MetaItemBlock.class, mortaredCobble2.getUnlocalizedName());
        }

        if(Config.enableIncendiaryPot){
            GameRegistry.registerBlock(flamingBlock, flamingBlock.getUnlocalizedName());
        }

        if(Config.enableSpikes){
            GameRegistry.registerBlock(spikeBlock, MetaItemBlock.class, spikeBlock.getUnlocalizedName());
        }

        if(Config.enableLeafCover){
            GameRegistry.registerBlock(leafCover, MetaItemBlock.class, leafCover.getUnlocalizedName());
        }

        if(Config.enableGabion){
            GameRegistry.registerBlock(gabion1, MetaItemBlock.class, gabion1.getUnlocalizedName());
            GameRegistry.registerBlock(gabion2, MetaItemBlock.class, gabion2.getUnlocalizedName());
        }

    }


}
