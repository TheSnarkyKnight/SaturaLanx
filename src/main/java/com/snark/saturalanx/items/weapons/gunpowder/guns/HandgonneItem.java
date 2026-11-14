package com.snark.saturalanx.items.weapons.gunpowder.guns;

import com.snark.saturalanx.core.Config;

public class HandgonneItem extends FirearmItem{
    public HandgonneItem(ToolMaterial toolMaterial, float damageVsEntity) {
        super(toolMaterial,damageVsEntity);
    }

    @Override
    protected void setupParameters() {
        this.oneHanded = false;
        this.mounted = false;
        this.loadingTime = Config.handgonneLoadingTime;
        this.firingTime = Config.handgonneAimingTime;
        this.bulletDamage = Config.handgonneDamage;
        this.bulletNumber = 1;
    }

}
