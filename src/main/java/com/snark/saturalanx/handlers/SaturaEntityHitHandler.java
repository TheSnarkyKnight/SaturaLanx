package com.snark.saturalanx.handlers;

import com.dunk.tfc.Core.Player.InventoryPlayerTFC;
import com.dunk.tfc.Core.TFC_Core;
import com.dunk.tfc.Handlers.EntityArmorHandler;
import com.dunk.tfc.Handlers.EntityDamageHandler;
import com.dunk.tfc.api.Entities.IAnimal;
import com.dunk.tfc.api.Enums.EnumArmorBodyPart;
import com.dunk.tfc.api.Events.EntityArmorCalcEvent;
import com.dunk.tfc.api.TFCBlocks;
import com.dunk.tfc.api.TFCItems;
import com.snark.saturalanx.blocks.traps.SpikeBlock;
import com.snark.saturalanx.core.Config;
import com.snark.saturalanx.entities.EntityBolas;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import java.util.Random;

public class SaturaEntityHitHandler extends EntityDamageHandler {

    public SaturaEntityHitHandler() {

    }

    @SubscribeEvent
    public void onEntityHurt(LivingHurtEvent event) {
        Entity source = event.source.getSourceOfDamage();
        EntityLivingBase entity = event.entityLiving;

        //Bolas slowness handler
        if (source instanceof EntityBolas && !entity.isDead) {
            if(!(entity instanceof EntityPlayer) || Config.bolasShouldAffectPlayers)
            {
                int chance = ((EntityBolas) source).chance;
                if ((Math.random() * 100) <= chance) {
                    int duration = ((EntityBolas) source).duration;
                    int amplifier = ((EntityBolas) source).amplifier;

                    if (Math.random() * 1 == 0)
                        duration += (Math.random() * Config.bolasDurationModifier);
                    else
                        duration -= (Math.random() * Config.bolasDurationModifier);

                    if (duration < 0)
                        duration = 0;

                    entity.addPotionEffect(new PotionEffect(2, duration, amplifier));
                }
            }
        }

    }

    private int getEquipmentFlammability(EntityPlayer player) {
        int i = 0;
        for (ItemStack is : player.inventory.armorInventory) {
            if (is.getItem() == TFCItems.strawHat || is.getItem() == TFCItems.strawHat2)
                i += 5;
            if (is.getItem() == TFCItems.strawSocks || is.getItem() == TFCItems.grassSandals)
                i += 5;
            if (is.getItem() == TFCItems.grassShirt || is.getItem() == TFCItems.grassSkirt)
                i += 5;
            if (is.getItem() == TFCItems.grassCloak)
                i += 10;
        }
        return i;
    }


}
