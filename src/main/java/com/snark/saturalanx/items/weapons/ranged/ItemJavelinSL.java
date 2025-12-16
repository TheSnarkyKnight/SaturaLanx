package com.snark.saturalanx.items.weapons.ranged;

import com.dunk.tfc.Items.Tools.ItemJavelin;
import com.dunk.tfc.Render.Item.PoleItemRenderer;
import com.snark.saturalanx.SaturaLanx;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraftforge.client.MinecraftForgeClient;

public class ItemJavelinSL extends ItemJavelin {
    public ItemJavelinSL(ToolMaterial par2EnumToolMaterial, float damage) {
        super(par2EnumToolMaterial, damage);
        this.setCreativeTab(SaturaLanx.tab);
    }

    public void registerIcons(IIconRegister registerer) {
        String name = this.getUnlocalizedName().replace("item.", "");
        this.itemIcon = registerer.registerIcon("saturalanx:weapons/" + name);
        MinecraftForgeClient.registerItemRenderer(this, new PoleItemRenderer());
    }

}
