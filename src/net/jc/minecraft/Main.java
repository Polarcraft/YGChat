package net.jc.minecraft;

import net.milkbowl.vault.chat.Chat;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.ChatColor;

public class Main extends JavaPlugin {
	
    public static Chat chat = null;
    
	public void onEnable() {
	    getConfig().options().copyDefaults(true);
	    saveDefaultConfig();
		getCommand("ygreload").setExecutor(new Reload(this));
		getServer().getPluginManager().registerEvents(new ChatPrefix(this), this);
		getServer().getPluginManager().registerEvents(new ChatColors(), this);
		getServer().getPluginManager().registerEvents(new Leave(this), this);
		getServer().getPluginManager().registerEvents(new Join(this), this);
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			public void run() {
				for (Player p : Bukkit.getOnlinePlayers()) {
					String prefix = chat.getPlayerPrefix(p);
					if(chat.getPlayerPrefix(p).isEmpty()) {
						String TabFormat = ChatColor.translateAlternateColorCodes('&', getConfig().getString("PlayerList").replace("%player", p.getName().toString()) + ChatColor.RESET);
						p.setPlayerListName(TabFormat);
					} else {
						String Tab2Format = ChatColor.translateAlternateColorCodes('&', getConfig().getString("PlayerList").replace("%player", p.getName().toString()).replace("%prefix", prefix.toString()) + ChatColor.RESET);
						p.setPlayerListName(Tab2Format);
					}
				}
			}
		}
		, 20L, 30L);
		setupChat();
	}
    private boolean setupChat() {
        RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
        chat = rsp.getProvider();
        return chat != null;
    }
}
