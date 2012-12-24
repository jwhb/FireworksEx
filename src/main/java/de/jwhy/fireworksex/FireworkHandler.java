package de.jwhy.fireworksex;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Builder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.plugin.Plugin;

public class FireworkHandler implements Listener {

	private Plugin plugin;
	private Logger logger;
	private Configuration config;

	public FireworkHandler(Plugin plugin, Configuration config) {
		this.plugin = plugin;
		this.logger = plugin.getLogger();
		if (config != null) {
			this.config = config;
		} else {
			this.logger.log(Level.WARNING, "Could not obtain configuration.");
			this.config = new YamlConfiguration();
		}
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		final Player player = event.getPlayer();
		logger.log(Level.INFO, "Test: "+ (new Integer(config.getInt("join-firework.delay",
			1000))).toString());
		plugin.getServer().getScheduler()
				.scheduleSyncDelayedTask(plugin, new Runnable() {
					@Override
					public void run() {
						launchFirework(player);
					}
				}, (long) config.getInt("join-firework.delay", 40));

	}

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (cmd.getName().equalsIgnoreCase("fwltest")) {
			if (sender instanceof Player) {
				this.launchFirework((Player) sender);
				return (true);
			}
			this.logger.log(Level.WARNING,
					"This command can be used by players only.");
		}
		return (false);
	}

	public void launchFirework(Player player) {
		Firework fw = (Firework) player.getWorld().spawnEntity(
				player.getLocation(), EntityType.FIREWORK);
		FireworkMeta fwmeta = fw.getFireworkMeta();
		fwmeta.setPower(4);
		Builder fweb = FireworkEffect.builder();
		fweb.trail(true);
		fweb.flicker(true);
		fweb.withColor(Color.FUCHSIA);
		fweb.with(FireworkEffect.Type.CREEPER);
		fwmeta.addEffect(fweb.build());
		fw.setFireworkMeta(fwmeta);
	}

}
