package me.UUT118.CrazyScenarios.BlockStandActions;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public interface BlockStepAction {
	public boolean checkMaterial(Material material);
	public void onStep(Player player, Location loc);
}
