package com.snark.saturalanx.renders.blocks;

import com.dunk.tfc.Items.ItemDrink;
import com.dunk.tfc.api.TFCBlocks;
import com.dunk.tfc.api.TFCFluids;
import com.snark.saturalanx.TE.ChaliceTE;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;

import static com.snark.saturalanx.core.BlockSetup.chaliceBlock;
import static com.snark.saturalanx.core.BlockSetup.chaliceBlock2;

public class ChaliceRender implements ISimpleBlockRenderingHandler {

    @Override
    public void renderInventoryBlock(Block block, int meta, int modelId, RenderBlocks render) {
        GL11.glPushMatrix();

        IIcon icon = block.getIcon(0,meta);
        render.setOverrideBlockTexture(icon);
        render.setRenderAllFaces(true);

            if(meta < 6 && block == chaliceBlock){
                //foot
                render.setRenderBounds(0.375,0,0.375,0.625,0.025,0.625);
                renderInvBlock(block,meta,render);
                render.setRenderBounds(0.425,0.025,0.425,0.575,0.05,0.575);
                renderInvBlock(block,meta,render);

                //stem
                render.setRenderBounds(0.47,0.05,0.47,0.53,0.2,0.53);
                renderInvBlock(block,meta,render);

                //cup
                render.setRenderBounds(0.4,0.2,0.4,0.6,0.25,0.6);
                renderInvBlock(block,meta,render);
                render.setRenderBounds(0.39,0.25,0.4,0.41,0.4,0.6);
                renderInvBlock(block,meta,render);
                render.setRenderBounds(0.59,0.25,0.4,0.61,0.4,0.6);
                renderInvBlock(block,meta,render);
                render.setRenderBounds(0.4,0.25,0.39,0.6,0.4,0.41);
                renderInvBlock(block,meta,render);
                render.setRenderBounds(0.4,0.25,0.59,0.6,0.4,0.61);
                renderInvBlock(block,meta,render);
            }
            else{
                //foot
                render.setRenderBounds(0.375,0,0.375,0.625,0.025,0.625);
                renderInvBlock(block,meta,render);
                render.setRenderBounds(0.425,0.025,0.425,0.575,0.05,0.575);
                renderInvBlock(block,meta,render);

                //stem
                render.setRenderBounds(0.47,0.05,0.47,0.53,0.25,0.53);
                renderInvBlock(block,meta,render);
                render.setRenderBounds(0.455,0.1,0.455,0.545,0.2,0.545);
                renderInvBlock(block,meta,render);

                //cup
                render.setRenderBounds(0.38,0.25,0.38,0.62,0.27,0.62);
                renderInvBlock(block,meta,render);
                render.setRenderBounds(0.37,0.27,0.38,0.39,0.42,0.62);
                renderInvBlock(block,meta,render);
                render.setRenderBounds(0.61,0.27,0.38,0.63,0.42,0.62);
                renderInvBlock(block,meta,render);
                render.setRenderBounds(0.38,0.27,0.37,0.62,0.42,0.39);
                renderInvBlock(block,meta,render);
                render.setRenderBounds(0.38,0.27,0.61,0.62,0.42,0.63);
                renderInvBlock(block,meta,render);
            }

        GL11.glScalef(1.5F,1.5F,1.5F);
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

        int n, facing;
        n = facing = 1;
        TileEntity te = world.getTileEntity(x,y,z);
        if(te instanceof ChaliceTE){
            ChaliceTE cTE = (ChaliceTE) te;
            n = cTE.getNum();
            facing = cTE.getFacing();
        }
        int meta = world.getBlockMetadata(x,y,z);
        IIcon icon = block.getIcon(0,meta);
        render.setOverrideBlockTexture(icon);
        render.setRenderAllFaces(true);


        if(n==1){
            if (block == chaliceBlock) {
                if (meta < 6 || (meta > 7 && meta < 14)) {
                    renderCup(render,block,x,y,z,0,0,false);
                } else {
                    renderCup(render,block,x,y,z,0,0,true);
                }
                if (meta > 7) {
                    if (te instanceof ChaliceTE) {
                        ItemDrink d = ((ChaliceTE) te).getDrink();
                        int color = TFCFluids.FRESHWATER.getColor();
                        Tessellator tes = Tessellator.instance;

                        if (d != null)
                            color = d.getColorFromItemStack(new ItemStack(d, 1, 0), 1);
                        int red = (color >> 16 & 255);
                        int green = (color >> 8 & 255);
                        int blue = (color & 255);

                        tes.setBrightness(block.getMixedBrightnessForBlock(world, x, y, z));
                        tes.setColorOpaque(red, green, blue);

                        IIcon water = TFCBlocks.freshWaterStationary.getIcon(0, 0);
                        double minU = water.getMinU();
                        double minV = water.getMinV();
                        double maxU = water.getMaxU();
                        double maxV = water.getMaxV();

                        double minX, minZ, maxX, maxZ, yLevel;

                        if (meta > 7 && meta < 14) {
                            minX = minZ = 0.4;
                            maxX = maxZ = 0.6;
                            yLevel = 0.38;
                        } else {
                            minX = minZ = 0.38;
                            maxX = maxZ = 0.62;
                            yLevel = 0.4;
                        }

                        double[] v1 = new double[]{minX, yLevel, minZ, minU, minV};
                        double[] v2 = new double[]{maxX, yLevel, minZ, maxU, minV};
                        double[] v3 = new double[]{minX, yLevel, maxZ, minU, maxV};
                        double[] v4 = new double[]{maxX, yLevel, maxZ, maxU, maxV};

                        Vec3[] vertexPositions = new Vec3[4];
                        vertexPositions[0] = Vec3.createVectorHelper(v1[0], v1[1], v1[2]);
                        vertexPositions[1] = Vec3.createVectorHelper(v2[0], v2[1], v2[2]);
                        vertexPositions[2] = Vec3.createVectorHelper(v3[0], v3[1], v3[2]);
                        vertexPositions[3] = Vec3.createVectorHelper(v4[0], v4[1], v4[2]);

                        tes.addVertexWithUV(vertexPositions[3].xCoord + x, vertexPositions[3].yCoord + y, vertexPositions[3].zCoord + z, v4[3], v4[4]);
                        tes.addVertexWithUV(vertexPositions[2].xCoord + x, vertexPositions[2].yCoord + y, vertexPositions[2].zCoord + z, v3[3], v3[4]);
                        tes.addVertexWithUV(vertexPositions[1].xCoord + x, vertexPositions[1].yCoord + y, vertexPositions[1].zCoord + z, v2[3], v2[4]);
                        tes.addVertexWithUV(vertexPositions[0].xCoord + x, vertexPositions[0].yCoord + y, vertexPositions[0].zCoord + z, v1[3], v1[4]);

                        tes.addVertexWithUV(vertexPositions[0].xCoord + x, vertexPositions[0].yCoord + y, vertexPositions[0].zCoord + z, v1[3], v1[4]);
                        tes.addVertexWithUV(vertexPositions[1].xCoord + x, vertexPositions[1].yCoord + y, vertexPositions[1].zCoord + z, v2[3], v2[4]);
                        tes.addVertexWithUV(vertexPositions[2].xCoord + x, vertexPositions[2].yCoord + y, vertexPositions[2].zCoord + z, v3[3], v3[4]);
                        tes.addVertexWithUV(vertexPositions[3].xCoord + x, vertexPositions[3].yCoord + y, vertexPositions[3].zCoord + z, v4[3], v4[4]);
                    }
                }
            } else if (block == chaliceBlock2) {
                renderCup(render,block,x,y,z,0,0,true);
                if (meta > 5) {
                    if (te instanceof ChaliceTE) {
                        ItemDrink d = ((ChaliceTE) te).getDrink();
                        int color = TFCFluids.FRESHWATER.getColor();
                        Tessellator tes = Tessellator.instance;

                        if (d != null)
                            color = d.getColorFromItemStack(new ItemStack(d, 1, 0), 1);
                        int red = (color >> 16 & 255);
                        int green = (color >> 8 & 255);
                        int blue = (color & 255);

                        tes.setBrightness(block.getMixedBrightnessForBlock(world, x, y, z));
                        tes.setColorOpaque(red, green, blue);

                        IIcon water = TFCBlocks.freshWaterStationary.getIcon(0, 0);
                        double minU = water.getMinU();
                        double minV = water.getMinV();
                        double maxU = water.getMaxU();
                        double maxV = water.getMaxV();

                        double minX, minZ, maxX, maxZ, yLevel;

                        minX = minZ = 0.38;
                        maxX = maxZ = 0.62;
                        yLevel = 0.4;

                        double[] v1 = new double[]{minX, yLevel, minZ, minU, minV};
                        double[] v2 = new double[]{maxX, yLevel, minZ, maxU, minV};
                        double[] v3 = new double[]{minX, yLevel, maxZ, minU, maxV};
                        double[] v4 = new double[]{maxX, yLevel, maxZ, maxU, maxV};

                        Vec3[] vertexPositions = new Vec3[4];
                        vertexPositions[0] = Vec3.createVectorHelper(v1[0], v1[1], v1[2]);
                        vertexPositions[1] = Vec3.createVectorHelper(v2[0], v2[1], v2[2]);
                        vertexPositions[2] = Vec3.createVectorHelper(v3[0], v3[1], v3[2]);
                        vertexPositions[3] = Vec3.createVectorHelper(v4[0], v4[1], v4[2]);

                        tes.addVertexWithUV(vertexPositions[3].xCoord + x, vertexPositions[3].yCoord + y, vertexPositions[3].zCoord + z, v4[3], v4[4]);
                        tes.addVertexWithUV(vertexPositions[2].xCoord + x, vertexPositions[2].yCoord + y, vertexPositions[2].zCoord + z, v3[3], v3[4]);
                        tes.addVertexWithUV(vertexPositions[1].xCoord + x, vertexPositions[1].yCoord + y, vertexPositions[1].zCoord + z, v2[3], v2[4]);
                        tes.addVertexWithUV(vertexPositions[0].xCoord + x, vertexPositions[0].yCoord + y, vertexPositions[0].zCoord + z, v1[3], v1[4]);

                        tes.addVertexWithUV(vertexPositions[0].xCoord + x, vertexPositions[0].yCoord + y, vertexPositions[0].zCoord + z, v1[3], v1[4]);
                        tes.addVertexWithUV(vertexPositions[1].xCoord + x, vertexPositions[1].yCoord + y, vertexPositions[1].zCoord + z, v2[3], v2[4]);
                        tes.addVertexWithUV(vertexPositions[2].xCoord + x, vertexPositions[2].yCoord + y, vertexPositions[2].zCoord + z, v3[3], v3[4]);
                        tes.addVertexWithUV(vertexPositions[3].xCoord + x, vertexPositions[3].yCoord + y, vertexPositions[3].zCoord + z, v4[3], v4[4]);
                    }
                }
            }
        }
        else{
            double xOffset, zOffset;
            xOffset = zOffset = 0;
            boolean isBig = (block == chaliceBlock2) || !(block==chaliceBlock&&(meta < 6 || (meta > 7 && meta < 14)));

            if(n == 2) {
                if(facing == 0||facing == 2)
                    xOffset = 0.2;
                else
                    zOffset = 0.2;
                renderCup(render, block, x, y, z, xOffset, zOffset, isBig);
                renderCup(render, block, x, y, z, -xOffset, -zOffset, isBig);
            }
            else if(n==3){
                if(facing==0) {
                    xOffset = 0.2;
                    zOffset = 0.2;
                }
                else if(facing==2){
                    xOffset = 0.2;
                    zOffset = -0.2;
                }
                else if(facing==1){
                    xOffset=-0.2;
                    zOffset=0.2;
                }
                else if(facing==3) {
                    xOffset = 0.2;
                    zOffset = 0.2;
                }

                if(facing==0||facing==2){
                    renderCup(render,block,x,y,z,-xOffset,zOffset,isBig);
                    renderCup(render,block,x,y,z,xOffset,zOffset,isBig);
                    renderCup(render,block,x,y,z,0,-zOffset,isBig);
                }
                else{
                    renderCup(render,block,x,y,z,xOffset,-zOffset,isBig);
                    renderCup(render,block,x,y,z,xOffset,zOffset,isBig);
                    renderCup(render,block,x,y,z,-xOffset,0,isBig);
                }
            }
            else if(n==4){
                xOffset = zOffset = 0.2;
                renderCup(render,block,x,y,z,xOffset,zOffset,isBig);
                renderCup(render,block,x,y,z,-xOffset,zOffset,isBig);
                renderCup(render,block,x,y,z,xOffset,-zOffset,isBig);
                renderCup(render,block,x,y,z,-xOffset,-zOffset,isBig);
            }

        }
        render.clearOverrideBlockTexture();

        return true;
    }

    protected void renderCup(RenderBlocks render, Block block, int x, int y, int z, double xOffset, double zOffset, boolean isBig){
        if(!isBig){
            //foot
            render.setRenderBounds(0.375+xOffset, 0, 0.375+zOffset, 0.625+xOffset, 0.025, 0.625+zOffset);
            render.renderStandardBlock(block, x, y, z);
            render.setRenderBounds(0.425+xOffset, 0.025, 0.425+zOffset, 0.575+xOffset, 0.05, 0.575+zOffset);
            render.renderStandardBlock(block, x, y, z);

            //stem
            render.setRenderBounds(0.47+xOffset, 0.05, 0.47+zOffset, 0.53+xOffset, 0.2, 0.53+zOffset);
            render.renderStandardBlock(block, x, y, z);

            //cup
            render.setRenderBounds(0.4+xOffset, 0.2, 0.4+zOffset, 0.6+xOffset, 0.25, 0.6+zOffset);
            render.renderStandardBlock(block, x, y, z);
            render.setRenderBounds(0.39+xOffset, 0.25, 0.4+zOffset, 0.41+xOffset, 0.4, 0.6+zOffset);
            render.renderStandardBlock(block, x, y, z);
            render.setRenderBounds(0.59+xOffset, 0.25, 0.4+zOffset, 0.61+xOffset, 0.4, 0.6+zOffset);
            render.renderStandardBlock(block, x, y, z);
            render.setRenderBounds(0.4+xOffset, 0.25, 0.39+zOffset, 0.6+xOffset, 0.4, 0.41+zOffset);
            render.renderStandardBlock(block, x, y, z);
            render.setRenderBounds(0.4+xOffset, 0.25, 0.59+zOffset, 0.6+xOffset, 0.4, 0.61+zOffset);
            render.renderStandardBlock(block, x, y, z);
        }
        else{
            //foot
            render.setRenderBounds(0.375+xOffset, 0, 0.375+zOffset, 0.625+xOffset, 0.025, 0.625+zOffset);
            render.renderStandardBlock(block, x, y, z);
            render.setRenderBounds(0.425+xOffset, 0.025, 0.425+zOffset, 0.575+xOffset, 0.05, 0.575+zOffset);
            render.renderStandardBlock(block, x, y, z);

            //stem
            render.setRenderBounds(0.47+xOffset, 0.05, 0.47+zOffset, 0.53+xOffset, 0.25, 0.53+zOffset);
            render.renderStandardBlock(block, x, y, z);
            render.setRenderBounds(0.455+xOffset, 0.1, 0.455+zOffset, 0.545+xOffset, 0.2, 0.545+zOffset);
            render.renderStandardBlock(block, x, y, z);

            //cup
            render.setRenderBounds(0.38+xOffset, 0.25, 0.38+zOffset, 0.62+xOffset, 0.27, 0.62+zOffset);
            render.renderStandardBlock(block, x, y, z);
            render.setRenderBounds(0.37+xOffset, 0.27, 0.38+zOffset, 0.39+xOffset, 0.42, 0.62+zOffset);
            render.renderStandardBlock(block, x, y, z);
            render.setRenderBounds(0.61+xOffset, 0.27, 0.38+zOffset, 0.63+xOffset, 0.42, 0.62+zOffset);
            render.renderStandardBlock(block, x, y, z);
            render.setRenderBounds(0.38+xOffset, 0.27, 0.37+zOffset, 0.62+xOffset, 0.42, 0.39+zOffset);
            render.renderStandardBlock(block, x, y, z);
            render.setRenderBounds(0.38+xOffset, 0.27, 0.61+zOffset, 0.62+xOffset, 0.42, 0.63+zOffset);
            render.renderStandardBlock(block, x, y, z);
        }
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
