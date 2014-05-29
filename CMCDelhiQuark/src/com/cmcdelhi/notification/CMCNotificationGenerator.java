package com.cmcdelhi.notification;

import java.util.Date;

import com.cmcdelhi.cmcdelhiquark.R;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.IBinder;

public class CMCNotificationGenerator {

	Context context;
	NotificationManager nfmgr;
	Notification nf;
	PendingIntent pi;

	// For future message
	String tickerText2;
	String msg2;
	PendingIntent pI2;
	Date d2;

	CMCNotificationGenerationService cngs;

	Intent i;

	ServiceConnection sc = new ServiceConnection() {

		@Override
		public void onServiceConnected(ComponentName name, IBinder binder) {
			cngs = ((CMCNotificationGenerationService.CMCNotificationServiceBinder) binder)
					.getService();

			cngs.message = msg2;
			cngs.launchDate = d2;
			cngs.context = context;
			cngs.pi = pI2;
			cngs.tickerText = tickerText2;

			context.startService(i);
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			cngs = null;
		}

	};

	public CMCNotificationGenerator(Context context) {
		super();
		this.context = context;
		nfmgr = (NotificationManager) context
				.getSystemService(context.NOTIFICATION_SERVICE);

	}

	// generate notification --> right now
	public boolean generateNotification(String tickerText, String message,
			PendingIntent pi, int notificationId, boolean vibrate, boolean sound) {
		nf = new Notification(R.drawable.ic_launcher, tickerText,
				System.currentTimeMillis());
		nf.setLatestEventInfo(context, "CMC Delhi Quark", message, pi);

		if (vibrate) {
			nf.vibrate = new long[] { 100, 250, 100, 500 };
		}

		if (sound) {
			nf.sound = Uri.parse("android.resource://"
					+ context.getPackageName() + "/" + R.raw.but50mp3);
		}

		nfmgr.notify(notificationId, nf);

		return true;
	}

	// generate notification --> at a perticular time
	public boolean generateNotification(String tickerText, String msg,
			PendingIntent pI, int notificationId, boolean vibrate,
			boolean sound, Date d) {

		tickerText2 = tickerText;
		msg2 = msg;
		pI2 = pI;
		d2 = d;

		i = new Intent(context, CMCNotificationGenerationService.class);
		context.bindService(i, sc, Context.BIND_AUTO_CREATE);

		// context.startService(i);

		return true;
	}
}
