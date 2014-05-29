package com.cmcdelhi.cmcdelhiquark;

import java.util.List;

import android.R.animator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class CustomListViewAdapter extends ArrayAdapter<RowItem> {

	Context context;
	Typeface tf;
	Typeface tfThick;

	String[] animals = { "Apple", "Animation", "Ball", "Cat", "Beta", "Camel",
			"Deepak", " De Caprio", "Kate Winslet" };

	public CustomListViewAdapter(Context context, int resourceId,
			List<RowItem> items) {
		super(context, resourceId, items);
		this.context = context;
		tf = Typeface.createFromAsset(getContext().getAssets(),
				"fonts/Roboto-Thin.ttf");
		tfThick = Typeface.createFromAsset(getContext().getAssets(),
				"fonts/Roboto-Light.ttf");

	}

	class ViewHolder {
		ImageView imageView;
		TextView txtTitle;
		TextView txtDesc;
	}

	

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder holder = null;
		RowItem rowItem = getItem(position);
		

		LayoutInflater layoutInflater = (LayoutInflater) context
				.getSystemService(context.LAYOUT_INFLATER_SERVICE);

		if (convertView == null) {

			holder = new ViewHolder();
			

			if (position < 4) {
				convertView = layoutInflater.inflate(R.layout.sexylayout, null);

				// setting the horizontal title bar color
				RelativeLayout horzTitleBar = (RelativeLayout) convertView
						.findViewById(R.id.horzTitleBar);
				horzTitleBar.setBackgroundColor(LockedColorSingleton
						.getInstance().colorVal);

				// setting the vertBar Color
				TextView vertBar = (TextView) convertView
						.findViewById(R.id.verColorBar);
				vertBar.setBackgroundColor(LockedColorSingleton.getInstance().colorVal);

				// Setting the course description

				TextView description = (TextView) convertView
						.findViewById(R.id.description);
				description.setTextColor(Color.BLACK);
				description.setTypeface(tfThick);
				// android:textAppearance="?android:attr/textAppearanceLarge"

				// Setting Requset Help Text
				TextView reqHelp = (TextView) convertView
						.findViewById(R.id.requestHelp);
				reqHelp.setTextColor(LockedColorSingleton.getInstance().colorVal);
				reqHelp.setTypeface(tf);
				reqHelp.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						Toast.makeText(context, "Requset Help",
								Toast.LENGTH_SHORT).show();
					}
				});

				// Setting Course Title
				TextView courseTitle = (TextView) convertView
						.findViewById(R.id.courseTitle);
				courseTitle.setTextColor(Color.WHITE);
				courseTitle.setText("Java Android");
				courseTitle.setTypeface(tf);

				// Setting Course Starts Text
				TextView courseStart = (TextView) convertView
						.findViewById(R.id.courseStart);
				courseStart
						.setTextColor(LockedColorSingleton.getInstance().colorVal);
				courseStart.setTypeface(tf);

				// settting the spinner
				Spinner spn = (Spinner) convertView.findViewById(R.id.spinner1);

				// ArrayAdapter<String> adapterSpin = new ArrayAdapter<String>(
				// context, android.R.layout.simple_spinner_dropdown_item,
				// animals);

				// setting the spinner color
				CustomizedSpinnerAdapter adapterSpin = new CustomizedSpinnerAdapter(
						(Activity) context,
						android.R.layout.simple_spinner_item, animals);

				if (spn != null) {
					if (adapterSpin != null) {
						spn.setAdapter(adapterSpin);
					} else {
						Toast.makeText(context, "Adapter SPN Is Null",
								Toast.LENGTH_SHORT).show();
					}
				} else {
					Toast.makeText(context, "SPN is NUll", Toast.LENGTH_SHORT)
							.show();
				}

				spn.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> parent,
							View view, int pos, long id) {

						int index = parent.getSelectedItemPosition();

						Toast.makeText(context,
								"You have selected " ,
								Toast.LENGTH_SHORT).show();

					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub

					}

				});

			} else if (position == 4) {
				convertView = layoutInflater.inflate(R.layout.list_item, null);

				holder.txtDesc = (TextView) convertView.findViewById(R.id.desc);
				holder.txtTitle = (TextView) convertView
						.findViewById(R.id.title);
				holder.imageView = (ImageView) convertView
						.findViewById(R.id.icon);
				convertView.setTag(holder);

//				holder.txtDesc.setText(rowItem.getDesc());
//				holder.txtTitle.setText(rowItem.getTitle());
				holder.imageView.setImageResource(rowItem.getImageId());

			} else {

				convertView = layoutInflater.inflate(R.layout.list_item2, null);

			}

		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		return convertView;
	}
}
