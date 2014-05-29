package com.cmcdelhi.widget;

import com.cmcdelhi.cmcdelhiquark.R;

import android.app.IntentService;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.widget.RemoteViews;

public class WidgetHandler extends AppWidgetProvider {

	Context context;

	static MediaPlayer mp;

	static boolean isLocationSilentClicked = false;

	@Override
	public void onReceive(Context context, Intent intent) {
		this.context = context;
		super.onReceive(context, intent);
		context.startService(new Intent(context, BackgroundService.class));
		Log.d("GUFRAN DEBUG", "INSIDE ON RECIVE");
	}

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {

		super.onUpdate(context, appWidgetManager, appWidgetIds);
		context.startService(new Intent(context, BackgroundService.class));
		Log.d("GUFRAN DEBUG", "INSIDE UPDATE");
	}

	public static class BackgroundService extends IntentService {

		public BackgroundService() {
			super("WidgetHandler$BackgroundService");
			Log.d("GUFRAN DEBUG", "INSIDE BACKGROUND SERVICE");
		}

		@Override
		protected void onHandleIntent(Intent intent) {
			Log.d("GUFRAN DEBUG", "INSIDE ONHANDLE INTENT");
			ComponentName cp = new ComponentName(this, WidgetHandler.class);
			AppWidgetManager appWidgetManager = AppWidgetManager
					.getInstance(this);

			RemoteViews remoteViews = new RemoteViews(this.getPackageName(),
					R.layout.widget_layout);

			if (isLocationSilentClicked == false) {
				remoteViews.setImageViewResource(R.id.locationsilent,
						R.drawable.locsilblack);
				isLocationSilentClicked = true;

			} else {
				remoteViews.setImageViewResource(R.id.locationsilent,
						R.drawable.locsil);
				isLocationSilentClicked = false;

			}

			mp = MediaPlayer.create(this, R.raw.but50mp3);

			mp.start();

			// setting up Browser
			Intent i2 = new Intent(android.content.Intent.ACTION_VIEW);
			i2.setData(Uri.parse("http://www.facebook.com"));
			i2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			PendingIntent pi2 = PendingIntent.getActivity(this, 0, i2, 0);
			remoteViews.setOnClickPendingIntent(R.id.browser, pi2);

			// setting up Browser
			Intent i3 = new Intent(android.content.Intent.ACTION_CALL);
			i3.setData(Uri.parse("tel:01165905335"));
			i3.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			PendingIntent pi3 = PendingIntent.getActivity(this, 0, i3, 0);
			remoteViews.setOnClickPendingIntent(R.id.call, pi3);

			// setting up locationSilent
			Intent i = new Intent(this, WidgetHandler.class);
			PendingIntent pi = PendingIntent.getBroadcast(this, 0, i, 0);
			remoteViews.setOnClickPendingIntent(R.id.locationsilent, pi);

			appWidgetManager.updateAppWidget(cp, remoteViews);

		}
	}

}
