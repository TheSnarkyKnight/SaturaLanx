package com.snark.saturalanx.blocks.functional;

import com.dunk.tfc.Blocks.BlockTerraContainer;
import com.dunk.tfc.api.TFCBlocks;
import com.snark.saturalanx.SaturaLanx;
import com.snark.saturalanx.TE.HypocaustTE;
import com.snark.saturalanx.core.BlockSetup;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class HypocaustControlBlock extends BlockTerraContainer {
    public HypocaustControlBlock(){
        super(Material.rock);
        this.setHardness(5.0F);
        this.setStepSound(soundTypeStone);
        this.setCreativeTab(SaturaLanx.tab);
        this.setTickRandomly(true);
        this.setBlockName("HypocaustControlBlock");
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        return TFCBlocks.bricks.getIcon(side,meta);
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public int getRenderType() {
        return BlockSetup.hypocaustRenderId;
    }

    @Override
    public TileEntity createNewTileEntity(World var1, int var2) {
        return new HypocaustTE();
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        if (!world.isRemote) {
            TileEntity te = world.getTileEntity(x, y, z);
            if (te instanceof HypocaustTE) {
                HypocaustTE h = (HypocaustTE) te;
                h.getSize();
                h.checkIntegrity();
                h.getActiveHeatSource();
                h.updateFluids();

                //System.out.println(h.isActive());
                //System.out.println(h.isFormed());
                //System.out.println(h.getMinX() + " - " + h.getMaxX());
                //System.out.println(h.getMinZ() + " - " + h.getMaxZ());
                return true;
            }
        }
        return false;
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack is) {
        world.setBlockMetadataWithNotify(x,y,z, MathHelper.floor_double((double)(entity.rotationYaw*4.0/360+0.5)) & 3,3);
    }
}
