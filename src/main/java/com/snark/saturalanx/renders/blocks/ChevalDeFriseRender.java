package com.snark.saturalanx.renders.blocks;

import com.snark.saturalanx.blocks.building.ChevalDeFrise;
import com.snark.saturalanx.core.BlockSetup;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.model.obj.*;

public class ChevalDeFriseRender implements ObjBlockRendererHandler {

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
        Tessellator tes = Tessellator.instance;

        RenderHelper.disableStandardItemLighting();

        tes.startDrawingQuads();
        tes.setColorOpaque_F(1, 1, 1);
        tes.addTranslation(0,-0.3F,0);
        ObjBlockRendererHandler.renderWithOBJ(((ChevalDeFrise) block).getModel(), block.getIcon(0,0), tes, 1, 0.0f);
        tes.addTranslation(0,0.3F,0);
        tes.draw();

        RenderHelper.enableStandardItemLighting();

    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        Tessellator tes = Tessellator.instance;

        float rot = (float) (world.getBlockMetadata(x,y,z) == 1 ? Math.toRadians(90) : Math.toRadians(0));

        tes.setColorOpaque_F(1.0F, 1.0F, 1.0F);//Reset the colour multiplier
        tes.addTranslation(x+0.5F,y,z+0.5F);//Translate the render to the block position
        ObjBlockRendererHandler.renderWithOBJ(((ChevalDeFrise) block).getModel(),block.getIcon(0,0),tes,1,rot);//Render the block model
        tes.addTranslation(-(x+0.5F),-y,-(z+0.5F));//Reset the render position

        return true;
    }

    @Override
    public boolean shouldRender3DInInventory(int modelId) {
        return true;
    }

    @Override
    public int getRenderId() {
        return BlockSetup.chevalDeFriseRenderId;
    }

}
