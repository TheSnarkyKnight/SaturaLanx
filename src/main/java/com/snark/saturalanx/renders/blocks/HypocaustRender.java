package com.snark.saturalanx.renders.blocks;

import com.dunk.tfc.Core.TFC_Textures;
import com.dunk.tfc.api.TFCBlocks;
import com.snark.saturalanx.core.BlockSetup;
import com.snark.saturalanx.core.Util;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;

public class HypocaustRender implements ISimpleBlockRenderingHandler {
    public HypocaustRender(){}

    @Override
    public void renderInventoryBlock(Block block, int meta, int modelId, RenderBlocks render) {
        GL11.glPushMatrix();

        render.setOverrideBlockTexture(TFCBlocks.bricks.getIcon(0,0));
        render.setRenderBounds(0,0,0,1,0.2,1);
        Util.renderInvBlock(block,meta,render);
        render.setRenderBounds(0,0.8,0,1,1,1);
        Util.renderInvBlock(block,meta,render);

        if(block == BlockSetup.hypocaustControlBlock)
            render.setOverrideBlockTexture(TFC_Textures.sheetBrass);
        render.setRenderBounds(0,0.2,0,0.2,0.8,0.2);
        Util.renderInvBlock(block,meta,render);
        render.setRenderBounds(0.8,0.2,0,1,0.8,0.2);
        Util.renderInvBlock(block,meta,render);
        render.setRenderBounds(0,0.2,0.8,0.2,0.8,1);
        Util.renderInvBlock(block,meta,render);
        render.setRenderBounds(0.8,0.2,0.8,1,0.8,1);
        Util.renderInvBlock(block,meta,render);

        render.clearOverrideBlockTexture();
        GL11.glPopMatrix();
    }



    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks render) {

        render.setOverrideBlockTexture(TFCBlocks.bricks.getIcon(0,0));
        render.setRenderAllFaces(true);

        render.setRenderBounds(0,0,0,1,0.2,1);
        render.renderStandardBlock(block,x,y,z);
        render.setRenderBounds(0,0.8,0,1,1,1);
        render.renderStandardBlock(block,x,y,z);

        if(world.getBlock(x,y,z) == BlockSetup.hypocaustControlBlock)
            render.setOverrideBlockTexture(TFC_Textures.sheetBrass);
        render.setRenderBounds(0,0.2,0,0.2,0.8,0.2);
        render.renderStandardBlock(block,x,y,z);
        render.setRenderBounds(0.8,0.2,0,1,0.8,0.2);
        render.renderStandardBlock(block,x,y,z);
        render.setRenderBounds(0,0.2,0.8,0.2,0.8,1);
        render.renderStandardBlock(block,x,y,z);
        render.setRenderBounds(0.8,0.2,0.8,1,0.8,1);
        render.renderStandardBlock(block,x,y,z);

        render.clearOverrideBlockTexture();

        return true;
    }

    @Override
    public boolean shouldRender3DInInventory(int i) {
        return true;
    }

    @Override
    public int getRenderId() {
        return 0;
    }
}
