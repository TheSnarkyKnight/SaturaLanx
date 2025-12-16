package com.snark.saturalanx.items;

import com.dunk.tfc.Items.ItemDrink;
import com.dunk.tfc.api.TFCItems;
import com.snark.saturalanx.core.DrinkSetup;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;

import static com.snark.saturalanx.SaturaLanx.tab;

public class Drink extends ItemDrink {

    public Drink(float v){
        super(v);
        this.setCreativeTab(tab);
    }

    public Drink(float v, int t){
        super(v,t);
        this.setCreativeTab(tab);
    }
    private void registerFluidContainerHelper(Fluid fluid, int baseVolume, ItemStack baseItem, ItemStack baseContainer) {
        FluidContainerRegistry.registerFluidContainer(new FluidStack(fluid, baseVolume), baseItem, baseContainer);
        if (DrinkSetup.potteryDrinks != null && DrinkSetup.potteryDrinks.containsKey(baseItem.getUnlocalizedName())) {
            if (baseContainer != null && baseItem != null && baseContainer.getItem() == baseItem.getItem()) {
                Item potteryVersion = (Item) DrinkSetup.potteryDrinks.get(baseItem.getUnlocalizedName());
                FluidContainerRegistry.registerFluidContainer(new FluidStack(fluid, baseVolume), new ItemStack(potteryVersion, 1, baseItem.getItemDamage()), new ItemStack(potteryVersion, 1, baseContainer.getItemDamage()));
            } else {
                FluidContainerRegistry.registerFluidContainer(new FluidStack(fluid, baseVolume), new ItemStack((Item) DrinkSetup.potteryDrinks.get(baseItem.getUnlocalizedName()), 1, baseItem.getItemDamage()), new ItemStack(TFCItems.potteryJug, 1, 1));
            }
        }

    }

    public void setupFluidDrinks(Fluid fluid, int mB, Item drink, Item container) {
        int num = mB / 50;
        this.registerFluidContainerHelper(fluid, 50, new ItemStack(drink, 1, num - 1), new ItemStack(container));

        for(int i = 0; i < num - 1; ++i) {
            this.registerFluidContainerHelper(fluid, 50, new ItemStack(drink, 1, i), new ItemStack(drink, 1, i + 1));
        }

    }

    public void setupFluidDrinks(Fluid fluid, int mB, Item drink, int minDrinkDamage, Item container, int containerDamage) {
        int num = mB / 50;
        this.registerFluidContainerHelper(fluid, 50, new ItemStack(drink, 1, minDrinkDamage + num - 1), new ItemStack(container, 1, containerDamage));

        for(int i = 0; i < num - 1; ++i) {
            this.registerFluidContainerHelper(fluid, 50, new ItemStack(drink, 1, minDrinkDamage + i), new ItemStack(drink, 1, minDrinkDamage + i + 1));
        }

    }

    @Override
    public ItemStack onEaten(ItemStack is, World world, EntityPlayer player) {
        if(this== DrinkSetup.oliveOil){
            if(Math.random()*100<60)
                player.addPotionEffect(new PotionEffect(9, (int) (Math.random() * 300)+10,1));
        }
        if(this== DrinkSetup.goldenrodTea){
            player.addPotionEffect(new PotionEffect(10, (int) (Math.random()*20)+10,1));
        }

        return super.onEaten(is, world, player);
    }
}
