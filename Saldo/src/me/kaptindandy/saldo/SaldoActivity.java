package me.kaptindandy.saldo;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class SaldoActivity extends Activity {
	
	boolean saldoEnable;
	String saldoRecipient;
	String saldoMessage;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
	
	@Override
	public void onStart() {
		getPrefs();
		super.onStart();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		switch (item.getItemId()) {
		case R.id.settings:
			startActivity(new Intent(this, SaldoPreferences.class));
			break;
		case R.id.sendtest:
			if (saldoEnable) {
				sendSaldo();
			} else {
				Toast.makeText(this, "Nothing sent, enable Saldo in preferences!", Toast.LENGTH_LONG).show();
			}
			break;
		case R.id.showprefs:
			String isTrue;
			if (saldoEnable == true) {
				isTrue = "true";
			} else {
				isTrue = "false";
			}
			Toast.makeText(this, "Enable: " + isTrue + " rec: " + saldoRecipient + " msg: " + saldoMessage, Toast.LENGTH_LONG).show();
			break;
		}
		
		return true;
	}
	
	// for sending message
	private void sendSaldo() {
			SmsManager sms = SmsManager.getDefault();
			sms.sendTextMessage(saldoRecipient, null, saldoMessage, null, null);
			Toast.makeText(this, saldoMessage + " sent to " + saldoRecipient, Toast.LENGTH_LONG).show();
	}
	
	
	// preferences
	
	public void getPrefs() {
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
		saldoEnable = prefs.getBoolean("saldoEnable", true);
		saldoRecipient = prefs.getString("saldoRecipient", "");
		saldoMessage = prefs.getString("saldoMessage", "");
	}
	
}
