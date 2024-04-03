package com.snark.saturalanx.blocks.traps;

import com.dunk.tfc.Blocks.Flora.BlockLeafLitter;
import com.dunk.tfc.api.TFCBlocks;
import com.dunk.tfc.api.TFCItems;
import com.snark.saturalanx.core.BlockSetup;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Random;

import static com.snark.saturalanx.SaturaLanx.tab;

public class LeafCover extends BlockLeafLitter {

    public LeafCover(){
        this.setCreativeTab(tab);
        this.setBlockName("LeafCover");
        this.setHardness(0.5F);
        this.setStepSound(Block.soundTypeGrass);
    }

    @Override
    public boolean canBlockStay(World world, int x, int y, int z) {
        if(world.getBlock(x,y-1,z).isBlockSolid(world,x,y-1,z,1)||isSupported(world,x,y,z))
            return true;
        return false;
    }

    public void harvestBlock(World world, EntityPlayer player, int x, int y, int z, int meta) {
        if (!world.isRemote) {
         this.dropBlockAsItem(world,x,y,z,0,0);
        }
    }

    private boolean isSupported(World w, int x, int y, int z){

        int c = 0;
        Block[] b = new Block[4];
        b[0] = w.getBlock(x-1,y,z);
        b[1] = w.getBlock(x+1,y,z);
        b[2] = w.getBlock(x,y,z-1);
        b[3] = w.getBlock(x,y,z+1);

        for(int i = 0;i < 4;i++){
            if(b[i].getMaterial().isSolid()||b[i].equals(TFCBlocks.leafLitter)||b[i].equals(BlockSetup.leafCover))
                c++;
        }

        return c >= 2;
    }

    @Override
    public Item getItemDropped(int i, Random r, int j) {
        return null;
    }

    @Override
    public int damageDropped(int i) {
        return i;
    }

    @Override
    public int quantityDropped(Random p_149745_1_) {
        return 1;
    }

    @Override
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
        if(!world.isRemote){
            if(!world.getBlock(x,y-1,z).isBlockSolid(world,x,y-1,z,1)&&entity instanceof EntityLivingBase&&entity.fallDistance>0) {
                world.playSoundAtEntity(entity,"entity.zombie.break_wooden_door",1.0F,1.0F);
                world.setBlockToAir(x, y, z);
                this.dropBlockAsItem(world,x,y,z,0,0);
            }
        }
    }

    @Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        ArrayList<ItemStack> drops = new ArrayList<ItemStack>();
        int i = (int)(Math.random()*2);
        if(i>0)
            drops.add(new ItemStack(TFCItems.stick,i));
        i = (int)(Math.random()*2);
        if(i>0)
            drops.add(new ItemStack(TFCItems.straw,i));

        return drops;
    }

    @Override
    public boolean canBeReplacedByLeaves(IBlockAccess bAccess, int x, int y, int z) {
        return false;
    }

    @Override
    public boolean isReplaceable(IBlockAccess world, int x, int y, int z) {
        return false;
    }

    @Override
    public boolean canPlaceBlockAt(World world, int x, int y, int z) {
        return (world.getBlock(x,y,z).isReplaceable(world,x,y,z)||isSupported(world,x,y,z));
    }
}
