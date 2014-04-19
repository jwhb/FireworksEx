package de.jwhy.fireworksex;

import org.bukkit.FireworkEffect.Type;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.Plugin;

import de.jwhy.fireworksex.model.FireworkStyle;

public class FireworkManager {
	
	private Configuration config;

	public FireworkManager(Plugin plugin, Configuration config) {
		this.config = config;
	}

	public static FireworkStyle getFireworkStyle(String name,
			Configuration config) {
		// TODO: Generate FireworkStyle
		ConfigurationSection section;
		section = config.getConfigurationSection("styles." + name);
		FireworkStyle fs = new FireworkStyle();
		if (section != null) {
			// Firework style in configuration
			fs.color = FireworksExUtils.getColor(section.getString("color"));
			Type cshape = FireworksExUtils.getFireworkEffectType(section
					.getString("shape"));
			if (cshape != null)
				fs.shape = cshape;
			// TODO: Document flicker in config.yml
			fs.flicker = section.getBoolean("flicker", false);
			fs.power = section.getInt("power", 1);
		}
		return (fs);
	}

	public FireworkStyle getFireworkStyle(String string) {
		return (getFireworkStyle(string, this.config));
	}

}
