package me.kaptindandy.saldo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

public class SaldoWidget extends AppWidgetProvider {
	// code tutorial @ http://buildmobile.com/how-to-code-an-android-widget/
	  DateFormat df = new SimpleDateFormat("hh:mm:ss");
	  public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
	    final int N = appWidgetIds.length;
	    Log.i("ExampleWidget",  "Updating widgets " + Arrays.asList(appWidgetIds));
	    // Perform this loop procedure for each App Widget that belongs to this
	    // provider
	    for (int i = 0; i < N; i++) {
	      int appWidgetId = appWidgetIds[i];
	      // Create an Intent to launch ExampleActivity
	      Intent intent = new Intent(context, SaldoActivity.class);
	      PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
	      // Get the layout for the App Widget and attach an on-click listener
	      // to the button
	      RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget1);
	      views.setOnClickPendingIntent(R.id.button, pendingIntent);
	      // To update a label
	      //views.setTextViewText(R.id.widget1label, df.format(new Date()));
	      // Tell the AppWidgetManager to perform an update on the current app
	      // widget
	      appWidgetManager.updateAppWidget(appWidgetId, views);
	    }
	  }


}
