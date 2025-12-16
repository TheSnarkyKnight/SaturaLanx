package com.snark.saturalanx.items.tools;

import com.dunk.tfc.Items.Tools.ItemMiscToolHead;
import net.minecraft.client.renderer.texture.IIconRegister;

import static com.snark.saturalanx.SaturaLanx.tab;

public class ToolHeadSL extends ItemMiscToolHead {

    private String path;
    public ToolHeadSL(){
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
