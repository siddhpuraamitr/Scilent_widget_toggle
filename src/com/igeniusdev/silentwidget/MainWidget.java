package com.igeniusdev.silentwidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

/**
 * 
 * @author seymores@gmail.com
 * 
 */
public class MainWidget extends AppWidgetProvider {

	private static final String TAG = "MainWidget";
	public static final String ACTION_WIDGET_SILENT = "ActionReceiverSilent";

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {

		RemoteViews remoteViews = new RemoteViews(
				context.getPackageName(), R.layout.main);

		Intent active = new Intent(context, MyReceiver.class);
		active.setAction(ACTION_WIDGET_SILENT);
		PendingIntent actionPendingIntent = PendingIntent.getBroadcast(
				context, 0, active, 0);
		remoteViews.setOnClickPendingIntent(R.id.btnSilent,
				actionPendingIntent);

		appWidgetManager.updateAppWidget(appWidgetIds, remoteViews);

	}

}
