package com.snark.saturalanx.core;


import com.snark.saturalanx.SaturaLanx;
import com.snark.saturalanx.blocks.building.*;
import com.snark.saturalanx.blocks.decoration.TallCandlestickBlock;
import com.snark.saturalanx.blocks.decoration.TallCandlestickOffBlock;
import com.snark.saturalanx.blocks.functional.FlamingBlock;
import com.snark.saturalanx.blocks.functional.LeafCover;
import com.snark.saturalanx.blocks.functional.SpikeBlock;
import com.snark.saturalanx.renders.blocks.StockadeRender;
import com.snark.saturalanx.renders.blocks.TallCandlestickRender;
import com.snark.saturalanx.renders.blocks.TileBlockRender;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;

import java.util.ArrayList;

public class BlockSetup {

    public static String BLOCKPATH = "saturalanx:blocks/";
    public static int tileRenderId,tallCandlestickRenderId, stockadeRenderId;
    public static ArrayList<Block> blockList;
    public static Block colouredBricks, floorTiles, mortaredCobble1, mortaredCobble2;
    public static Block brassTallCandlestickOff,brassTallCandlestick,pewterTallCandlestick,pewterTallCandlestickOff,silverTallCandlestick,silverTallCandlestickOff,sterlingSilverTallCandlestick,sterlingSilverTallCandlestickOff,goldTallCandlestick,goldTallCandlestickOff,roseGoldTallCandlestick,roseGoldTallCandlestickOff;
    public static Block flamingBlock;
    public static Block spikeBlock, leafCover;
    public static Block gabion1, gabion2, stockadeBlock;

    public BlockSetup() {

    }

    public static void initializeBlocks(){

        SaturaLanx.log.info("Initializing blocks...");

        blockList = new ArrayList<Block>();

        if(Config.enableColouredBricks) {
            colouredBricks = new ColouredBricks();
            blockList.add(colouredBricks);
        }

        if(Config.enableMortaredCobble)
        {
            mortaredCobble1 = new MortaredCobble().setBlockName("MortaredCobble1");
            mortaredCobble2 = new MortaredCobble().setBlockName("MortaredCobble2");
            ((MortaredCobble) mortaredCobble1).setNames(new String[]{"Granite", "Diorite", "Gabbro", "Rhyolite", "Basalt", "Andesite", "Dacite", "Shale", "Claystone", "Rock Salt", "Limestone", "Sandstone", "Dolomite", "Chert", "Quartzite", "Slate"});
            ((MortaredCobble) mortaredCobble2).setNames(new String[]{"Phyllite", "Schist", "Gneiss", "Marble", "Chalk"});

            blockList.add(mortaredCobble1);
            blockList.add(mortaredCobble2);
        }

        if(Config.enableFloorTiles) {
            floorTiles = new TilesBlock().setNames(new String[]{"CeramicTiles", "CeramicTilesLapis", "CeramicTilesHematite", "CeramicTilesLimonite", "CeramicTilesMalachite"}).setBlockName("FloorTiles");

            blockList.add(floorTiles);
        }

        if(Config.enableTallCandlestick){
            float b = Config.tallCandlestickBurnModifier;
            float l = Config.tallCandlestickLightModifier;

            brassTallCandlestick = (new TallCandlestickBlock(brassTallCandlestickOff)).setBurnModifier(1.25F*b).setLightLevel(0.8F*l).setBlockName("BrassTallCandlestick");
            brassTallCandlestickOff = new TallCandlestickOffBlock(brassTallCandlestick).setBlockName("BrassTallCandlestickOff");
            pewterTallCandlestick = (new TallCandlestickBlock(pewterTallCandlestickOff)).setBurnModifier(1.125F*b).setLightLevel(0.8F*l).setBlockName("PewterTallCandlestick");
            pewterTallCandlestickOff = new TallCandlestickOffBlock(pewterTallCandlestick).setBlockName("PewterTallCandlestickOff");
            silverTallCandlestick = (new TallCandlestickBlock(silverTallCandlestickOff)).setBurnModifier(1.75F*b).setLightLevel(Math.min(0.9333F*l,1)).setBlockName("SilverTallCandlestick");
            silverTallCandlestickOff = new TallCandlestickOffBlock(silverTallCandlestick).setBlockName("SilverTallCandlestickOff");
            sterlingSilverTallCandlestick = (new TallCandlestickBlock(sterlingSilverTallCandlestickOff)).setBurnModifier(1.5F*b).setLightLevel(Math.min(0.86667F*l,1)).setBlockName("SterlingSilverTallCandlestick");
            sterlingSilverTallCandlestickOff = new TallCandlestickOffBlock(sterlingSilverTallCandlestick).setBlockName("SterlingSilverTallCandlestickOff");
            goldTallCandlestick = (new TallCandlestickBlock(goldTallCandlestickOff)).setBurnModifier(3.0F*b).setLightLevel(1).setBlockName("GoldTallCandlestick");
            goldTallCandlestickOff = new TallCandlestickOffBlock(goldTallCandlestick).setBlockName("GoldTallCandlestickOff");
            roseGoldTallCandlestick = (new TallCandlestickBlock(roseGoldTallCandlestickOff)).setBurnModifier(2.0F*b).setLightLevel(Math.min(0.9333F*l,1)).setBlockName("RoseGoldTallCandlestick");
            roseGoldTallCandlestickOff = new TallCandlestickOffBlock(roseGoldTallCandlestick).setBlockName("RoseGoldTallCandlestickOff");

            ((TallCandlestickBlock)brassTallCandlestick).setAlternate(brassTallCandlestickOff);
            ((TallCandlestickBlock)pewterTallCandlestick).setAlternate(pewterTallCandlestickOff);
            ((TallCandlestickBlock)silverTallCandlestick).setAlternate(silverTallCandlestickOff);
            ((TallCandlestickBlock)sterlingSilverTallCandlestick).setAlternate(sterlingSilverTallCandlestickOff);
            ((TallCandlestickBlock)goldTallCandlestick).setAlternate(goldTallCandlestickOff);
            ((TallCandlestickBlock)roseGoldTallCandlestick).setAlternate(roseGoldTallCandlestickOff);

            blockList.add(brassTallCandlestick);
            blockList.add(brassTallCandlestickOff);
            blockList.add(pewterTallCandlestick);
            blockList.add(pewterTallCandlestickOff);
            blockList.add(silverTallCandlestick);
            blockList.add(silverTallCandlestickOff);
            blockList.add(sterlingSilverTallCandlestick);
            blockList.add(sterlingSilverTallCandlestickOff);
            blockList.add(goldTallCandlestick);
            blockList.add(goldTallCandlestickOff);
            blockList.add(roseGoldTallCandlestick);
            blockList.add(roseGoldTallCandlestickOff);
        }

        if(Config.enableGabion) {
            gabion1 = new Gabion().setBlockName("Gabion1");
            ((Gabion) gabion1).setNames(new String[]{"Granite", "Diorite", "Gabbro", "Rhyolite", "Basalt", "Andesite", "Dacite", "Shale", "Claystone", "Rock Salt", "Limestone", "Sandstone", "Dolomite", "Chert", "Quartzite", "Slate"});
            ((Gabion) gabion1).setNum(0);
            gabion2 = new Gabion().setBlockName("Gabion2");
            ((Gabion) gabion2).setNames(new String[]{"Phyllite", "Schist", "Gneiss", "Marble", "Chalk"});
            ((Gabion) gabion2).setNum(1);

            blockList.add(gabion1);
            blockList.add(gabion2);
        }

        if(Config.enableStockade){
            stockadeBlock = new StockadeBlock();

            blockList.add(stockadeBlock);
        }

        if(Config.enableIncendiaryPot)
        {
            flamingBlock = ((Block) new FlamingBlock()).setBlockName("FlamingBlock");

            blockList.add(flamingBlock);
        }

        if(Config.enableSpikes) {
            spikeBlock = new SpikeBlock();

            blockList.add(spikeBlock);
        }

        if(Config.enableLeafCover) {
            leafCover = new LeafCover();

            blockList.add(leafCover);
        }
    }

    public static void registerBlocks(){

        SaturaLanx.log.info("Registering blocks...");

        for(Block b : blockList){
            GameRegistry.registerBlock(b, MetaItemBlock.class, b.getUnlocalizedName());
        }


    }

    @SideOnly(Side.CLIENT)
    public static void registerBlockRenders(){
        RenderingRegistry.registerBlockHandler(tileRenderId = RenderingRegistry.getNextAvailableRenderId(), new TileBlockRender());
        RenderingRegistry.registerBlockHandler(tallCandlestickRenderId = RenderingRegistry.getNextAvailableRenderId(), new TallCandlestickRender());
        RenderingRegistry.registerBlockHandler(stockadeRenderId = RenderingRegistry.getNextAvailableRenderId(), new StockadeRender());
    }


}
