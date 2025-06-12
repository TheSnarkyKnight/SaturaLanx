package com.snark.saturalanx.renders.blocks;

import com.snark.saturalanx.blocks.building.StockadeBlock;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;

public class StockadeRender implements ISimpleBlockRenderingHandler {
    @Override
    public void renderInventoryBlock(Block block, int meta, int modelId, RenderBlocks render) {

        double bmod = 0.0125;
        int hmod = 15;

        GL11.glPushMatrix();
        GL11.glTranslatef(0,-0.2F,0);
        render.clearOverrideBlockTexture();
        render.setOverrideBlockTexture(block.getIcon(0,0));
        render.setRenderBounds(0,0,0,1,1,0.25);
        renderInvBlock(block,meta,render);
        GL11.glPopMatrix();

        GL11.glPushMatrix();
        GL11.glTranslatef(0,0.8F,0);
        render.setRenderBounds(0, 0, 0, 1, 0.7, 0.25);
        renderInvBlock(block,meta,render);
        render.setOverrideBlockTexture(block.getIcon(0,1));
        for(int i = 1;i<=5;i++) {
            render.setRenderBounds(0+(bmod*i), 0.7+(((float)i-1)/hmod), 0.0+(bmod*i), 0.25-(bmod*i), 0.7+(((float)i)/hmod), 0.25-(bmod*i));
            renderInvBlock(block,meta,render);

            render.setRenderBounds(0.25+(bmod*i), 0.7+(((float)i-1)/hmod), 0.0+(bmod*i), 0.50-(bmod*i), 0.7+(((float)i)/hmod), 0.25-(bmod*i));
            renderInvBlock(block,meta,render);

            render.setRenderBounds(0.5+(bmod*i), 0.7+(((float)i-1)/hmod), 0.0+(bmod*i), 0.75-(bmod*i), 0.7+(((float)i)/hmod), 0.25-(bmod*i));
            renderInvBlock(block,meta,render);

            render.setRenderBounds(0.75+(bmod*i), 0.7+(((float)i-1)/hmod), 0.0+(bmod*i), 1-(bmod*i), 0.7+(((float)i)/hmod), 0.25-(bmod*i));
            renderInvBlock(block,meta,render);
        }
        GL11.glPopMatrix();

        render.clearOverrideBlockTexture();
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks render) {
        int meta = world.getBlockMetadata(x,y,z);
        double bmod = 0.0125;
        int hmod = 15;
        render.setOverrideBlockTexture(block.getIcon(0,0));
        render.renderAllFaces = true;
        switch (meta){
            case 0:
                if(world.getBlock(x,y,z-1)instanceof StockadeBlock && world.getBlockMetadata(x, y, z-1) == 1&&!(world.getBlock(x-1,y,z)instanceof StockadeBlock))
                    render.setRenderBounds(0.75,0,0,1,1,0.25);
                else if (world.getBlock(x,y,z-1)instanceof StockadeBlock &&world.getBlockMetadata(x,y,z-1)==3&&!(world.getBlock(x+1,y,z)instanceof StockadeBlock))
                    render.setRenderBounds(0,0,0,0.25,1,0.25);
                else
                    render.setRenderBounds(0,0,0,1,1,0.25);
                render.renderStandardBlock(block,x,y,z);
                break;
            case 1:
                render.setRenderBounds(0.75,0,0,1,1,1);
                render.renderStandardBlock(block,x,y,z);
                break;
            case 2:
                if(world.getBlock(x,y,z+1)instanceof StockadeBlock &&world.getBlockMetadata(x, y, z+1) == 1&&!(world.getBlock(x-1,y,z)instanceof StockadeBlock))
                    render.setRenderBounds(0.75,0,0.75,1,1,1);
                else if (world.getBlock(x,y,z+1)instanceof StockadeBlock &&world.getBlockMetadata(x,y,z+1)==3&&!(world.getBlock(x+1,y,z)instanceof StockadeBlock))
                    render.setRenderBounds(0,0,0.75,0.25,1,1);
                else
                    render.setRenderBounds(0,0,0.75,1,1,1);
                render.renderStandardBlock(block,x,y,z);
                break;
            case 3:
                render.setRenderBounds(0,0,0,0.25,1,1);
                render.renderStandardBlock(block,x,y,z);
                break;
            case 4:
                if(world.getBlock(x,y,z-1)instanceof StockadeBlock && world.getBlockMetadata(x, y, z-1) == 5&&!(world.getBlock(x-1,y,z)instanceof StockadeBlock)) {
                    render.setRenderBounds(0.75, 0, 0, 1, 0.7, 0.25);
                    render.renderStandardBlock(block,x,y,z);

                    render.setOverrideBlockTexture(block.getIcon(0,1));
                    for(int i = 1;i<=5;i++) {
                        render.setRenderBounds(0.75+(bmod*i), 0.7+(((float)i-1)/hmod), 0.0+(bmod*i), 1-(bmod*i), 0.7+(((float)i)/hmod), 0.25-(bmod*i));
                        render.renderStandardBlock(block, x, y, z);
                    }

                }
                else if (world.getBlock(x,y,z-1)instanceof StockadeBlock &&world.getBlockMetadata(x,y,z-1)==7&&!(world.getBlock(x+1,y,z)instanceof StockadeBlock)) {
                    render.setRenderBounds(0, 0, 0, 0.25, 0.7, 0.25);
                    render.renderStandardBlock(block,x,y,z);

                    render.setOverrideBlockTexture(block.getIcon(0,1));
                    for(int i = 1;i<=5;i++) {
                        render.setRenderBounds(0+(bmod*i), 0.7+(((float)i-1)/hmod), 0.0+(bmod*i), 0.25-(bmod*i), 0.7+(((float)i)/hmod), 0.25-(bmod*i));
                        render.renderStandardBlock(block, x, y, z);
                    }
                }
                else {
                    render.setRenderBounds(0, 0, 0, 1, 0.7, 0.25);
                    render.renderStandardBlock(block,x,y,z);

                    render.setOverrideBlockTexture(block.getIcon(0,1));
                    for(int i = 1;i<=5;i++) {
                        render.setRenderBounds(0+(bmod*i), 0.7+(((float)i-1)/hmod), 0.0+(bmod*i), 0.25-(bmod*i), 0.7+(((float)i)/hmod), 0.25-(bmod*i));
                        render.renderStandardBlock(block, x, y, z);

                        render.setRenderBounds(0.25+(bmod*i), 0.7+(((float)i-1)/hmod), 0.0+(bmod*i), 0.50-(bmod*i), 0.7+(((float)i)/hmod), 0.25-(bmod*i));
                        render.renderStandardBlock(block, x, y, z);

                        render.setRenderBounds(0.5+(bmod*i), 0.7+(((float)i-1)/hmod), 0.0+(bmod*i), 0.75-(bmod*i), 0.7+(((float)i)/hmod), 0.25-(bmod*i));
                        render.renderStandardBlock(block, x, y, z);

                        render.setRenderBounds(0.75+(bmod*i), 0.7+(((float)i-1)/hmod), 0.0+(bmod*i), 1-(bmod*i), 0.7+(((float)i)/hmod), 0.25-(bmod*i));
                        render.renderStandardBlock(block, x, y, z);
                    }
                }
                break;
            case 5:
                render.setRenderBounds(0.75,0,0,1,0.7,1);
                render.renderStandardBlock(block,x,y,z);

                for(int i = 1;i<=5;i++){
                    render.setRenderBounds(0.75+(bmod*i),0.7+(((float)i-1)/hmod),0+(bmod*i),1-(bmod*i),0.7+(((float)i)/hmod),0.25-(bmod*i));
                    render.renderStandardBlock(block,x,y,z);

                    render.setRenderBounds(0.75+(bmod*i),0.7+(((float)i-1)/hmod),0.25+(bmod*i),1-(bmod*i),0.7+(((float)i)/hmod),0.5-(bmod*i));
                    render.renderStandardBlock(block,x,y,z);

                    render.setRenderBounds(0.75+(bmod*i),0.7+(((float)i-1)/hmod),0.5+(bmod*i),1-(bmod*i),0.7+(((float)i)/hmod),0.75-(bmod*i));
                    render.renderStandardBlock(block,x,y,z);

                    render.setRenderBounds(0.75+(bmod*i),0.7+(((float)i-1)/hmod),0.75+(bmod*i),1-(bmod*i),0.7+(((float)i)/hmod),1-(bmod*i));
                    render.renderStandardBlock(block,x,y,z);
                }
                break;
            case 6:
                if(world.getBlock(x,y,z+1)instanceof StockadeBlock &&world.getBlockMetadata(x, y, z+1) == 5&&!(world.getBlock(x-1,y,z)instanceof StockadeBlock)) {
                    render.setRenderBounds(0.75,0,0.75,1,0.7,1);
                    render.renderStandardBlock(block,x,y,z);

                    render.setOverrideBlockTexture(block.getIcon(0,1));
                    for(int i = 1;i<=5;i++) {
                        render.setRenderBounds(0.75+(bmod*i), 0.7+(((float)i-1)/hmod), 0.75+(bmod*i), 1-(bmod*i), 0.7+(((float)i)/hmod), 1-(bmod*i));
                        render.renderStandardBlock(block, x, y, z);
                    }

                }
                else if (world.getBlock(x,y,z+1)instanceof StockadeBlock &&world.getBlockMetadata(x, y, z+1) == 7&&!(world.getBlock(x+1,y,z)instanceof StockadeBlock)) {
                        render.setRenderBounds(0,0,0.75,0.25,0.7,1);
                        render.renderStandardBlock(block,x,y,z);

                        render.setOverrideBlockTexture(block.getIcon(0,1));
                        for(int i = 1;i<=5;i++) {
                            render.setRenderBounds(0+(bmod*i), 0.7+(((float)i-1)/hmod), 0.75+(bmod*i), 0.25-(bmod*i), 0.7+(((float)i)/hmod), 1-(bmod*i));
                            render.renderStandardBlock(block, x, y, z);
                        }
                    }
                else{
                    render.setRenderBounds(0, 0, 0.75, 1, 0.7, 1);
                    render.renderStandardBlock(block, x, y, z);

                    render.setOverrideBlockTexture(block.getIcon(0, 1));
                    for (int i = 1; i <= 5; i++) {
                    render.setRenderBounds(0 + (bmod * i), 0.7 + (((float) i - 1) / hmod), 0.75 + (bmod * i), 0.25 - (bmod * i), 0.7 + (((float) i) / hmod), 1 - (bmod * i));
                    render.renderStandardBlock(block, x, y, z);

                    render.setRenderBounds(0.25 + (bmod * i), 0.7 + (((float) i - 1) / hmod), 0.75 + (bmod * i), 0.50 - (bmod * i), 0.7 + (((float) i) / hmod), 1 - (bmod * i));
                    render.renderStandardBlock(block, x, y, z);

                    render.setRenderBounds(0.5 + (bmod * i), 0.7 + (((float) i - 1) / hmod), 0.75 + (bmod * i), 0.75 - (bmod * i), 0.7 + (((float) i) / hmod), 1 - (bmod * i));
                    render.renderStandardBlock(block, x, y, z);

                    render.setRenderBounds(0.75 + (bmod * i), 0.7 + (((float) i - 1) / hmod), 0.75 + (bmod * i), 1 - (bmod * i), 0.7 + (((float) i) / hmod), 1 - (bmod * i));
                    render.renderStandardBlock(block, x, y, z);
            }
        }
                break;
            case 7:
                render.setRenderBounds(0,0,0,0.25,0.7,1);
                render.renderStandardBlock(block,x,y,z);

                for(int i = 1;i<=5;i++){
                    render.setRenderBounds(0+(bmod*i),0.7+(((float)i-1)/hmod),0+(bmod*i),0.25-(bmod*i),0.7+((float)i)/hmod,0.25-(bmod*i));
                    render.renderStandardBlock(block,x,y,z);

                    render.setRenderBounds(0+(bmod*i),0.7+(((float)i-1)/hmod),0.25+(bmod*i),0.25-(bmod*i),0.7+((float)i)/hmod,0.5-(bmod*i));
                    render.renderStandardBlock(block,x,y,z);

                    render.setRenderBounds(0+(bmod*i),0.7+(((float)i-1)/hmod),0.5+(bmod*i),0.25-(bmod*i),0.7+((float)i)/hmod,0.75-(bmod*i));
                    render.renderStandardBlock(block,x,y,z);

                    render.setRenderBounds(0+(bmod*i),0.7+(((float)i-1)/hmod),0.75+(bmod*i),0.25-(bmod*i),0.7+((float)i)/hmod,1-(bmod*i));
                    render.renderStandardBlock(block,x,y,z);
                }
                break;
        }
        render.clearOverrideBlockTexture();
        return true;
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
    public boolean shouldRender3DInInventory(int i) {
        return true;
    }

    @Override
    public int getRenderId() {
        return 0;
    }
}
