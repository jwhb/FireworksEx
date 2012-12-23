package de.jwhy.fireworksex;

import java.io.File;
import java.util.Map;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class FireworksEx extends JavaPlugin implements Listener {
	
	private File configFile;
	public FileConfiguration config;
	private FireworkManager fm;

    public void onDisable() {
    	
    }

    public void onEnable() {
        this.fm = new FireworkManager(this, config);
        
        //Register events
        Map<String, Map<String, Object>> commands = this.getDescription().getCommands();
        //commands.
        getServer().getPluginManager().registerEvents((Listener) this.fm, this);

        //Prepare configuration
    	this.config = new YamlConfiguration();
        this.configFile = new File(getDataFolder(), "config.yml");
        FireworksExUtils.runFirstSetup(this, configFile);
        FireworksExUtils.loadConfigs(configFile, config);
    }
   
    @EventHandler
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
    	return(this.fm.onCommand(sender, cmd, label, args));
    }
    
} 