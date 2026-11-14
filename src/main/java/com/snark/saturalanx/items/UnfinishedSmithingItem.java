package com.snark.saturalanx.items;

import com.dunk.tfc.Core.TFC_Core;
import com.dunk.tfc.Items.*;
import com.dunk.tfc.Items.Tools.ItemTerraTool;
import com.dunk.tfc.api.HeatRegistry;
import com.dunk.tfc.api.TFCItems;
import com.dunk.tfc.api.TFC_ItemHeat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

import java.util.List;

public class UnfinishedSmithingItem extends ItemSL {
    public UnfinishedSmithingItem(String[] s){
        super(s);
    }

    public void addExtraInformation(ItemStack is, EntityPlayer player, List<String> arraylist) {
        ItemTerraTool.addSmithingBonusInformation(is, arraylist);
        if (TFC_ItemHeat.hasTemp(is)) {
            String s = "";
            if (HeatRegistry.getInstance().isTemperatureDanger(is)) {
                s = s + EnumChatFormatting.WHITE + TFC_Core.translate("gui.ingot.danger") + " | ";
            }

            if (HeatRegistry.getInstance().isTemperatureWeldable(is)) {
                s = s + EnumChatFormatting.WHITE + TFC_Core.translate("gui.ingot.weldable") + " | ";
            }

            if (HeatRegistry.getInstance().isTemperatureWorkable(is)) {
                s = s + EnumChatFormatting.WHITE + TFC_Core.translate("gui.ingot.workable");
            }

            if (!s.isEmpty()) {
                arraylist.add(s);
            }
        }
    }
}
