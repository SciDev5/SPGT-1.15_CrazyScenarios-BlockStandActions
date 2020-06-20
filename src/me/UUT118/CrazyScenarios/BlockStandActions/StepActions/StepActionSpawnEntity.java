package me.UUT118.CrazyScenarios.BlockStandActions.StepActions;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import me.UUT118.CrazyScenarios.BlockStandActions.BlockStepAction;

public class StepActionSpawnEntity implements BlockStepAction {
	
	EntityType type;
	Material trigger;
	
	public StepActionSpawnEntity(Material triggerMaterial, EntityType entityType) {
		trigger = triggerMaterial;
		type = entityType;
	}
	
	@Override
	public boolean checkMaterial(Material material) {
		return material == trigger;
	}

	@Override
	public void onStep(Player player, Location loc) {
		player.getWorld().spawnEntity(loc, type);
	}

}
