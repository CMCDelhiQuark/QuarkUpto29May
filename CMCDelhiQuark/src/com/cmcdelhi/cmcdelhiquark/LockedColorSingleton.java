package com.cmcdelhi.cmcdelhiquark;

import android.graphics.Color;

public class LockedColorSingleton {

	private static LockedColorSingleton uniqueInstance;
	public int colorVal;

	private LockedColorSingleton() {
		colorVal = RandomColorGenerator.generateColor();
		// colorVal=Color.argb(255, 0, 0, 0);
	}

	public static LockedColorSingleton getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new LockedColorSingleton();
		}
		return uniqueInstance;
	}
}
