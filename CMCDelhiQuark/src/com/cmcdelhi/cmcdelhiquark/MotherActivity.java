package com.cmcdelhi.cmcdelhiquark;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import org.json.JSONException;
import org.json.JSONObject;

import cm.cmcdelhi.dialog.CMCDialogCreator;

import com.cmcdelhi.locationSilent.CMCCellIdInfoUpdateService;
import com.cmcdelhi.locationSilent.PhoneSilenterByCellId;
import com.cmcdelhi.locationSilent.PhoneSilenterByGPS;
import com.cmcdelhi.notification.CMCNotificationGenerator;
import com.facebook.FacebookException;
import com.facebook.FacebookOperationCanceledException;
import com.facebook.FacebookRequestError;
import com.facebook.HttpMethod;
import com.facebook.Request;
import com.facebook.RequestAsyncTask;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.widget.WebDialog;
import com.facebook.widget.WebDialog.OnCompleteListener;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ShareActionProvider;
import android.widget.TextView;
import android.widget.Toast;

public class MotherActivity extends Activity {
	private ShareActionProvider mShareActionProvider;
	ActionBar ab;
	HorizontalScrollView hsv;
	Intent recievedFromSplash;

	private static final List<String> PERMISSIONS = Arrays
			.asList("publish_actions");
	private static final String PENDING_PUBLISH_KEY = "pendingPublishReauthorization";
	private boolean pendingPublishReauthorization = false;

	static UserFBData ufbd;
	CMCCellIdInfoUpdateService ccius;

	// Intent cellIdServiceIntent;
	//
	// ServiceConnection sc = new ServiceConnection() {
	//
	// @Override
	// public void onServiceConnected(ComponentName className, IBinder binder) {
	// ccius = ((CMCCellIdInfoUpdateService.CMCCellIdInfoUpdateServiceBinder)
	// binder)
	// .getService();
	// ccius.context = getBaseContext();
	// startService(cellIdServiceIntent);
	// }
	//
	// @Override
	// public void onServiceDisconnected(ComponentName name) {
	// ccius = null;
	// }
	//
	// };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d("FB QUARK", "Inside OnCreate of Moher Activity");

		super.onCreate(savedInstanceState);

		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		// setContentView(R.layout.activity_main);

		// setContentView(new CMCView(this));

		// setContentView(new CMCCourseTabView(this));

		LinearLayout layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);

		LayoutParams horizontalScrViewParams = new LinearLayout.LayoutParams(
				LayoutParams.FILL_PARENT, 130, 3);

		hsv = new HorizontalScrollView(this);
		hsv.setLayoutParams(horizontalScrViewParams);
		hsv.setBackgroundColor(Color.argb(255, 233, 231, 232));

		LinearLayout layoutInsideHSV = new LinearLayout(this);
		layoutInsideHSV.setOrientation(LinearLayout.HORIZONTAL);

		LayoutParams imgParams = new LinearLayout.LayoutParams(300,
				LayoutParams.FILL_PARENT);

		ImageView imgv1 = new ImageView(this);
		Random rnd = new Random();
		boolean val = rnd.nextBoolean();

		if (val) {
			imgv1.setImageResource(R.drawable.redgirl);
		} else {
			imgv1.setImageResource(R.drawable.greenboy);
		}

		imgv1.setLayoutParams(imgParams);
		// imgv1.setBackgroundColor(LockedColorSingleton.getInstance().colorVal);
		// imgv1.setBackgroundColor(Color.WHITE);
		imgv1.setBackgroundColor(Color.argb(255, 233, 231, 232));

		// Typeface tf = Typeface.createFromAsset(this.getAssets(),
		// "fonts/Roboto-Thin.ttf");
		Typeface tf = Typeface.createFromAsset(this.getAssets(),
				"fonts/Mathlete-Bulky.otf");

		LayoutParams txtDescParams = new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);

		TextView tvDesc = new TextView(this);
		tvDesc.setText(" Action  ");
		// How would you like to start an action here

		tvDesc.setTypeface(tf);
		tvDesc.setLayoutParams(txtDescParams);
		tvDesc.setTextColor(LockedColorSingleton.getInstance().colorVal);
		Display display = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		int height = size.y;
		tvDesc.setTextSize((float) (0.080 * height));
		// tvDesc.setTextScaleX(TextVie)
		tvDesc.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(getBaseContext(), "Registered ",
						Toast.LENGTH_SHORT).show();

			}
		});

		ImageView imgv2 = new ImageView(this);
		imgv2.setImageResource(R.drawable.redgirl);
		// imgv2.setLayoutParams(imgParams);
		imgv2.setBackgroundColor(Color.WHITE);

		// imgv2.setBackgroundColor(LockedColorSingleton.getInstance().colorVal);

		// ImageView imgv3 = new ImageView(this);
		// imgv3.setImageResource(R.drawable.orangegirl);
		// imgv3.setLayoutParams(imgParams);
		// //
		// imgv3.setBackgroundColor(LockedColorSingleton.getInstance().colorVal);
		// imgv3.setBackgroundColor(Color.WHITE);

		TextView tvUp = new TextView(this);
		tvUp.setLayoutParams(txtDescParams);
		tvUp.setText("   Share with us ");
		tvUp.setTextColor(LockedColorSingleton.getInstance().colorVal);
		tvUp.setTypeface(tf);
		tvUp.setTextSize((float) (0.080 * height));
		tvUp.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				Bundle params = new Bundle();
				params.putString("name", "Guffy Wave is amazing guy");
				params.putString("caption", "I have made an amazing app");
				params.putString(
						"description",
						"The Facebook SDK for Android makes it easier and faster to dCMC Limited is a leading IT solutions company and a subsidiary of Tata Consultancy Services Limited (TCS Ltd), one of the world's leading information technology consulting, services and business process outsourcing organisations. We are a part of the Tata group, India's best-known business conglomerate. Today, CMC Limited, an ISO 9001:2000, certified and CMMI Level V accredited organisation, is positioned as a premier IT solutions provider in the fast growing and competitive IT market");
				params.putString("link", "http://www.cmcdelhi.com/");
				params.putString("picture",
						"http://www.cmcdelhi.com/cmclogo.png");

				WebDialog feedDialog = (new WebDialog.FeedDialogBuilder(
						getBaseContext(), Session.getActiveSession(), params))
						.setOnCompleteListener(new OnCompleteListener() {

							@Override
							public void onComplete(Bundle values,
									FacebookException error) {
								if (error == null) {
									// When the story is posted, echo the
									// success
									// and the post Id.
									final String postId = values
											.getString("post_id");
									if (postId != null) {
										Toast.makeText(getBaseContext(),
												"Posted story, id: " + postId,
												Toast.LENGTH_SHORT).show();
									} else {
										// User clicked the Cancel button
										Toast.makeText(getBaseContext(),
												"Publish cancelled",
												Toast.LENGTH_SHORT).show();
									}
								} else if (error instanceof FacebookOperationCanceledException) {
									// User clicked the "x" button
									Toast.makeText(getBaseContext(),
											"Publish cancelled",
											Toast.LENGTH_SHORT).show();
								} else {
									// Generic, ex: network error
									Toast.makeText(getBaseContext(),
											"Error posting story",
											Toast.LENGTH_SHORT).show();
								}
							}

						}).build();
				feedDialog.show();

				// ///////////xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx/////////////////////////////////////////////////////////
				// Session session = Session.getActiveSession();
				//
				// if (session != null) {
				//
				// // Check for publish permissions
				// List<String> permissions = session.getPermissions();
				// if (!isSubsetOf(PERMISSIONS, permissions)) {
				// pendingPublishReauthorization = true;
				// Session.NewPermissionsRequest newPermissionsRequest = new
				// Session.NewPermissionsRequest(
				// MotherActivity.this, PERMISSIONS);
				// session.requestNewPublishPermissions(newPermissionsRequest);
				// return;
				// }
				//
				// Bundle postParams = new Bundle();
				// postParams.putString("name", "Facebook SDK for Android");
				// postParams.putString("caption",
				// "Build great social apps and get more installs.");
				// postParams
				// .putString(
				// "description",
				// "The Facebook SDK for Android makes it easier and faster to develop Facebook integrated Android apps.");
				// postParams.putString("link",
				// "https://developers.facebook.com/android");
				// postParams
				// .putString("picture",
				// "https://raw.github.com/fbsamples/ios-3.x-howtos/master/Images/iossdk_logo.png");
				//
				// Request.Callback callback = new Request.Callback() {
				// public void onCompleted(Response response) {
				// JSONObject graphResponse = response
				// .getGraphObject().getInnerJSONObject();
				// String postId = null;
				// try {
				// postId = graphResponse.getString("id");
				// } catch (JSONException e) {
				// Log.i("JSONException",
				// "JSON error " + e.getMessage());
				// }
				// FacebookRequestError error = response.getError();
				// if (error != null) {
				// Toast.makeText(getBaseContext(),
				// error.getErrorMessage(),
				// Toast.LENGTH_SHORT).show();
				// } else {
				// Toast.makeText(getBaseContext(), postId,
				// Toast.LENGTH_LONG).show();
				// }
				// }
				// };
				//
				// Request request = new Request(session, "me/feed",
				// postParams, HttpMethod.POST, callback);
				//
				// RequestAsyncTask task = new RequestAsyncTask(request);
				// task.execute();
				// }

			}
		});

		layoutInsideHSV.addView(imgv1);
		// layoutInsideHSV.addView(imgv2);
		// layoutInsideHSV.addView(imgv3);
		layoutInsideHSV.addView(tvDesc);
		layoutInsideHSV.addView(tvUp);

		hsv.addView(layoutInsideHSV);

		LayoutParams cmcvParams = new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 6);
		CMCView cmcv = new CMCView(this);
		cmcv.setLayoutParams(cmcvParams);

		// sending user data to CMC View
		recievedFromSplash = getIntent();
		Bundle data = recievedFromSplash.getExtras();
		ufbd = (UserFBData) data.getParcelable("fbUser");

		Log.d("FB STudy ", "Refered The ufbd inside Mother onCreate");

		Toast.makeText(getBaseContext(), "Name " + ufbd.getName(),
				Toast.LENGTH_SHORT).show();
		Toast.makeText(getBaseContext(), "Id " + ufbd.getId(),
				Toast.LENGTH_SHORT).show();
		Toast.makeText(getBaseContext(), "Email " + ufbd.getEmail(),
				Toast.LENGTH_SHORT).show();
		// ///////////////

		LayoutParams textParams = new LinearLayout.LayoutParams(
				LayoutParams.FILL_PARENT, 17, 1);
		TextView tv = new TextView(this);
		tv.setBackgroundColor(Color.WHITE);
		tv.setLayoutParams(textParams);

		layout.addView(hsv);
		layout.addView(cmcv);
		layout.addView(tv);

		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);

		this.addContentView(layout, layoutParams);

		// working with action bar

		ab = getActionBar();
		ab.setDisplayShowCustomEnabled(true);

		CMCActionBarGenerator cabg = new CMCActionBarGenerator(
				getApplicationContext());

		// ab.setCustomView(cabg.getCMCActionBar());

		ab.setIcon(R.drawable.cmclogo);

		// Drawable d =
		// getResources().getDrawable(R.drawable.backgroundjpgbigpng);
		ColorDrawable d = new ColorDrawable(
				LockedColorSingleton.getInstance().colorVal);
		ab.setTitle("CMC Delhi");
		ab.setSubtitle("cmcdelhi.com");
		ab.setBackgroundDrawable(d);

		Log.d("FB STudy ", "Inside oN cREATE Of MOthyer ACtivity");

		// /starting the CMCCellIdInfo Service
		// START THE SERVICE ONLY IF THE GPS IS AVAILABLE ie(put it inside a
		// reciver so that when GPS is made available then service get started)
		// cellIdServiceIntent = new Intent(MotherActivity.this,
		// CMCCellIdInfoUpdateService.class);
		// bindService(cellIdServiceIntent, sc, Context.BIND_AUTO_CREATE);

		// PhoneSilentByCellIdIntent
		// Intent phoneSilentByCellIdIntent = new Intent(MotherActivity.this,
		// PhoneSilenterByCellId.class);
		// startService(phoneSilentByCellIdIntent);

		// PhoneSilentByGPSIntent
		// Intent phoneSilentByGPSIntent = new Intent(MotherActivity.this,
		// PhoneSilenterByGPS.class);
		// startService(phoneSilentByGPSIntent);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		// getMenuInflater().inflate(R.menu.activity_main, menu);
		// Locate MenuItem with ShareActionProvider
		// MenuItem item = menu.findItem(R.id.menu_item_share);

		mShareActionProvider = new ShareActionProvider(this);

		MenuItem mnu1 = menu.add(0, 0, 0, "Share");
		mnu1.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

		mShareActionProvider.setShareIntent(createShareIntent());

		mnu1.setActionProvider(mShareActionProvider);

		MenuItem mnu2 = menu.add(0, 1, 1, "Action Bar");
		mnu2.setIcon(R.drawable.action_collapse);
		mnu2.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

		// Return true to display menu
		return true;
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch (item.getItemId()) {
		case 1:
			if (hsv.getVisibility() == HorizontalScrollView.VISIBLE) {
				hsv.setVisibility(HorizontalScrollView.GONE);
				item.setIcon(R.drawable.action_expand);
			} else {
				hsv.setVisibility(HorizontalScrollView.VISIBLE);
				item.setIcon(R.drawable.action_collapse);
			}

			break;

		default:
			break;
		}
		return super.onMenuItemSelected(featureId, item);
	}

	private Intent createShareIntent() {

		Intent sendIntent = new Intent();
		sendIntent.setAction(Intent.ACTION_SEND);
		sendIntent
				.putExtra(
						Intent.EXTRA_TEXT,
						"Hey check out my app at: https://play.google.com/store/apps/details?id=com.google.android.apps.plus");
		sendIntent.setType("text/plain");

		return sendIntent;
	}

	@Override
	protected void onPause() {
		Log.d("FB QUARK", "Inside OnPuause of Mother Activity");

		super.onPause();

	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();

		// CMCDialogCreator cdc = new CMCDialogCreator(MotherActivity.this);
		// Dialog d = cdc.generateDialog(0);
		// d.show();

		Intent startMain = new Intent(Intent.ACTION_MAIN);
		startMain.addCategory(Intent.CATEGORY_HOME);
		startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(startMain);
	}

	@Override
	protected void onResume() {
		Log.d("FB QUARK", "Inside OnResume of Moher Activity");
		super.onResume();
	}

	private boolean isSubsetOf(Collection<String> subset,
			Collection<String> superset) {
		for (String string : subset) {
			if (!superset.contains(string)) {
				return false;
			}
		}
		return true;
	}

}
