package com.snark.saturalanx.recipes;

import com.dunk.tfc.api.Crafting.AnvilManager;
import com.dunk.tfc.api.Crafting.AnvilRecipe;
import com.dunk.tfc.api.Crafting.AnvilReq;
import com.dunk.tfc.api.Crafting.PlanRecipe;
import com.dunk.tfc.api.Enums.RuleEnum;
import com.dunk.tfc.api.TFCItems;
import com.snark.saturalanx.SaturaLanx;
import com.snark.saturalanx.core.BlockSetup;
import com.snark.saturalanx.core.Config;
import com.snark.saturalanx.core.ItemSetup;
import net.minecraft.item.ItemStack;

public class AnvilRecipes {

    public AnvilRecipes(){

    }

    public static void registerRecipes(){

        SaturaLanx.log.info("Registering anvil recipes...");

        AnvilManager manager = AnvilManager.getInstance();


        manager.addPlan("ballScrew", new PlanRecipe(new RuleEnum[]{RuleEnum.DRAWTHIRDFROMLAST, RuleEnum.DRAWSECONDFROMLAST, RuleEnum.BENDLAST}));

        manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.blackBronzeIngot),null,"ballScrew",false,AnvilReq.BRONZE,new ItemStack(ItemSetup.gunComponent,1,0)).addRecipeSkill("skill.gensmith"));
        manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.bismuthBronzeIngot),null,"ballScrew",false,AnvilReq.BRONZE,new ItemStack(ItemSetup.gunComponent,1,0)).addRecipeSkill("skill.gensmith"));
        manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.bronzeIngot),null,"ballScrew",false,AnvilReq.BRONZE,new ItemStack(ItemSetup.gunComponent,1,0)).addRecipeSkill("skill.gensmith"));
        manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.wroughtIronIngot),null,"ballScrew",false,AnvilReq.WROUGHTIRON,new ItemStack(ItemSetup.gunComponent,1,0)).addRecipeSkill("skill.gensmith"));
        manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.steelIngot),null,"ballScrew",false,AnvilReq.WROUGHTIRON,new ItemStack(ItemSetup.gunComponent,1,0)).addRecipeSkill("skill.gensmith"));

        manager.addPlan("matchlockMechanism", new PlanRecipe(new RuleEnum[]{RuleEnum.BENDANY, RuleEnum.BENDANY, RuleEnum.DRAWANY}));

        manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.blackBronzeIngot),null,"matchlockMechanism",false,AnvilReq.BRONZE,new ItemStack(ItemSetup.gunComponent,1,1)).addRecipeSkill("skill.gensmith"));
        manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.bismuthBronzeIngot),null,"matchlockMechanism",false,AnvilReq.BRONZE,new ItemStack(ItemSetup.gunComponent,1,1)).addRecipeSkill("skill.gensmith"));
        manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.bronzeIngot),null,"matchlockMechanism",false,AnvilReq.BRONZE,new ItemStack(ItemSetup.gunComponent,1,1)).addRecipeSkill("skill.gensmith"));
        manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.wroughtIronIngot),null,"matchlockMechanism",false,AnvilReq.WROUGHTIRON,new ItemStack(ItemSetup.gunComponent,1,1)).addRecipeSkill("skill.gensmith"));
        manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.steelIngot),null,"matchlockMechanism",false,AnvilReq.WROUGHTIRON,new ItemStack(ItemSetup.gunComponent,1,1)).addRecipeSkill("skill.gensmith"));

        manager.addPlan("gunBarrel", new PlanRecipe(new RuleEnum[]{RuleEnum.BENDTHIRDFROMLAST, RuleEnum.HITSECONDFROMLAST, RuleEnum.BENDLAST}));

        manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.bronzeSheet),null,"gunBarrel",false,AnvilReq.BRONZE,new ItemStack(ItemSetup.unfinishedBarrel,1,0)).addRecipeSkill("skill.weaponsmith"));
        manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.bismuthBronzeSheet),null,"gunBarrel",false,AnvilReq.BRONZE,new ItemStack(ItemSetup.unfinishedBarrel,1,1)).addRecipeSkill("skill.weaponsmith"));
        manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.blackBronzeSheet),null,"gunBarrel",false,AnvilReq.BRONZE,new ItemStack(ItemSetup.unfinishedBarrel,1,2)).addRecipeSkill("skill.weaponsmith"));
        manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.brassSheet),null,"gunBarrel",false,AnvilReq.BRONZE,new ItemStack(ItemSetup.unfinishedBarrel,1,3)).addRecipeSkill("skill.weaponsmith"));
        manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.wroughtIronSheet),null,"gunBarrel",false,AnvilReq.WROUGHTIRON,new ItemStack(ItemSetup.unfinishedBarrel,1,5)).addRecipeSkill("skill.weaponsmith"));
        manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.steelSheet),null,"gunBarrel",false,AnvilReq.STEEL,new ItemStack(ItemSetup.unfinishedBarrel,1,7)).addRecipeSkill("skill.weaponsmith"));

        manager.addRecipe(new AnvilRecipe(new ItemStack(ItemSetup.unfinishedBarrel,1,0),null,"gunBarrel",false,AnvilReq.BRONZE,new ItemStack(ItemSetup.bronzeBarrel)).addRecipeSkill("skill.weaponsmith"));
        manager.addRecipe(new AnvilRecipe(new ItemStack(ItemSetup.unfinishedBarrel,1,1),null,"gunBarrel",false,AnvilReq.BRONZE,new ItemStack(ItemSetup.bismuthBronzeBarrel)).addRecipeSkill("skill.weaponsmith"));
        manager.addRecipe(new AnvilRecipe(new ItemStack(ItemSetup.unfinishedBarrel,1,2),null,"gunBarrel",false,AnvilReq.BRONZE,new ItemStack(ItemSetup.blackBronzeBarrel)).addRecipeSkill("skill.weaponsmith"));
        manager.addRecipe(new AnvilRecipe(new ItemStack(ItemSetup.unfinishedBarrel,1,3),null,"gunBarrel",false,AnvilReq.BRONZE,new ItemStack(ItemSetup.brassBarrel)).addRecipeSkill("skill.weaponsmith"));
        manager.addRecipe(new AnvilRecipe(new ItemStack(ItemSetup.unfinishedBarrel,1,5),null,"gunBarrel",false,AnvilReq.WROUGHTIRON,new ItemStack(ItemSetup.ironBarrel)).addRecipeSkill("skill.weaponsmith"));
        manager.addRecipe(new AnvilRecipe(new ItemStack(ItemSetup.unfinishedBarrel,1,7),null,"gunBarrel",false,AnvilReq.STEEL,new ItemStack(ItemSetup.steelBarrel)).addRecipeSkill("skill.weaponsmith"));

        manager.addWeldRecipe(new AnvilRecipe(new ItemStack(ItemSetup.unfinishedBarrel,1,3),new ItemStack(TFCItems.brassSheet),AnvilReq.BRONZE,new ItemStack(ItemSetup.unfinishedBarrel,1,4)).addRecipeSkill("skill.weaponsmith"));
        manager.addWeldRecipe(new AnvilRecipe(new ItemStack(ItemSetup.unfinishedBarrel,1,5),new ItemStack(TFCItems.wroughtIronSheet),AnvilReq.WROUGHTIRON,new ItemStack(ItemSetup.unfinishedBarrel,1,6)).addRecipeSkill("skill.weaponsmith"));
        manager.addWeldRecipe(new AnvilRecipe(new ItemStack(ItemSetup.unfinishedBarrel,1,7),new ItemStack(TFCItems.brassSheet),true,AnvilReq.STEEL,new ItemStack(ItemSetup.unfinishedBarrel,1,8)).addRecipeSkill("skill.weaponsmith"));

        manager.addRecipe(new AnvilRecipe(new ItemStack(ItemSetup.unfinishedBarrel,1,4),null,"gunBarrel",false,AnvilReq.BRONZE,new ItemStack(ItemSetup.brassLongBarrel)).addRecipeSkill("skill.weaponsmith"));
        manager.addRecipe(new AnvilRecipe(new ItemStack(ItemSetup.unfinishedBarrel,1,6),null,"gunBarrel",false,AnvilReq.WROUGHTIRON,new ItemStack(ItemSetup.ironLongBarrel)).addRecipeSkill("skill.weaponsmith"));
        manager.addRecipe(new AnvilRecipe(new ItemStack(ItemSetup.unfinishedBarrel,1,8),null,"gunBarrel",false,AnvilReq.STEEL,new ItemStack(ItemSetup.steelLongBarrel)).addRecipeSkill("skill.weaponsmith"));

        manager.addPlan("flaredBarrel", new PlanRecipe(new RuleEnum[]{RuleEnum.BENDTHIRDFROMLAST,RuleEnum.HITSECONDFROMLAST,RuleEnum.UPSETLAST}));

        manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.bronzeSheet),null,"flaredBarrel",false,AnvilReq.BRONZE,new ItemStack(ItemSetup.unfinishedFlaredBarrel,1,0)).addRecipeSkill("skill.weaponsmith"));
        manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.bismuthBronzeSheet),null,"flaredBarrel",false,AnvilReq.BRONZE,new ItemStack(ItemSetup.unfinishedFlaredBarrel,1,1)).addRecipeSkill("skill.weaponsmith"));
        manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.blackBronzeSheet),null,"flaredBarrel",false,AnvilReq.BRONZE,new ItemStack(ItemSetup.unfinishedFlaredBarrel,1,2)).addRecipeSkill("skill.weaponsmith"));
        manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.brassSheet),null,"flaredBarrel",false,AnvilReq.BRONZE,new ItemStack(ItemSetup.unfinishedFlaredBarrel,1,3)).addRecipeSkill("skill.weaponsmith"));
        manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.wroughtIronSheet),null,"flaredBarrel",false,AnvilReq.WROUGHTIRON,new ItemStack(ItemSetup.unfinishedFlaredBarrel,1,5)).addRecipeSkill("skill.weaponsmith"));
        manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.steelSheet),null,"flaredBarrel",false,AnvilReq.STEEL,new ItemStack(ItemSetup.unfinishedFlaredBarrel,1,7)).addRecipeSkill("skill.weaponsmith"));

        manager.addRecipe(new AnvilRecipe(new ItemStack(ItemSetup.unfinishedFlaredBarrel,1,0),null,"flaredBarrel",false,AnvilReq.BRONZE,new ItemStack(ItemSetup.bronzeFlaredBarrel)).addRecipeSkill("skill.weaponsmith"));
        manager.addRecipe(new AnvilRecipe(new ItemStack(ItemSetup.unfinishedFlaredBarrel,1,1),null,"flaredBarrel",false,AnvilReq.BRONZE,new ItemStack(ItemSetup.bismuthBronzeFlaredBarrel)).addRecipeSkill("skill.weaponsmith"));
        manager.addRecipe(new AnvilRecipe(new ItemStack(ItemSetup.unfinishedFlaredBarrel,1,2),null,"flaredBarrel",false,AnvilReq.BRONZE,new ItemStack(ItemSetup.blackBronzeFlaredBarrel)).addRecipeSkill("skill.weaponsmith"));
        manager.addRecipe(new AnvilRecipe(new ItemStack(ItemSetup.unfinishedFlaredBarrel,1,3),null,"flaredBarrel",false,AnvilReq.BRONZE,new ItemStack(ItemSetup.brassFlaredBarrel)).addRecipeSkill("skill.weaponsmith"));
        manager.addRecipe(new AnvilRecipe(new ItemStack(ItemSetup.unfinishedFlaredBarrel,1,5),null,"flaredBarrel",false,AnvilReq.WROUGHTIRON,new ItemStack(ItemSetup.ironFlaredBarrel)).addRecipeSkill("skill.weaponsmith"));
        manager.addRecipe(new AnvilRecipe(new ItemStack(ItemSetup.unfinishedFlaredBarrel,1,7),null,"flaredBarrel",false,AnvilReq.STEEL,new ItemStack(ItemSetup.steelFlaredBarrel)).addRecipeSkill("skill.weaponsmith"));

        manager.addWeldRecipe(new AnvilRecipe(new ItemStack(ItemSetup.unfinishedFlaredBarrel,1,3),new ItemStack(TFCItems.brassSheet),AnvilReq.BRONZE,new ItemStack(ItemSetup.unfinishedFlaredBarrel,1,4)).addRecipeSkill("skill.weaponsmith"));
        manager.addWeldRecipe(new AnvilRecipe(new ItemStack(ItemSetup.unfinishedFlaredBarrel,1,5),new ItemStack(TFCItems.wroughtIronSheet),AnvilReq.WROUGHTIRON,new ItemStack(ItemSetup.unfinishedFlaredBarrel,1,6)).addRecipeSkill("skill.weaponsmith"));
        manager.addWeldRecipe(new AnvilRecipe(new ItemStack(ItemSetup.unfinishedFlaredBarrel,1,7),new ItemStack(TFCItems.brassSheet),true,AnvilReq.STEEL,new ItemStack(ItemSetup.unfinishedFlaredBarrel,1,8)).addRecipeSkill("skill.weaponsmith"));

        manager.addRecipe(new AnvilRecipe(new ItemStack(ItemSetup.unfinishedFlaredBarrel,1,4),null,"flaredBarrel",false,AnvilReq.BRONZE,new ItemStack(ItemSetup.brassLongFlaredBarrel)).addRecipeSkill("skill.weaponsmith"));
        manager.addRecipe(new AnvilRecipe(new ItemStack(ItemSetup.unfinishedFlaredBarrel,1,6),null,"flaredBarrel",false,AnvilReq.WROUGHTIRON,new ItemStack(ItemSetup.ironLongFlaredBarrel)).addRecipeSkill("skill.weaponsmith"));
        manager.addRecipe(new AnvilRecipe(new ItemStack(ItemSetup.unfinishedFlaredBarrel,1,8),null,"flaredBarrel",false,AnvilReq.STEEL,new ItemStack(ItemSetup.steelLongFlaredBarrel)).addRecipeSkill("skill.weaponsmith"));


        if(Config.enableExtraMaces){

            manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.leadIngot2x),(ItemStack) null,"mace",false, AnvilReq.COPPER, new ItemStack(ItemSetup.leadMaceHead,1)).addRecipeSkill("skill.weaponsmith"));
            manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.brassIngot2x),(ItemStack) null,"mace",false, AnvilReq.BRONZE, new ItemStack(ItemSetup.brassMaceHead,1)).addRecipeSkill("skill.weaponsmith"));

        }

        if(Config.enableSpikes){

            manager.addPlan("spike", new PlanRecipe(new RuleEnum[]{RuleEnum.DRAWLAST, RuleEnum.HITNOTLAST, RuleEnum.DRAWNOTLAST}));

            manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.wroughtIronIngot),(ItemStack) null,"spike",false,AnvilReq.WROUGHTIRON,new ItemStack(ItemSetup.spikeItem,1,1)).addRecipeSkill("skill.gensmith"));

        }
        if(Config.enableTallCandlestick){
            manager.addPlan("tallCandleholder",new PlanRecipe(new RuleEnum[]{RuleEnum.DRAWTHIRDFROMLAST,RuleEnum.DRAWSECONDFROMLAST,RuleEnum.BENDLAST}));

            manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.brassIngot2x),null,"tallCandleholder",false,AnvilReq.COPPER,new ItemStack(ItemSetup.tallCandleholder,1,0)).addRecipeSkill("skill.gensmith"));
            manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.pewterIngot2x),null,"tallCandleholder",false,AnvilReq.COPPER,new ItemStack(ItemSetup.tallCandleholder,1,1)).addRecipeSkill("skill.gensmith"));
            manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.silverIngot2x),null,"tallCandleholder",false,AnvilReq.COPPER,new ItemStack(ItemSetup.tallCandleholder,1,2)).addRecipeSkill("skill.gensmith"));
            manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.sterlingSilverIngot2x),null,"tallCandleholder",false,AnvilReq.COPPER,new ItemStack(ItemSetup.tallCandleholder,1,3)).addRecipeSkill("skill.gensmith"));
            manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.goldIngot2x),null,"tallCandleholder",false,AnvilReq.COPPER,new ItemStack(ItemSetup.tallCandleholder,1,4)).addRecipeSkill("skill.gensmith"));
            manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.roseGoldIngot2x),null,"tallCandleholder",false,AnvilReq.COPPER,new ItemStack(ItemSetup.tallCandleholder,1,5)).addRecipeSkill("skill.gensmith"));

        }
        if(Config.enableChalices){
            manager.addPlan("chalice",new PlanRecipe(new RuleEnum[]{RuleEnum.DRAWTHIRDFROMLAST,RuleEnum.BENDSECONDFROMLAST,RuleEnum.UPSETLAST}));

            manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.copperIngot),null,"chalice",false,AnvilReq.COPPER, new ItemStack(BlockSetup.chaliceBlock,1,0)).addRecipeSkill("skill.gensmith"));
            manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.bronzeIngot),null,"chalice",false,AnvilReq.COPPER, new ItemStack(BlockSetup.chaliceBlock,1,1)).addRecipeSkill("skill.gensmith"));
            manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.bismuthBronzeIngot),null,"chalice",false,AnvilReq.COPPER, new ItemStack(BlockSetup.chaliceBlock,1,2)).addRecipeSkill("skill.gensmith"));
            manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.blackBronzeIngot),null,"chalice",false,AnvilReq.COPPER, new ItemStack(BlockSetup.chaliceBlock,1,3)).addRecipeSkill("skill.gensmith"));
            manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.brassIngot),null,"chalice",false,AnvilReq.COPPER, new ItemStack(BlockSetup.chaliceBlock,1,4)).addRecipeSkill("skill.gensmith"));
            manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.pewterIngot),null,"chalice",false,AnvilReq.COPPER, new ItemStack(BlockSetup.chaliceBlock,1,5)).addRecipeSkill("skill.gensmith"));
            manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.silverIngot),null,"chalice",false,AnvilReq.COPPER, new ItemStack(BlockSetup.chaliceBlock,1,6)).addRecipeSkill("skill.gensmith"));
            manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.sterlingSilverIngot),null,"chalice",false,AnvilReq.COPPER, new ItemStack(BlockSetup.chaliceBlock,1,7)).addRecipeSkill("skill.gensmith"));
            manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.goldIngot),null,"chalice",false,AnvilReq.COPPER, new ItemStack(BlockSetup.chaliceBlock2,1,0)).addRecipeSkill("skill.gensmith"));
            manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.roseGoldIngot),null,"chalice",false,AnvilReq.COPPER, new ItemStack(BlockSetup.chaliceBlock2,1,1)).addRecipeSkill("skill.gensmith"));
            manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.platinumIngot),null,"chalice",false,AnvilReq.STEEL, new ItemStack(BlockSetup.chaliceBlock2,1,2)).addRecipeSkill("skill.gensmith"));
            manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.blackSteelIngot),null,"chalice",false,AnvilReq.STEEL, new ItemStack(BlockSetup.chaliceBlock2,1,3)).addRecipeSkill("skill.gensmith"));
            manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.redSteelIngot),null,"chalice",false,AnvilReq.STEEL, new ItemStack(BlockSetup.chaliceBlock2,1,4)).addRecipeSkill("skill.gensmith"));
            manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.blueSteelIngot),null,"chalice",false,AnvilReq.STEEL, new ItemStack(BlockSetup.chaliceBlock2,1,5)).addRecipeSkill("skill.gensmith"));

        }
    }
}
