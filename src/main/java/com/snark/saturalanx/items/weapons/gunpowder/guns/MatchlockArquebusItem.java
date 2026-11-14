package com.snark.saturalanx.items.weapons.gunpowder.guns;

import com.snark.saturalanx.core.Config;

public class MatchlockArquebusItem extends MatchlockFirearmItem{

    public MatchlockArquebusItem(ToolMaterial par2EnumToolMaterial, float damage) {
        super(par2EnumToolMaterial, damage);
    }

    @Override
    protected void setupParameters() {
        this.oneHanded = false;
        this.mounted = false;
        this.bulletDamage = Config.arquebusDamage;
        this.firingTime = Config.arquebusAimingTime;
        this.loadingTime = Config.arquebusLoadingTime;
        this.bulletNumber = 1;
    }

}
