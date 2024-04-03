package com.snark.saturalanx.entities;

import com.dunk.tfc.Entities.EntityProjectileTFC;
import com.dunk.tfc.Entities.EntitySlingStone;
import com.dunk.tfc.api.Enums.EnumDamageType;
import com.dunk.tfc.api.Interfaces.ICausesDamage;
import com.snark.saturalanx.core.Config;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

public class EntityBolas extends EntitySlingStone {
    public int chance = 0;
    public int duration = 0;
    public int amplifier = 0;

    public EntityBolas(World par1World) {
        super(par1World);

    }

    public EntityBolas(World par1World, double i, double j, double k) {
        super(par1World, i, j, k);
    }

    public EntityBolas(World world, EntityLivingBase shooter, EntityLivingBase victim, float force, float forceVariation) {
        super(world, shooter, victim, force, forceVariation);
    }

    public EntityBolas(World par1World, EntityLivingBase shooter, float force) {
        super(par1World, shooter, force);
    }

    public EntityBolas(World par1World, EntityLivingBase shooter, float force, int chance) {
        super(par1World, shooter, force);
        this.chance = chance;
        this.duration = Config.bolasDuration;
        this.amplifier = Config.bolasAmplifier;
        if(force<0.5F&&amplifier>1)
            amplifier--;
        else if (force>1.0F&&amplifier<5) {
            amplifier++;
        }
    }

    public EnumDamageType getDamageType(EntityLivingBase is) {
        return EnumDamageType.CRUSHING;
    }

}
