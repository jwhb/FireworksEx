package de.jwhy.fireworksex;

import org.bukkit.ChatColor;

public class FireworksExHelp {

	public static String getGenericHeader(){
		ChatColor g = ChatColor.GREEN;
		ChatColor o = ChatColor.GOLD;
		ChatColor r = ChatColor.DARK_RED;
		return(g+"============["+o+"Fireworks"+r+"Ex"+g+"]============");
	}
	
	public static String[] getNoArgsHelp(String label){
		ChatColor g = ChatColor.GRAY;
		ChatColor o = ChatColor.GOLD;
		ChatColor n = ChatColor.RESET;
		String[] s = new String[2];
		s[0] = FireworksExHelp.getGenericHeader();
		s[1] = o + "Commands: /" + label + " shoot <style> - " + n + "Shoot rocket of predefined style";
		return(s);
	}
	
}
