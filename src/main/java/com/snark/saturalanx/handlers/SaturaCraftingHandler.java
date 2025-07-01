package com.snark.saturalanx.handlers;

import com.dunk.tfc.Items.Pottery.ItemPotterySmallVessel;
import com.dunk.tfc.api.TFCBlocks;
import com.dunk.tfc.api.TFCItems;
import com.snark.saturalanx.core.Config;
import com.snark.saturalanx.core.ItemSetup;
import com.snark.saturalanx.items.warfare.gunpowder.PotGrenade;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.List;

import static com.dunk.tfc.Handlers.FoodCraftingHandler.gridHasItem;
import static com.dunk.tfc.Handlers.FoodCraftingHandler.handleItem;

public class SaturaCraftingHandler {


    public SaturaCraftingHandler(){

    }

    @SubscribeEvent
    public void onCrafting(PlayerEvent.ItemCraftedEvent e) {

        EntityPlayer player = e.player;
        ItemStack itemstack = e.crafting;
        Item item = itemstack.getItem();
        int itemDamage = itemstack.getItemDamage();
        IInventory iinventory = e.craftMatrix;
        List tools;

        if(iinventory!=null){
            //Sharpened stick knife durability
            if(makeSharpenedStick(itemstack, iinventory)){
                tools = OreDictionary.getOres("itemKnife", false);
                handleItem(e.player, iinventory, tools);
            }
            //Shot crafting
            if(makeShot(itemstack, iinventory)){
                tools = OreDictionary.getOres("itemHammer",false);
                handleItem(e.player, iinventory,tools);
            }

        }
    }

    private boolean makeSharpenedStick(ItemStack result, IInventory grid){
        if(Config.enableSpikes)
            return (result.getItem().equals(ItemSetup.spikeItem) && gridHasItem(grid, TFCItems.stick));
        return false;
    }

    private boolean makeShot(ItemStack result, IInventory grid){
        if(Config.enableMBlunderbuss)
            return (result.getItem().equals(ItemSetup.shot) && (gridHasItem(grid, TFCItems.looseRock)||gridHasItem(grid, TFCBlocks.gravel2)||gridHasItem(grid, TFCItems.leadBullet)));
        return false;
    }

}
