package net.yougold.org.YGChat;

import net.milkbowl.vault.chat.Chat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	public static Chat chat = null;

	public void onEnable() {
		getConfig().options().copyDefaults(false);
		saveDefaultConfig();
		getCommand("ygreload").setExecutor(new Reload(this));
		getCommand("mute").setExecutor(new Mute(this));
		getServer().getPluginManager().registerEvents(new ChatPrefix(this), this);
		getServer().getPluginManager().registerEvents(new Leave(this), this);
		getServer().getPluginManager().registerEvents(new Join(this), this);
		getServer().getPluginManager().registerEvents(new MuteChecks(this), this);
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			public void run() {
				for (Player p : Bukkit.getOnlinePlayers()) {
					String prefix = Main.chat.getPlayerPrefix(p);
					String Tab2Format = ChatColor.translateAlternateColorCodes('&', Main.this.getConfig().getString("PlayerList").replace("%player", p.getName().toString()).replace("%prefix", prefix.toString()).replace("%nickname", p.getDisplayName().toString()) + ChatColor.RESET);
					p.setPlayerListName(Tab2Format);
				}
			}
		}
		, 20L, 30L);
		setupChat();
	}
	
	private boolean setupChat() {
		@SuppressWarnings("rawtypes")
		RegisteredServiceProvider rsp = getServer().getServicesManager().getRegistration(Chat.class);
		chat = (Chat)rsp.getProvider();
		return chat != null;
	}
}
