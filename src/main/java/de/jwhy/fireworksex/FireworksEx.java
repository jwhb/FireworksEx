package de.jwhy.fireworksex;

import java.io.File;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class FireworksEx extends JavaPlugin implements Listener {

	private File configFile;
	public FileConfiguration config;
	private FireworkHandler fm;

	public void onDisable() {

	}

	public void onEnable() {
		// Prepare configuration
		this.config = new YamlConfiguration();
		this.configFile = new File(getDataFolder(), "config.yml");
		FireworksExUtils.runFirstSetup(this, configFile);
		FireworksExUtils.loadConfigs(configFile, config);

		// Initialize FireworkManager instance
		this.fm = new FireworkHandler(this, config);

		// Register events
		// Map<String, Map<String, Object>> commands =
		// this.getDescription().getCommands();
		getServer().getPluginManager().registerEvents(this.fm, this);
	}

	@EventHandler
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		return (this.fm.onCommand(sender, cmd, label, args));
	}

}