package com.cmcdelhi.cmcdelhiquark;

import java.util.ArrayList;
import java.util.List;

import android.net.Uri;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class FullMenuActivity extends Activity {

	ListView lv;
	ActionBar ab;

	// static final Integer[] imageIds = { R.drawable.next_nav,
	// R.drawable.next_nav, R.drawable.next_nav, R.drawable.next_nav };

	static final String[] textDescription = { "Courses ", "Register",
			"Know Us", "Feedback", "Mail Us ", "Call Now", "Locate Us ",
			"Utilities ", "Terms and Conditions ", "Share with Us" };

	List<RowItem> rowItems;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.course_list_activity);

		rowItems = new ArrayList<RowItem>();

		for (int i = 0; i < textDescription.length; i++) {
			RowItem item = new RowItem();
			item.setImageId(R.drawable.next_nav);
			// item.setButInfo(buttonDescription[i]);
			item.setInfo(textDescription[i]);

			rowItems.add(item);
		}

		lv = (ListView) findViewById(R.id.listView1);

		// ArrayAdapter<String> adapter = new ArrayAdapter<String>(
		// getBaseContext(), android.R.layout.simple_dropdown_item_1line,
		// items);

		MeraArrayAdapter adapter = new MeraArrayAdapter(this,
				R.layout.list_items, rowItems);

		lv.setAdapter(adapter);

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
		ab.setTitle("Menu");
		// ab.setSubtitle("cmcdelhi.com");
		ab.setBackgroundDrawable(d);
		ab.setDisplayHomeAsUpEnabled(true);

		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long id) {

				Toast.makeText(getBaseContext(),
						"You selected " + textDescription[position],
						Toast.LENGTH_SHORT).show();

				switch (position) {
				case 0:
					// courses
					Intent i2 = new Intent(
							"com.cmcdelhi.cmcdelhiquark.CourseListActivity");
					startActivity(i2);
					overridePendingTransition(R.anim.push_left_in,
							R.anim.push_left_out);

					break;
				case 1:
					// rgister
					break;

				case 2:
					// know us

					Intent i3 = new Intent(
							"com.cmcdelhi.cmcdelhiquark.KnowUsActivity");
					startActivity(i3);
					overridePendingTransition(R.anim.push_left_in,
							R.anim.push_left_out);

					break;

				case 3:
					// feedback
					break;
				case 4:
					// mail us

					break;

				case 5:
					// call now
					Intent i5 = new Intent(android.content.Intent.ACTION_DIAL);
					i5.setData(Uri.parse("tel:01165905335"));
					startActivity(i5);
					break;

				case 6:
					// locate us

					break;
				case 7:
					// utilities

					break;
				case 8:
					// terms and conditions

					break;

				case 9:
					// share with us

					break;

				case 10:

					break;

				default:
					break;
				}

			}
		});

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
