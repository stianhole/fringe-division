package me.kaptindandy.saldo;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.telephony.SmsManager;
import android.widget.RemoteViews;
import android.widget.Toast;

public class SaldoWidget extends AppWidgetProvider {
	private static String APPWIDGET_SALDO = "ActionReceiverSaldo";

	boolean saldoEnable;
	String saldoRecipient;
	String saldoMessage;
	
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
		
		final int N = appWidgetIds.length;
		
		for (int i=0; i<N; i++) {
			int appWidgetId = appWidgetIds[i];
			
			// set intent and action
			Intent intent = new Intent(context, SaldoWidget.class);
			intent.setAction(APPWIDGET_SALDO);
			
			PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
			
			RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget1);
			views.setOnClickPendingIntent(R.id.button, pendingIntent);
			
			// update widget
			appWidgetManager.updateAppWidget(appWidgetId, views);
		}
	}
		
	public void onReceive(Context context, Intent intent) {

		if (intent.getAction().equals(APPWIDGET_SALDO)) {
			// Send message
			SmsManager sms = SmsManager.getDefault();
			sms.sendTextMessage("04800", null, "saldo", null, null);
			
			// Let the user know
			Toast.makeText(context, "Message sent", Toast.LENGTH_SHORT).show();
		} else {
			super.onReceive(context, intent);
		}
	}
}
