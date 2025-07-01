package com.snark.saturalanx.core;

import com.snark.saturalanx.SaturaLanx;
import com.snark.saturalanx.TE.ChaliceTE;
import com.snark.saturalanx.TE.FlamingBlockTE;
import com.snark.saturalanx.TE.HypocaustTE;
import com.snark.saturalanx.TE.TileTE;
import com.snark.saturalanx.entities.*;
import com.snark.saturalanx.renders.entity.RenderBullet;
import com.snark.saturalanx.renders.entity.RenderCustomArrow;
import com.snark.saturalanx.renders.entity.RenderGrenade;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.entity.RenderSnowball;

public class EntitySetup {


    public static void registerEntities(){

        SaturaLanx.log.info("Registering entities...");

        if(Config.enableIncendiaryPot) {
            EntityRegistry.registerGlobalEntityID(EntityIncendiaryPot.class, "incendiaryPotSL", EntityRegistry.findGlobalUniqueEntityId());
            EntityRegistry.registerModEntity(EntityIncendiaryPot.class, "incendiaryPotSL", 0, SaturaLanx.instance, 64, 10, true);
        }
        if(Config.enableFlameArrows){
            EntityRegistry.registerGlobalEntityID(EntityFlameArrow.class,"flamearrowSL",EntityRegistry.findGlobalUniqueEntityId());
            EntityRegistry.registerModEntity(EntityFlameArrow.class,"flamearrowSL",6,SaturaLanx.instance,64,10,true);
        }
        if(Config.enablePoisonArrows){
            EntityRegistry.registerGlobalEntityID(EntityPoisonArrow.class,"poisonarrowSL",EntityRegistry.findGlobalUniqueEntityId());
            EntityRegistry.registerModEntity(EntityPoisonArrow.class,"poisonarrowSL",7,SaturaLanx.instance,64,10,true);
        }
        if(Config.enablePotGrenades) {
            EntityRegistry.registerGlobalEntityID(EntityPotGrenade.class, "potGrenadeSL", EntityRegistry.findGlobalUniqueEntityId());
            EntityRegistry.registerModEntity(EntityPotGrenade.class, "potGrenadeSL", 1, SaturaLanx.instance, 64, 10, true);
            EntityRegistry.registerGlobalEntityID(EntityShrapnel.class, "shrapnelSL", EntityRegistry.findGlobalUniqueEntityId());
            EntityRegistry.registerModEntity(EntityShrapnel.class, "shrapnelSL", 2, SaturaLanx.instance, 64, 10, true);
        }
        if (Config.enableFirecrackers) {
            EntityRegistry.registerGlobalEntityID(EntityFireCracker.class, "firecrackerSL", EntityRegistry.findGlobalUniqueEntityId());
            EntityRegistry.registerModEntity(EntityFireCracker.class, "firecrackerSL", 3, SaturaLanx.instance, 64, 10, true);
        }
        if(Config.enableSmokeBombs){
            EntityRegistry.registerGlobalEntityID(EntitySmokeBomb.class, "smokebombSL", EntityRegistry.findGlobalUniqueEntityId());
            EntityRegistry.registerModEntity(EntitySmokeBomb.class,"smokebombSL",4,SaturaLanx.instance,64,10,true);
        }
        if(Config.enableHandgonne||Config.enableArquebus||Config.enableMBlunderbuss){
            EntityRegistry.registerGlobalEntityID(EntityBullet.class,"bulletSL",EntityRegistry.findGlobalUniqueEntityId());
            EntityRegistry.registerModEntity(EntityBullet.class,"bulletSL",5,SaturaLanx.instance,64,10,true);
        }
    }

    public static void registerTileEntities(){

        SaturaLanx.log.info("Registering tile entities...");

        if(Config.enableIncendiaryPot)
            GameRegistry.registerTileEntity(FlamingBlockTE.class,"SLFlamingBlockTE");
        if(Config.enableFloorTiles)
            GameRegistry.registerTileEntity(TileTE.class,"SLFloorTilesTE");
        if(Config.enableChalices)
            GameRegistry.registerTileEntity(ChaliceTE.class,"SLChaliceBlockTE");
        if(Config.enableHypocaust)
            GameRegistry.registerTileEntity(HypocaustTE.class,"SLHypocaustTE");
    }

    @SideOnly(Side.CLIENT)
    public static void registerEntityRenders() {

        SaturaLanx.log.info("Registering entity renders...");

        if (Config.enableIncendiaryPot)
            RenderingRegistry.registerEntityRenderingHandler(EntityIncendiaryPot.class, new RenderSnowball(ItemSetup.incendiaryPot));
        if (Config.enablePotGrenades) {
            RenderingRegistry.registerEntityRenderingHandler(EntityPotGrenade.class, new RenderGrenade());
            RenderingRegistry.registerEntityRenderingHandler(EntityShrapnel.class, new RenderCustomArrow("Shrapnel"));
        }
        if (Config.enableFirecrackers)
            RenderingRegistry.registerEntityRenderingHandler(EntityFireCracker.class, new RenderSnowball(ItemSetup.firecracker));
        if(Config.enableHandgonne||Config.enableMBlunderbuss||Config.enableArquebus)
            RenderingRegistry.registerEntityRenderingHandler(EntityBullet.class,new RenderBullet());
        if(Config.enableFlameArrows)
            RenderingRegistry.registerEntityRenderingHandler(EntityFlameArrow.class,new RenderCustomArrow(new String[]{"CrudeFlameArrow","FlameArrow","FireArrow"}));
        if(Config.enablePoisonArrows)
            RenderingRegistry.registerEntityRenderingHandler(EntityPoisonArrow.class, new RenderCustomArrow("PoisonArrow"));

    }
}
