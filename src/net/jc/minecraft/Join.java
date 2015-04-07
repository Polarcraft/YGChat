package net.jc.minecraft;

import org.bukkit.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Join implements Listener {
	
	private Main plugin;
	  
	public Join(Main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
	    if(!plugin.getConfig().getString("join-message").isEmpty()) {
		    e.setJoinMessage("");
				Player p = e.getPlayer();
				String prefix = Main.chat.getPlayerPrefix(p);
				String ChatFormat = ChatColor.translateAlternateColorCodes('&', (String)plugin.getConfig().getString("join-message").replace("%player", p.getName().toString()).replace("%prefix", prefix.toString()));
				Bukkit.broadcastMessage(ChatFormat);
			} else {
				e.setJoinMessage("");
		}
	}
}
