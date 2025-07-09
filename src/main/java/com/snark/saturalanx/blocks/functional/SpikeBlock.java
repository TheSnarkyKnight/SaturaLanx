package com.snark.saturalanx.blocks.functional;

import com.dunk.tfc.Blocks.BlockTerra;
import com.dunk.tfc.Core.TFC_Core;
import com.snark.saturalanx.core.Config;
import com.snark.saturalanx.core.ItemSetup;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

import static com.snark.saturalanx.SaturaLanx.MODID;

public class SpikeBlock extends BlockTerra{
    protected IIcon[] icons;
    protected String[] names;
    public static DamageSource spikeDamageSource;
    public static String damageShape = "1x3";

    public SpikeBlock(){
        this.setBlockName("SpikeBlock");
        this.setCreativeTab(null);
        spikeDamageSource = new DamageSource("spikes");
        names = new String[]{"WoodSpikes","IronSpikes"};
        icons = new IIcon[names.length];
        this.setHarvestLevel("axe",0,0);
    }

    @Override
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {

        if(entity instanceof EntityLivingBase && !isSneakingPlayer(entity)){
            entity.motionX *= 0.7F;
            entity.motionZ *= 0.7F;
            int damage;
            int metadata = world.getBlockMetadata(x,y,z);
            if(metadata==0)
                damage = Config.woodenSpikesDamage;
            else
                damage = Config.metalSpikesDamage;

            if(entity.fallDistance>=1){
                damage += (entity.fallDistance * 8F) * 10F;
                if(metadata==0){
                    if((Math.random() * 100) < Config.woodenSpikesBreakChance){
                        world.playSoundAtEntity(entity,"minecraft:entity.zombie.break_wooden_door",1.0F,1.0F);
                        world.setBlockToAir(x,y,z);
                    }

                }

            }

            entity.attackEntityFrom(spikeDamageSource,damage);
        }

        super.onEntityCollidedWithBlock(world, x, y, z, entity);
    }

    @Override
    public boolean renderAsNormalBlock ()
    {
        return false;
    }

    private boolean isSneakingPlayer(Entity entity){
        if(entity instanceof EntityPlayer)
            return entity.isSneaking() && entity.fallDistance < 1;
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons (IIconRegister registerer)
    {
        for(int i = 0;i< names.length;i++){
            this.icons[i] = registerer.registerIcon(MODID + ":spikes/"+names[i]);
        }
    }

    @Override
    public Item getItemDropped(int meta, Random random, int fortune) {
        return ItemSetup.spikeItem;
    }

    @Override
    public int damageDropped(int metadata) {
        if(metadata > names.length)
            metadata = 0;
        return metadata;
    }

    @Override
    public int quantityDropped(int meta, int fortune, Random random) {
        if(meta==0)
            return (int) (Math.random() * 4);
        else
            return 4;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered (IBlockAccess world, int x, int y, int z, int side)
    {
        return true;
    }

    @Override
    public String getHarvestTool(int metadata) {
        if(metadata==0)
            return "axe";
        else
            return "pickaxe";
    }

    @Override
    public int getHarvestLevel(int metadata) {
        return 0;
    }

    @Override
    public float getBlockHardness(World world, int x, int y, int z) {
        if(world.getBlockMetadata(x,y,z)==0)
            return 6.0F;
        else
            return 10.0F;
    }

    @Override
    public boolean canBlockStay(World world, int x, int y, int z) {
        Block b = world.getBlock(x,y-1,z);
        int meta = world.getBlockMetadata(x,y,z);
        if(b.isBlockSolid(world,x,y-1,z,1)){
            if(meta==0&&(TFC_Core.isGrass(b) || TFC_Core.isDirt(b) || TFC_Core.isSand(b) || TFC_Core.isGravel(b)))
                return true;
            if(meta>0)
                return true;
        }
        return false;
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
        if(!world.isRemote){
            if(!canBlockStay(world,x,y,z)) {
                int meta = world.getBlockMetadata(x,y,z);
                world.setBlockToAir(x, y, z);
                this.dropBlockAsItem(world,x,y,z,meta,0);
            }
        }
    }

    @Override
    public void getSubBlocks(Item item, CreativeTabs tab, List list) {
        for (int i = 0; i < names.length; i ++) {
            list.add(new ItemStack(item, 1, i));
        }
    }

    @Override
    public IIcon getIcon(int side, int meta){
        if(meta > names.length)
            meta = 0;

        return icons[meta];
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
        return null;
    }

    public int getRenderType() {
        return 1;
    }

    public boolean isOpaqueCube() {
        return false;
    }

}
