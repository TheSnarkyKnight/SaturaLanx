package com.snark.saturalanx.core;

import com.snark.saturalanx.SaturaLanx;
import com.snark.saturalanx.TE.FlamingBlockTE;
import com.snark.saturalanx.entities.*;
import com.snark.saturalanx.renders.entity.RenderBullet;
import com.snark.saturalanx.renders.entity.RenderGrenade;
import com.snark.saturalanx.renders.entity.RenderShrapnel;
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
        if(Config.enablePotGrenades) {
            EntityRegistry.registerGlobalEntityID(EntityPotGrenade.class, "potGrenadeSL", EntityRegistry.findGlobalUniqueEntityId());
            EntityRegistry.registerModEntity(EntityPotGrenade.class, "potGrenadeSL", 1, SaturaLanx.instance, 64, 10, true);
            EntityRegistry.registerGlobalEntityID(EntityShrapnel.class, "shrapnelSL", EntityRegistry.findGlobalUniqueEntityId());
            EntityRegistry.registerModEntity(EntityShrapnel.class, "shrapnelSL", 0, SaturaLanx.instance, 64, 10, true);
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
    }

    @SideOnly(Side.CLIENT)
    public static void registerEntityRenders() {

        SaturaLanx.log.info("Registering entity renders...");

        if (Config.enableIncendiaryPot)
            RenderingRegistry.registerEntityRenderingHandler(EntityIncendiaryPot.class, new RenderSnowball(ItemSetup.incendiaryPot));
        if (Config.enablePotGrenades) {
            RenderingRegistry.registerEntityRenderingHandler(EntityPotGrenade.class, new RenderGrenade());
            RenderingRegistry.registerEntityRenderingHandler(EntityShrapnel.class, new RenderShrapnel());
        }
        if (Config.enableFirecrackers)
            RenderingRegistry.registerEntityRenderingHandler(EntityFireCracker.class, new RenderSnowball(ItemSetup.firecracker));
        if(Config.enableHandgonne||Config.enableMBlunderbuss||Config.enableArquebus)
            RenderingRegistry.registerEntityRenderingHandler(EntityBullet.class,new RenderBullet());


    }
}
