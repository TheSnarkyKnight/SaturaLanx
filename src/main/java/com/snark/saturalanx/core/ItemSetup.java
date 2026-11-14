package com.snark.saturalanx.core;

import com.dunk.tfc.api.Constant.Global;
import com.dunk.tfc.api.Enums.EnumDamageType;
import com.dunk.tfc.api.Metal;
import com.snark.saturalanx.API.ammo.AmmoData;
import com.snark.saturalanx.API.ammo.AmmoIdentifier;
import com.snark.saturalanx.API.ammo.AmmoRegistry;
import com.snark.saturalanx.SaturaLanx;
import com.snark.saturalanx.items.*;
import com.snark.saturalanx.items.instruments.InstrumentSimpleSL;
import com.snark.saturalanx.items.pottery.PotteryMoldSL;
import com.snark.saturalanx.items.pottery.PotterySL;
import com.snark.saturalanx.items.pottery.TileItem;
import com.snark.saturalanx.items.tools.*;
import com.snark.saturalanx.items.weapons.*;
import com.snark.saturalanx.items.weapons.gunpowder.*;
import com.snark.saturalanx.items.weapons.gunpowder.guns.*;
import com.snark.saturalanx.items.weapons.incendiary.FlameArrowItem;
import com.snark.saturalanx.items.weapons.incendiary.IncendiaryPotItem;
import com.snark.saturalanx.items.weapons.ranged.BolasItem;
import com.snark.saturalanx.items.weapons.ranged.SpearThrowerItem;
import com.snark.saturalanx.renders.item.*;
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
    public static Item crudeFlameArrow, flameArrow, unfinishedFireArrow, fireArrow;
    public static Item slowmatch,match,potGrenade,potGrenadeQuick,potGrenadeLong;
    public static Item firecracker;
    public static Item bolas, primitiveSpearThrower, spearThrower;
    public static Item ropeArrow;
    public static Item saturaMaceMold, leadMaceHead, brassMaceHead;
    public static Item stoneClub, leadMace, brassMace;
    public static Item gunComponent, unfinishedBarrel, unfinishedFlaredBarrel, powderFlask, ballPuller;
    public static Item bronzeBarrel, bismuthBronzeBarrel, blackBronzeBarrel, brassBarrel, ironBarrel, steelBarrel, brassLongBarrel, ironLongBarrel, steelLongBarrel;
    public static Item bronzeFlaredBarrel, bismuthBronzeFlaredBarrel, blackBronzeFlaredBarrel, brassFlaredBarrel, ironFlaredBarrel, steelFlaredBarrel, brassLongFlaredBarrel, ironLongFlaredBarrel, steelLongFlaredBarrel;
    public static Item shotSL, bulletSL, shotPelletSL, paperCartridge, paperShotCartridge, saturaBulletMold, saturaBulletMoldExtra;
    public static Item brassHandgonne, bronzeHandgonne, bismuthBronzeHandgonne, blackBronzeHandgonne, ironHandgonne;
    public static Item brassEruptor, bronzeEruptor, bismuthBronzeEruptor, blackBronzeEruptor, ironEruptor;
    public static Item brassMArquebus, ironMArquebus, steelMArquebus;
    public static Item brassMPistol, ironMPistol, steelMPistol;
    public static Item brassMBlunderbuss, ironMBlunderbuss, steelMBlunderbuss;
    public static Item brassMDragon, ironMDragon, steelMDragon;
    public static Item seedsWhiteGrape;
    public static ArrayList<Item> seedList, itemList;
    public static Item lapisBrick, hematiteBrick, limoniteBrick, malachiteBrick;
    public static Item floorTile,floorTileLapis,floorTileHematite,floorTileLimonite,floorTileMalachite;
    public static Item tallCandleholder;
    public static Item spikeItem;
    public static String WEAPONPATH = MODID+":weapons/";

    public static AmmoRegistry bulletRegistry, shotRegistry;

    public ItemSetup(){

    }

    public static void initItems() {

        SaturaLanx.log.info("Initializing items...");

        itemList = new ArrayList<>();

        bulletSL = new ItemSL(new String[]{"GunBulletStone","GunBulletWax","GunBulletLead","GunBulletSilver","GunBulletGold"}).setFolder("weapons/guns/bullet").setUnlocalizedName("GunBullet");
        paperCartridge = new PaperCartridgeItem(new String[]{"PaperCartridgeStone","PaperCartridgeWax","PaperCartridgeLead","PaperCartridgeSilver","PaperCartridgeGold"}).setFolder("weapons/guns/bullet").setUnlocalizedName("PaperCartridge");
        paperShotCartridge = new PaperCartridgeItem(new String[]{"PaperShotCartridgeStone","PaperShotCartridgeSalt","PaperShotCartridgeLead","PaperShotCartridgeSilver","PaperShotCartridgeGold"}).setFolder("weapons/guns/bullet").setUnlocalizedName("PaperShotCartridge");
        shotSL = new ItemSL(new String[]{"ShotStone","ShotSalt", "ShotLead","ShotSilver","ShotGold"}).setFolder("weapons/guns/bullet").setUnlocalizedName("GunShot");
        shotPelletSL = new ItemSL(new String[]{"GunPelletStone","GunPelletSalt","GunPelletLead","GunPelletSilver","GunPelletGold"}).setFolder("weapons/guns/bullet").setUnlocalizedName("GunPellet").setCreativeTab(null);
        saturaBulletMold = (new PotteryMoldSL(true)).setCounter(5).setBaseDamage(2).setMaxUnits(100).setMetals(new Metal[]{Global.LEAD, Global.SILVER, Global.GOLD}).setMetaNames(new String[]{"ClayBulletMold", "EmptyBulletMold", "LeadBulletMold", "SilverBulletMold","GoldBulletMold"}).setUnlocalizedName("SaturaBulletMold");
        saturaBulletMoldExtra = new ItemSL(new String[]{"WaxBulletMold"}).setFolder("pottery/molds").setUnlocalizedName("SaturaBulletMoldExtra");

        unfinishedBarrel = new UnfinishedSmithingItem(new String[]{"UnfinishedBronzeBarrel","UnfinishedBismuthBronzeBarrel","UnfinishedBlackBronzeBarrel","UnfinishedBrassBarrel","UnfinishedLongBrassBarrel","UnfinishedIronBarrel","UnfinishedLongIronBarrel","UnfinishedSteelBarrel","UnfinishedLongSteelBarrel"}).setFolder("weapons/guns/components").setUnlocalizedName("GunBarrel");
        unfinishedFlaredBarrel = new UnfinishedSmithingItem(new String[]{"UnfinishedBronzeFlaredBarrel","UnfinishedBismuthBronzeFlaredBarrel","UnfinishedBlackBronzeFlaredBarrel","UnfinishedBrassFlaredBarrel","UnfinishedLongBrassFlaredBarrel","UnfinishedIronFlaredBarrel","UnfinishedLongIronFlaredBarrel","UnfinishedSteelFlaredBarrel","UnfinishedLongSteelFlaredBarrel"}).setFolder("weapons/guns/components").setUnlocalizedName("GunFlaredBarrel");
        bronzeBarrel = new GunBarrelItem("BronzeBarrel",Global.BRONZE);
        bismuthBronzeBarrel = new GunBarrelItem("BismuthBronzeBarrel",Global.BISMUTHBRONZE);
        blackBronzeBarrel = new GunBarrelItem("BlackBronzeBarrel",Global.BLACKBRONZE);
        brassBarrel = new GunBarrelItem("BrassBarrel",Global.BRASS);
        brassLongBarrel = new GunBarrelItem("LongBrassBarrel",Global.BRASS);
        ironBarrel = new GunBarrelItem("IronBarrel",Global.WROUGHTIRON);
        ironLongBarrel = new GunBarrelItem("LongIronBarrel",Global.WROUGHTIRON);
        steelBarrel = new GunBarrelItem("SteelBarrel",Global.STEEL);
        steelLongBarrel = new GunBarrelItem("LongSteelBarrel",Global.STEEL);
        bronzeFlaredBarrel = new GunBarrelItem("BronzeFlaredBarrel",Global.BRONZE);
        bismuthBronzeFlaredBarrel = new GunBarrelItem("BismuthBronzeFlaredBarrel",Global.BISMUTHBRONZE);
        blackBronzeFlaredBarrel = new GunBarrelItem("BlackBronzeFlaredBarrel",Global.BLACKBRONZE);
        brassFlaredBarrel = new GunBarrelItem("BrassFlaredBarrel",Global.BRASS);
        brassLongFlaredBarrel = new GunBarrelItem("LongBrassFlaredBarrel",Global.BRASS);
        ironFlaredBarrel = new GunBarrelItem("IronFlaredBarrel",Global.WROUGHTIRON);
        ironLongFlaredBarrel = new GunBarrelItem("LongIronFlaredBarrel",Global.WROUGHTIRON);
        steelFlaredBarrel = new GunBarrelItem("SteelFlaredBarrel",Global.STEEL);
        steelLongFlaredBarrel = new GunBarrelItem("LongSteelFlaredBarrel",Global.STEEL);

        gunComponent = new ItemSL(new String[]{"BallScrew","MatchlockMechanism","ShortMatchlockGunStock","LongMatchlockGunStock"}).setFolder("weapons/guns/components").setUnlocalizedName("GunComponent");

        ballPuller = new BallPuller();

        powderFlask = new PowderFlaskItem(new String[]{"PowderFlaskCow","PowderFlaskSheep"}).setFolder("tools").setUnlocalizedName("PowderFlask");

        match = (new ItemSL(new String[]{"ScouredString", "Blackmatch", "Quickmatch", "Long Blackmatch"})).setUnlocalizedName("Match");
        slowmatch = new SlowmatchItem().setUnlocalizedName("Slowmatch").setCreativeTab(tab);

        itemList.add(bulletSL);
        itemList.add(paperCartridge);
        itemList.add(shotSL);
        itemList.add(paperShotCartridge);
        itemList.add(shotPelletSL);
        itemList.add(saturaBulletMold);
        itemList.add(saturaBulletMoldExtra);
        itemList.add(unfinishedBarrel);
        itemList.add(unfinishedFlaredBarrel);
        itemList.add(bronzeBarrel);
        itemList.add(bronzeFlaredBarrel);
        itemList.add(bismuthBronzeBarrel);
        itemList.add(bismuthBronzeFlaredBarrel);
        itemList.add(blackBronzeBarrel);
        itemList.add(blackBronzeFlaredBarrel);
        itemList.add(brassBarrel);
        itemList.add(brassLongBarrel);
        itemList.add(brassFlaredBarrel);
        itemList.add(brassLongFlaredBarrel);
        itemList.add(ironBarrel);
        itemList.add(ironLongBarrel);
        itemList.add(ironFlaredBarrel);
        itemList.add(ironLongFlaredBarrel);
        itemList.add(steelBarrel);
        itemList.add(steelLongBarrel);
        itemList.add(steelFlaredBarrel);
        itemList.add(steelLongFlaredBarrel);
        itemList.add(gunComponent);
        itemList.add(powderFlask);
        itemList.add(match);
        itemList.add(slowmatch);
        itemList.add(ballPuller);

        //Building materials
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

        //Traps
        if (Config.enableSpikes) {
            spikeItem = new SpikeItem();

            itemList.add(spikeItem);
        }

        //Gunpowder weapons
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
        if (Config.enableEruptor){
            brassEruptor = new EruptorItem(bronzeToolMaterial, copperToolMaterial.getDamageVsEntity()).setUnlocalizedName("BrassEruptor");
            bronzeEruptor = new EruptorItem(bronzeToolMaterial, copperToolMaterial.getDamageVsEntity()).setUnlocalizedName("BronzeEruptor");
            bismuthBronzeEruptor = new EruptorItem(bismuthBronzeToolMaterial, copperToolMaterial.getDamageVsEntity()).setUnlocalizedName("BismuthBronzeEruptor");
            blackBronzeEruptor = new EruptorItem(blackBronzeToolMaterial, copperToolMaterial.getDamageVsEntity()).setUnlocalizedName("BlackBronzeEruptor");
            ironEruptor = new EruptorItem(ironToolMaterial, bronzeToolMaterial.getDamageVsEntity()).setUnlocalizedName("IronEruptor");

            itemList.add(brassEruptor);
            itemList.add(bronzeEruptor);
            itemList.add(bismuthBronzeEruptor);
            itemList.add(blackBronzeEruptor);
            itemList.add(ironEruptor);
        }
        if(Config.enableArquebus){
            brassMArquebus = new MatchlockArquebusItem(bronzeToolMaterial, copperToolMaterial.getDamageVsEntity()).setUnlocalizedName("BrassArquebus");
            ironMArquebus = new MatchlockArquebusItem(ironToolMaterial, bronzeToolMaterial.getDamageVsEntity()).setUnlocalizedName("IronArquebus");
            steelMArquebus = new MatchlockArquebusItem(steelToolMaterial, bronzeToolMaterial.getDamageVsEntity()).setUnlocalizedName("SteelArquebus");

            itemList.add(brassMArquebus);
            itemList.add(ironMArquebus);
            itemList.add(steelMArquebus);

        }
        if(Config.enableMPistol){
            brassMPistol = new MatchlockPistolItem(bronzeToolMaterial, copperToolMaterial.getDamageVsEntity()/2).setUnlocalizedName("BrassMPistol");
            ironMPistol = new MatchlockPistolItem(ironToolMaterial, copperToolMaterial.getDamageVsEntity()/2).setUnlocalizedName("IronMPistol");
            steelMPistol = new MatchlockPistolItem(steelToolMaterial, ironToolMaterial.getDamageVsEntity()/2).setUnlocalizedName("SteelMPistol");

            itemList.add(brassMPistol);
            itemList.add(ironMPistol);
            itemList.add(steelMPistol);
        }
        if(Config.enableMBlunderbuss){
            brassMBlunderbuss = new MatchlockBlunderbussItem(bronzeToolMaterial, copperToolMaterial.getDamageVsEntity()).setUnlocalizedName("BrassMBlunderbuss");
            ironMBlunderbuss = new MatchlockBlunderbussItem(ironToolMaterial, bronzeToolMaterial.getDamageVsEntity()).setUnlocalizedName("IronMBlunderbuss");
            steelMBlunderbuss = new MatchlockBlunderbussItem(steelToolMaterial, bronzeToolMaterial.getDamageVsEntity()).setUnlocalizedName("SteelMBlunderbuss");

            itemList.add(brassMBlunderbuss);
            itemList.add(ironMBlunderbuss);
            itemList.add(steelMBlunderbuss);
        }
        if(Config.enableMDragon){
            brassMDragon = new MatchlockDragonItem(bronzeToolMaterial, copperToolMaterial.getDamageVsEntity()/2).setUnlocalizedName("BrassMDragon");
            ironMDragon = new MatchlockDragonItem(ironToolMaterial, bronzeToolMaterial.getDamageVsEntity()/2).setUnlocalizedName("IronMDragon");
            steelMDragon = new MatchlockDragonItem(steelToolMaterial, ironToolMaterial.getDamageVsEntity()/2).setUnlocalizedName("SteelMDragon");

            itemList.add(brassMDragon);
            itemList.add(ironMDragon);
            itemList.add(steelMDragon);
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

        //Melee weapons
        if (Config.enableExtraMaces) {
            saturaMaceMold = (new PotteryMoldSL(true)).setCounter(4).setBaseDamage(2).setMaxUnits(100).setMetals(new Metal[]{Global.LEAD, Global.BRASS}).setMetaNames(new String[]{"ClayMaceMold", "EmptyMaceMold", "LeadMaceMold", "BrassMaceMold"}).setUnlocalizedName("SaturaMaceMold");

            stoneClub = (new SwordItemSL(igInToolMaterial, igInToolMaterial.getDamageVsEntity(), EnumDamageType.CRUSHING)).setCrushDamageShape("3X5_MACE").setAttackSpeed(19).setUnlocalizedName("StoneClub").setCreativeTab(tab).setTextureName(WEAPONPATH + "StoneClub").setMaxDamage(igInStoneUses);
            leadMaceHead = (new ToolHeadSL("LeadMaceHead",Global.LEAD)).setFolder(WEAPONPATH);
            leadMace = (new SwordItemSL(copperToolMaterial, copperToolMaterial.getDamageVsEntity() + 50, EnumDamageType.CRUSHING)).setCrushDamageShape("3X5_MACE").setAttackSpeed(23).setUnlocalizedName("LeadMace").setTextureName(WEAPONPATH + "LeadMace").setMaxDamage(copperUses / 2);
            brassMaceHead = (new ToolHeadSL("BrassMaceHead",Global.BRASS)).setFolder(WEAPONPATH);
            brassMace = (new SwordItemSL(bronzeToolMaterial, bronzeToolMaterial.getDamageVsEntity() - 30, EnumDamageType.CRUSHING)).setCrushDamageShape("3X5_MACE").setAttackSpeed(19).setUnlocalizedName("BrassMace").setTextureName(WEAPONPATH + "BrassMace").setMaxDamage(bronzeUses - 50);

            itemList.add(saturaMaceMold);
            itemList.add(stoneClub);
            itemList.add(leadMaceHead);
            itemList.add(leadMace);
            itemList.add(brassMaceHead);
            itemList.add(brassMace);
        }

        //Ranged weapons
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

        //Incendiary devices
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

        //Fireworks
        if (Config.enableFirecrackers){
            firecracker = (new Firecracker(1)).setUnlocalizedName("Firecracker").setTextureName(MODID + ":tools/Firecracker");

            itemList.add(firecracker);
        }
        //Musical instruments
        if(Config.enableCastanets) {
            castanets = (new InstrumentSimpleSL(1.3F, 1, 1, 1)).setUnlocalizedName("Castanets");
            ((InstrumentSimpleSL) castanets).setSounds(new String[]{MODID + ":castanet1", MODID + ":castanet2"});

            itemList.add(castanets);
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

    public static void setupAmmoRegistries(){

        bulletRegistry = new AmmoRegistry();
        bulletRegistry.addAmmoData(new AmmoIdentifier(bulletSL,0),new AmmoData("StoneBullet",1.0F,Global.UNKNOWN));
        bulletRegistry.addAmmoData(new AmmoIdentifier(bulletSL,1),new AmmoData("WaxBullet",0.05F,Global.UNKNOWN));
        bulletRegistry.addAmmoData(new AmmoIdentifier(bulletSL,2),new AmmoData("LeadBullet",1.2F,Global.LEAD));
        bulletRegistry.addAmmoData(new AmmoIdentifier(bulletSL,3),new AmmoData("SilverBullet",1.1F,Global.SILVER));
        bulletRegistry.addAmmoData(new AmmoIdentifier(bulletSL,4),new AmmoData("GoldBullet",1.25F,Global.GOLD));
        bulletRegistry.addAmmoData(new AmmoIdentifier(paperCartridge, 0),new AmmoData("StoneBullet",1.0F,Global.UNKNOWN));
        bulletRegistry.addAmmoData(new AmmoIdentifier(paperCartridge, 1),new AmmoData("WaxBullet",0.05F,Global.UNKNOWN));
        bulletRegistry.addAmmoData(new AmmoIdentifier(paperCartridge, 2),new AmmoData("LeadBullet",1.2F,Global.LEAD));
        bulletRegistry.addAmmoData(new AmmoIdentifier(paperCartridge, 3),new AmmoData("SilverBullet",1.1F,Global.SILVER));
        bulletRegistry.addAmmoData(new AmmoIdentifier(paperCartridge, 4),new AmmoData("GoldBullet",1.25F,Global.GOLD));

        shotRegistry = new AmmoRegistry();
        shotRegistry.addAmmoData(new AmmoIdentifier(shotSL,0),new AmmoData("StoneShot",1.0F,Global.UNKNOWN));
        shotRegistry.addAmmoData(new AmmoIdentifier(shotSL,1),new AmmoData("SaltShot",0.05F,Global.UNKNOWN));
        shotRegistry.addAmmoData(new AmmoIdentifier(shotSL,2),new AmmoData("LeadShot",1.2F,Global.LEAD));
        shotRegistry.addAmmoData(new AmmoIdentifier(shotSL,3),new AmmoData("SilverShot",1.1F,Global.SILVER));
        shotRegistry.addAmmoData(new AmmoIdentifier(shotSL,4),new AmmoData("GoldShot",1.25F,Global.GOLD));
        shotRegistry.addAmmoData(new AmmoIdentifier(paperShotCartridge,0),new AmmoData("StoneShot",1.0F,Global.UNKNOWN));
        shotRegistry.addAmmoData(new AmmoIdentifier(paperShotCartridge,1),new AmmoData("SaltShot",0.05F,Global.UNKNOWN));
        shotRegistry.addAmmoData(new AmmoIdentifier(paperShotCartridge,2),new AmmoData("LeadShot",1.2F,Global.LEAD));
        shotRegistry.addAmmoData(new AmmoIdentifier(paperShotCartridge,3),new AmmoData("SilverShot",1.1F,Global.SILVER));
        shotRegistry.addAmmoData(new AmmoIdentifier(paperShotCartridge,4),new AmmoData("GoldShot",1.25F,Global.GOLD));
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
        if(Config.enableEruptor){
            MinecraftForgeClient.registerItemRenderer(brassEruptor, new EruptorRender());
            MinecraftForgeClient.registerItemRenderer(bronzeEruptor, new EruptorRender());
            MinecraftForgeClient.registerItemRenderer(bismuthBronzeEruptor, new EruptorRender());
            MinecraftForgeClient.registerItemRenderer(blackBronzeEruptor, new EruptorRender());
            MinecraftForgeClient.registerItemRenderer(ironEruptor, new EruptorRender());
        }
        if(Config.enableArquebus){
            MinecraftForgeClient.registerItemRenderer(brassMArquebus, new ArquebusRender());
            MinecraftForgeClient.registerItemRenderer(ironMArquebus, new ArquebusRender());
            MinecraftForgeClient.registerItemRenderer(steelMArquebus, new ArquebusRender());
        }
        if(Config.enableMPistol){
            MinecraftForgeClient.registerItemRenderer(brassMPistol, new MatchlockPistolRender());
            MinecraftForgeClient.registerItemRenderer(ironMPistol, new MatchlockPistolRender());
            MinecraftForgeClient.registerItemRenderer(steelMPistol, new MatchlockPistolRender());
        }
        if(Config.enableMBlunderbuss){
            MinecraftForgeClient.registerItemRenderer(brassMBlunderbuss, new MatchlockBlunderbussRender());
            MinecraftForgeClient.registerItemRenderer(ironMBlunderbuss, new MatchlockBlunderbussRender());
            MinecraftForgeClient.registerItemRenderer(steelMBlunderbuss, new MatchlockBlunderbussRender());
        }
        if(Config.enableMDragon){
            MinecraftForgeClient.registerItemRenderer(brassMDragon, new MatchlockDragonRender());
            MinecraftForgeClient.registerItemRenderer(ironMDragon, new MatchlockDragonRender());
            MinecraftForgeClient.registerItemRenderer(steelMDragon, new MatchlockDragonRender());
        }
    }
}
