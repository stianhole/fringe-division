package me.kaptindandy.saldo;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class SaldoPreferences extends PreferenceActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        /* this is deprecated, need to figure out how to do preferences again. 
         * prefs are hardcoded in SaldoWidget */
        addPreferencesFromResource(R.xml.preferences);
    }
}