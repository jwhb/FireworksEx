package de.jwhy.fireworksex;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

import de.jwhy.fireworksex.model.FireworkLaunchAtPlayerTask;
import de.jwhy.fireworksex.model.FireworkStyle;

public class FireworkHandler implements Listener {

	private Configuration cfg;
	private Plugin plugin;

	public FireworkHandler(Plugin plugin, Configuration cfg) {
		this.plugin = plugin;
		this.cfg = cfg;
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();

		List<String> fw_styles = null;

		String node = "join-firework.fireworks";
		if (cfg.isList(node)) {
			fw_styles = cfg.getStringList(node);
		}else{
			fw_styles = new ArrayList<String>();
			fw_styles.add(cfg.getString(node));
		}
		for (String fw_style : fw_styles) {
			this.launchFirework(player, fw_style, 0);
		}
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (cmd.getName().equalsIgnoreCase("fireworksex")) {
			if (sender instanceof Player) {
				// TODO: Add permissions
				if (sender.isOp()) {
					switch (args.length) {
					case 2:
						if (args[0].equalsIgnoreCase("s")
								|| args[0].equalsIgnoreCase("shoot")) {
							this.launchFirework((Player) sender, args[1], 0);
							break;
						}
					default:
						sender.sendMessage(FireworksExHelp.getNoArgsHelp(label));
						break;
					}

					return (true);
				} else {
					sender.sendMessage("You're not permitted to do that!");
					return (true);
				}
			}
			sender.sendMessage("This command can be used by players only.");
			return (true);
		}
		return (false);
	}

	public void launchFirework(Player player, FireworkStyle style, int delay) {
		if (delay == 0) {
			Firework fw = (Firework) player.getWorld().spawnEntity(
					player.getLocation(), EntityType.FIREWORK);
			FireworkManager.applyStyle(fw, style);
		} else {
			new FireworkLaunchAtPlayerTask(player, style, this);
		}
	}

	public void launchFirework(Player player, String style, int delay) {
		FireworkStyle fw_style = FireworkManager.getFireworkStyle(style,
				this.cfg);
		launchFirework(player, fw_style, 0);
	}

}
