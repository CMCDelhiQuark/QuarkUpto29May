package com.cmcdelhi.notification;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import com.cmcdelhi.cmcdelhiquark.R;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

public class CMCNotificationGenerationService extends Service {

	Date launchDate;
	String tickerText;
	String message;
	PendingIntent pi;
	Context context;

	IBinder binder = new CMCNotificationServiceBinder();

	@Override
	public IBinder onBind(Intent intent) {

		return binder;
		// return null;
	}

	class CMCNotificationServiceBinder extends Binder {
		CMCNotificationGenerationService getService() {
			return CMCNotificationGenerationService.this;
		}
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {

		Toast.makeText(this,
				" CMC Notification Service Stared with " + launchDate,
				Toast.LENGTH_LONG).show();

		Timer timer = new Timer();
		TimerTask task = new TimerTask() {

			@Override
			public void run() {

				NotificationManager nfmgr = (NotificationManager) context
						.getSystemService(NOTIFICATION_SERVICE);

				Notification nf = new Notification(R.drawable.ic_launcher,
						tickerText, System.currentTimeMillis());
				nf.setLatestEventInfo(context, "CMC Delhi Quark", message, pi);

				nf.vibrate = new long[] { 100, 250, 100, 500 };

				nf.sound = Uri.parse("android.resource://"
						+ context.getPackageName() + "/" + R.raw.but50mp3);

				nfmgr.notify(0, nf);

				stopSelf();

			}
		};

		timer.schedule(task, launchDate);

		return START_STICKY;

	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

}
