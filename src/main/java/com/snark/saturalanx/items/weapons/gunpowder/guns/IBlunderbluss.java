package com.snark.saturalanx.items.weapons.gunpowder.guns;

import com.dunk.tfc.api.Constant.Global;
import com.dunk.tfc.api.Crafting.AnvilManager;
import com.dunk.tfc.api.TFCItems;
import com.snark.saturalanx.API.ammo.AmmoData;
import com.snark.saturalanx.SaturaLanx;
import com.snark.saturalanx.core.ItemSetup;
import com.snark.saturalanx.entities.EntityBullet;
import com.snark.saturalanx.items.weapons.gunpowder.PaperCartridgeItem;
import com.snark.saturalanx.items.tools.PowderFlaskItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import static com.snark.saturalanx.SaturaLanx.MODID;

public interface IBlunderbluss {

    public default void loadBlunderbluss(EntityPlayer player, ItemStack itemStack){
        int ammoPosition = itemStack.stackTagCompound.getInteger("ammoPosition");
        int powderPosition = itemStack.stackTagCompound.getInteger("powderPosition");
        int waddingPosition = itemStack.stackTagCompound.getInteger("waddingPosition");
        boolean cartridge = false;

        //Ammo handling
        if(ammoPosition >= 0){
            ItemStack is = player.inventory.mainInventory[ammoPosition];

            if(isValidBlunderbussAmmo(is)){

                NBTTagCompound itemTag = new NBTTagCompound();
                is.writeToNBT(itemTag);
                itemStack.stackTagCompound.setTag("ammoItem", itemTag);

                itemStack.stackTagCompound.setInteger("ammoPosition",-2);

                if(is.getItem() instanceof PaperCartridgeItem){
                    itemStack.stackTagCompound.setInteger("powder",1);
                    itemStack.stackTagCompound.setInteger("powderPosition",-2);
                    itemStack.stackTagCompound.setInteger("wadding",1);
                    itemStack.stackTagCompound.setInteger("waddingPosition",-2);
                    cartridge = true;
                }

                if(!player.capabilities.isCreativeMode){
                    is.stackSize--;
                    if(is.stackSize <= 0)
                        player.inventory.setInventorySlotContents(ammoPosition,null);
                }
            }
        }

        //Powder handling
        if(powderPosition >= 0){
            ItemStack is = player.inventory.mainInventory[powderPosition];

            if(is.getItem() == Items.gunpowder && !cartridge){

                itemStack.stackTagCompound.setInteger("powder",1);
                itemStack.stackTagCompound.setInteger("powderPosition",-2);

                if(!player.capabilities.isCreativeMode){
                    is.stackSize--;
                    if(is.stackSize <= 0)
                        player.inventory.setInventorySlotContents(powderPosition,null);
                }

            }
            else if(is.getItem() instanceof PowderFlaskItem && is.stackTagCompound != null && is.stackTagCompound.getInteger("powder") > 0){

                itemStack.stackTagCompound.setInteger("powder",1);
                itemStack.stackTagCompound.setInteger("powderPosition",-2);

                ((PowderFlaskItem)is.getItem()).decreasePowderCounter(is,1);
            }
        }

        //Wadding handling
        if(waddingPosition >= 0 && !cartridge){
            ItemStack is = player.inventory.mainInventory[waddingPosition];

            if(is.getItem() == TFCItems.straw){
                 itemStack.stackTagCompound.setInteger("wadding",0);
                 itemStack.stackTagCompound.setInteger("waddingPosition",-2);
            }
            else if(is.getItem() == Items.paper || is.getItem() == TFCItems.repairPatch || is.getItem() == TFCItems.sackPiece){
                itemStack.stackTagCompound.setInteger("wadding",1);
                itemStack.stackTagCompound.setInteger("waddingPosition",-2);
            }

            if(!player.capabilities.isCreativeMode){
                is.stackSize--;
                if(is.stackSize <= 0)
                    player.inventory.setInventorySlotContents(waddingPosition,null);
            }
        }

        //Load finalization
        if(itemStack.stackTagCompound.getInteger("ammoPosition")==-2&&
                itemStack.stackTagCompound.getInteger("powderPosition")==-2&&
                itemStack.stackTagCompound.getInteger("waddingPosition")==-2){

            itemStack.stackTagCompound.setBoolean("loaded",true);
        }
        else{
            SaturaLanx.log.error("Something went wrong in the loadGun process, resetting NBT. Notify this to the mod author.");
            resetNBT(itemStack);
        }
    }

    default void resetNBT(ItemStack stack){

        NBTTagCompound tag = stack.getTagCompound();
        if(tag == null)
            tag = new NBTTagCompound();

        tag.setTag("ammoItem",stack.writeToNBT(new NBTTagCompound()));
        tag.setInteger("ammoPosition",-1);
        tag.setInteger("powder",-1);
        tag.setInteger("powderPosition",-1);
        tag.setInteger("wadding",-1);
        tag.setInteger("waddingPosition",-1);
        tag.setBoolean("loaded",false);
        tag.setBoolean("sound",true);

        stack.stackTagCompound = tag;
    }

    default void fireBlunderbuss(EntityPlayer player, ItemStack itemStack, World world, int bulletDamage, int bulletNumber){

        NBTTagCompound itemTag = itemStack.stackTagCompound.getCompoundTag("ammoItem");

        float wadding = itemStack.stackTagCompound.getInteger("wadding") == 1 ? 1.1F : 1;

        //Resetting NBT as the gun is fired
        //itemStack.stackTagCompound.setTag("ammoItem",itemStack.writeToNBT(new NBTTagCompound()));
        itemStack.stackTagCompound.setInteger("ammoPosition",-1);
        itemStack.stackTagCompound.setInteger("powder",-1);
        itemStack.stackTagCompound.setInteger("powderPosition",-1);
        itemStack.stackTagCompound.setInteger("wadding",-1);
        itemStack.stackTagCompound.setInteger("waddingPosition",-1);
        itemStack.stackTagCompound.setBoolean("loaded",false);

        ItemStack ammoItem = ItemStack.loadItemStackFromNBT(itemTag);
        if(ammoItem.getItem() instanceof PaperCartridgeItem)
            ammoItem = new ItemStack(((PaperCartridgeItem) ammoItem.getItem()).getContainedItem(),1,ammoItem.getItemDamage());
        AmmoData ammoData = getBlunderbussAmmoData(ammoItem);

        for(int i = 0;i<bulletNumber;i++){
            Vec3 vecDir = player.getLookVec().addVector(player.getRNG().nextGaussian() * .1, player.getRNG().nextGaussian() * .1, player.getRNG().nextGaussian() * .1);
            EntityBullet bullet = new EntityBullet(world, player, 2.5F * (1 + (wadding / 100F)));
            bullet.setLocationAndAngles(player.posX + (vecDir.xCoord * 3), player.posY + player.getEyeHeight() + (vecDir.yCoord * 3), player.posZ + (vecDir.zCoord * 3), player.rotationYaw, player.rotationPitch);
            bullet.setPosition(bullet.posX, bullet.posY, bullet.posZ);
            bullet.motionX = vecDir.xCoord;
            bullet.motionY = vecDir.yCoord;
            bullet.motionZ = vecDir.zCoord;
            if(ammoData.getMaterial() == Global.UNKNOWN)
                bullet.setShouldPickup((byte) 0);
            else
                bullet.setShouldPickup((byte) 1);
            bullet.setPickupItem(ammoItem.getItem());
            bullet.setDamageTaken((short) ammoItem.getItemDamage());
            float damage = bulletDamage * ammoData.getDamageModifier();
            float extraDamage = (bulletDamage * ammoData.getDamageModifier()) * AnvilManager.getDamageBuff(itemStack);
            bullet.setDamage(damage + extraDamage);

            if(!world.isRemote){
                world.spawnEntityInWorld(bullet);
            }
        }

        world.playSoundAtEntity(player,MODID + ":handgonne",1.0F,1.0F);
        spawnBSmoke(player);
        if(!player.capabilities.isCreativeMode)
            itemStack.damageItem(1,player);

    }

    default void spawnBSmoke(EntityPlayer player) {
        for(int i = 0;i<15;i++) {
            int x = Math.random() < 0.5 ? 1 : -1;
            int z = Math.random() < 0.5 ? 1 : -1;
            Vec3 vecDir = player.getLookVec().addVector(player.getRNG().nextGaussian()*0.2,player.getRNG().nextGaussian()*0.2,player.getRNG().nextGaussian()*0.2);
            player.worldObj.spawnParticle("smoke", player.posX + vecDir.xCoord, player.posY + vecDir.yCoord + player.getEyeHeight(), player.posZ + vecDir.zCoord, 0.025*x,0.025,0.025*z);
        }
    }

    default boolean isValidBlunderbussAmmo(ItemStack is){
        return getBlunderbussAmmoData(is) != null;
    }

    default AmmoData getBlunderbussAmmoData(ItemStack is){
        return ItemSetup.shotRegistry.getAmmoDataFromItemStack(is);
    }

    default boolean hasBWadding(EntityPlayer player, ItemStack itemStack){

        //Paper cartridge handling
        int ammoPos = itemStack.stackTagCompound.getInteger("ammoPosition");
        if(ammoPos >= 0 && player.inventory.mainInventory[ammoPos].getItem() instanceof PaperCartridgeItem) {
            itemStack.stackTagCompound.setInteger("waddingPosition",ammoPos);
            return true;
        }

        for(int i = 0; i < player.inventory.mainInventory.length; i++){
            ItemStack is = player.inventory.mainInventory[i];
            if(is != null && (is.getItem() == Items.paper || is.getItem() == TFCItems.straw || is.getItem() == TFCItems.repairPatch || is.getItem() == TFCItems.sackPiece)){
                itemStack.stackTagCompound.setInteger("waddingPosition",i);
                return true;
            }
        }

        return false;
    }
}
