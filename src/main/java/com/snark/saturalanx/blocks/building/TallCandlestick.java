package com.snark.saturalanx.blocks.building;

import com.dunk.tfc.Blocks.BlockCandle;
import com.dunk.tfc.Blocks.BlockCandleOff;
import com.dunk.tfc.Blocks.BlockTerraContainer;
import com.dunk.tfc.Core.TFC_Core;
import com.dunk.tfc.Core.TFC_Time;
import com.dunk.tfc.TileEntities.TELightEmitter;
import com.dunk.tfc.api.TFCBlocks;
import com.dunk.tfc.api.TFCOptions;
import com.snark.saturalanx.core.BlockSetup;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import org.lwjgl.Sys;

import java.util.List;
import java.util.Random;

import static com.dunk.tfc.api.TFCBlocks.candleBrass;
import static com.snark.saturalanx.SaturaLanx.MODID;
import static com.snark.saturalanx.SaturaLanx.tab;
import static com.snark.saturalanx.core.BlockSetup.*;

public class TallCandlestick extends BlockTerraContainer {

    protected IIcon wickIconOff;
    protected IIcon wickIconOn;
    protected float burnTime;
    protected Block alternateBlock = null;
    public TallCandlestick() {
        super(Material.circuits);
        this.setCreativeTab(tab);
        this.setHardness(0.5F);
        this.setTickRandomly(true);
        this.setStepSound(Block.soundTypeStone);
    }

    public TallCandlestick(Block alternate) {
        this();
        this.alternateBlock = alternate;
    }

    public int getRenderType() {
        return BlockSetup.tallCandlestickRenderId;
    }

    public int getLightValue(IBlockAccess world, int x, int y, int z) {
        return this.getLightValue();
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        if(side==0)
            return this.blockIcon;
        if(side==1)
            return this.wickIconOn;
        else
            return this.wickIconOff;
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register) {
        super.registerBlockIcons(register);
        this.blockIcon = register.registerIcon(MODID +":misc/candle");
        this.wickIconOff = register.registerIcon(MODID +":misc/wickOff");
        this.wickIconOn = register.registerIcon(MODID +":misc/wickOn");
    }

    public Block getAlternate() {
        return this.alternateBlock;
    }
    public void setAlternate(Block alt){
        this.alternateBlock = alt;
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        int meta;
        if (!world.isRemote && !(this instanceof TallCandlestickOff)) {
            meta = world.getBlockMetadata(x, y, z);
            ItemStack is = player.inventory.getCurrentItem();
            Item item = is != null ? is.getItem() : null;
            if (item == Item.getItemFromBlock(TFCBlocks.torchOff)) {
                player.inventory.consumeInventoryItem(Item.getItemFromBlock(TFCBlocks.torchOff));
                TFC_Core.giveItemToPlayer(new ItemStack(TFCBlocks.torch), player);
            } else if (Block.getBlockFromItem(item) instanceof BlockCandleOff) {
                player.inventory.consumeInventoryItem(item);
                TFC_Core.giveItemToPlayer(new ItemStack(((BlockCandle) Block.getBlockFromItem(item)).getAlternate()), player);
            } else if (Block.getBlockFromItem(item) instanceof TallCandlestickOff){
                player.inventory.consumeInventoryItem(item);
                TFC_Core.giveItemToPlayer(new ItemStack(((TallCandlestickOff)Block.getBlockFromItem(item)).getAlternate()),player);
            }else {
                TELightEmitter te;
                if (item == Item.getItemFromBlock(TFCBlocks.torch)) {
                    te = (TELightEmitter) world.getTileEntity(x, y, z);
                    te.hourPlaced = (int) TFC_Time.getTotalHours();
                } else if (Block.getBlockFromItem(item) instanceof BlockCandle && !(Block.getBlockFromItem(item) instanceof BlockCandleOff)) {
                    te = (TELightEmitter) world.getTileEntity(x, y, z);
                    te.hourPlaced = (int) TFC_Time.getTotalHours();
                } else if (Block.getBlockFromItem(item)instanceof TallCandlestick && !(Block.getBlockFromItem(item) instanceof TallCandlestickOff)) {
                    te = (TELightEmitter) world.getTileEntity(x, y, z);
                    te.hourPlaced = (int) TFC_Time.getTotalHours();
                } else if (this.alternateBlock != null) {
                    world.setBlock(x, y, z, this.alternateBlock, meta, 3);
                }
            }
        }
            return true;

    }

    public TileEntity createNewTileEntity(World var1, int var2) {
        return new TELightEmitter();
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    private boolean canSupportTorch(World world, int x, int y, int z) {
        if (World.doesBlockHaveSolidTopSurface(world, x, y, z)) {
            return true;
        } else {
            Block block = world.getBlock(x, y, z);
            return block.canPlaceTorchOnTop(world, x, y, z);
        }
    }

    public boolean canPlaceBlockAt(World world, int x, int y, int z) {
        return this.canSupportTorch(world, x, y - 1, z)&&world.getBlock(x,y+1,z)== Blocks.air;
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack is) {
        int side = MathHelper.floor_double((double)(entity.rotationYaw*4.0/360+0.5)) & 3;
        if(side==1||side==3)
            world.setBlockMetadataWithNotify(x,y,z,0,3);
        else
            world.setBlockMetadataWithNotify(x,y,z,1,3);
    }

    public boolean getBlocksMovement(IBlockAccess bAccess, int x, int y, int z) {
        return true;
    }

    public void onBlockAdded(World world, int x, int y, int z) {

        if(canSupportTorch(world,x,y,z)) {
            TileEntity te = world.getTileEntity(x,y,z);
            if(te instanceof TELightEmitter)
                ((TELightEmitter) world.getTileEntity(x, y, z)).create();
        }

    }

    public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB aaBB, List list, Entity entity) {
        Block b = world.getBlock(x,y,z);
        int meta = world.getBlockMetadata(x,y,z);
        if(b==brassTallCandlestick||b==brassTallCandlestickOff||b==pewterTallCandlestick||b==pewterTallCandlestickOff){
            this.setBlockBounds(0.25F,0,0.25F,0.75F,2F,0.75F);
            super.addCollisionBoxesToList(world,x,y,z,aaBB,list, entity);
        }
        else if(b==silverTallCandlestick||b==silverTallCandlestickOff||b==sterlingSilverTallCandlestick||b==sterlingSilverTallCandlestickOff){
            if(meta==0)
                this.setBlockBounds(0.25F, 0F, 0.22F, 0.75F, 2F, 0.78F);
            else
                this.setBlockBounds(0.22F, 0F, 0.25F, 0.78F, 2F, 0.75F);
            super.addCollisionBoxesToList(world,x,y,z,aaBB,list,entity);

        } else {
            if(meta==0)
                this.setBlockBounds(0.25F,0F,0.1F,0.75F,2F,0.95F);
            else
                this.setBlockBounds(0.1F,0F,0.25F,0.95F,2F,0.75F);
            super.addCollisionBoxesToList(world,x,y,z,aaBB,list,entity);
        }
    }

    public TallCandlestick setBurnModifier(float f) {
        this.burnTime = f;
        return this;
    }

    public float getBurnModifier() {
        return 2.0F * this.burnTime;
    }

    public void updateTick(World world, int x, int y, int z, Random rand) {
        super.updateTick(world, x, y, z, rand);
        int meta = world.getBlockMetadata(x,y,z);

        if (!world.isRemote&&TFCOptions.torchBurnTime != 0) {
            if (world.getTileEntity(x, y, z) instanceof TELightEmitter) {
                TELightEmitter te = (TELightEmitter) world.getTileEntity(x, y, z);
                if ((float) TFC_Time.getTotalHours() > (float) te.hourPlaced + (float) TFCOptions.torchBurnTime * this.getBurnModifier() || TFC_Core.isExposedToRain(world, x, y, z)) {
                    world.setBlock(x, y, z, this.alternateBlock, meta, 3);
                }
            }
        }

    }

    public boolean isReplaceable(IBlockAccess world, int x, int y, int z) {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, int x, int y, int z, Random rand) {
        int meta = world.getBlockMetadata(x,y,z);
        if(this==brassTallCandlestick||this==pewterTallCandlestick){
            world.spawnParticle("flame",x+0.5,y+1.88,z+0.5,0,0,0);
            world.spawnParticle("smoke",x+0.5,y+1.88,z+0.5,0,0,0);
        } else if (this==silverTallCandlestick||this==sterlingSilverTallCandlestick) {
            if(meta==0){
                world.spawnParticle("flame", x + 0.5, y + 1.88, z + 0.29, 0, 0, 0);
                world.spawnParticle("smoke", x + 0.5, y + 1.88, z + 0.29, 0, 0, 0);

                world.spawnParticle("flame", x + 0.5, y + 1.88, z + 0.71, 0, 0, 0);
                world.spawnParticle("smoke", x + 0.5, y + 1.88, z + 0.71, 0, 0, 0);
            }
            else{
                world.spawnParticle("flame", x + 0.29, y + 1.88, z + 0.5, 0, 0, 0);
                world.spawnParticle("smoke", x + 0.29, y + 1.88, z + 0.5, 0, 0, 0);

                world.spawnParticle("flame", x + 0.71, y + 1.88, z + 0.5, 0, 0, 0);
                world.spawnParticle("smoke", x + 0.71, y + 1.88, z + 0.5, 0, 0, 0);
            }
        } else if (this==goldTallCandlestick||this==roseGoldTallCandlestick) {
            if(meta==0){
                world.spawnParticle("flame", x + 0.5, y + 1.88, z + 0.12, 0, 0, 0);
                world.spawnParticle("smoke", x + 0.5, y + 1.88, z + 0.12, 0, 0, 0);

                world.spawnParticle("flame",x+0.5,y+1.88,z+0.5,0,0,0);
                world.spawnParticle("smoke",x+0.5,y+1.88,z+0.5,0,0,0);

                world.spawnParticle("flame", x + 0.5, y + 1.88, z + 0.85, 0, 0, 0);
                world.spawnParticle("smoke", x + 0.5, y + 1.88, z + 0.85, 0, 0, 0);
            }
            else{
                world.spawnParticle("flame", x + 0.12, y + 1.88, z + 0.5, 0, 0, 0);
                world.spawnParticle("smoke", x + 0.12, y + 1.88, z + 0.5, 0, 0, 0);

                world.spawnParticle("flame",x+0.5,y+1.88,z+0.5,0,0,0);
                world.spawnParticle("smoke",x+0.5,y+1.88,z+0.5,0,0,0);

                world.spawnParticle("flame", x + 0.85, y + 1.88, z + 0.5, 0, 0, 0);
                world.spawnParticle("smoke", x + 0.85, y + 1.88, z + 0.5, 0, 0, 0);
            }
        }

    }
}

