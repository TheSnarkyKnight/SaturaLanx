package com.snark.saturalanx.renders.blocks;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;

public class ArrowRopeRender implements ISimpleBlockRenderingHandler {
    public ArrowRopeRender(){}


    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {

    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {

        int meta = world.getBlockMetadata(x,y,z);
        IIcon icon = block.getIcon(0,meta);
        int brightness = block.getMixedBrightnessForBlock(world,x,y,z);
        int color = block.colorMultiplier(world,x,y,z);

        if(meta == 0){
            //Rope
            this.drawRope(icon,x,y,z,brightness,color,1);
        }
        else{
            //Arrow
            meta--;
            double rot;

            switch (meta){
                default:
                case 0:
                    rot = -90;
                    break;
                case 1:
                    rot = -180;
                    break;
                case 2:
                    rot = -270;
                    break;
                case 3:
                    rot = 0;
                    break;
            }

            this.drawArrow(icon,x,y,z,1.0F,2,(float)Math.toRadians(90),(float)Math.toRadians(rot),brightness,color);
            this.drawRope(block.getIcon(0,0),x,y,z,brightness,color,0.6);
        }

        return true;
    }

    public void drawArrow(IIcon icon, double x, double y, double z, float scale, int num, float rotation, float yRotation, int brightness, int color) {
        Tessellator tessellator = Tessellator.instance;
        tessellator.setBrightness(brightness);
        tessellator.setColorOpaque_I(color);
        double d3 = (double)icon.getMinU();
        double d4 = (double)icon.getMinV();
        double d5 = (double)icon.getMaxU();
        double d6 = (double)icon.getMaxV();
        double d7 = 0.45 * (double)scale;
        double d8 = (double)0.5F - d7;
        double d9 = (double)0.5F + d7;
        double d10 = (double)0.5F - d7;
        double d11 = (double)0.5F + d7;
        Vec3 xz2 = Vec3.createVectorHelper(d8, (double)0.0F, d10);
        Vec3 xZ2 = Vec3.createVectorHelper(d9, (double)0.0F, d11);
        Vec3 XZ2 = Vec3.createVectorHelper(d9, (double)scale, d11);
        Vec3 Xz2 = Vec3.createVectorHelper(d8, (double)scale, d10);
        Vec3.createVectorHelper(d8, (double)0.0F, d10);
        Vec3.createVectorHelper(d9, (double)0.0F, d11);
        Vec3.createVectorHelper(d9, (double)scale, d11);
        Vec3.createVectorHelper(d8, (double)scale, d10);

        for(int i = 0; i < num; ++i) {
            Vec3 xz = xz2.addVector((double)-0.5F, (double)-0.5F, (double)-0.5F);
            Vec3 xZ = xZ2.addVector((double)-0.5F, (double)-0.5F, (double)-0.5F);
            Vec3 XZ = XZ2.addVector((double)-0.5F, (double)-0.5F, (double)-0.5F);
            Vec3 Xz = Xz2.addVector((double)-0.5F, (double)-0.5F, (double)-0.5F);
            xz.rotateAroundY((float)i * (float)Math.PI / (float)num);
            xZ.rotateAroundY((float)i * (float)Math.PI / (float)num);
            XZ.rotateAroundY((float)i * (float)Math.PI / (float)num);
            Xz.rotateAroundY((float)i * (float)Math.PI / (float)num);
            xz.rotateAroundZ(rotation);
            xZ.rotateAroundZ(rotation);
            XZ.rotateAroundZ(rotation);
            Xz.rotateAroundZ(rotation);
            xz.rotateAroundY(yRotation);
            xZ.rotateAroundY(yRotation);
            XZ.rotateAroundY(yRotation);
            Xz.rotateAroundY(yRotation);
            xz = xz.addVector((double)0.5F, (double)0.5F, (double)0.5F);
            xZ = xZ.addVector((double)0.5F, (double)0.5F, (double)0.5F);
            XZ = XZ.addVector((double)0.5F, (double)0.5F, (double)0.5F);
            Xz = Xz.addVector((double)0.5F, (double)0.5F, (double)0.5F);
            tessellator.addVertexWithUV(x + Xz.xCoord, y + Xz.yCoord, z + Xz.zCoord, d3, d4);
            tessellator.addVertexWithUV(x + xz.xCoord, y + xz.yCoord, z + xz.zCoord, d3, d6);
            tessellator.addVertexWithUV(x + xZ.xCoord, y + xZ.yCoord, z + xZ.zCoord, d5, d6);
            tessellator.addVertexWithUV(x + XZ.xCoord, y + XZ.yCoord, z + XZ.zCoord, d5, d4);
            tessellator.addVertexWithUV(x + XZ.xCoord, y + XZ.yCoord, z + XZ.zCoord, d3, d4);
            tessellator.addVertexWithUV(x + xZ.xCoord, y + xZ.yCoord, z + xZ.zCoord, d3, d6);
            tessellator.addVertexWithUV(x + xz.xCoord, y + xz.yCoord, z + xz.zCoord, d5, d6);
            tessellator.addVertexWithUV(x + Xz.xCoord, y + Xz.yCoord, z + Xz.zCoord, d5, d4);
        }

    }

    public void drawRope(IIcon icon, double x, double y, double z, int brightness, int color, double yMod){
        Tessellator tess = Tessellator.instance;

        tess.setBrightness(brightness);
        tess.setColorOpaque_I(color);

        double minX = x + 0.2;
        double maxX = x + 0.8;
        double minZ = z + 0.2;
        double maxZ = z + 0.8;
        double minY = y;
        double maxY = y + yMod;
        double minU = icon.getMinU();
        double maxU = icon.getMaxU();
        double minV = icon.getMinV();
        double maxV = icon.getMaxV();

        tess.addVertexWithUV(minX, maxY, minZ, minU, minV);
        tess.addVertexWithUV(minX, minY, minZ, minU, maxV);
        tess.addVertexWithUV(maxX, minY, maxZ, maxU, maxV);
        tess.addVertexWithUV(maxX, maxY, maxZ, maxU, minV);
        tess.addVertexWithUV(maxX, maxY, maxZ, minU, minV);
        tess.addVertexWithUV(maxX, minY, maxZ, minU, maxV);
        tess.addVertexWithUV(minX, minY, minZ, maxU, maxV);
        tess.addVertexWithUV(minX, maxY, minZ, maxU, minV);
        tess.addVertexWithUV(minX, maxY, maxZ, minU, minV);
        tess.addVertexWithUV(minX, minY, maxZ, minU, maxV);
        tess.addVertexWithUV(maxX, minY, minZ, maxU, maxV);
        tess.addVertexWithUV(maxX, maxY, minZ, maxU, minV);
        tess.addVertexWithUV(maxX, maxY, minZ, minU, minV);
        tess.addVertexWithUV(maxX, minY, minZ, minU, maxV);
        tess.addVertexWithUV(minX, minY, maxZ, maxU, maxV);
        tess.addVertexWithUV(minX, maxY, maxZ, maxU, minV);
    }

    @Override
    public boolean shouldRender3DInInventory(int modelId) {
        return false;
    }

    @Override
    public int getRenderId() {
        return 0;
    }

}
