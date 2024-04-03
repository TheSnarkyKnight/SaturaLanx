package com.snark.saturalanx.items.tools;

import com.dunk.tfc.api.Enums.EnumItemReach;
import com.dunk.tfc.api.Enums.EnumSize;
import com.dunk.tfc.api.Enums.EnumWeight;
import com.dunk.tfc.api.TFCBlocks;
import com.snark.saturalanx.entities.EntityFireCracker;
import com.snark.saturalanx.items.ItemSatura;
import net.minecraft.client.renderer.texture.IIconRegister;
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

public class Firecracker extends ItemSatura {
    int num;

    public Firecracker(int i){
        super();
        this.num = i;
        this.maxStackSize = 16;
    }

    @Override
    public void registerIcons(IIconRegister registerer) {
        this.itemIcon = registerer.registerIcon(MODID +":tools/"+this.getUnlocalizedName().replace("item.",""));
    }

    @Override
    public boolean canStack() {
        return true;
    }

    @Override
    public EnumSize getSize(ItemStack is) {
        return EnumSize.SMALL;
    }

    @Override
    public EnumWeight getWeight(ItemStack is) {
        return EnumWeight.LIGHT;
    }

    @Override
    public EnumItemReach getReach(ItemStack is) {
        return EnumItemReach.SHORT;
    }

    @Override
    public int getItemStackLimit(ItemStack is) {
        return 16;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player){

        if(canLight(player)){
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
    public void onPlayerStoppedUsing(ItemStack stack, World world, EntityPlayer player, int inUseCount) {

        world.playSoundAtEntity(player, "fire.ignite", 1, 1);
        world.playSoundAtEntity(player,"saturalanx:fuse",0.8F,1);
        EntityFireCracker f = new EntityFireCracker(world,player,num);
        if(!world.isRemote){
            world.playSoundAtEntity(player,"random.bow",1,1);
            world.spawnEntityInWorld(f);
        }
        if(!player.capabilities.isCreativeMode){
            stack.stackSize--;
            if(stack.stackSize<=0)
                player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
        }

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
    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.bow;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return 72000;
    }

    @Override
    public IIcon getIconIndex(ItemStack p_77650_1_) {
        return itemIcon;
    }

    @Override
    public IIcon getIconFromDamage(int i) {
        return itemIcon;
    }

    @Override
    public IIcon getIcon(ItemStack stack, int pass) {
        return itemIcon;
    }
}
