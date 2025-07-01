package com.snark.saturalanx.blocks.functional;

import com.dunk.tfc.Blocks.BlockTerraContainer;
import com.dunk.tfc.Core.TFC_Core;
import com.dunk.tfc.api.TFCBlocks;
import com.snark.saturalanx.SaturaLanx;
import com.snark.saturalanx.TE.HypocaustTE;
import com.snark.saturalanx.core.BlockSetup;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class HypocaustControlBlock extends BlockTerraContainer {
    public HypocaustControlBlock(){
        super(Material.rock);
        this.setHardness(5.0F);
        this.setStepSound(soundTypeStone);
        this.setCreativeTab(SaturaLanx.tab);
        this.setBlockName("HypocaustControlBlock");
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        return TFCBlocks.bricks.getIcon(side,meta);
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public int getRenderType() {
        return BlockSetup.hypocaustRenderId;
    }

    @Override
    public TileEntity createNewTileEntity(World var1, int var2) {
        return new HypocaustTE();
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        if (!world.isRemote) {
            TileEntity te = world.getTileEntity(x, y, z);
            if (te instanceof HypocaustTE) {
                HypocaustTE h = (HypocaustTE) te;
                h.getSize();
                h.checkIntegrity();
                h.getActiveHeatSource();
                h.updateFluids();

                if(TFC_Core.isPlayerInDebugMode(player))
                    h.printSize();

                if(h.isFormed()) {
                    if(h.isActive())
                        TFC_Core.sendInfoMessage(player, new ChatComponentText("The hypocaust is active."));
                    else
                        TFC_Core.sendInfoMessage(player, new ChatComponentText("The hypocaust is inactive."));
                }
                else
                    TFC_Core.sendInfoMessage(player, new ChatComponentText("The structure is incomplete."));

                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side) {
        return side == ForgeDirection.UP || side == ForgeDirection.DOWN;
    }

    @Override
    public void onBlockAdded(World world, int x, int y, int z) {
        if (!world.isRemote) {
            TileEntity te = world.getTileEntity(x, y, z);
            if (te instanceof HypocaustTE) {
                HypocaustTE h = (HypocaustTE) te;
                h.init();
            }
        }
    }
}
