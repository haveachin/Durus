package de.haveachin.durus.events.player;

import de.haveachin.durus.Main;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class PlayerEatEvent
{
	@SubscribeEvent
	public static void onPlayerEat(LivingEntityUseItemEvent.Finish event)
	{		
		EntityLivingBase e = event.getEntityLiving();
		
		if (e == null || !(e instanceof EntityPlayer))
			return;
		
		EntityPlayer p = (EntityPlayer)e;
		
		World w = p.getEntityWorld();
		
		if (w == null || w.isRemote)
			return;
		
		ItemStack item = event.getItem();
		
		if (item == null || item.getItemUseAction() != EnumAction.EAT || !(item.getItem() instanceof ItemFood))
			return;
		
		ItemFood food = (ItemFood)item.getItem();
		
		int healAmount = food.getHealAmount(item);
			
		switch (Main.difficulty) {
		case EASY:
			p.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 50 * healAmount, 0, false, false));
			break;
		case NORMAL:
			p.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 50 * healAmount / 2, 0, false, false));
			break;
		default:
			return;
		}
	}
}
