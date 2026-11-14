package com.snark.saturalanx.items.weapons.gunpowder.guns;

import com.snark.saturalanx.API.ammo.AmmoData;
import com.snark.saturalanx.core.Config;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class MatchlockBlunderbussItem extends MatchlockFirearmItem implements IBlunderbluss{
    public MatchlockBlunderbussItem(ToolMaterial par2EnumToolMaterial, float damage) {
        super(par2EnumToolMaterial, damage);
    }

    @Override
    protected void setupParameters() {
        this.oneHanded = false;
        this.mounted = false;
        this.bulletDamage = Config.mBlunderbussDamage;
        this.bulletNumber = Config.mBlunderbussPellets;
        this.loadingTime = Config.mBlunderbussLoadingTime;
        this.firingTime = Config.mBlunderbussAimingTime;
    }

    @Override
    public boolean isValidAmmo(ItemStack itemStack) {
        return isValidBlunderbussAmmo(itemStack);
    }

    @Override
    public AmmoData getAmmoData(ItemStack itemStack) {
        return getBlunderbussAmmoData(itemStack);
    }

    @Override
    public boolean hasWadding(EntityPlayer player, ItemStack itemStack) {
        return hasBWadding(player,itemStack);
    }

    @Override
    public void loadGun(EntityPlayer player, ItemStack itemStack) {
        loadBlunderbluss(player,itemStack);
    }

    @Override
    public void fireGun(EntityPlayer player, ItemStack itemStack, World world) {
        fireBlunderbuss(player,itemStack,world,bulletDamage,bulletNumber);
    }
}
