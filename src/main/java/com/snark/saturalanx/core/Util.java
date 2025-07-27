package com.snark.saturalanx.core;

import com.dunk.tfc.Blocks.BlockCandle;
import com.dunk.tfc.Blocks.BlockCandleOff;
import com.dunk.tfc.Blocks.Vanilla.BlockTorch;
import com.dunk.tfc.Blocks.Vanilla.BlockTorchOff;
import com.dunk.tfc.TileEntities.TEFirepit;
import com.dunk.tfc.TileEntities.TEForge;
import com.dunk.tfc.api.TFCBlocks;
import com.dunk.tfc.api.TileEntities.TEFireEntity;
import com.snark.saturalanx.blocks.decoration.TallCandlestickBlock;
import com.snark.saturalanx.blocks.decoration.TallCandlestickOffBlock;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class Util {

    public static int rgbToInt(int r, int g, int b){
        return 65536 * r + 256 * g + b;
    }

    public static boolean canPlayerLight(EntityPlayer player){
        if(player.capabilities.isCreativeMode)
            return true;

        ItemStack s;
        for(int i=0;i<9;i++){
            s = player.inventory.mainInventory[i];
            if(s!=null){
                if(s.getItem() == Item.getItemFromBlock(TFCBlocks.torch))
                    return true;
                else if(Block.getBlockFromItem(s.getItem()) instanceof BlockCandle)
                    return true;
                else if(s.getItem() == ItemSetup.slowmatch && s.stackTagCompound != null && s.stackTagCompound.getBoolean("lit"))
                    return true;
            }
        }

        return false;
    }

    public static boolean canBlockLight(int x, int y, int z, World w){
        Block b = w.getBlock(x,y,z);

        if(b instanceof BlockTorch && !(b instanceof BlockTorchOff))
            return true;

        else if(b instanceof BlockCandle && !(b instanceof BlockCandleOff))
            return true;

        else if(b instanceof TallCandlestickBlock && !(b instanceof TallCandlestickOffBlock))
            return true;

        else if(b == TFCBlocks.firepit || b == TFCBlocks.forge) {
            TEFireEntity f = (TEFireEntity) w.getTileEntity(x, y, z);
            return f != null && f.fireTemp > 1;
        }

        return false;

    }

    public static void burnSurroundings(World world, int x, int y, int z, int dX, int dY, int dZ) {
        if (!world.isRemote) {
            int i, j, k;

            //x axis

            for (int h = 0; h <= dX; h++) {
                if (world.isAirBlock(x + h, y, z) || world.getBlock(x + h, y, z).isFlammable(world, x + h, y, z, ForgeDirection.WEST)) {
                    i = x + h;
                    if (world.getBlock(x + h, y, z).isFlammable(world, x + h, y, z, ForgeDirection.WEST) || world.getBlock(i - 1, y, z).isFlammable(world, i - 1, y, z, ForgeDirection.EAST) || world.getBlock(i + 1, y, z).isFlammable(world, i + 1, y, z, ForgeDirection.WEST) || world.getBlock(i, y + h, z).isFlammable(world, i, y + h, z, ForgeDirection.DOWN) || world.getBlock(i, y - h, z).isFlammable(world, i, y - h, z, ForgeDirection.UP) || world.getBlock(i, y, z - h).isFlammable(world, i, y, z - h, ForgeDirection.SOUTH) || world.getBlock(i, y, z + h).isFlammable(world, i, y, z + h, ForgeDirection.NORTH)) {
                        world.setBlock(i, y, z, Blocks.fire);
                    }
                }

                if (world.isAirBlock(x - h, y, z) || world.getBlock(x - h, y, z).isFlammable(world, x - h, y, z, ForgeDirection.EAST)) {
                    i = x - h;
                    if (world.getBlock(x - 1, y, z).isFlammable(world, x - 1, y, z, ForgeDirection.EAST) || world.getBlock(i - 1, y, z).isFlammable(world, i - 1, y, z, ForgeDirection.EAST) || world.getBlock(i + 1, y, z).isFlammable(world, i + 1, y, z, ForgeDirection.WEST) || world.getBlock(i, y + h, z).isFlammable(world, i, y + h, z, ForgeDirection.DOWN) || world.getBlock(i, y - h, z).isFlammable(world, i, y - h, z, ForgeDirection.UP) || world.getBlock(i, y, z - h).isFlammable(world, i, y, z - h, ForgeDirection.SOUTH) || world.getBlock(i, y, z + h).isFlammable(world, i, y, z + 1, ForgeDirection.NORTH)) {
                        world.setBlock(i, y, z, Blocks.fire);
                    }
                }
            }


            //z axis
            for (int h = 0; h <= dZ; h++) {
                if (world.isAirBlock(x, y, z - h) || world.getBlock(x, y, z - h).isFlammable(world, x, y, z - h, ForgeDirection.SOUTH)) {
                    k = z - h;
                    if (world.getBlock(x, y, z - h).isFlammable(world, x, y, z - h, ForgeDirection.SOUTH) || world.getBlock(x - h, y, k).isFlammable(world, x - h, y, k, ForgeDirection.EAST) || world.getBlock(x + h, y, k).isFlammable(world, x + h, y, k, ForgeDirection.WEST) || world.getBlock(x, y + h, k).isFlammable(world, x, y + h, k, ForgeDirection.DOWN) || world.getBlock(x, y - h, k).isFlammable(world, x, y - h, k, ForgeDirection.UP) || world.getBlock(x, y, k - 1).isFlammable(world, x, y, k - 1, ForgeDirection.SOUTH) || world.getBlock(x, y, k + 1).isFlammable(world, x, y, k + 1, ForgeDirection.NORTH)) {
                        world.setBlock(x, y, k, Blocks.fire);
                    }
                }

                if (world.isAirBlock(x, y, z + h) || world.getBlock(x, y, z + h).isFlammable(world, x, y, z + h, ForgeDirection.NORTH)) {
                    k = z + h;
                    if (world.getBlock(x, y, z + h).isFlammable(world, x, y, z + h, ForgeDirection.NORTH) || world.getBlock(x - h, y, k).isFlammable(world, x - h, y, k, ForgeDirection.EAST) || world.getBlock(x + h, y, k).isFlammable(world, x + h, y, k, ForgeDirection.WEST) || world.getBlock(x, y + h, k).isFlammable(world, x, y + h, k, ForgeDirection.DOWN) || world.getBlock(x, y - h, k).isFlammable(world, x, y - h, k, ForgeDirection.UP) || world.getBlock(x, y, k - 1).isFlammable(world, x, y, k - 1, ForgeDirection.SOUTH) || world.getBlock(x, y, k + 1).isFlammable(world, x, y, k + 1, ForgeDirection.NORTH)) {
                        world.setBlock(x, y, k, Blocks.fire);
                    }
                }
            }

            //y axis
            for (int h = 0; h <= dY; h++) {
                if (world.isAirBlock(x, y + h, z) || world.getBlock(x, y + h, z).isFlammable(world, x, y + h, z, ForgeDirection.DOWN)) {
                    j = y + h;
                    if (world.getBlock(x, y + h, z).isFlammable(world, x, y + h, z, ForgeDirection.DOWN) || world.getBlock(x - h, j, z).isFlammable(world, x - h, j, z, ForgeDirection.EAST) || world.getBlock(x + h, j, z).isFlammable(world, x + h, j, z, ForgeDirection.WEST) || world.getBlock(x, j + 1, z).isFlammable(world, x, j + 1, z, ForgeDirection.DOWN) || world.getBlock(x, j - 1, z).isFlammable(world, x, j - 1, z, ForgeDirection.UP) || world.getBlock(x, j, z - h).isFlammable(world, x, j, z - h, ForgeDirection.SOUTH) || world.getBlock(x, j, z + h).isFlammable(world, x, j, z + h, ForgeDirection.NORTH)) {
                        world.setBlock(x, j, z, Blocks.fire);
                    }
                }

                if (world.isAirBlock(x, y - h, z) || world.getBlock(x, y - h, z).isFlammable(world, x, y - h, z, ForgeDirection.UP)) {
                    j = y - h;
                    if (world.getBlock(x, y - h, z).isFlammable(world, x, y - h, z, ForgeDirection.UP) || world.getBlock(x - h, j, z).isFlammable(world, x - h, j, z, ForgeDirection.EAST) || world.getBlock(x + h, j, z).isFlammable(world, x + h, j, z, ForgeDirection.WEST) || world.getBlock(x, j + 1, z).isFlammable(world, x, j + 1, z, ForgeDirection.DOWN) || world.getBlock(x, j - 1, z).isFlammable(world, x, j - 1, z, ForgeDirection.UP) || world.getBlock(x, j, z - h).isFlammable(world, x, j, z - h, ForgeDirection.SOUTH) || world.getBlock(x, j, z + h).isFlammable(world, x, j, z + h, ForgeDirection.NORTH)) {
                        world.setBlock(x, j, z, Blocks.fire);
                    }
                }
            }
        }
    }


        public static void burnSurroundings (World world,int x, int y, int z){
            Util.burnSurroundings(world, x, y, z, 1, 1, 1);
        }

    }

