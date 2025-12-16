package com.snark.saturalanx.recipes;

import com.dunk.tfc.Items.Pottery.ItemPotterySmallVessel;
import com.dunk.tfc.api.TFCBlocks;
import com.dunk.tfc.api.TFCItems;
import com.snark.saturalanx.core.ItemSetup;
import com.snark.saturalanx.items.weapons.gunpowder.PotGrenadeItem;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class BombRecipe implements IRecipe {
    private ItemStack output;
    private ItemStack fuse;
    private List<Item> sheets;
    public BombRecipe(ItemStack b, ItemStack f){
        this.output = b.copy();
        this.fuse = f.copy();

        sheets = new ArrayList<Item>();
        sheets.add(TFCItems.linenCloth);
        sheets.add(TFCItems.cottonCloth);
        sheets.add(TFCItems.woolCloth);
        sheets.add(TFCItems.silkCloth);
        sheets.add(TFCItems.burlapCloth);
    }

    @Override
    public boolean matches(InventoryCrafting inv, World world) {
        if(inv != null){
            boolean vessel = false;
            boolean rope = false;
            boolean sheet = false;
            boolean match = false;
            Item i;
            for (int j = 0; j < inv.getSizeInventory(); j++) {
                if (inv.getStackInSlot(j) != null) {
                    i = inv.getStackInSlot(j).getItem();
                    if (i == TFCItems.potterySmallVessel)
                        vessel = true;
                    else if (i == TFCItems.rope)
                        rope = true;
                    else if (i == fuse.getItem() && inv.getStackInSlot(j).getItemDamage() == fuse.getItemDamage())
                        match = true;
                    else if (sheets.contains(i))
                        sheet = true;
                }
            }
            return vessel && rope && match && sheet;
        }
        return false;
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting inv) {
        if(inv != null){
            ItemStack stack = this.output.copy();

            ItemStack vessel = null;

            for (int i = 0; i < inv.getSizeInventory(); i++) {
                if (inv.getStackInSlot(i) != null) {
                    if (inv.getStackInSlot(i).getItem() == TFCItems.potterySmallVessel)
                        vessel = inv.getStackInSlot(i);
                }
            }
            if (vessel != null) {
                float[] comp = getComponents(vessel);
                ((PotGrenadeItem) stack.getItem()).setComponents(stack, comp[0], comp[1], comp[2], comp[3],false);
            }

            return stack;
        }
        return null;
    }

    @Override
    public int getRecipeSize() {
        return 4;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return output;
    }

    private float[] getComponents(ItemStack stack) {
        float[] f = new float[]{0, 0, 0, 0};

        ItemPotterySmallVessel container = (ItemPotterySmallVessel) stack.getItem();
        ItemStack[] inv = container.loadBagInventory(stack);
        if (inv != null) {
            for (ItemStack i : inv) {
                if (i != null) {
                    if (i.getItem() == Items.gunpowder)
                        f[0] += i.stackSize;
                    if (i.getItem() == Item.getItemFromBlock(TFCBlocks.gravel) || i.getItem() == Item.getItemFromBlock(TFCBlocks.gravel2))
                        f[1] += (i.stackSize * 10);
                    if (i.getItem() == ItemSetup.shot)
                        f[2] += (i.stackSize*10);
                    if (i.getItem() == TFCItems.resin || i.getItem() == TFCItems.coal)
                        f[3] += i.stackSize;
                    if (i.getItem() == TFCItems.powder && i.getItemDamage() == 3)
                        f[3] += i.stackSize;
                }
            }
        }

        return f;
    }
}
