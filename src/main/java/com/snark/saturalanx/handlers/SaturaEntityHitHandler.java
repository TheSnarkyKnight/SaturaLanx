package com.snark.saturalanx.handlers;

import com.dunk.tfc.Handlers.EntityDamageHandler;
import com.snark.saturalanx.core.Config;
import com.snark.saturalanx.entities.EntityBolas;
import com.snark.saturalanx.entities.EntityBullet;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

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
                        duration += (int) (Math.random() * Config.bolasDurationModifier);
                    else
                        duration -= (int) (Math.random() * Config.bolasDurationModifier);

                    if (duration < 0)
                        duration = 0;

                    entity.addPotionEffect(new PotionEffect(2, duration, amplifier));
                }
            }
        }

        //Gun bullets handler
        else if(source instanceof EntityBullet && !entity.isDead){

            //Resets the i-frames to 0, necessary for blunderbuss pellets to not be largely ignored
            entity.hurtResistantTime = 0;

            ItemStack is = ((EntityBullet) source).getSampleStack();
            //Silver bullet holy damage handler
            if(is.getItemDamage() == 4 && entity.isEntityUndead()){
                event.ammount *= 1.3F;
            }

        }

    }

}
