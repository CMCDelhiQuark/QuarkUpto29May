package com.cmcdelhi.locationSilent;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.telephony.CellLocation;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.widget.Toast;

/*
 * This is a service class
 * that updates the cell Id of CMC based on GPS Data
 * per user 
 * and saves it
 * ie . finds and saves CMC Pitampura Cell ID based on its networkd Provider(ed Airtel , Voda)
 */
public class CMCCellIdInfoUpdateService extends Service {

	public Context context;
	LocationManager lmgr;
	LocationListener locLis;
	Location currentLocation;
	TelephonyManager telmgr;
	PhoneStateListener cellLocationListener;
	int cellId;
	SharedPreferences sp;
	IBinder binder = new CMCCellIdInfoUpdateServiceBinder();

	@Override
	public IBinder onBind(Intent intent) {
		return binder;
	}

	public class CMCCellIdInfoUpdateServiceBinder extends Binder {
		public CMCCellIdInfoUpdateService getService() {
			return CMCCellIdInfoUpdateService.this;
		}
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {

		Toast.makeText(this, "CMCCellInfoUpdateSewrvice Service Started ",
				Toast.LENGTH_LONG).show();
		lmgr = (LocationManager) context
				.getSystemService(context.LOCATION_SERVICE);

		locLis = new LocationListener() {

			@Override
			public void onLocationChanged(Location location) {

				currentLocation = location;

				if (currentLocation != null) {

					float[] result = new float[5];
					Location.distanceBetween(currentLocation.getLatitude(),
							currentLocation.getLongitude(), 28.695833, 77.1525,
							result);
					if (result[0] <= 500) {

						Toast.makeText(context,
								"The Distance is  " + result[0],
								Toast.LENGTH_SHORT).show();

						sp = getSharedPreferences("cellId_info", MODE_PRIVATE);
						SharedPreferences.Editor sped = sp.edit();
						sped.putInt("cellId", cellId);
						sped.commit();

						Toast.makeText(context, "Cell Id Saved " + cellId,
								Toast.LENGTH_SHORT).show();
						stopSelf();
					}

				}

			}

			@Override
			public void onStatusChanged(String provider, int status,
					Bundle extras) {

				switch (status) {
				case LocationProvider.AVAILABLE:
					Toast.makeText(
							context,
							"Now " + provider + "  Location Info is available ",
							Toast.LENGTH_SHORT).show();
					break;
				case LocationProvider.OUT_OF_SERVICE:
					Toast.makeText(context,
							"Now " + provider + " is Out of Service ",
							Toast.LENGTH_SHORT).show();
					break;
				case LocationProvider.TEMPORARILY_UNAVAILABLE:
					Toast.makeText(context,
							"Now " + provider + " is temporary unavailable ",
							Toast.LENGTH_SHORT).show();
					break;

				default:
					break;
				}

			}

			@Override
			public void onProviderEnabled(String provider) {
				Toast.makeText(context, "Now " + provider + " is Enabled ",
						Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onProviderDisabled(String provider) {
				Toast.makeText(context, "Now " + provider + " is Disabled ",
						Toast.LENGTH_SHORT).show();
			}

		};

		lmgr.requestLocationUpdates(LocationManager.GPS_PROVIDER,
				30 * 60 * 1000, 1000, locLis);

		telmgr = (TelephonyManager) context
				.getSystemService(context.TELEPHONY_SERVICE);

		cellLocationListener = new PhoneStateListener() {
			@Override
			public void onCellLocationChanged(CellLocation location) {
				GsmCellLocation gsmCellLocation = (GsmCellLocation) location;
				Toast.makeText(
						context,
						" Cell Id " + String.valueOf(gsmCellLocation.getCid())
								+ " Cell LAC "
								+ String.valueOf(gsmCellLocation.getLac())
								+ " Cell PAC "
								+ String.valueOf(gsmCellLocation.getPsc())

						, Toast.LENGTH_LONG).show();

				cellId = gsmCellLocation.getCid();

			}
		};

		telmgr.listen(cellLocationListener,
				PhoneStateListener.LISTEN_CELL_LOCATION);

		return START_STICKY;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Toast.makeText(this, "CMCCellInfoService Stopped", Toast.LENGTH_LONG)
				.show();

		lmgr.removeUpdates(locLis);

	}

}
