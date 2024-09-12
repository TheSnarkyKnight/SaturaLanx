package com.snark.saturalanx.renders.blocks;

import com.dunk.tfc.Render.RenderBlocksWithRotation;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;

public class RenderClayCandlestick implements ISimpleBlockRenderingHandler {

    public RenderClayCandlestick(){

    }

    @Override
    public void renderInventoryBlock(Block block, int i, int i1, RenderBlocks renderBlocks) {

    }

    @Override
    public boolean renderWorldBlock(IBlockAccess iBlockAccess, int i, int i1, int i2, Block block, int i3, RenderBlocks renderBlocks) {
        RenderBlocksWithRotation render;


        return true;
    }

    @Override
    public boolean shouldRender3DInInventory(int i) {
        return false;
    }

    @Override
    public int getRenderId() {
        return 0;
    }
}
