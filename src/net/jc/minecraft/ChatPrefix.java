package net.jc.minecraft;

import org.bukkit.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatPrefix implements Listener {
    
	  private Main plugin;
	  
	  public ChatPrefix(Main plugin) {
	    this.plugin = plugin;
	  }
	@EventHandler(priority = EventPriority.NORMAL)
    public void onPLayerChatEvent(AsyncPlayerChatEvent e) {
		e.setCancelled(true);
		Player p = e.getPlayer();
		String message = e.getMessage();
		String prefix = Main.chat.getPlayerPrefix(p);
		String empty = null;
		if(Main.chat.getPlayerPrefix(p).isEmpty()) {
			String ChatFormat2 = ChatColor.translateAlternateColorCodes('&', (String)plugin.getConfig().getString("chat-format").replace("%player", p.getName().toString()).replace("%message", message.toString()).replace("%prefix", empty) + ChatColor.RESET);
			Bukkit.broadcastMessage(ChatFormat2);
		} else {
			String ChatFormat = ChatColor.translateAlternateColorCodes('&', (String)plugin.getConfig().getString("chat-format").replace("%player", p.getDisplayName().toString()).replace("%message", message.toString()).replace("%prefix", prefix.toString()));
		//Bukkit.broadcastMessage(ChatFormat);
			Bukkit.broadcastMessage(ChatFormat);
		}
	}
}
