package com.snark.saturalanx.core;

import com.dunk.tfc.api.Constant.Global;
import com.dunk.tfc.api.Enums.EnumDamageType;
import com.dunk.tfc.api.Metal;
import com.snark.saturalanx.SaturaLanx;
import com.snark.saturalanx.items.*;
import com.snark.saturalanx.items.instruments.InstrumentSimpleSL;
import com.snark.saturalanx.items.pottery.PotteryMoldSL;
import com.snark.saturalanx.items.pottery.PotterySL;
import com.snark.saturalanx.items.pottery.TileItem;
import com.snark.saturalanx.items.tools.Firecracker;
import com.snark.saturalanx.items.tools.ToolHeadSL;
import com.snark.saturalanx.items.weapons.*;
import com.snark.saturalanx.items.weapons.gunpowder.*;
import com.snark.saturalanx.items.weapons.incendiary.FlameArrowItem;
import com.snark.saturalanx.items.weapons.incendiary.IncendiaryPotItem;
import com.snark.saturalanx.items.weapons.ranged.BolasItem;
import com.snark.saturalanx.items.weapons.ranged.ItemJavelinSL;
import com.snark.saturalanx.items.weapons.ranged.SpearThrowerItem;
import com.snark.saturalanx.renders.item.*;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.util.EnumHelper;

import java.util.ArrayList;

import static com.dunk.tfc.api.TFCItems.*;
import static com.snark.saturalanx.core.FoodSetup.FOODPATH;
import static com.snark.saturalanx.SaturaLanx.MODID;
import static com.snark.saturalanx.SaturaLanx.tab;

public class ItemSetup {

    public static Item castanets;
    public static Item pitchPot, wick, incendiaryPot;
    public static Item crudeFlameArrow, flameArrow, unfinishedFireArrow, fireArrow;
    public static Item slowmatch,match,potGrenade,potGrenadeQuick,potGrenadeLong;
    public static Item firecracker;
    public static Item bolas, primitiveSpearThrower, spearThrower;
    public static Item ropeArrow;
    public static Item saturaMaceMold, leadMaceHead, brassMaceHead;
    public static Item stoneClub, leadMace, brassMace;
    public static Item mechanism, flaredBarrel, shot, dummyBullet;
    public static Item brassHandgonne, bronzeHandgonne, bismuthBronzeHandgonne, blackBronzeHandgonne, ironHandgonne;
    public static Item brassArquebus, ironArquebus, steelArquebus;
    public static Item brassMBlunderbuss, ironMBlunderbuss, steelMBlunderbuss;
    public static Item seedsWhiteGrape;
    public static ArrayList<Item> seedList, itemList;
    public static Item lapisBrick, hematiteBrick, limoniteBrick, malachiteBrick;
    public static Item floorTile,floorTileLapis,floorTileHematite,floorTileLimonite,floorTileMalachite;
    public static Item tallCandleholder;
    public static Item spikeItem;
    public static String WEAPONPATH = MODID+":weapons/";

    public ItemSetup(){

    }

    public static void initItems() {

        SaturaLanx.log.info("Initializing items...");

        itemList = new ArrayList<Item>();

        dummyBullet = new ItemSL(new String[]{"GunBulletStone", "GunBulletLead", "GunPelletStone", "GunPelletLead", "GunPelletSalt"}).setFolder("weapons/guns/bullet").setUnlocalizedName("DummyBullet").setCreativeTab(null);
        itemList.add(dummyBullet);

        if (Config.enableColouredBricks) {
            lapisBrick = (new PotterySL()).setMetaNames(new String[]{"LapisClayBrick", "LapisBrick"}).setUnlocalizedName("LapisBrick");
            hematiteBrick = (new PotterySL()).setMetaNames(new String[]{"HematiteClayBrick", "HematiteBrick"}).setUnlocalizedName("HematiteBrick");
            limoniteBrick = (new PotterySL()).setMetaNames(new String[]{"LimoniteClayBrick", "LimoniteBrick"}).setUnlocalizedName("LimoniteBrick");
            malachiteBrick = (new PotterySL()).setMetaNames(new String[]{"MalachiteClayBrick", "MalachiteBrick"}).setUnlocalizedName("MalachiteBrick");

            itemList.add(lapisBrick);
            itemList.add(hematiteBrick);
            itemList.add(limoniteBrick);
            itemList.add(malachiteBrick);
        }
        if (Config.enableFloorTiles) {
            floorTile = (new TileItem(0)).setMetaNames(new String[]{"ClayFloorTile", "CeramicFloorTile"}).setUnlocalizedName("FloorTile");
            floorTileLapis = (new TileItem(1)).setMetaNames(new String[]{"ClayFloorTileLapis", "CeramicFloorTileLapis"}).setUnlocalizedName("FloorTileLapis");
            floorTileHematite = (new TileItem(2)).setMetaNames(new String[]{"ClayFloorTileHematite", "CeramicFloorTileHematite"}).setUnlocalizedName("FloorTileHematite");
            floorTileLimonite = (new TileItem(3)).setMetaNames(new String[]{"ClayFloorTileLimonite", "CeramicFloorTileLimonite"}).setUnlocalizedName("FloorTileLimonite");
            floorTileMalachite = (new TileItem(4)).setMetaNames(new String[]{"ClayFloorTileMalachite", "CeramicFloorTileMalachite"}).setUnlocalizedName("FloorTileMalachite");

            itemList.add(floorTile);
            itemList.add(floorTileLapis);
            itemList.add(floorTileHematite);
            itemList.add(floorTileLimonite);
            itemList.add(floorTileMalachite);
        }
        if(Config.enableTallCandlestick){
            tallCandleholder = new ItemSL(new String[]{"BrassTallCandleholder","PewterTallCandleholder","SilverTallCandleholder","SterlingSilverTallCandleholder","GoldTallCandleholder","RoseGoldTallCandleholder"}).setUnlocalizedName("TallCandleholder");

            itemList.add(tallCandleholder);
        }
        if (Config.enableHandgonne) {
            brassHandgonne = new HandgonneItem(bronzeToolMaterial, copperToolMaterial.getDamageVsEntity()).setUnlocalizedName("BrassHandgonne");
            bronzeHandgonne = new HandgonneItem(bronzeToolMaterial, copperToolMaterial.getDamageVsEntity()).setUnlocalizedName("BronzeHandgonne").setTextureName(WEAPONPATH + "guns/BronzeHandgonne");
            bismuthBronzeHandgonne = new HandgonneItem(bismuthBronzeToolMaterial, copperToolMaterial.getDamageVsEntity()).setUnlocalizedName("BismuthBronzeHandgonne").setTextureName(WEAPONPATH + "guns/BismuthBronzeHandgonne");
            blackBronzeHandgonne = new HandgonneItem(blackBronzeToolMaterial, copperToolMaterial.getDamageVsEntity()).setUnlocalizedName("BlackBronzeHandgonne").setTextureName(WEAPONPATH + "guns/BlackBronzeHandgonne");
            ironHandgonne = new HandgonneItem(ironToolMaterial, bronzeToolMaterial.getDamageVsEntity()).setUnlocalizedName("IronHandgonne").setTextureName(WEAPONPATH + "guns/WroughtIronHandgonne");

            itemList.add(brassHandgonne);
            itemList.add(bronzeHandgonne);
            itemList.add(bismuthBronzeHandgonne);
            itemList.add(blackBronzeHandgonne);
            itemList.add(ironHandgonne);
        }
        if (Config.enableExtraMaces) {
            saturaMaceMold = (new PotteryMoldSL(true)).setCounter(4).setBaseDamage(2).setMaxUnits(100).setMetals(new Metal[]{Global.LEAD, Global.BRASS}).setMetaNames(new String[]{"ClayMaceMold", "EmptyMaceMold", "LeadMaceMold", "BrassMaceMold"}).setUnlocalizedName("SaturaMaceMold");

            stoneClub = (new SwordItemSL(igInToolMaterial, igInToolMaterial.getDamageVsEntity(), EnumDamageType.CRUSHING)).setCrushDamageShape("3X5_MACE").setAttackSpeed(19).setUnlocalizedName("StoneClub").setCreativeTab(tab).setTextureName(WEAPONPATH + "StoneClub").setMaxDamage(igInStoneUses);
            leadMaceHead = (new ToolHeadSL()).setMetal(Global.LEAD).setUnlocalizedName("LeadMaceHead").setTextureName(WEAPONPATH + "LeadMaceHead");
            ((ToolHeadSL) leadMaceHead).setPath(WEAPONPATH);
            leadMace = (new SwordItemSL(copperToolMaterial, copperToolMaterial.getDamageVsEntity() + 50, EnumDamageType.CRUSHING)).setCrushDamageShape("3X5_MACE").setAttackSpeed(23).setUnlocalizedName("LeadMace").setTextureName(WEAPONPATH + "LeadMace").setMaxDamage(copperUses / 2);
            brassMaceHead = (new ToolHeadSL()).setMetal(Global.BRASS).setUnlocalizedName("BrassMaceHead").setTextureName(WEAPONPATH + "BrassMaceHead");
            ((ToolHeadSL) brassMaceHead).setPath(WEAPONPATH);
            brassMace = (new SwordItemSL(bronzeToolMaterial, bronzeToolMaterial.getDamageVsEntity() - 30, EnumDamageType.CRUSHING)).setCrushDamageShape("3X5_MACE").setAttackSpeed(19).setUnlocalizedName("BrassMace").setTextureName(WEAPONPATH + "BrassMace").setMaxDamage(bronzeUses - 50);

            itemList.add(saturaMaceMold);
            itemList.add(stoneClub);
            itemList.add(leadMaceHead);
            itemList.add(leadMace);
            itemList.add(brassMaceHead);
            itemList.add(brassMace);
        }
        if (Config.enableBolas) {
            bolas = (new BolasItem()).setUnlocalizedName("Bolas").setTextureName(WEAPONPATH + "Bolas").setMaxDamage(1).setMaxStackSize(1);

            itemList.add(bolas);
        }
        if(Config.enableSpearThrower){
            primitiveSpearThrower = new SpearThrowerItem(100, Config.primitiveSpearThrowerForceMultiplier).setUnlocalizedName("PrimitiveSpearThrower");
            spearThrower = new SpearThrowerItem(250,Config.spearThrowerForceMultiplier).setUnlocalizedName("SpearThrower");

            itemList.add(primitiveSpearThrower);
            itemList.add(spearThrower);
        }
        if(Config.enableRopeArrows){
            ropeArrow = new ItemSL().setFolder("weapons/projectiles").setUnlocalizedName("RopeArrow").setMaxStackSize(16);

            itemList.add(ropeArrow);
        }
        if (Config.enableIncendiaryPot) {
            pitchPot = (new ItemSL().setUnlocalizedName("CeramicPotPitch"));
            wick = (new ItemSL()).setUnlocalizedName("Wick");
            incendiaryPot = (new IncendiaryPotItem()).setUnlocalizedName("IncendiaryPot").setCreativeTab(tab).setTextureName(WEAPONPATH + "IncendiaryPot");

            itemList.add(pitchPot);
            itemList.add(wick);
            itemList.add(incendiaryPot);
        }
        if (Config.enableFlameArrows){
            crudeFlameArrow = new FlameArrowItem(1,0,0).setUnlocalizedName("CrudeFlameArrow");
            flameArrow = new FlameArrowItem(2, -10,20).setUnlocalizedName("FlameArrow");
            unfinishedFireArrow = new ItemSL().setFolder("weapons/projectiles").setUnlocalizedName("UnfinishedFireArrow");
            fireArrow = new FlameArrowItem(3,-20,30).setUnlocalizedName("FireArrow");

            itemList.add(crudeFlameArrow);
            itemList.add(flameArrow);
            itemList.add(unfinishedFireArrow);
            itemList.add(fireArrow);
        }
        if (Config.enableSpikes) {
            spikeItem = new SpikeItem();

            itemList.add(spikeItem);
        }
        if (Config.enableFirecrackers || Config.enablePotGrenades || Config.enableSmokeBombs) {
            match = (new ItemSL(new String[]{"ScouredString", "Blackmatch", "Quickmatch", "Long Blackmatch"})).setUnlocalizedName("Match");

            itemList.add(match);
        }
        if (Config.enablePotGrenades) {
            potGrenade = (new PotGrenadeItem()).setUnlocalizedName("PotGrenade").setCreativeTab(tab).setMaxDamage(Config.potGrenadeFuseLength);
            ((PotGrenadeItem) potGrenade).setType(1);
            potGrenadeQuick = (new PotGrenadeItem()).setUnlocalizedName("PotGrenadeQuick").setCreativeTab(tab).setMaxDamage(Config.potGrenadeFuseLength / 2);
            ((PotGrenadeItem) potGrenadeQuick).setType(2);
            potGrenadeLong = (new PotGrenadeItem()).setUnlocalizedName("PotGrenadeLong").setCreativeTab(tab).setMaxDamage(Config.potGrenadeFuseLength + (Config.potGrenadeFuseLength / 2));
            ((PotGrenadeItem) potGrenadeLong).setType(3);

            itemList.add(potGrenade);
            itemList.add(potGrenadeQuick);
            itemList.add(potGrenadeLong);
        }
        if (Config.enableFirecrackers){
            firecracker = (new Firecracker(1)).setUnlocalizedName("Firecracker").setTextureName(MODID + ":tools/Firecracker");

        itemList.add(firecracker);
        }
        if(Config.enableCastanets) {
            castanets = (new InstrumentSimpleSL(1.3F, 1, 1, 1)).setUnlocalizedName("Castanets");
            ((InstrumentSimpleSL) castanets).setSounds(new String[]{MODID + ":castanet1", MODID + ":castanet2"});

            itemList.add(castanets);
        }

        if(Config.enableArquebus||Config.enableMBlunderbuss) {
            slowmatch = new SlowmatchItem().setUnlocalizedName("Slowmatch").setCreativeTab(tab);
            mechanism = new ItemSL(new String[]{"MatchlockMechanism"}).setFolder("weapons/guns").setUnlocalizedName("Mechanism");

            itemList.add(slowmatch);
            itemList.add(mechanism);
        }
        if(Config.enableArquebus){
            brassArquebus = new MatchlockArquebusItem(bronzeToolMaterial, copperToolMaterial.getDamageVsEntity()).setUnlocalizedName("BrassArquebus");
            ironArquebus = new MatchlockArquebusItem(ironToolMaterial, bronzeToolMaterial.getDamageVsEntity()).setUnlocalizedName("IronArquebus");
            steelArquebus = new MatchlockArquebusItem(steelToolMaterial, bronzeToolMaterial.getDamageVsEntity()).setUnlocalizedName("SteelArquebus");

            itemList.add(brassArquebus);
            itemList.add(ironArquebus);
            itemList.add(steelArquebus);
        }
        if(Config.enableMBlunderbuss){
            flaredBarrel = new ItemSL(new String[]{"BrassFlaredBarrel", "IronFlaredBarrel", "SteelFlaredBarrel"}).setFolder("weapons/guns").setUnlocalizedName("FlaredBarrel");
            shot = new ItemSL(new String[]{"StoneShot", "LeadShot"}).setFolder("weapons/guns").setUnlocalizedName("Shot");
            brassMBlunderbuss = new MatchlockBlunderbussItem(bronzeToolMaterial, copperToolMaterial.getDamageVsEntity()).setUnlocalizedName("BrassMBlunderbuss");
            ironMBlunderbuss = new MatchlockBlunderbussItem(ironToolMaterial, bronzeToolMaterial.getDamageVsEntity()).setUnlocalizedName("IronMBlunderbuss");
            steelMBlunderbuss = new MatchlockBlunderbussItem(steelToolMaterial, bronzeToolMaterial.getDamageVsEntity()).setUnlocalizedName("SteelMBlunderbuss");

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

        if(Config.enableBolas)
            MinecraftForgeClient.registerItemRenderer(bolas, new BolasItemRenderer());
        if(Config.enableSpearThrower){
            MinecraftForgeClient.registerItemRenderer(primitiveSpearThrower, new SpearThrowerRender());
            MinecraftForgeClient.registerItemRenderer(spearThrower, new SpearThrowerRender());
        }
        if(Config.enableHandgonne) {
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
