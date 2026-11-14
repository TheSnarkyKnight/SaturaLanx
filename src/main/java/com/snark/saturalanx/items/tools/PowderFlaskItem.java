package com.snark.saturalanx.items.tools;

import com.dunk.tfc.Core.TFC_Core;
import com.dunk.tfc.api.Enums.EnumSize;
import com.dunk.tfc.api.Enums.EnumWeight;
import com.snark.saturalanx.SaturaLanx;
import com.snark.saturalanx.items.ItemSL;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class PowderFlaskItem extends ItemSL {

    public PowderFlaskItem(String[] s){
        super(s);
        this.stackable = false;
        this.size = EnumSize.MEDIUM;
        this.weight = EnumWeight.MEDIUM;
    }

    @Override
    public boolean canStack() {
        return false;
    }

    @Override
    public void onCreated(ItemStack stack, World world, EntityPlayer player) {
        setupNBT(stack);
    }

    public void setupNBT(ItemStack stack){

        NBTTagCompound tag = stack.getTagCompound();

        if(tag == null)
            tag = new NBTTagCompound();

        tag.setInteger("powder",0);

        stack.stackTagCompound = tag;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {

        addPowder(stack,player);

        return  stack;

    }

    @Override
    public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack) {

        if(entityLiving instanceof EntityPlayer)
            removePowder(stack,(EntityPlayer) entityLiving);

        return true;
    }

    public void addPowder(ItemStack stack, EntityPlayer player){

        if(stack.stackTagCompound == null)
            setupNBT(stack);

        for(int i = 0; i < player.inventory.mainInventory.length; i++){
            ItemStack is = player.inventory.mainInventory[i];
            if(is != null && is.getItem() == Items.gunpowder){

                int powder = stack.stackTagCompound.getInteger("powder");

                if(powder<256){

                    int amount = (player.isSneaking()) ? is.stackSize : 1;

                    powder += amount;

                    if(powder >= 256) {
                        amount = is.stackSize - (powder - 256);
                        powder = 256;
                    }

                    stack.stackTagCompound.setInteger("powder",powder);
                    is.stackSize -= amount;
                    if(is.stackSize <= 0)
                        player.inventory.setInventorySlotContents(i,null);
                }

                break;
            }
        }
    }

    public void decreasePowderCounter(ItemStack stack, int amount){
        if(stack.stackTagCompound == null)
            setupNBT(stack);

        int powder = stack.stackTagCompound.getInteger("powder");

        if(powder - amount >= 0){
            powder -= amount;
            stack.stackTagCompound.setInteger("powder",powder);
        }
    }

    public void removePowder(ItemStack stack, EntityPlayer player){

        if(stack.stackTagCompound == null)
            setupNBT(stack);

        int powder = stack.stackTagCompound.getInteger("powder");

        if(powder > 0){

            int amount = (player.isSneaking()) ? Math.min(64,powder) : 1;

            powder -= amount;

            stack.stackTagCompound.setInteger("powder",powder);
            TFC_Core.giveItemToPlayer(new ItemStack(Items.gunpowder,amount),player);

        }
    }
}
