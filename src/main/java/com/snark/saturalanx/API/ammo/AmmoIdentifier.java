package com.snark.saturalanx.API.ammo;

import net.minecraft.item.Item;

import java.util.Objects;

public class AmmoIdentifier{
    private Item item;
    private Integer meta;

    public AmmoIdentifier(){

    }

    public AmmoIdentifier(Item item, int meta){
        this.item = item;
        this.meta = meta;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Integer getMeta() {
        return meta;
    }

    public void setMeta(Integer meta) {
        this.meta = meta;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof AmmoIdentifier && this.item == ((AmmoIdentifier) obj).item && this.meta == ((AmmoIdentifier) obj).meta;
    }

    @Override
    public int hashCode() {
        return Objects.hash(item, meta);
    }
}
