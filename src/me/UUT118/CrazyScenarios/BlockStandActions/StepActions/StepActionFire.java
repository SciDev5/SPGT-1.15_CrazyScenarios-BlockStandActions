package me.UUT118.CrazyScenarios.BlockStandActions.StepActions;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import me.UUT118.CrazyScenarios.BlockStandActions.BlockStepAction;

public class StepActionFire implements BlockStepAction {
	
	int power;
	Material trigger;
	
	public StepActionFire(Material triggerMaterial, int fireTicks) {
		trigger = triggerMaterial;
		power = fireTicks;
	}
	
	@Override
	public boolean checkMaterial(Material material) {
		return material == trigger;
	}

	@Override
	public void onStep(Player player, Location loc) {
		Entity entity = player.getWorld().spawnEntity(new Location(player.getWorld(), 0, -10000, 0), EntityType.BAT);
		entity.setCustomName(trigger.toString());
		player.damage(0, entity);
		player.setFireTicks(power);
	}

}
