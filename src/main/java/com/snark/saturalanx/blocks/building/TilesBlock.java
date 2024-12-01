package com.snark.saturalanx.blocks.building;

import com.dunk.tfc.Blocks.BlockTerra;
import com.dunk.tfc.Blocks.BlockTerraContainer;
import com.dunk.tfc.Core.TFC_Core;
import com.dunk.tfc.TileEntities.TERoad;
import com.dunk.tfc.api.TFCBlocks;
import com.snark.saturalanx.TE.TileTE;
import com.snark.saturalanx.core.BlockSetup;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import static com.snark.saturalanx.SaturaLanx.MODID;
import static com.snark.saturalanx.SaturaLanx.tab;

public class TilesBlock extends BlockTerraContainer {
    private String[] names;
    private IIcon[] icons;

    public TilesBlock(){
        super(Material.rock);
        this.setHardness(8);
        this.setCreativeTab(null);
    }

    public int getRenderType() {
        return BlockSetup.tileRenderId;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public TilesBlock setNames(String[] names){
        this.names = names;
        this.icons = new IIcon[names.length];

        return this;
    }

    public String[] getNames(){
        return names;
    }

    public IIcon getIcon(int side, int meta){
        if(side == 1) {
            if (meta > names.length)
                meta = 0;
            return icons[meta];
        }
        else
            return TFCBlocks.dirt.getIcon(side, meta);
    }

    @Override
    public void registerBlockIcons(IIconRegister registerer) {
        for(int i =0; i < names.length; i++)
            icons[i] = registerer.registerIcon(MODID + ":tiles/" +this.names[i]);
    }

    @Override
    public void getSubBlocks(Item item, CreativeTabs tab, List list) {
        for (int i = 0; i < names.length; i ++) {
            list.add(new ItemStack(item, 1, i));
        }
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public Item getItemDropped(int i, Random random, int j) {
        return null;
    }

    public boolean hasTileEntity() {
        return true;
    }

    public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side) {
        return true;
    }

    public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
    }

    public void onBlockDestroyedByExplosion(World world, int x, int y, int z, Explosion explosion) {
    }

    public TileEntity createNewTileEntity(World world, int metadata) {
        return new TileTE();
    }

    @Override
    public void onBlockAdded(World world, int i, int j, int k) {

    }

    public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbor) {
        if (!world.isSideSolid(x, y - 1, z, ForgeDirection.UP)) {
            this.dropBlocksInWorld(world, x, y, z);
            world.setBlockToAir(x, y, z);
        }
    }

    public void onBlockHarvested(World world, int x, int y, int z, int side, EntityPlayer player) {
        if (!world.isRemote) {
            this.dropBlocksInWorld(world, x, y, z);
        }
    }

    protected void dropBlocksInWorld(World world, int x, int y, int z) {
        if (!world.isRemote) {
            TileEntity te = world.getTileEntity(x, y, z);
            if (te != null && te instanceof TileTE) {
                TileTE tile = (TileTE) te;
                int dirtId = tile.getDirtId();
                int gravelId = tile.getGravelId();
                ArrayList<ItemStack> drops = new ArrayList();
                if (dirtId > 15) {
                    drops.add(new ItemStack(TFCBlocks.dirt2, 1, dirtId - 16));
                } else if (dirtId >= 0) {
                    drops.add(new ItemStack(TFCBlocks.dirt, 1, dirtId));
                }

                if (gravelId > 15) {
                    drops.add(new ItemStack(TFCBlocks.gravel2, 1, gravelId - 16));
                } else if (gravelId >= 0) {
                    drops.add(new ItemStack(TFCBlocks.gravel, 1, gravelId));
                }

                for(ItemStack i : drops) {
                    EntityItem ei = new EntityItem(world, (double)x, (double)y, (double)z, i);
                    world.spawnEntityInWorld(ei);
                }
            }
        }

    }

    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
        if(entity instanceof EntityLivingBase && !world.isRemote) {
            entity.motionX *= 1.05;
            entity.motionZ *= 1.05;
        }

        super.onEntityCollidedWithBlock(world, x, y, z, entity);
    }
}
