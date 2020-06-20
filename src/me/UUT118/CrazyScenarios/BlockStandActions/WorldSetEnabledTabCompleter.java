package me.UUT118.CrazyScenarios.BlockStandActions;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class WorldSetEnabledTabCompleter implements TabCompleter {
	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		List<String> list = new ArrayList<String>();
		if (args.length > 1) return list;
		int idx = args.length-1;
		String currentText = args[idx];
		for (String test : new String[]{"true","false"})
			if (test.startsWith(currentText)) list.add(test);
		return list;
	}
}
