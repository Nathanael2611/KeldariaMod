/**
 * Copyright 2019-2021 Keldaria. Tous droits réservés.
 * Toute reproduction, diffusion, partage, distribution,
 * commercialisation sans autorisation explicite est interdite.
 */
package uk.co.mysterymayhem.speedbasedfalldamage;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

@Mod(
        modid = SpeedBasedFallDamage.MODID,
        version = SpeedBasedFallDamage.VERSION,
        acceptedMinecraftVersions = "[1.10.2, 1.13)",
        name = "Speed Based Fall Damage",
        acceptableRemoteVersions = "[1.0.0,1.1)"
)
public class SpeedBasedFallDamage {

    static final String MODID = "speedbasedfalldamage";
    static final String VERSION = "1.0.2";

    static final SimpleNetworkWrapper NETWORK_WRAPPER = NetworkRegistry.INSTANCE.newSimpleChannel(MODID);

    @SidedProxy(clientSide = "uk.co.mysterymayhem.speedbasedfalldamage.ProxyClient", serverSide = "uk.co.mysterymayhem.speedbasedfalldamage.ProxyCommon")
    public static ProxyCommon proxy;

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onLivingFall(LivingFallEvent event) {
        proxy.processLegBreakage(event, event.getEntityLiving());
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public static void onLivingHurt(LivingHurtEvent event)
    {
        if(event.getSource() == DamageSource.FALL)
        {

            if (event.getEntityLiving() != null && event.getEntityLiving().world.isRemote && event.getEntityLiving() instanceof EntityPlayer) return;
        }
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        NETWORK_WRAPPER.registerMessage(new HiMrServerPleaseBreakMyLegsHandler(), HiMrServerPleaseBreakMyLegsPacket.class, 0, Side.SERVER);
    }

    // Register the listening methods
    @EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(SpeedBasedFallDamage.class);
    }
}
