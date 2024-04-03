package com.snark.saturalanx.blocks.building;

import com.dunk.tfc.Blocks.BlockTerra;
import com.dunk.tfc.api.Enums.EnumItemReach;
import com.dunk.tfc.api.Enums.EnumSize;
import com.dunk.tfc.api.Enums.EnumWeight;
import com.dunk.tfc.api.Interfaces.ISize;
import com.snark.saturalanx.SaturaLanx;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import java.util.List;

import static com.snark.saturalanx.SaturaLanx.MODID;

public class ColouredBricks extends BlockTerra{

    protected IIcon[] icons;
    protected String[] names;

    public ColouredBricks(){
        super(Material.rock);
        setCreativeTab(SaturaLanx.tab);
        setHardness(8.0F);
        this.setBlockName("ColouredBricks");
        this.names = new String[]{"HematiteBrickBlock","LapisBrickBlock","LimoniteBrickBlock","MalachiteBrickBlock"};
        icons = new IIcon[names.length];
    }

    @Override
    public IIcon getIcon(int side, int meta){
        if(meta > names.length)
            meta = 0;

        return icons[meta];
    }

    @Override
    public void registerBlockIcons(IIconRegister registerer) {
        for(int i =0; i < names.length; i++)
            icons[i] = registerer.registerIcon(MODID + ":colouredbricks/" +this.names[i]);
    }

    @Override
    public int damageDropped(int meta) {
        return meta;
    }

    @Override
    public void getSubBlocks(Item item, CreativeTabs tab, List list) {
        for (int i = 0; i < names.length; i ++) {
            list.add(new ItemStack(item, 1, i));
        }
    }

}

