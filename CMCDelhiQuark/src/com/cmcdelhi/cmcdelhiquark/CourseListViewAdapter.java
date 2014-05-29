package com.cmcdelhi.cmcdelhiquark;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class CourseListViewAdapter extends ArrayAdapter<TypeARowItem> {

	Context context;
	Typeface tf;
	Typeface tfThin;
	SharedPreferences sp;

	public CourseListViewAdapter(Context context, int resourceId,
			List<TypeARowItem> objects) {
		super(context, resourceId, objects);

		Log.d("GUFRAN", "Inside  CourseListViewAdapter contructor ");

		this.context = context;
		tf = Typeface.createFromAsset(getContext().getAssets(),
				"fonts/Roboto-Thin.ttf");
		tfThin = Typeface.createFromAsset(getContext().getAssets(),
				"fonts/Roboto-Light.ttf");

	}

	class TypeAViewHolder {
		ImageView img;
		TextView courseTitle;
		Spinner courseTypes;
		TextView courseDecsription;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		sp = context.getSharedPreferences("course_list", context.MODE_PRIVATE);

		int currentSize = sp.getInt("font_size", 30);

		Log.d("GUFRAN", "Inside getView  method of CourseListViewAdapter");
		TypeAViewHolder holder = null;
		final TypeARowItem rowItem = getItem(position);

		LayoutInflater layoutInflater = (LayoutInflater) context
				.getSystemService(context.LAYOUT_INFLATER_SERVICE);

		// if (convertView == null) {
		holder = new TypeAViewHolder();

		Log.d("GUFRAN",
				"setting convertView when convertView =null Inside getView  method of CourseListViewAdapter");

		convertView = layoutInflater.inflate(R.layout.sexylayout, null);
		holder.img = (ImageView) convertView.findViewById(R.id.imageCourse);
		holder.courseTitle = (TextView) convertView
				.findViewById(R.id.courseTitle);
		holder.courseTypes = (Spinner) convertView.findViewById(R.id.spinner1);
		holder.courseDecsription = (TextView) convertView
				.findViewById(R.id.description);
		convertView.setTag(holder);

		// setting the horizontal title bar color
		RelativeLayout horzTitleBar = (RelativeLayout) convertView
				.findViewById(R.id.horzTitleBar);
		horzTitleBar
				.setBackgroundColor(LockedColorSingleton.getInstance().colorVal);

		// setting the vertBar Color
		TextView vertBar = (TextView) convertView
				.findViewById(R.id.verColorBar);
		vertBar.setBackgroundColor(LockedColorSingleton.getInstance().colorVal);

		// setting course description text color and font
		holder.courseDecsription.setTextColor(Color.BLACK);
		holder.courseDecsription.setTypeface(tfThin);

		// Setting Requset Help Text
		TextView reqHelp = (TextView) convertView
				.findViewById(R.id.requestHelp);
		reqHelp.setTextColor(LockedColorSingleton.getInstance().colorVal);
		reqHelp.setTypeface(tf);
		reqHelp.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(context, "Requset Help", Toast.LENGTH_SHORT)
						.show();
			}
		});

		// setting course title text color and font
		holder.courseTitle.setTextColor(Color.WHITE);
		holder.courseTitle.setTypeface(tf);

		// Setting Course Starts Text
		TextView courseStart = (TextView) convertView
				.findViewById(R.id.courseStart);
		courseStart.setTextColor(LockedColorSingleton.getInstance().colorVal);
		courseStart.setTypeface(tf);

		// setting the spinner color
		CustomizedSpinnerAdapter adapterSpin = new CustomizedSpinnerAdapter(
				(Activity) context, android.R.layout.simple_spinner_item,
				rowItem.getCourseTypes());

		if (holder.courseTypes != null) {
			if (adapterSpin != null) {
				holder.courseTypes.setAdapter(adapterSpin);
			} else {
				Toast.makeText(context, "Adapter SPN Is Null",
						Toast.LENGTH_SHORT).show();
			}
		} else {
			Toast.makeText(context, "SPN is NUll", Toast.LENGTH_SHORT).show();
		}

		holder.courseTypes
				.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> parent,
							View view, int pos, long id) {

						int index = parent.getSelectedItemPosition();

						String course_name = "Java SE 6 Weeks";
						String course_type = "6 Weeks";

						// com.cmcdelhi.cmcdelhiquark.CourseActivity
						Intent i = new Intent(
								"com.cmcdelhi.cmcdelhiquark.CourseActivity");

						if (!rowItem.getCourseTypes()[index]
								.equals("Select Course")) {

							if (rowItem.getCourseTypes()[index]
									.equals("Java SE 6 Weeks")) {
								course_name = "JavaSE";
								course_type = "6weeks";
							} else if (rowItem.getCourseTypes()[index]
									.equals("Java SE 6 Months")) {
								course_name = "JavaSE";
								course_type = "6months";

							} else if (rowItem.getCourseTypes()[index]
									.equals("J2EE(JEE) 6 Weeks")) {
								course_name = "JavaEE";
								course_type = "6weeks";

							} else if (rowItem.getCourseTypes()[index]
									.equals("Java SE 6 Months")) {
								course_name = "JavaSE";
								course_type = "6months";

							} else if (rowItem.getCourseTypes()[index]
									.equals("Android 4.1 6 Weeks")) {
								course_name = "Android";
								course_type = "6months";

							} else if (rowItem.getCourseTypes()[index]
									.equals("Android 4.1 6 Months")) {
								course_name = "Android";
								course_type = "6months";

							} else if (rowItem.getCourseTypes()[index]
									.equals("Asp.Net 6 Weeks")) {
								course_name = "Asp.NET";
								course_type = "6weeks";

							} else if (rowItem.getCourseTypes()[index]
									.equals("Asp.Net 6 Months")) {
								course_name = "Asp.NET";
								course_type = "6months";

							} else if (rowItem.getCourseTypes()[index]
									.equals("PHP-MySQL 6 Weeks")) {
								course_name = "PHP-MySQL";
								course_type = "6weeks";

							} else if (rowItem.getCourseTypes()[index]
									.equals("PHP-MySQL 6 Months")) {
								course_name = "PHP-MySQL";
								course_type = "6months";

							} else if (rowItem.getCourseTypes()[index]
									.equals("ES 6 Weeks")) {
								course_name = "Embeded System";
								course_type = "6weeks";

							} else if (rowItem.getCourseTypes()[index]
									.equals("ES 6 Months")) {
								course_name = "Embeded System";
								course_type = "6months";

							} else if (rowItem.getCourseTypes()[index]
									.equals("Catia 6 Weeks")) {
								course_name = "Catia";
								course_type = "6weeks";

							} else if (rowItem.getCourseTypes()[index]
									.equals("Catia 6 Weeks")) {
								course_name = "Catia";
								course_type = "6months";

							} else if (rowItem.getCourseTypes()[index]
									.equals("Android 4.1 6 Weeks")) {
								course_name = "Android";
								course_type = "6weeks";

							} else if (rowItem.getCourseTypes()[index]
									.equals("Android 4.1 6 Months")) {
								course_name = "Android";
								course_type = "6months";

							} else if (rowItem.getCourseTypes()[index]
									.equals("Android 4.1 6 Weeks")) {
								course_name = "Android";
								course_type = "6weeks";

							} else if (rowItem.getCourseTypes()[index]
									.equals("Android 4.1 6 Months")) {
								course_name = "Android";
								course_type = "6months";

							} else {

								Toast.makeText(
										context,
										"You have selected "
												+ rowItem.getCourseTypes()[index],
										Toast.LENGTH_SHORT).show();
							}

							i.putExtra("course_name", course_name);
							i.putExtra("course_type", course_type);
							context.startActivity(i);
							((Activity) context).overridePendingTransition(
									R.anim.push_left_in, R.anim.push_left_out);

						}

					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub

					}

				});

		holder.courseTitle.setText(rowItem.getCourseTitle());
		holder.courseDecsription.setText(rowItem.getCourseDesc());
		holder.img.setImageResource(rowItem.getImageId());

		holder.courseDecsription.setTextSize(currentSize);

		// Toast.makeText(context, "Yize is  " + currentSize,
		// Toast.LENGTH_SHORT)
		// .show();

		// } else {
		// Log.d("GUFRAN",
		// "setting convertView when convertView != null Inside getView  method of CourseListViewAdapter");
		//
		// holder = (TypeAViewHolder) convertView.getTag();
		// }

		return convertView;

	}
}
