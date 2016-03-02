package net.yougold.org.YGChat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Join implements Listener {
	private Main plugin;

	public Join(Main plugin)
	{
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		if (!this.plugin.getConfig().getString("join-message").isEmpty() && (!this.plugin.getConfig().getString("first-join-message").isEmpty())) {
			if(!e.getPlayer().hasPlayedBefore()) {
				e.setJoinMessage("");
				Player p = e.getPlayer();
				String prefix = Main.chat.getPlayerPrefix(p);
				String suffix = Main.chat.getPlayerSuffix(p);
				String world = p.getWorld().getName();
				int current = plugin.getConfig().getInt("playerjoincount");
				int toAdd = 1;
				String ChatFormat = ChatColor.translateAlternateColorCodes('&', this.plugin.getConfig().getString("first-join-message").replace("%player", p.getDisplayName().toString()).replace("%prefix", prefix.toString()).replace("%suffix", suffix.toString()).replace("%world", world));
				Bukkit.broadcastMessage(ChatFormat);	
				plugin.getConfig().set("playerjoincount", current + toAdd);
				plugin.saveConfig();
				String joined = ChatColor.translateAlternateColorCodes('&', this.plugin.getConfig().getString("players-joined-message").replace("%joincount", Integer.toString(current + 1)));
				Bukkit.broadcastMessage(joined);
			} else if(e.getPlayer().hasPlayedBefore()) {
				e.setJoinMessage("");
				Player p = e.getPlayer();
				String prefix = Main.chat.getPlayerPrefix(p);
				String ChatFormat = ChatColor.translateAlternateColorCodes('&', this.plugin.getConfig().getString("join-message").replace("%player", p.getDisplayName().toString()).replace("%prefix", prefix.toString()));
				Bukkit.broadcastMessage(ChatFormat);
			}
		}
	}
}
