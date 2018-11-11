package de.haveachin.durus.events.player;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import de.haveachin.durus.Main;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class PlayerRunning
{
	private static Map<EntityPlayer, Integer> playerSprintTimes = new HashMap<EntityPlayer, Integer>();
	
	@SubscribeEvent
	public static void onPlayerRunning(LivingUpdateEvent event)
	{
		EntityLivingBase e = event.getEntityLiving();
		
		if (e == null || !(e instanceof EntityPlayer))
			return;
		
		EntityPlayer p = (EntityPlayer)e;
		
		World w = p.getEntityWorld();
		
		if (w == null || w.isRemote)
			return;
		
		if (!p.isSprinting())
		{
			if (playerSprintTimes.containsKey(p))
				playerSprintTimes.remove(p);
			
			return;
		}
		
		if (!playerSprintTimes.containsKey(p))
			playerSprintTimes.put(p, 0);
		
		int sprintTime = playerSprintTimes.get(p) + 1;
		
		playerSprintTimes.put(p, sprintTime);
		
		if (sprintTime < 400)
			return;
		
		if (ThreadLocalRandom.current().nextInt(0, 190) != 0) // 10% Chance in one second to get a MobEffect
			return;
		
		p.setSprinting(false);
		
		switch(Main.difficulty)
		{
		case NORMAL:
			if (ThreadLocalRandom.current().nextInt(0, 2) != 0)
				p.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 100, 0, false, false));
			else
				p.addPotionEffect(new PotionEffect(MobEffects.HUNGER, 100, 0, false, false));
			break;
		case HARD:
			if (ThreadLocalRandom.current().nextInt(0, 2) != 0)
				p.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 100, 1, false, false));
			else
				p.addPotionEffect(new PotionEffect(MobEffects.HUNGER, 100, 1, false, false));
			break;
		default:
			break;
		}
	}
}
