package me.UUT118.CrazyScenarios.BlockStandActions.StepActions;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import me.UUT118.CrazyScenarios.BlockStandActions.BlockStepAction;

public class StepActionHeal implements BlockStepAction {
	
	double power;
	Material trigger;
	
	public StepActionHeal(Material triggerMaterial, double healAmount) {
		trigger = triggerMaterial;
		power = healAmount;
	}
	
	@Override
	public boolean checkMaterial(Material material) {
		return material == trigger;
	}

	@Override
	public void onStep(Player player, Location loc) {
		player.setHealth(Math.min(player.getHealthScale(),player.getHealth()+power));
	}

}
