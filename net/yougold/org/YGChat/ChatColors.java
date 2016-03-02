package net.yougold.org.YGChat;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatColors implements Listener {
	@EventHandler(priority=EventPriority.NORMAL)
	public void onPLayerChatEvent(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		if (p.hasPermission("yg.chat.color")) {
			String message = e.getMessage();
			message = message.replace('&', '§');
			e.setMessage(message);
		}
	}
}
