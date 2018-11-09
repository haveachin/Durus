package de.haveachin.durus.events.game;

import de.haveachin.durus.Main;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class WorldLoad
{
	@SubscribeEvent
	public static void onWorldLoad(WorldEvent.Load event)
	{
		// Get difficulty
		World w = event.getWorld();
		
		if (w == null || w.isRemote)
			return;
		
		Main.difficulty = w.getDifficulty();
		
		Main.logger.info(new StringBuilder().append("Playing in ").append(Main.difficulty.toString()).toString());
		
		// Disable health regeneration
		GameRules gameRules = w.getGameRules();
		
		if (gameRules == null || !gameRules.getBoolean("naturalRegeneration"))
			return;
		
		gameRules.setOrCreateGameRule("naturalRegeneration", "false");
	}
}
