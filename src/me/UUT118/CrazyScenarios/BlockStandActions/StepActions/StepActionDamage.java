package me.UUT118.CrazyScenarios.BlockStandActions.StepActions;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import me.UUT118.CrazyScenarios.BlockStandActions.BlockStepAction;

public class StepActionDamage implements BlockStepAction {
	
	double power;
	Material trigger;
	
	public StepActionDamage(Material triggerMaterial, double damage) {
		trigger = triggerMaterial;
		power = damage;
	}
	
	@Override
	public boolean checkMaterial(Material material) {
		return material == trigger;
	}

	@Override
	public void onStep(Player player, Location loc) {
		Entity entity = player.getWorld().spawnEntity(new Location(player.getWorld(), loc.getX(), -10000, loc.getZ()), EntityType.BAT);
		entity.setCustomName(trigger.toString());
		player.damage(power, entity);
	}

}
