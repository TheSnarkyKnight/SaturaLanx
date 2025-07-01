package com.snark.saturalanx.renders.blocks;

import com.dunk.tfc.Core.TFC_Textures;
import com.dunk.tfc.api.TFCBlocks;
import com.snark.saturalanx.core.BlockSetup;
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
        renderInvBlock(block,meta,render);
        render.setRenderBounds(0,0.8,0,1,1,1);
        renderInvBlock(block,meta,render);

        if(block == BlockSetup.hypocaustControlBlock)
            render.setOverrideBlockTexture(TFC_Textures.sheetBrass);
        render.setRenderBounds(0,0.2,0,0.2,0.8,0.2);
        renderInvBlock(block,meta,render);
        render.setRenderBounds(0.8,0.2,0,1,0.8,0.2);
        renderInvBlock(block,meta,render);
        render.setRenderBounds(0,0.2,0.8,0.2,0.8,1);
        renderInvBlock(block,meta,render);
        render.setRenderBounds(0.8,0.2,0.8,1,0.8,1);
        renderInvBlock(block,meta,render);

        render.clearOverrideBlockTexture();
        GL11.glPopMatrix();
    }

    public static void renderInvBlock(Block block, int m, RenderBlocks renderer) {
        Tessellator var14 = Tessellator.instance;
        int meta = m;

        var14.startDrawingQuads();
        var14.setNormal(0.0F, -1.0F, 0.0F);
        renderer.renderFaceYNeg(block, 0.0, 0.0, 0.0, block.getIcon(0, meta));
        var14.draw();
        var14.startDrawingQuads();
        var14.setNormal(0.0F, 1.0F, 0.0F);
        renderer.renderFaceYPos(block, 0.0, 0.0, 0.0, block.getIcon(1, meta));
        var14.draw();
        var14.startDrawingQuads();
        var14.setNormal(0.0F, 0.0F, -1.0F);
        renderer.renderFaceXNeg(block, 0.0, 0.0, 0.0, block.getIcon(2, meta));
        var14.draw();
        var14.startDrawingQuads();
        var14.setNormal(0.0F, 0.0F, 1.0F);
        renderer.renderFaceXPos(block, 0.0, 0.0, 0.0, block.getIcon(3, meta));
        var14.draw();
        var14.startDrawingQuads();
        var14.setNormal(-1.0F, 0.0F, 0.0F);
        renderer.renderFaceZNeg(block, 0.0, 0.0, 0.0, block.getIcon(4, meta));
        var14.draw();
        var14.startDrawingQuads();
        var14.setNormal(1.0F, 0.0F, 0.0F);
        renderer.renderFaceZPos(block, 0.0, 0.0, 0.0, block.getIcon(5, meta));
        var14.draw();
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
