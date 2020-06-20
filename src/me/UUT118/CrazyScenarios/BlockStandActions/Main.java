package me.UUT118.CrazyScenarios.BlockStandActions;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffectType;

import me.UUT118.CrazyScenarios.BlockStandActions.StepActions.StepActionDamage;
import me.UUT118.CrazyScenarios.BlockStandActions.StepActions.StepActionDiamondBlock;
import me.UUT118.CrazyScenarios.BlockStandActions.StepActions.StepActionExplode;
import me.UUT118.CrazyScenarios.BlockStandActions.StepActions.StepActionFire;
import me.UUT118.CrazyScenarios.BlockStandActions.StepActions.StepActionHeal;
import me.UUT118.CrazyScenarios.BlockStandActions.StepActions.StepActionPotionEffect;
import me.UUT118.CrazyScenarios.BlockStandActions.StepActions.StepActionRandomTeleport;
import me.UUT118.CrazyScenarios.BlockStandActions.StepActions.StepActionSpawnEntity;
import me.UUT118.CrazyScenarios.BlockStandActions.StepActions.StepActionStrikeLigtning;

public class Main extends JavaPlugin implements Listener {
	private ArrayList<String> worlds = new ArrayList<String>();
	private ArrayList<BlockStepAction> stepActions = new ArrayList<BlockStepAction>();
	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(new PluginEvents(this), this);
		saveDefaultConfig();
		worlds = (ArrayList<String>) getConfig().getStringList("blockstandactions.worlds");
		getLogger().log(Level.INFO, "Enabled In: "+worlds.toString());
		getServer().getPluginCommand("setenabledinworld").setPermission("blockstandactions.commands.setenabledinworld.use");
		getServer().getPluginCommand("setenabledinworld").setExecutor(new WorldSetEnabledCommand(this));
		getServer().getPluginCommand("setenabledinworld").setTabCompleter(new WorldSetEnabledTabCompleter());
		registerBlockStepAction(new StepActionDiamondBlock());
		registerBlockStepAction(new StepActionPotionEffect(Material.DIORITE, PotionEffectType.POISON, 120, 1));
		registerBlockStepAction(new StepActionExplode(Material.GRANITE, 2f));
		registerBlockStepAction(new StepActionExplode(Material.ACACIA_LEAVES, 80f));
		registerBlockStepAction(new StepActionRandomTeleport(Material.OAK_LOG, 30));
		registerBlockStepAction(new StepActionFire(Material.PUMPKIN, 400));
		registerBlockStepAction(new StepActionDamage(Material.IRON_ORE, 3.0));
		registerBlockStepAction(new StepActionDamage(Material.GOLD_ORE, 13.0));
		registerBlockStepAction(new StepActionFire(Material.COAL_ORE, 40));
		registerBlockStepAction(new StepActionHeal(Material.DARK_OAK_PLANKS, 2.0));
		registerBlockStepAction(new StepActionHeal(Material.BIRCH_PLANKS, 5.0));
		registerBlockStepAction(new StepActionHeal(Material.JUNGLE_PLANKS, 3.0));
		registerBlockStepAction(new StepActionHeal(Material.OAK_PLANKS, 2.0));
		registerBlockStepAction(new StepActionHeal(Material.SPRUCE_PLANKS, 1.0));
		registerBlockStepAction(new StepActionStrikeLigtning(Material.COBBLESTONE));
		registerBlockStepAction(new StepActionPotionEffect(Material.COBBLESTONE, PotionEffectType.SATURATION, 1, 20));
		registerBlockStepAction(new StepActionPotionEffect(Material.GRAVEL, PotionEffectType.LEVITATION, 80, 2));
		registerBlockStepAction(new StepActionSpawnEntity(Material.REDSTONE_ORE, EntityType.ZOMBIE));
		registerBlockStepAction(new StepActionSpawnEntity(Material.DIAMOND_ORE, EntityType.MINECART));
		registerBlockStepAction(new StepActionSpawnEntity(Material.NETHER_BRICKS, EntityType.BLAZE));
		registerBlockStepAction(new StepActionPotionEffect(Material.MAGMA_BLOCK, PotionEffectType.FIRE_RESISTANCE,200));
		registerBlockStepAction(new StepActionPotionEffect(Material.SAND, PotionEffectType.SPEED,80,4));
		registerBlockStepAction(new StepActionRandomTeleport(Material.OBSIDIAN, 5));
		// brown glazed terracotta = overkill
		registerBlockStepAction(new StepActionPotionEffect(Material.BROWN_GLAZED_TERRACOTTA, PotionEffectType.LEVITATION,80,127));
		registerBlockStepAction(new StepActionPotionEffect(Material.BROWN_GLAZED_TERRACOTTA, PotionEffectType.NIGHT_VISION,80));
		registerBlockStepAction(new StepActionPotionEffect(Material.BROWN_GLAZED_TERRACOTTA, PotionEffectType.BLINDNESS,80));
		registerBlockStepAction(new StepActionSpawnEntity(Material.BROWN_GLAZED_TERRACOTTA, EntityType.WITHER));
		registerBlockStepAction(new StepActionStrikeLigtning(Material.BROWN_GLAZED_TERRACOTTA));
		registerBlockStepAction(new StepActionFire(Material.BROWN_GLAZED_TERRACOTTA, 20));
		registerBlockStepAction(new StepActionExplode(Material.BROWN_GLAZED_TERRACOTTA, 20));
		
	}
	@Override
	public void onDisable() {
		saveConfig();
	}
	public void registerBlockStepAction(BlockStepAction action) {
		if (!stepActions.contains(action)) stepActions.add(action);
	}
	public boolean worldAllowed(String worldName) {
		if (worldName == null) return false;
		for (String world : worlds) if (worldName.equalsIgnoreCase(world)) return true;
		return false;
	}
	public World[] getWorldsEnabledIn() {
		World[] worldObjs = new World[worlds.size()];
		for (int i = 0; i < worlds.size(); i++) worldObjs[i] = getServer().getWorld(worlds.get(i));
		return worldObjs;
	}
	public void doStepOnNewBlock(Material mat, Player plr, Location loc) {
		for (BlockStepAction action : stepActions) if (action.checkMaterial(mat)) 
			action.onStep(plr, loc);
	}
	public void setWorldEnabled(String world, boolean enabled) {
		if (enabled && !worlds.contains(world)) worlds.add(world);
		if (!enabled && worlds.contains(world)) worlds.remove(world);
		getConfig().set("blockstandactions.worlds", (List<String>) worlds);
	}
}
