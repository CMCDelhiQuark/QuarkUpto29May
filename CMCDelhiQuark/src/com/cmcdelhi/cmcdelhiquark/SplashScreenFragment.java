package com.cmcdelhi.cmcdelhiquark;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.FacebookException;
import com.facebook.FacebookOperationCanceledException;
import com.facebook.FacebookRequestError;
import com.facebook.HttpMethod;
import com.facebook.LoggingBehavior;
import com.facebook.Request;
import com.facebook.RequestAsyncTask;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.Settings;
import com.facebook.UiLifecycleHelper;
import com.facebook.android.FacebookError;
import com.facebook.model.GraphObject;
import com.facebook.model.GraphObjectList;
import com.facebook.model.GraphUser;
import com.facebook.widget.LoginButton;
import com.facebook.widget.WebDialog;
import com.facebook.widget.WebDialog.OnCompleteListener;

public class SplashScreenFragment extends  Fragment {

	LinearLayout ll;
	GradientDrawable gd;
	TextView cmcTitle;
	TextView quarkTitle, buddyApp, cmcAddress;
	// TextView expText;
	TextView skipTitle;
	// ImageView imgv;

	// https://graph.facebook.com/me?fields=id,name

	private static final String TAG = "Splash Fragment";
	private UiLifecycleHelper uiHelper;
	private Session.StatusCallback callback = new Session.StatusCallback() {
		@Override
		public void call(Session session, SessionState state,
				Exception exception) {
			onSessionStateChange(session, state, exception);
		}

	};

	// String userName;
	// String userEmail;
	// String
	//

	// private Button publishButton;
	// private Button shareButton;
	// private Button sendRequestButton;
	// private TextView userInfoTextView;
	// static ImageView profilePic;
	// WebView wv;

	// share permissions
	private static final List<String> PERMISSIONS = Arrays
			.asList("publish_actions");
	private static final String PENDING_PUBLISH_KEY = "pendingPublishReauthorization";
	private boolean pendingPublishReauthorization = false;

	// User FB Data object
	UserFBData ufbd;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		uiHelper = new UiLifecycleHelper(getActivity(), callback);
		uiHelper.onCreate(savedInstanceState);
		Log.d("FB QUARK", "Inside Oncreate on Splash Screen Fragment");

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.d("FB QUARK", "Inside starting OncreateView of SplashFragment");

		View view = inflater.inflate(R.layout.splash_screen, container, false);

		LoginButton authButton = (LoginButton) view
				.findViewById(R.id.authButton);

		authButton.setFragment(this);
		authButton.setReadPermissions(Arrays.asList("email"));

		// publishButton = (Button) view.findViewById(R.id.publishButton);
		// publishButton.setOnClickListener(new View.OnClickListener() {
		// @Override
		// public void onClick(View v) {
		// publishFeedDialog();
		// }
		// });

		// shareButton = (Button) view.findViewById(R.id.shareButton);
		//
		// shareButton = (Button) view.findViewById(R.id.shareButton);
		// shareButton.setOnClickListener(new View.OnClickListener() {
		// @Override
		// public void onClick(View v) {
		// publishStory();
		// }
		// });

		if (savedInstanceState != null) {
			pendingPublishReauthorization = savedInstanceState.getBoolean(
					PENDING_PUBLISH_KEY, false);
		}

		// // /sendRequestButon
		// sendRequestButton = (Button)
		// view.findViewById(R.id.sendRequestButton);
		// sendRequestButton.setOnClickListener(new View.OnClickListener() {
		// @Override
		// public void onClick(View v) {
		// sendRequestDialog();
		// }
		// });

		// userInfoTextView = (TextView)
		// view.findViewById(R.id.userInfoTextView);
		// // profilePic = (ImageView) view.findViewById(R.id.profilePicture);
		// // wv = (WebView) view.findViewById(R.id.webView1);
		// // WebSettings ws = wv.getSettings();
		// // ws.setBuiltInZoomControls(true);
		// // ws.setJavaScriptEnabled(true);
		// //
		// wv.loadUrl("http://thesecret.tv/stories/stories-read.html?id=23950");

		// ///////////////////////////////////////////////;;;;;;;;;;;;;;;;

		Typeface tf = Typeface.createFromAsset(getActivity().getAssets(),
				"fonts/Mathlete-Bulky.otf");
		Typeface tfSkinny = Typeface.createFromAsset(getActivity().getAssets(),
				"fonts/Mathlete-Skinny.otf");
		// pKnowUs.setTypeface(tf);
		// imgv = (ImageView) view.findViewById(R.id.imageView3);

		cmcTitle = (TextView) view.findViewById(R.id.cmcTitle);
		quarkTitle = (TextView) view.findViewById(R.id.quarkTitle);
		buddyApp = (TextView) view.findViewById(R.id.buddyApp);
		cmcAddress = (TextView) view.findViewById(R.id.cmcAddress);
		skipTitle = (TextView) view.findViewById(R.id.textView1);
		// expText = (TextView) view.findViewById(R.id.expText);
		skipTitle.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent maaKiOor = new Intent(
						"com.cmcdelhi.cmcdelhiquark.MotherActivity");

				maaKiOor.putExtra("fbUser", ufbd);
				startActivity(maaKiOor);

				getActivity().overridePendingTransition(R.anim.push_left_in,
						R.anim.push_left_out);

			}
		});

		GradientDrawable gd = new GradientDrawable(
				GradientDrawable.Orientation.TOP_BOTTOM, new int[] {
						Color.BLACK,
						LockedColorSingleton.getInstance().colorVal });
		gd.setGradientType(GradientDrawable.RADIAL_GRADIENT);
		gd.setGradientRadius(20.0f);
		gd.setGradientCenter(1000f, 1000f);
		gd.setCornerRadius(1f);

		ll = (LinearLayout) view.findViewById(R.id.mainlayout);

		cmcTitle.setTextColor(LockedColorSingleton.getInstance().colorVal);

		cmcTitle.setTypeface(tf);
		cmcTitle.setTextSize(60);
		quarkTitle.setTypeface(tf);
		quarkTitle.setTextSize(100);
		buddyApp.setTypeface(tfSkinny);
		buddyApp.setTextSize(40);

		cmcAddress.setTextColor(LockedColorSingleton.getInstance().colorVal);
		cmcAddress.setTypeface(tf);
		cmcAddress.setTextSize(25);

		// ll.setBackground(gd);

		// g = new GradientDrawable(Orientation.TL_BR, new int[] { Color.WHITE,
		// Color.BLUE });
		// g.setGradientType(GradientDrawable.RADIAL_GRADIENT);
		// g.setGradientRadius(20.0f);
		// g.setGradientCenter(0.5f, 0.5f);

		ll.setBackground(gd);

		Log.d("FB QUARK", "Inside completed OncreateView of SplashFragment");

		// ////////////////////////////////////////////////''''''''''''''';;;;;;;;;;;

		ufbd = new UserFBData();

		return view;
	}

	private void onSessionStateChange(Session session, SessionState state,
			Exception exception) {

		Log.d("FB QUARK",
				"Inside start of onSessionState Changed of SplashFragment");

		if (state.isOpened()) {

			// Intent maaKiOor = new Intent(
			// "com.cmcdelhi.cmcdelhiquark.MotherActivity");
			// startActivity(maaKiOor);

			Request.executeMeRequestAsync(session,
					new Request.GraphUserCallback() {

						@Override
						public void onCompleted(GraphUser user,
								Response response) {
							if (user != null) {
								// Display the parsed user info
								Settings.addLoggingBehavior(LoggingBehavior.REQUESTS);
								// expText.setText(buildUserInfoDisplay(user));
								// buildUserInfoDisplay(user);
								Intent maaKiOor = new Intent(
										"com.cmcdelhi.cmcdelhiquark.MotherActivity");

								maaKiOor.putExtra("fbUser", ufbd);
								startActivity(maaKiOor);

								// new ProfilePictureGrabber().execute(user);

							}
						}
					});

			// new ProfilePictureGrabber().execute(user);

			// Request user data and show the results

		} else if (state.isClosed()) {

		}

		// share buton visibility
		if (state.isOpened()) {
			// shareButton.setVisibility(View.VISIBLE);

			if (pendingPublishReauthorization
					&& state.equals(SessionState.OPENED_TOKEN_UPDATED)) {

				pendingPublishReauthorization = false;

				// publishStory();
			}

		} else if (state.isClosed()) {
			// shareButton.setVisibility(View.INVISIBLE);
		}

		// sendRequestVisibility

		// User Info

		if (state.isOpened()) {

		} else if (state.isClosed()) {

		}
	}

	class ProfilePictureGrabber extends AsyncTask<GraphUser, Void, String> {

		Bitmap bmp;

		@Override
		protected String doInBackground(GraphUser... arr) {

			// set the profilepicture

			try {

				URL image_url = null;

				String name = arr[0].getId().replace(" ", "");

				image_url = new URL("https://graph.facebook.com/" + name
						+ "/picture");

				// https://graph.facebook.com/GuffyWave/picture

				bmp = BitmapFactory.decodeStream(image_url.openConnection()
						.getInputStream());

			} catch (MalformedURLException e) {

				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FacebookError e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return null;
		}

		@Override
		protected void onPostExecute(String result) {

			super.onPostExecute(result);
			// imgv.setImageBitmap(bmp);

			try {

				File sd = Environment.getExternalStorageDirectory();
				File f = new File(sd, "profilePic.bmp");
				FileOutputStream out = new FileOutputStream(f);
				bmp.compress(Bitmap.CompressFormat.PNG, 90, out);
				out.close();

				Log.d("GUFRAN FILE", "FILE SAVED");

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void onResume() {
		super.onResume();
		// For scenarios where the main activity is launched and user
		// session is not null, the session state change notification
		// may not be triggered. Trigger it if it's open/closed.
		Session session = Session.getActiveSession();

		if (session != null && (session.isOpened() || session.isClosed())) {

			onSessionStateChange(session, session.getState(), null);
		}
		Log.d("FB QUARK", "Inside OnResume of Spalsh Fragment");

		uiHelper.onResume();
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		Log.d("FB QUARK", "Inside OnActivity Result of Spalsh Fragment");
		uiHelper.onActivityResult(requestCode, resultCode, data);

	}

	@Override
	public void onPause() {
		super.onPause();
		Log.d("FB QUARK", "Inside OnPause of Spalsh Fragment");
		uiHelper.onPause();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.d("FB QUARK", "Inside OnDestroy of Spalsh Fragment");
		uiHelper.onDestroy();
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putBoolean(PENDING_PUBLISH_KEY, pendingPublishReauthorization);
		Log.d("FB QUARK", "Inside OnSaveInstanceState of Spalsh Fragment");
		uiHelper.onSaveInstanceState(outState);
	}

	public  void publishFeedDialog() {
		Bundle params = new Bundle();
		params.putString("name", "Guffy Wave is amazing guy");
		params.putString("caption", "I have made an amazing app");
		params.putString(
				"description",
				"The Facebook SDK for Android makes it easier and faster to dCMC Limited is a leading IT solutions company and a subsidiary of Tata Consultancy Services Limited (TCS Ltd), one of the world's leading information technology consulting, services and business process outsourcing organisations. We are a part of the Tata group, India's best-known business conglomerate. Today, CMC Limited, an ISO 9001:2000, certified and CMMI Level V accredited organisation, is positioned as a premier IT solutions provider in the fast growing and competitive IT market");
		params.putString("link", "http://www.cmcdelhi.com/");
		params.putString("picture", "http://www.cmcdelhi.com/cmclogo.png");

		
		
		
		WebDialog feedDialog = (new WebDialog.FeedDialogBuilder(getActivity(),
				Session.getActiveSession(), params)).setOnCompleteListener(
				new OnCompleteListener() {

					@Override
					public void onComplete(Bundle values,
							FacebookException error) {
						if (error == null) {
							// When the story is posted, echo the success
							// and the post Id.
							final String postId = values.getString("post_id");
							if (postId != null) {
								Toast.makeText(getActivity(),
										"Posted story, id: " + postId,
										Toast.LENGTH_SHORT).show();
							} else {
								// User clicked the Cancel button
								Toast.makeText(
										getActivity().getApplicationContext(),
										"Publish cancelled", Toast.LENGTH_SHORT)
										.show();
							}
						} else if (error instanceof FacebookOperationCanceledException) {
							// User clicked the "x" button
							Toast.makeText(
									getActivity().getApplicationContext(),
									"Publish cancelled", Toast.LENGTH_SHORT)
									.show();
						} else {
							// Generic, ex: network error
							Toast.makeText(
									getActivity().getApplicationContext(),
									"Error posting story", Toast.LENGTH_SHORT)
									.show();
						}
					}

				}).build();
		feedDialog.show();
	}

	public  void publishStory() {
		Session session = Session.getActiveSession();

		if (session != null) {

			// Check for publish permissions
			List<String> permissions = session.getPermissions();
			if (!isSubsetOf(PERMISSIONS, permissions)) {
				pendingPublishReauthorization = true;
				Session.NewPermissionsRequest newPermissionsRequest = new Session.NewPermissionsRequest(
						this, PERMISSIONS);
				session.requestNewPublishPermissions(newPermissionsRequest);
				return;
			}

			Bundle postParams = new Bundle();
			postParams.putString("name", "Facebook SDK for Android");
			postParams.putString("caption",
					"Build great social apps and get more installs.");
			postParams
					.putString(
							"description",
							"The Facebook SDK for Android makes it easier and faster to develop Facebook integrated Android apps.");
			postParams.putString("link",
					"https://developers.facebook.com/android");
			postParams
					.putString("picture",
							"https://raw.github.com/fbsamples/ios-3.x-howtos/master/Images/iossdk_logo.png");

			Request.Callback callback = new Request.Callback() {
				public void onCompleted(Response response) {
					JSONObject graphResponse = response.getGraphObject()
							.getInnerJSONObject();
					String postId = null;
					try {
						postId = graphResponse.getString("id");
					} catch (JSONException e) {
						Log.i(TAG, "JSON error " + e.getMessage());
					}
					FacebookRequestError error = response.getError();
					if (error != null) {
						Toast.makeText(getActivity().getApplicationContext(),
								error.getErrorMessage(), Toast.LENGTH_SHORT)
								.show();
					} else {
						Toast.makeText(getActivity().getApplicationContext(),
								postId, Toast.LENGTH_LONG).show();
					}
				}
			};

			Request request = new Request(session, "me/feed", postParams,
					HttpMethod.POST, callback);

			RequestAsyncTask task = new RequestAsyncTask(request);
			task.execute();
		}

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

	private void sendRequestDialog() {
		Bundle params = new Bundle();
		params.putString("message",
				"Learn how to make your Android apps social");

		WebDialog requestsDialog = (new WebDialog.RequestsDialogBuilder(
				getActivity(), Session.getActiveSession(), params))
				.setOnCompleteListener(new OnCompleteListener() {

					@Override
					public void onComplete(Bundle values,
							FacebookException error) {
						if (error != null) {
							if (error instanceof FacebookOperationCanceledException) {
								Toast.makeText(
										getActivity().getApplicationContext(),
										"Request cancelled", Toast.LENGTH_SHORT)
										.show();
							} else {
								Toast.makeText(
										getActivity().getApplicationContext(),
										"Network Error", Toast.LENGTH_SHORT)
										.show();
							}
						} else {
							final String requestId = values
									.getString("request");
							if (requestId != null) {
								Toast.makeText(
										getActivity().getApplicationContext(),
										"Request sent", Toast.LENGTH_SHORT)
										.show();
							} else {
								Toast.makeText(
										getActivity().getApplicationContext(),
										"Request cancelled", Toast.LENGTH_SHORT)
										.show();
							}
						}
					}

				}).build();
		requestsDialog.show();
	}

	private String buildUserInfoDisplay(GraphUser user) {
		StringBuilder userInfo = new StringBuilder("");

		// Example: typed access (name)
		// - no special permissions required
		userInfo.append(String.format("ID  : %s\n\n", user.getId()));
		ufbd.setId(Long.parseLong(user.getId()));

		userInfo.append(String.format("Name: %s\n\n", user.getName()));
		ufbd.setName(user.getName());

		userInfo.append(String.format("First Name : %s\n\n",
				user.getFirstName()));
		ufbd.setFname(user.getFirstName());

		userInfo.append(String.format("Last Name : %s\n\n", user.getLastName()));

		ufbd.setLname(user.getLastName());

		// Example: typed access (birthday)
		// - requires user_birthday permission
		// userInfo.append(String.format("Birthday: %s\n\n",
		// user.getBirthday()));

		// Example: partially typed access, to location field,
		// name key (location)
		// - requires user_location permission
		userInfo.append(String.format("Location: %s\n\n", user.getLocation()
				.getProperty("name")));

		ufbd.setLocationName(user.getLocation().getProperty("name").toString());

		// Example: access via property name (locale)
		// - no special permissions required
		userInfo.append(String.format("Locale: %s\n\n",
				user.getProperty("locale")));

		ufbd.setLocale(user.getProperty("locale").toString());

		userInfo.append(String.format("Email : %s\n\n",
				user.getProperty("email")));

		ufbd.setEmail(user.getProperty("email").toString());

		userInfo.append(String.format("Gender : %s\n\n",
				user.getProperty("gender")));

		ufbd.setGender(user.getProperty("gender").toString());

		userInfo.append(String.format("Link : %s\n\n", user.getProperty("link")));

		ufbd.setLink(user.getProperty("link").toString());

		// userInfo.append(String.format("Relationship Status : %s\n\n",
		// user.getProperty("relationship_status")));

		// Example: access via key for array (languages)
		// - requires user_likes permission
		// JSONArray friendsList = (JSONArray) user.getProperty("friendlists");
		// if (friendsList.length() > 0) {
		// ArrayList<String> friendNames = new ArrayList<String>();
		// for (int i = 0; i < friendsList.length(); i++) {
		// JSONObject friend = friendsList.optJSONObject(i);
		// // Add the language name to a list. Use JSON
		// // methods to get access to the name field.
		// friendNames.add(friend.optString("name"));
		// }
		// userInfo.append(String.format("Languages: %s\n\n",
		// friendNames.toString()));
		// }
		return userInfo.toString();
	}

	private interface MyGraphLanguage extends GraphObject {
		// Getter for the ID field
		String getId();

		// Getter for the Name field
		String getName();
	}

	private interface MyGraphUser extends GraphUser {
		// Create a setter to enable easy extraction of the languages field
		GraphObjectList<MyGraphLanguage> getLanguages();
	}

}
