package com.snark.saturalanx.items.warfare;

import com.dunk.tfc.Items.ItemTerra;
import com.dunk.tfc.api.Enums.EnumItemReach;
import com.dunk.tfc.api.Enums.EnumSize;
import com.dunk.tfc.api.Enums.EnumWeight;
import com.dunk.tfc.api.Interfaces.ISize;
import com.snark.saturalanx.core.ItemSetup;
import com.snark.saturalanx.core.Config;
import com.snark.saturalanx.entities.EntityBolas;
import com.snark.saturalanx.renders.item.BolasItemRenderer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;

import static com.snark.saturalanx.core.ItemSetup.WEAPONPATH;
import static com.snark.saturalanx.SaturaLanx.tab;

public class Bolas extends ItemTerra{
    public int projectileDamage;
    public int maxSwing;
    public int minSwing;
    public int chance;
    private String[] bowPullIconNameArray = new String[]{"pulling_0", "pulling_1", "pulling_2", "pulling_3"};
    private IIcon[] iconArray;

    public Bolas(){
        super();
        this.setCreativeTab(tab);
        this.setMaxStackSize(1);
        this.projectileDamage = Config.bolasDamage;
        this.maxSwing = Config.bolasMaxSwing;
        this.minSwing = Config.bolasMinSwing;
        this.chance = Config.bolasChance;
    }

    @Override
    public void onUpdate(ItemStack is, World world, Entity entity, int i, boolean b) {
        if (!world.isRemote) {
            if (is.hasTagCompound() && is.stackTagCompound.hasKey("swing") && is.stackTagCompound.getInteger("swing") > 0) {
                if (entity instanceof EntityPlayer && ((EntityPlayer)entity).getHeldItem() != is) {
                    is.stackTagCompound = null;
                } else {
                    int ticksSinceSwing = is.getTagCompound().getInteger("ticksSinceSwing");
                    int swing = is.stackTagCompound.getInteger("swing");
                    EntityPlayer player = (EntityPlayer)entity;
                    int curSwingSpeed = player.isPotionActive(Potion.digSpeed) ? 6 - (1 + player.getActivePotionEffect(Potion.digSpeed).getAmplifier()) * 1 : (player.isPotionActive(Potion.digSlowdown) ? 6 + (1 + player.getActivePotionEffect(Potion.digSlowdown).getAmplifier()) * 2 : 6);
                    if (swing >= 0 || ticksSinceSwing < curSwingSpeed) {
                        is.stackTagCompound.setInteger("ticksSinceSwing", ticksSinceSwing + 1);
                    }

                    if (ticksSinceSwing >= curSwingSpeed + 3) {
                        is.stackTagCompound.setInteger("swing", is.stackTagCompound.getInteger("swing") - Math.max(swing / 3, 1));
                        is.stackTagCompound.setInteger("ticksSinceSwing", (int)((double)curSwingSpeed * 0.9));
                    }
                }
            }

        }
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return 72000;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack is, World world, EntityPlayer player){
        if (is.hasTagCompound()) {
            is.stackTagCompound.setInteger("swing", 0);
            is.stackTagCompound.setInteger("ticksSinceSwing", 0);
        } else {
            is.stackTagCompound = new NBTTagCompound();
        }

        player.setItemInUse(is, this.getMaxItemUseDuration(is));

        return is;
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World world, EntityPlayer player, int inUseCount) {

        int elapsedTime = stack.getMaxItemUseDuration() - inUseCount;

        if (elapsedTime > minSwing) {
            player.swingItem();
            if (!world.isRemote) {
                float force = (float)Math.pow((double)Math.min((float)elapsedTime / maxSwing, 1.0F), 2.0);
                force *= 1.5F;
                int chance1 = (int) (chance * force);
                EntityBolas bolas = new EntityBolas(world, player, force,Math.min(chance1,100));
                bolas.posX -= (double)(MathHelper.cos(player.rotationYaw / 180.0F * 3.1415927F) * 0.5F);
                bolas.posZ -= (double)(MathHelper.sin(player.rotationYaw / 180.0F * 3.1415927F) * 0.5F);
                bolas.setDamage(projectileDamage);

                world.playSoundAtEntity(player, "random.bow", 1.0F, 0.3F);
                bolas.setPickupItem(ItemSetup.bolas);
                if (!player.capabilities.isCreativeMode) {
                    if(stack.stackSize-->=0)
                        player.inventory.setInventorySlotContents(player.inventory.currentItem,null);
                }

                if (!world.isRemote) {
                    world.spawnEntityInWorld(bolas);
                }
            }
        }
    }


    @Override
    public EnumAction getItemUseAction(ItemStack p_77661_1_) {
        return EnumAction.none;
    }

    @Override
    public EnumSize getSize(ItemStack is) {
        return EnumSize.SMALL;
    }

    @Override
    public EnumWeight getWeight(ItemStack is) {
        return EnumWeight.MEDIUM;
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

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister par1IconRegister) {
        MinecraftForgeClient.registerItemRenderer(this, new BolasItemRenderer());
        this.itemIcon = par1IconRegister.registerIcon(WEAPONPATH + "bolas0");
        this.iconArray = new IIcon[this.bowPullIconNameArray.length];

        for(int i = 0; i < this.iconArray.length; ++i) {
            this.iconArray[i] = par1IconRegister.registerIcon(WEAPONPATH + "bolas" + (i + 1));
        }

    }

    @SideOnly(Side.CLIENT)
    public IIcon getItemIconForUseDuration(int par1) {
        return this.iconArray[par1];
    }

    public IIcon getIcon(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining) {
        if (usingItem != null && usingItem.getItem() == this) {
            int j = usingItem.getMaxItemUseDuration() - useRemaining;
            float force = (float)j / this.getUseSpeed(player);
            return (double)force < 0.5 ? this.getIcon(stack, renderPass) : this.getItemIconForUseDuration(0);
        } else {
            return this.getIcon(stack, renderPass);
        }
    }

    public EnumItemReach getReach(ItemStack is) {
        return EnumItemReach.SHORT;
    }

    @Override
    public boolean canStack() {
        return false;
    }
}


