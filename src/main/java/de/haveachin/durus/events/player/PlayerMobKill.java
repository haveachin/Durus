package de.haveachin.durus.events.player;

import java.util.concurrent.ThreadLocalRandom;

import de.haveachin.durus.Main;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class PlayerMobKill
{
	@SubscribeEvent
	public static void onPlayerMobKill(LivingDeathEvent event)
	{		
		DamageSource dmgSource = event.getSource();
		
		if (dmgSource == null)
			return;
		
		Entity trueSource = dmgSource.getTrueSource();
		
		if (trueSource == null || !(trueSource instanceof EntityPlayer))
			return;
		
		EntityPlayer p = (EntityPlayer)trueSource;
		
		World w = p.getEntityWorld();
		
		if (w == null || w.isRemote)
			return;
		
		Entity e = event.getEntity();
		
		if (e == null || !e.isCreatureType(EnumCreatureType.MONSTER, false))
			return;
		
		switch (Main.difficulty) {
		case EASY:
			p.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 200, 0));
			break;
		case NORMAL:
			p.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 160, 0));
			break;
		case HARD:
			p.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 100, 0));
			break;
		default:
			return;
		}
		
		if (ThreadLocalRandom.current().nextInt(0, 4) == 0) // 25% Chance
			p.addPotionEffect(new PotionEffect(MobEffects.MINING_FATIGUE, 60, 0));
	}
}
