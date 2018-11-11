package de.haveachin.durus.events.game;

import de.haveachin.durus.Main;
import net.minecraft.world.EnumDifficulty;
import net.minecraftforge.event.DifficultyChangeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class DifficultyChange
{
	@SubscribeEvent
	public static void onDifficultyChange(DifficultyChangeEvent event) 
	{
		Main.difficulty = event.getDifficulty();
		
		Main.logger.info(new StringBuilder().append("Difficulty changed to ").append(Main.difficulty.toString()).toString());
		
		// TODO: Changing difficulty to peaceful should re-enable natural regeneration
		//if (Main.difficulty == EnumDifficulty.PEACEFUL)
		//	return;
		
		// Disable health regeneration
		//Rules.setNaturalRegeneration(w, false);
	}
}
