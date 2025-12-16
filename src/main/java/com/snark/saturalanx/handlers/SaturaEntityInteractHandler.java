package com.snark.saturalanx.handlers;

import com.snark.saturalanx.core.Config;
import com.snark.saturalanx.core.ItemSetup;
import com.snark.saturalanx.entities.EntityPotGrenade;
import com.snark.saturalanx.items.weapons.gunpowder.PotGrenadeItem;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.EntityInteractEvent;

public class SaturaEntityInteractHandler {

    public SaturaEntityInteractHandler(){}

    @SubscribeEvent
    public void onInteractWithEntity(EntityInteractEvent event){
        EntityPlayer player = event.entityPlayer;
        if(!player.worldObj.isRemote){
            if(Config.enablePotGrenades && event.target instanceof EntityPotGrenade && player.inventory.getCurrentItem() == null){
                EntityPotGrenade g = (EntityPotGrenade) event.target;
                ItemStack is;
                int type = g.getType();
                float[] comp = g.getComponents();

                switch(type){
                    case 2:
                        is = new ItemStack(ItemSetup.potGrenadeQuick,1,g.getMaxFuse()-g.getFuse());
                        break;
                    case 3:
                        is = new ItemStack(ItemSetup.potGrenadeLong,1,g.getMaxFuse()-g.getFuse());
                        break;
                    case 1:
                    default:
                        is = new ItemStack(ItemSetup.potGrenade,1,g.getMaxFuse()-g.getFuse());
                        break;
                }

                ((PotGrenadeItem)is.getItem()).setComponents(is,comp[0],comp[1],comp[2],comp[3],true);

                player.inventory.mainInventory[player.inventory.currentItem] = is;

                event.target.setDead();
            }
        }
    }

}
