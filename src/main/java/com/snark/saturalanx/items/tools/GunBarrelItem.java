package com.snark.saturalanx.items.tools;

import com.dunk.tfc.api.Metal;

import static com.snark.saturalanx.core.ItemSetup.WEAPONPATH;

public class GunBarrelItem extends ToolHeadSL{

    public GunBarrelItem(String name, Metal metal) {
        super(name, metal);
        this.textureFolder = WEAPONPATH + "guns/components/";
    }
}
