package me.UUT118.CrazyScenarios.BlockStandActions.StepActions;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import me.UUT118.CrazyScenarios.BlockStandActions.BlockStepAction;

public class StepActionExplode implements BlockStepAction {
	
	float power;
	Material trigger;
	
	public StepActionExplode(Material triggerMaterial, float explosionPower) {
		trigger = triggerMaterial;
		power = explosionPower;
	}
	
	@Override
	public boolean checkMaterial(Material material) {
		return material == trigger;
	}

	@Override
	public void onStep(Player player, Location loc) {
		Entity entity = player.getWorld().spawnEntity(new Location(player.getWorld(), 0, -10000, 0), EntityType.BAT);
		entity.setCustomName(trigger.toString());
		loc.clone().add(0, -1, 0).getBlock().breakNaturally();
		player.getWorld().createExplosion(loc.clone().add(0, -1, 0).getBlock().getLocation().add(loc.getBlock().getBoundingBox().getCenter()),power,false,true,entity);
	}

}
