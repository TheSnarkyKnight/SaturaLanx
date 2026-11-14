package com.snark.saturalanx.items.weapons.gunpowder;

import com.snark.saturalanx.core.ItemSetup;
import com.snark.saturalanx.items.ItemSL;
import net.minecraft.item.Item;

public class PaperCartridgeItem extends ItemSL {

    public PaperCartridgeItem(String[] s){
        super(s);
    }

    public Item getContainedItem(){
        if(this == ItemSetup.paperCartridge)
            return ItemSetup.bulletSL;
        else
            return ItemSetup.shotSL;
    }
}
