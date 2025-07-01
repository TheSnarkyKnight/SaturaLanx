package com.snark.saturalanx.blocks.functional;

import com.dunk.tfc.api.TFCBlocks;
import com.snark.saturalanx.core.BlockSetup;
import com.dunk.tfc.Blocks.BlockTerra;
import com.snark.saturalanx.SaturaLanx;
import net.minecraft.block.material.Material;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;

public class HypocaustBlock extends BlockTerra {
    public HypocaustBlock(){
        super(Material.rock);
        this.setHardness(5.0F);
        this.setStepSound(soundTypeStone);
        this.setCreativeTab(SaturaLanx.tab);
        this.setBlockName("HypocaustBlock");
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
    public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side) {
        return side == ForgeDirection.UP || side == ForgeDirection.DOWN;
    }

    @Override
    public int getRenderType() {
        return BlockSetup.hypocaustRenderId;
    }


}
