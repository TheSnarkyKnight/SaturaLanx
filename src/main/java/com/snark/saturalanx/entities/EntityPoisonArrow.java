package com.snark.saturalanx.entities;

import com.dunk.tfc.Entities.EntityProjectileTFC;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityPoisonArrow extends EntityProjectileTFC {
    private NBTTagCompound tag;

    public EntityPoisonArrow(World world){
        super(world);
    }

    public EntityPoisonArrow(World world, EntityLivingBase shooter, float force,NBTTagCompound tag){
        super(world,shooter,force);

        this.tag = tag;
    }

    public NBTTagCompound getTag() {
        return tag;
    }
}
