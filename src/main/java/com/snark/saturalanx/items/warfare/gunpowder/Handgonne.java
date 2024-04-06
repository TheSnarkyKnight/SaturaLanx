package com.snark.saturalanx.items.warfare.gunpowder;

import com.dunk.tfc.Blocks.Devices.BlockPottery;
import com.dunk.tfc.Core.Player.PlayerManagerTFC;
import com.dunk.tfc.Core.TFC_Core;
import com.dunk.tfc.Entities.EntitySlingStone;
import com.dunk.tfc.Items.Tools.ItemCustomSword;
import com.dunk.tfc.TileEntities.TEPottery;
import com.dunk.tfc.api.Enums.EnumDamageType;
import com.dunk.tfc.api.Enums.EnumItemReach;
import com.dunk.tfc.api.Enums.EnumSize;
import com.dunk.tfc.api.Enums.EnumWeight;
import com.dunk.tfc.api.TFCBlocks;
import com.dunk.tfc.api.TFCItems;
import com.snark.saturalanx.SaturaLanx;
import com.snark.saturalanx.core.Config;
import com.snark.saturalanx.entities.EntityBullet;
import com.snark.saturalanx.items.warfare.SwordSatura;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowNockEvent;

import static com.snark.saturalanx.SaturaLanx.MODID;
import static net.minecraft.init.Items.gunpowder;

public class Handgonne extends SwordSatura {

    int loadingTime;
    int aimingTime;
    int bulletDamage;

    public Handgonne(Item.ToolMaterial material, float damage){
        super(material,damage);
        this.damageType = EnumDamageType.CRUSHING;
        this.setCreativeTab(SaturaLanx.tab);
        this.setMaxStackSize(1);
        this.loadingTime = Config.handgonneLoadingTime;
        this.aimingTime = Config.handgonneAimingTime;
        this.bulletDamage = Config.handgonneDamage;
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
    public void onCreated(ItemStack stack, World world, EntityPlayer player){
        stack.stackTagCompound = new NBTTagCompound();
        stack.stackTagCompound.setBoolean("load", false);
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
    public void registerIcons(IIconRegister registerer) {
        this.itemIcon = registerer.registerIcon(MODID+":/"+this.getUnlocalizedName().replace("item.",""));
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player){

        if(stack.stackTagCompound == null){
            stack.stackTagCompound = new NBTTagCompound();
            stack.stackTagCompound.setBoolean("load",false);
        }

        boolean state = stack.stackTagCompound.getBoolean("load");

            if((!state&&canLoad(player))||(state&&canFire(player))){

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

        stack.stackTagCompound.setBoolean("s",true);
        boolean state = stack.stackTagCompound.getBoolean("load");
        int elapsedTime = stack.getMaxItemUseDuration()-inUseCount;

        if(!state&&elapsedTime>=loadingTime){
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
            stack.stackTagCompound.setBoolean("load",true);

        }
        else if(state&&(elapsedTime>=aimingTime))
            fireGun(stack,player,world);
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

    public boolean canFire(EntityPlayer player){

        if(player.capabilities.isCreativeMode)
            return true;

        for(int i = 0;i < 9;i++){

            if(player.inventory.mainInventory[i]!=null&&player.inventory.mainInventory[i].getItem().equals(Item.getItemFromBlock(TFCBlocks.torch)))
                return true;

        }

        return false;
    }

    public void fireGun(ItemStack stack, EntityPlayer player, World world){

        int ammo = stack.stackTagCompound.getInteger("ammo");
        float damage = bulletDamage;
        if(ammo == 0) {
            bulletDamage = bulletDamage * 75 / 100;
        }

        EntityBullet bullet = new EntityBullet(world,player,2.5F);
        bullet.setPickupItem(TFCItems.leadBullet);
        if(ammo == 0) {
            bullet.setPickupItem(TFCItems.looseRock);
            bullet.setDamageTaken((short)stack.stackTagCompound.getInteger("ammoIndex"));
        }
        bullet.setShouldPickup((byte)1);
        bullet.setDamage(damage);
        bullet.setType(ammo);

        world.playSoundAtEntity(player,MODID + ":handgonne",1.0F,1.0F);
        world.spawnParticle("smoke",0.2,0.2,0.2,0.2,0.2,0.2);
        if(!world.isRemote){
            world.spawnEntityInWorld(bullet);
        }
        stack.damageItem(1,player);
        stack.stackTagCompound.setBoolean("load",false);
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
