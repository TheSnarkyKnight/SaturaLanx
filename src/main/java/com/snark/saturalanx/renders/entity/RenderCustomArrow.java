package com.snark.saturalanx.renders.entity;

import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.util.ResourceLocation;

import static com.snark.saturalanx.SaturaLanx.MODID;

public class RenderCustomArrow extends RenderArrow {
    ResourceLocation res;
    public RenderCustomArrow(String name){
        super();
        res = new ResourceLocation(MODID +":textures/entities/"+name+".png");
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityArrow p_getEntityTexture_1_) {
        return res;
    }
}
