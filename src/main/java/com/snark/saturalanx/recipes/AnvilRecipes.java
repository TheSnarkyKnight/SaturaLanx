package com.snark.saturalanx.recipes;

import com.dunk.tfc.api.Crafting.AnvilManager;
import com.dunk.tfc.api.Crafting.AnvilRecipe;
import com.dunk.tfc.api.Crafting.AnvilReq;
import com.dunk.tfc.api.Crafting.PlanRecipe;
import com.dunk.tfc.api.Enums.RuleEnum;
import com.dunk.tfc.api.TFCItems;
import com.snark.saturalanx.SaturaLanx;
import com.snark.saturalanx.core.Config;
import com.snark.saturalanx.core.ItemSetup;
import net.minecraft.item.ItemStack;

public class AnvilRecipes {

    public AnvilRecipes(){

    }

    public static void registerRecipes(){

        SaturaLanx.log.info("Registering anvil recipes...");

        AnvilManager manager = AnvilManager.getInstance();

        if(Config.enableExtraMaces){

            manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.leadIngot2x),(ItemStack) null,"mace",false, AnvilReq.COPPER, new ItemStack(ItemSetup.leadMaceHead,1)).addRecipeSkill("skill.weaponsmith"));
            manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.brassIngot2x),(ItemStack) null,"mace",false, AnvilReq.BRONZE, new ItemStack(ItemSetup.brassMaceHead,1)).addRecipeSkill("skill.weaponsmith"));

        }

        if(Config.enableSpikes){

            manager.addPlan("spike", new PlanRecipe(new RuleEnum[]{RuleEnum.DRAWLAST, RuleEnum.HITNOTLAST, RuleEnum.DRAWNOTLAST}));

            manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.wroughtIronIngot),(ItemStack) null,"spike",false,AnvilReq.WROUGHTIRON,new ItemStack(ItemSetup.spikeItem,1,1)).addRecipeSkill("skill.gensmith"));

        }
        if(Config.enableArquebus||Config.enableMBlunderbuss){
            manager.addPlan("matchlockMechanism", new PlanRecipe(new RuleEnum[]{RuleEnum.BENDANY, RuleEnum.BENDANY, RuleEnum.DRAWANY}));

            manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.wroughtIronIngot),null,"matchlockMechanism",false,AnvilReq.WROUGHTIRON,new ItemStack(ItemSetup.mechanism,1,0)).addRecipeSkill("skill.gensmith"));
            manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.steelIngot),null,"matchlockMechanism",false,AnvilReq.WROUGHTIRON,new ItemStack(ItemSetup.mechanism,1,0)).addRecipeSkill("skill.gensmith"));

        }
        if(Config.enableMBlunderbuss){
            manager.addPlan("flaredBarrel", new PlanRecipe(new RuleEnum[]{RuleEnum.UPSETTHIRDFROMLAST,RuleEnum.UPSETSECONDFROMLAST,RuleEnum.UPSETLAST}));

            manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.brassSheet2x),null,"flaredBarrel",false,AnvilReq.BRONZE,new ItemStack(ItemSetup.flaredBarrel,1,0)).addRecipeSkill("skill.weaponsmith"));
            manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.wroughtIronSheet2x),null,"flaredBarrel",false,AnvilReq.WROUGHTIRON,new ItemStack(ItemSetup.flaredBarrel,1,1)).addRecipeSkill("skill.weaponsmith"));
            manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.steelSheet2x),null,"flaredBarrel",false,AnvilReq.STEEL,new ItemStack(ItemSetup.flaredBarrel,1,2)).addRecipeSkill("skill.weaponsmith"));
        }
    }
}
