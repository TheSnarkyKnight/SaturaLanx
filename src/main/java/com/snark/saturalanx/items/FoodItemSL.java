package com.snark.saturalanx.items;

import com.dunk.tfc.Core.Player.FoodStatsTFC;
import com.dunk.tfc.Core.TFC_Core;
import com.dunk.tfc.Food.ItemFoodTFC;
import com.dunk.tfc.TerraFirmaCraft;
import com.dunk.tfc.api.Enums.EnumFoodGroup;
import com.dunk.tfc.api.Interfaces.IFood;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

import static com.snark.saturalanx.SaturaLanx.tab;
import static com.snark.saturalanx.core.FoodSetup.tulipBulb;

public class Food extends ItemFoodTFC {

    public Food(EnumFoodGroup fw, int sw, int so, int sa, int bi, int um, boolean edible){
        super(fw,sw,so,sa,bi,um,edible);
        this.setCreativeTab(tab);
    }

    public Food(EnumFoodGroup fw, int sw, int so, int sa, int bi, int um, boolean edible,boolean poison){
        super(fw,sw,so,sa,bi,um,edible,poison);
        this.setCreativeTab(tab);
    }

    public Food(EnumFoodGroup fw, int sw, int so, int sa, int bi, int um, boolean edible,boolean poison, boolean guaranteePoison){
        super(fw,sw,so,sa,bi,um,edible,poison,guaranteePoison);
        this.setCreativeTab(tab);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IIconRegister register)
    {
        this.itemIcon = register.registerIcon(getIconString());
    }

    @Override
    public ItemStack onEaten(ItemStack is, World world, EntityPlayer player) {

        if(is.getItem() == tulipBulb && com.dunk.tfc.api.Food.isCooked(is) && (float)itemRand.nextInt(100)<20)
            player.addPotionEffect(new PotionEffect(Potion.poison.getId(), 100, 1));

        return super.onEaten(is,world,player);
    }
}
