package net.jc.minecraft;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Reload implements CommandExecutor {

	  private Main plugin;
	  
	  public Reload(Main plugin) {
	    this.plugin = plugin;
	  }
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
	{
		if(cmd.getName().equalsIgnoreCase("ygreload")) {
			if(sender.hasPermission("yg.reload")) {
				plugin.reloadConfig();
	            sender.sendMessage(ChatColor.GREEN.toString()+ ChatColor.BOLD + " Has Been Reloaded From Disk Space!");
			}
		}
		return false;
	}
	
}

