package com.snark.saturalanx.core;

import com.dunk.tfc.Items.ItemTerraMetal;
import com.dunk.tfc.Items.Pottery.ItemPotteryMoldBase;
import com.dunk.tfc.api.Constant.Global;
import com.dunk.tfc.api.Enums.EnumDamageType;
import com.dunk.tfc.api.Metal;
import com.snark.saturalanx.SaturaLanx;
import com.snark.saturalanx.items.*;
import com.snark.saturalanx.items.instruments.SaturaInstrumentSimple;
import com.snark.saturalanx.items.tools.Firecracker;
import com.snark.saturalanx.items.warfare.*;
import com.snark.saturalanx.items.warfare.gunpowder.*;
import com.snark.saturalanx.items.warfare.incendiary.IncendiaryPot;
import com.snark.saturalanx.renders.item.ArquebusRender;
import com.snark.saturalanx.renders.item.HandgonneRender;
import com.snark.saturalanx.renders.item.MatchlockBlunderbussRender;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;

import java.util.ArrayList;

import static com.dunk.tfc.api.TFCItems.*;
import static com.snark.saturalanx.core.FoodSetup.FOODPATH;
import static com.snark.saturalanx.SaturaLanx.MODID;
import static com.snark.saturalanx.SaturaLanx.tab;

public class ItemSetup {

    public static Item castanets;
    public static Item pitchPot, wick, incendiaryPot;
    public static Item slowmatch,match,potGrenade,potGrenadeQuick,potGrenadeLong;
    public static Item firecracker;
    public static Item bolas;
    public static Item saturaMaceMold, leadMaceHead, brassMaceHead;
    public static Item stoneClub, leadMace, brassMace;
    public static Item mechanism, flaredBarrel, shot, dummyBullet;
    public static Item brassHandgonne, bronzeHandgonne, bismuthBronzeHandgonne, blackBronzeHandgonne, ironHandgonne;
    public static Item brassArquebus, ironArquebus, steelArquebus;
    public static Item brassMBlunderbuss, ironMBlunderbuss, steelMBlunderbuss;
    public static Item seedsWhiteGrape;
    public static ArrayList<Item> seedList, itemList;
    public static Item lapisBrick, hematiteBrick, limoniteBrick, malachiteBrick;
    public static Item spikeItem;
    public static String WEAPONPATH = MODID+":weapons/";

    public ItemSetup(){

    }

    public static void initItems(){

        SaturaLanx.log.info("Initializing items...");

        //Traps
        spikeItem = new SpikeItem();

        //Pottery
        lapisBrick = (new Pottery()).setMetaNames(new String[]{"LapisClayBrick", "LapisBrick"}).setUnlocalizedName("LapisBrick");
        hematiteBrick = (new Pottery()).setMetaNames(new String[]{"HematiteClayBrick", "HematiteBrick"}).setUnlocalizedName("HematiteBrick");
        limoniteBrick = (new Pottery()).setMetaNames(new String[]{"LimoniteClayBrick", "LimoniteBrick"}).setUnlocalizedName("LimoniteBrick");
        malachiteBrick = (new Pottery()).setMetaNames(new String[]{"MalachiteClayBrick", "MalachiteBrick"}).setUnlocalizedName("MalachiteBrick");

        saturaMaceMold = (new PotteryMold(true)).setCounter(4).setBaseDamage(2).setMaxUnits(100).setMetals(new Metal[]{Global.LEAD,Global.BRASS}).setMetaNames(new String[]{"ClayMaceMold","EmptyMaceMold","LeadMaceMold","BrassMaceMold"}).setUnlocalizedName("SaturaMaceMold");

        //Melee weapons
        stoneClub = (new SwordSatura(igInToolMaterial,igInToolMaterial.getDamageVsEntity(), EnumDamageType.CRUSHING)).setCrushDamageShape("3X5_MACE").setAttackSpeed(19).setUnlocalizedName("StoneClub").setCreativeTab(tab).setTextureName(WEAPONPATH+"StoneClub").setMaxDamage(igInStoneUses);

        leadMaceHead = (new ToolHead()).setMetal(Global.LEAD).setUnlocalizedName("LeadMaceHead").setTextureName(WEAPONPATH+"LeadMaceHead");
        ((ToolHead)leadMaceHead).setPath(WEAPONPATH);
        leadMace = (new SwordSatura(copperToolMaterial,copperToolMaterial.getDamageVsEntity()+50, EnumDamageType.CRUSHING)).setCrushDamageShape("3X5_MACE").setAttackSpeed(23).setUnlocalizedName("LeadMace").setTextureName(WEAPONPATH+"LeadMace").setMaxDamage(copperUses/2);
        brassMaceHead = (new ToolHead()).setMetal(Global.BRASS).setUnlocalizedName("BrassMaceHead").setTextureName(WEAPONPATH+"BrassMaceHead");
        ((ToolHead)brassMaceHead).setPath(WEAPONPATH);
        brassMace = (new SwordSatura(bronzeToolMaterial,bronzeToolMaterial.getDamageVsEntity()-30, EnumDamageType.CRUSHING)).setCrushDamageShape("3X5_MACE").setAttackSpeed(19).setUnlocalizedName("BrassMace").setTextureName(WEAPONPATH+"BrassMace").setMaxDamage(bronzeUses-50);

        //Ranged weapons
        bolas = (new Bolas()).setUnlocalizedName("Bolas").setTextureName(WEAPONPATH+"Bolas").setMaxDamage(1).setMaxStackSize(1);

        //Firearms
        slowmatch = new Slowmatch().setUnlocalizedName("Slowmatch").setCreativeTab(tab);
        mechanism = new ItemSatura(new String[]{"MatchlockMechanism"}).setFolder("weapons/guns").setUnlocalizedName("Mechanism");
        flaredBarrel = new ItemSatura(new String[]{"BrassFlaredBarrel","IronFlaredBarrel","SteelFlaredBarrel"}).setFolder("weapons/guns").setUnlocalizedName("FlaredBarrel");
        shot = new ItemSatura(new String[]{"StoneShot","LeadShot"}).setFolder("weapons/guns").setUnlocalizedName("Shot");
        dummyBullet = new ItemSatura(new String[]{"GunBulletStone","GunBulletLead","GunPelletStone","GunPelletLead","GunPelletSalt"}).setFolder("weapons/guns/bullet").setUnlocalizedName("DummyBullet").setCreativeTab(null);

        brassHandgonne = new Handgonne(bronzeToolMaterial,copperToolMaterial.getDamageVsEntity()).setUnlocalizedName("BrassHandgonne");
        bronzeHandgonne = new Handgonne(bronzeToolMaterial,copperToolMaterial.getDamageVsEntity()).setUnlocalizedName("BronzeHandgonne").setTextureName(WEAPONPATH+"guns/BronzeHandgonne");
        bismuthBronzeHandgonne = new Handgonne(bismuthBronzeToolMaterial,copperToolMaterial.getDamageVsEntity()).setUnlocalizedName("BismuthBronzeHandgonne").setTextureName(WEAPONPATH+"guns/BismuthBronzeHandgonne");
        blackBronzeHandgonne = new Handgonne(blackBronzeToolMaterial,copperToolMaterial.getDamageVsEntity()).setUnlocalizedName("BlackBronzeHandgonne").setTextureName(WEAPONPATH+"guns/BlackBronzeHandgonne");
        ironHandgonne = new Handgonne(ironToolMaterial,bronzeToolMaterial.getDamageVsEntity()).setUnlocalizedName("IronHandgonne").setTextureName(WEAPONPATH+"guns/WroughtIronHandgonne");

        brassArquebus = new Arquebus(bronzeToolMaterial,copperToolMaterial.getDamageVsEntity()).setUnlocalizedName("BrassArquebus");
        ironArquebus = new Arquebus(ironToolMaterial,bronzeToolMaterial.getDamageVsEntity()).setUnlocalizedName("IronArquebus");
        steelArquebus = new Arquebus(steelToolMaterial,bronzeToolMaterial.getDamageVsEntity()).setUnlocalizedName("SteelArquebus");

        brassMBlunderbuss = new MatchlockBlunderbuss(bronzeToolMaterial,copperToolMaterial.getDamageVsEntity()).setUnlocalizedName("BrassMBlunderbuss");
        ironMBlunderbuss = new MatchlockBlunderbuss(ironToolMaterial,bronzeToolMaterial.getDamageVsEntity()).setUnlocalizedName("IronMBlunderbuss");
        steelMBlunderbuss = new MatchlockBlunderbuss(steelToolMaterial,bronzeToolMaterial.getDamageVsEntity()).setUnlocalizedName("SteelMBlunderbuss");

        //Incendiaries
        pitchPot = (new ItemSatura().setUnlocalizedName("CeramicPotPitch").setCreativeTab(tab).setTextureName(MODID + ":" + "CeramicPotPitch"));
        wick = (new ItemSatura()).setUnlocalizedName("Wick").setCreativeTab(tab).setTextureName(MODID + ":" + "Wick");
        incendiaryPot = (new IncendiaryPot()).setUnlocalizedName("IncendiaryPot").setCreativeTab(tab).setTextureName(WEAPONPATH + "IncendiaryPot");

        //Explosives
        match = (new ItemSatura(new String[]{"ScouredString","Blackmatch","Quickmatch","Long Blackmatch"})).setUnlocalizedName("Match");
        potGrenade = (new PotGrenade()).setUnlocalizedName("PotGrenade").setCreativeTab(tab).setMaxDamage(Config.potGrenadeFuseLength);
        ((PotGrenade)potGrenade).setType(1);
        potGrenadeQuick = (new PotGrenade()).setUnlocalizedName("PotGrenadeQuick").setCreativeTab(tab).setMaxDamage(Config.potGrenadeFuseLength/2);
        ((PotGrenade)potGrenadeQuick).setType(2);
        potGrenadeLong = (new PotGrenade()).setUnlocalizedName("PotGrenadeLong").setCreativeTab(tab).setMaxDamage(Config.potGrenadeFuseLength+(Config.potGrenadeFuseLength/2));
        ((PotGrenade)potGrenadeLong).setType(3);
        firecracker = (new Firecracker(1)).setUnlocalizedName("Firecracker").setTextureName(MODID+":tools/Firecracker");

        //Music
        castanets =(new SaturaInstrumentSimple(1.3F,1,1,1)).setUnlocalizedName("Castanets");
        ((SaturaInstrumentSimple)castanets).setSounds(new String[]{MODID+":castanet1",MODID+":castanet2"});

        //Item list population
        itemList = new ArrayList<Item>();
        if(Config.enableColouredBricks){
            itemList.add(lapisBrick);
            itemList.add(hematiteBrick);
            itemList.add(limoniteBrick);
            itemList.add(malachiteBrick);
        }
        if(Config.enableHandgonne) {
            itemList.add(brassHandgonne);
            itemList.add(bronzeHandgonne);
            itemList.add(bismuthBronzeHandgonne);
            itemList.add(blackBronzeHandgonne);
            itemList.add(ironHandgonne);
        }
        if(Config.enableExtraMaces){
            itemList.add(saturaMaceMold);
            itemList.add(stoneClub);
            itemList.add(leadMaceHead);
            itemList.add(leadMace);
            itemList.add(brassMaceHead);
            itemList.add(brassMace);
        }
        if(Config.enableBolas){
            itemList.add(bolas);
        }
        if(Config.enableIncendiaryPot) {
            itemList.add(pitchPot);
            itemList.add(wick);
            itemList.add(incendiaryPot);
        }
        if(Config.enableSpikes){
            itemList.add(spikeItem);
        }
        if(Config.enableFirecrackers||Config.enablePotGrenades||Config.enableSmokeBombs) {
            itemList.add(match);
        }
        if(Config.enablePotGrenades) {
            itemList.add(potGrenade);
            itemList.add(potGrenadeQuick);
            itemList.add(potGrenadeLong);
        }
        if(Config.enableFirecrackers)
            itemList.add(firecracker);
        if(Config.enableCastanets)
            itemList.add(castanets);
        if(Config.enableHandgonne||Config.enableMBlunderbuss||Config.enableArquebus)
            itemList.add(dummyBullet);
        if(Config.enableArquebus||Config.enableMBlunderbuss) {
            itemList.add(slowmatch);
            itemList.add(mechanism);
        }
        if(Config.enableArquebus){
            itemList.add(brassArquebus);
            itemList.add(ironArquebus);
            itemList.add(steelArquebus);
        }
        if(Config.enableMBlunderbuss){
            itemList.add(brassMBlunderbuss);
            itemList.add(ironMBlunderbuss);
            itemList.add(steelMBlunderbuss);
            itemList.add(flaredBarrel);
            itemList.add(shot);
        }


        //Seeds
        seedsWhiteGrape = (new Item()).setUnlocalizedName("SeedsWhiteGrape").setTextureName(FOODPATH+"SeedsWhiteGrape").setCreativeTab(tab);

        seedList = new ArrayList<Item>();
        seedList.add(seedsWhiteGrape);

    }

    public static void setupItems(){



    }
    public static void registeritems(){

        SaturaLanx.log.info("Registering items...");

        for(Item i : itemList){
            GameRegistry.registerItem(i, i.getUnlocalizedName());
        }

        for(Item i: seedList){
            GameRegistry.registerItem(i, i.getUnlocalizedName());
        }

    }

    @SideOnly(Side.CLIENT)
    public static void registerRenders(){

        SaturaLanx.log.info("Registering item renders...");

        if(Config.enableHandgonne&&Config.enableHandgonneModel) {
            MinecraftForgeClient.registerItemRenderer(brassHandgonne, new HandgonneRender());
            MinecraftForgeClient.registerItemRenderer(bronzeHandgonne, new HandgonneRender());
            MinecraftForgeClient.registerItemRenderer(bismuthBronzeHandgonne, new HandgonneRender());
            MinecraftForgeClient.registerItemRenderer(blackBronzeHandgonne, new HandgonneRender());
            MinecraftForgeClient.registerItemRenderer(ironHandgonne, new HandgonneRender());
        }
        if(Config.enableArquebus){
            MinecraftForgeClient.registerItemRenderer(brassArquebus, new ArquebusRender());
            MinecraftForgeClient.registerItemRenderer(ironArquebus, new ArquebusRender());
            MinecraftForgeClient.registerItemRenderer(steelArquebus, new ArquebusRender());
        }
        if(Config.enableMBlunderbuss){
            MinecraftForgeClient.registerItemRenderer(brassMBlunderbuss, new MatchlockBlunderbussRender());
            MinecraftForgeClient.registerItemRenderer(ironMBlunderbuss, new MatchlockBlunderbussRender());
            MinecraftForgeClient.registerItemRenderer(steelMBlunderbuss, new MatchlockBlunderbussRender());
        }
    }
}
