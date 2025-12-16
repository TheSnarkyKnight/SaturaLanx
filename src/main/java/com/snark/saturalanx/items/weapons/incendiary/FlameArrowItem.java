package com.snark.saturalanx.items.warfare.incendiary;

import com.dunk.tfc.Core.TFC_Core;
import com.dunk.tfc.api.TFCItems;
import com.snark.saturalanx.core.Config;
import com.snark.saturalanx.core.Util;
import com.snark.saturalanx.items.ItemSL;
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
import static com.snark.saturalanx.core.ItemSetup.fireArrow;

public class FlameArrowItem extends ItemSL {
    private IIcon litIcon;
    private int salvageChance, igniteChance;

    public FlameArrowItem(int dMod, int sMod, int iMod){
        super();
        this.maxStackSize = 1;
        this.setMaxDamage(Config.flameArrowBurnDuration * dMod * 20);
        salvageChance = Config.flameArrowSalvageChance + sMod;
        igniteChance = Config.flameArrowIgniteChance + iMod;
    }

    public int getSalvageChance() {
        return salvageChance;
    }

    public int getIgniteChance() {
        return igniteChance;
    }

    @Override
    public void registerIcons(IIconRegister registerer) {
        this.itemIcon = registerer.registerIcon(WEAPONPATH+"projectiles/"+this.getUnlocalizedName().substring(5));
        this.litIcon = registerer.registerIcon(WEAPONPATH+"projectiles/"+this.getUnlocalizedName().substring(5)+"Lit");
    }

    @Override
    public IIcon getIcon(ItemStack stack, int pass) {
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
            return litIcon;
        else
            return itemIcon;
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
    public boolean canStack() {
        return false;
    }

    @Override
    public void onCreated(ItemStack stack, World world, EntityPlayer player) {
        stack.stackTagCompound = new NBTTagCompound();
        stack.stackTagCompound.setBoolean("lit",false);
        if(stack.getItem() == fireArrow)
            stack.stackTagCompound.setInteger("counter",0);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player){

        if(stack.stackTagCompound == null){
            stack.stackTagCompound = new NBTTagCompound();
            stack.stackTagCompound.setBoolean("lit",false);
            if(stack.getItem() == fireArrow)
                stack.stackTagCompound.setInteger("counter",0);
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
            stack.stackTagCompound.setBoolean("lit",false);
        }
        return stack;
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World world, EntityPlayer player, int inUseCount){

        boolean lit = stack.stackTagCompound.getBoolean("lit");

        if(!lit){
            stack.stackTagCompound.setBoolean("lit",true);
            world.playSoundAtEntity(player,"fire.ignite",1,1);
            if(stack.getItem() == fireArrow) {
                stack.stackTagCompound.setInteger("counter",0);
                world.playSoundAtEntity(player, "saturalanx:fuse", 0.8F, 1);
            }
        }

    }

    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int slot, boolean isHeld){
        if(stack.stackTagCompound!=null&&stack.stackTagCompound.getBoolean("lit")){
            stack.damageItem(1, (EntityLivingBase) entity);
            if(stack.getItem()==fireArrow){
                int i = stack.stackTagCompound.getInteger("counter");
                if (i == 100) {
                    world.playSoundAtEntity(entity, "saturalanx:fuse", 0.8F, 1);
                    i = 0;
                } else
                    i++;
                stack.stackTagCompound.setInteger("counter", i);
            }
            if(stack.getItemDamage()<=0) {
                if (!(entity instanceof EntityPlayer && ((EntityPlayer) entity).capabilities.isCreativeMode)) {
                    stack.stackTagCompound.setBoolean("lit", false);
                    if(entity instanceof EntityPlayer) {
                        ((EntityPlayer) entity).inventory.setInventorySlotContents(slot,null);
                        if(world.rand.nextInt(100) <= this.salvageChance)
                            TFC_Core.giveItemToPlayer(new ItemStack(TFCItems.arrow,1), (EntityPlayer) entity);
                    }
                }
            }
        }
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {

        if(stack.stackTagCompound == null) {
            stack.stackTagCompound = new NBTTagCompound();
            stack.stackTagCompound.setBoolean("lit",false);
        }

        if(!stack.stackTagCompound.getBoolean("lit")&& Util.canBlockLight(x,y,z,world)){
            stack.stackTagCompound.setBoolean("lit",true);
            if(stack.getItem() == fireArrow) {
                stack.stackTagCompound.setInteger("counter",0);
                world.playSoundAtEntity(player, "saturalanx:fuse", 0.8F, 1);
            }
        }

        return false;
    }

}
