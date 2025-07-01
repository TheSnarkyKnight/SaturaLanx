package com.snark.saturalanx.renders.entity;

import com.snark.saturalanx.entities.EntityFlameArrow;
import com.snark.saturalanx.entities.EntityShrapnel;
import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

import static com.snark.saturalanx.SaturaLanx.MODID;

public class RenderCustomArrow extends RenderArrow {

    ResourceLocation[] res;

    public RenderCustomArrow(String[] name){
        super();

        res = new ResourceLocation[name.length];

        for(int i = 0;i<name.length;i++){
            res[i] = new ResourceLocation(MODID +":textures/entities/"+name[i]+".png");
        }
    }

    public RenderCustomArrow(String name){
        super();

        res = new ResourceLocation[1];
        
        res[0] = new ResourceLocation(MODID +":textures/entities/"+name+".png");
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityArrow arrow) {
        if(arrow instanceof EntityFlameArrow){
            return res[((EntityFlameArrow)arrow).getType()-1];
        }
        return res[0];
    }
}
