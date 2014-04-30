package de.jwhy.fireworksex.model;

import org.bukkit.entity.Player;

import de.jwhy.fireworksex.FireworkHandler;

public class FireworkLaunchAtPlayerTask implements Runnable {

	private FireworkHandler fh;
	private Player player;
	private FireworkStyle style;

	public FireworkLaunchAtPlayerTask(Player player, FireworkStyle style,
			FireworkHandler fh) {
		this.fh = fh;
		this.player = player;
		this.style = style;
	}

	@Override
	public void run() {
		this.fh.launchFirework(player, style, 0);
	}

}
