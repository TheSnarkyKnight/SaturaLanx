package com.snark.saturalanx.blocks.building;

import com.dunk.tfc.Blocks.BlockTerra;
import com.dunk.tfc.Blocks.Terrain.BlockCobble;
import com.dunk.tfc.api.Enums.EnumItemReach;
import com.dunk.tfc.api.Enums.EnumSize;
import com.dunk.tfc.api.Enums.EnumWeight;
import com.dunk.tfc.api.Interfaces.ISize;
import com.snark.saturalanx.core.Config;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.List;

import static com.snark.saturalanx.SaturaLanx.MODID;
import static com.snark.saturalanx.SaturaLanx.tab;

public class MortaredCobble extends BlockTerra{

    protected IIcon[] icons;
    protected String[] names;

    public MortaredCobble() {
        super(Material.rock);
        this.setHardness(8f);
        this.setCreativeTab(tab);
    }

    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs, List list) {
        for(int i = 0; i < this.names.length; i++) {
            list.add(new ItemStack(this, 1, i));
        }
    }

    @Override
    public int damageDropped(int meta) {
        return meta;
    }

    @Override
    public IIcon getIcon(int side, int meta){
        return icons[meta];
    }

    @Override
    public void registerBlockIcons(IIconRegister iconRegisterer) {
        if(Config.enableMortaredCobbleTextures){
            for(int i = 0; i < this.names.length; ++i) {
                this.icons[i] = iconRegisterer.registerIcon(MODID+ ":mortaredcobble/" + this.names[i] + " Cobble");
            }
        }
        else{
            for(int i = 0; i < this.names.length; ++i) {
                this.icons[i] = iconRegisterer.registerIcon("terrafirmacraftplus:rocks/" + this.names[i] + " Cobble");
            }
        }
    }

    public void setNames(String[] names){
        this.names = names;
        this.icons = new IIcon[this.names.length];
    }

}
