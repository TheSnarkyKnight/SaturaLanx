package com.snark.saturalanx.items.warfare.gunpowder;

import com.dunk.tfc.TileEntities.TEFirepit;
import com.dunk.tfc.TileEntities.TEForge;
import com.dunk.tfc.api.Enums.EnumSize;
import com.dunk.tfc.api.Enums.EnumWeight;
import com.dunk.tfc.api.TFCBlocks;
import com.snark.saturalanx.core.Config;
import com.snark.saturalanx.entities.EntityIncendiaryPot;
import com.snark.saturalanx.items.ItemSatura;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowNockEvent;

import static com.snark.saturalanx.SaturaLanx.MODID;
import static com.snark.saturalanx.SaturaLanx.tab;

public class Slowmatch extends ItemSatura {
    private IIcon litIcon;
    public Slowmatch(){
        this.setCreativeTab(tab);
        this.setSize(EnumSize.SMALL);
        this.setWeight(EnumWeight.LIGHT);
        this.setMaxStackSize(1);
        this.setMaxDamage(Config.slowmatchLength);
    }

    @Override
    public void registerIcons(IIconRegister registerer) {
        this.itemIcon = registerer.registerIcon(MODID + ":Slowmatch");
        this.litIcon = registerer.registerIcon(MODID + ":SlowmatchLit");
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
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player){

        if(stack.stackTagCompound == null){
            stack.stackTagCompound = new NBTTagCompound();
            stack.stackTagCompound.setBoolean("lit",false);
        }

        boolean lit = stack.stackTagCompound.getBoolean("lit");

        if(!lit&&canLight(player)){
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

    public boolean canLight(EntityPlayer player){
        if(player.capabilities.isCreativeMode)
            return true;

        for(int i=0;i<9;i++){
            if(player.inventory.mainInventory[i]!=null&&player.inventory.mainInventory[i].getItem().equals(Item.getItemFromBlock(TFCBlocks.torch)))
                return true;
        }

        return false;
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World world, EntityPlayer player, int inUseCount){

        boolean lit = stack.stackTagCompound.getBoolean("lit");

        if(!lit){
            stack.stackTagCompound.setBoolean("lit",true);
            world.playSoundAtEntity(player,"fire.ignite",1,1);
        }

    }

    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int slot, boolean isHeld){
        if(stack.stackTagCompound!=null&&stack.stackTagCompound.getBoolean("lit")){
            stack.damageItem(1, (EntityLivingBase) entity);
            if(stack.getItemDamage()<=0) {
                if (!(entity instanceof EntityPlayer && ((EntityPlayer) entity).capabilities.isCreativeMode)) {
                    stack.stackTagCompound.setBoolean("lit", false);
                    if(entity instanceof EntityPlayer)
                        ((EntityPlayer) entity).inventory.setInventorySlotContents(slot,null);
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

        if(!stack.stackTagCompound.getBoolean("lit")&&canBlockLight(x,y,z,world)){
            stack.stackTagCompound.setBoolean("lit",true);
        }

        return false;
    }

    public boolean canBlockLight(int x, int y, int z, World w){
        Block b = w.getBlock(x,y,z);
        if(b == TFCBlocks.torch)
            return true;
        if(b == TFCBlocks.candle)
            return true;
        if(b == TFCBlocks.candleBrass)
            return true;
        if(b == TFCBlocks.candleGold)
            return true;
        if(b == TFCBlocks.candlePewter)
            return true;
        if(b == TFCBlocks.candleSilver)
            return true;
        if(b == TFCBlocks.firepit) {
            TEFirepit f = (TEFirepit) w.getTileEntity(x, y, z);
            if (f != null && f.fireTemp > 1)
                return true;
        }
        if(b == TFCBlocks.forge){
            TEForge f = (TEForge) w.getTileEntity(x,y,z);
            if(f != null && f.fireTemp > 1)
                return true;
        }

        return false;

    }


}
