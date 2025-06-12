package com.snark.saturalanx.blocks.building;

import com.dunk.tfc.Blocks.BlockTerra;
import com.dunk.tfc.Items.Tools.ItemCustomAxe;
import com.dunk.tfc.Items.Tools.ItemKnife;
import com.dunk.tfc.api.TFCItems;
import com.snark.saturalanx.core.BlockSetup;
import com.snark.saturalanx.core.Config;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.*;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

import static com.snark.saturalanx.SaturaLanx.MODID;
import static com.snark.saturalanx.SaturaLanx.tab;
import static com.snark.saturalanx.blocks.functional.SpikeBlock.spikeDamageSource;

public class StockadeBlock extends BlockTerra {
    protected IIcon pointIcon;
    public StockadeBlock(){
        super(Material.wood);
        this.setCreativeTab(tab);
        this.setHardness(14.0F);
        this.setStepSound(Block.soundTypeWood);
        this.setHarvestLevel("axe",0);
        this.setBlockName("StockadeBlock");
    }

    @Override
    public void registerBlockIcons(IIconRegister register) {
        this.blockIcon = register.registerIcon(MODID+":misc/stockadeWall");
        this.pointIcon = register.registerIcon(MODID+":misc/stockadePoint");
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        return meta == 0 ? blockIcon : pointIcon;
    }

    @Override
    public boolean canBeReplacedByLeaves(IBlockAccess world, int x, int y, int z) {
        return false;
    }

    private boolean canSupport(World world, int x, int y, int z) {
        if (World.doesBlockHaveSolidTopSurface(world, x, y, z)) {
            return true;
        } else {
            Block block = world.getBlock(x, y, z);
            return block.canPlaceTorchOnTop(world, x, y, z);
        }
    }

    @Override
    public boolean canPlaceBlockAt(World world, int x, int y, int z) {
        return canSupport(world,x,y-1,z)&&world.getBlock(x,y+1,z).isReplaceable(world,x,y,z)&&world.getBlock(x,y+2,z).isReplaceable(world,x,y,z)&&world.getBlock(x,y+3,z).isReplaceable(world,x,y,z);
    }

    @Override
    public boolean isReplaceable(IBlockAccess world, int x, int y, int z) {
        return false;
    }

    public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB aaBB, List list, Entity entity) {
        int meta = world.getBlockMetadata(x,y,z);

        switch (meta){
            case 0:
            case 4:
                if(world.getBlock(x,y,z-1)instanceof StockadeBlock && (world.getBlockMetadata(x, y, z-1) == 1||world.getBlockMetadata(x, y, z+1) == 5)&&!(world.getBlock(x-1,y,z)instanceof StockadeBlock))
                    this.setBlockBounds(0.75F,0,0,1,1,0.25F);
                else if (world.getBlock(x,y,z-1)instanceof StockadeBlock &&(world.getBlockMetadata(x,y,z-1)==3||world.getBlockMetadata(x, y, z+1) == 7)&&!(world.getBlock(x+1,y,z)instanceof StockadeBlock))
                    this.setBlockBounds(0,0,0,0.25F,1,0.25F);
                else
                    this.setBlockBounds(0,0,0,1,1,0.25F);
                break;
            case 1:
            case 5:
                this.setBlockBounds(0.75F,0,0,1,1,1);
                break;
            case 2:
            case 6:
                if(world.getBlock(x,y,z+1)instanceof StockadeBlock &&(world.getBlockMetadata(x, y, z+1) == 1||world.getBlockMetadata(x, y, z+1) == 5)&&!(world.getBlock(x-1,y,z)instanceof StockadeBlock))
                    this.setBlockBounds(0.75F,0,0.75F,1,1,1);
                else if (world.getBlock(x,y,z+1)instanceof StockadeBlock &&(world.getBlockMetadata(x,y,z+1)==3||world.getBlockMetadata(x, y, z+1) == 7)&&!(world.getBlock(x+1,y,z)instanceof StockadeBlock))
                    this.setBlockBounds(0,0,0.75F,0.25F,1,1);
                else
                    this.setBlockBounds(0,0,0.75F,1,1,1);
                break;
            case 3:
            case 7:
                this.setBlockBounds(0,0,0,0.25F,1,1);
                break;
        }
        super.addCollisionBoxesToList(world, x, y, z, aaBB, list, entity);
    }

    public MovingObjectPosition collisionRayTrace(World world, int x, int y, int z, Vec3 startVec, Vec3 endVec) {
        int meta = world.getBlockMetadata(x,y,z);
        switch (meta){
            case 0:
            case 4:
                if(world.getBlock(x,y,z-1)instanceof StockadeBlock && (world.getBlockMetadata(x, y, z-1) == 1||world.getBlockMetadata(x, y, z+1) == 5)&&!(world.getBlock(x-1,y,z)instanceof StockadeBlock))
                    this.setBlockBounds(0.75F,0,0,1,1,0.25F);
                else if (world.getBlock(x,y,z-1)instanceof StockadeBlock &&(world.getBlockMetadata(x,y,z-1)==3||world.getBlockMetadata(x, y, z+1) == 7)&&!(world.getBlock(x+1,y,z)instanceof StockadeBlock))
                    this.setBlockBounds(0,0,0,0.25F,1,0.25F);
                else
                    this.setBlockBounds(0,0,0,1,1,0.25F);
                break;
            case 1:
            case 5:
                this.setBlockBounds(0.75F,0,0,1,1,1);
                break;
            case 2:
            case 6:
                if(world.getBlock(x,y,z+1)instanceof StockadeBlock &&(world.getBlockMetadata(x, y, z+1) == 1||world.getBlockMetadata(x, y, z+1) == 5)&&!(world.getBlock(x-1,y,z)instanceof StockadeBlock))
                    this.setBlockBounds(0.75F,0,0.75F,1,1,1);
                else if (world.getBlock(x,y,z+1)instanceof StockadeBlock &&(world.getBlockMetadata(x,y,z+1)==3||world.getBlockMetadata(x, y, z+1) == 7)&&!(world.getBlock(x+1,y,z)instanceof StockadeBlock))
                    this.setBlockBounds(0,0,0.75F,0.25F,1,1);
                else
                    this.setBlockBounds(0,0,0.75F,1,1,1);
                break;
            case 3:
            case 7:
                this.setBlockBounds(0,0,0,0.25F,1,1);
                break;
        }
        return super.collisionRayTrace(world, x, y, z, startVec, endVec);
    }

    @Override
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
        if(world.getBlockMetadata(x,y,z)>3 && entity instanceof EntityLivingBase){
            if(entity.boundingBox.expand(0.05,-0.2,0.05).intersectsWith(world.getBlock(x,y,z).getCollisionBoundingBoxFromPool(world,x,y,z))){
                if (Config.doesStockadeBlockSpiders && entity instanceof EntitySpider) {
                    entity.motionY -= 3;
                    ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.moveSlowdown.getId(), 2, 20));
                }
                entity.motionX *= 0.7F;
                entity.motionZ *= 0.7F;

                int damage = Config.woodenSpikesDamage;
                if (entity.fallDistance >= 1)
                    damage += (int) ((entity.fallDistance * 8F) * 10F);

                entity.attackEntityFrom(spikeDamageSource, damage);
            }
        }

        super.onEntityCollidedWithBlock(world, x, y, z, entity);
    }

    @Override
    public void onEntityWalking(World world, int x, int y, int z, Entity entity) {
        if(world.getBlockMetadata(x,y,z)>3&&entity instanceof EntityLivingBase){
            entity.motionX *= 0.7F;
            entity.motionZ *= 0.7F;
            ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.moveSlowdown.getId(),2,20));
            int damage = Config.woodenSpikesDamage;

            if(entity.fallDistance>=1)
                damage += (entity.fallDistance * 8F) * 10F;

            entity.attackEntityFrom(spikeDamageSource,damage);
        }

        super.onEntityWalking(world, x, y, z, entity);
    }

    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack is) {
        int side = MathHelper.floor_double((double)(entity.rotationYaw*4.0/360+0.5)) & 3;
        world.setBlockMetadataWithNotify(x,y,z,side,3);
        if(!world.isRemote){
            world.setBlock(x,y+1,z,this,side,3);
            world.setBlock(x,y+2,z,this,side,3);
            world.setBlock(x,y+3,z,this,side+4,3);
        }
    }

    @Override
    public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int p_149664_5_) {
        handleBreak(world, x, y, z);
    }

    @Override
    public void onBlockExploded(World world, int x, int y, int z, Explosion explosion) {
        handleBreak(world, x, y, z);
    }

    public void handleBreak(World world, int x, int y, int z){
        if(!world.isRemote){
            int j = 1;
            for (int i = 1; i <= 3; i++) {
                if (world.getBlock(x, y + i, z) == BlockSetup.stockadeBlock) {
                    j += 1;
                    world.setBlock(x, y + i, z, Blocks.air);
                }
            }
            for(int i = 1; i<= j;i++){
                EntityItem item = new EntityItem(world,x,y,z,new ItemStack(TFCItems.stick,world.rand.nextInt(1)+1,0));
                world.spawnEntityInWorld(item);
            }
        }
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        if(!world.isRemote){
            ItemStack is = player.inventory.getCurrentItem();
            if(is!=null&&(is.getItem() instanceof ItemCustomAxe||is.getItem() instanceof ItemKnife)){
                if(world.getBlock(x,y+1,z)==Blocks.air){
                    is.damageItem(1,player);
                    world.setBlockMetadataWithNotify(x,y,z, world.getBlockMetadata(x,y,z)+4,3 );
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {

    }

    @Override
    public boolean canBlockStay(World world, int x, int y, int z) {
        return canSupport(world,x,y-1,z)||world.getBlock(x,y-1,z)==this;
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
        return null;
    }

    @Override
    public int getRenderType() {
        return BlockSetup.stockadeRenderId;
    }
}
