package com.snark.saturalanx.entities;

import com.dunk.tfc.Core.TFC_Core;
import com.dunk.tfc.Entities.EntityProjectileTFC;
import com.snark.saturalanx.core.Config;
import com.snark.saturalanx.core.Util;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

public class EntityFlameArrow extends EntityProjectileTFC {

    private int type;
    private int iChance;
    private int duration;
    private int burnRadius;

    public EntityFlameArrow(World world) {
        super(world);
    }

    public EntityFlameArrow(World world, EntityLivingBase shooter, float force, int duration, int iChance) {
        super(world, shooter, force);
        this.duration = duration*20;
        this.setFire(this.duration);
        this.iChance = iChance;
        this.canBePickedUp = 2;
        if(iChance== Config.flameArrowIgniteChance+30)
            burnRadius=1;
        else
            burnRadius=0;
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(30,0);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound nbt) {
        super.readEntityFromNBT(nbt);
        if(nbt.hasKey("btype")){
            this.dataWatcher.updateObject(30, nbt.getInteger("btype"));
            this.type = nbt.getInteger("btype");
        }
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound nbt) {
        super.writeEntityToNBT(nbt);
        nbt.setInteger("btype", this.dataWatcher.getWatchableObjectInt(30));
    }

    public void setType(int i){
        this.type = i;
        this.dataWatcher.updateObject(30,i);
    }

    public int getType(){
        return this.dataWatcher.getWatchableObjectInt(30);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        if(this.getType()==3) {
            this.duration--;

            if(!this.isBurning()&&!this.isInWater())
                this.setFire(duration);

            if(this.isBurning()){
                if (Config.fireArrowIgnitesEntities && worldObj.getTotalWorldTime() % (Config.fireArrowIgniteEntityInterval * 20L) == 0) {
                    AxisAlignedBB box = this.boundingBox.expand(Config.fireArrowIgniteAreaOfEffect, Config.fireArrowIgniteAreaOfEffect, Config.fireArrowIgniteAreaOfEffect);
                    List<Entity> entities = worldObj.getEntitiesWithinAABB(EntityLivingBase.class, box);

                    for (Entity ent : entities) {
                        if (ent instanceof EntityLivingBase && worldObj.rand.nextInt(100) < Config.fireArrowIgniteEntityChance)
                            ent.setFire((worldObj.rand.nextInt(4)+2) * 20);
                    }
                }

                if (Config.fireArrowGasEffects > 0 && worldObj.getTotalWorldTime() % (Config.fireArrowGasEffectsInterval * 20L) == 0) {
                    AxisAlignedBB box = this.boundingBox.expand(Config.fireArrowGasAreaOfEffect, Config.fireArrowGasAreaOfEffect, Config.fireArrowGasAreaOfEffect);
                    List<Entity> entities = worldObj.getEntitiesWithinAABB(EntityLivingBase.class, box);

                    for (Entity ent : entities) {
                        if (ent instanceof EntityLivingBase && worldObj.rand.nextInt(100) < Config.fireArrowGasEffectsChance) {

                            int p;
                            if (Config.fireArrowGasEffects == 4)
                                p = worldObj.rand.nextInt(2) + 1;
                            else
                                p = Config.fireArrowGasEffects;

                            switch (p) {
                                case 1:
                                    p = Potion.confusion.getId();
                                    break;
                                case 2:
                                    p = Potion.blindness.getId();
                                    break;
                                case 3:
                                    p = Potion.poison.getId();
                                    break;
                            }

                            if(!(ent instanceof EntityPlayer&&((EntityPlayer) ent).capabilities.isCreativeMode))
                                ((EntityLivingBase) ent).addPotionEffect(new PotionEffect(p, (worldObj.rand.nextInt(Config.fireArrowGasEffectsInterval)+2) * 20, 0));
                        }
                    }
                }

                int n = Math.max((int) Math.ceil(Config.fireArrowGasAreaOfEffect*10),20);
                for (int i = 0; i < n; i++) {
                        double dx = posX + (worldObj.rand.nextInt(Config.fireArrowGasAreaOfEffect+2) - (0.6D + (double) Config.fireArrowGasAreaOfEffect /2)) + (worldObj.rand.nextDouble()- 0.5D);
                        double dz = posZ + (worldObj.rand.nextInt(Config.fireArrowGasAreaOfEffect+2) - (0.6D + (double) Config.fireArrowGasAreaOfEffect /2)) + (worldObj.rand.nextDouble()- 0.5D);
                        double dy = posY + (worldObj.rand.nextInt(Config.fireArrowGasAreaOfEffect+2) - (0.6D + (double) Config.fireArrowGasAreaOfEffect /2)) + (worldObj.rand.nextDouble()- 0.5D);
                        this.worldObj.spawnParticle("smoke", dx, dy, dz, 0, 0, 0);
                }

                if(worldObj.getTotalWorldTime()%10==0) {
                    int m = worldObj.rand.nextInt(5) + 1;
                    for (int i = 0; i < m;i++) {
                        worldObj.spawnParticle("lava", posX, posY, posZ, 0.0D, 0.0D, 0.0D);
                    }
                }

                if(worldObj.rand.nextInt(100)==0)
                    worldObj.playSound(posX+rand.nextFloat(), posY, posZ+rand.nextFloat(), "liquid.lavapop", 0.2F + rand.nextFloat() * 0.2F, 0.9F + rand.nextFloat() * 0.15F, false);

                if(duration%100==0&&duration>=100)
                    worldObj.playSoundAtEntity(this,"saturalanx:fuse",1F,1);
            }
        }
        if(this.isBurning()){
            for (int i = 0; i < 5; i++) {
                double dx = posX + worldObj.rand.nextGaussian()*0.2;
                double dy = posY + Double.max(worldObj.rand.nextGaussian()*0.2,-0.1);
                double dz = posZ + worldObj.rand.nextGaussian()*0.2;
                this.worldObj.spawnParticle("smoke", dx, dy, dz, 0, 0, 0);
            }
            this.worldObj.spawnParticle("flame",posX,posY+0.1,posZ,0,0,0);
        }
        if (!this.isAirBorne && this.isBurning() && worldObj.rand.nextInt(8) < 2) {
                if ((this.worldObj.getTotalWorldTime() % 100) == 0 && this.worldObj.rand.nextInt(100) <= this.iChance)
                    Util.burnSurroundings(worldObj, (int) posX, (int) posY, (int) posZ, burnRadius, burnRadius, burnRadius);
        }

    }
}
