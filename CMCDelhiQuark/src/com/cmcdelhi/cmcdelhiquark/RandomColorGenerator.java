package com.cmcdelhi.cmcdelhiquark;

import java.util.Random;

import android.graphics.Color;

public class RandomColorGenerator {

	private RandomColorGenerator() {
		// TODO Auto-generated constructor stub
	}

	// private static int[] colorArray = { Color.argb(255, 0, 145, 165),
	// Color.argb(255, 63, 122, 182), Color.argb(255, 90, 56, 181),
	// Color.argb(255, 42, 128, 238), Color.argb(255, 0, 152, 0),
	// Color.argb(255, 215, 79, 42), Color.argb(255, 0, 0, 0),
	// Color.argb(255, 154, 0, 162), Color.argb(255, 182, 28, 68),
	// Color.argb(255, 220, 79, 173), Color.argb(255, 172, 25, 681),
	// Color.argb(255, 130, 186, 0), Color.argb(255, 0, 130, 153),
	// Color.argb(255, 0, 114, 198), Color.argb(255, 70, 23, 180),
	// Color.argb(255, 140, 0, 149), Color.argb(255, 0, 75, 139),
	// Color.argb(255, 87, 0, 0), Color.argb(255, 88, 88, 88) };

	// turquoise bluish greenis Color.argb(255, 0, 145, 165)
	// CMC logo blue Color.argb(255, 63, 122, 182)
	// voilet Color.argb(255, 90, 56, 181)
	// dark green Color.argb(255, 42, 128, 238)
	// other grenen Color.argb(255, 0, 152, 0)
	// orange Color.argb(255, 215, 79, 42)
	// pure black Color.argb(255, 0, 0, 0)
	// other jamun voilet Color.argb(255, 154, 0, 162)
	// brown Color.argb(255, 182, 28, 68)
	// pink Color.argb(255, 220, 79, 173)
	// light green Color.argb(255, 130, 186, 0)
	// another blue shade Color.argb(255, 0, 130, 153)
	// bluish shade Color.argb(255, 0, 114, 198)
	// / Color.argb(255, 236, 173, 0) yellow

	private static int[] colorArray = { Color.argb(255, 90, 56, 181),
			Color.argb(255, 42, 128, 238), Color.argb(255, 0, 152, 0),
			Color.argb(255, 236, 173, 0), Color.argb(255, 215, 79, 42),
			Color.argb(255, 0, 0, 0), Color.argb(255, 154, 0, 162),
			Color.argb(255, 182, 28, 68), Color.argb(255, 220, 79, 173),
			Color.argb(255, 130, 186, 0), Color.argb(255, 0, 130, 153),
			Color.argb(255, 0, 114, 198), Color.argb(255, 70, 23, 180),
			Color.argb(255, 140, 0, 149), Color.argb(255, 0, 75, 139),
			Color.argb(255, 87, 0, 0), Color.argb(255, 88, 88, 88) };

	public static int generateColor() {
		int randomColorVal = getColorRandomArrayIndex();

		return colorArray[randomColorVal];
	}

	private static int getColorRandomArrayIndex() {

		Random rnd = new Random();

		int val = (int) (rnd.nextFloat() * 100);

		if (val < colorArray.length) {
			return val;
		} else {
			return getColorRandomArrayIndex();
		}

	}

}
