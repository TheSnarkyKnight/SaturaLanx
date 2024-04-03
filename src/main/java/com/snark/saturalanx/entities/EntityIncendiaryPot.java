package com.snark.saturalanx.entities;

import com.dunk.tfc.api.TFCBlocks;
import com.snark.saturalanx.core.BlockSetup;
import com.snark.saturalanx.core.Config;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityIncendiaryPot extends EntityThrowable {

    int radius = Config.incendiaryPotDiameter;

    public EntityIncendiaryPot(World world) {
        super(world);
    }

    public EntityIncendiaryPot(World world, EntityLivingBase entity) {
        super(world, entity);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        if(this.isInWater())
            this.setDead();

        worldObj.spawnParticle("flame", posX, posY, posZ, 0.0D, 0.0D, 0.0D);
    }

    @Override
    protected void onImpact(MovingObjectPosition movingObjectPosition) {
        for (int i = 0; i < 10; i++) {
            double x = (double) (rand.nextInt(10) - 5) / 10.0D;
            double y = (double) (rand.nextInt(10) - 5) / 10.0D;
            double z = (double) (rand.nextInt(10) - 5) / 10.0D;
            worldObj.spawnParticle("flame", posX, posY, posZ, x, y, z);
        }
        explode();
    }

    @Override
    protected float getGravityVelocity() {
        return 0.3F;
    }

    public void explode() {

        if(!this.worldObj.isRemote){
            worldObj.playSoundAtEntity(this, "game.potion.break", 1.0F, 1.0F);

            this.splashOil(this.worldObj,(int)this.posX,(int)this.posY,(int)this.posZ,radius);

            this.setDead();
        }
    }

    public void splashOil(World world, int x, int y, int z, int radius){

        boolean f = false;
        int r = radius;

        if(r==0){
            int y2;
            for(int i = -2;i<=2;i++){
                //stupid inefficient code starts here
                y2 = y+i;
                if (this.canDisplace(world.getBlock(x,y2,z)) && this.notForbidden(world.getBlock(x, y2 - 1, z)))
                {
                    world.setBlock(x, y2, z, BlockSetup.flamingBlock);
                    f = true;
                }
                if (notForbidden(world.getBlock(x,y2,z)) && world.getBlock(x, y2 + 1, z) == Blocks.air && !f)
                {
                    world.setBlock(x, y2+1, z, BlockSetup.flamingBlock);
                }
                f = false;

                if (this.canDisplace(world.getBlock(x+1,y2,z)) && this.notForbidden(world.getBlock(x+1, y2 - 1, z)))
                {
                    world.setBlock(x+1, y2, z, BlockSetup.flamingBlock);
                    f = true;
                }
                if (notForbidden(world.getBlock(x+1,y2,z)) && world.getBlock(x+1, y2 + 1, z) == Blocks.air && !f)
                {
                    world.setBlock(x+1, y2+1, z, BlockSetup.flamingBlock);
                }
                f = false;

                if (this.canDisplace(world.getBlock(x-1,y2,z)) && this.notForbidden(world.getBlock(x-1, y2 - 1, z)))
                {
                    world.setBlock(x-1, y2, z, BlockSetup.flamingBlock);
                    f = true;
                }
                if (notForbidden(world.getBlock(x-1,y2,z)) && world.getBlock(x-1, y2 + 1, z) == Blocks.air && !f)
                {
                    world.setBlock(x-1, y2+1, z, BlockSetup.flamingBlock);
                }
                f = false;

                if (this.canDisplace(world.getBlock(x,y2,z-1)) && this.notForbidden(world.getBlock(x, y2 - 1, z-1)))
                {
                    world.setBlock(x, y2, z-1, BlockSetup.flamingBlock);
                    f = true;
                }
                if (notForbidden(world.getBlock(x,y2,z-1)) && world.getBlock(x, y2 + 1, z-1) == Blocks.air && !f)
                {
                    world.setBlock(x, y2+1, z-1, BlockSetup.flamingBlock);
                }
                f = false;

                if (this.canDisplace(world.getBlock(x,y2,z+1)) && this.notForbidden(world.getBlock(x, y2 - 1, z+1)))
                {
                    world.setBlock(x, y2, z+1, BlockSetup.flamingBlock);
                    f = true;
                }
                if (notForbidden(world.getBlock(x,y2,z+1)) && world.getBlock(x, y2 + 1, z+1) == Blocks.air && !f)
                {
                    world.setBlock(x, y2+1, z+1, BlockSetup.flamingBlock);
                }
                f = false;
            }
        }
        else {
            int r2 = r * r;
            int r22 = r2 / 2;
            for (int xx = -r; xx < r; xx++) {
                int X = xx + x;
                int XX = xx * xx;
                for (int yy = -r; yy < r; yy++) {
                    int Y = yy + y;
                    int YY = XX + yy * yy;
                    for (int zz = -r; zz < r; zz++) {
                        int Z = zz + z;
                        int ZZ = YY + zz * zz;
                        if (ZZ < r22) {
                            Block b = world.getBlock(X, Y, Z);
                            if (this.canDisplace(b) && this.notForbidden(world.getBlock(X, Y - 1, Z))) {
                                world.setBlock(X, Y, Z, BlockSetup.flamingBlock);
                                f = true;
                            }
                            if (notForbidden(b) && world.getBlock(X, Y + 1, Z) == Blocks.air && !f) {
                                world.setBlock(X, Y + 1, Z, BlockSetup.flamingBlock);
                            }
                            f = false;
                        }
                    }
                }
            }
        }
    }

    public boolean canDisplace(Block b){
        if(b == Blocks.tallgrass)
            return true;
        if(b == Blocks.red_flower || b == Blocks.yellow_flower)
            return true;
        if(b == Blocks.snow)
            return true;
        if(b == TFCBlocks.tallGrass)
            return true;
        if(b == TFCBlocks.leafLitter)
            return true;
        if(b == TFCBlocks.undergrowth || b == TFCBlocks.lowUndergrowth)
            return true;
        if(b == TFCBlocks.flowers || b == TFCBlocks.flowers2)
            return true;
        if(b == TFCBlocks.snow)
            return true;
        if(b == TFCBlocks.worldItem)
            return true;

        return false;
    }
    public boolean notForbidden(Block b){
        if(b == Blocks.air)
            return false;
        if(b == Blocks.water)
            return false;
        if(b == TFCBlocks.freshWater)
            return false;
        if(b == TFCBlocks.hotWater)
            return false;
        if(b == TFCBlocks.saltWater)
            return false;
        if(b == BlockSetup.flamingBlock || b == Blocks.fire)
            return false;

        return true;
    }

}
