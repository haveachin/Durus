package de.haveachin.durus.events.game;

import net.minecraft.world.GameRules;
import net.minecraft.world.World;

public class Rules
{
	public static void setNaturalRegeneration(World w, boolean value)
	{
		GameRules gameRules = w.getGameRules();
		
		if (gameRules == null || !gameRules.getBoolean("naturalRegeneration"))
			return;
		
		gameRules.setOrCreateGameRule("naturalRegeneration", value ? "true" : "false");
	}
}
