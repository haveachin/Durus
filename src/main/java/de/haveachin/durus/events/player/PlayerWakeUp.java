package de.haveachin.durus.events.player;

import de.haveachin.durus.Main;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerWakeUpEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class PlayerWakeUp
{
	@SubscribeEvent
	public static void onPlayerWakeUp(PlayerWakeUpEvent event)
	{
		EntityPlayer p = event.getEntityPlayer();
		
		if (p == null)
			return;
		
		World w = p.getEntityWorld();
		
		if (w == null || w.isRemote)
			return;
		
		// TODO: Add slept thru night check
		
		switch(Main.difficulty)
		{
		case EASY:
			p.addPotionEffect(new PotionEffect(MobEffects.INSTANT_HEALTH, 1, 1));
			p.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 3600, 0));
			p.addPotionEffect(new PotionEffect(MobEffects.HUNGER, 200, 0));
			break;
		case NORMAL:
			p.addPotionEffect(new PotionEffect(MobEffects.INSTANT_HEALTH, 1, 1));
			p.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 3600, 0));
			p.addPotionEffect(new PotionEffect(MobEffects.HUNGER, 400, 0));
			p.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 200, 0));
			break;
		case HARD:
			p.addPotionEffect(new PotionEffect(MobEffects.INSTANT_HEALTH, 1, 0));
			p.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 3600, 0));
			p.addPotionEffect(new PotionEffect(MobEffects.HUNGER, 600, 0));
			p.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 300, 0));
			p.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 300, 0));
			break;
		default:
			break;
		}
	}
}
