package me.UUT118.CrazyScenarios.BlockStandActions.StepActions;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.UUT118.CrazyScenarios.BlockStandActions.BlockStepAction;

public class StepActionPotionEffect implements BlockStepAction {
	private Material trigger;
	private PotionEffectType potionType;
	private int potionDuration;
	private int potionAmplifier;

	public StepActionPotionEffect(Material material, PotionEffectType type, int tickDuration, int amplifier) {
		trigger = material;
		potionType = type;
		potionDuration = tickDuration;
		potionAmplifier = amplifier;
	}
	public StepActionPotionEffect(Material material, PotionEffectType type, int tickDuration) {
		trigger = material;
		potionType = type;
		potionDuration = tickDuration;
		potionAmplifier = 0;
	}
	
	@Override
	public boolean checkMaterial(Material material) {
		return material == trigger;
	}

	@Override
	public void onStep(Player player, Location loc) {
		player.addPotionEffect(new PotionEffect(potionType,potionDuration,potionAmplifier));
	}

}
