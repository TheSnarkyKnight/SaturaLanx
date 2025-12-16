package com.snark.saturalanx.items.weapons.gunpowder;

import com.dunk.tfc.Blocks.Devices.BlockPottery;
import com.dunk.tfc.Core.Player.PlayerManagerTFC;
import com.dunk.tfc.Core.TFC_Core;
import com.dunk.tfc.Items.Tools.ItemCustomSword;
import com.dunk.tfc.TileEntities.TEPottery;
import com.dunk.tfc.api.Enums.EnumDamageType;
import com.dunk.tfc.api.Enums.EnumItemReach;
import com.dunk.tfc.api.Enums.EnumSize;
import com.dunk.tfc.api.Enums.EnumWeight;
import com.dunk.tfc.api.TFCItems;
import com.snark.saturalanx.SaturaLanx;
import com.snark.saturalanx.core.Config;
import com.snark.saturalanx.entities.EntityBullet;
import com.snark.saturalanx.items.weapons.SwordItemSL;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowNockEvent;

import java.util.List;
import java.util.Random;

import static com.snark.saturalanx.SaturaLanx.MODID;
import static com.snark.saturalanx.core.ItemSetup.slowmatch;
import static net.minecraft.init.Items.gunpowder;

public class ArquebusItem extends SwordItemSL {
    int loadingTime,aimingTime,bulletDamage,matchLength;
    public ArquebusItem(Item.ToolMaterial material, float damage){
        super(material,damage);
        this.damageType = EnumDamageType.CRUSHING;
        this.setCreativeTab(SaturaLanx.tab);
        this.setMaxStackSize(1);
        this.loadingTime = Config.arquebusLoadingTime;
        this.aimingTime = Config.arquebusAimingTime;
        this.bulletDamage = Config.arquebusDamage;
        this.matchLength = slowmatch.getMaxDamage();
        this.setAttackSpeed(30);
        this.setCrushDamageShape("3X5_MACE");
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
    public void onCreated(ItemStack stack, World world, EntityPlayer player) {
        stack.stackTagCompound = new NBTTagCompound();
        stack.stackTagCompound.setInteger("match",0);
        stack.stackTagCompound.setBoolean("load",false);
    }

    public boolean onEntitySwing(EntityLivingBase entity, ItemStack is) {
        if (entity instanceof EntityPlayer && PlayerManagerTFC.getInstance().getPlayerInfoFromPlayer((EntityPlayer)entity) != null && PlayerManagerTFC.getInstance().getPlayerInfoFromPlayer((EntityPlayer)entity).canAttack()) {
            if(is.stackTagCompound==null){
                is.stackTagCompound = new NBTTagCompound();
                is.stackTagCompound.setBoolean("load", false);
            }
            if(is.stackTagCompound.getBoolean("load")) {
                is.stackTagCompound.setBoolean("load", false);
                TFC_Core.giveItemToPlayer(new ItemStack(TFCItems.leadBullet),(EntityPlayer) entity);
                TFC_Core.giveItemToPlayer(new ItemStack(gunpowder),(EntityPlayer) entity);
            }
            if (!entity.worldObj.isRemote) {
                MovingObjectPosition mop = this.getMovingObjectPositionFromPlayer(entity.worldObj, (EntityPlayer)entity, true);
                if (mop != null && mop.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
                    if (this instanceof ItemCustomSword) {
                        Block b = entity.worldObj.getBlock(mop.blockX, mop.blockY, mop.blockZ);
                        if (b instanceof BlockPottery) {
                            TileEntity te = entity.worldObj.getTileEntity(mop.blockX, mop.blockY, mop.blockZ);
                            if (te != null && te instanceof TEPottery && ((TEPottery)te).attemptToSmash(mop)) {
                                PlayerManagerTFC.getInstance().getPlayerInfoFromPlayer((EntityPlayer)entity).setAttackTimer(this.getAttackSpeed(entity), this);
                                return true;
                            }
                        }
                    }

                    return false;
                }

                PlayerManagerTFC.getInstance().getPlayerInfoFromPlayer((EntityPlayer)entity).setAttackTimer(this.getAttackSpeed(entity), this);
            }

            return false;
        } else {
            return true;
        }

    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player){

        if(stack.stackTagCompound == null){
            stack.stackTagCompound = new NBTTagCompound();
            stack.stackTagCompound.setBoolean("load",false);
            stack.stackTagCompound.setInteger("match",0);
            stack.stackTagCompound.setLong("time",0);
        }

        boolean load = stack.stackTagCompound.getBoolean("load");
        int match = stack.stackTagCompound.getInteger("match");
        long time = stack.stackTagCompound.getLong("time");

        if((!load&&canLoad(player)||load&&match>0)&&!player.isSneaking()) {
            ArrowNockEvent event = new ArrowNockEvent(player, stack);
            MinecraftForge.EVENT_BUS.post(event);
            if (event.isCanceled()) {
                return event.result;
            }

            player.setItemInUse(stack, this.getMaxItemUseDuration(stack));
        }
        else if(player.isSneaking()){
            if(match>0&&world.getWorldTime()>time){
                int dam = (int) ((matchLength-match)+(world.getWorldTime()-time));
                ItemStack stack1 = new ItemStack(slowmatch,1,dam);
                stack1.stackTagCompound = new NBTTagCompound();
                stack1.stackTagCompound.setBoolean("lit",true);
                stack.stackTagCompound.setInteger("match",0);
                player.inventory.setInventorySlotContents(player.inventory.getFirstEmptyStack(),stack1);
            }
            else{
                for(int i =0;i < 9;i++){
                    if(player.inventory.mainInventory[i]!=null&&player.inventory.mainInventory[i].getItem().equals(slowmatch))
                        if(player.inventory.mainInventory[i].stackTagCompound!=null)
                            if(player.inventory.mainInventory[i].stackTagCompound.getBoolean("lit")){
                                stack.stackTagCompound.setLong("time",world.getWorldTime());
                                stack.stackTagCompound.setInteger("match",matchLength-player.inventory.mainInventory[i].getItemDamage());
                                player.inventory.mainInventory[i] = null;
                                break;
                            }
                }
            }
        }


        return stack;
    }

    @Override
    public IIcon getIconFromDamage(int p_77617_1_) {
        return null;
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World world, EntityPlayer player, int inUseCount){
        NBTTagCompound tag = stack.stackTagCompound;

        boolean load = tag.getBoolean("load");
        int match = tag.getInteger("match");
        long time = tag.getLong("time");
        int useCount = stack.getMaxItemUseDuration()-inUseCount;

        if(load&&match>0&&useCount>this.aimingTime){
            fireGun(stack,player,world);
            tag.setBoolean("load",false);
        }
        if(!load&&useCount>this.loadingTime){
            this.loadGun(stack,player);
        }
    }

    @Override
    public void onUsingTick(ItemStack stack, EntityPlayer player, int count) {
        super.onUsingTick(stack, player, count);

        boolean b;
        if(stack.stackTagCompound.hasKey("s"))
            b = stack.stackTagCompound.getBoolean("s");
        else{
            b = true;
            stack.stackTagCompound.setBoolean("s",true);
        }

        if(stack.getMaxItemUseDuration()-count >= loadingTime && b) {
            player.worldObj.playSoundAtEntity(player, MODID + ":load", 1, 1);
            b = false;
            stack.stackTagCompound.setBoolean("s",false);
        }
    }

    public boolean canLoad(EntityPlayer player){

        if(player.capabilities.isCreativeMode)
            return true;

        if(hasAmmo(player)&&hasRamrod(player))
            return true;
        else
            return false;
    }

    public boolean hasRamrod(EntityPlayer player){

        for(int i =0;i < 9;i++){
            if(player.inventory.mainInventory[i]!=null&&player.inventory.mainInventory[i].getItem().equals(TFCItems.stick))
                return true;
        }

        return false;
    }

    public boolean hasAmmo(EntityPlayer player){

        if((player.inventory.hasItem(TFCItems.leadBullet)||player.inventory.hasItem(TFCItems.looseRock))&&player.inventory.hasItem(gunpowder))
            return true;
        else
            return false;

    }

    public void loadGun(ItemStack stack, EntityPlayer player){
        for(int i = 0;i<36;i++){
            ItemStack s = player.inventory.mainInventory[i];
            if(s != null && s.getItem()==TFCItems.looseRock){
                stack.stackTagCompound.setInteger("ammo",0);
                stack.stackTagCompound.setInteger("ammoIndex",s.getItemDamage());
                s.stackSize--;
                if(s.stackSize<=0)
                    player.inventory.setInventorySlotContents(i,null);
                break;
            }
            else if(s != null && s.getItem() == TFCItems.leadBullet) {
                stack.stackTagCompound.setInteger("ammo", 1);
                s.stackSize--;
                if(s.stackSize<=0)
                    player.inventory.setInventorySlotContents(i,null);
                break;
            }
        }
        player.inventory.consumeInventoryItem(gunpowder);
        stack.stackTagCompound.setBoolean("s",true);
        stack.stackTagCompound.setBoolean("load",true);
    }

    public void fireGun(ItemStack stack, EntityPlayer player, World world){

        int ammo = stack.stackTagCompound.getInteger("ammo");
        float damage = bulletDamage;
        if(ammo == 0) {
            bulletDamage = bulletDamage * 75 / 100;
        }

        EntityBullet bullet = new EntityBullet(world,player,3);
        bullet.setPickupItem(TFCItems.leadBullet);
        if(ammo == 0) {
            bullet.setPickupItem(TFCItems.looseRock);
            bullet.setDamageTaken((short)stack.stackTagCompound.getInteger("ammoIndex"));
        }
        bullet.setDamage(damage);
        bullet.setType(ammo);
        bullet.setShouldPickup((byte)1);

        world.playSoundAtEntity(player,MODID + ":handgonne",1.0F,1.0F);
        //spawnSmoke(player);
        if(!world.isRemote){
            world.spawnEntityInWorld(bullet);
        }
        stack.damageItem(1,player);
        stack.stackTagCompound.setBoolean("load",false);
    }

    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int slot, boolean isHeld) {
        if(stack.stackTagCompound == null){
            stack.stackTagCompound = new NBTTagCompound();
            stack.stackTagCompound.setBoolean("load",false);
            stack.stackTagCompound.setInteger("match",0);
            stack.stackTagCompound.setLong("time",0);
        }
        NBTTagCompound tag = stack.stackTagCompound;
        int match = tag.getInteger("match");
        long time = tag.getLong("time");
        if(world.getWorldTime()>(time+match)){
            stack.stackTagCompound.setInteger("match",0);
        }
    }

    public void addItemInformation(ItemStack is, EntityPlayer player, List<String> arraylist){
        NBTTagCompound tag = is.getTagCompound();
        if(tag != null){
            int match = is.stackTagCompound.getInteger("match");

            if(match<=matchLength&&match>((matchLength/2)+(matchLength/4)))
                arraylist.add(EnumChatFormatting.WHITE+"The match is almost new");
            if(match<=((matchLength/2)+(matchLength/4))&&match>matchLength/2)
                arraylist.add(EnumChatFormatting.WHITE+"There's plenty of match left");
            if(match<=matchLength/2&&match>matchLength/4)
                arraylist.add(EnumChatFormatting.WHITE+"The match is starting to run out");
            if(match<=matchLength/4&&match>0)
                arraylist.add(EnumChatFormatting.WHITE+"The match has almost run out");
            if(match<=0)
                arraylist.add(EnumChatFormatting.WHITE+"There's no match");
        }
    }

    protected void spawnSmoke(EntityPlayer player){
        for(int i = 0;i<5;i++) {
            Random rand = new Random();
            int x = rand.nextInt(2);
            int y = rand.nextInt(2);
            int z = rand.nextInt(2);
            if(rand.nextInt(2)==0)
                x *= -1;
            if(rand.nextInt(2)==0)
                y *= -1;
            if(rand.nextInt(2)==0)
                z *= -1;
            player.worldObj.spawnParticle("smoke", player.posX, player.posY + player.getEyeHeight(), player.posZ, x,y,z);
        }
    }

    @Override
    public EnumSize getSize(ItemStack stack) {
        return EnumSize.LARGE;
    }

    @Override
    public EnumWeight getWeight(ItemStack stack) {
        return EnumWeight.HEAVY;
    }

    @Override
    public EnumItemReach getReach(ItemStack itemStack) {
        return EnumItemReach.MEDIUM;
    }

    @Override
    public boolean canStack() {
        return false;
    }
}
