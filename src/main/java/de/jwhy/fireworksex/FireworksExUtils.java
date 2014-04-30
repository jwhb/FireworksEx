package de.jwhy.fireworksex;

import java.lang.reflect.Field;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;

public class FireworksExUtils {

	public static Color getColor(String colorname) {
		String cn = colorname.toUpperCase();
		try {
			Field cf = Color.class.getField(cn);
			if (cf.getType().isAssignableFrom(Color.class)) {
				// Valid color
				return ((Color) (cf.get(null)));
			}
			return (null);
		} catch (Exception e) {
			e.printStackTrace();
			return (null);
		}
	}

	public static FireworkEffect.Type getFireworkEffectType(String effectname) {
		String en = effectname.toUpperCase();
		try {
			Field ef = FireworkEffect.Type.class.getField(en);
			if (ef.getType().isAssignableFrom(FireworkEffect.Type.class)) {
				// Valid color
				return ((FireworkEffect.Type) (ef.get(null)));
			}
			return (null);
		} catch (Exception e) {
			e.printStackTrace();
			return (null);
		}
	}

}
