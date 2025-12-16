package com.snark.saturalanx.items.warfare.incendiary;

import com.dunk.tfc.api.Enums.EnumItemReach;
import com.dunk.tfc.api.Enums.EnumSize;
import com.dunk.tfc.api.Enums.EnumWeight;
import com.snark.saturalanx.core.Config;
import com.snark.saturalanx.core.Util;
import com.snark.saturalanx.entities.EntityIncendiaryPot;
import com.snark.saturalanx.items.ItemSL;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowNockEvent;

import static com.snark.saturalanx.core.ItemSetup.WEAPONPATH;

public class IncendiaryPot extends ItemSL {

    public IIcon icon2;
    public IncendiaryPot(){
        super();
        this.stackable = false;
        this.size = EnumSize.SMALL;
        this.weight = EnumWeight.HEAVY;
        this.reach = EnumItemReach.SHORT;
        this.setMaxDamage(Config.incendiaryPotWickLength);
    }

    @Override
    public void registerIcons(IIconRegister registerer) {
        this.itemIcon = registerer.registerIcon(WEAPONPATH + "IncendiaryPotLit");
        this.icon2 = registerer.registerIcon(WEAPONPATH + "IncendiaryPot");
    }



    @Override
    public IIcon getIcon(ItemStack stack, int pass){
        return this.getIconIndex(stack);
    }

    @Override
    public IIcon getIconIndex(ItemStack stack) {
        NBTTagCompound tag = stack.getTagCompound();

        if(tag==null){
            tag = new NBTTagCompound();
            tag.setBoolean("lit",false);
        }

        if(tag.getBoolean("lit"))
            return itemIcon;
        else
            return icon2;
    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.bow;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return 72000;
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        Block b = world.getBlock(x,y,z);

        if(stack.stackTagCompound == null) {
            stack.stackTagCompound = new NBTTagCompound();
            stack.stackTagCompound.setBoolean("lit",false);
        }

        if(!stack.stackTagCompound.getBoolean("lit")&& Util.canBlockLight(x,y,z,world)){
            stack.stackTagCompound.setBoolean("lit",true);
            return true;
        }

        return false;
    }

    @Override
    public void onCreated(ItemStack stack, World world, EntityPlayer player){
        stack.stackTagCompound = new NBTTagCompound();
        stack.stackTagCompound.setBoolean("lit", false);
    }

    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int slot, boolean isHeld){
        if(stack != null) {
            if (stack.stackTagCompound != null && stack.stackTagCompound.getBoolean("lit")) {
                stack.damageItem(1, (EntityLivingBase) entity);
                if (stack.getItemDamage() <= 0) {
                    if (!(entity instanceof EntityPlayer && ((EntityPlayer) entity).capabilities.isCreativeMode)) {
                        EntityIncendiaryPot pot = new EntityIncendiaryPot(world);
                        pot.posX = entity.posX;
                        pot.posZ = entity.posZ;
                        pot.posY = entity.posY;
                        if (!world.isRemote)
                            world.spawnEntityInWorld(pot);
                        stack.stackTagCompound.setBoolean("lit", false);
                        if (entity instanceof EntityPlayer)
                            ((EntityPlayer) entity).inventory.setInventorySlotContents(slot, null);
                    }
                }
                if(stack.stackTagCompound.hasKey("delete"))
                    if(stack.stackTagCompound.getBoolean("delete"))
                        ((EntityPlayer) entity).inventory.setInventorySlotContents(slot, null);
            }
        }
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player){

        if(stack.stackTagCompound == null){
            stack.stackTagCompound = new NBTTagCompound();
            stack.stackTagCompound.setBoolean("lit",false);
        }

        boolean lit = stack.stackTagCompound.getBoolean("lit");

        if(!lit&&Util.canPlayerLight(player)){
            ArrowNockEvent event = new ArrowNockEvent(player, stack);
            MinecraftForge.EVENT_BUS.post(event);
            if (event.isCanceled()) {
                return event.result;
            }

            player.setItemInUse(stack, this.getMaxItemUseDuration(stack));
        } else if (lit&&!player.isSneaking()) {
            EntityIncendiaryPot pot = new EntityIncendiaryPot(world, player);
            world.playSoundAtEntity(player,"random.bow",1,1);
            if(!world.isRemote)
                world.spawnEntityInWorld(pot);
            if(!player.capabilities.isCreativeMode) {
                stack.stackTagCompound.setBoolean("delete",true);
            }
        }
        return stack;
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World world, EntityPlayer player, int inUseCount){

        boolean lit = stack.stackTagCompound.getBoolean("lit");

        if(!lit){
            stack.stackTagCompound.setBoolean("lit",true);
            world.playSoundAtEntity(player,"fire.ignite",1,1);
        }

    }
}
