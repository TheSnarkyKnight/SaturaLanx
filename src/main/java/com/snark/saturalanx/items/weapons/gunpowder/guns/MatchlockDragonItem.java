package com.snark.saturalanx.items.weapons.gunpowder.guns;

import com.snark.saturalanx.core.Config;

public class MatchlockDragonItem extends MatchlockBlunderbussItem{
    public MatchlockDragonItem(ToolMaterial par2EnumToolMaterial, float damage) {
        super(par2EnumToolMaterial, damage);
    }

    @Override
    protected void setupParameters() {
        this.oneHanded = true;
        this.mounted = true;
        this.bulletDamage = Config.mDragonDamage;
        this.bulletNumber = Config.mDragonPellets;
        this.loadingTime = Config.mDragonLoadingTime;
        this.firingTime = Config.mDragonAimingTime;
    }
}
