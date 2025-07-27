package com.snark.saturalanx.entities;

import com.dunk.tfc.Entities.EntityProjectileTFC;
import com.snark.saturalanx.blocks.functional.ArrowRopeBlock;
import com.snark.saturalanx.core.BlockSetup;
import com.snark.saturalanx.core.Config;
import com.snark.saturalanx.core.ItemSetup;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityRopeArrow extends EntityProjectileTFC {
    int facing;
    boolean placed = false;
    public EntityRopeArrow(World world){super(world);}

    public EntityRopeArrow(World world, EntityLivingBase shooter, float force){
        super(world,shooter,force);
        this.facing = (MathHelper.floor_double((double) (shooter.rotationYaw * 4.0 / 360 + 0.5)) & 3) + 1;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        Vec3 vec3 = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
        Vec3 vec31 = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
        MovingObjectPosition mop = this.worldObj.rayTraceBlocks(vec3, vec31);

        if(!worldObj.isRemote&&mop!=null){

            if (!this.isAirBorne) {
                int x,y,z;
                x = (int) Math.floor(mop.hitVec.xCoord);
                y = (int) Math.floor(mop.hitVec.yCoord);
                z = (int) Math.floor(mop.hitVec.zCoord);
                boolean solid = false;
                Block b;

                switch (facing){
                    case 1:
                        z--;
                        b = worldObj.getBlock(x,y,z+1);
                        solid = b.getMaterial().isSolid() && b != BlockSetup.arrowRopeBlock;
                        break;
                    case 2:
                        b = worldObj.getBlock(x-1,y,z);
                        solid = b.getMaterial().isSolid() && b != BlockSetup.arrowRopeBlock;
                        break;
                    case 3:
                        b = worldObj.getBlock(x,y,z-1);
                        solid = b.getMaterial().isSolid() && b != BlockSetup.arrowRopeBlock;
                        break;
                    case 4:
                        x--;
                        b = worldObj.getBlock(x+1,y,z);
                        solid = b.getMaterial().isSolid() && b != BlockSetup.arrowRopeBlock;
                        break;
                }

                if(worldObj.getBlock(x,y,z) == Blocks.air && solid) {
                    this.worldObj.setBlock((int) x, (int) y, (int) z, BlockSetup.arrowRopeBlock, facing, 3);

                    for (int i = 1; i <= Config.ropeArrowRopeLenght; i++) {
                        if (this.worldObj.getBlock((int) x, (int) y - i, (int) z) == Blocks.air)
                            this.worldObj.setBlock((int) x, (int) y - i, (int) z, BlockSetup.arrowRopeBlock, 0, 3);
                        else
                            break;
                    }

                    this.placed = true;
                }

                if (worldObj.getBlock((int) x, (int) y, (int) z) instanceof ArrowRopeBlock) {
                    if(!this.placed){
                        ItemStack is = new ItemStack(ItemSetup.ropeArrow,1,0);
                        EntityItem item = new EntityItem(worldObj,x,y,z,is);
                        worldObj.spawnEntityInWorld(item);
                    }
                    this.setDead();
                }
            }
        }

    }



}
