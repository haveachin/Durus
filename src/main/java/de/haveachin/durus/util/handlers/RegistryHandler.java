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
