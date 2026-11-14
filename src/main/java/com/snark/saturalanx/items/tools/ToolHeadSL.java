package com.snark.saturalanx.items.tools;

import com.dunk.tfc.Items.Tools.ItemMiscToolHead;
import com.dunk.tfc.api.Constant.Global;
import com.dunk.tfc.api.Metal;
import net.minecraft.client.renderer.texture.IIconRegister;

import static com.snark.saturalanx.SaturaLanx.tab;

public class ToolHeadSL extends ItemMiscToolHead {

    public ToolHeadSL(String name, Metal metal){
        super();
        this.setCreativeTab(tab);
        this.setUnlocalizedName(name);
        this.setMetal(metal);
    }

    @Override
    public void registerIcons(IIconRegister registerer){
        String name = this.getUnlocalizedName().replace("item.","");
        this.itemIcon = registerer.registerIcon(textureFolder + name);
    }

    public Metal getMetalNoArgs(){
        return this.getMetal(Global.UNKNOWN);
    }
}
