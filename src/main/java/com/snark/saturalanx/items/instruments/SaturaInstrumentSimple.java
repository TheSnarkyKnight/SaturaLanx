package com.snark.saturalanx.items.instruments;

import com.dunk.tfc.api.Enums.EnumItemReach;
import com.dunk.tfc.api.Enums.EnumSize;
import com.dunk.tfc.api.Enums.EnumWeight;
import com.snark.saturalanx.items.ItemSatura;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import static com.snark.saturalanx.SaturaLanx.tab;

public class SaturaInstrumentSimple extends ItemSatura {
    private String sound, sound2;
    private float volume, volume2, pitch, pitch2;

    public SaturaInstrumentSimple(float v, float v2, float p, float p2){
        super();
        this.setMaxStackSize(1);
        this.volume = v;
        this.volume2 = v2;
        this.pitch = p;
        this.pitch2 = p2;
    }

    @Override
    public boolean canStack() {
        return false;
    }

    public void setSounds(String[] sounds){
        this.sound = sounds[0];
        this.sound2 = sounds[1];
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
        Vec3 lookVec = player.getLookVec();
        float lookAngle = (float)(lookVec.yCoord / 2.0);
        if(player.isSneaking())
            world.playSoundAtEntity(player,sound2,volume2,pitch2+lookAngle);
        else
            world.playSoundAtEntity(player,sound,volume,pitch+lookAngle);

        return itemStack;
    }
}
