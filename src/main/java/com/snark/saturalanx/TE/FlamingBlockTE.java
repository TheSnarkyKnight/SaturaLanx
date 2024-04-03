package com.snark.saturalanx.TE;

import com.snark.saturalanx.core.Config;
import net.minecraft.init.Blocks;
import net.minecraft.server.gui.IUpdatePlayerListBox;
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
}
