package com.snark.saturalanx.blocks.functional;

import com.dunk.tfc.api.TFCBlocks;
import com.snark.saturalanx.core.BlockSetup;
import com.dunk.tfc.Blocks.BlockTerra;
import com.snark.saturalanx.SaturaLanx;
import net.minecraft.block.material.Material;
import net.minecraft.util.IIcon;

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
    public int getRenderType() {
        return BlockSetup.hypocaustRenderId;
    }


}
