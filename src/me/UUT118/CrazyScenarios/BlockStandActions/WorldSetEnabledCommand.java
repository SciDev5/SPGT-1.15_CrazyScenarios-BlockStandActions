package me.UUT118.CrazyScenarios.BlockStandActions;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WorldSetEnabledCommand implements CommandExecutor {

	private Main plugin;
	public WorldSetEnabledCommand(Main plugin) {
		this.plugin = plugin;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command command, String commandName, String[] args) {
		if (sender instanceof Player) {
			if (args.length != 1) 
				sender.sendMessage("§cInvalid arguments, use: /"+commandName+" <true/false>");
			else if (!args[0].equals("true") && !args[0].equals("false"))
				sender.sendMessage("§cInvalid arguments, use: /"+commandName+" <true/false>");
			else {
				boolean enabled = args[0].equals("true");
				Player plr = (Player) sender;
				String world = plr.getWorld().getName();
				plugin.setWorldEnabled(world,enabled);
				if (enabled) sender.sendMessage("§aEnabled plugin in world \""+world+"\".");
				else sender.sendMessage("§aDisabled plugin in world \""+world+"\".");
			}
		} else sender.sendMessage("§cOnly players can execute this command!");
		return true;
	}

}
