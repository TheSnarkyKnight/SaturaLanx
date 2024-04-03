package com.snark.saturalanx.entities;

import net.minecraft.entity.Entity;
import net.minecraft.util.EntityDamageSourceIndirect;

public class EntityDamageSourceGrenade extends EntityDamageSourceIndirect {
    private Entity thrower;

    public EntityDamageSourceGrenade(Entity grenade, Entity user){
        super("potGrenade",grenade,user);
        thrower = user;
    }
}
