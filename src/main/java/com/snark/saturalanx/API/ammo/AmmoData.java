package com.snark.saturalanx.API.ammo;

import com.dunk.tfc.api.Metal;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import java.util.List;
import java.util.Objects;

public class AmmoData {
    private String id;
    private Float damageModifier;
    private Metal material;


    public AmmoData(){}

    public AmmoData(String id, Float damageModifier) {
        this.id = id;
        this.damageModifier = damageModifier;
    }

    public AmmoData(String id, Float damageModifier, Metal material) {
        this.id = id;
        this.damageModifier = damageModifier;
        this.material = material;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Float getDamageModifier() {
        return damageModifier;
    }

    public void setDamageModifier(Float damageModifier) {
        this.damageModifier = damageModifier;
    }

    public Metal getMaterial() {
        return material;
    }

    public void setMaterial(Metal material) {
        this.material = material;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof AmmoData && ((AmmoData)obj).id.equals(this.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

