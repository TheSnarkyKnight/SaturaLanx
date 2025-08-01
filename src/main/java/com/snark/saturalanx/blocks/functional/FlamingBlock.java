package com.snark.saturalanx.blocks.functional;

import com.dunk.tfc.Blocks.BlockTerraContainer;
import com.snark.saturalanx.TE.FlamingBlockTE;
import com.snark.saturalanx.core.Config;
import com.snark.saturalanx.core.Util;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.Random;

import static com.snark.saturalanx.SaturaLanx.MODID;

public class FlamingBlock extends BlockTerraContainer {
public static IIcon icon;
    public FlamingBlock(){
        super(Material.iron);
        this.setCreativeTab(null);
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
        this.setBlockUnbreakable();
        this.opaque = false;
        this.setTickRandomly(true);
        this.setLightLevel(0.65F);
        this.setBlockName("FlamingBlock");
    }

    @Override
    public TileEntity createNewTileEntity(World var1, int var2) {
        return new FlamingBlockTE();
    }

    public int getBlockColor() {
        return 15452320;
    }

    public int colorMultiplier(IBlockAccess bAccess, int x, int y, int z) {
        return 15452320;
    }

    public int getRenderColor(int par1) {
        return this.getBlockColor();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        return icon;
    }


    public void registerBlockIcons(IIconRegister register) {
        icon = register.registerIcon(MODID +":misc/FlamingBlock");
    }

    @Override
    public boolean canBlockStay(World world, int x, int y, int z) {
        return world.getBlock(x, y - 1, z).isBlockSolid(world, x, y - 1, z, 1);
    }

    @Override
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity){

        if(entity instanceof EntityLivingBase && !world.isRemote){
            entity.motionX *= 0.7;
            entity.motionZ *= 0.7;
            entity.setFire(Config.incendiaryPotDamageTimes);
        }

        super.onEntityCollidedWithBlock(world, x, y, z, entity);
    }

    public Item getItemDropped(int i, Random r, int j) {
        return null;
    }

    public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side) {
        return side == ForgeDirection.DOWN;
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
        return null;
    }

    public void onNeighborBlockChange(World world, int x, int y, int z, Block b) {
        if (!world.isRemote) {
            world.getBlock(x, y, z);
            if (!this.canBlockStay(world, x, y, z)) {
                world.setBlockToAir(x, y, z);
            }
        }

    }

    public boolean renderAsNormalBlock() {
        return true;
    }

    public boolean isOpaqueCube() {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, int x, int y, int z, Random rand) {

        double dx;
        double dy;
        double dz;

        if(world.getBlock(x, y + 1, z).getMaterial() == Material.air && !world.getBlock(x, y + 1, z).isOpaqueCube()) {
            if(rand.nextInt(100) == 0) {
                dx = (double) ((float) x + rand.nextFloat());
                dy = (double) y + this.maxY;
                dz = (double) ((float) z + rand.nextFloat());
                world.spawnParticle("lava", dx, dy, dz, 0.0D, 0.0D, 0.0D);
                world.playSound(dx, dy, dz, "liquid.lavapop", 0.2F + rand.nextFloat() * 0.2F, 0.9F + rand.nextFloat() * 0.15F, false);
            }

            if(rand.nextInt(200) == 0) {
                world.playSound((double) x, (double) y, (double) z, "liquid.lava", 0.2F + rand.nextFloat() * 0.2F, 0.9F + rand.nextFloat() * 0.15F, false);
            }

        }

        dy = (double) (y + 0.125F);
        for(int i = 0; i< rand.nextInt(5+1);i++){
            dx = (double) ((float) x + rand.nextFloat());
            dz = (double) ((float) z + rand.nextFloat());

            world.spawnParticle("flame",dx,dy,dz,0,0,0);
        }
    }


    @Override
    public void updateTick(World world, int x, int y, int z, Random rand) {

        super.updateTick(world, x, y, z, rand);

        Util.burnSurroundings(world, x, y, z);
    }


}
