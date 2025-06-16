package com.snark.saturalanx.entities;

import com.dunk.tfc.Entities.EntityProjectileTFC;
import com.snark.saturalanx.core.Util;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

public class EntityFlameArrow extends EntityProjectileTFC {
    private int iChance;

    public EntityFlameArrow(World world) {
        super(world);
    }

    public EntityFlameArrow(World world, EntityLivingBase shooter, float force, int duration, int iChance) {
        super(world, shooter, force);
        this.setFire(duration*20);
        this.iChance = iChance;
        this.canBePickedUp = 2;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        if(!this.isAirBorne && this.isBurning() && worldObj.rand.nextInt(8) < 2){
            if((this.worldObj.getTotalWorldTime()%100)==0 && this.worldObj.rand.nextInt(100)<=this.iChance)
                Util.burnSurroundings(worldObj,(int) posX,(int) posY,(int) posZ,0,0,0);
        }


    }
}
