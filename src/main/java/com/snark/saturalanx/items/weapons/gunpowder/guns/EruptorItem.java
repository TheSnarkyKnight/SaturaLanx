package com.snark.saturalanx.items.weapons.gunpowder.guns;

import com.snark.saturalanx.API.ammo.AmmoData;
import com.snark.saturalanx.core.Config;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EruptorItem extends FirearmItem implements IBlunderbluss{

    public EruptorItem(ToolMaterial par2EnumToolMaterial, float damage){
        super(par2EnumToolMaterial, damage);
    }

    @Override
    protected void setupParameters() {
        this.bulletDamage = Config.eruptorDamage;
        this.firingTime = Config.eruptorAimingTime;
        this.loadingTime = Config.eruptorLoadingTime;
        this.bulletNumber = Config.eruptorPellets;
        this.oneHanded = false;
        this.mounted = false;
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
