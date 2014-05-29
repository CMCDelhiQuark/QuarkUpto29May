package com.cmcdelhi.locationSilent;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.os.IBinder;
import android.telephony.CellLocation;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.widget.Toast;

public class PhoneSilenterByCellId extends Service {

	TelephonyManager telmgr;
	PhoneStateListener cellLocationListener;
	int cellId;
	SharedPreferences sp;

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Toast.makeText(this, "PhoneSilenterByCellIdss Service Started ",
				Toast.LENGTH_LONG).show();

		telmgr = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);

		// if (telmgr != null) {
		// Toast.makeText(this, "Telmgr is not null", Toast.LENGTH_LONG)
		// .show();
		// }

		cellLocationListener = new PhoneStateListener() {
			@Override
			public void onCellLocationChanged(CellLocation location) {
				GsmCellLocation gsmCellLocation = (GsmCellLocation) location;
				Toast.makeText(
						PhoneSilenterByCellId.this,
						" Cell Id " + String.valueOf(gsmCellLocation.getCid())
								+ " Cell LAC "
								+ String.valueOf(gsmCellLocation.getLac())
								+ " Cell PAC "
								+ String.valueOf(gsmCellLocation.getPsc())

						, Toast.LENGTH_LONG).show();

				cellId = gsmCellLocation.getCid();

				sp = getSharedPreferences("cellId_info", MODE_PRIVATE);

				int savedCellId = sp.getInt("cellId", -1);

				if ((savedCellId != -1) && (cellId != -1)
						&& (cellId == savedCellId)) {
					AudioManager adm = (AudioManager) getSystemService(AUDIO_SERVICE);
					adm.setRingerMode(AudioManager.RINGER_MODE_SILENT);

					Toast.makeText(PhoneSilenterByCellId.this,
							"Phone put on Silent Mode  ", Toast.LENGTH_LONG)
							.show();

				} else {

					AudioManager adm = (AudioManager) getSystemService(AUDIO_SERVICE);
					adm.setRingerMode(AudioManager.RINGER_MODE_NORMAL);

					Toast.makeText(PhoneSilenterByCellId.this,
							"Celll Id is not equal   ", Toast.LENGTH_LONG)
							.show();
				}

			}
		};

		telmgr.listen(cellLocationListener,
				PhoneStateListener.LISTEN_CELL_LOCATION);

		return START_STICKY;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();

		Toast.makeText(this, "PhoneSilenterByCellIdss Service Stopped ",
				Toast.LENGTH_LONG).show();
	}

}
