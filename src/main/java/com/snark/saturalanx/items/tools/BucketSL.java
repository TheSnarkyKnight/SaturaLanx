package com.snark.saturalanx.items.tools;

import com.dunk.tfc.Items.Tools.ItemCustomBucket;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;

public class BucketSL extends ItemCustomBucket {

    public BucketSL(Block contents){
        super(contents);
    }

    public BucketSL(Block contents, Item container) {
        super(contents, container);
    }

    public void registerFluidContainerHelper(Fluid fluid, int baseVolume, ItemStack baseItem, ItemStack baseContainer) {
        FluidContainerRegistry.registerFluidContainer(new FluidStack(fluid, baseVolume), baseItem, baseContainer);
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

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IIconRegister register)
    {
        itemIcon = register.registerIcon(getIconString());
    }

}
