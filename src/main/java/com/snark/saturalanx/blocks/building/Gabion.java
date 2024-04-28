package com.snark.saturalanx.blocks.building;

import com.dunk.tfc.BlockSetup;
import com.dunk.tfc.Blocks.BlockTerra;
import com.dunk.tfc.Blocks.Terrain.BlockCollapsible;
import com.dunk.tfc.api.Enums.EnumItemReach;
import com.dunk.tfc.api.Enums.EnumSize;
import com.dunk.tfc.api.Enums.EnumWeight;
import com.dunk.tfc.api.Interfaces.ISize;
import com.dunk.tfc.api.TFCBlocks;
import com.dunk.tfc.api.TFCItems;
import com.snark.saturalanx.core.Config;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

import static com.dunk.tfc.api.TFCBlocks.*;
import static com.snark.saturalanx.SaturaLanx.MODID;
import static com.snark.saturalanx.SaturaLanx.tab;
import static com.snark.saturalanx.core.BlockSetup.*;

public class Gabion extends BlockTerra{

    protected IIcon[] icons;
    protected String[] names;
    protected int num;
    public Gabion(){
        super(Material.wood);
        this.setCreativeTab(tab);
        this.setHardness(5.0F);
        this.setResistance(10.0F);
    }

    public void setNum(int i){
        this.num = i;
    }

    @Override
    public void onBlockExploded(World world, int x, int y, int z, Explosion explosion) {
        if(!world.isRemote&&Config.enableGabionMultistage){
            gabionBreak(world,x,y,z,world.getBlockMetadata(x,y,z));
        }
    }

    @Override
    public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int meta) {
        if(!world.isRemote&&Config.enableGabionMultistage){
            gabionBreak(world,x,y,z,meta);
        }
    }

    private void gabionBreak(World world, int x, int y, int z, int meta){
        if(this.num == 0){
            switch (meta){
                case 0:
                case 1:
                case 2:
                    world.setBlock(x,y,z,TFCBlocks.stoneIgInCobble,meta,0);
                    break;
                case 3:
                    world.setBlock(x,y,z, TFCBlocks.stoneIgExCobble,0,0);
                    break;
                case 4:
                    world.setBlock(x,y,z, TFCBlocks.stoneIgExCobble,1,0);
                    break;
                case 5:
                    world.setBlock(x,y,z, TFCBlocks.stoneIgExCobble,2,0);
                    break;
                case 6:
                    world.setBlock(x,y,z,TFCBlocks.stoneIgExCobble,3,0);
                case 7:
                case 8:
                case 9:
                case 10:
                case 11:
                case 12:
                case 13:
                    world.setBlock(x,y,z,TFCBlocks.stoneSedCobble,meta-7,0);
                    break;
                case 14:
                case 15:
                    world.setBlock(x,y,z,TFCBlocks.stoneMMCobble,meta-14,0);
                    break;
                default:
                    world.setBlock(x,y,z, stoneIgInCobble,0,0);
                    break;
            }
        }
        else if(this.num == 1){
            switch (meta){
                case 0:
                case 1:
                case 2:
                case 3:
                    world.setBlock(x,y,z,stoneMMCobble,meta+2,0);
                    break;
                case 4:
                    world.setBlock(x,y,z,stoneSed,7,0);
                    break;
                default:
                    world.setBlock(x,y,z, stoneIgInCobble,0,0);
                    break;
            }

        }
    }

    public void setNames(String[] names){
        this.names = names;
        this.icons = new IIcon[this.names.length];
    }



    @Override
    public void registerBlockIcons(IIconRegister register) {
        for(int i = 0;i < this.names.length;++i){
            this.icons[i] = register.registerIcon(MODID + ":gabion/"+this.names[i]+"Gabion");
        }
    }

    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs, List list) {
        for(int i = 0; i < this.names.length; i++) {
            list.add(new ItemStack(this, 1, i));
        }
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        if(side == 1 || side ==0)
            return this.icons[meta];
        else
            return BlockSetup.wattle.getIcon(2,0);

    }

    @Override
    public Item getItemDropped(int meta, Random random, int fortune) {
        return TFCItems.stick;
    }

    @Override
    public int quantityDropped(Random rand) {
        return rand.nextInt(6) + 1;
    }

    @Override
    public int damageDropped(int i) {
        return i;
    }

    @Override
    public void updateTick(World world, int i, int j, int k, Random random) {
        if (!world.isRemote && world.doChunksNearChunkExist(i, j, k, 1) && !BlockCollapsible.isNearSupport(world, i, j, k, 4, 0.0F)) {
            if(BlockCollapsible.canFallBelow(world, i, j - 1, k)){
                if (Config.enableGabionOpenBottom) {
                    this.dropBlockAsItem(world, i, j, k, 0, 0);
                    gabionBreak(world, i, j, k, world.getBlockMetadata(i, j, k));
                }
                else
                    BlockCollapsible.tryToFall(world, i, j, k, this);
            }
        }
    }

    @Override
    public void onBlockAdded(World world, int i, int j, int k) {
        world.scheduleBlockUpdate(i, j, k, this, this.tickRate(world));
    }

    @Override
    public void onNeighborBlockChange(World world, int i, int j, int k, Block l) {
        world.scheduleBlockUpdate(i, j, k, this, this.tickRate(world));
    }

}
