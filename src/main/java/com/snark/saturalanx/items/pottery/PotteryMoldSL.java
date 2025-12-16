package com.snark.saturalanx.items.pottery;

import com.dunk.tfc.Items.Pottery.ItemPotteryMold;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import java.util.List;

import static com.snark.saturalanx.SaturaLanx.tab;

public class PotteryMoldSL extends ItemPotteryMold {
boolean b;
    public PotteryMoldSL(boolean b) {
        super();
        this.b = b;
        this.setCreativeTab(tab);
    }

    @Override
    public void registerIcons(IIconRegister registerer) {

        this.clayIcon = registerer.registerIcon("saturalanx:pottery/"+this.metaNames[0]);
        this.ceramicIcon = registerer.registerIcon("saturalanx:pottery/"+this.metaNames[1]);
        if (this.metaNames.length > 2) {
            this.metalIcons = new IIcon[this.metaNames.length - 2];

            for (int i = 0; i < this.metalIcons.length; ++i) {
                this.metalIcons[i] = registerer.registerIcon("saturalanx:pottery/" + this.metaNames[2 + i]);
            }
        }

    }

    @Override
    public IIcon getIconFromDamage(int damage) {
        if (damage > this.counter + 1) {
            damage = (damage - 2) % this.counter + 2;
        }

        if (damage == 0) {
            return this.clayIcon;
        } else if (damage == 1) {
            return this.ceramicIcon;
        } else {
            return damage < this.metaNames.length ? this.metalIcons[damage - 2] : this.clayIcon;
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tabs, List list) {

        for(int i = 0; i < this.counter; ++i) {
            if(!(this.b&&i==0))
             list.add(new ItemStack(item, 1, i));
        }

    }
}