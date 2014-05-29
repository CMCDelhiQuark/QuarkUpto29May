package com.cmcdelhi.cmcdelhiquark;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class SplashScreenActivity extends FragmentActivity {

	private SplashScreenFragment mainFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Log.d("FB QUARK", "Inside Oncreate on Splash Screen ACtivity");
		// showHashCode();
		// App ID 635133926519343

		Log.d("FB QUARK", "Inside Oncreate on Splash Screen ACtivity");

		if (savedInstanceState == null) {
			// Add the fragment on initial activity setup
			mainFragment = new SplashScreenFragment();
			getSupportFragmentManager().beginTransaction()
					.add(android.R.id.content, mainFragment).commit();
			Log.d("FB QUARK", "Splash Fragment Attached in null");
		} else {
			// Or set the fragment from restored state info
			mainFragment = (SplashScreenFragment) getSupportFragmentManager()
					.findFragmentById(android.R.id.content);
			Log.d("FB QUARK", "Splash Fragment Attached if not null ");
		}

	}

	private void showHashCode() {
		try {
			PackageInfo info = getPackageManager().getPackageInfo(
					"com.cmcdelhi.guffyfbandyapp",
					PackageManager.GET_SIGNATURES);
			for (Signature signature : info.signatures) {
				MessageDigest md = MessageDigest.getInstance("SHA");
				md.update(signature.toByteArray());
				Log.d("GUFRAN",
						Base64.encodeToString(md.digest(), Base64.DEFAULT));

				Toast.makeText(
						getApplicationContext(),
						"" + Base64.encodeToString(md.digest(), Base64.DEFAULT),
						Toast.LENGTH_SHORT).show();
			}
		} catch (NameNotFoundException e) {
			Log.d("GUFRAN", "Name Not Found Exception");

		} catch (NoSuchAlgorithmException e) {
			Log.d("GUFRAN", "No Such ALGO Exception");
		}

	}

}
