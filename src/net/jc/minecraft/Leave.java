package net.jc.minecraft;

import org.bukkit.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class Leave implements Listener {

	  private Main plugin;
	  
	  public Leave(Main plugin) {
	    this.plugin = plugin;
	  }
	  @EventHandler
	  public void onJoin(PlayerQuitEvent e) {
		  e.setQuitMessage("");
		  Player p = e.getPlayer();
		  String prefix = Main.chat.getPlayerPrefix(p);
		  String ChatFormat = ChatColor.translateAlternateColorCodes('&', (String)plugin.getConfig().getString("quit-message").replace("%player", p.getName().toString()).replace("%prefix", prefix.toString()));
		  Bukkit.broadcastMessage(ChatFormat);
	}
}
