package com.snark.saturalanx;

import com.snark.saturalanx.core.Config;
import com.snark.saturalanx.core.FoodSetup;
import com.snark.saturalanx.core.ItemSetup;
import com.snark.saturalanx.handlers.SaturaCraftingHandler;
import com.snark.saturalanx.handlers.SaturaEntityHitHandler;
import com.snark.saturalanx.handlers.SaturaFoodCraftingHandler;
import com.snark.saturalanx.handlers.SaturaWorldEventHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = com.snark.saturalanx.SaturaLanx.MODID, name = com.snark.saturalanx.SaturaLanx.MODNAME, version = com.snark.saturalanx.SaturaLanx.VERSION, dependencies = "required-after:terrafirmacraftplus;")
public class SaturaLanx
{
    public static final String MODID = "saturalanx";
    public static final String MODNAME = "Satura Lanx";
    public static final String VERSION = "0.2.2";
    public static boolean bidLoaded, kvassLoaded;
    public static CreativeTabs tab = new CreativeTabs("Satura Lanx") {
        @Override
        public Item getTabIconItem() {
            return ItemSetup.dummyBullet;
        }
    };

    @Mod.Instance("saturalanx")
    public static SaturaLanx instance;

    public static Logger log = LogManager.getLogger("SaturaLanx");

    @SidedProxy(clientSide = "com.snark.saturalanx.ClientProxy",
                serverSide = "com.snark.saturalanx.CommonProxy"
    )
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event){

        String configDir = event.getModConfigurationDirectory().toString();
        Config.init(configDir);
        proxy.preInit(event);

    }
    @EventHandler
    public void init(FMLInitializationEvent event){

        FMLCommonHandler.instance().bus().register(new SaturaFoodCraftingHandler());
        FMLCommonHandler.instance().bus().register(new SaturaCraftingHandler());
        MinecraftForge.EVENT_BUS.register(new SaturaEntityHitHandler());
        proxy.init(event);

    }
    @EventHandler
    public void postInit(FMLPostInitializationEvent event){

        MinecraftForge.EVENT_BUS.register(new SaturaWorldEventHandler());
        proxy.postInit(event);

    }
}
