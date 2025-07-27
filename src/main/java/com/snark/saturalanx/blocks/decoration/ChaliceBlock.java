package com.snark.saturalanx.blocks.decoration;

import com.dunk.tfc.Blocks.BlockTerraContainer;
import com.dunk.tfc.Core.TFC_Core;
import com.dunk.tfc.Core.TFC_Textures;
import com.dunk.tfc.Items.ItemDrink;
import com.dunk.tfc.api.TFCItems;
import com.snark.saturalanx.SaturaLanx;
import com.snark.saturalanx.TE.ChaliceTE;
import com.snark.saturalanx.core.BlockSetup;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.List;

public class ChaliceBlock extends BlockTerraContainer {
    protected String[] names;
    protected IIcon[] icons;
    public ChaliceBlock(){
        super(Material.circuits);
        setCreativeTab(SaturaLanx.tab);
        setHardness(0.5F);
    }

    public ChaliceBlock(String[] s){
        this();
        this.names = s;
        this.icons = new IIcon[names.length];
    }

    @Override
    public String getHarvestTool(int metadata) {
        return null;
    }

    @Override
    public int damageDropped(int meta) {
        if((this==BlockSetup.chaliceBlock&&meta>7))
            meta -= 8;
        else if(this==BlockSetup.chaliceBlock2&&meta>5)
            meta -= 6;
        return meta;
    }

    @Override
    public void getSubBlocks(Item item, CreativeTabs tab, List list) {
        for (int i = 0; i < names.length; i ++) {
            list.add(new ItemStack(item, 1, i));
        }
    }

    @Override
    public void registerBlockIcons(IIconRegister register) {
        if(this == BlockSetup.chaliceBlock){
            icons[0] = TFC_Textures.sheetCopper;
            icons[1] = TFC_Textures.sheetBronze;
            icons[2] = TFC_Textures.sheetBismuthBronze;
            icons[3] = TFC_Textures.sheetBlackBronze;
            icons[4] = TFC_Textures.sheetBrass;
            icons[5] = TFC_Textures.sheetPewter;
            icons[6] = TFC_Textures.sheetSilver;
            icons[7] = TFC_Textures.sheetSterlingSilver;
        }
        else if(this == BlockSetup.chaliceBlock2){
            icons[0] = TFC_Textures.sheetGold;
            icons[1] = TFC_Textures.sheetRoseGold;
            icons[2] = TFC_Textures.sheetPlatinum;
            icons[3] = TFC_Textures.sheetBlackSteel;
            icons[4] = TFC_Textures.sheetRedSteel;
            icons[5] = TFC_Textures.sheetBlueSteel;
        }
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        if(this==BlockSetup.chaliceBlock){
            if (meta>7)
                meta -= 8;
            return icons[meta];
        }
        else{
            if(meta>5)
                meta -= 6;
            return icons[meta];
        }
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int metadata) {
        if(!world.isRemote){
            TileEntity te = world.getTileEntity(x,y,z);
            if(te instanceof ChaliceTE){
                int num = ((ChaliceTE) te).getNum();
                if(num>1){
                    ItemStack is = new ItemStack(Item.getItemFromBlock(block),num-1,metadata);
                    EntityItem ei = new EntityItem(world,x,y,z,is);
                    world.spawnEntityInWorld(ei);
                }
            }
        }
    }

    public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB aaBB, List list, Entity entity) {
        TileEntity te = world.getTileEntity(x,y,z);
        int num = 1;
        int facing = 1;
        if(te instanceof ChaliceTE) {
            num = ((ChaliceTE) te).getNum();
            facing = ((ChaliceTE) te).getFacing();
        }

        if(num == 1) {
            this.setBlockBounds(0.37F, 0, 0.37F, 0.63F, 0.41F, 0.63F);
        }
        else if(num == 2){
            if(facing==0||facing==2)
                this.setBlockBounds(0.17F,0,0.37F,0.83F,0.41F,0.63F);
            else
                this.setBlockBounds(0.37F,0,0.17F,0.63F,0.41F,0.83F);
        }
        else{
            this.setBlockBounds(0.17F,0,0.17F,0.83F,0.41F,0.83F);
        }

        super.addCollisionBoxesToList(world, x, y, z, aaBB, list, entity);
    }

    public MovingObjectPosition collisionRayTrace(World world, int x, int y, int z, Vec3 startVec, Vec3 endVec) {
        TileEntity te = world.getTileEntity(x,y,z);
        int num = 1;
        int facing = 1;
        if(te instanceof ChaliceTE) {
            num = ((ChaliceTE) te).getNum();
            facing = ((ChaliceTE) te).getFacing();
        }

        if(num == 1){
            this.setBlockBounds(0.37F, 0, 0.37F, 0.63F, 0.41F, 0.63F);
        }
        else if(num == 2){
            if(facing==0||facing==2)
                this.setBlockBounds(0.17F,0,0.37F,0.83F,0.41F,0.63F);
            else
                this.setBlockBounds(0.37F,0,0.17F,0.63F,0.41F,0.83F);
        }
        else{
            this.setBlockBounds(0.17F,0,0.17F,0.83F,0.41F,0.83F);
        }

        return super.collisionRayTrace(world, x, y, z, startVec, endVec);
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        if (!world.isRemote) {

            int meta = world.getBlockMetadata(x, y, z);
            int n;
            if (this == BlockSetup.chaliceBlock)
                n = 7;
            else
                n = 5;
            ItemStack is = player.inventory.getCurrentItem();
            Item item = is != null ? is.getItem() : null;

            //Full chalice
            if (meta > n) {
                if (item == null && !player.isSneaking()) {
                    TileEntity te = world.getTileEntity(x, y, z);
                    if (te instanceof ChaliceTE) {
                        Item drink = ((ChaliceTE) te).getDrink();
                        if (drink != null) {
                            drink.onEaten(new ItemStack(drink, 1, drink.getMaxDamage() - 5), world, player);
                            player.inventory.mainInventory[player.inventory.currentItem] = null;
                            world.playSoundAtEntity(player, "random.drink", 1, 1);
                        }
                        ((ChaliceTE) te).clearDrink();
                        world.setBlockMetadataWithNotify(x, y, z, meta - (n + 1), 3);
                        return true;
                    }
                } else if (item == null && player.isSneaking()) {
                    TileEntity te = world.getTileEntity(x, y, z);
                    if (te instanceof ChaliceTE)
                        ((ChaliceTE) te).clearDrink();
                    world.setBlockMetadataWithNotify(x, y, z, meta - (n + 1), 3);
                    return true;
                } else if (item != null && item instanceof ItemDrink) {
                    TileEntity te = world.getTileEntity(x, y, z);
                    if (te instanceof ChaliceTE) {
                        ItemDrink drink = ((ChaliceTE) te).getDrink();
                        if (drink != null && drink == item && is.getItemDamage() >= 5) {
                            is.setItemDamage(is.getItemDamage() - 5);
                            ((ChaliceTE) te).clearDrink();
                            world.setBlockMetadataWithNotify(x, y, z, meta - (n + 1), 3);
                            return true;
                        }
                    }
                }

            }

            //Empty chalice
            else {
                if (item != null && item instanceof ItemDrink && (is.getItemDamage() + 5 <= is.getMaxDamage())) {
                    TileEntity te = world.getTileEntity(x, y, z);
                    if (te instanceof ChaliceTE) {
                        ((ChaliceTE) te).setDrink((ItemDrink) is.getItem());

                        if(((ChaliceTE) te).getNum()>1)
                            return false;

                        is.setItemDamage(is.getItemDamage() + 5);
                        if (is.getItemDamage() == is.getMaxDamage()) {
                            int m;
                            if (is.getItem().getContainerItem() == TFCItems.potteryJug)
                                m = 1;
                            else
                                m = 0;
                            player.inventory.mainInventory[player.inventory.currentItem] = new ItemStack(is.getItem().getContainerItem(), 1, m);
                        }

                        world.setBlockMetadataWithNotify(x, y, z, meta + (n + 1), 3);

                        return true;
                    }
                } else if (item != null && item == Item.getItemFromBlock(world.getBlock(x, y, z)) && is.getItemDamage() == meta) {
                    TileEntity te = world.getTileEntity(x, y, z);
                    if (te instanceof ChaliceTE) {
                        int num = ((ChaliceTE) te).getNum();
                        if (num < 4) {
                            ((ChaliceTE) te).setNum(num + 1);
                            ((ChaliceTE) te).setFacing(MathHelper.floor_double((double) (player.rotationYaw * 4.0 / 360 + 0.5)) & 3);
                            world.markBlockForUpdate(x, y, z);

                            if (!player.capabilities.isCreativeMode)
                                is.stackSize--;
                            if (is.stackSize <= 0)
                                is = null;

                            return true;
                        }
                    }
                } else if (item == null && player.isSneaking()) {
                    TileEntity te = world.getTileEntity(x, y, z);
                    if (te instanceof ChaliceTE) {
                        int num = ((ChaliceTE) te).getNum();

                        if (num >= 1) {
                            ((ChaliceTE) te).setNum(num - 1);
                            TFC_Core.giveItemToPlayer(new ItemStack(Item.getItemFromBlock(world.getBlock(x, y, z)), 1, meta), player);

                            if (num == 1) {
                                world.setBlock(x, y, z, Blocks.air);
                                world.setTileEntity(x, y, z, null);
                            }

                            world.markBlockForUpdate(x, y, z);

                            return true;
                        }
                    }
                }

            }
        }
        return false;
    }

    @Override
    public boolean canBeReplacedByLeaves(IBlockAccess world, int x, int y, int z) {
        return false;
    }


    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public int getRenderType() {
        return BlockSetup.chaliceRenderId;
    }

    public TileEntity createNewTileEntity(World var1, int var2) {
        return new ChaliceTE();
    }

}
