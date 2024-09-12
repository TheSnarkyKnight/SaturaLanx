package com.snark.saturalanx.recipes;

import com.dunk.tfc.api.TFCBlocks;
import com.dunk.tfc.api.TFCItems;
import com.snark.saturalanx.SaturaLanx;
import com.snark.saturalanx.core.Config;
import com.snark.saturalanx.core.ItemSetup;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import java.util.ArrayList;
import java.util.List;

import static com.snark.saturalanx.core.BlockSetup.*;
import static com.snark.saturalanx.core.ItemSetup.*;

public class CraftingRecipes {

    public CraftingRecipes(){

    }

    public static void registerRecipes() {

        SaturaLanx.log.info("Registering crafting recipes...");

        ArrayList<Item> string = new ArrayList<Item>();
        string.add(TFCItems.linenString);
        string.add(TFCItems.silkString);
        string.add(TFCItems.woolYarn);
        string.add(TFCItems.cottonYarn);
        string.add(TFCItems.sinew);
        string.add(TFCItems.grassCordage);

        if (Config.enableColouredBricks) {
            GameRegistry.addRecipe(new ItemStack(lapisBrick, 8, 0), "XXX", "XYX", "XXX", 'X', new ItemStack(TFCItems.brick, 1, 0), 'Y', new ItemStack(TFCItems.powder, 1, 6));
            GameRegistry.addRecipe(new ItemStack(hematiteBrick, 8, 0), "XXX", "XYX", "XXX", 'X', new ItemStack(TFCItems.brick, 1, 0), 'Y', new ItemStack(TFCItems.powder, 1, 5));
            GameRegistry.addRecipe(new ItemStack(limoniteBrick, 8, 0), "XXX", "XYX", "XXX", 'X', new ItemStack(TFCItems.brick, 1, 0), 'Y', new ItemStack(TFCItems.powder, 1, 7));
            GameRegistry.addRecipe(new ItemStack(malachiteBrick, 8, 0), "XXX", "XYX", "XXX", 'X', new ItemStack(TFCItems.brick, 1, 0), 'Y', new ItemStack(TFCItems.powder, 1, 8));

            GameRegistry.addRecipe(new ItemStack(colouredBricks, 8, 1), "PXP", "XPX", "PXP", 'P', new ItemStack(lapisBrick, 1, 1), 'X', new ItemStack(TFCItems.mortar, 1));
            GameRegistry.addRecipe(new ItemStack(colouredBricks, 8, 0), "PXP", "XPX", "PXP", 'P', new ItemStack(hematiteBrick, 1, 1), 'X', new ItemStack(TFCItems.mortar, 1));
            GameRegistry.addRecipe(new ItemStack(colouredBricks, 8, 2), "PXP", "XPX", "PXP", 'P', new ItemStack(limoniteBrick, 1, 1), 'X', new ItemStack(TFCItems.mortar, 1));
            GameRegistry.addRecipe(new ItemStack(colouredBricks, 8, 3), "PXP", "XPX", "PXP", 'P', new ItemStack(malachiteBrick, 1, 1), 'X', new ItemStack(TFCItems.mortar, 1));
        }

        if (Config.enableMortaredCobble) {
            GameRegistry.addRecipe(new ItemStack(mortaredCobble1, 4, 0), "RXR", "X X", "RXR", 'R', new ItemStack(TFCItems.looseRock, 1, 0), 'X', new ItemStack(TFCItems.mortar, 1));
            GameRegistry.addRecipe(new ItemStack(mortaredCobble1, 4, 1), "RXR", "X X", "RXR", 'R', new ItemStack(TFCItems.looseRock, 1, 1), 'X', new ItemStack(TFCItems.mortar, 1));
            GameRegistry.addRecipe(new ItemStack(mortaredCobble1, 4, 2), "RXR", "X X", "RXR", 'R', new ItemStack(TFCItems.looseRock, 1, 2), 'X', new ItemStack(TFCItems.mortar, 1));
            GameRegistry.addRecipe(new ItemStack(mortaredCobble1, 4, 3), "RXR", "X X", "RXR", 'R', new ItemStack(TFCItems.looseRock, 1, 11), 'X', new ItemStack(TFCItems.mortar, 1));
            GameRegistry.addRecipe(new ItemStack(mortaredCobble1, 4, 4), "RXR", "X X", "RXR", 'R', new ItemStack(TFCItems.looseRock, 1, 12), 'X', new ItemStack(TFCItems.mortar, 1));
            GameRegistry.addRecipe(new ItemStack(mortaredCobble1, 4, 5), "RXR", "X X", "RXR", 'R', new ItemStack(TFCItems.looseRock, 1, 13), 'X', new ItemStack(TFCItems.mortar, 1));
            GameRegistry.addRecipe(new ItemStack(mortaredCobble1, 4, 6), "RXR", "X X", "RXR", 'R', new ItemStack(TFCItems.looseRock, 1, 14), 'X', new ItemStack(TFCItems.mortar, 1));
            GameRegistry.addRecipe(new ItemStack(mortaredCobble1, 4, 7), "RXR", "X X", "RXR", 'R', new ItemStack(TFCItems.looseRock, 1, 3), 'X', new ItemStack(TFCItems.mortar, 1));
            GameRegistry.addRecipe(new ItemStack(mortaredCobble1, 4, 8), "RXR", "X X", "RXR", 'R', new ItemStack(TFCItems.looseRock, 1, 4), 'X', new ItemStack(TFCItems.mortar, 1));
            GameRegistry.addRecipe(new ItemStack(mortaredCobble1, 4, 9), "RXR", "X X", "RXR", 'R', new ItemStack(TFCItems.looseRock, 1, 5), 'X', new ItemStack(TFCItems.mortar, 1));
            GameRegistry.addRecipe(new ItemStack(mortaredCobble1, 4, 10), "RXR", "X X", "RXR", 'R', new ItemStack(TFCItems.looseRock, 1, 6), 'X', new ItemStack(TFCItems.mortar, 1));
            GameRegistry.addRecipe(new ItemStack(mortaredCobble1, 4, 11), "RXR", "X X", "RXR", 'R', new ItemStack(TFCItems.looseRock, 1, 7), 'X', new ItemStack(TFCItems.mortar, 1));
            GameRegistry.addRecipe(new ItemStack(mortaredCobble1, 4, 12), "RXR", "X X", "RXR", 'R', new ItemStack(TFCItems.looseRock, 1, 8), 'X', new ItemStack(TFCItems.mortar, 1));
            GameRegistry.addRecipe(new ItemStack(mortaredCobble1, 4, 13), "RXR", "X X", "RXR", 'R', new ItemStack(TFCItems.looseRock, 1, 9), 'X', new ItemStack(TFCItems.mortar, 1));
            GameRegistry.addRecipe(new ItemStack(mortaredCobble1, 4, 14), "RXR", "X X", "RXR", 'R', new ItemStack(TFCItems.looseRock, 1, 15), 'X', new ItemStack(TFCItems.mortar, 1));
            GameRegistry.addRecipe(new ItemStack(mortaredCobble1, 4, 15), "RXR", "X X", "RXR", 'R', new ItemStack(TFCItems.looseRock, 1, 16), 'X', new ItemStack(TFCItems.mortar, 1));
            GameRegistry.addRecipe(new ItemStack(mortaredCobble2, 4, 0), "RXR", "X X", "RXR", 'R', new ItemStack(TFCItems.looseRock, 1, 17), 'X', new ItemStack(TFCItems.mortar, 1));
            GameRegistry.addRecipe(new ItemStack(mortaredCobble2, 4, 1), "RXR", "X X", "RXR", 'R', new ItemStack(TFCItems.looseRock, 1, 18), 'X', new ItemStack(TFCItems.mortar, 1));
            GameRegistry.addRecipe(new ItemStack(mortaredCobble2, 4, 2), "RXR", "X X", "RXR", 'R', new ItemStack(TFCItems.looseRock, 1, 19), 'X', new ItemStack(TFCItems.mortar, 1));
            GameRegistry.addRecipe(new ItemStack(mortaredCobble2, 4, 3), "RXR", "X X", "RXR", 'R', new ItemStack(TFCItems.looseRock, 1, 20), 'X', new ItemStack(TFCItems.mortar, 1));
            GameRegistry.addRecipe(new ItemStack(mortaredCobble2, 4, 4), "RXR", "X X", "RXR", 'R', new ItemStack(TFCItems.looseRock, 1, 10), 'X', new ItemStack(TFCItems.mortar, 1));

        }

        if(Config.enableGabion){
            GameRegistry.addRecipe(new ItemStack(gabion1,1,0),"WSW",'W', new ItemStack(TFCBlocks.wattle,1),'S', new ItemStack(TFCBlocks.stoneIgInCobble,1,0));
            GameRegistry.addRecipe(new ItemStack(gabion1,1,1),"WSW",'W', new ItemStack(TFCBlocks.wattle,1),'S', new ItemStack(TFCBlocks.stoneIgInCobble,1,1));
            GameRegistry.addRecipe(new ItemStack(gabion1,1,2),"WSW",'W', new ItemStack(TFCBlocks.wattle,1),'S', new ItemStack(TFCBlocks.stoneIgInCobble,1,2));
            GameRegistry.addRecipe(new ItemStack(gabion1,1,3),"WSW",'W', new ItemStack(TFCBlocks.wattle,1),'S', new ItemStack(TFCBlocks.stoneIgExCobble,1,0));
            GameRegistry.addRecipe(new ItemStack(gabion1,1,4),"WSW",'W', new ItemStack(TFCBlocks.wattle,1),'S', new ItemStack(TFCBlocks.stoneIgExCobble,1,1));
            GameRegistry.addRecipe(new ItemStack(gabion1,1,5),"WSW",'W', new ItemStack(TFCBlocks.wattle,1),'S', new ItemStack(TFCBlocks.stoneIgExCobble,1,2));
            GameRegistry.addRecipe(new ItemStack(gabion1,1,6),"WSW",'W', new ItemStack(TFCBlocks.wattle,1),'S', new ItemStack(TFCBlocks.stoneIgExCobble,1,3));
            GameRegistry.addRecipe(new ItemStack(gabion1,1,7),"WSW",'W', new ItemStack(TFCBlocks.wattle,1),'S', new ItemStack(TFCBlocks.stoneSedCobble,1,0));
            GameRegistry.addRecipe(new ItemStack(gabion1,1,8),"WSW",'W', new ItemStack(TFCBlocks.wattle,1),'S', new ItemStack(TFCBlocks.stoneSedCobble,1,1));
            GameRegistry.addRecipe(new ItemStack(gabion1,1,9),"WSW",'W', new ItemStack(TFCBlocks.wattle,1),'S', new ItemStack(TFCBlocks.stoneSedCobble,1,2));
            GameRegistry.addRecipe(new ItemStack(gabion1,1,10),"WSW",'W', new ItemStack(TFCBlocks.wattle,1),'S', new ItemStack(TFCBlocks.stoneSedCobble,1,3));
            GameRegistry.addRecipe(new ItemStack(gabion1,1,11),"WSW",'W', new ItemStack(TFCBlocks.wattle,1),'S', new ItemStack(TFCBlocks.stoneSedCobble,1,4));
            GameRegistry.addRecipe(new ItemStack(gabion1,1,12),"WSW",'W', new ItemStack(TFCBlocks.wattle,1),'S', new ItemStack(TFCBlocks.stoneSedCobble,1,5));
            GameRegistry.addRecipe(new ItemStack(gabion1,1,13),"WSW",'W', new ItemStack(TFCBlocks.wattle,1),'S', new ItemStack(TFCBlocks.stoneSedCobble,1,6));
            GameRegistry.addRecipe(new ItemStack(gabion1,1,14),"WSW",'W', new ItemStack(TFCBlocks.wattle,1),'S', new ItemStack(TFCBlocks.stoneMMCobble,1,0));
            GameRegistry.addRecipe(new ItemStack(gabion1,1,15),"WSW",'W', new ItemStack(TFCBlocks.wattle,1),'S', new ItemStack(TFCBlocks.stoneMMCobble,1,1));
            GameRegistry.addRecipe(new ItemStack(gabion2,1,0),"WSW",'W', new ItemStack(TFCBlocks.wattle,1),'S', new ItemStack(TFCBlocks.stoneMMCobble,1,2));
            GameRegistry.addRecipe(new ItemStack(gabion2,1,1),"WSW",'W', new ItemStack(TFCBlocks.wattle,1),'S', new ItemStack(TFCBlocks.stoneMMCobble,1,3));
            GameRegistry.addRecipe(new ItemStack(gabion2,1,2),"WSW",'W', new ItemStack(TFCBlocks.wattle,1),'S', new ItemStack(TFCBlocks.stoneMMCobble,1,4));
            GameRegistry.addRecipe(new ItemStack(gabion2,1,3),"WSW",'W', new ItemStack(TFCBlocks.wattle,1),'S', new ItemStack(TFCBlocks.stoneMMCobble,1,5));
            GameRegistry.addRecipe(new ItemStack(gabion2,1,4),"WSW",'W', new ItemStack(TFCBlocks.wattle,1),'S', new ItemStack(TFCBlocks.stoneSedCobble,1,7));
        }

        if (Config.enableHandgonne) {
            GameRegistry.addRecipe(new ItemStack(brassHandgonne,1,0),"TXX", "XRX", "XXS", 'T', new ItemStack(TFCItems.brassTube, 1, 0), 'R', new ItemStack(TFCItems.resin, 1, 0), 'S', new ItemStack(TFCItems.stick, 1, 0));
            GameRegistry.addRecipe(new ItemStack(bronzeHandgonne, 1, 0), "TXX", "XRX", "XXS", 'T', new ItemStack(TFCItems.tuyereBronze, 1, 0), 'R', new ItemStack(TFCItems.resin, 1, 0), 'S', new ItemStack(TFCItems.stick, 1, 0));
            GameRegistry.addRecipe(new ItemStack(blackBronzeHandgonne, 1, 0), "TXX", "XRX", "XXS", 'T', new ItemStack(TFCItems.tuyereBlackBronze, 1, 0), 'R', new ItemStack(TFCItems.resin, 1, 0), 'S', new ItemStack(TFCItems.stick, 1, 0));
            GameRegistry.addRecipe(new ItemStack(bismuthBronzeHandgonne, 1, 0), "TXX", "XRX", "XXS", 'T', new ItemStack(TFCItems.tuyereBismuthBronze, 1, 0), 'R', new ItemStack(TFCItems.resin, 1, 0), 'S', new ItemStack(TFCItems.stick, 1, 0));
            GameRegistry.addRecipe(new ItemStack(ironHandgonne, 1, 0), "TXX", "XRX", "XXS", 'T', new ItemStack(TFCItems.tuyereWroughtIron, 1, 0), 'R', new ItemStack(TFCItems.resin, 1, 0), 'S', new ItemStack(TFCItems.stick, 1, 0));
        }

        if (Config.enableExtraMaces) {

            for (Item i : string) {
                GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(stoneClub, 1), "itemRock", new ItemStack(i, 1), "stickWood"));
            }

            GameRegistry.addShapelessRecipe(new ItemStack(saturaMaceMold,1,1),new ItemStack(com.dunk.tfc.ItemSetup.clayMoldMace,1,1));
            GameRegistry.addShapelessRecipe(new ItemStack(com.dunk.tfc.ItemSetup.clayMoldMace,1,1), new ItemStack(saturaMaceMold,1,1));

            GameRegistry.addShapelessRecipe(new ItemStack(leadMaceHead, 1), new ItemStack(saturaMaceMold, 1, 2));
            GameRegistry.addShapedRecipe(new ItemStack(leadMace, 1), "H", "I", 'H', new ItemStack(leadMaceHead, 1), 'I', new ItemStack(TFCItems.stick, 1));

            GameRegistry.addShapelessRecipe(new ItemStack(brassMaceHead, 1), new ItemStack(saturaMaceMold, 1, 3));
            GameRegistry.addShapedRecipe(new ItemStack(brassMace, 1), "H", "I", 'H', new ItemStack(brassMaceHead, 1), 'I', new ItemStack(TFCItems.stick, 1));
        }

        if (Config.enableBolas) {
            for (Item i : string) {
                if (i != TFCItems.sinew)
                    GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(bolas, 1), new ItemStack(i, 1), "itemRock", "itemRock", "itemRock"));
            }
        }

        if (Config.enableIncendiaryPot) {
            GameRegistry.addShapelessRecipe(new ItemStack(incendiaryPot, 1), new ItemStack(pitchPot, 1), new ItemStack(wick));
        }

        if (Config.enableSpikes) {
            GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(spikeItem, 1, 0), "stickWood", "itemKnife"));
        }

        if (Config.enableLeafCover) {
            GameRegistry.addShapedRecipe(new ItemStack(leafCover, 1, 0), "SS", "II", 'S', new ItemStack(TFCItems.straw, 1), 'I', new ItemStack(TFCItems.stick));
            GameRegistry.addShapelessRecipe(new ItemStack(leafCover,1,0),new ItemStack(leafCover,1,1));
            GameRegistry.addShapelessRecipe(new ItemStack(leafCover,1,1),new ItemStack(leafCover,1,0));
        }
        if (Config.enablePotGrenades) {

            List<Item> sheets = new ArrayList<Item>();
            sheets.add(TFCItems.linenCloth);
            sheets.add(TFCItems.cottonCloth);
            sheets.add(TFCItems.woolCloth);
            sheets.add(TFCItems.silkCloth);
            sheets.add(TFCItems.burlapCloth);

            for (Item i : sheets) {
                GameRegistry.addShapelessRecipe(new ItemStack(potGrenade, 1), new ItemStack(TFCItems.potterySmallVessel, 1, 1), new ItemStack(match, 1, 1), new ItemStack(TFCItems.rope, 1), new ItemStack(i, 1));
                GameRegistry.addShapelessRecipe(new ItemStack(potGrenadeQuick, 1), new ItemStack(TFCItems.potterySmallVessel, 1, 1), new ItemStack(match, 1, 2), new ItemStack(TFCItems.rope, 1), new ItemStack(i, 1));
                GameRegistry.addShapelessRecipe(new ItemStack(potGrenadeLong, 1), new ItemStack(TFCItems.potterySmallVessel, 1, 1), new ItemStack(match, 1, 3), new ItemStack(TFCItems.rope, 1), new ItemStack(i, 1));


            }
        }
        if (Config.enableFirecrackers)
            GameRegistry.addShapelessRecipe(new ItemStack(firecracker,4),new ItemStack(match,1,1),new ItemStack(Items.paper,1),new ItemStack(Items.paper,1),new ItemStack(Items.gunpowder,1));
        if (Config.enableSmokeBombs){

        }
        if(Config.enablePotGrenades||Config.enableFirecrackers||Config.enableSmokeBombs) {
            GameRegistry.addShapelessRecipe(new ItemStack(match, 1, 3), new ItemStack(match, 1, 1), new ItemStack(match, 1, 1));
            GameRegistry.addShapelessRecipe(new ItemStack(match, 1, 2), new ItemStack(match, 1, 1), new ItemStack(Items.paper, 1), new ItemStack(Items.paper, 1));
        }
        if(Config.enableCastanets)
            for(Item s : string) {
                GameRegistry.addShapelessRecipe(new ItemStack(castanets, 1), new ItemStack(s,1),new ItemStack(TFCItems.potteryBowl,1,1),new ItemStack(TFCItems.potteryBowl,1,1));
            }
        if(Config.enableArquebus){
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(brassArquebus, 1), new Object[]{"LMT", "LL ", 'L', "woodLumber", 'M', new ItemStack(mechanism, 1, 0), 'T', new ItemStack(TFCItems.brassTube, 1)}));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ironArquebus, 1), new Object[]{"LMT", "LL ", 'L', "woodLumber", 'M', new ItemStack(mechanism, 1, 0), 'T', new ItemStack(TFCItems.tuyereWroughtIron, 1)}));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(steelArquebus, 1), new Object[]{"LMT", "LL ", 'L', "woodLumber", 'M', new ItemStack(mechanism, 1, 0), 'T', new ItemStack(TFCItems.tuyereSteel, 1)}));
        }
        if(Config.enableMBlunderbuss) {
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(brassMBlunderbuss, 1), new Object[]{"LMT", "LL ", 'L', "woodLumber", 'M', new ItemStack(mechanism, 1, 0), 'T', new ItemStack(flaredBarrel, 1, 0)}));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ironMBlunderbuss, 1), new Object[]{"LMT", "LL ", 'L', "woodLumber", 'M', new ItemStack(mechanism, 1, 0), 'T', new ItemStack(flaredBarrel, 1, 1)}));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(steelMBlunderbuss, 1), new Object[]{"LMT", "LL ", 'L', "woodLumber", 'M', new ItemStack(mechanism, 1, 0), 'T', new ItemStack(flaredBarrel, 1, 2)}));
            GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(shot,4,0),"itemRock","itemHammer"));
            GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(shot,1,1),new ItemStack(TFCItems.leadBullet,1),"itemHammer"));
        }
    }
}
