package com.snark.saturalanx.blocks.functional;

import com.dunk.tfc.Blocks.BlockTerra;
import com.dunk.tfc.Core.Player.InventoryPlayerTFC;
import com.dunk.tfc.Core.TFC_Core;
import com.dunk.tfc.Items.ItemBlocks.ItemAnvil;
import com.dunk.tfc.Items.ItemBlocks.ItemBarrels;
import com.dunk.tfc.Items.ItemBlocks.ItemLargeVessel;
import com.dunk.tfc.Items.ItemClothing;
import com.dunk.tfc.Items.ItemTFCArmor;
import com.dunk.tfc.api.Armor;
import com.dunk.tfc.api.Interfaces.IEquipable;
import com.dunk.tfc.api.TFCItems;
import com.snark.saturalanx.SaturaLanx;
import com.snark.saturalanx.core.BlockSetup;
import com.snark.saturalanx.core.Config;
import com.snark.saturalanx.core.ItemSetup;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import org.lwjgl.Sys;

import java.util.List;
import java.util.Random;

import static com.snark.saturalanx.SaturaLanx.MODID;

public class ArrowRopeBlock extends BlockTerra {
    private IIcon ropeIcon;
    private int breakTreshold;
    public ArrowRopeBlock(){
        super(Material.cloth);
        this.setCreativeTab(null);
        this.setHardness(1.0F);
        this.setBlockName("ArrowRope");
        this.breakTreshold = Config.ropeArrowWeightAllowance*6;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_) {
        return null;
    }


    protected void dropBlocksInWorld(World world, int x, int y, int z) {

    }

    @Override
    public void registerBlockIcons(IIconRegister register) {
        this.blockIcon = register.registerIcon(MODID + ":misc/RopeArrow");
        this.ropeIcon = register.registerIcon(MODID + ":misc/ArrowRope");
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
        if(!world.isRemote){
            if(!canBlockStay(world,x,y,z)) {
                world.setBlockToAir(x, y, z);
            }
        }
    }

    @Override
    public boolean canBlockStay(World world, int x, int y, int z) {
        if(world.getBlockMetadata(x,y,z)==0)
            return world.getBlock(x,y+1,z)==this;
        else
            return world.getBlock(x+1,y,z).getMaterial().isSolid()||world.getBlock(x-1,y,z).getMaterial().isSolid()||world.getBlock(x,y,z+1).getMaterial().isSolid()||world.getBlock(x,y,z-1).getMaterial().isSolid();
    }

    @Override
    public IIcon getIcon(int side, int meta){
        if(meta==0)
            return ropeIcon;
        return blockIcon;
    }

    @Override
    public boolean isLadder(IBlockAccess world, int x, int y, int z, EntityLivingBase entity) {
        return entity instanceof EntityPlayer;
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack is) {
        if(is.getItemDamage()>0&&!world.isRemote){
            int side = (MathHelper.floor_double((double)(entity.rotationYaw*4.0/360+0.5)) & 3)+1;
            world.setBlockMetadataWithNotify(x,y,z,side,3);
            for(int i = 1;i<= Config.ropeArrowRopeLenght;i++)
                if(world.getBlock(x,y-i,z) == Blocks.air)
                    world.setBlock(x,y-i,z,this,0,3);
        }
    }

    @Override
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
        if(!world.isRemote && entity instanceof EntityPlayer){
            int c = 0;

            EntityPlayer player = (EntityPlayer) entity;
            for (ItemStack is : ((InventoryPlayerTFC)player.inventory).extraEquipInventory)
                c += getArmorType(is);
            for (ItemStack is : player.inventory.armorInventory)
                c += getArmorType(is);

            if (c > breakTreshold && Math.abs(entity.motionY) > 0.1) {
                world.playSoundAtEntity(entity, "random:break", 1, 1);
                world.setBlock(x, y, z, Blocks.air, 0, 3);
            }
        }
    }

    private int getArmorType(ItemStack is){
        int c = 0;
        if (is != null) {

            Armor a = null;
            if(is.getItem() instanceof ItemClothing)
                a = ((ItemClothing) is.getItem()).getArmorType();
            else if(is.getItem() instanceof ItemTFCArmor)
                a = ((ItemTFCArmor) is.getItem()).armorTypeTFC;

            if (a!=null) {
                    if (Armor.isMetal(a))
                        c = 10;
                    else if (a.armorId == 0 || a.armorId == 10 || a.armorId == 15 || a.armorId == 16 || a.armorId == 18)
                        c = 2;
                    else
                        c = 1;

            } else if (is.getItem() instanceof IEquipable && ((IEquipable) is.getItem()).getEquipType(is) == IEquipable.EquipType.BACK) {
                if (is.getItem() instanceof ItemLargeVessel)
                    c = 2;
                else if (is.getItem() instanceof ItemBarrels)
                    c = 5;
                else if (is.getItem() instanceof ItemAnvil)
                    c = 10;
            }
        }
        return c;
    }

    @Override
    public void onBlockHarvested(World world, int x, int y, int z, int side, EntityPlayer player) {
        if(!world.isRemote&&world.getBlockMetadata(x,y,z)>0)
            this.dropBlocksInWorld(world, x, y, z);
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public int getRenderType() {
        return BlockSetup.arrowRopeRenderId;
    }
}
