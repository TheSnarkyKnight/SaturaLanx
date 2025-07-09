package com.snark.saturalanx.entities;

import com.dunk.tfc.Entities.EntityProjectileTFC;
import com.snark.saturalanx.blocks.functional.ArrowRopeBlock;
import com.snark.saturalanx.core.BlockSetup;
import com.snark.saturalanx.core.Config;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityRopeArrow extends EntityProjectileTFC {
    public EntityRopeArrow(World world){super(world);}

    public EntityRopeArrow(World world, EntityLivingBase shooter, float force){
        super(world,shooter,force);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        Vec3 vec3 = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
        Vec3 vec31 = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
        MovingObjectPosition mop = this.worldObj.rayTraceBlocks(vec3, vec31);
        vec3 = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
        vec31 = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);

        if(!worldObj.isRemote&&mop!=null){
            int x = (int) Math.floor(mop.hitVec.xCoord);
            int y = (int) Math.floor(mop.hitVec.yCoord);
            int z = (int) Math.floor(mop.hitVec.zCoord);

            if (!this.isAirBorne) {
                int side = (MathHelper.floor_double((double) (this.rotationYaw * 4.0 / 360 + 0.5)) & 3) + 1;

                if(worldObj.getBlock(x,y,z) == Blocks.air) {
                    this.worldObj.setBlock((int) x, (int) y, (int) z, BlockSetup.arrowRopeBlock, side, 3);

                    for (int i = 1; i <= Config.ropeArrowRopeLenght; i++)
                        if (this.worldObj.getBlock((int) x, (int) y - i, (int) z) == Blocks.air)
                            this.worldObj.setBlock((int) x, (int) y - i, (int) z, BlockSetup.arrowRopeBlock, 0, 3);
                }

            }
            if (worldObj.getBlock((int) x, (int) y, (int) z) instanceof ArrowRopeBlock)
                this.setDead();
        }

    }



}
