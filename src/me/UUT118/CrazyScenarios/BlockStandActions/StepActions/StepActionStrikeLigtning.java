package me.UUT118.CrazyScenarios.BlockStandActions.StepActions;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import me.UUT118.CrazyScenarios.BlockStandActions.BlockStepAction;

public class StepActionStrikeLigtning implements BlockStepAction {

	Material trigger;
	
	public StepActionStrikeLigtning(Material triggerMaterial) {
		trigger = triggerMaterial;
	}
	
	@Override
	public boolean checkMaterial(Material material) {
		return material == trigger;
	}

	@Override
	public void onStep(Player player, Location loc) {
		player.getWorld().strikeLightning(loc);
	}

}
