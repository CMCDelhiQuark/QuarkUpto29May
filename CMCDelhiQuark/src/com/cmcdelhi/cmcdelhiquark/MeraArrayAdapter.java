package com.cmcdelhi.cmcdelhiquark;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MeraArrayAdapter extends ArrayAdapter<RowItem> {

	Context context;
	Typeface tf;

	public MeraArrayAdapter(Context context, int textViewResourceId,
			List<RowItem> objects) {
		super(context, textViewResourceId, objects);
		this.context = context;

		tf = Typeface.createFromAsset(context.getAssets(),
				"fonts/Mathlete-Bulky.otf");
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder holder = null;

		RowItem rowItem = getItem(position);

		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(context.LAYOUT_INFLATER_SERVICE);

		if (convertView == null) {
			convertView = inflater.inflate(R.layout.list_items, null);
			holder = new ViewHolder();

			convertView
					.setBackgroundColor(LockedColorSingleton.getInstance().colorVal);

			holder.imageView = (ImageView) convertView
					.findViewById(R.id.imageView1);
			holder.tv = (TextView) convertView.findViewById(R.id.textView1);
			holder.tv.setTextColor(Color.WHITE);
			holder.tv.setTypeface(tf);
			holder.tv.setTextSize(50f);
			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		// holder.bt.setText(rowItem.getButInfo());
		holder.imageView.setImageResource(rowItem.imageId);
		holder.tv.setText(rowItem.info);

		return convertView;
	}
}
