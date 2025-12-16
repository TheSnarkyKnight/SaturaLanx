package com.snark.saturalanx.items;

import com.dunk.tfc.Items.Tools.ItemMiscToolHead;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;

import static com.snark.saturalanx.SaturaLanx.tab;

public class ToolHead extends ItemMiscToolHead {

    private String path;
    public ToolHead(){
        super();
        this.setCreativeTab(tab);
        path = "";
    }

    @Override
    public void registerIcons(IIconRegister registerer){
        String name = this.getUnlocalizedName().replace("item.","");
        this.itemIcon = registerer.registerIcon(path + name);
    }

     public void setPath(String p){
        this.path = p;
     }

     public String getPath(){
        return this.path;
     }
}
