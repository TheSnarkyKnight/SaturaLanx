package com.snark.saturalanx.items;

import com.dunk.tfc.Core.TFCTabs;
import com.dunk.tfc.Items.Pottery.ItemPotteryBase;
import com.dunk.tfc.api.Enums.EnumSize;
import com.dunk.tfc.api.Enums.EnumWeight;
import net.minecraft.client.renderer.texture.IIconRegister;

import static com.snark.saturalanx.SaturaLanx.MODID;
import static com.snark.saturalanx.SaturaLanx.tab;

public class Pottery extends ItemPotteryBase {

    public Pottery(){
        this.hasSubtypes = true;
        this.setCreativeTab(tab);
        this.metaNames = new String[]{"", ""};
        this.setWeight(EnumWeight.MEDIUM);
        this.setSize(EnumSize.SMALL);
    }

    @Override
    public void registerIcons(IIconRegister registerer) {
        this.clayIcon = registerer.registerIcon(MODID + ":pottery/" + this.metaNames[0]);
        if (this.metaNames.length > 1) {
            this.ceramicIcon = registerer.registerIcon(MODID + ":pottery/" + this.metaNames[1]);
        }
    }


}
