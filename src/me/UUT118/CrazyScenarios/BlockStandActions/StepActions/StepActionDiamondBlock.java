package me.UUT118.CrazyScenarios.BlockStandActions.StepActions;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import me.UUT118.CrazyScenarios.BlockStandActions.BlockStepAction;

public class StepActionDiamondBlock implements BlockStepAction {

	@Override
	public boolean checkMaterial(Material material) {
		return material == Material.DIAMOND_BLOCK;
	}

	@Override
	public void onStep(Player player, Location loc) {
		player.setHealth(20.0);
		player.setAbsorptionAmount(4.0);
	}

}
