package com.cmcdelhi.cmcdelhiquark;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomizedSpinnerAdapter extends ArrayAdapter<String> {

	Activity context;
	String[] data = null;
	Typeface tf;

	public CustomizedSpinnerAdapter(Activity context, int resource,
			String[] data2) {
		super(context, resource, data2);
		this.context = context;
		this.data = data2;
		tf = Typeface.createFromAsset(getContext().getAssets(),
				"fonts/Roboto-Thin.ttf");
	}

	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		if (row == null) {
			// inflate your customlayout for the textview
			LayoutInflater inflater = context.getLayoutInflater();
			row = inflater.inflate(R.layout.spinner_item, parent, false);
			row.setBackgroundColor(LockedColorSingleton.getInstance().colorVal);
		}
		// put the data in it
		String item = data[position];
		if (item != null) {
			TextView text1 = (TextView) row.findViewById(R.id.spinnerText);
			text1.setTextColor(Color.WHITE);
			text1.setText(item);
			text1.setTypeface(tf);
		}

		return row;
	}

}
