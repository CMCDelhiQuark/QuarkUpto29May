package com.cmcdelhi.cmcdelhiquark;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class EnquiryActivity extends Activity {

	Spinner spinnerReplyVia, spinnerCourse;

	String[] courseList = { "Select Course ", "Java", ".Net", "PHP", "Pascal" };
	String[] replyVia = { "Reply via ", "EMial", "Phone" };

	Button submitButton;
	EditText edName;
	ActionBar ab;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.enquiry_layout);

		spinnerCourse = (Spinner) findViewById(R.id.spinnerCourse);
		spinnerReplyVia = (Spinner) findViewById(R.id.spinnerReplyVia);

		submitButton = (Button) findViewById(R.id.submitButton);

		edName = (EditText) findViewById(R.id.edName);

		spinnerCourse
				.setBackgroundColor(LockedColorSingleton.getInstance().colorVal);

		spinnerReplyVia
				.setBackgroundColor(LockedColorSingleton.getInstance().colorVal);

		submitButton
				.setBackgroundColor(LockedColorSingleton.getInstance().colorVal);

		CustomizedSpinnerAdapter courseAdapter = new CustomizedSpinnerAdapter(
				this, android.R.layout.simple_spinner_dropdown_item, courseList);

		// // setting the spinner color
		// CustomizedSpinnerAdapter adapterSpin = new CustomizedSpinnerAdapter(
		// (Activity) context, android.R.layout.simple_spinner_item,
		// rowItem.getCourseTypes());

		CustomizedSpinnerAdapter replyViaAdapter = new CustomizedSpinnerAdapter(
				this, android.R.layout.simple_spinner_dropdown_item, replyVia);

		spinnerCourse.setAdapter(courseAdapter);
		spinnerReplyVia.setAdapter(replyViaAdapter);

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
		ab.setTitle("Enquiry");
		// ab.setSubtitle("cmcdelhi.com");
		ab.setBackgroundDrawable(d);

	}

}
