package com.snark.saturalanx.items.weapons.ranged;

import com.snark.saturalanx.core.Config;
import com.snark.saturalanx.items.ItemSL;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

import java.util.List;

public class PoisonedArrowItem extends ItemSL {
    public PoisonedArrowItem(){
        super();
        this.setMaxStackSize(4);
        this.textureFolder = "weapons/arrows";
    }

    @Override
    public void addItemInformation(ItemStack stack, EntityPlayer player, List<String> list) {
        NBTTagCompound tag = stack.stackTagCompound;
        if(tag != null){
            String poison1 = tag.getString("poison1");
            String poison2 = tag.getString("poison2");

            EnumChatFormatting form = null;
            if(poison1==poison2&&poison1!=null)
                form = EnumChatFormatting.GOLD;
            else if (poison1 != poison2 && poison1 != null)
                form = EnumChatFormatting.YELLOW;
            else
                form = EnumChatFormatting.DARK_GRAY;

            if(poison1!=null&& !poison1.equals("null"))
                list.add(form+poison1.substring(0,1).toUpperCase()+poison1.substring(1));
            if(poison2 != null && !poison2.equals("null") && (poison1 == null || !poison1.equals(poison2)))
                list.add(form+poison2.substring(0,1).toUpperCase()+poison2.substring(1));
            if(poison1 == null && poison2 == null)
                list.add(form+"The poison seems to have worn off...");
        }
    }

    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int slot, boolean isHeld){
        if(Config.poisonProjectileExpirationTime != -1 && world.getTotalWorldTime() % 24000 == 0){
            NBTTagCompound tag = stack.stackTagCompound;
            if(tag != null){
                if(tag.getLong("expiration")<= world.getTotalWorldTime()){
                    tag.setString("poison1","null");
                    tag.setString("poison2","null");
                    tag.setLong("expiration",world.getTotalWorldTime()+Config.poisonProjectileExpirationTime * 24000L);
                    stack.setTagCompound(tag);
                }
            }
        }
    }

    @Override
    public void onCreated(ItemStack stack, World world, EntityPlayer player) {
        NBTTagCompound tag = stack.stackTagCompound;
        if(tag != null){
            tag.setLong("expiration",world.getTotalWorldTime()+Config.poisonProjectileExpirationTime * 24000L);
            stack.setTagCompound(tag);
        }
        else{
            tag = new NBTTagCompound();
            tag.setString("poison1",null);
            tag.setString("poison2",null);
            tag.setLong("expiration",world.getTotalWorldTime()+Config.poisonProjectileExpirationTime * 24000L);
            stack.setTagCompound(tag);

        }
    }
}
