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
            //Pot grenade crafting
            if(makePotGrenade(itemstack, iinventory)||makePotGrenadeL(itemstack, iinventory)||makePotGrenadeQ(itemstack,iinventory)){
                ItemStack vessel = null;
                for(int i = 0;i < iinventory.getSizeInventory();i++) {
                    if (iinventory.getStackInSlot(i) != null) {
                        if (iinventory.getStackInSlot(i).getItem() == TFCItems.potterySmallVessel)
                            vessel = iinventory.getStackInSlot(i);
                    }
                }
                if(vessel != null) {
                    float[] comp = getComponents(vessel);
                    ((PotGrenade) itemstack.getItem()).setComponents(itemstack, comp[0],comp[1],comp[2],comp[3]);
                }
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

    private boolean makePotGrenade(ItemStack result, IInventory grid){
        if(Config.enablePotGrenades)
            return (result.getItem().equals(ItemSetup.potGrenade) && gridHasItem(grid, TFCItems.rope));
        return false;
    }

    private boolean makePotGrenadeL(ItemStack result, IInventory grid){
        if(Config.enablePotGrenades)
            return (result.getItem().equals(ItemSetup.potGrenadeLong) && gridHasItem(grid, TFCItems.rope));
        return false;
    }

    private boolean makePotGrenadeQ(ItemStack result, IInventory grid){
        if(Config.enablePotGrenades)
            return (result.getItem().equals(ItemSetup.potGrenadeQuick) && gridHasItem(grid, TFCItems.rope));
        return false;
    }

    private boolean makeShot(ItemStack result, IInventory grid){
        if(Config.enableMBlunderbuss)
            return (result.getItem().equals(ItemSetup.shot) && (gridHasItem(grid, TFCItems.looseRock)||gridHasItem(grid, TFCBlocks.gravel2)||gridHasItem(grid, TFCItems.leadBullet)));
        return false;
    }

    private float[] getComponents(ItemStack stack) {
        float[] f = new float[]{0, 0, 0, 0};

        ItemPotterySmallVessel container = (ItemPotterySmallVessel) stack.getItem();
        ItemStack[] inv = container.loadBagInventory(stack);
        if (inv != null) {
            for (ItemStack i : inv) {
                if (i != null) {
                    if (i.getItem() == Items.gunpowder)
                        f[0] += i.stackSize;
                    if (i.getItem() == Item.getItemFromBlock(TFCBlocks.gravel) || i.getItem() == Item.getItemFromBlock(TFCBlocks.gravel2))
                        f[1] += (i.stackSize * 4);
                    if (i.getItem() == TFCItems.stoneFlake)
                        f[1] += i.stackSize;
                    if (i.getItem() == TFCItems.lime)
                        f[2] += i.stackSize;
                    if (i.getItem() == TFCItems.resin || i.getItem() == TFCItems.coal)
                        f[3] += i.stackSize;
                    if (i.getItem() == TFCItems.powder && i.getItemDamage() == 3)
                        f[3] += i.stackSize;
                    if (i.getItem() == TFCItems.powder && i.getItemDamage() == 14)
                        f[2] += i.stackSize;
                }
            }
        }

            return f;
        }

}
