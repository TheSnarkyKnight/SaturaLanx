package com.snark.saturalanx.TE;

import com.dunk.tfc.Core.Player.BodyTempStats;
import com.dunk.tfc.Core.TFC_Core;
import com.dunk.tfc.TileEntities.NetworkTileEntity;
import com.dunk.tfc.api.Interfaces.IHeatSource;
import com.dunk.tfc.api.TFCBlocks;
import com.dunk.tfc.api.TileEntities.TEFireEntity;
import com.snark.saturalanx.SaturaLanx;
import com.snark.saturalanx.core.BlockSetup;
import com.snark.saturalanx.core.Config;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.dunk.tfc.api.TFCBlocks.*;
import static com.snark.saturalanx.core.BlockSetup.*;

public class HypocaustTE extends NetworkTileEntity {
    private int minX,maxX,minZ,maxZ;
    private boolean active,formed;

    public HypocaustTE(){
        super();
    }

    public void init(){
        active = false;
        formed = false;
        minX = this.xCoord;
        minZ = this.zCoord;
        maxX = this.xCoord;
        maxZ = this.zCoord;

        this.getSize();
        this.checkIntegrity();
        this.getActiveHeatSource();
    }

    @Override
    public void updateEntity() {
        if (!worldObj.isRemote) {
            if (worldObj.getTotalWorldTime() % Config.hypocaustIntegrityCheckFrequency == 0) {
                this.getSize();
                this.checkIntegrity();
            }
            if (formed&&worldObj.getTotalWorldTime() % Config.hypocaustFuelCheckFrequency == 0) {
                this.getActiveHeatSource();
                this.updateFluids();
            }

            if (active && formed) {
                AxisAlignedBB box = worldObj.getBlock(xCoord, yCoord, zCoord).getCollisionBoundingBoxFromPool(worldObj, xCoord, yCoord, zCoord).setBounds(minX - 1, yCoord, minZ - 1, maxX + 1, yCoord + 6, maxZ + 1);
                List<Entity> l = this.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, box);
                for (Entity e : l) {
                    if (e instanceof EntityPlayer) {

                        EntityPlayer p = (EntityPlayer) e;
                        BodyTempStats temp = TFC_Core.getBodyTempStats(p);

                        if (temp.temporaryColdProtection < 1) {
                            temp.temporaryColdProtection++;
                        } else if (temp.temporaryColdProtection < 2 && temp.tempColdTimeRemaining > 180L) {
                            temp.temporaryColdProtection++;
                        }
                        else if(temp.temporaryColdProtection == 2 && temp.tempColdTimeRemaining <= 180L){
                            temp.temporaryColdProtection--;
                        }
                        if (temp.tempColdTimeRemaining < 180L)
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

    public boolean isFormed(){
        return formed;
    }

    public void getActiveHeatSource(){

        Map<Integer[],Block> map = new HashMap<>();
        boolean check = false;

        //Get all blocks and their coordinates from possible heat source positions
        map.put(new Integer[]{xCoord-1,yCoord,zCoord},worldObj.getBlock(xCoord-1,yCoord,zCoord));
        map.put(new Integer[]{xCoord+1,yCoord,zCoord},worldObj.getBlock(xCoord+1,yCoord,zCoord));
        map.put(new Integer[]{xCoord-1,yCoord-1,zCoord},worldObj.getBlock(xCoord-1,yCoord-1,zCoord));
        map.put(new Integer[]{xCoord+1,yCoord-1,zCoord},worldObj.getBlock(xCoord+1,yCoord-1,zCoord));
        map.put(new Integer[]{xCoord,yCoord,zCoord-1},worldObj.getBlock(xCoord,yCoord,zCoord-1));
        map.put(new Integer[]{xCoord,yCoord,zCoord+1},worldObj.getBlock(xCoord,yCoord,zCoord+1));
        map.put(new Integer[]{xCoord,yCoord-1,zCoord-1},worldObj.getBlock(xCoord,yCoord-1,zCoord-1));
        map.put(new Integer[]{xCoord,yCoord-1,zCoord+1},worldObj.getBlock(xCoord,yCoord-1,zCoord+1));
        map.put(new Integer[]{xCoord,yCoord-1,zCoord},worldObj.getBlock(xCoord,yCoord-1,zCoord));


        //Check each possible block and position for a heat source
        for(Map.Entry<Integer[],Block> e : map.entrySet() ){

            Block b = e.getValue();
            Integer[] pos = e.getKey();

            if(b instanceof IHeatSource){
                TileEntity te = worldObj.getTileEntity(pos[0],pos[1],pos[2]);
                if(te instanceof TEFireEntity)
                    if(((TEFireEntity)te).fuelTimeLeft > 0)
                        check = true;
            }
            else if(b == hotWater || b == hotWaterStationary)
                check = true;
            else if(b == lava || b == lavaStationary)
                check = true;

            if(check)
                break;
        }

        this.active = check;
    }

    public void updateFluids(){

        Block b;

        //gets total height of water over the hypocaust
        int h = 0;
        for(int i = 1;i<=3;i++){
            b = worldObj.getBlock(xCoord,yCoord+i,zCoord);
            if(b==freshWater||b==freshWaterStationary||b==bathWater||b==bathWaterStatic)
                h++;
        }

        //updates fluid blocks
            for(int y = 1;y<=h;y++){
                for(int x = minX;x<=maxX;x++){
                    for(int z = minZ;z<=maxZ;z++){
                        b = worldObj.getBlock(x,yCoord+y,z);
                        if(active&&(b==freshWater||b==freshWaterStationary)) {
                            worldObj.setBlock(x, yCoord+y, z, bathWaterStatic);
                        }
                        else if(!active&&(b==bathWater||b==bathWaterStatic)) {
                            worldObj.setBlock(x, yCoord+y, z, freshWaterStationary);
                        }
                    }
                }
            }
    }

    public void printSize(){
        SaturaLanx.log.info("X: "+minX+" - "+maxX);
        SaturaLanx.log.info("Z: "+minZ+" - "+maxZ);
    }

    public void getSize(){
        if(!worldObj.isRemote){
            int negX = 0;
            int posX = 0;
            int negZ = 0;
            int posZ = 0;
            boolean bMinX = true;
            boolean bMaxX = true;
            boolean bMinZ = true;
            boolean bMaxZ = true;

            for(int i = 1;i<=Config.hypocaustSizeCap;i++){
                if(worldObj.getBlock(xCoord-i,yCoord,zCoord)==hypocaustBlock&&bMinX)
                    negX++;
                else
                    bMinX = false;

                if(worldObj.getBlock(xCoord+i,yCoord,zCoord)==hypocaustBlock&&bMaxX)
                    posX++;
                else
                    bMaxX = false;

                if(worldObj.getBlock(xCoord,yCoord,zCoord-i)==hypocaustBlock&&bMinZ)
                    negZ++;
                else
                    bMinZ = false;

                if(worldObj.getBlock(xCoord,yCoord,zCoord+i)==hypocaustBlock&&bMaxZ)
                    posZ++;
                else
                    bMaxZ = false;
            }

            this.minX = xCoord - negX;
            this.maxX = xCoord + posX;
            this.minZ = zCoord - negZ;
            this.maxZ = zCoord + posZ;

        }
    }

    public void checkIntegrity(){
        boolean c = true;
        Block b;

        for(int x = minX;x<=maxX;x++)
            for(int z = minZ;z<=maxZ;z++){
                b = worldObj.getBlock(x,yCoord,z);
                if(!(b==hypocaustBlock||b==hypocaustControlBlock)) {
                    c = false;
                    break;
                }
            }

        this.formed = c;
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
