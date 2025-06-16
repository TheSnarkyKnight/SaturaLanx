package com.snark.saturalanx.TE;

import com.dunk.tfc.Items.ItemDrink;
import com.dunk.tfc.TileEntities.NetworkTileEntity;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class ChaliceTE extends NetworkTileEntity {
    private ItemDrink drink;
    private int num,facing;

    public ChaliceTE(){
        this.drink = null;
        this.num = 1;
        this.facing = 1;
    }

    public void setDrink(ItemDrink drink){
        this.drink = drink;
    }

    public void clearDrink(){
        this.drink = null;
    }

    public ItemDrink getDrink(){
        return this.drink;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getFacing() {
        return facing;
    }

    public void setFacing(int facing) {
        this.facing = facing;
    }

    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        if (nbt.hasKey("drink"))
        {
            NBTTagCompound var4 = nbt.getCompoundTag("drink");
            ItemStack is = ItemStack.loadItemStackFromNBT(var4);
            if(is != null)
                this.drink = (ItemDrink) is.getItem();
            else
                this.drink = null;
        }
        else
        {
            this.drink = null;
        }
        this.num = nbt.getInteger("num");
        this.facing = nbt.getInteger("facing");
    }

    public void writeToNBT(NBTTagCompound nbt){
        super.writeToNBT(nbt);
        NBTTagCompound var4 = new NBTTagCompound();
        ItemStack is = new ItemStack(this.drink,1,0);
        is.writeToNBT(var4);
        nbt.setTag("drink", var4);
        nbt.setInteger("num",this.num);
        nbt.setInteger("facing",this.facing);
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
