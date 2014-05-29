package com.cmcdelhi.cmcdelhiquark;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.view.Menu;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ImageNotesActivity extends Activity {

	LinearLayout imagescanner1, imagescanner2;
	FrameLayout frame;

	ImageView img;

	LockedColorSingleton lcsI;
	TextView tv1, tv2;

	Typeface tpf;

	ActionBar ab;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image_notes);

		// LockedColorSingleton.getInstance().colorVal

		imagescanner1 = (LinearLayout) findViewById(R.id.imagescan);

		imagescanner1
				.setBackgroundColor(LockedColorSingleton.getInstance().colorVal);

		frame = (FrameLayout) findViewById(R.id.frames);

		frame.setBackgroundColor(LockedColorSingleton.getInstance().colorVal);

		imagescanner2 = (LinearLayout) findViewById(R.id.newlinearlayout);

		imagescanner2
				.setBackgroundColor(LockedColorSingleton.getInstance().colorVal);

		img = (ImageView) findViewById(R.id.imageView1);

		tv1 = (TextView) findViewById(R.id.textView1);

		tv2 = (TextView) findViewById(R.id.textView2);

		tv2.setText("Share your image notes on Dropbox ,\n Evernotes, GDrive, Facebook etc...");

		tv2.setTextColor(Color.WHITE);

		tpf = Typeface.createFromAsset(getAssets(), "fonts/Mathlete-Bulky.otf");

		tv2.setTypeface(tpf);

		tv2.setTextSize(27f);

		tv1.setTypeface(tpf);

		tv1.setTextSize(50f);

		tv1.setTextColor(LockedColorSingleton.getInstance().colorVal);

		ab = getActionBar();

		ab.setIcon(R.drawable.ic_launcher);
		ab.setDisplayHomeAsUpEnabled(true);
		ab.setDisplayShowCustomEnabled(true);
		ab.setTitle("ImageScanner");

		ColorDrawable d = new ColorDrawable(
				LockedColorSingleton.getInstance().colorVal);

		ab.setBackgroundDrawable(d);

	}

}
