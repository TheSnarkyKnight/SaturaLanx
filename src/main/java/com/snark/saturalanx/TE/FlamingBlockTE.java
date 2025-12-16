package com.snark.saturalanx.TE;

import com.dunk.tfc.TileEntities.NetworkTileEntity;
import com.snark.saturalanx.core.Config;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class FlamingBlockTE extends TileEntity {
    int count;

    public FlamingBlockTE(){
        super();
        count = Config.incendiaryPotBlockLife * 20;
    }

    @Override
    public void updateEntity(){
        count--;
        if(!this.worldObj.isRemote&&count<=0){
            this.worldObj.setBlock(this.xCoord,this.yCoord,this.zCoord, Blocks.air);
        }

    }

    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        this.count = nbt.getInteger("count");
    }

    public void writeToNBT(NBTTagCompound nbt){
        super.writeToNBT(nbt);
        nbt.setInteger("count",this.count);
    }

}
