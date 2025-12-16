package com.snark.saturalanx.core;

import com.dunk.tfc.api.TFCOptions;
import com.snark.saturalanx.SaturaLanx;
import net.minecraftforge.common.config.Configuration;

import java.io.File;


public class Config {

    public static Configuration config;

    //Food
    public static String FOOD_CATEGORY = "food";
    public static boolean enableDandelionFood = true;
    public static boolean enableGoldenrodFood = true;
    public static boolean enableNasturtiumFood = true;
    public static boolean enableCalendulaFood = true;
    public static boolean enableMilkweedFood = true;
    public static boolean enableDaisyFood = true;
    public static boolean enableTulipFood = true;
    public static boolean enablePoppyFood = true;
    public static boolean enableWhiteGrapes = true;

    //Drinks
    public static String DRINK_CATEGORY = "drinks";
    public static boolean enableLiqueurs = true;

    //Blocks
    public static String BUILDINGBLOCKS = "General Building Blocks";
    public static boolean enableColouredBricks = true;
    public static boolean enableFloorTiles = true;
    public static boolean enableMortaredCobble = true;
    public static boolean enableMortaredCobbleTextures = true;
    public static boolean enableStockade = true;
    public static boolean doesStockadeBlockSpiders = true;
    public static boolean enableGabion = true;
    public static boolean enableGabionMultistage = true;
    public static boolean enableGabionOpenBottom = true;
    public static boolean enableHypocaust = true;
    public static int hypocaustSizeCap = 8;
    public static int hypocaustFuelCheckFrequency = 3600;
    public static int hypocaustIntegrityCheckFrequency = 6000;
    public static String DECORATION = "Decorative Blocks";
    public static boolean enableTallCandlestick = true;
    public static float tallCandlestickBurnModifier = 1.2F;
    public static float tallCandlestickLightModifier = 1.2F;
    public static boolean enableAnimalTrophies = true;
    public static boolean enableChalices = true;


    //Traps
    public static String TRAPS = "Traps";
    public static boolean enableSpikes = true;
    public static int woodenSpikesBreakChance = 50;
    public static int woodenSpikesDamage = 20;
    public static int metalSpikesDamage = 40;
    public static boolean enableLeafCover = true;
    public static boolean enableBearTrap = true;
    public static boolean bearTrapShouldAffectPlayers = true;
    public static int bearTrapDamageFactor = 10;
    public static int bearTrapAnimalMaxHealth = 3000;
    public static int bearTrapAnimalEscapeChance = 50;


    //Ranged weapons
    public static String BOLAS = "Bolas";
    public static boolean enableBolas = true;
    public static int bolasDamage = 100;
    public static int bolasMinSwing = 20;
    public static int bolasMaxSwing = 60;
    public static int bolasChance = 40;
    public static int bolasDuration = 600;
    public static int bolasDurationModifier = 300;
    public static int bolasAmplifier = 4;
    public static boolean bolasShouldAffectPlayers = true;
    public static String POISONPROJECTILES = "Poison Projectiles";
    public static boolean enablePoisonArrows = true;
    public static int poisonProjectileEffectDurationModifier = 50;
    public static int poisonProjectileExpirationTime = TFCOptions.yearLength;
    public static String SPEARTHROWER = "Spear Thrower";
    public static boolean enableSpearThrower = true;
    public static float primitiveSpearThrowerForceMultiplier = 1.3F;
    public static float spearThrowerForceMultiplier = 1.7F;
    public static int spearThrowerReadyTime = 4;

    public static String ROPEARROWS = "Rope Arrows";
    public static boolean enableRopeArrows = true;
    public static int ropeArrowRopeLenght = 9;
    public static int ropeArrowWeightAllowance = 2;

    //Melee weapons
    public static String EXTRAMACES = "Extra Maces";
    public static boolean enableExtraMaces = true;

    public static String HARDENEDSPEAR = "Fire Hardened Wood Spears";
    public static boolean enableFireHardenedSpears = true;
    public static int fireHardenedSpearDamageModifier = 50;
    public static int fireHardenedSpearDurabilityModifier = 50;

    //Gunpowder
    public static String FIREARMS = "Firearms";
    public static int slowmatchLength = 6000;

    public static boolean enableHandgonne = true;
    public static int handgonneLoadingTime = 200;
    public static int handgonneAimingTime = 80;
    public static int handgonneDamage = 320;

    public static boolean enableArquebus = true;
    public static int arquebusLoadingTime = 200;
    public static int arquebusAimingTime = 20;
    public static int arquebusDamage = 450;

    public static boolean enableMBlunderbuss = true;
    public static int mBlunderbussLoadingTime = 200;
    public static int mBlunderbussAimingTime = 20;
    public static int mBlunderbussDamage = 44;
    public static int mBlunderbussPellets = 10;

    public static boolean enableMHandMortar = true;
    public static int mHandMortarLoadingTime = 400;
    public static int mHandMortarAimingTime = 40;
    public static int mHandMortarCannonballDamage = 1070;
    public static int mHandMortarBombPhysicalDamage = 530;
    public static int mHandMortarBombExplosionDamage = 16;

    //Incendiaries
    public static String INCENDIARYPOT = "Incendiary Pot";
    public static boolean enableIncendiaryPot = true;
    public static int incendiaryPotWickLength = 1200;
    public static int incendiaryPotDiameter = 3;
    public static int incendiaryPotDamageTimes = 6;
    public static int incendiaryPotBlockLife = 20;

    public static String FLAMEARROWS = "Flame Arrows";
    public static boolean enableFlameArrows = true;
    public static int flameArrowBurnDuration = 10;
    public static int flameArrowIgniteChance = 50;
    public static int flameArrowSalvageChance = 30;
    public static boolean fireArrowIgnitesEntities = true;
    public static int fireArrowIgniteEntityChance = 30;
    public static int fireArrowIgniteEntityInterval = 2;
    public static int fireArrowGasEffects = 4;
    public static int fireArrowGasEffectsInterval = 10;
    public static int fireArrowIgniteAreaOfEffect = 1;
    public static int fireArrowGasAreaOfEffect = 1;
    public static int fireArrowGasEffectsChance = 70;
    //Explosives
    public static String POTGRENADE = "Pot Grenades";
    public static boolean enablePotGrenades = true;
    public static int potGrenadeFuseLength = 500;
    public static int potGrenadePowerCap = 64;
    public static int potGrenadeMaxRange = 4;
    public static int potGrenadeMinRange = 1;
    //Pyrotechnics
    public static String PYROTECHNICS = "Pyrotechnics";
    public static boolean enableFirecrackers = true;
    public static boolean enableSmokeBombs = false;
    //Music
    public static String MUSIC = "Music";
    public static boolean enableCastanets = true;
    public static boolean enableRattle = true;
    public static boolean enableRainstick = true;
    public static boolean enablePanFlute = true;
    public static boolean enableSistrum = true;
    public static boolean enableBagpipe = true;


    public Config(){

    }

    public static void init(String configDir){

        if(config==null){
            File path = new File(configDir + "/" + SaturaLanx.MODID + ".cfg");

            config = new Configuration(path);
            loadConfig();
        }
    }

    public static void loadConfig(){

        //Food
        enableDandelionFood = config.getBoolean("Enable Dandelion Food",FOOD_CATEGORY,true,"Enables dandelion tops and dandelion roots, as well as related drinks.");
        enableGoldenrodFood = config.getBoolean("Enable Goldenrod Food",FOOD_CATEGORY,true,"Enables goldenrod tops and goldenrod leaves, as well as related drinks.");
        enableNasturtiumFood = config.getBoolean("Enable Nasturtium Food",FOOD_CATEGORY,true,"Enables prepared nasturtium.");
        enableMilkweedFood = config.getBoolean("Enable Milkweed Food",FOOD_CATEGORY,true,"Enables milkweed pods.");
        enableCalendulaFood = config.getBoolean("Enable Calendula Food",FOOD_CATEGORY,true,"Enables calendula petals.");
        enableTulipFood = config.getBoolean("Enable Tulip Food",FOOD_CATEGORY,true,"Enables tulip petals and bulbs.");
        enableDaisyFood = config.getBoolean("Enable Daisy Food",FOOD_CATEGORY,true,"Enables daisy tops, as well as related drinks.");
        enablePoppyFood = config.getBoolean("Enable Poppy Food",FOOD_CATEGORY,true,"Enables poppy seed.");

        enableWhiteGrapes = config.getBoolean("Enable White Grapes",FOOD_CATEGORY,true,"Enables white grapes.");

        //Drink
        enableLiqueurs = config.getBoolean("Enable Liqueurs",DRINK_CATEGORY,true,"Enables liqueurs.");

        //Building Blocks
        enableColouredBricks = config.getBoolean("Enable Coloured Bricks",BUILDINGBLOCKS,true,"Enables coloured bricks.");

        enableFloorTiles = config.getBoolean("Enable Floor Tiles",BUILDINGBLOCKS,true,"Enables ceramic floor tiles.");

        enableMortaredCobble = config.getBoolean("Enable Mortared Cobble",BUILDINGBLOCKS,true,"Enables mortared cobblestone.");

        enableMortaredCobbleTextures = config.getBoolean("Enable Custom Mortared Cobble Textures",BUILDINGBLOCKS,true,"Enables custom textures for mortared cobble. If disabled, it will use the same textures as normal cobble");

        enableHypocaust = config.getBoolean("Enable Hypocaust",BUILDINGBLOCKS,true,"Enables hypocaust blocks");
        hypocaustSizeCap = config.getInt("Hypocaust Size Cap",BUILDINGBLOCKS,16,0,Integer.MAX_VALUE,"Max size (to one side) that hypocausts can be built to. Warning: setting this too high will likely cause lag when running integrity/fuel/fluid update checks");
        hypocaustIntegrityCheckFrequency = config.getInt("Hypocaust Integrity Check Frequency",BUILDINGBLOCKS,6000,0,Integer.MAX_VALUE,"The time between each integrity check run by hypocaust structures, expressed in seconds");
        hypocaustFuelCheckFrequency = config.getInt("Hypocaust Fuel Check Frequency",BUILDINGBLOCKS,3600,0,Integer.MAX_VALUE,"The time between each fuel check run by hypocaust structures, expressed in seconds.");

        //Decorative blocks

        enableTallCandlestick = config.getBoolean("Enable Tall Candlesticks",DECORATION,true,"Enables tall candlesticks.");
        tallCandlestickBurnModifier = config.getFloat("Tall Candlestick Burn Modifier",DECORATION,1.2F,0,Float.MAX_VALUE,"Burn time modifier for tall candlesticks.");
        tallCandlestickLightModifier = config.getFloat("Tall Candlestick Light Modifier",DECORATION,1.2F,0,Float.MAX_VALUE,"Light intensity modifier for tall candlesticks.");

        enableAnimalTrophies = config.getBoolean("Enable Animal Trophies",DECORATION, true, "Enables animal trophies");

        //Fortifications

        enableGabion = config.getBoolean("Enable Gabion",BUILDINGBLOCKS,true,"Enables gabion blocks.");
        enableGabionMultistage = config.getBoolean("Enable Gabion Multistage Destruction",BUILDINGBLOCKS,true,"If true, gabion blocks turn into cobblestone when broken or exploded. If set to false, they are simply destroyed.");
        enableGabionOpenBottom = config.getBoolean("Enable Gabion Open Bottom",BUILDINGBLOCKS,true,"Enables gabions having an open bottom, and spawning a falling cobble block if not placed on a solid block.");

        enableStockade = config.getBoolean("Enable Stockade",BUILDINGBLOCKS,true,"Enables stockade blocks.");
        doesStockadeBlockSpiders = config.getBoolean("Does Blockade Block Spiders",BUILDINGBLOCKS,true,"If true, spiders will be prevented from climbing over stockade walls.");

        //Traps
        enableSpikes = config.getBoolean("Enable Spikes",TRAPS,true,"Enable spike blocks");
        woodenSpikesBreakChance = config.getInt("Wooden Spikes Break Chance",TRAPS,50,0,100,"0-100 chance for wooden spikes to break when an entity falls on them");
        woodenSpikesDamage = config.getInt("Wooden Spikes Damage",TRAPS,20,0,Integer.MAX_VALUE,"The damage dealt by wooden spikes");
        metalSpikesDamage = config.getInt("Metal Spikes Damage",TRAPS,40,0,Integer.MAX_VALUE,"The damage dealt by metal spikes");

        enableLeafCover = config.getBoolean("Enable Leaf Cover",TRAPS,true,"Enable leaf cover blocks");

        enableBearTrap = config.getBoolean("Enable Bear Trap",TRAPS,true,"Enable bear traps");
        bearTrapShouldAffectPlayers = config.getBoolean("Bear Trap Should Affect Players",TRAPS,true,"Determines if players can be affected by bear traps");
        bearTrapDamageFactor = config.getInt("Bear Trap Damage Factor",TRAPS,10,0,Integer.MAX_VALUE/20,"How much damage should a bear trap inflict. This is multiplied by 20 by TFC, and is modified by bear trap material");
        bearTrapAnimalEscapeChance = config.getInt("Bear Trap Animal Escape Chance",TRAPS,50,0,100,"What's the chance for an aggroed animal to escape from a bear trap. Acts as a max value, scales with the inverse of animal health");
        bearTrapAnimalMaxHealth = config.getInt("Bear Trap Animal Max Health",TRAPS,3000,0,Integer.MAX_VALUE,"Mobs with more health than this won't get stuck in bear traps");

        //Ranged weapons
        enableBolas = config.getBoolean("Enable Bolas",BOLAS,true,"Enables bolas.");
        bolasDamage = config.getInt("Bolas Damage",BOLAS,120,0,Integer.MAX_VALUE,"The amount of damage done by a bolas. Keep in mind that the actual damage equals this * 1.5");
        bolasMinSwing = config.getInt("Bolas Min Swing",BOLAS,20,0,72000,"The amount of time (in game ticks) that the player has to swing a bolas in order to launch it");
        bolasMaxSwing = config.getInt("Bolas Max Swing",BOLAS,60,0,72000,"The amount of time (in game ticks) that the player has to swing a bolas for it to reach max force.");
        bolasChance = config.getInt("Bolas Slowness Chance",BOLAS,40,0,100,"The chance the bolas has to apply the slowness effect.");
        bolasDuration = config.getInt("Bolas Slowness Duration",BOLAS,600,0,Integer.MAX_VALUE,"The duration of the slowness effect applied by the bolas.");
        bolasDurationModifier = config.getInt("Bolas Slowness Duration Modifier",BOLAS,300,0,Integer.MAX_VALUE,"How much the duration of the slowness effect can vary.");
        bolasAmplifier = config.getInt("Bolas Slowness Amplifier",BOLAS,4,0,5,"The amplifier of the slowness effect applied by the bolas");
        bolasShouldAffectPlayers = config.getBoolean("Bolas Effects Players",BOLAS,true,"If false, players won't be subjected to the bolas' slowing effects");

        enableRopeArrows = config.getBoolean("Enable Rope Arrows",ROPEARROWS,true,"Enables rope arrows");
        ropeArrowRopeLenght = config.getInt("Rope Arrow Rope Lenght",ROPEARROWS,9,0,128,"How many blocks long should the rope below a rope arrow be");
        ropeArrowWeightAllowance = config.getInt("Rope Arrow Weight Allowance",ROPEARROWS,2,1,4,"How heavy can the player's equipment be without breaking the rope arrow: 1-Very Light, 2-Light, 3-Medium, 3-Heavy");

        enablePoisonArrows = config.getBoolean("Enable Poison Arrows", POISONPROJECTILES,true,"Enables poison arrows");
        poisonProjectileExpirationTime = config.getInt("Poison Projectile Expiration Time", POISONPROJECTILES,TFCOptions.yearLength,-1,Integer.MAX_VALUE,"How many days do poison projectiles keep their effects for. If set to -1, poison projectiles will keep their effect indefinitely");
        poisonProjectileEffectDurationModifier = config.getInt("Poison Arrow Effect Duration Modifier", POISONPROJECTILES,50,0,100,"How much the duration of the poison projectile's effect can vary, expressed as a percentage of its base duration.");

        enableFireHardenedSpears = config.getBoolean("Enable Fire Hardened Spears",HARDENEDSPEAR,true,"Enables fire hardened spears");
        fireHardenedSpearDurabilityModifier = config.getInt("Fire Hardened Spear Durability Modifier",HARDENEDSPEAR,50,1,100,"Percentage modifier for hardened spear durability.");
        fireHardenedSpearDamageModifier = config.getInt("Fire Hardened Spear Damage Modifier",HARDENEDSPEAR,50,1,100,"Percentage modifier for hardened spear damage.");

        enableSpearThrower = config.getBoolean("Enable Spear Thrower",SPEARTHROWER,true,"Enables spear throwers");
        spearThrowerForceMultiplier = config.getFloat("Spear Thrower Force Multiplier",SPEARTHROWER,1.7F,1,5,"Force multiplier for spear thrower projectiles");
        primitiveSpearThrowerForceMultiplier = config.getFloat("Primitive Spear Thrower Force Multiplier",SPEARTHROWER,1.3F,1,5,"Force multiplier for spear thrower projectiles");
        spearThrowerReadyTime = config.getInt("Spear Thrower Ready Time",SPEARTHROWER,4,0,Integer.MAX_VALUE,"How many seconds it takes to ready the spear thrower");
        
        //Melee weapons
        enableExtraMaces = config.getBoolean("Enable Extra Maces",EXTRAMACES,true,"Enables stone clubs, lead and brass maces");

        //Firearms
        slowmatchLength = config.getInt("Slowmatch Burn Length",FIREARMS,6000,0,Integer.MAX_VALUE,"How long (in ticks) should slowmatch burn");

        enableHandgonne = config.getBoolean("Enable Handgonnes",FIREARMS,true,"Enables handgonnes.");
        handgonneLoadingTime = config.getInt("Handgonne Loading Time",FIREARMS,200,0,72000,"The amount of time (in game ticks) that the player needs to reload the handgonne.");
        handgonneAimingTime = config.getInt("Handgonne Aiming Time",FIREARMS,80,0,72000,"The amount of time (in game ticks) that the player needs to aim and fire the handgonne.");
        handgonneDamage = config.getInt("Handgonne Damage",FIREARMS,320,1,Integer.MAX_VALUE,"The base damage dealt by handgonne bullets.");

        enableArquebus = config.getBoolean("Enable Arquebuses",FIREARMS,true,"Enables arquebuses.");
        arquebusLoadingTime = config.getInt("Arquebus Loading TIme",FIREARMS,200,0,72000,"The amount of time (in game ticks) that the player needs to reload the arquebus.");
        arquebusAimingTime = config.getInt("Arquebus Aiming Time",FIREARMS,20,0,72000,"The amount of time (in game ticks) that the player needs to aim and fire the arquebus.");
        arquebusDamage = config.getInt("Arquebus Damage",FIREARMS,450,0,Integer.MAX_VALUE,"The base damage dealt by arquebus bullets.");

        enableMBlunderbuss = config.getBoolean("Enable Matchlock Blunderbusses",FIREARMS,true,"Enables matchlock blunderbusses");
        mBlunderbussLoadingTime = config.getInt("Matchlock Blunderbuss Loading Time",FIREARMS,200,0,72000,"The amount of time (in game ticks) that the player needs to reload the matchlock blunderbuss");
        mBlunderbussAimingTime = config.getInt("Matchlock Blunderbuss Aiming Time",FIREARMS,20,0,72000,"The amount of time (in game ticks) that the player needs to fire the matchlock blunderbuss");
        mBlunderbussDamage = config.getInt("Matchlock Blunderbuss Damage",FIREARMS,44,0,Integer.MAX_VALUE,"The base damage dealt by the matchlock blunderbuss's pellet.");
        mBlunderbussPellets = config.getInt("Matchlock Blunderbuss Pellets",FIREARMS,10,0,Integer.MAX_VALUE,"The amounts of pellets fired by the matchlock blunderbuss");

        //Incendiaries
        enableIncendiaryPot = config.getBoolean("Enable Incendiary Pots",INCENDIARYPOT,true,"Enables incendiary pots");
        incendiaryPotWickLength = config.getInt("Incendiary Pot Wick Length",INCENDIARYPOT,600,0,72000,"The amount of time (in game ticks) that the wick burns for.");
        incendiaryPotDiameter = config.getInt("Incendiary Pot Diameter",INCENDIARYPOT,3,0,10,"The diameter in blocks of the incediary pot's oil. If set to 0, it will spawn a cross shape with 1-long arms.");
        incendiaryPotDamageTimes = config.getInt("Incendiary Pot Damage Times",INCENDIARYPOT,6,1,Integer.MAX_VALUE,"How many times the fire inflicted by the flaming oil should damage entities.");
        incendiaryPotBlockLife = config.getInt("Incendiary Pot Block Life",INCENDIARYPOT,20,1,(Integer.MAX_VALUE)/20,"How long the flaming block spawned by incendiary pots stays in the world, expressed in seconds.");

        enableFlameArrows = config.getBoolean("Enable Flame Arrows",FLAMEARROWS,true,"Enables flame arrows.");
        flameArrowBurnDuration = config.getInt("Flame Arrow Burn Duration",FLAMEARROWS,10,0, Integer.MAX_VALUE/20,"What is the burn duration of flame arrows, expressed in seconds.");
        flameArrowIgniteChance = config.getInt("Flame Arrow Block Ignition Chance",FLAMEARROWS,50,0,100,"The chance for the arrow entity to set fire to surrounding blocks.");
        flameArrowSalvageChance = config.getInt("Flame Arrow Salvage Chance",FLAMEARROWS,30,0,100,"The chance that a flame arrow burning out in the player's inventory will return an intact arrow.");
        fireArrowIgnitesEntities = config.getBoolean("Fire Arrow Ignites Entities",FLAMEARROWS,true,"Whetever burning fire arrows have a chance to set nearby entities on fire");
        fireArrowIgniteEntityChance = config.getInt("Fire Arrow Entity Ignition Chance",FLAMEARROWS,30,0,100,"The chance that a fire arrow will set fire to nearby entities");
        fireArrowIgniteEntityInterval = config.getInt("Fire Arrow Entity Ignition Interval",FLAMEARROWS,2,0,Integer.MAX_VALUE/20,"How many seconds should the fire arrow try to set fire to entities");
        fireArrowGasEffects = config.getInt("Fire Arrow Gas Effects",FLAMEARROWS,4,0,4,"Whetever entities close to a fire arrow should suffer effects from gas. 0-No, 1-Nausea, 2-Blindness, 3-Poison, 4-Random");
        fireArrowGasEffectsInterval = config.getInt("Fire Arrow Gas Effects Interval",FLAMEARROWS,10,0,Integer.MAX_VALUE/20,"How many seconds should a fire arrow try to apply gas effects tp entities");
        fireArrowIgniteAreaOfEffect = config.getInt("Fire Arrow Entity Ignition AOE",FLAMEARROWS,1,1,Integer.MAX_VALUE,"Radius in blocks that a fire arrow will check for entities to set on fire.");
        fireArrowGasAreaOfEffect = config.getInt("Fire Arrow Gas Effects AOE",FLAMEARROWS,2,1,Integer.MAX_VALUE,"Radius in blocks that a fire arrow will check for entities to subject to gas effects.");

        /*enableFiresticks = config.getBoolean("Enable Firesticks",FIRESTICK,true,"Enables firesticks.");
        firestickMeleeDamage = config.getInt("Firestick Melee Damage",FIRESTICK,50,0,Integer.MAX_VALUE,"Damage dealt by firesticks in melee");
        firestickBurnTime = config.getInt("Firestick Burn Time",FIRESTICK,600,0,Integer.MAX_VALUE,"The burn length (in ticks) of lit firesticks.");
        entityFirestickIgniteChance = config.getInt("Firestick Ignite Chance",FIRESTICK,20,0,100,"Base chance for firesticks to set the hit entity on fire.");
        entityFirestickFireDamageTimes = config.getInt("Firestick Fire Damage Times",FIRESTICK,3,0,Integer.MAX_VALUE,"How many times the fire inflicted by the firestick should damage entities");*/

        //Explosives
        enablePotGrenades = config.getBoolean("Enable Pot Grenades",POTGRENADE,true,"Enables pot grenades");
        potGrenadeFuseLength = config.getInt("Pot Grenade Fuse Lenght",POTGRENADE,300,0,Integer.MAX_VALUE,"Base fuse length for pot grenades");
        potGrenadePowerCap = config.getInt("Pot Grenade Power Cap",POTGRENADE,64,0,Integer.MAX_VALUE,"Hard cap for pot grenades' explosive power");
        potGrenadeMaxRange = config.getInt("Pot Grenade Max Range",POTGRENADE,4,0,Integer.MAX_VALUE,"The max radius of an explosion, expressed in blocks. Note that the actual range is equal to this*2.");
        potGrenadeMinRange = config.getInt("Pot Grenade Min Range",POTGRENADE,1,0,Integer.MAX_VALUE,"The min radius of an explosion, expressed in blocks. Note that the actual range is equal to this*2.");

        //Pyrotechnics
        enableFirecrackers = config.getBoolean("Enable Firecrackers",PYROTECHNICS,true,"Enables firecrackers");
        //enableSmokeBombs = config.getBoolean("Enable Smoke Bombs",PYROTECHNICS,false,"Enables smoke bombs (Currently do not work)");

        //Music
        enableCastanets = config.getBoolean("Enable Castanets",MUSIC,true,"Enables castanets");

        if(config.hasChanged())
            config.save();
    }

    public static Configuration getConfig(){
        return config;
    }
}
