package com.snark.saturalanx.renders.entity;

import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.util.ResourceLocation;

import static com.snark.saturalanx.SaturaLanx.MODID;

public class RenderShrapnel extends RenderArrow {
    ResourceLocation res= new ResourceLocation(MODID +":textures/entities/Shrapnel.png");
    public RenderShrapnel(){
        super();
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityArrow p_getEntityTexture_1_) {
        return res;
    }
}
