package com.snark.saturalanx.renders.blocks;

import com.dunk.tfc.Core.TFC_Textures;
import com.dunk.tfc.Render.RenderBlocksWithRotation;
import com.dunk.tfc.api.TFCBlocks;
import com.snark.saturalanx.blocks.building.TallCandlestick;
import com.snark.saturalanx.blocks.building.TallCandlestickOff;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;
import org.lwjgl.opengl.GL11;

import static com.snark.saturalanx.core.BlockSetup.*;

public class TallCandlestickRender implements ISimpleBlockRenderingHandler {

    public TallCandlestickRender(){

    }

    @Override
    public void renderInventoryBlock(Block block, int meta, int modelId, RenderBlocks render) {

        GL11.glPushMatrix();
        GL11.glTranslatef(0,-0.2F,0);
        render.clearOverrideBlockTexture();
        IIcon metal = null;
        if(block==brassTallCandlestick||block==brassTallCandlestickOff)
            metal = TFC_Textures.sheetBrass;
        else if(block==pewterTallCandlestick||block==pewterTallCandlestickOff)
            metal = TFC_Textures.sheetPewter;
        else if (block==silverTallCandlestick||block==silverTallCandlestickOff)
            metal = TFC_Textures.sheetSilver;
        else if(block==sterlingSilverTallCandlestick||block==sterlingSilverTallCandlestickOff)
            metal = TFC_Textures.sheetSterlingSilver;
        else if(block==goldTallCandlestick||block==goldTallCandlestickOff)
            metal = TFC_Textures.sheetGold;
        else
            metal = TFC_Textures.sheetRoseGold;

        render.setOverrideBlockTexture(metal);

        render.setRenderBounds(0.25,0,0.25,0.75,0.10,0.75);
        renderInvBlock(block,meta,render);
        render.setRenderBounds(0.3,0.10,0.3,0.7,0.20,0.7);
        renderInvBlock(block,meta,render);
        render.setRenderBounds(0.45,0.2,0.45,0.55,1,0.55);
        renderInvBlock(block,meta,render);

        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glTranslatef(0,0.8F,0);
        int i = block instanceof TallCandlestickOff ? 2 : 1;
        if(block==brassTallCandlestick||block==brassTallCandlestickOff||block==pewterTallCandlestick||block==pewterTallCandlestickOff) {

            //Arm
            render.setRenderBounds(0.45,0.0,0.45,0.55,0.1,0.55);
            renderInvBlock(block,meta,render);

            //Cup
            render.setRenderBounds(0.42, 0.1, 0.42, 0.58, 0.4, 0.58);
            renderInvBlock(block,meta,render);
            render.setRenderBounds(0.4,0.38,0.4,0.6,0.39,0.6);
            renderInvBlock(block,meta,render);

            //Candle
            render.setOverrideBlockTexture(block.getIcon(0,0));
            render.setRenderBounds(0.45, 0.4, 0.45, 0.55, 0.7, 0.55);
            renderInvBlock(block,meta,render);

            //Wick
            render.setOverrideBlockTexture(block.getIcon(i,0));
            render.setRenderBounds(0.49,0.7,0.49,0.51,0.75,0.51);
            renderInvBlock(block,meta,render);
        }
        else if(block==silverTallCandlestick||block==silverTallCandlestickOff||block==sterlingSilverTallCandlestick||block==sterlingSilverTallCandlestickOff){

            //Arm
            render.setRenderBounds(0.45,0.0,0.29,0.55,0.1,0.71);
            renderInvBlock(block,meta,render);

            //Cups
            render.setRenderBounds(0.42,0.1,0.22,0.58,0.4,0.38);
            renderInvBlock(block,meta,render);
            render.setRenderBounds(0.42,0.1,0.63,0.58,0.4,0.79);
            renderInvBlock(block,meta,render);

            render.setRenderBounds(0.40,0.38,0.2,0.6,0.39,0.4);
            renderInvBlock(block,meta,render);
            render.setRenderBounds(0.42,0.38,0.61,0.60,0.39,0.81);
            renderInvBlock(block,meta,render);

            //Candles
            render.setOverrideBlockTexture(block.getIcon(0,0));
            render.setRenderBounds(0.45,0.4,0.25,0.55,0.7,0.35);
            renderInvBlock(block,meta,render);
            render.setRenderBounds(0.45,0.4,0.66,0.55,0.7,0.76);
            renderInvBlock(block,meta,render);

            //Wicks
            render.setOverrideBlockTexture(block.getIcon(i,0));
            render.setRenderBounds(0.49,0.7,0.29,0.51,0.75,0.31);
            renderInvBlock(block,meta,render);
            render.setRenderBounds(0.49,0.7,0.7,0.51,0.75,0.72);
            renderInvBlock(block,meta,render);
        }else{

            //Arm
            render.setRenderBounds(0.45, 0.0, 0.13, 0.55, 0.1, 0.87);
            renderInvBlock(block,meta,render);

            //Cups
            render.setRenderBounds(0.42, 0.1, 0.05, 0.58, 0.4, 0.21);
            renderInvBlock(block,meta,render);
            render.setRenderBounds(0.42, 0.1, 0.42, 0.58, 0.4, 0.58);
            renderInvBlock(block,meta,render);
            render.setRenderBounds(0.42, 0.1, 0.79, 0.58, 0.4, 0.95);
            renderInvBlock(block,meta,render);

            render.setRenderBounds(0.4, 0.38, 0.03, 0.6, 0.39, 0.23);
            renderInvBlock(block,meta,render);
            render.setRenderBounds(0.4, 0.38, 0.4, 0.6, 0.39, 0.6);
            renderInvBlock(block,meta,render);
            render.setRenderBounds(0.4, 0.38, 0.77, 0.6, 0.39, 0.97);
            renderInvBlock(block,meta,render);

            //Candles
            render.setOverrideBlockTexture(block.getIcon(0,0));
            render.setRenderBounds(0.45,0.4,0.08,0.55,0.7,0.18);
            renderInvBlock(block,meta,render);
            render.setRenderBounds(0.45,0.4,0.45,0.55,0.7,0.55);
            renderInvBlock(block,meta,render);
            render.setRenderBounds(0.45,0.4,0.81,0.55,0.7,0.91);
            renderInvBlock(block,meta,render);

            //Wicks
            render.setOverrideBlockTexture(block.getIcon(i,0));
            render.setRenderBounds(0.49,0.7,0.12,0.51,0.75,0.14);
            renderInvBlock(block,meta,render);
            render.setRenderBounds(0.49,0.7,0.49,0.51,0.75,0.51);
            renderInvBlock(block,meta,render);
            render.setRenderBounds(0.49,0.7,0.85,0.51,0.75,0.87);
            renderInvBlock(block,meta,render);

        }

        render.clearOverrideBlockTexture();
        GL11.glPopMatrix();
    }

    public static void renderInvBlock(Block block, int m, RenderBlocks renderer) {
        Tessellator var14 = Tessellator.instance;
        int meta = m;
        if (m >= 8) {
            meta = m - 8;
        }

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
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {


        RenderBlocksWithRotation render = new RenderBlocksWithRotation(renderer);

        IIcon metal = null;
        if(block==brassTallCandlestick||block==brassTallCandlestickOff)
         metal = TFC_Textures.sheetBrass;
        if(block==pewterTallCandlestick||block==pewterTallCandlestickOff)
            metal = TFC_Textures.sheetPewter;
        if(block==silverTallCandlestick||block==silverTallCandlestickOff)
            metal = TFC_Textures.sheetSilver;
        if(block==sterlingSilverTallCandlestick||block==sterlingSilverTallCandlestickOff)
            metal = TFC_Textures.sheetSterlingSilver;
        if(block==goldTallCandlestick||block==goldTallCandlestickOff)
            metal = TFC_Textures.sheetGold;
        if(block==roseGoldTallCandlestick||block==roseGoldTallCandlestickOff)
            metal = TFC_Textures.sheetRoseGold;

        render.renderAllFaces = true;
        render.setOverrideBlockTexture(metal);

        //Candlestick base

        render.setRenderBounds(0.25,0,0.25,0.75,0.10,0.75);
        render.renderStandardBlock(block,x,y,z);
        render.setRenderBounds(0.3,0.10,0.3,0.7,0.20,0.7);
        render.renderStandardBlock(block,x,y,z);
        render.setRenderBounds(0.45,0.2,0.45,0.55,1,0.55);
        render.renderStandardBlock(block,x,y,z);

        //Candlestick upper part - Material dependent
        int i = block instanceof TallCandlestickOff ? 2 : 1;
        if(block==brassTallCandlestick||block==brassTallCandlestickOff||block==pewterTallCandlestick||block==pewterTallCandlestickOff) {

            //Arm
            render.setRenderBounds(0.45,0.0,0.45,0.55,0.1,0.55);
            render.renderStandardBlock(block, x, y+1, z);

            //Cup
            render.setRenderBounds(0.42, 0.1, 0.42, 0.58, 0.4, 0.58);
            render.renderStandardBlock(block, x, y+1, z);
            render.setRenderBounds(0.4,0.38,0.4,0.6,0.39,0.6);
            render.renderStandardBlock(block, x, y+1, z);

            //Candle
            render.setOverrideBlockTexture(block.getIcon(0,0));
            render.setRenderBounds(0.45, 0.4, 0.45, 0.55, 0.7, 0.55);
            render.renderStandardBlock(block, x, y+1, z);

            //Wick
            render.setOverrideBlockTexture(block.getIcon(i,0));
            render.setRenderBounds(0.49,0.7,0.49,0.51,0.75,0.51);
            render.renderStandardBlock(block, x, y+1, z);

        }
        else if(block==silverTallCandlestick||block==silverTallCandlestickOff||block==sterlingSilverTallCandlestick||block==sterlingSilverTallCandlestickOff){
            if(world.getBlockMetadata(x,y,z)==0){

                //Arm
                render.setRenderBounds(0.45,0.0,0.29,0.55,0.1,0.71);
                render.renderStandardBlock(block,x,y+1,z);

                //Cups
                render.setRenderBounds(0.42,0.1,0.22,0.58,0.4,0.38);
                render.renderStandardBlock(block,x,y+1,z);
                render.setRenderBounds(0.42,0.1,0.63,0.58,0.4,0.79);
                render.renderStandardBlock(block,x,y+1,z);

                render.setRenderBounds(0.40,0.38,0.2,0.6,0.39,0.4);
                render.renderStandardBlock(block,x,y+1,z);
                render.setRenderBounds(0.42,0.38,0.61,0.60,0.39,0.81);
                render.renderStandardBlock(block,x,y+1,z);

                //Candles
                render.setOverrideBlockTexture(block.getIcon(0,0));
                render.setRenderBounds(0.45,0.4,0.25,0.55,0.7,0.35);
                render.renderStandardBlock(block,x,y+1,z);
                render.setRenderBounds(0.45,0.4,0.66,0.55,0.7,0.76);
                render.renderStandardBlock(block,x,y+1,z);

                //Wicks
                render.setOverrideBlockTexture(block.getIcon(i,0));
                render.setRenderBounds(0.49,0.7,0.29,0.51,0.75,0.31);
                render.renderStandardBlock(block,x,y+1,z);
                render.setRenderBounds(0.49,0.7,0.7,0.51,0.75,0.72);
                render.renderStandardBlock(block,x,y+1,z);
            }
            else{

                //Arm
                render.setRenderBounds(0.29,0.0,0.45,0.71,0.1,0.55);
                render.renderStandardBlock(block,x,y+1,z);

                //Cups
                render.setRenderBounds(0.22,0.1,0.42,0.38,0.4,0.58);
                render.renderStandardBlock(block,x,y+1,z);
                render.setRenderBounds(0.63,0.1,0.42,0.79,0.4,0.58);
                render.renderStandardBlock(block,x,y+1,z);

                render.setRenderBounds(0.20,0.38,0.4,0.4,0.39,0.6);
                render.renderStandardBlock(block,x,y+1,z);
                render.setRenderBounds(0.61,0.38,0.42,0.81,0.39,0.60);
                render.renderStandardBlock(block,x,y+1,z);

                //Candles
                render.setOverrideBlockTexture(block.getIcon(0,0));
                render.setRenderBounds(0.25,0.4,0.45,0.35,0.7,0.55);
                render.renderStandardBlock(block,x,y+1,z);
                render.setRenderBounds(0.66,0.4,0.45,0.76,0.7,0.55);
                render.renderStandardBlock(block,x,y+1,z);

                //Wicks
                render.setOverrideBlockTexture(block.getIcon(i,0));
                render.setRenderBounds(0.29,0.7,0.49,0.31,0.75,0.51);
                render.renderStandardBlock(block,x,y+1,z);
                render.setRenderBounds(0.7,0.7,0.49,0.72,0.75,0.51);
                render.renderStandardBlock(block,x,y+1,z);
            }
        } else if (block==goldTallCandlestick||block==goldTallCandlestickOff||block==roseGoldTallCandlestick||block==roseGoldTallCandlestickOff) {
            if(world.getBlockMetadata(x,y,z)==1){

                //Arm
                render.setRenderBounds(0.13, 0.0, 0.45, 0.87, 0.1, 0.55);
                render.renderStandardBlock(block, x, y + 1, z);

                //Cups
                render.setRenderBounds(0.05, 0.1, 0.42, 0.21, 0.4, 0.58);
                render.renderStandardBlock(block, x, y + 1, z);
                render.setRenderBounds(0.42, 0.1, 0.42, 0.58, 0.4, 0.58);
                render.renderStandardBlock(block, x, y + 1, z);
                render.setRenderBounds(0.79, 0.1, 0.42, 0.95, 0.4, 0.58);
                render.renderStandardBlock(block, x, y + 1, z);

                render.setRenderBounds(0.03, 0.38, 0.4, 0.23, 0.39, 0.6);
                render.renderStandardBlock(block, x, y + 1, z);
                render.setRenderBounds(0.4, 0.38, 0.4, 0.6, 0.39, 0.6);
                render.renderStandardBlock(block, x, y + 1, z);
                render.setRenderBounds(0.77, 0.38, 0.4, 0.97, 0.39, 0.6);
                render.renderStandardBlock(block, x, y + 1, z);

                //Candles
                render.setOverrideBlockTexture(block.getIcon(0,0));
                render.setRenderBounds(0.08,0.4,0.45,0.18,0.7,0.55);
                render.renderStandardBlock(block,x,y+1,z);
                render.setRenderBounds(0.45,0.4,0.45,0.55,0.7,0.55);
                render.renderStandardBlock(block,x,y+1,z);
                render.setRenderBounds(0.81,0.4,0.45,0.91,0.7,0.55);
                render.renderStandardBlock(block,x,y+1,z);

                //Wicks
                render.setOverrideBlockTexture(block.getIcon(i,0));
                render.setRenderBounds(0.12,0.7,0.49,0.14,0.75,0.51);
                render.renderStandardBlock(block,x,y+1,z);
                render.setRenderBounds(0.49,0.7,0.49,0.51,0.75,0.51);
                render.renderStandardBlock(block,x,y+1,z);
                render.setRenderBounds(0.85,0.7,0.49,0.87,0.75,0.51);
                render.renderStandardBlock(block,x,y+1,z);
            }else{

                //Arm
                render.setRenderBounds(0.45, 0.0, 0.13, 0.55, 0.1, 0.87);
                render.renderStandardBlock(block, x, y + 1, z);

                //Cups
                render.setRenderBounds(0.42, 0.1, 0.05, 0.58, 0.4, 0.21);
                render.renderStandardBlock(block, x, y + 1, z);
                render.setRenderBounds(0.42, 0.1, 0.42, 0.58, 0.4, 0.58);
                render.renderStandardBlock(block, x, y + 1, z);
                render.setRenderBounds(0.42, 0.1, 0.79, 0.58, 0.4, 0.95);
                render.renderStandardBlock(block, x, y + 1, z);

                render.setRenderBounds(0.4, 0.38, 0.03, 0.6, 0.39, 0.23);
                render.renderStandardBlock(block, x, y + 1, z);
                render.setRenderBounds(0.4, 0.38, 0.4, 0.6, 0.39, 0.6);
                render.renderStandardBlock(block, x, y + 1, z);
                render.setRenderBounds(0.4, 0.38, 0.77, 0.6, 0.39, 0.97);
                render.renderStandardBlock(block, x, y + 1, z);

                //Candles
                render.setOverrideBlockTexture(block.getIcon(0,0));
                render.setRenderBounds(0.45,0.4,0.08,0.55,0.7,0.18);
                render.renderStandardBlock(block,x,y+1,z);
                render.setRenderBounds(0.45,0.4,0.45,0.55,0.7,0.55);
                render.renderStandardBlock(block,x,y+1,z);
                render.setRenderBounds(0.45,0.4,0.81,0.55,0.7,0.91);
                render.renderStandardBlock(block,x,y+1,z);

                //Wicks
                render.setOverrideBlockTexture(block.getIcon(i,0));
                render.setRenderBounds(0.49,0.7,0.12,0.51,0.75,0.14);
                render.renderStandardBlock(block,x,y+1,z);
                render.setRenderBounds(0.49,0.7,0.49,0.51,0.75,0.51);
                render.renderStandardBlock(block,x,y+1,z);
                render.setRenderBounds(0.49,0.7,0.85,0.51,0.75,0.87);
                render.renderStandardBlock(block,x,y+1,z);

            }


        }
        render.clearOverrideBlockTextures();
        return true;
    }

    public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side) {
        return side == ForgeDirection.DOWN;
    }

    public boolean shouldRender3DInInventory(int modelId) {
        return true;
    }

    public int getRenderId() {
        return 0;
    }
}
