package com.snark.saturalanx.TE;

import com.dunk.tfc.Core.Player.BodyTempStats;
import com.dunk.tfc.Core.Player.InventoryPlayerTFC;
import com.dunk.tfc.Core.TFC_Core;
import com.dunk.tfc.Handlers.Network.PlayerUpdatePacket;
import com.dunk.tfc.TerraFirmaCraft;
import com.dunk.tfc.TileEntities.NetworkTileEntity;
import com.dunk.tfc.api.TFCBlocks;
import com.dunk.tfc.api.TileEntities.TEFireEntity;
import com.snark.saturalanx.core.BlockSetup;
import com.snark.saturalanx.core.Config;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import org.lwjgl.Sys;

import java.util.List;

import static com.dunk.tfc.api.TFCBlocks.firepit;
import static com.dunk.tfc.api.TFCBlocks.forge;
import static com.snark.saturalanx.core.BlockSetup.hypocaustBlock;
import static com.snark.saturalanx.core.BlockSetup.hypocaustControlBlock;

public class HypocaustTE extends NetworkTileEntity {
    private int minX,maxX,minZ,maxZ;
    private boolean active,formed;

    public HypocaustTE(){
        super();
        active = false;
        formed = false;
        minX = 0;
        minZ = 0;
        maxX = 0;
        maxZ = 0;
    }

    @Override
    public void updateEntity() {
        if(!worldObj.isRemote){
            if(minX+minZ+maxX+maxZ==0) {
                this.getSize();
                this.checkIntegrity();
            }
            if(worldObj.getWorldTime()%(Config.hypocaustFuelCheckFrequency*20L)==0)
                this.getActiveHeatSource();
            if(worldObj.getWorldTime()%(Config.hypocaustIntegrityCheckFrequency*20L)==0) {
                this.getSize();
                this.checkIntegrity();
            }
            if(active&&formed){
                AxisAlignedBB box = worldObj.getBlock(xCoord,yCoord,zCoord).getCollisionBoundingBoxFromPool(worldObj,xCoord,yCoord,zCoord).setBounds(minX-1,yCoord,minZ-1,maxX+1,yCoord+6,maxZ+1);
                List<Entity> l = this.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, box);
                for (Entity e : l) {
                    if (e instanceof EntityPlayer) {

                        EntityPlayer p = (EntityPlayer)e;
                        BodyTempStats temp = TFC_Core.getBodyTempStats(p);

                        if(temp.temporaryColdProtection < 1) {
                            temp.temporaryColdProtection++;
                            System.out.println("Yes - 1");
                        }
                        else if(temp.temporaryColdProtection < 2 && temp.tempColdTimeRemaining >= 180L){
                            temp.temporaryColdProtection++;
                            System.out.println("Yes - 2");
                        }
                        if(temp.tempColdTimeRemaining < 180L)
                            temp.tempColdTimeRemaining = 180L;

                        TFC_Core.setBodyTempStats((EntityPlayer) e, temp);

                    }
                }
            }


        }
    }

    public boolean isActive() {
        return active;
    }

    public boolean isFormed(){return formed;}

    public int getMinX() {
        return minX;
    }

    public void setMinX(int minX) {
        this.minX = minX;
    }

    public int getMaxX() {
        return maxX;
    }

    public void setMaxX(int maxX) {
        this.maxX = maxX;
    }

    public int getMinZ() {
        return minZ;
    }

    public void setMinZ(int minZ) {
        this.minZ = minZ;
    }

    public int getMaxZ() {
        return maxZ;
    }

    public void setMaxZ(int maxZ) {
        this.maxZ = maxZ;
    }

    public void getActiveHeatSource(){
        boolean p = this.active;

        if(worldObj.getBlock(xCoord-1,yCoord,zCoord)==firepit||worldObj.getBlock(xCoord-1,yCoord-1,zCoord)==forge){
            TileEntity te = worldObj.getTileEntity(xCoord-1,yCoord,zCoord);
            if(te instanceof TEFireEntity && ((TEFireEntity)te).fireTemp>0)
                this.active = true;
            else
                this.active = false;
        }
        else if(worldObj.getBlock(xCoord+1,yCoord,zCoord)==firepit||worldObj.getBlock(xCoord+1,yCoord-1,zCoord)==forge){
            TileEntity te = worldObj.getTileEntity(xCoord+1,yCoord,zCoord);
            if(te instanceof TEFireEntity && ((TEFireEntity)te).fireTemp>0)
                this.active = true;
            else
                this.active = false;
        }
        else if(worldObj.getBlock(xCoord,yCoord,zCoord-1)==firepit||worldObj.getBlock(xCoord,yCoord-1,zCoord-1)==forge){
            TileEntity te = worldObj.getTileEntity(xCoord,yCoord,zCoord-1);
            if(te instanceof TEFireEntity && ((TEFireEntity)te).fireTemp>0)
                this.active = true;
            else
                this.active = false;
        }
        else if(worldObj.getBlock(xCoord,yCoord,zCoord+1)==firepit||worldObj.getBlock(xCoord,yCoord-1,zCoord-1)==forge){
            TileEntity te = worldObj.getTileEntity(xCoord,yCoord,zCoord+1);
            if(te instanceof TEFireEntity && ((TEFireEntity)te).fireTemp>0)
                this.active = true;
            else
                this.active = false;
        }
        else if(worldObj.getBlock(xCoord-1,yCoord,zCoord)== TFCBlocks.hotWater||worldObj.getBlock(xCoord-1,yCoord,zCoord)==TFCBlocks.hotWaterStationary)
            this.active = true;
        else if(worldObj.getBlock(xCoord+1,yCoord,zCoord)== TFCBlocks.hotWater||worldObj.getBlock(xCoord+1,yCoord,zCoord)==TFCBlocks.hotWaterStationary)
            this.active = true;
        else if(worldObj.getBlock(xCoord,yCoord,zCoord-1)== TFCBlocks.hotWater||worldObj.getBlock(xCoord,yCoord,zCoord-1)==TFCBlocks.hotWaterStationary)
            this.active = true;
        else if(worldObj.getBlock(xCoord,yCoord,zCoord+1)== TFCBlocks.hotWater||worldObj.getBlock(xCoord,yCoord,zCoord+1)==TFCBlocks.hotWaterStationary)
            this.active = true;
        else if(worldObj.getBlock(xCoord-1,yCoord,zCoord)== TFCBlocks.lava||worldObj.getBlock(xCoord-1,yCoord,zCoord)==TFCBlocks.lavaStationary)
            this.active = true;
        else if(worldObj.getBlock(xCoord+1,yCoord,zCoord)== TFCBlocks.lava||worldObj.getBlock(xCoord+1,yCoord,zCoord)==TFCBlocks.lavaStationary)
            this.active = true;
        else if(worldObj.getBlock(xCoord,yCoord,zCoord-1)== TFCBlocks.lava||worldObj.getBlock(xCoord,yCoord,zCoord-1)==TFCBlocks.lavaStationary)
            this.active = true;
        else if(worldObj.getBlock(xCoord,yCoord,zCoord+1)== TFCBlocks.lava||worldObj.getBlock(xCoord,yCoord,zCoord+1)==TFCBlocks.lavaStationary)
            this.active = true;
        else
            this.active = false;

        if(p!=this.active&&!worldObj.isRemote)
            this.updateFluids();
    }

    public void updateFluids(){
        Block b = worldObj.getBlock(xCoord,yCoord+1,zCoord);

        if((b == TFCBlocks.freshWater || b == TFCBlocks.freshWaterStationary)&&active){
            for(int x = minX; x <= maxX; x++){
                for(int z = minZ; z <= maxZ;z++){
                    b = worldObj.getBlock(x,yCoord+1,z);
                    if(b == TFCBlocks.freshWater || b == TFCBlocks.freshWaterStationary)
                        worldObj.setBlock(x, yCoord + 1, z, BlockSetup.bathWaterStatic);
                }
            }
        }
        else if((b == BlockSetup.bathWater|| b == BlockSetup.bathWaterStatic)&&!active){
            for(int x = minX; x <= maxX; x++) {
                for (int z = minZ; z <= maxZ; z++) {
                    b = worldObj.getBlock(x,yCoord+1,z);
                    if (b == BlockSetup.bathWater || b == BlockSetup.bathWaterStatic)
                        worldObj.setBlock(x, yCoord + 1, z, TFCBlocks.freshWaterStationary);
                }
            }
        }

        b = worldObj.getBlock(xCoord,yCoord+2,zCoord);
        if((b == TFCBlocks.freshWater || b == TFCBlocks.freshWaterStationary)&&active){
            for(int x = minX; x <= maxX; x++){
                for(int z = minZ; z <= maxZ;z++){
                    b = worldObj.getBlock(x,yCoord+2,z);
                    if(b == TFCBlocks.freshWater || b == TFCBlocks.freshWaterStationary)
                        worldObj.setBlock(x, yCoord + 2, z, BlockSetup.bathWaterStatic);
                }
            }
        }
        else if((b == BlockSetup.bathWater|| b == BlockSetup.bathWaterStatic)&&!active){
            for(int x = minX; x <= maxX; x++) {
                for (int z = minZ; z <= maxZ; z++) {
                    b = worldObj.getBlock(x,yCoord+2,z);
                    if (b == BlockSetup.bathWater || b == BlockSetup.bathWaterStatic)
                        worldObj.setBlock(x, yCoord + 2, z, TFCBlocks.freshWaterStationary);
                }
            }
        }

        b = worldObj.getBlock(xCoord,yCoord+3,zCoord);
        if((b == TFCBlocks.freshWater || b == TFCBlocks.freshWaterStationary)&&active){
            for(int x = minX; x <= maxX; x++){
                for(int z = minZ; z <= maxZ;z++){
                    b = worldObj.getBlock(x,yCoord+3,z);
                    if(b == TFCBlocks.freshWater || b == TFCBlocks.freshWaterStationary)
                        worldObj.setBlock(x, yCoord + 3, z, BlockSetup.bathWaterStatic);
                }
            }
        }
        else if((b == BlockSetup.bathWater|| b == BlockSetup.bathWaterStatic)&&!active){
            for(int x = minX; x <= maxX; x++) {
                for (int z = minZ; z <= maxZ; z++) {
                    b = worldObj.getBlock(x,yCoord+3,z);
                    if (b == BlockSetup.bathWater||b == BlockSetup.bathWaterStatic)
                        worldObj.setBlock(x, yCoord + 3, z, TFCBlocks.freshWaterStationary);
                }
            }
        }
    }

    public void getSize(){
        if(!worldObj.isRemote){
            boolean b = false;
            boolean c = false;
            int i1 = 0;
            int i2 = 0;

            //X Axis
            for(int i = 1;i<=Config.hypocaustSizeCap;i++){
                if(worldObj.getBlock(xCoord-i,yCoord,zCoord)!=hypocaustBlock&&!b) {
                    i1 = xCoord - i;
                    b = true;
                }
                if(worldObj.getBlock(xCoord+i,yCoord,zCoord)!=hypocaustBlock&&!c) {
                    i2 = xCoord + i;
                    c = true;
                }
                if(b&&c)
                    break;
            }
            this.minX = Math.min(i1,i2);
            this.maxX = Math.max(i1,i2);

            //Z Axis
            b = false;
            c = false;
            for(int i = 1;i<=Config.hypocaustSizeCap;i++){
                if(worldObj.getBlock(xCoord,yCoord,zCoord-i)!=hypocaustBlock&&!b) {
                    i1 = zCoord - i;
                    b = true;
                }
                if(worldObj.getBlock(xCoord,yCoord,zCoord+i)!=hypocaustBlock&&!c) {
                    i2 = zCoord + i;
                    c = true;
                }
                if(b&&c)
                    break;
            }
            this.minZ = Math.min(i1,i2);
            this.maxZ = Math.max(i1,i2);
        }
    }



    //I have no goddamned idea why Z needs to be one less. The blocks returned by the function do not correspond to the ones present in-world
    public boolean checkIntegrity(){
        formed = true;
        for(int i = minX;i<=maxX;i++)
            for(int j = minZ-1;j<maxZ;j++) {
                Block b = worldObj.getBlock(i, yCoord, j);
                //System.out.println(b.getUnlocalizedName()+ " "+i+" "+j);
                if (!(b == hypocaustBlock || b == hypocaustControlBlock))
                    formed = false;
            }
        return formed;
    }

    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        this.minX = nbt.getInteger("minX");
        this.maxX = nbt.getInteger("maxX");
        this.minZ = nbt.getInteger("minZ");
        this.maxZ = nbt.getInteger("maxZ");
        this.active = nbt.getBoolean("active");
        this.formed = nbt.getBoolean("formed");
    }

    public void writeToNBT(NBTTagCompound nbt){
        super.writeToNBT(nbt);
        nbt.setInteger("minX",this.minX);
        nbt.setInteger("maxX",this.maxX);
        nbt.setInteger("minZ",this.minZ);
        nbt.setInteger("maxZ",this.maxX);
        nbt.setBoolean("active",this.active);
        nbt.setBoolean("formed",this.formed);
    }

    public void handleInitPacket(NBTTagCompound nbt) {
        this.readFromNBT(nbt);
    }

    public void createInitNBT(NBTTagCompound nbt) {
        this.writeToNBT(nbt);
    }

    public void handleDataPacket(NBTTagCompound nbt) {
        this.readFromNBT(nbt);
    }

    public void createDataNBT(NBTTagCompound nbt) {
        this.writeToNBT(nbt);
    }
}
