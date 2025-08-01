package com.snark.saturalanx.blocks.liquid;

import com.dunk.tfc.Blocks.Liquids.BlockHotWaterStatic;
import com.dunk.tfc.Core.Player.BodyTempStats;
import com.dunk.tfc.Core.TFC_Core;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;

public class BathWaterBlockStatic extends BlockHotWaterStatic {
    public BathWaterBlockStatic(Fluid fluid, Material m, Block f) {
        super(fluid, m, f);
        this.setBlockName("BathWaterBlockStatic");
        this.setHardness(100.0F);
        this.setLightOpacity(3);
    }

    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
        if (entity instanceof EntityLivingBase) {
            EntityLivingBase e = (EntityLivingBase)entity;
            if (world.rand.nextInt(25) == 0 && e.getHealth() < e.getMaxHealth()) {
                float diff = e.getMaxHealth() - e.getHealth();
                e.heal(Math.max(diff * 0.0005F, 1.0E-4F));
            }
        }
        if(entity instanceof EntityPlayer){

            BodyTempStats temp = TFC_Core.getBodyTempStats((EntityPlayer) entity);
            if(temp.temporaryHeatProtection>-2)
                temp.temporaryHeatProtection--;
            if(temp.tempHeatTimeRemaining<10L)
                temp.tempHeatTimeRemaining = 10L;
            if(temp.temporaryColdProtection<4)
                temp.temporaryColdProtection++;
            if(temp.tempColdTimeRemaining<10L)
                temp.tempColdTimeRemaining=10L;

            TFC_Core.setBodyTempStats((EntityPlayer)entity,temp);
        }

    }
}
