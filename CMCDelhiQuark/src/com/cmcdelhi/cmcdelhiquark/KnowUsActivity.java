package com.cmcdelhi.cmcdelhiquark;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class KnowUsActivity extends Activity {

	ActionBar ab;
	// ImageView for expandable arrow
	ImageView cmcDelhiAboutExpand, cmcAboutExpand;

	TextView cmcAboutContent, cmcDelhiAboutContent, rateUsTitle, cmcDelhiTitle;

	LinearLayout aboutLayoutBackground;

	Typeface tf;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.know_us_layout);

		tf = Typeface.createFromAsset(getAssets(), "fonts/Mathlete-Bulky.otf");

		aboutLayoutBackground = (LinearLayout) findViewById(R.id.aboutLayoutBackground);

		cmcDelhiAboutExpand = (ImageView) findViewById(R.id.cmcDelhiAboutExpand);
		cmcAboutExpand = (ImageView) findViewById(R.id.cmcAboutExpand);

		cmcAboutContent = (TextView) findViewById(R.id.cmcAboutContent);
		cmcDelhiAboutContent = (TextView) findViewById(R.id.cmcDelhiAboutContent);

		cmcDelhiTitle = (TextView) findViewById(R.id.cmcDelhiTitle);
		cmcDelhiTitle.setTextColor(LockedColorSingleton.getInstance().colorVal);

		rateUsTitle = (TextView) findViewById(R.id.rateUsTitle);

		rateUsTitle.setTextColor(LockedColorSingleton.getInstance().colorVal);
		rateUsTitle.setTypeface(tf);
		rateUsTitle.setTextSize(50f);

		cmcDelhiAboutExpand.setOnClickListener(new OnClickListener() {

			@SuppressLint("NewApi")
			@Override
			public void onClick(View v) {

				// tvMore.setVisibility(View.INVISIBLE);
				int lines = cmcDelhiAboutContent.getMaxLines();
				if (lines <= 6) {
					cmcDelhiAboutContent.setMaxLines(Integer.MAX_VALUE);
					cmcDelhiAboutExpand
							.setImageResource(R.drawable.nav_collapse_dark);
				} else {
					cmcDelhiAboutContent.setMaxLines(6);
					cmcDelhiAboutExpand
							.setImageResource(R.drawable.nav_expand_dark);

				}

			}
		});

		cmcAboutExpand.setOnClickListener(new OnClickListener() {

			@SuppressLint("NewApi")
			@Override
			public void onClick(View v) {

				// tvMore.setVisibility(View.INVISIBLE);
				int lines = cmcAboutContent.getMaxLines();
				if (lines <= 6) {
					cmcAboutContent.setMaxLines(Integer.MAX_VALUE);
					cmcAboutExpand
							.setImageResource(R.drawable.nav_collapse_dark);
				} else {
					cmcAboutContent.setMaxLines(6);
					cmcAboutExpand.setImageResource(R.drawable.nav_expand_dark);

				}

			}
		});

		aboutLayoutBackground.setBackgroundColor(LockedColorSingleton
				.getInstance().colorVal);

		// working with action bar

		ab = getActionBar();
		ab.setDisplayShowCustomEnabled(true);

		CMCActionBarGenerator cabg = new CMCActionBarGenerator(
				getApplicationContext());

		// ab.setCustomView(cabg.getCMCActionBar());

		ab.setIcon(R.drawable.cmclogo);

		// Drawable d =
		// getResources().getDrawable(R.drawable.backgroundjpgbigpng);
		ColorDrawable d = new ColorDrawable(
				LockedColorSingleton.getInstance().colorVal);
		ab.setTitle("Know Us");
		// ab.setSubtitle("cmcdelhi.com");
		ab.setBackgroundDrawable(d);
		ab.setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			overridePendingTransition(R.anim.push_right_in,
					R.anim.push_right_out);

			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void finish() {

		super.finish();
		overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);

	}

}
