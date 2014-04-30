package de.jwhy.fireworksex;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class FireworksEx extends JavaPlugin implements Listener {

	public FileConfiguration cfg;
	private FireworkHandler fh;

	public void onEnable() {
		// Prepare configuration
		if (getDataFolder().mkdir()) {
			getLogger().info(
					"Base data directory not found, creating it for you now.");
		}
		cfg = getConfig();
		cfg.options().copyDefaults(true);
		saveDefaultConfig();

		// Initialize FireworkHandler instance
		fh = new FireworkHandler(this, cfg);

		// Register events
		getServer().getPluginManager().registerEvents(fh, this);
	}

	public void onDisable() {

	}

	@EventHandler
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		return (fh.onCommand(sender, cmd, label, args));
	}

}