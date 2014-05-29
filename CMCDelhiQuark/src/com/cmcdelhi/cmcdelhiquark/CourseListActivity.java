package com.cmcdelhi.cmcdelhiquark;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class CourseListActivity extends Activity implements OnItemClickListener {

	ListView lv;

	Spinner spn;
	ImageView banner;
	ActionBar ab;

	SharedPreferences sp;

	// CharSequence[] items = { "Apple", "Ball", "Cat", "Dog", "Fish", "Image",
	// "Android", "Custom", " ListView", "Text", " ArrayAdapter" };

	public static final String[] titles = new String[] { "Java(JSE)",
			"Android 4.1", "J2EE(JEE)", "Asp.Net", "C/C++", "PHP-MySQL",
			"Embeded System", "Catia", "AutoCAD", "SQT" };
	public static final String[] descriptions = new String[] {
			"Learn Java 7 Fundamental advanced concepts including OOP Concepts Control Structures Classes, Interfaces & Packages Threads Exception Handling The I/O Package Applets GUI Programming with AWT & Swing and much more ",
			"Develop you own android app while learning Introduction to Android Programming, Activities, Fragments, Intents, User Interfaces, Persisting Data, Messaging,Location-Based Services ,Publishing applications and more Jelly Bean updates",
			"Learn Advanced JEE concepts like Java Server Pages,Servlets,EJB,Java Mail & Message Services,Internationalization and other advanced topics ",
			"The course Introduction to ASP.NET Language ,Support Controls ,Navigation (for User Interface),Error Handling,ADO.NET,Testing, Tracing & Debugging and more",
			"You will be learning Programming Fundamentals, Basic Input, Output Operators, Expressions & Flow Control Functions, Pointers & Arrays Files, Structures,Standard Template Library ,etc",
			"Mixed Fruits", "It is the largest herbaceous flowering plant",
			"It is an aggregate accessory fruit", "Mixed Fruits",
			"It is an aggregate accessory fruit" };

	public static final Integer[] images = { R.drawable.javatransparent,
			R.drawable.andy, R.drawable.jeelogo, R.drawable.csharp,
			R.drawable.ccpplogo, R.drawable.phpmysqllogo,
			R.drawable.embededlogo, R.drawable.catialogo, R.drawable.noimage,
			R.drawable.noimage };

	public static final String[] arrJavaSE = { "Select Course",
			"Java SE 6 Weeks", "Java SE 6 Months" };
	public static final String[] arrAndroid = { "Select Course",
			"Android 4.1 6 Weeks", "Android 4.1 6 Months" };
	public static final String[] arrJavaEE = { "Select Course",
			"J2EE(JEE) 6 Weeks", "J2EE(JEE) 6 Months" };

	public static final String[] arrDotNet = { "Select Course",
			"Asp.Net 6 Weeks", "Asp.Net 6 Months" };
	public static final String[] arrCCPP = { "Select Course", "C/C++ 6 Weeks",
			"C/C++ 6 Months" };
	public static final String[] arrPHPMySQL = { "Select Course",
			"PHP-MySQL 6 Weeks", "PHP-MySQL 6 Months" };
	public static final String[] arrEmbeded = { "Select Course", "ES 6 Weeks",
			"ES 6 Months" };
	public static final String[] arrCatia = { "Select Course", "Catia 6 Weeks",
			"Catia 6 Months" };
	public static final String[] arrAutoCAD = { "Select Course",
			"AutoCAD 6 Weeks", "AutoCAD 6 Months" };
	public static final String[] arrSQT = { "Select Course", "SQT 6 Weeks",
			"SQT 6 Months" };

	List<RowItemCourse> rowItems;
	List<TypeARowItem> rowItemsTypeA;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d("GUFRAN", "Inside On Create");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.course_list_layout);

		sp = getSharedPreferences("course_list", MODE_PRIVATE);

		banner = (ImageView) findViewById(R.id.banner);
		banner.setBackgroundColor(LockedColorSingleton.getInstance().colorVal);

		rowItems = new ArrayList<RowItemCourse>();
		for (int i = 0; i < titles.length; i++) {
			RowItemCourse item = new RowItemCourse(images[i], titles[i],
					descriptions[i]);

			rowItems.add(item);
		}

		Log.d("GUFRAN", "Trying to set arry list of typeARowItem");
		rowItemsTypeA = new ArrayList<TypeARowItem>();
		for (int i = 0; i < titles.length; i++) {
			TypeARowItem item = new TypeARowItem(images[i], titles[i],
					arrJavaSE, descriptions[i]);

			switch (i) {
			case 0:
				item = new TypeARowItem(images[i], titles[i], arrJavaSE,
						descriptions[i]);
				break;
			case 1:
				item = new TypeARowItem(images[i], titles[i], arrAndroid,
						descriptions[i]);

				break;
			case 2:
				item = new TypeARowItem(images[i], titles[i], arrJavaEE,
						descriptions[i]);

				break;
			case 3:
				item = new TypeARowItem(images[i], titles[i], arrDotNet,
						descriptions[i]);

				break;
			case 4:
				item = new TypeARowItem(images[i], titles[i], arrCCPP,
						descriptions[i]);

				break;
			case 5:
				item = new TypeARowItem(images[i], titles[i], arrPHPMySQL,
						descriptions[i]);

				break;
			case 6:
				item = new TypeARowItem(images[i], titles[i], arrEmbeded,
						descriptions[i]);

				break;
			case 7:
				item = new TypeARowItem(images[i], titles[i], arrCatia,
						descriptions[i]);

				break;
			case 8:
				item = new TypeARowItem(images[i], titles[i], arrAutoCAD,
						descriptions[i]);

				break;
			case 9:
				item = new TypeARowItem(images[i], titles[i], arrSQT,
						descriptions[i]);

				break;

			default:
				item = new TypeARowItem(images[i], titles[i], arrJavaSE,
						descriptions[i]);

				break;
			}
			rowItemsTypeA.add(item);
		}

		Log.d("GUFRAN", "Successfully set arry list of typeARowItem");

		lv = (ListView) findViewById(R.id.listView1);

		// CustomListViewAdapter adapter = new CustomListViewAdapter(this,
		// R.layout.list_item, rowItems);

		CourseListViewAdapter courseListViewAdapter = new CourseListViewAdapter(
				this, R.layout.sexylayout, rowItemsTypeA);

		Log.d("GUFRAN", "setting the adapter in list view");

		lv.setAdapter(courseListViewAdapter);

		// lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

		Log.d("GUFRAN", "the adapter successfully set in list view");

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
		ab.setTitle("Course List");
		// ab.setSubtitle("cmcdelhi.com");
		ab.setBackgroundDrawable(d);
		ab.setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public void onItemClick(AdapterView<?> adapter, View view, int position,
			long id) {
		Toast.makeText(getApplicationContext(), "Hello Dost",
				Toast.LENGTH_SHORT).show();
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
	public void onBackPressed() {
		finish();
		overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);

		super.onBackPressed();
	}

}
