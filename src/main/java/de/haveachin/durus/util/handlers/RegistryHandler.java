package de.haveachin.durus.util.handlers;

import de.haveachin.durus.Main;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class RegistryHandler
{
	@SubscribeEvent
	public static void onEntityJoinWorld(EntityJoinWorldEvent event)
	{
		Entity entity = event.getEntity();
		
		if (entity == null || !(entity instanceof EntityPlayer))
			return;
		
		World w = entity.getEntityWorld();
		
		if (w == null || w.isRemote)
			return;
		
		String log = new StringBuilder()
				.append("World difficulty: ")
				.append(w.getDifficulty().toString())
				.toString();
		
		Main.logger.info(log);
	}
	
	@SubscribeEvent
	public static void onSettingsChanged(GuiScreenEvent.ActionPerformedEvent.Post event)
	{
		GuiScreen gui = event.getGui();
		
		if (gui == null || !(gui instanceof GuiOptions))
			return;
		
		GuiButton button = event.getButton();
		
		if (button == null || button.id != 108) // 108; difficulty button ID
			return;
		
		// *insert world reference here*
		
		Main.logger.info("Difficulty changed");
	}
}
