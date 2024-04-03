package com.snark.saturalanx.entities;

import com.snark.saturalanx.core.Config;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import java.util.List;

public class EntityFireCracker extends Entity {
    int counter, num, fuse;
    EntityLivingBase thrower;
    public EntityFireCracker(World world) {
        super(world);
        this.fuse = Config.potGrenadeFuseLength/2;
        this.num = 1;
    }

    @Override
    protected void entityInit() {

    }

    public EntityFireCracker(World world, EntityLivingBase entity, int i){
        super(world);
        this.thrower = entity;
        this.counter = 0;
        this.num = i;
        this.fuse = Config.potGrenadeFuseLength/2;

        this.setSize(0.5F,0.5F);
        this.setLocationAndAngles(thrower.posX, thrower.posY + thrower.getEyeHeight(), thrower.posZ,
                thrower.rotationYaw, thrower.rotationPitch);
        this.posX -= MathHelper.cos(this.rotationYaw / 180.0F * (float) Math.PI) * 0.16F;
        this.posY -= 0.10000000149011612D;
        this.posZ -= MathHelper.sin(this.rotationYaw / 180.0F * (float) Math.PI) * 0.16F;
        this.setPosition(this.posX, this.posY, this.posZ);

        float force = this.getThrownForce();

        float f = 0.4F;
        this.motionX = -MathHelper.sin(this.rotationYaw / 180.0F * (float) Math.PI)
                * MathHelper.cos(this.rotationPitch / 180.0F * (float) Math.PI) * f;
        this.motionZ = MathHelper.cos(this.rotationYaw / 180.0F * (float) Math.PI)
                * MathHelper.cos(this.rotationPitch / 180.0F * (float) Math.PI) * f;
        this.motionY = -MathHelper.sin((this.rotationPitch + this.getThrownOffset()) / 180.0F * (float) Math.PI) * f;
        this.setThrowableHeading(this.motionX, this.motionY, this.motionZ, force, 1.0F);

        this.prevPosX = posX;
        this.prevPosY = posY;
        this.prevPosZ = posZ;
    }

    @Override
    protected boolean canTriggerWalking() {
        return false;
    }

    @Override
    public boolean canBeCollidedWith() {
        return true;
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound nbtTagCompound) {

    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound nbtTagCompound) {

    }

    @Override
    public void applyEntityCollision(Entity entity) {
        if (!(thrower != null && thrower == entity)) {
            this.motionX = 0;
            this.motionZ = 0;
        }
    }

    public void setThrowableHeading(double x, double y, double z, float offset, float force) {
        float f2 = MathHelper.sqrt_double(x * x + y * y + z * z);
        x /= f2;
        y /= f2;
        z /= f2;
        x += this.rand.nextGaussian() * 0.007499999832361937D * force;
        y += this.rand.nextGaussian() * 0.007499999832361937D * force;
        z += this.rand.nextGaussian() * 0.007499999832361937D * force;
        x *= offset;
        y *= offset;
        z *= offset;
        this.motionX = x;
        this.motionY = y;
        this.motionZ = z;
        float f3 = MathHelper.sqrt_double(x * x + z * z);
        this.prevRotationYaw = this.rotationYaw = (float) (Math.atan2(x, z) * 180.0D / Math.PI);
        this.prevRotationPitch = this.rotationPitch = (float) (Math.atan2(y, f3) * 180.0D / Math.PI);
    }

    protected float getThrownOffset() {
        return 0.0F;
    }

    protected float getThrownForce() {
        return 1.5F;
    }

    @Override
    public void onUpdate() {

        if(this.isInWater())
            this.setDead();

        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        this.motionY -= (0.03999999910593033D);
        this.moveEntity(this.motionX, this.motionY, this.motionZ);
        this.motionX *= 0.9800000190734863D;
        this.motionY *= 0.9800000190734863D;
        this.motionZ *= 0.9800000190734863D;

        if (this.onGround) {
            double d = 0.75D;
            this.motionX *= d;
            this.motionZ *= d;
            this.motionY *= -0.99D;
        }
        List<Entity> collide = worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox);
        if (!collide.isEmpty() && !(thrower != null && collide.contains(thrower))) {
            this.motionX = motionZ = 0;
        }

        if(this.fuse % 100 == 0&&this.fuse>=100)
            worldObj.playSoundAtEntity(this,"saturalanx:fuse",0.7F,1);

        if (this.fuse-- <= 0) {

            if(counter <= 0) {
                counter = 50;
                num--;
                if (!this.worldObj.isRemote) {
                    worldObj.createExplosion(this, posX, posY, posZ, 0, false);
                }
            }
            else
                counter--;

            if(num <= 0)
                this.setDead();
        }
        this.worldObj.spawnParticle("smoke", this.posX, this.posY+0.3, this.posZ, 0.0D, 0.0D, 0.0D);
        this.worldObj.spawnParticle("flame", this.posX, this.posY+0.3, this.posZ, 0.0D, 0.0D, 0.0D);

    }
}
