package com.snark.saturalanx.entities;

import com.dunk.tfc.Blocks.Devices.BlockPottery;
import com.dunk.tfc.Entities.EntityProjectileTFC;
import com.dunk.tfc.Entities.EntitySlingStone;
import com.dunk.tfc.Items.ItemMetalChunk;
import com.dunk.tfc.TileEntities.TEPottery;
import com.dunk.tfc.api.Constant.Global;
import com.dunk.tfc.api.Enums.EnumDamageType;
import com.dunk.tfc.api.TFCItems;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityBullet extends EntityProjectileTFC {
    int type = 0;
    byte shouldPickup = 1;
    public EntityBullet(World world) {
        super(world);
    }

    public EntityBullet(World world, double x, double y, double z) {
        super(world, x, y, z);
    }

    public EntityBullet(World world, EntityLivingBase shooter, EntityLivingBase victim, float force, float forceVariation) {
        super(world, shooter, victim, force, forceVariation);
    }

    public EntityBullet(World world, EntityLivingBase shooter, float force) {
        super(world, shooter, force);
    }

    public void onUpdate() {
        super.onUpdate();
        Vec3 vec31 = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
        Vec3 vec3 = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
        MovingObjectPosition mop = this.worldObj.func_147447_a(vec31, vec3, false, true, false);
        if (mop != null && !this.isDead) {
            int x = mop.blockX;
            int y = mop.blockY;
            int z = mop.blockZ;
            Block block = this.worldObj.getBlock(x, y, z);
            if (block.getMaterial() != Material.air) {
                block.setBlockBoundsBasedOnState(this.worldObj, x, y, z);
                AxisAlignedBB axisalignedbb = block.getCollisionBoundingBoxFromPool(this.worldObj, x, y, z);
                if (axisalignedbb != null && axisalignedbb.expand(0.1, 0.1, 0.1).isVecInside(Vec3.createVectorHelper((double)x, (double)y, (double)z)) && !this.worldObj.isRemote) {
                    if (block instanceof BlockPottery) {
                        TileEntity te = this.worldObj.getTileEntity(x, y, z);
                        if (te != null && te instanceof TEPottery) {
                            ((TEPottery)te).attemptToSmash(mop);
                        }
                    }

                    if(this.getShouldPickup() == 1){
                        ItemStack drop = this.getSampleStack();
                        if (drop != null && drop.getItem() == TFCItems.leadBullet && (block.getMaterial() == Material.rock || block.getMaterial() == Material.iron)) {
                            drop = new ItemStack(TFCItems.metalChunk, 1, 0);
                            ItemMetalChunk.setItemMetal(drop, Global.LEAD);
                            ItemMetalChunk.setItemReturnAmount(drop, (short) 10);
                        }

                        EntityItem ei = new EntityItem(this.worldObj, (double) x, (double) y, (double) z, drop);
                        this.worldObj.spawnEntityInWorld(ei);
                    }
                    this.playSound("step." + block.stepSound.soundName, 1.0F, 4.2F / (this.rand.nextFloat() * 0.2F + 0.9F));
                    this.playSound("dig.stone", 2.0F, 4.2F / (this.rand.nextFloat() * 0.2F + 0.9F));
                    this.setDead();
                }
            }
        }

    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(30,0);
        this.dataWatcher.addObject(31, (byte) 0);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound nbt) {
        super.readEntityFromNBT(nbt);
        if(nbt.hasKey("btype")){
            this.dataWatcher.updateObject(30, nbt.getInteger("btype"));
            this.type = nbt.getInteger("btype");
        }
        if(nbt.hasKey("shouldPickup")){
            this.dataWatcher.updateObject(31,nbt.getByte("shouldPickup"));
            this.shouldPickup = nbt.getByte("shouldPickup");
        }
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound nbt) {
        super.writeEntityToNBT(nbt);
        nbt.setInteger("btype", this.dataWatcher.getWatchableObjectInt(30));
        nbt.setByte("shouldPickup",this.dataWatcher.getWatchableObjectByte(31));
    }

    public void setType(int i){
        this.type = i;
        this.dataWatcher.updateObject(30,i);
    }

    public int getType(){
        return this.dataWatcher.getWatchableObjectInt(30);
    }

    public void setShouldPickup(byte i){
        this.shouldPickup = i;
        this.dataWatcher.updateObject(31,i);
    }

    public byte getShouldPickup(){
        return this.dataWatcher.getWatchableObjectByte(31);
    }

    @Override
    public EnumDamageType getDamageType(EntityLivingBase is) {
        return EnumDamageType.CRUSHING;
    }
}
