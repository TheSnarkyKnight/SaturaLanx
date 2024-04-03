package com.snark.saturalanx.handlers;

import com.dunk.tfc.Core.TFC_Core;
import com.dunk.tfc.Food.ItemFoodTFC;
import com.dunk.tfc.api.TFCBlocks;
import com.dunk.tfc.api.TFCItems;
import com.snark.saturalanx.core.FoodSetup;
import com.snark.saturalanx.core.ItemSetup;
import com.snark.saturalanx.core.Config;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import net.minecraft.block.Block;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.List;

import static com.dunk.tfc.Handlers.FoodCraftingHandler.gridHasItem;
import static com.dunk.tfc.Handlers.FoodCraftingHandler.handleItem;

public class SaturaFoodCraftingHandler{

    public static boolean preCrafted;
    public SaturaFoodCraftingHandler(){

    }

    @SubscribeEvent
    public void onFoodCrafting(PlayerEvent.ItemCraftedEvent e) {
        if (preCrafted) {
            preCrafted = false;
        } else {
            ItemStack craftResult = e.crafting;
            IInventory craftingInv = e.craftMatrix;
            if (craftingInv != null) {
                List knives;
                if (refineDandelionRecipe(craftResult, craftingInv)) {
                    knives = OreDictionary.getOres("itemKnife", false);
                    handleItem(e.player, craftingInv, knives);
                    if (craftResult.getItem().equals(FoodSetup.dandelionTop)) {
                        TFC_Core.giveItemToPlayer(ItemFoodTFC.createTag(new ItemStack(FoodSetup.dandelionRoot),getRandomFloat(4,8)), e.player);
                    }
                }
                if (refineGoldenrodRecipe(craftResult, craftingInv)){
                    knives = OreDictionary.getOres("itemKnife", false);
                    handleItem(e.player, craftingInv, knives);
                    if (craftResult.getItem().equals(FoodSetup.goldenrodLeaves)) {
                        TFC_Core.giveItemToPlayer(ItemFoodTFC.createTag(new ItemStack(FoodSetup.goldenrodTop),getRandomFloat(4,8)), e.player);
                    }
                }
                if (refineNasturtiumRecipe(craftResult, craftingInv)){
                    knives = OreDictionary.getOres("itemKnife", false);
                    handleItem(e.player, craftingInv, knives);
                }
                if (refineCalendulaRecipe(craftResult, craftingInv)){
                    knives = OreDictionary.getOres("itemKnife", false);
                    handleItem(e.player, craftingInv, knives);
                }
                if (refineDaisyRecipe(craftResult, craftingInv)){
                    knives = OreDictionary.getOres("itemKnife", false);
                    handleItem(e.player, craftingInv, knives);
                }
                if (refinePoppyRecipe(craftResult, craftingInv)){
                    knives = OreDictionary.getOres("itemKnife", false);
                    handleItem(e.player, craftingInv, knives);
                }
                if (refineMilkweedRecipe(craftResult, craftingInv)){
                    knives = OreDictionary.getOres("itemKnife", false);
                    handleItem(e.player, craftingInv, knives);
                }
                if (cleanMilkweedRecipe(craftResult, craftingInv)){
                    knives = OreDictionary.getOres("itemKnife", false);
                    handleItem(e.player, craftingInv, knives);
                    if (craftResult.getItem().equals(FoodSetup.cleanedMilkweedPod)) {
                        TFC_Core.giveItemToPlayer(new ItemStack(TFCItems.straw,getRandomInt(1,4)), e.player);
                    }
                }
                if (refineTulipRecipe(craftResult, craftingInv)){
                    knives = OreDictionary.getOres("itemKnife", false);
                    handleItem(e.player, craftingInv, knives);
                    Item i = craftResult.getItem();
                    if (i.equals(FoodSetup.tulipPetalsOrange)||i.equals(FoodSetup.tulipPetalsPink)||i.equals(FoodSetup.tulipPetalsRed)||i.equals(FoodSetup.tulipPetalsWhite)) {
                        TFC_Core.giveItemToPlayer(ItemFoodTFC.createTag(new ItemStack(FoodSetup.tulipBulb),getRandomFloat(4,8)), e.player);
                    }
                }
            }
        }
    }

    public static boolean refineDandelionRecipe(ItemStack result, IInventory grid){
        if(Config.enableDandelionFood)
            return (result.getItem().equals(FoodSetup.dandelionTop) && gridHasItem(grid, TFCBlocks.flowers));
        return false;
    }

    public static boolean refineGoldenrodRecipe(ItemStack result, IInventory grid){
        if(Config.enableGoldenrodFood)
            return (result.getItem().equals(FoodSetup.goldenrodLeaves) && gridHasItem(grid, TFCBlocks.flora));
        return false;
    }

    public static boolean refineNasturtiumRecipe(ItemStack result, IInventory grid){
        if(Config.enableNasturtiumFood)
            return (result.getItem().equals(FoodSetup.preparedNasturtium) && gridHasItemMeta(grid, TFCBlocks.flowers,1));
        return false;
    }

    public static boolean refineCalendulaRecipe(ItemStack result, IInventory grid){
        if(Config.enableCalendulaFood)
            return (result.getItem().equals(FoodSetup.calendulaPetals) && gridHasItemMeta(grid, TFCBlocks.flowers, 5));
        return false;
    }

    public static boolean refineMilkweedRecipe(ItemStack result, IInventory grid){
        if(Config.enableMilkweedFood)
            return (result.getItem().equals(FoodSetup.milkweedPod) && (gridHasItemMeta(grid, TFCBlocks.flowers,2)||gridHasItemMeta(grid, TFCBlocks.flowers,3)||gridHasItemMeta(grid, TFCBlocks.flowers,4)));
        return false;
    }

    public static boolean cleanMilkweedRecipe(ItemStack result, IInventory grid){
        if(Config.enableMilkweedFood)
            return (result.getItem().equals(FoodSetup.cleanedMilkweedPod) && gridHasItem(grid, FoodSetup.milkweedPod));
        return false;
    }

    public static boolean refineDaisyRecipe(ItemStack result, IInventory grid){
        if(Config.enableDaisyFood)
            return (result.getItem().equals(FoodSetup.daisyFlower) && gridHasItemMeta(grid, TFCBlocks.flowers2,8));
        return false;
    }

    public static boolean refineTulipRecipe(ItemStack result, IInventory grid){
        if(Config.enableTulipFood)
            return ((result.getItem().equals(FoodSetup.tulipPetalsWhite)||result.getItem().equals(FoodSetup.tulipPetalsPink)||result.getItem().equals(FoodSetup.tulipPetalsRed)||result.getItem().equals(FoodSetup.tulipPetalsOrange)) &&
                    (gridHasItemMeta(grid, TFCBlocks.flowers2,4)||gridHasItemMeta(grid, TFCBlocks.flowers2,5)||gridHasItemMeta(grid, TFCBlocks.flowers2,6)||gridHasItemMeta(grid, TFCBlocks.flowers2,7)));
        return false;
    }

    public static boolean refinePoppyRecipe(ItemStack result, IInventory grid){
        if(Config.enablePoppyFood)
            return (result.getItem().equals(FoodSetup.poppyseed) && gridHasItem(grid, TFCBlocks.flowers2));
    return false;
    }

    private float getRandomFloat(float min, float max){
        return (float)Math.random() * (max-min) + min;
    }

    private int getRandomInt(int min, int max){
        return (int) (Math.random() * (max - min) + min);
    }

    public static boolean gridHasItemMeta(IInventory iinventory, Block id, int meta) {
        for(int i = 0; i < iinventory.getSizeInventory(); ++i) {
            if (iinventory.getStackInSlot(i) != null && iinventory.getStackInSlot(i).getItem().equals(Item.getItemFromBlock(id)) && iinventory.getStackInSlot(i).getItemDamage() == meta) {
                return true;
            }
        }

        return false;
    }

    public static boolean gridHasItemMeta(IInventory iinventory, Item id, int meta) {
        for(int i = 0; i < iinventory.getSizeInventory(); ++i) {
            if (iinventory.getStackInSlot(i) != null && iinventory.getStackInSlot(i).getItem().equals(id) && iinventory.getStackInSlot(i).getItemDamage() == meta) {
                return true;
            }
        }

        return false;
    }
}
