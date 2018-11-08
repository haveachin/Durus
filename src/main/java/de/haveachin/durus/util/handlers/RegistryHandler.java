package de.haveachin.durus.util.handlers;

import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;

@EventBusSubscriber
public class RegistryHandler
{
	@SubscribeEvent
	public void onEntityJoinWorld(EntityJoinWorldEvent event)
	{
		World w = event.getEntity().getEntityWorld();
		
		if (w == null)
		{
			System.out.println("The world is null!");
			return;
		}
		
		String log = new StringBuilder()
				.append("World difficulty: ")
				.append(w.getDifficulty().toString())
				.toString();
		
		System.out.println(log);
	}
}
