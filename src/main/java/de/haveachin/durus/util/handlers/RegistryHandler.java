package de.haveachin.durus.util.handlers;

import net.minecraft.world.World;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class RegistryHandler
{
	@SubscribeEvent
	public static void onWorldLoad(WorldEvent.Load event)
	{
		World w = event.getWorld();
		
		String log = new StringBuilder()
				.append("World difficulty: ")
				.append(w.getDifficulty().toString())
				.toString();
		
		System.console().writer().println(log);
	}
}

/*
	Error:
	
	java.lang.NullPointerException: Exception in server tick loop
			at de.haveachin.durus.util.handlers.RegistryHandler.onWorldLoad(RegistryHandler.java:21)
			at net.minecraftforge.fml.common.eventhandler.ASMEventHandler_4_RegistryHandler_onWorldLoad_Load.invoke(.dynamic)
			at net.minecraftforge.fml.common.eventhandler.ASMEventHandler.invoke(ASMEventHandler.java:90)
			at net.minecraftforge.fml.common.eventhandler.EventBus.post(EventBus.java:182)
			at net.minecraft.server.integrated.IntegratedServer.loadAllWorlds(IntegratedServer.java:133)
			at net.minecraft.server.integrated.IntegratedServer.init(IntegratedServer.java:160)
			at net.minecraft.server.MinecraftServer.run(MinecraftServer.java:552)
			at java.lang.Thread.run(Unknown Source)
*/