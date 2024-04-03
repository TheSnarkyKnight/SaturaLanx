package com.snark.saturalanx.items.warfare;


import com.dunk.tfc.Core.TFC_Core;
import com.dunk.tfc.Items.Tools.ItemHammer;
import com.dunk.tfc.api.Enums.EnumSize;
import com.dunk.tfc.api.Enums.EnumWeight;
import com.dunk.tfc.api.TFCBlocks;
import com.snark.saturalanx.SaturaLanx;
import com.snark.saturalanx.core.BlockSetup;
import com.snark.saturalanx.items.ItemSatura;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.List;

import static com.snark.saturalanx.SaturaLanx.MODID;

public class SpikeItem extends ItemSatura {
    protected IIcon[] icons;


    public SpikeItem(){
        this.setUnlocalizedName("SpikeItem");
        this.hasSubtypes = true;
        this.setCreativeTab(SaturaLanx.tab);
        this.setMaxStackSize(64);
        this.metaNames = new String[]{"SharpenedStick","IronSpike"};
        this.icons = new IIcon[metaNames.length];
    }

    @Override
    public EnumSize getSize(ItemStack is) {
        return EnumSize.MEDIUM;
    }

    @Override
    public EnumWeight getWeight(ItemStack is) {
        return EnumWeight.MEDIUM;
    }

    @Override
    public void registerIcons(IIconRegister registerer) {
        for(int i = 0;i< metaNames.length;i++){
            this.icons[i] = registerer.registerIcon(MODID + ":weapons/" + this.metaNames[i]);
        }
    }

    @Override
    public int getItemStackLimit(ItemStack is) {
        return 64;
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        Block b = world.getBlock(x,y,z);

        if(side == 1){
            if(stack.getItemDamage()==0 && (TFC_Core.isGrass(b) || TFC_Core.isDirt(b) || TFC_Core.isSand(b) || TFC_Core.isGravel(b))){
                if(!world.isRemote){
                    ItemStack heldItem = player.inventory.getCurrentItem();
                    if(heldItem.stackSize>=4) {
                        world.setBlock(x, y + 1, z, BlockSetup.spikeBlock, 0, 0);
                        heldItem.stackSize -= 4;
                    }
                    else
                        return false;
                }
            }
            if(stack.getItemDamage()>0 && ((TFC_Core.isGrass(b) || TFC_Core.isDirt(b) || TFC_Core.isSand(b) || TFC_Core.isGravel(b))||(b.getMaterial()==Material.rock&&hasHammer(player)))){
                if(!world.isRemote){
                    ItemStack heldItem = player.inventory.getCurrentItem();
                    if(heldItem.stackSize>=4) {
                        world.setBlock(x, y + 1, z, BlockSetup.spikeBlock, 1, 0);
                        heldItem.stackSize -= 4;
                    }
                    else
                        return false;
                }
            }
            return true;
        }
        else{
            return b == TFCBlocks.toolRack;
        }
    }

    private boolean hasHammer(EntityPlayer player){
        if(player.capabilities.isCreativeMode)
            return true;

        for(int i = 0;i < 9;i++){
                if(player.inventory.mainInventory[i]!=null && player.inventory.mainInventory[i].getItem() instanceof ItemHammer)
                    return true;
            }

            return false;
        }

    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int damage) {
        if(damage> icons.length)
            damage = 0;
        return icons[damage];
    }

    public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List list) {
        if (this.metaNames != null) {
            for (int i = 0; i < this.metaNames.length; ++i) {
                list.add(new ItemStack(this, 1, i));
            }
        }

    }
}





