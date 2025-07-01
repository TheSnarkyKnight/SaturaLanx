package com.snark.saturalanx.items;

import com.dunk.tfc.Items.ItemTerra;
import com.dunk.tfc.api.Enums.EnumItemReach;
import com.dunk.tfc.api.Enums.EnumSize;
import com.dunk.tfc.api.Enums.EnumWeight;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import java.util.List;

import static com.snark.saturalanx.SaturaLanx.MODID;
import static com.snark.saturalanx.SaturaLanx.tab;

public class ItemSatura extends ItemTerra {
    public ItemSatura() {
        super();
        this.setCreativeTab(tab);
        this.setMaxDamage(-1);
        this.setSize(EnumSize.SMALL);
        this.setWeight(EnumWeight.LIGHT);
        this.setReach(EnumItemReach.SHORT);
        this.textureFolder = "";
    }

    public ItemSatura(String[] s) {
        this();
        this.hasSubtypes = true;
        this.metaNames = s;
        this.metaIcons = new IIcon[metaNames.length];
    }

    public IIcon[] getIcons(){
        return this.metaIcons;
    }

    @Override
    public void registerIcons(IIconRegister registerer) {
        if (this.hasSubtypes) {
            for (int i = 0; i < metaNames.length; i++) {
                if(!this.textureFolder.matches(""))
                    this.metaIcons[i] = registerer.registerIcon(MODID + ":" + this.textureFolder +"/" + this.metaNames[i]);
                else
                    this.metaIcons[i] = registerer.registerIcon(MODID + ":" + this.metaNames[i]);
            }
            this.itemIcon = metaIcons[0];
        } else {
            this.itemIcon = registerer.registerIcon(MODID + ":" + this.textureFolder + "/" + this.getUnlocalizedName().replace("item.", ""));
        }
    }

    @Override
    public IIcon getIconFromDamage(int i) {
        if(this.hasSubtypes){
            if(i > this.metaNames.length)
                i = 0;
            return metaIcons[i];
        }
        else{
            return itemIcon;
        }
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tabs, List list) {
        if(this.hasSubtypes)
            for(int i = 0;i<this.metaNames.length;i++){
            list.add(new ItemStack(item,1,i));
            }
        else
            list.add(new ItemStack(item, 1));
    }

    @Override
    public String getUnlocalizedName(ItemStack itemstack) {
        if(this.hasSubtypes)
            return this.getUnlocalizedName()+itemstack.getItemDamage();
        else
            return this.getUnlocalizedName();
    }
}
