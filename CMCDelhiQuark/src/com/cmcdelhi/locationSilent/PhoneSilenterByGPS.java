package com.cmcdelhi.locationSilent;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Toast;

public class PhoneSilenterByGPS extends Service {

	LocationManager lmgr;
	LocationListener locLis;
	Location currentLocation;

	@Override
	public IBinder onBind(Intent intent) {

		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {

		Toast.makeText(this, "PhoneSilenterByGPS Service Started ",
				Toast.LENGTH_LONG).show();

		lmgr = (LocationManager) getSystemService(LOCATION_SERVICE);

		locLis = new LocationListener() {

			@Override
			public void onLocationChanged(Location location) {

				currentLocation = location;

				if (currentLocation != null) {

					float[] result = new float[5];
					Location.distanceBetween(currentLocation.getLatitude(),
							currentLocation.getLongitude(), 28.695833, 77.1525,
							result);
					Toast.makeText(PhoneSilenterByGPS.this,
							"The Distance is  " + result[0], Toast.LENGTH_SHORT)
							.show();
					if (result[0] <= 500) {

						AudioManager adm = (AudioManager) getSystemService(AUDIO_SERVICE);
						adm.setRingerMode(AudioManager.RINGER_MODE_SILENT);

						Toast.makeText(PhoneSilenterByGPS.this,
								"Phone put on Silent Mode  ", Toast.LENGTH_LONG)
								.show();

					} else {
						AudioManager adm = (AudioManager) getSystemService(AUDIO_SERVICE);
						adm.setRingerMode(AudioManager.RINGER_MODE_NORMAL);

						Toast.makeText(PhoneSilenterByGPS.this,
								"Phone put on Normal Mode  ", Toast.LENGTH_LONG)
								.show();

					}

				}

			}

			@Override
			public void onStatusChanged(String provider, int status,
					Bundle extras) {

				switch (status) {
				case LocationProvider.AVAILABLE:
					Toast.makeText(
							PhoneSilenterByGPS.this,
							"Now " + provider + "  Location Info is available ",
							Toast.LENGTH_SHORT).show();
					break;
				case LocationProvider.OUT_OF_SERVICE:
					Toast.makeText(PhoneSilenterByGPS.this,
							"Now " + provider + " is Out of Service ",
							Toast.LENGTH_SHORT).show();
					break;
				case LocationProvider.TEMPORARILY_UNAVAILABLE:
					Toast.makeText(PhoneSilenterByGPS.this,
							"Now " + provider + " is temporary unavailable ",
							Toast.LENGTH_SHORT).show();
					break;

				default:
					break;
				}

			}

			@Override
			public void onProviderEnabled(String provider) {
				Toast.makeText(PhoneSilenterByGPS.this,
						"Now " + provider + " is Enabled ", Toast.LENGTH_SHORT)
						.show();
			}

			@Override
			public void onProviderDisabled(String provider) {
				Toast.makeText(PhoneSilenterByGPS.this,
						"Now " + provider + " is Disabled ", Toast.LENGTH_SHORT)
						.show();
			}

		};

		lmgr.requestLocationUpdates(LocationManager.GPS_PROVIDER,
				30 * 60 * 1000, 1000, locLis);

		return START_STICKY;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Toast.makeText(this, "PhoneSilenterByGPS Service Stopped ",
				Toast.LENGTH_LONG).show();
	}

}
