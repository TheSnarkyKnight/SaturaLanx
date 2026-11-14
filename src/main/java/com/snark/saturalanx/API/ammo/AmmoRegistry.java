package com.snark.saturalanx.API.ammo;

import com.snark.saturalanx.SaturaLanx;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class AmmoRegistry {

    private LinkedHashMap<AmmoIdentifier,AmmoData> ammoRegistry;

    public AmmoRegistry(){
        ammoRegistry = new LinkedHashMap<>();
    }

    public void addAmmoData(ItemStack itemStack, AmmoData ammoData){
            AmmoIdentifier id = new AmmoIdentifier(itemStack.getItem(),itemStack.getItemDamage());
            ammoRegistry.put(id, ammoData);
    }

    public void addAmmoData(AmmoIdentifier id, AmmoData data){
        ammoRegistry.put(id,data);
    }

    public void overwriteAmmoData(ItemStack itemStack, AmmoData ammoData){
        if(!ammoRegistry.containsKey(itemStack))
            SaturaLanx.log.warn("Tried overwriting ammo data with ID {}, but no such id was found. Adding to registry.", ammoData.getId());

        AmmoIdentifier id = new AmmoIdentifier(itemStack.getItem(),itemStack.getItemDamage());
        ammoRegistry.put(id, ammoData);
    }

    public AmmoData getAmmoDataFromItemStack(ItemStack itemStack){
        return ammoRegistry.get(new AmmoIdentifier(itemStack.getItem(),itemStack.getItemDamage()));
    }

    public AmmoData getAmmoDataFromID(String id){
        List<AmmoData> l = new ArrayList<>(ammoRegistry.values());
        for(AmmoData a : l) {
            if (a.getId().equals(id))
                return a;
        }
        return null;
    }

    public LinkedHashMap<AmmoIdentifier, AmmoData> getAmmoRegistry(){
        return ammoRegistry;
    }

}
