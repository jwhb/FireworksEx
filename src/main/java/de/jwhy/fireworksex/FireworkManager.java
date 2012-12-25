package de.jwhy.fireworksex;

import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.Plugin;

public class FireworkManager {
	private Plugin plugin;
	private Configuration config;

	public FireworkManager(Plugin plugin, Configuration config){
		this.plugin = plugin;
		this.config = config;
	}
	
	public FireworkStyle getFireworkStyle(String name){
		//TODO: Generate FireworkStyle
		ConfigurationSection section = this.config.getConfigurationSection("styles." + name);
		FireworkStyle fs = new FireworkStyle();
		if(section != null){
			//Firework style in configuration
			fs.color = FireworksExUtils.getColor(section.getString("color"));
			fs.shape = FireworksExUtils.getFireworkEffectType(section.getString("shape"));
			//TODO: Document flicker in config.yml
			fs.flicker = section.getBoolean("flicker", false);
			fs.power = section.getLong("power");
		}
		return(fs);
	}
}
