package com.snark.saturalanx.items.warfare;

import com.dunk.tfc.Items.Tools.ItemCustomSword;
import com.dunk.tfc.api.Enums.EnumDamageType;
import net.minecraft.client.renderer.texture.IIconRegister;

import static com.snark.saturalanx.core.ItemSetup.WEAPONPATH;
import static com.snark.saturalanx.SaturaLanx.tab;

public class SwordSatura extends ItemCustomSword {


    public SwordSatura(ToolMaterial par2EnumToolMaterial, float damage, EnumDamageType dt) {
        super(par2EnumToolMaterial, damage, dt);
        this.setCreativeTab(tab);
    }

    public SwordSatura(ToolMaterial par2EnumToolMaterial, float damage) {
        super(par2EnumToolMaterial, damage);
        this.setCreativeTab(tab);
    }

    @Override
    public void registerIcons(IIconRegister registerer) {
        this.itemIcon = registerer.registerIcon(WEAPONPATH + this.getUnlocalizedName().replace("item.", ""));
    }
}
