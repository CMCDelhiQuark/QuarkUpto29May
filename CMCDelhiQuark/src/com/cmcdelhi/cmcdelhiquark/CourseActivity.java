package com.cmcdelhi.cmcdelhiquark;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CourseActivity extends Activity {

	SharedPreferences sp;
	ActionBar ab;
	// ImageView for expandable arrow
	ImageView syllabusArrow, courseDescriptionArrow;
	// TextView for content
	TextView syllabusContent, courseDescriptionContent, whatsNewContent,
			registerTitle;
	TextView seprator1, seprator2, seprator3, seprator4, seprator5, seprator6;

	TextView courseDurationText;
	// titles
	TextView courseTitle, courseDescriptionTitle, whatsNewTitle, syllabusTitle;

	// Obtained Intent from Course List Activity
	Intent obtainedIntentFromCourseList;

	String course_name;
	String course_type;

	// LockedColorSingleton.getInstance().colorVal
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.course_layout);
		sp = getSharedPreferences("course_list", MODE_PRIVATE);
		int currentSize = sp.getInt("font_size", 30);

		// the cool font
		Typeface tf = Typeface.createFromAsset(getAssets(),
				"fonts/Mathlete-Bulky.otf");

		syllabusContent = (TextView) findViewById(R.id.syllabusContent);
		syllabusArrow = (ImageView) findViewById(R.id.syllabusArrow);

		courseDescriptionContent = (TextView) findViewById(R.id.courseDescriptionContent);
		courseDescriptionArrow = (ImageView) findViewById(R.id.courseDescriptionArrow);

		courseDurationText = (TextView) findViewById(R.id.courseDurationText);

		// refrencing the seprator
		seprator1 = (TextView) findViewById(R.id.seprator1);
		seprator2 = (TextView) findViewById(R.id.seprator2);
		seprator3 = (TextView) findViewById(R.id.seprator3);
		seprator4 = (TextView) findViewById(R.id.seprator4);
		seprator5 = (TextView) findViewById(R.id.seprator5);
		seprator6 = (TextView) findViewById(R.id.seprator6);
		// set up the backgroungd color
		seprator1
				.setBackgroundColor(LockedColorSingleton.getInstance().colorVal);
		seprator2
				.setBackgroundColor(LockedColorSingleton.getInstance().colorVal);
		seprator3
				.setBackgroundColor(LockedColorSingleton.getInstance().colorVal);
		seprator4
				.setBackgroundColor(LockedColorSingleton.getInstance().colorVal);
		seprator5
				.setBackgroundColor(LockedColorSingleton.getInstance().colorVal);
		seprator6
				.setBackgroundColor(LockedColorSingleton.getInstance().colorVal);

		// refrencing the titles
		courseTitle = (TextView) findViewById(R.id.courseTitle);
		courseDescriptionTitle = (TextView) findViewById(R.id.courseDescriptionTitle);
		whatsNewTitle = (TextView) findViewById(R.id.whatsNewTitle);
		syllabusTitle = (TextView) findViewById(R.id.syllabusTitle);

		syllabusContent = (TextView) findViewById(R.id.syllabusContent);
		courseDescriptionContent = (TextView) findViewById(R.id.courseDescriptionContent);
		whatsNewContent = (TextView) findViewById(R.id.whatsNewContent);

		syllabusContent.setTextSize(currentSize);
		courseDescriptionContent.setTextSize(currentSize);
		whatsNewContent.setTextSize(currentSize);

		syllabusArrow.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				// tvMore.setVisibility(View.INVISIBLE);
				int lines = syllabusContent.getMaxLines();
				if (lines <= 6) {
					syllabusContent.setMaxLines(Integer.MAX_VALUE);
					syllabusArrow
							.setImageResource(R.drawable.nav_collapse_dark);
				} else {
					syllabusContent.setMaxLines(6);
					syllabusArrow.setImageResource(R.drawable.nav_expand_dark);

				}

			}
		});

		courseDescriptionArrow.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				// tvMore.setVisibility(View.INVISIBLE);
				int lines = courseDescriptionContent.getMaxLines();
				if (lines <= 3) {
					courseDescriptionContent.setMaxLines(Integer.MAX_VALUE);
					courseDescriptionArrow
							.setImageResource(R.drawable.nav_collapse_dark);
				} else {
					courseDescriptionContent.setMaxLines(3);
					courseDescriptionArrow
							.setImageResource(R.drawable.nav_expand_dark);

				}

			}
		});

		// seting the font and color of titles ans size
		courseTitle.setTypeface(tf);
		courseDescriptionTitle.setTypeface(tf);
		whatsNewTitle.setTypeface(tf);
		syllabusTitle.setTypeface(tf);
		courseTitle.setTextColor(LockedColorSingleton.getInstance().colorVal);
		courseDescriptionTitle
				.setTextColor(LockedColorSingleton.getInstance().colorVal);
		whatsNewTitle.setTextColor(LockedColorSingleton.getInstance().colorVal);
		syllabusTitle.setTextColor(LockedColorSingleton.getInstance().colorVal);

		courseTitle.setTextSize(50);
		courseDescriptionTitle.setTextSize(40);
		whatsNewTitle.setTextSize(40);
		syllabusTitle.setTextSize(40);

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
		ab.setTitle("Course");
		// ab.setSubtitle("cmcdelhi.com");
		ab.setBackgroundDrawable(d);
		ab.setDisplayHomeAsUpEnabled(true);

		obtainedIntentFromCourseList = getIntent();

		course_name = obtainedIntentFromCourseList
				.getStringExtra("course_name");
		course_type = obtainedIntentFromCourseList
				.getStringExtra("course_type");

		Toast.makeText(getApplicationContext(), "Course Name" + course_name,
				Toast.LENGTH_SHORT).show();

		Toast.makeText(getApplicationContext(), "Course  Type" + course_type,
				Toast.LENGTH_SHORT).show();

		readData();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuItem mnu1 = menu.add(0, 0, 0, "Increase Font Size");
		mnu1.setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);

		MenuItem mnu2 = menu.add(0, 1, 1, "Decrease Font Size");
		mnu2.setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);

		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case 0:

			SharedPreferences.Editor sped = sp.edit();
			int currentSize = sp.getInt("font_size", 30);
			sped.putInt("font_size", currentSize + 5);
			sped.commit();

			onCreate(null);

			break;
		case 1:
			SharedPreferences.Editor sped2 = sp.edit();
			int currentSize2 = sp.getInt("font_size", 30);
			sped2.putInt("font_size", currentSize2 - 5);
			sped2.commit();

			onCreate(null);

			break;
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

	public void readData() {

		try {
			InputStream in = openFileInput("CourseIndividualObject.json");
			InputStreamReader insr = new InputStreamReader(in);
			String str = "";
			for (int i = insr.read(); i != -1; i = insr.read()) {
				str += (char) i;
			}

			JSONObject loadedJSONObject = new JSONObject(str);

			JSONObject courseType = loadedJSONObject.getJSONArray(course_name)
					.getJSONObject(1);

			if (course_type.equals("6weeks")) {
				courseType = loadedJSONObject.getJSONArray(course_name)
						.getJSONObject(0);
			}

			JSONObject courseInner = courseType.getJSONObject(course_type);

			// tv1.setText(javaSE6MonthsInner.getString("courseDuration"));

			courseTitle.setText(courseInner.getString("courseTitle"));
			courseDurationText.setText(courseInner.getString("courseDuration"));
			courseDescriptionContent.setText(courseInner
					.getString("courseDescription"));
			whatsNewContent.setText(courseInner.getString("whatsNew"));
			JSONArray syllabusItems = courseInner.getJSONArray("syllabus");
			String syll = "";
			for (int i = 0; i < syllabusItems.length(); i++) {
				String item = syllabusItems.getString(i);
				syll += item + "\n";
			}

			syllabusContent.setText(syll);
		} catch (FileNotFoundException e) {
			Toast.makeText(getBaseContext(),
					"File Not Found Exception " + e.getMessage(),
					Toast.LENGTH_SHORT).show();
			e.printStackTrace();
		} catch (IOException e) {
			Toast.makeText(getBaseContext(), "IO Exception " + e.getMessage(),
					Toast.LENGTH_LONG).show();
			e.printStackTrace();
		} catch (JSONException e) {
			// tv1.setText("JSON Exception " + e.getMessage());
			Toast.makeText(getBaseContext(),
					"JSON Exception " + e.getMessage(), Toast.LENGTH_LONG)
					.show();
			e.printStackTrace();
		}

	}
}
