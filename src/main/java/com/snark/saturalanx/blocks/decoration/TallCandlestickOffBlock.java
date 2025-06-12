package com.snark.saturalanx.blocks.decoration;

import com.dunk.tfc.Blocks.BlockCandle;
import com.dunk.tfc.Blocks.BlockCandleOff;
import com.dunk.tfc.Core.TFC_Time;
import com.dunk.tfc.TileEntities.TELightEmitter;
import com.dunk.tfc.api.TFCBlocks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Random;

import static com.snark.saturalanx.SaturaLanx.tab;

public class TallCandlestickOffBlock extends TallCandlestickBlock {
    public TallCandlestickOffBlock(){
        super();
        this.setCreativeTab(tab);
    }

    public TallCandlestickOffBlock(Block alternate){
        this();
        this.alternateBlock = alternate;
    }

    public int getLightValue(IBlockAccess world, int x, int y, int z) {
        return 0;
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        if (!world.isRemote) {
            int meta = world.getBlockMetadata(x, y, z);
            if(meta<=1){
                TELightEmitter te;
                if (player.inventory.getCurrentItem() != null && player.inventory.getCurrentItem().getItem() == Item.getItemFromBlock(TFCBlocks.torch)) {
                    world.setBlock(x, y, z, this.getAlternate(), meta, 3);
                    world.setBlock(x, y - 1, z, this.alternateBlock, 2, 3);
                    if (world.getTileEntity(x, y, z) instanceof TELightEmitter) {
                        te = (TELightEmitter) world.getTileEntity(x, y, z);
                        te.hourPlaced = (int) TFC_Time.getTotalHours();
                    }
                } else if (player.inventory.getCurrentItem() != null && getBlockFromItem(player.inventory.getCurrentItem().getItem()) instanceof BlockCandle && !(getBlockFromItem(player.inventory.getCurrentItem().getItem()) instanceof BlockCandleOff)) {
                    world.setBlock(x, y, z, this.getAlternate(), meta, 3);
                    world.setBlock(x, y - 1, z, this.alternateBlock, 2, 3);
                    if (world.getTileEntity(x, y, z) instanceof TELightEmitter) {
                        te = (TELightEmitter) world.getTileEntity(x, y, z);
                        te.hourPlaced = (int) TFC_Time.getTotalHours();
                    }
                } else if (player.inventory.getCurrentItem() != null && getBlockFromItem(player.inventory.getCurrentItem().getItem()) instanceof TallCandlestickBlock && !(getBlockFromItem(player.inventory.getCurrentItem().getItem()) instanceof TallCandlestickOffBlock)) {
                    world.setBlock(x, y, z, this.getAlternate(), meta, 3);
                    world.setBlock(x, y - 1, z, this.alternateBlock, 2, 3);
                    if (world.getTileEntity(x, y, z) instanceof TELightEmitter) {
                        te = (TELightEmitter) world.getTileEntity(x, y, z);
                        te.hourPlaced = (int) TFC_Time.getTotalHours();
                    }
                }
            }
        }

        return super.onBlockActivated(world, x, y, z, player, side, hitX, hitY, hitZ);
    }

    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        return super.getDrops(world, x, y, z, metadata, fortune);
    }

    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, int x, int y, int z, Random rand) {
    }

    public void updateTick(World world, int x, int y, int z, Random rand) {

    }
}
