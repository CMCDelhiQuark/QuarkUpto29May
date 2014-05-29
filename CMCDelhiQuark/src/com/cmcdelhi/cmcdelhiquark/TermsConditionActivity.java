package com.cmcdelhi.cmcdelhiquark;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.Menu;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TermsConditionActivity extends Activity {

	TextView title, seprator1;
	RelativeLayout bottomBar;
	ImageButton backButton;
	ActionBar ab;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.terms_condition_layout);

		title = (TextView) findViewById(R.id.title);
		seprator1 = (TextView) findViewById(R.id.seprator1);

		bottomBar = (RelativeLayout) findViewById(R.id.bottomBar);

		backButton = (ImageButton) findViewById(R.id.backButton);
		// .setBackgroundColor(LockedColorSingleton.getInstance().colorVal);

		title.setTextColor(LockedColorSingleton.getInstance().colorVal);
		seprator1
				.setBackgroundColor(LockedColorSingleton.getInstance().colorVal);
		bottomBar
				.setBackgroundColor(LockedColorSingleton.getInstance().colorVal);
		backButton
				.setBackgroundColor(LockedColorSingleton.getInstance().colorVal);

		// working with action bar

		ab = getActionBar();
		ab.setDisplayShowCustomEnabled(true);

		CMCActionBarGenerator cabg = new CMCActionBarGenerator(
				getApplicationContext());

		// ab.setCustomView(cabg.getCMCActionBar());

		ab.setIcon(R.drawable.ic_launcher);

		// Drawable d =
		// getResources().getDrawable(R.drawable.backgroundjpgbigpng);
		ColorDrawable d = new ColorDrawable(
				LockedColorSingleton.getInstance().colorVal);
		ab.setTitle("Terms & Condition");
		// ab.setSubtitle("cmcdelhi.com");
		ab.setBackgroundDrawable(d);

	}

}
