package com.snark.saturalanx.blocks.building;

import com.dunk.tfc.Blocks.BlockTerra;
import com.snark.saturalanx.SaturaLanx;
import com.snark.saturalanx.core.BlockSetup;
import com.snark.saturalanx.core.Config;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.obj.WavefrontObject;

import java.util.List;

import static com.snark.saturalanx.SaturaLanx.MODID;
import static com.snark.saturalanx.blocks.functional.SpikeBlock.spikeDamageSource;

public class ChevalDeFrise extends BlockTerra {
    private WavefrontObject model;

    public ChevalDeFrise(){
        super(Material.wood);
        setCreativeTab(SaturaLanx.tab);
        this.setHardness(10.0F);
        this.setStepSound(Block.soundTypeWood);
        this.setHarvestLevel("axe",0);
        this.setBlockName("ChevalDeFrise");
    }

    public WavefrontObject getModel(){
        return model;
    }

    @Override
    public void registerBlockIcons(IIconRegister registerer) {
        this.blockIcon = registerer.registerIcon(MODID+":modeled/ChevalDeFrise");
        this.model = (WavefrontObject) AdvancedModelLoader.loadModel(new ResourceLocation(MODID +":models/blocks/ChevalDeFrise.obj"));
    }

    @Override
    public IIcon getIcon(int p_149691_1_, int p_149691_2_) {
        return this.blockIcon;
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public int getRenderType() {
        return BlockSetup.chevalDeFriseRenderId;
    }

    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack is) {
        int side = MathHelper.floor_double((double)(entity.rotationYaw*4.0/360+0.5)) & 3;
        if(!world.isRemote){
            if (side == 1 || side == 3)
                world.setBlock(x, y, z, this, 1, 3);
        }
    }

    public MovingObjectPosition collisionRayTrace(World world, int x, int y, int z, Vec3 startVec, Vec3 endVec) {
        if (world.getBlockMetadata(x,y,z)==0){
            this.setBlockBounds(0,0,0.1F,1,1,0.9F);
        }
        else{
            this.setBlockBounds(0.1F,0,0,0.9F,1,1);
        }
        return super.collisionRayTrace(world, x, y, z, startVec, endVec);
    }

    public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB aaBB, List list, Entity entity) {
        if (world.getBlockMetadata(x,y,z)==0){
            this.setBlockBounds(0,0,0.1F,1,1.5F,0.9F);
        }
        else{
            this.setBlockBounds(0.1F,0,0,0.9F,1.5F,1);
        }
        super.addCollisionBoxesToList(world, x, y, z, aaBB, list, entity);
    }

    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
        if(entity.boundingBox.expand(0.05,-0.2,0.05).intersectsWith(world.getBlock(x,y,z).getCollisionBoundingBoxFromPool(world,x,y,z))){
            if(!(entity instanceof EntityPlayer && entity.isSneaking())){
                entity.motionX *= 0.7F;
                entity.motionZ *= 0.7F;

                int damage = Config.woodenSpikesDamage;
                if (entity.fallDistance >= 1)
                    damage += (int) ((entity.fallDistance * 8F) * 10F);

                entity.attackEntityFrom(spikeDamageSource, damage);
            }
        }
    }
}
