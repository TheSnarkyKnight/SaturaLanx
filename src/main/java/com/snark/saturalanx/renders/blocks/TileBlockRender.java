package com.snark.saturalanx.renders.blocks;

import com.dunk.tfc.api.TFCBlocks;
import com.snark.saturalanx.TE.TileTE;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;

import static com.snark.saturalanx.core.BlockSetup.floorTiles;

public class TileBlockRender implements ISimpleBlockRenderingHandler {
    public TileBlockRender(){

    }

    @Override
    public void renderInventoryBlock(Block block, int i, int i1, RenderBlocks renderBlocks) {

    }



    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        TileEntity te = world.getTileEntity(x,y,z);
        if(te != null && te instanceof TileTE){
            TileTE tile = (TileTE) te;
            int dirtId = tile.getDirtId();
            int gravelId = tile.getGravelId();
            double dirtHeight = 0.5;
            double gravelHeight = 0.2;
            double tileHeight = 0.299;

            //Dirt layer
            renderer.setRenderBounds(0.0, 0.0, 0.0, 1.0, dirtHeight, 1.0);
            if (dirtId > 15) {
                renderer.setOverrideBlockTexture(TFCBlocks.dirt2.getIcon(0, dirtId - 16));
            } else if (dirtId >= 0) {
                renderer.setOverrideBlockTexture(TFCBlocks.dirt.getIcon(0, dirtId));
            }

            if (dirtId >= 0) {
                renderer.renderStandardBlock(block, x, y, z);
            }

            //Gravel layer
            renderer.setRenderBounds(0.0, dirtHeight, 0.0, 1.0, dirtHeight + gravelHeight, 1.0);
            if (gravelId > 15) {
                renderer.setOverrideBlockTexture(TFCBlocks.gravel2.getIcon(0, gravelId - 16));
            } else if (gravelId >= 0) {
                renderer.setOverrideBlockTexture(TFCBlocks.gravel.getIcon(0, gravelId));
            }
            renderer.renderStandardBlock(block, x, y, z);

            //Get tile texture
            if(block==floorTiles)
             renderer.setOverrideBlockTexture(floorTiles.getIcon(1, world.getBlockMetadata(x,y,z)));

            //South face tile layer
            renderer.setRenderBounds(0.0, dirtHeight + gravelHeight, 0.0, 1.0, dirtHeight + gravelHeight + tileHeight, 0.0);
            renderer.flipTexture = true;
            renderer.renderStandardBlock(block, x, y, z);
            renderer.flipTexture = false;

            //North face tile layer
            renderer.setRenderBounds(0.0, dirtHeight + gravelHeight, 1.0, 1.0, dirtHeight + gravelHeight + tileHeight, 1.0);
            renderer.renderStandardBlock(block,x,y,z);

            //West face tile layer
            renderer.setRenderBounds(0.0, dirtHeight + gravelHeight, 0.0, 0.0, dirtHeight + gravelHeight + tileHeight, 1.0);
            renderer.renderStandardBlock(block, x, y, z);

            //East face tile layer
            renderer.setRenderBounds(1.0, dirtHeight + gravelHeight, 0.0, 1.0, dirtHeight + gravelHeight + tileHeight, 1.0);
            renderer.flipTexture = true;
            renderer.renderStandardBlock(block,x,y,z);
            renderer.flipTexture = false;

            //Top face texture
            renderer.setRenderBounds(0.0, dirtHeight + gravelHeight + tileHeight, 0.0, 1.0, 1.0, 1.0);
            renderer.renderStandardBlock(block,x,y,z);

            renderer.clearOverrideBlockTexture();
        }

        return true;
    }

    public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side) {
        return true;
    }

    public boolean shouldRender3DInInventory(int modelId) {
        return false;
    }

    public int getRenderId() {
        return 0;
    }


}
