package com.snark.saturalanx.TE;

import com.dunk.tfc.TileEntities.NetworkTileEntity;
import com.dunk.tfc.api.TFCBlocks;
import com.snark.saturalanx.blocks.building.TilesBlock;
import com.snark.saturalanx.core.BlockSetup;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TileTE extends TileEntity {

    private int dirtId = -1;
    private int gravelId = -1;

    public TileTE(){

    }

    public boolean shouldRefresh(Block oldBlock, Block newBlock, int oldMeta, int newMeta, World world, int x, int y, int z) {
        return oldBlock != TFCBlocks.stoneRoad||newBlock!=BlockSetup.floorTiles;
    }

    public void setDirtId(int dirtId) {
        this.dirtId = dirtId;
    }

    public void setGravelId(int gravelId) {
        this.gravelId = gravelId;
    }

    public int getDirtId() {
        return dirtId;
    }

    public int getGravelId() {
        return gravelId;
    }

    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        this.dirtId = nbt.getInteger("dirtId");
        this.gravelId = nbt.getInteger("gravelId");
    }

    public void writeToNBT(NBTTagCompound nbt){
        super.writeToNBT(nbt);
        nbt.setInteger("dirtId",this.dirtId);
        nbt.setInteger("gravelId",this.gravelId);
    }

}
