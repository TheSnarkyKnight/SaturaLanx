package com.snark.saturalanx.items.tools;

import com.dunk.tfc.Core.TFC_Core;
import com.dunk.tfc.api.Enums.EnumSize;
import com.dunk.tfc.api.Enums.EnumWeight;
import com.snark.saturalanx.core.ItemSetup;
import com.snark.saturalanx.items.ItemSL;
import com.snark.saturalanx.items.weapons.gunpowder.PaperCartridgeItem;
import com.snark.saturalanx.items.weapons.gunpowder.guns.FirearmItem;
import com.snark.saturalanx.items.weapons.gunpowder.guns.IBlunderbluss;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowNockEvent;

public class BallPuller extends ItemSL {
    //lol, lmao
    public BallPuller(){
        super();
        this.setUnlocalizedName("BallPuller");
        this.textureFolder = "tools";
        this.size = EnumSize.MEDIUM;
        this.weight = EnumWeight.MEDIUM;
        this.stackable = false;
    }

    @Override
    public EnumAction getItemUseAction(ItemStack p_77661_1_) {
        return EnumAction.bow;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return 72000;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player){

        boolean gunFound = false;

        for(int i = 0;i<=9;i++){
            ItemStack is = player.inventory.mainInventory[i];
            if(is != null && is.getItem() instanceof FirearmItem && is.stackTagCompound.getBoolean("loaded")){
                gunFound = true;
                break;
            }
        }

        if(gunFound){
            ArrowNockEvent event = new ArrowNockEvent(player, stack);
            MinecraftForge.EVENT_BUS.post(event);
            if (event.isCanceled()) {
                return event.result;
            }

            player.setItemInUse(stack, this.getMaxItemUseDuration(stack));
        }

        return stack;
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World world, EntityPlayer player, int inUseCount){

        for(int i = 0;i<=9;i++){
            ItemStack is = player.inventory.mainInventory[i];
            if(is != null && is.getItem() instanceof FirearmItem && is.stackTagCompound.getBoolean("loaded")){

                ItemStack ammo = ItemStack.loadItemStackFromNBT((NBTTagCompound) is.stackTagCompound.getTag("ammoItem"));

                if(ammo.getItem() instanceof PaperCartridgeItem)
                    ammo = new ItemStack(((PaperCartridgeItem) ammo.getItem()).getContainedItem(),1,ammo.getItemDamage());

                ammo.stackSize = 1;

                TFC_Core.giveItemToPlayer(ammo,player);
                TFC_Core.giveItemToPlayer(new ItemStack(Items.gunpowder,1),player);

                is.stackTagCompound.setBoolean("loaded",false);
                is.stackTagCompound.setInteger("ammoPosition",-2);
                is.stackTagCompound.setInteger("powderPosition",-2);
                if(is.getItem() instanceof IBlunderbluss)
                    is.stackTagCompound.setInteger("waddingPosition",-2);
                break;

            }
        }
    }

    }
