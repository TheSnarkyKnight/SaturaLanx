package com.snark.saturalanx.entities;

import com.dunk.tfc.Entities.EntityProjectileTFC;
import com.dunk.tfc.api.TFCItems;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.world.World;

public class EntityShrapnel extends EntityProjectileTFC {

    public EntityShrapnel(World world){
        super(world);
    }
    public EntityShrapnel(World world,double x, double y, double z) {
        super(world,x,y,z);
        this.setDamage(10);
        this.setKnockbackStrength(0);
        this.pickupItem = null;
        this.setPierceDamageShape("1x1");
    }

}
