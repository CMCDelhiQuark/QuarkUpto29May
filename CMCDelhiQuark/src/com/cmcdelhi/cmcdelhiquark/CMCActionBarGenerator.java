package com.cmcdelhi.cmcdelhiquark;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.view.Gravity;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;

public class CMCActionBarGenerator {
	LockedColorSingleton lcs;
	Context context;

	public CMCActionBarGenerator(Context context) {
		this.context = context;
	}

	public RelativeLayout getCMCActionBar() {
		lcs = LockedColorSingleton.getInstance();

		RelativeLayout relativeLayout = new RelativeLayout(context);
		android.widget.RelativeLayout.LayoutParams layoutParams = (android.widget.RelativeLayout.LayoutParams) new android.widget.RelativeLayout.LayoutParams(
				android.widget.RelativeLayout.LayoutParams.FILL_PARENT,
				android.widget.RelativeLayout.LayoutParams.FILL_PARENT);

		// creating gradiant
		// int[] colors1 = {Color.BLACK, Color.GREEN};
		//
		// GradientDrawable shadow = new
		// GradientDrawable(Orientation.TOP_BOTTOM, colors1);
		//

		int[] colors = new int[3];
		colors[0] = Color.WHITE;
		colors[1] = Color.WHITE;
		colors[2] = lcs.colorVal;

		GradientDrawable backColor = new GradientDrawable(
				Orientation.LEFT_RIGHT, colors);

		// finally create a layer list and set them as background.
		Drawable[] layers = new Drawable[1];
		layers[0] = backColor;
		// /layers[1] = shadow;

		LayerDrawable layerList = new LayerDrawable(layers);
		layerList.setLayerInset(0, 0, 0, 0, 0);

		// layerList.setLayerInset(1, 0, 98, 0, 0);

		relativeLayout.setBackground(layerList);

		// -----------CMC DElhi Text on Action Bar-----------------------//

		android.widget.RelativeLayout.LayoutParams textVeiwLayoutParams = new android.widget.RelativeLayout.LayoutParams(
				android.widget.RelativeLayout.LayoutParams.FILL_PARENT,
				android.widget.RelativeLayout.LayoutParams.FILL_PARENT);

		textVeiwLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		textVeiwLayoutParams.addRule(RelativeLayout.CENTER_VERTICAL);

		TextView tv = new TextView(context);
		// Typeface tf = Typeface.createFromAsset(context.getAssets(),
		// "fonts/Roboto-Regular.ttf");
		Typeface tf = Typeface.createFromAsset(context.getAssets(),
				"fonts/Mathlete-Bulky.otf");

		tv.setTypeface(tf);
		tv.setTextSize(50);
		tv.setText(" CMC Delhi");

		tv.setLayoutParams(textVeiwLayoutParams);
		tv.setTextColor(lcs.colorVal);

		// --------------------------------------------//

		//
		// android.widget.RelativeLayout.LayoutParams imageVeiwLayoutParams =
		// new android.widget.RelativeLayout.LayoutParams(
		// android.widget.RelativeLayout.LayoutParams.WRAP_CONTENT,
		// android.widget.RelativeLayout.LayoutParams.WRAP_CONTENT);
		//
		// imageVeiwLayoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
		// imageVeiwLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		//
		// ImageView shareImageView = new ImageView(context);
		// shareImageView.setImageDrawable(context.getResources().getDrawable(
		// R.drawable.ic_action_mail));
		// shareImageView.setLayoutParams(imageVeiwLayoutParams);
		// shareImageView.setBackgroundColor(Color.TRANSPARENT);

		android.widget.RelativeLayout.LayoutParams searchVeiwLayoutParams = new android.widget.RelativeLayout.LayoutParams(
				android.widget.RelativeLayout.LayoutParams.WRAP_CONTENT,
				android.widget.RelativeLayout.LayoutParams.WRAP_CONTENT);

		searchVeiwLayoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
		searchVeiwLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

		SearchView sv = new SearchView(context);
		sv.setLayoutParams(searchVeiwLayoutParams);
		sv.setBackgroundColor(Color.TRANSPARENT);

		relativeLayout.addView(tv);
		relativeLayout.addView(sv);
		// relativeLayout.addView(shareImageView);

		relativeLayout.setLayoutParams(layoutParams);

		return relativeLayout;

	}
}
