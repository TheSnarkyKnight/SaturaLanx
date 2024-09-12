package com.snark.saturalanx.entities;

import com.snark.saturalanx.core.Config;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import java.util.List;

//Code for the grenade's movement and explosion damage adapted from Minefantasy Reforged, made by ThatPolishKid. All credit goes to him.
//https://github.com/TeamMFR/MineFantasyReforged/blob/1.12.2/src/main/java/minefantasy/mfr/entity/EntityBomb.java

public class EntityPotGrenade extends Entity implements IProjectile {
    protected int fuse;
    protected int maxFuse;
    protected float explosive;
    protected float shrapnel;
    protected float pellet;
    protected float incendiary;
    protected EntityLivingBase thrower;
    protected int type = 1;

    public EntityPotGrenade(World world) {
        super(world);
        this.fuse = Config.potGrenadeFuseLength;
        this.explosive = 0;
        this.shrapnel = 0;
        this.pellet = 0;
        this.incendiary = 0;
    }

    public EntityPotGrenade(World world, EntityLivingBase entity, float[] comp) {
        super(world);
        this.thrower = entity;
        this.fuse = Config.potGrenadeFuseLength;
        this.explosive = comp[0];
        this.shrapnel = comp[1];
        this.pellet = comp[2];
        this.incendiary = comp[3];
        this.setType(1);
    }

    public EntityPotGrenade(World world, EntityLivingBase entity, float[] comp, int fuse, int maxFuse, int type) {
        this(world, entity,comp);
        this.maxFuse = maxFuse;
        this.fuse = maxFuse-fuse;
        this.setType(type);

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

    public EntityPotGrenade(World world, EntityLivingBase entity, float[] comp, int fuse, int maxFuse, boolean instant){
        this(world, entity, comp, fuse, maxFuse,1);
        if(instant) {
            this.fuse = 0;
            this.motionZ = this.motionX = this.motionY = 0;
        }
    }

    @Override
    protected void entityInit() {
        this.dataWatcher.addObject(30,0);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound nbt) {
        if(nbt.hasKey("btype")){
            this.dataWatcher.updateObject(30, nbt.getInteger("btype"));
            this.type = nbt.getInteger("btype");
        }
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound nbt) {
        nbt.setInteger("btype", this.dataWatcher.getWatchableObjectInt(30));
    }

    public void setType(int i){
        this.type = i;
        this.dataWatcher.updateObject(30,i);
    }

    public int getType(){
        return this.dataWatcher.getWatchableObjectInt(30);
    }

    public static DamageSource getDamageSource(Entity grenade, Entity user){
        return (new EntityDamageSourceGrenade(grenade,user)).setExplosion();
    }

    public int getMaxFuse(){
        return this.maxFuse;

    }

    public boolean isInWater() {
        return this.worldObj.handleMaterialAcceleration(this.boundingBox.expand(0.0, -0.0200000238418579, 0.0), Material.water, this);
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
            this.setDead();

            if (!this.worldObj.isRemote) {
                this.explode();
            }
        }
        this.worldObj.spawnParticle("smoke", this.posX, this.posY+0.3, this.posZ, 0.0D, 0.0D, 0.0D);
        this.worldObj.spawnParticle("flame", this.posX, this.posY+0.3, this.posZ, 0.0D, 0.0D, 0.0D);

    }

    public void explode() {
            double power = Math.min(explosive,Config.potGrenadePowerCap);
            worldObj.createExplosion(this, posX, posY, posZ, (float) 0, false);
            if (!worldObj.isRemote) {
                double range = Math.max(Math.min(power,Config.potGrenadeMaxRange),Config.potGrenadeMinRange);
                AxisAlignedBB box = this.boundingBox.expand(range, range, range);
                List<Entity> entities = worldObj.getEntitiesWithinAABB(EntityLivingBase.class, box);

                for (Entity entity : entities) {
                    double distance = this.getDistanceToEntity(entity);
                    if(distance<range){
                        int damage = (int)(4 * power);
                        DamageSource source = getDamageSource(this, thrower != null ? thrower : this);
                        source.setExplosion();
                        System.out.println(damage);
                        if(!(entity instanceof EntityItem)){
                            entity.attackEntityFrom(source,damage);
                            if(incendiary>0){
                                entity.setFire((int)(Math.max(Math.ceil(incendiary/4),1)));
                            }
                        }
                    }


            }
        }
            if(shrapnel>0){
                for(int i = 0;i<Math.min(Math.ceil(shrapnel/2),(power*2));i++){
                    double x = rand.nextDouble();
                    double y = rand.nextDouble();
                    double z = rand.nextDouble();
                    if(rand.nextInt(2)==0)
                        x *= -1;
                    if(rand.nextInt(2)==0)
                        z *= -1;

                    EntityShrapnel shrapnel = new EntityShrapnel(worldObj, posX, posY, posZ);
                    shrapnel.motionX = x;
                    shrapnel.motionY = y;
                    shrapnel.motionZ = z;
                    shrapnel.setThrowableHeading(shrapnel.motionX,shrapnel.motionY,shrapnel.motionZ,(float)(1.5F),1.0F);
                    shrapnel.setDamage(10+power);
                    if(incendiary>0)
                        shrapnel.setFire((int)(Math.max(Math.ceil(incendiary/4),1)));
                    if(!worldObj.isRemote){
                        worldObj.spawnEntityInWorld(shrapnel);
                    }
                }
            }
        if(pellet>0){
            for(int i = 0;i<Math.min(Math.ceil(pellet/2),(power*2));i++){
                double x = rand.nextDouble();
                double y = rand.nextDouble();
                double z = rand.nextDouble();
                if(rand.nextInt(2)==0)
                    x *= -1;
                if(rand.nextInt(2)==0)
                    z *= -1;

                EntityBullet b = new EntityBullet(worldObj, posX, posY, posZ);
                b.setType(0);
                b.setDamage(10+power);
                b.motionX = x;
                b.motionY = y;
                b.motionZ = z;
                b.setThrowableHeading(b.motionX,b.motionY,b.motionZ,(float)(1.5F),1.0F);
                if(incendiary>0)
                    b.setFire((int)(Math.max(Math.ceil(incendiary/4),1)));
                if(!worldObj.isRemote){
                    worldObj.spawnEntityInWorld(b);
                }
            }
        }
    }



}
