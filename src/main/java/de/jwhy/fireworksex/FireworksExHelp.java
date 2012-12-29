package de.jwhy.fireworksex;

import org.bukkit.ChatColor;

public class FireworksExHelp {

	private static ChatColor o = ChatColor.GOLD;
	private static ChatColor n = ChatColor.RESET;
	private static ChatColor r = ChatColor.DARK_RED;
	private static ChatColor y = ChatColor.YELLOW;
	
	private static String getPluginNameFormatted(){
		String msg = o+"Fireworks"+r+"Ex";
		return(msg);
	}
	
	public static String getGenericHeader(){
		ChatColor g = ChatColor.GREEN;
		String name = FireworksExHelp.getPluginNameFormatted();
		return(g + "============[" + name + g + "]============");
	}
	
	public static String[] getNoArgsHelp(String label){
		ChatColor g = ChatColor.GRAY;
		String[] msg = new String[2];
		msg[0] = FireworksExHelp.getGenericHeader();
		msg[1] = g + "Commands: " + o + "/" + label + " shoot <style> - " + n 
				+ "Shoot rocket of predefined style";
		return(msg);
	}

	public static String getInvalidStyle(String style) {
		String name = FireworksExHelp.getPluginNameFormatted();
		String stylepad;
		if(!style.isEmpty()){
			//Return style with padding
			stylepad = y + style + " ";
		}else{
			//Return nothing with padding
			stylepad = "";
		}
		String msg = name + n + " The requested style " + stylepad  + "could not be found!";

		return(msg);
	}
	
	public static String getInvalidStyle(){
		return(FireworksExHelp.getInvalidStyle(""));
	}
	
}
