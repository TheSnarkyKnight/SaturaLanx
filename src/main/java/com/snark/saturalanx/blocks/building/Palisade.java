package com.snark.saturalanx.blocks.building;

import com.dunk.tfc.Blocks.BlockTerra;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.List;

import static com.snark.saturalanx.SaturaLanx.tab;

public class Palisade extends BlockTerra {
    public Palisade(){
        super(Material.wood);
        this.setCreativeTab(tab);
        this.setHardness(5.0F);
        this.setResistance(6.0F);
    }

    @Override
    public void addCollisionBoxesToList(World p_addCollisionBoxesToList_1_, int p_addCollisionBoxesToList_2_, int p_addCollisionBoxesToList_3_, int p_addCollisionBoxesToList_4_, AxisAlignedBB p_addCollisionBoxesToList_5_, List p_addCollisionBoxesToList_6_, Entity p_addCollisionBoxesToList_7_) {
        boolean var8 = this.canConnectFenceTo(p_addCollisionBoxesToList_1_, p_addCollisionBoxesToList_2_, p_addCollisionBoxesToList_3_, p_addCollisionBoxesToList_4_ - 1);
        boolean var9 = this.canConnectFenceTo(p_addCollisionBoxesToList_1_, p_addCollisionBoxesToList_2_, p_addCollisionBoxesToList_3_, p_addCollisionBoxesToList_4_ + 1);
        boolean var10 = this.canConnectFenceTo(p_addCollisionBoxesToList_1_, p_addCollisionBoxesToList_2_ - 1, p_addCollisionBoxesToList_3_, p_addCollisionBoxesToList_4_);
        boolean var11 = this.canConnectFenceTo(p_addCollisionBoxesToList_1_, p_addCollisionBoxesToList_2_ + 1, p_addCollisionBoxesToList_3_, p_addCollisionBoxesToList_4_);
        float var12 = 0.375F;
        float var13 = 0.625F;
        float var14 = 0.375F;
        float var15 = 0.625F;
        if (var8) {
            var14 = 0.0F;
        }

        if (var9) {
            var15 = 1.0F;
        }

        if (var8 || var9) {
            this.setBlockBounds(var12, 0.0F, var14, var13, 1.5F, var15);
            super.addCollisionBoxesToList(p_addCollisionBoxesToList_1_, p_addCollisionBoxesToList_2_, p_addCollisionBoxesToList_3_, p_addCollisionBoxesToList_4_, p_addCollisionBoxesToList_5_, p_addCollisionBoxesToList_6_, p_addCollisionBoxesToList_7_);
        }

        var14 = 0.375F;
        var15 = 0.625F;
        if (var10) {
            var12 = 0.0F;
        }

        if (var11) {
            var13 = 1.0F;
        }

        if (var10 || var11 || !var8 && !var9) {
            this.setBlockBounds(var12, 0.0F, var14, var13, 1.5F, var15);
            super.addCollisionBoxesToList(p_addCollisionBoxesToList_1_, p_addCollisionBoxesToList_2_, p_addCollisionBoxesToList_3_, p_addCollisionBoxesToList_4_, p_addCollisionBoxesToList_5_, p_addCollisionBoxesToList_6_, p_addCollisionBoxesToList_7_);
        }

        if (var8) {
            var14 = 0.0F;
        }

        if (var9) {
            var15 = 1.0F;
        }

        this.setBlockBounds(var12, 0.0F, var14, var13, 1.0F, var15);
    }



    public boolean canConnectFenceTo(IBlockAccess p_canConnectFenceTo_1_, int p_canConnectFenceTo_2_, int p_canConnectFenceTo_3_, int p_canConnectFenceTo_4_) {
        Block var5 = p_canConnectFenceTo_1_.getBlock(p_canConnectFenceTo_2_, p_canConnectFenceTo_3_, p_canConnectFenceTo_4_);
        if (var5 != this && var5 != Blocks.fence_gate) {
            if (var5.getMaterial().isOpaque() && var5.renderAsNormalBlock()) {
                return var5.getMaterial() != Material.gourd;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public boolean getBlocksMovement(IBlockAccess p_getBlocksMovement_1_, int p_getBlocksMovement_2_, int p_getBlocksMovement_3_, int p_getBlocksMovement_4_) {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess p_shouldSideBeRendered_1_, int p_shouldSideBeRendered_2_, int p_shouldSideBeRendered_3_, int p_shouldSideBeRendered_4_, int p_shouldSideBeRendered_5_) {
        return true;
    }


}
