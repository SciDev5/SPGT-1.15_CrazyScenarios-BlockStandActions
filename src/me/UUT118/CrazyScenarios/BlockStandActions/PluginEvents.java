package me.UUT118.CrazyScenarios.BlockStandActions;

import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
 
public class PluginEvents implements Listener {
	private Main plugin;
	public PluginEvents(Main plugin) {
		this.plugin = plugin;
	}
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) {
		if (!plugin.worldAllowed(e.getPlayer().getWorld().getName())) return;
		double dsq = e.getFrom().getBlock().getLocation().distanceSquared(e.getTo().getBlock().getLocation());
		if (dsq < 0.01) return;
		Material mat = e.getTo().clone().add(0, -1, 0).getBlock().getType();
		plugin.doStepOnNewBlock(mat, e.getPlayer(), e.getTo());
	}
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e) {
		if (!plugin.worldAllowed(e.getPlayer().getWorld().getName())) return;
		for (Player p : e.getPlayer().getWorld().getPlayers())
			if (p.getLocation().getBlockX() == e.getBlock().getX() && p.getLocation().getBlockZ() == e.getBlock().getZ() && p.getLocation().getBlockY() - 1 == e.getBlock().getY()) {
				Material mat = p.getLocation().clone().add(0, -1, 0).getBlock().getType();
				plugin.doStepOnNewBlock(mat, p, p.getLocation());
			}
	}
	@EventHandler
	public void onBlockBroken(BlockBreakEvent e) {
		if (!plugin.worldAllowed(e.getPlayer().getWorld().getName())) return;
		for (Player p : e.getPlayer().getWorld().getPlayers())
			if (p.getLocation().getBlockX() == e.getBlock().getX() && p.getLocation().getBlockZ() == e.getBlock().getZ() && p.getLocation().getBlockY() - 1 == e.getBlock().getY()) {
				plugin.doStepOnNewBlock(Material.AIR, p, p.getLocation());
			}
	}
}
