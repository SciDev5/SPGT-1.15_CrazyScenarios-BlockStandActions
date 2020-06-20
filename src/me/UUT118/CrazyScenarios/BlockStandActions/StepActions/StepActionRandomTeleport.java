package me.UUT118.CrazyScenarios.BlockStandActions.StepActions;

import java.util.ArrayList;
import java.util.Random;
import java.util.function.Predicate;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;

import me.UUT118.CrazyScenarios.BlockStandActions.BlockStepAction;

public class StepActionRandomTeleport implements BlockStepAction {

	Random random;
	int distance;
	Material trigger;
	
	public StepActionRandomTeleport(Material triggerMaterial, int teleportDistance) {
		trigger = triggerMaterial;
		distance = teleportDistance;
		random = new Random();
	}
	
	@Override
	public boolean checkMaterial(Material material) {
		return material == trigger;
	}

	@Override
	public void onStep(Player player, Location loc) {
		Predicate<Location> locationSafe = (Location l) -> {
			return l.clone().add(0, -1, 0).getBlock().getBoundingBox().contains(l.getBlockX()+0.5, l.getBlockY()-0.01, l.getBlockZ()+0.5) && l.clone().add(0, 0, 0).getBlock().isPassable() && l.clone().add(0, 1, 0).getBlock().isPassable();
		};
		ArrayList<Location> safeDestinations = new ArrayList<Location>();
		for (int x = -distance; x <= distance; x++)
			for (int y = -distance; y <= distance; y++)
				for (int z = -distance; z <= distance; z++)
					if (locationSafe.test(loc.clone().add(x, y, z)))
						safeDestinations.add(new Location(loc.getWorld(),loc.getBlockX()+x+0.5,loc.getBlockY()+y+0.0,loc.getBlockZ()+z+0.5,loc.getYaw(),loc.getPitch()));
		if (safeDestinations.size() > 0)
			player.teleport(safeDestinations.get(random.nextInt(safeDestinations.size())),TeleportCause.ENDER_PEARL);
		else 
			player.teleport(loc.add(random.nextInt(distance*2+1)-distance,random.nextInt(distance*2+1)-distance,random.nextInt(distance*2+1)-distance),TeleportCause.PLUGIN);
		player.getWorld().playSound(loc, Sound.ENTITY_ENDERMAN_TELEPORT, 0.3f, 0.9f+random.nextFloat()*0.4f);
	}

}
