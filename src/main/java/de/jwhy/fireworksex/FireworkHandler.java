package de.jwhy.fireworksex;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.Color;
import org.bukkit.Effect;
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

import de.jwhy.fireworksex.model.FireworkJoinDelayTask;
import de.jwhy.fireworksex.model.FireworkStyle;

public class FireworkHandler implements Listener {

	private Plugin plugin;
	private Logger logger;
	public Configuration config;

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
		Player player = event.getPlayer();
		String jf = FireworkHandler.this.config.getString("join-firework.firework-style", "join");
		FireworkStyle style = FireworkManager.getFireworkStyle(jf, config);
		plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, 
				new	FireworkJoinDelayTask(player, style, this), (long) config.getInt("join-firework.delay", 40)
		);

	}

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (cmd.getName().equalsIgnoreCase("fireworksex")) {
			if (sender instanceof Player) {
				//TODO: Add permissions
				if(sender.isOp()){
					switch(args.length){
					case 2:
						if(args[0].equalsIgnoreCase("s") || args[0].equalsIgnoreCase("shoot")){
							this.launchFirework((Player) sender, args[1]);
							break;
						}
					default:
						sender.sendMessage(FireworksExHelp.getNoArgsHelp(label));
						break;
					}

					return(true);
				}else{
					sender.sendMessage("You're not permitted to do that!");
					return(true);
				}
			}
			sender.sendMessage("This command can be used by players only.");
			return(true);
		}
		return (false);
	}

	public void launchFirework(Player player, FireworkStyle style) {
		Firework fw = (Firework) player.getWorld().spawnEntity(player.getLocation(), EntityType.FIREWORK);
		FireworkHandler.applyStyle(fw, style);
	}
	
	public void launchFirework(Player player, String style) {
		this.launchFirework(player, FireworkManager.getFireworkStyle(style, this.config));
	}

	public static Firework applyStyle(Firework fw, FireworkStyle style) {
		FireworkMeta fwmeta = fw.getFireworkMeta();
		fwmeta.setPower(style.power);
		Builder fweb = FireworkEffect.builder();
			fweb.withColor(style.color);
			fweb.with(style.shape);
			fweb.trail(style.trail);
			fweb.flicker(style.flicker);
		fwmeta.addEffect(fweb.build());
		fw.setFireworkMeta(fwmeta);
		return(fw);
	}

}
