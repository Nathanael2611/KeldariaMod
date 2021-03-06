/**
 * Copyright 2019-2021 Keldaria. Tous droits réservés.
 * Toute reproduction, diffusion, partage, distribution,
 * commercialisation sans autorisation explicite est interdite.
 */
package de.mennomax.astikorcarts.config;

import de.mennomax.astikorcarts.AstikorCarts;
import fr.nathanael2611.keldaria.mod.Keldaria;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.LangKey;
import net.minecraftforge.common.config.Config.RangeDouble;
import net.minecraftforge.common.config.Config.RequiresMcRestart;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = AstikorCarts.MODID)
public class ModConfig
{
    @LangKey("config.astikorcarts:speedmodifier")
    @RequiresMcRestart
    @RangeDouble(min = -1.0D, max = 0.0D)
    public static double speedModifier = -0.5D;
    
    @LangKey("entity.astikorcarts:cargocart.name")
    public static CargoCart cargoCart = new CargoCart();

    @LangKey("entity.astikorcarts:plowcart.name")
    public static PlowCart plowCart = new PlowCart();
    
    @LangKey("entity.astikorcarts:mobcart.name")
    public static MobCart mobCart = new MobCart();

    public static class CargoCart
    {
        public String[] canPull = {
                "minecraft:horse",
                "minecraft:donkey",
                "minecraft:mule",
                "minecraft:pig"
        };
    }

    public static class PlowCart
    {
        public String[] canPull = {
                "minecraft:horse",
                "minecraft:donkey",
                "minecraft:mule",
                "minecraft:pig"
        };
    }
    
    public static class MobCart
    {
        public String[] canPull = {
                "minecraft:horse",
                "minecraft:donkey",
                "minecraft:mule",
                "minecraft:pig"
        };
    }

    @Mod.EventBusSubscriber(modid = Keldaria.MOD_ID)
    private static class EventHandler
    {
        @SubscribeEvent
        public static void onConfigChanged(final ConfigChangedEvent.OnConfigChangedEvent event)
        {
            if (event.getModID().equals(AstikorCarts.MODID))
            {
                ConfigManager.sync(AstikorCarts.MODID, Config.Type.INSTANCE);
            }
        }
    }
}
