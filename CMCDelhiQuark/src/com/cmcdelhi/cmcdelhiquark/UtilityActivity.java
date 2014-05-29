package com.cmcdelhi.cmcdelhiquark;



import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.view.Menu;
import android.widget.LinearLayout;
import android.widget.TextView;

public class UtilityActivity extends Activity {

	LockedColorSingleton lcs;
	
	LinearLayout l1,l2,linears,linearsss;
	TextView tv1, tv2, tv3, tv4;
	Typeface tpf;
	
	ActionBar ab;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_utility);
ab= getActionBar();
		
		ab.setTitle("Utilities");
		ab.setIcon(R.drawable.ic_launcher);
		ab.setDisplayHomeAsUpEnabled(true);
		
		ColorDrawable d= new ColorDrawable(Color.BLUE);
		
		ab.setBackgroundDrawable(d);
		
		linears= (LinearLayout) findViewById(R.id.linears);
		
		tv3 = (TextView) findViewById(R.id.textView3);
		tv1 = (TextView) findViewById(R.id.textview1);
		tv3.setText("ON");
		tv3.setTypeface(tpf);
		tv3.setTextSize(24f);
		// tv5 = (TextView) findViewById(R.id.textView5);
		// tv6 = (TextView) findViewById(R.id.textView6);
		// tv7 = (TextView) findViewById(R.id.textView7);
		// tv8 = (TextView) findViewById(R.id.textView8);
		// tv9 = (TextView) findViewById(R.id.textView9);
		// tv10 = (TextView) findViewById(R.id.textView10);
		//
		// tv5.setBackgroundColor(Color.BLACK);
		// tv6.setBackgroundColor(Color.RED);
		// tv7.setBackgroundColor(Color.BLUE);
		// tv8.setBackgroundColor(Color.GREEN);
		// tv9.setBackgroundColor(Color.GRAY);
		// tv10.setBackgroundColor(Color.YELLOW);

		tpf = Typeface.createFromAsset(getAssets(), "fonts/Mathlete-Bulky.otf");

		tv1.setBackgroundColor(Color.WHITE);

		tv2 = (TextView) findViewById(R.id.textView2);
		tv4 = (TextView) findViewById(R.id.textView4);
		tv2.setTypeface(tpf);
		tv2.setTextSize(31f);
		tv2.setTextColor(Color.WHITE);
		
		tv4.setTypeface(tpf);
		tv4.setTextSize(30f);
		tv4.setTextColor(Color.WHITE);
		
		l2= (LinearLayout) findViewById(R.id.linear14);
		
		linearsss= (LinearLayout) findViewById(R.id.linearsss);
		
		linears.setBackgroundColor(LockedColorSingleton.getInstance().colorVal);
		linearsss.setBackgroundColor(LockedColorSingleton.getInstance().colorVal);
		

//		tv3.setOnTouchListener(new OnTouchListener() {
//
//			@Override
//			public boolean onTouch(View v, MotionEvent event) {
//
//				switch (event.getAction()) {
//				case MotionEvent.ACTION_DOWN:
//
//					l1.setBackground(getResources().getDrawable(
//							R.drawable.button2_circle));
//
//					break;
//
//				case MotionEvent.ACTION_UP:
//
//					l1.setBackground(getResources().getDrawable(
//							R.drawable.button_circle));
//
//					tv3.setText("OFF");
//
//				default:
//					break;
//				}
//				return false;
//			}
//		});

		// ColorDrawable d= new ColorDrawable(Color.BLUE);
		// l2.setBackgroundDrawable(d);
		// l3.setBackgroundColor(Color.CYAN);
		// l4.setBackgroundColor(Color.GREEN);
		// l5.setBackgroundColor(Color.RED);
		// l6.setBackgroundColor(Color.YELLOW);
		// l7.setBackgroundColor(Color.LTGRAY);
	
	}


}
