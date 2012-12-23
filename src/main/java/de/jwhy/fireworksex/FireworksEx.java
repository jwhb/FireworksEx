package de.jwhy.fireworksex;

import java.util.logging.Level;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Builder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public class FireworksEx extends JavaPlugin implements Listener {
    
    public void onDisable() {
    	
        // TODO: Place any custom disable code here.
    }

    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }
    
    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent event) {
    	launchFirework(event.getPlayer());    	
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
    	if(cmd.getName().equalsIgnoreCase("fwltest")){
    		if(sender instanceof Player){
        		this.launchFirework((Player) sender);
        		return(true);
    		}
    		this.getLogger().log(Level.WARNING, "This command can be used by players only.");
    	}
    	return(false); 
    }
    
    public void launchFirework(Player player){
        Firework fw = (Firework) player.getWorld().spawnEntity(player.getLocation(), EntityType.FIREWORK);
        FireworkMeta fwmeta = fw.getFireworkMeta();
        fwmeta.setPower(4);
        Builder fweb = FireworkEffect.builder();
        fweb.trail(true);
        fweb.flicker(true);
        fweb.withColor(Color.FUCHSIA);
        fwmeta.addEffect(fweb.build());
        fw.setFireworkMeta(fwmeta);
    }
    
} 