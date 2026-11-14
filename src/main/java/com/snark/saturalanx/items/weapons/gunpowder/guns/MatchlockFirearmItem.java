package com.snark.saturalanx.items.weapons.gunpowder.guns;

import com.snark.saturalanx.core.Config;
import com.snark.saturalanx.core.ItemSetup;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowNockEvent;

public class MatchlockFirearmItem extends FirearmItem {

    public MatchlockFirearmItem(ToolMaterial par2EnumToolMaterial, float damage) {
        super(par2EnumToolMaterial, damage);
    }

    @Override
    public void setupNBT(ItemStack stack) {
        super.setupNBT(stack);

        stack.stackTagCompound.setLong("match",-1);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if(stack.stackTagCompound == null){
            setupNBT(stack);
        }

        if(!player.isSneaking()){
            boolean loaded = stack.stackTagCompound.getBoolean("loaded");

            if ((!loaded && canLoad(player, stack)) || (loaded && canFire(player, stack))) {

                ArrowNockEvent event = new ArrowNockEvent(player, stack);
                MinecraftForge.EVENT_BUS.post(event);
                if (event.isCanceled()) {
                    return event.result;
                }

                player.setItemInUse(stack, this.getMaxItemUseDuration(stack));
            }
        }
        else {

            long match = stack.stackTagCompound.getLong("match");

            if(match == -1){
                addMatch(stack,world,player);
            }
            else{
                removeMatch(stack, world, player);
            }
        }

        return stack;
    }

    public void addMatch(ItemStack itemStack, World world, EntityPlayer player){
        for(int i = 0;i<player.inventory.mainInventory.length;i++){
            ItemStack is = player.inventory.mainInventory[i];
            if(is != null && is.getItem() == ItemSetup.slowmatch && is.stackTagCompound.getBoolean("lit")){
                long time = world.getWorldTime();
                int matchLength = Config.slowmatchLength - is.getItemDamage();
                itemStack.stackTagCompound.setLong("match",time+matchLength);
                player.inventory.setInventorySlotContents(i,null);
                break;
            }
        }
    }

    public void removeMatch(ItemStack itemStack, World world, EntityPlayer player){
        long match = itemStack.stackTagCompound.getLong("match");
        long time = world.getWorldTime();
        int matchLength = Math.toIntExact(Config.slowmatchLength - ((int) match - time));
        ItemStack matchIs = new ItemStack(ItemSetup.slowmatch,1,matchLength);
        matchIs.stackTagCompound = new NBTTagCompound();
        matchIs.stackTagCompound.setBoolean("lit",true);
        itemStack.stackTagCompound.setLong("match",-1);
        player.inventory.addItemStackToInventory(matchIs);

    }

    @Override
    public boolean canLight(EntityPlayer player, ItemStack itemStack) {
        return itemStack.stackTagCompound.getLong("match") != -1;
    }

    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int slot, boolean isHeld) {
        if(stack.stackTagCompound == null)
            setupNBT(stack);

        long match = stack.stackTagCompound.getLong("match");

        if(world.getWorldTime() > match){
            stack.stackTagCompound.setLong("match",-1);
        }
    }
}
