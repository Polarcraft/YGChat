package net.yougold.org.YGChat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

public class Mute implements CommandExecutor {

	private Main plugin;
	  
	public Mute(Main plugin) {
		this.plugin = plugin;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
	{
		if (cmd.getName().equalsIgnoreCase("mute")) {
			if(!sender.hasPermission("yg.mute")) {
				sender.sendMessage(ChatColor.DARK_RED + "ERROR:" + " You are unable to mute players!");
			} else if(sender.hasPermission("yg.mute")) {
				if(args.length == 0) {
					sender.sendMessage(ChatColor.DARK_RED + "ERROR: " + " Invalid args try: /mute <players>");
				} else if(!(args.length == 0)) {
					Player t = (Bukkit.getServer().getPlayer(args[0]));
					if (t == null) {
						sender.sendMessage(args[0] + " is not online!");
						return true;
					} else {
						if(!t.hasMetadata("muted")) {
							t.setMetadata("muted", new FixedMetadataValue(plugin, this));
							sender.sendMessage(ChatColor.YELLOW + t.getDisplayName() + " has been muted!");
						} else if(t.hasMetadata("muted")) {
							t.removeMetadata("muted", plugin);
							sender.sendMessage(ChatColor.YELLOW + t.getDisplayName() + " has been unmuted!");
						}
					}
				}
			}
		}
		return false;
	}
}
