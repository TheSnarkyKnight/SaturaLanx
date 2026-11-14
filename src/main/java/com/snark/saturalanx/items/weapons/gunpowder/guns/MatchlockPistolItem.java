package com.snark.saturalanx.items.weapons.gunpowder.guns;

import com.snark.saturalanx.core.Config;

public class MatchlockPistolItem extends MatchlockFirearmItem{
    public MatchlockPistolItem(ToolMaterial par2EnumToolMaterial, float damage) {
        super(par2EnumToolMaterial, damage);
    }

    @Override
    protected void setupParameters() {
        this.oneHanded = true;
        this.mounted = true;
        this.bulletDamage = Config.mPistolDamage;
        this.bulletNumber = 1;
        this.loadingTime = Config.mPistolLoadingTime;
        this.firingTime = Config.mPistolAimingTime;
    }
}
