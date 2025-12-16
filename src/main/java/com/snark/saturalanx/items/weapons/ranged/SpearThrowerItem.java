package com.snark.saturalanx.items.weapons.ranged;

import com.dunk.tfc.Entities.EntityJavelin;
import com.dunk.tfc.Items.ItemQuiver;
import com.dunk.tfc.Items.Tools.ItemJavelin;
import com.dunk.tfc.api.Crafting.AnvilManager;
import com.dunk.tfc.api.Enums.EnumAmmo;
import com.dunk.tfc.api.Enums.EnumSize;
import com.dunk.tfc.api.Interfaces.ISize;
import com.snark.saturalanx.core.Config;
import com.snark.saturalanx.items.ItemSL;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class SpearThrowerItem extends ItemSL {
    private float forceMult;
    private int readyTime;

    public SpearThrowerItem(int maxDamage, float forceMult){
        super();
        this.setSize(EnumSize.LARGE);
        this.setMaxDamage(maxDamage);
        this.textureFolder = "weapons";
        this.forceMult = forceMult;
        this.readyTime = Config.spearThrowerReadyTime;
        this.stackable = false;
        this.maxStackSize = 1;
    }

    @Override
    public boolean canStack() {
        return false;
    }

    public float getUseSpeed(EntityPlayer player) {
        float speed = 20.0F;
        ItemStack[] armor = player.inventory.armorInventory;
        if (armor[0] != null && armor[0].getItem() instanceof ISize) {
            speed += 20.0F / (float)((ISize)armor[0].getItem()).getWeight(armor[0]).multiplier;
        }

        if (armor[1] != null && armor[1].getItem() instanceof ISize) {
            speed += 20.0F / (float)((ISize)armor[1].getItem()).getWeight(armor[1]).multiplier;
        }

        if (armor[2] != null && armor[2].getItem() instanceof ISize) {
            speed += 20.0F / (float)((ISize)armor[2].getItem()).getWeight(armor[2]).multiplier;
        }

        if (armor[3] != null && armor[3].getItem() instanceof ISize) {
            speed += 20.0F / (float)((ISize)armor[3].getItem()).getWeight(armor[3]).multiplier;
        }

        return speed;
    }

    @Override
    public EnumAction getItemUseAction(ItemStack p_77661_1_) {
        return EnumAction.none;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return 72000;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack is, World world, EntityPlayer player){

        if(getInventorySlotContainJavelin(player)>-1||consumeJavelinInQuiver(player,false)!=null)
            player.setItemInUse(is, this.getMaxItemUseDuration(is));

        return is;
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World world, EntityPlayer player, int inUseCount) {

        int elapsedTime = stack.getMaxItemUseDuration() - inUseCount;

        if (elapsedTime > (readyTime*20)) {
            player.swingItem();
            if (!world.isRemote) {
                ItemStack javelinIs = null;
                int j = getInventorySlotContainJavelin(player);
                if(j>-1){
                    javelinIs = player.inventory.mainInventory[j];
                    if(!player.capabilities.isCreativeMode)
                        player.inventory.mainInventory[j] = null;
                }
                else{
                    javelinIs = consumeJavelinInQuiver(player,!player.capabilities.isCreativeMode);
                }

                if(javelinIs!=null){
                    float force = Math.min((elapsedTime/20F)+readyTime,1.0F)*forceMult;
                    EntityJavelin javelinE = new EntityJavelin(world,player,force*1.5F);
                    javelinE.setDamage(((ItemJavelin)javelinIs.getItem()).getRangedDamage(javelinIs));
                    javelinE.duraBuff = AnvilManager.getDurabilityBuff(javelinIs);
                    javelinE.damageBuff = AnvilManager.getDamageBuff(javelinIs);

                    int var9 = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, javelinIs);
                    if (var9 > 0) {
                        javelinE.setDamage(javelinE.getDamage() + (double)var9 * (double)0.5F + (double)0.5F);
                    }

                    int var10 = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, javelinIs);
                    if (var10 > 0) {
                        javelinE.setDamage(javelinE.getDamage() + (double)var10);
                    }

                    if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, javelinIs) > 0) {
                        javelinE.setFire(100);
                    }

                    world.playSoundAtEntity(player, "random.bow", 1.0F, 0.3F);
                    javelinE.setDamageTaken((short)javelinIs.getItemDamage());
                    javelinE.setPickupItem(javelinIs.getItem());

                    stack.damageItem(1,player);
                    
                    if (!world.isRemote) {
                        world.spawnEntityInWorld(javelinE);
                    }
                }

            }
        }
    }

    private int getInventorySlotContainJavelin(EntityPlayer player) {
        for(int j = 0; j < player.inventory.mainInventory.length; ++j) {
            if (player.inventory.mainInventory[j] != null && player.inventory.mainInventory[j].getItem() instanceof ItemJavelin) {
                return j;
            }
        }

        return -1;
    }

    public ItemStack consumeJavelinInQuiver(EntityPlayer player, boolean shouldConsume) {
        ItemStack quiver = player.inventory.getStackInSlot(36);
        return quiver != null && quiver.getItem() instanceof ItemQuiver ? ((ItemQuiver)quiver.getItem()).consumeAmmo(quiver, EnumAmmo.JAVELIN, shouldConsume) : null;
    }
}
