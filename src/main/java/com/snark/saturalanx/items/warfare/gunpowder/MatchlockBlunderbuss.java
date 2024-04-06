package com.snark.saturalanx.items.warfare.gunpowder;

import com.dunk.tfc.api.TFCItems;
import com.snark.saturalanx.core.Config;
import com.snark.saturalanx.core.ItemSetup;
import com.snark.saturalanx.entities.EntityBullet;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import static com.snark.saturalanx.SaturaLanx.MODID;
import static net.minecraft.init.Items.*;

public class MatchlockBlunderbuss extends Arquebus{
    private int pellets;
    public MatchlockBlunderbuss(ToolMaterial material, float damage) {
        super(material, damage);
        this.loadingTime = Config.mBlunderbussLoadingTime;
        this.aimingTime = Config.mBlunderbussAimingTime;
        this.bulletDamage = Config.mBlunderbussDamage;
        this.pellets = Config.mBlunderbussPellets;
    }

    @Override
    public boolean hasAmmo(EntityPlayer player) {
        return player.inventory.hasItem(gunpowder)&&player.inventory.hasItem(ItemSetup.shot)&&(player.inventory.hasItem(TFCItems.straw)||player.inventory.hasItem(paper));
    }

    @Override
    public void loadGun(ItemStack stack, EntityPlayer player) {
        for(int i = 0;i<36;i++){
            ItemStack s = player.inventory.mainInventory[i];
            if(s != null && s.getItem()== ItemSetup.shot){
                stack.stackTagCompound.setInteger("ammo",s.getItemDamage());
                s.stackSize--;
                if(s.stackSize<=0)
                    player.inventory.setInventorySlotContents(i,null);
                break;
            }
            else if(s != null && s.getItem() == TFCItems.powder && s.getItemDamage() == 9) {
                stack.stackTagCompound.setInteger("ammo", 2);
                s.stackSize--;
                if(s.stackSize<=0)
                    player.inventory.setInventorySlotContents(i,null);
                break;
            }
        }
        for(int i = 0;i<36;i++){
            ItemStack s = player.inventory.mainInventory[i];
            if(s != null && s.getItem()== paper){
                stack.stackTagCompound.setInteger("wadding",1);
                if(s.stackSize--<=0)
                    player.inventory.setInventorySlotContents(i,null);
                break;
            }
            else if(s != null && s.getItem() == TFCItems.straw) {
                stack.stackTagCompound.setInteger("wadding", 0);
                if(s.stackSize--<=0)
                    player.inventory.setInventorySlotContents(i,null);
                break;
            }
        }
        player.inventory.consumeInventoryItem(gunpowder);
        stack.stackTagCompound.setBoolean("s",true);
        stack.stackTagCompound.setBoolean("load",true);
    }

    @Override
    public void fireGun(ItemStack stack, EntityPlayer player, World world) {
        int f = 2+stack.stackTagCompound.getInteger("wadding");
        int ammo = stack.stackTagCompound.getInteger("ammo");
        float damage = bulletDamage;
        switch (ammo){
            case 0:
                damage = damage * 75 / 100;
                break;
            case 1:
                break;
            case 2:
                damage = 2;
                break;
        }
        for(int i = 0;i<pellets;i++){

            Vec3 vecDir = player.getLookVec().addVector(player.getRNG().nextGaussian()*.1,player.getRNG().nextGaussian()*.1,player.getRNG().nextGaussian()*.1);
            EntityBullet bullet = new EntityBullet(world,player,f);
            bullet.setLocationAndAngles(player.posX+(vecDir.xCoord*3),player.posY+player.getEyeHeight()+(vecDir.yCoord*3),player.posZ+(vecDir.zCoord*3),player.rotationYaw,player.rotationPitch);
            bullet.setPosition(bullet.posX,bullet.posY,bullet.posZ);
            bullet.motionX = vecDir.xCoord;
            bullet.motionY = vecDir.yCoord;
            bullet.motionZ = vecDir.zCoord;
            bullet.setDamage(damage);
            bullet.setShouldPickup((byte)0);
            bullet.setType(2+ammo);
            if(!world.isRemote)
                world.spawnEntityInWorld(bullet);

        }
        //spawnSmoke(player);
        world.playSoundAtEntity(player,MODID+":handgonne",1,1);
        stack.stackTagCompound.setBoolean("load",false);
        stack.damageItem(1,player);
    }
}
