package com.snark.saturalanx.items.warfare.gunpowder;

import com.dunk.tfc.TileEntities.TEFirepit;
import com.dunk.tfc.TileEntities.TEForge;
import com.dunk.tfc.api.Enums.EnumItemReach;
import com.dunk.tfc.api.Enums.EnumSize;
import com.dunk.tfc.api.Enums.EnumWeight;
import com.dunk.tfc.api.TFCBlocks;
import com.snark.saturalanx.core.Util;
import com.snark.saturalanx.entities.EntityPotGrenade;
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
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowNockEvent;

import java.util.List;

import static com.snark.saturalanx.core.ItemSetup.WEAPONPATH;

public class PotGrenade extends ItemSatura {

protected IIcon icon2;
protected int type;

    public PotGrenade(){
        super();
        this.stackable = false;
        this.size = EnumSize.SMALL;
        this.weight = EnumWeight.HEAVY;
        this.reach = EnumItemReach.SHORT;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public void registerIcons(IIconRegister registerer) {
        this.itemIcon = registerer.registerIcon(WEAPONPATH + this.getUnlocalizedName().replace("item.","")+ "Lit");
        this.icon2 = registerer.registerIcon(WEAPONPATH + this.getUnlocalizedName().replace("item.",""));
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

        boolean lit = tag.getBoolean("lit");

        if(lit)
            return itemIcon;
        return icon2;
    }

    public void setComponents(ItemStack stack, float e, float sh, float pe, float i,boolean lit){

        NBTTagCompound tag = stack.getTagCompound();

        if(tag == null)
            tag = new NBTTagCompound();

        tag.setFloat("explosive",e);
        tag.setFloat("shrapnel",sh);
        tag.setFloat("pellet",pe);
        tag.setFloat("incendiary",i);
        tag.setBoolean("lit",lit);

        stack.setTagCompound(tag);

    }

    public float[] getComponents(ItemStack stack){

        NBTTagCompound tag = stack.getTagCompound();
        float[] comp = new float[4];

        if(tag == null)
            tag = new NBTTagCompound();
        else {
            comp[0] = tag.getFloat("explosive");
            comp[1] = tag.getFloat("shrapnel");
            comp[2] = tag.getFloat("pellet");
            comp[3] = tag.getFloat("incendiary");
        }

        return comp;
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

        if(!stack.stackTagCompound.getBoolean("lit")&&Util.canBlockLight(x,y,z,world)){
            stack.stackTagCompound.setBoolean("lit",true);
            stack.stackTagCompound.setInteger("counter",100);
        }

        return false;
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
        }
        else if (lit&&!player.isSneaking()) {
            world.playSoundAtEntity(player,"saturalanx:fuse",0.8F,1);
            EntityPotGrenade grenade = new EntityPotGrenade(world,player,getComponents(stack),stack.getItemDamage(),stack.getMaxDamage(),this.getType());
            if(!world.isRemote){
                world.playSoundAtEntity(player,"random.bow",1,1);
                world.spawnEntityInWorld(grenade);
            }
            if(!player.capabilities.isCreativeMode) {
                stack.stackTagCompound.setBoolean("delete",true);
            }
        }
        else if(lit&&player.isSneaking()){
            stack.stackTagCompound.setBoolean("lit",false);
        }
        return stack;
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World world, EntityPlayer player, int inUseCount) {

        boolean lit = stack.stackTagCompound.getBoolean("lit");
        int time = this.getMaxItemUseDuration(stack) - inUseCount;

        if (!lit) {
            stack.stackTagCompound.setBoolean("lit", true);
            stack.stackTagCompound.setInteger("counter",100);
            world.playSoundAtEntity(player, "fire.ignite", 1, 1);
        }

    }

    private int getType(){
        return type;
    }

    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int slot, boolean isHeld){
        if(stack != null) {
            if (stack.stackTagCompound != null && stack.stackTagCompound.getBoolean("lit")) {
                stack.damageItem(1, (EntityLivingBase) entity);
                if (stack.getItemDamage() <= 0) {
                    if (!(entity instanceof EntityPlayer && ((EntityPlayer) entity).capabilities.isCreativeMode)) {
                        EntityPotGrenade grenade = new EntityPotGrenade(world, ((EntityPlayer) entity), this.getComponents(stack), stack.getMaxDamage(), stack.getMaxDamage(), true);
                        grenade.posX = entity.posX;
                        grenade.posZ = entity.posZ;
                        grenade.posY = entity.posY;
                        if (!world.isRemote)
                            world.spawnEntityInWorld(grenade);
                        stack.stackTagCompound.setBoolean("lit", false);
                        if (entity instanceof EntityPlayer)
                            ((EntityPlayer) entity).inventory.setInventorySlotContents(slot, null);
                    }}
                    int i = stack.stackTagCompound.getInteger("counter");
                    if (i == 100) {
                        world.playSoundAtEntity(entity, "saturalanx:fuse", 0.7F, 1);
                        i = 0;
                    } else
                        i++;
                    stack.stackTagCompound.setInteger("counter", i);
                if(stack.stackTagCompound.hasKey("delete"))
                    if(stack.stackTagCompound.getBoolean("delete"))
                        ((EntityPlayer) entity).inventory.setInventorySlotContents(slot, null);
            }
        }
    }

    public void addItemInformation(ItemStack is, EntityPlayer player, List<String> arraylist){
        NBTTagCompound tag = is.getTagCompound();
        if(tag != null){
            float[] comp = this.getComponents(is);

            if(comp[0]>0)
                arraylist.add(EnumChatFormatting.YELLOW+"Explosive: "+comp[0]);
            if(comp[1]>0)
                arraylist.add(EnumChatFormatting.YELLOW+"Shrapnel: "+comp[1]);
            if(comp[2]>0)
                arraylist.add(EnumChatFormatting.YELLOW+"Pellets: "+comp[2]);
            if(comp[3]>0)
                arraylist.add(EnumChatFormatting.YELLOW+"Incendiary: "+comp[3]);
            if(comp[0]+comp[1]+comp[2]+comp[3]==0)
                arraylist.add(EnumChatFormatting.WHITE+"This is an empty pot with a fuse.");
        }
    }

    public IIcon getLitIcon(){
        return this.itemIcon;
    }
}
