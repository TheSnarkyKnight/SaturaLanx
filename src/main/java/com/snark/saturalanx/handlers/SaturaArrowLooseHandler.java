package com.snark.saturalanx.handlers;

import com.dunk.tfc.Entities.EntityProjectileTFC;
import com.dunk.tfc.Items.Tools.ItemCustomBow;
import com.snark.saturalanx.core.Config;
import com.snark.saturalanx.core.ItemSetup;
import com.snark.saturalanx.entities.EntityFlameArrow;
import com.snark.saturalanx.entities.EntityPoisonArrow;
import com.snark.saturalanx.entities.EntityRopeArrow;
import com.snark.saturalanx.items.weapons.ranged.PoisonedArrowItem;
import com.snark.saturalanx.items.weapons.incendiary.FlameArrowItem;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;

public class SaturaArrowLooseHandler{

    public SaturaArrowLooseHandler(){

    }

    @SubscribeEvent
    public void onArrowLoose(ArrowLooseEvent event){
        EntityPlayer player = event.entityPlayer;
        ItemStack bow = event.bow;
        ItemStack arrow = null;

        if(bow.getItem() instanceof ItemCustomBow && (Config.enableFlameArrows||Config.enablePoisonArrows||Config.enableRopeArrows)){
            ItemStack s;
            int index = 0;

            for(int i = 0;i < player.inventory.mainInventory.length;i++){
                s = player.inventory.mainInventory[i];

                if (s != null && s.getItem() instanceof FlameArrowItem) {
                    if (s.stackTagCompound != null && s.stackTagCompound.getBoolean("lit")) {
                        arrow = s;
                        index = i;
                    }
                }
                else if(s!=null && (s.getItem() instanceof PoisonedArrowItem || s.getItem() == ItemSetup.ropeArrow)){
                    arrow = s;
                    index = i;
                }

            }

            if(arrow != null && player.isSneaking()){
                event.setCanceled(true);

                int j = event.charge;
                float forceMult = (float)j / ((ItemCustomBow) bow.getItem()).getUseSpeed(player);
                forceMult *= forceMult;
                if ((double)forceMult < 0.25) {
                    return;
                }

                if (forceMult > ((ItemCustomBow) bow.getItem()).getDamageMultiplier()) {
                    forceMult = ((ItemCustomBow) bow.getItem()).getDamageMultiplier();
                }

                EntityProjectileTFC eArrow = null;

                if(arrow.getItem() instanceof FlameArrowItem){
                    forceMult = Math.min(((ItemCustomBow) bow.getItem()).getDamageMultiplier()*60/100,forceMult);

                    eArrow = new EntityFlameArrow(player.worldObj,player,forceMult*2,arrow.getMaxDamage()-arrow.getItemDamage(),((FlameArrowItem) arrow.getItem()).getIgniteChance());

                    if(arrow.getItem() == ItemSetup.fireArrow)
                        ((EntityFlameArrow)eArrow).setType(3);
                    else if(arrow.getItem() == ItemSetup.flameArrow)
                        ((EntityFlameArrow)eArrow).setType(2);
                    else
                        ((EntityFlameArrow)eArrow).setType(1);


                    if(!player.capabilities.isCreativeMode)
                        player.inventory.setInventorySlotContents(index,null);
                }
                else if(arrow.getItem() instanceof PoisonedArrowItem){
                    eArrow = new EntityPoisonArrow(player.worldObj,player,forceMult*2,arrow.stackTagCompound);

                    if(!player.capabilities.isCreativeMode){
                        if(player.inventory.mainInventory[index].stackSize-- <= 0)
                            player.inventory.setInventorySlotContents(index,null);
                    }
                }
                else if(arrow.getItem() == ItemSetup.ropeArrow){
                    eArrow = new EntityRopeArrow(player.worldObj,player,forceMult*2);

                    if(!player.capabilities.isCreativeMode){
                        if(player.inventory.mainInventory[index].stackSize-- <= 0)
                            player.inventory.setInventorySlotContents(index,null);
                    }
                }
                eArrow.setDamage((double)forceMult * 60.0);

                if (forceMult >= 1.24F) {
                    eArrow.setIsCritical(true);
                }

                int k = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, bow);
                if (k > 0) {
                    eArrow.setDamage(eArrow.getDamage() + (double)k * 0.5 + 0.5);
                }

                int l = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, bow);
                if (l > 0) {
                    eArrow.setKnockbackStrength(l);
                }

                if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, bow) > 0) {
                    eArrow.setFire(100);
                }

                bow.damageItem(1,player);
                player.worldObj.playSoundAtEntity(player, "random.bow", 1.0F, 1.0F / (player.worldObj.rand.nextFloat() * 0.4F + 1.2F) + forceMult * 0.5F);
                if(!player.worldObj.isRemote)
                    player.worldObj.spawnEntityInWorld(eArrow);
            }

        }
    }

}
