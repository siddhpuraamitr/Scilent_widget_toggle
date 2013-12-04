package com.igeniusdev.silentwidget;

import android.appwidget.AppWidgetManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.net.wifi.WifiManager;
import android.widget.RemoteViews;

public class MyReceiver extends BroadcastReceiver {

	private static final String TAG = "AirplaneModeSilent";
	private Context context;
	private boolean isEnabled = false;
	private RemoteViews views;

	@Override
	public void onReceive(Context context, Intent intent) {

		views = new RemoteViews(context.getPackageName(), R.layout.main);
		this.context = context;
		System.out.println("OnReceive Call");
		
		if (intent.getAction().equals(MainWidget.ACTION_WIDGET_SILENT)) {
			System.out.println("Silent");
			toggleSilentMode();
		}

		AppWidgetManager appWidgetManager = AppWidgetManager
				.getInstance(context);
		appWidgetManager.updateAppWidget(new ComponentName(context,
				MainWidget.class), views);

	}

	private void toggleSilentMode() {
		final AudioManager mode = (AudioManager) context
				.getSystemService(Context.AUDIO_SERVICE);

		if (mode.getRingerMode() == AudioManager.RINGER_MODE_SILENT) {
			mode.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
			isEnabled = false;
		} else {
			mode.setRingerMode(AudioManager.RINGER_MODE_SILENT);
			isEnabled = true;
		}
		if (isEnabled) {
			views.setInt(R.id.silent, "setBackgroundResource", R.color.on_green);
		} else {
			views.setInt(R.id.silent, "setBackgroundResource", R.color.on_red);
		}
	}

}
