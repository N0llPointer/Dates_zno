package com.nollpointer.dates_zno.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.SwitchPreference;

import androidx.appcompat.app.ActionBar;

import com.nollpointer.dates_zno.R;

/**
 * A {@link PreferenceActivity} that presents a set of application settings. On
 * handset devices, settings are presented as a single list. On tablets,
 * settings are split by category, with category headers shown to the left of
 * the list of settings.
 * <p>
 * See <a href="http://developer.android.com/design/patterns/settings.html">
 * Android Design: Settings</a> for design guidelines and the <a
 * href="http://developer.android.com/guide/topics/ui/settings.html">Settings
 * API Guide</a> for more information on developing a Settings UI.
 */
public class SettingsActivity extends AppCompatPreferenceActivity implements
        SharedPreferences.OnSharedPreferenceChangeListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
        setupActionBar();

        SharedPreferences sharedPreferences = getPreferenceScreen().getSharedPreferences();
        CharSequence[] texts = getResources().getTextArray(R.array.dates_cards_types_entries);
        CharSequence[] languageTexts = getResources().getTextArray(R.array.languages_entries);
        boolean saveCurrentState = sharedPreferences.getBoolean("save_current_state",true);

        ListPreference listPreference = (ListPreference) findPreference("dates_card_type");

        //ListPreference languagesPreference = (ListPreference) findPreference("Locale");
        SwitchPreference switchPreference = (SwitchPreference) findPreference("save_current_state");

        listPreference
                .setSummary(texts[Integer.parseInt(sharedPreferences.getString("dates_card_type", "0"))]);
//        languagesPreference
//                .setSummary(languageTexts[Integer.parseInt(sharedPreferences.getString("Locale", "0"))]);
        switchPreference.setChecked(saveCurrentState);

    }

    /**
     * Set up the {@link android.app.ActionBar}, if the API is available.
     */
    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Show the Up button in the action bar.
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Preference preference = findPreference(key);
        if (preference instanceof ListPreference) {
            ListPreference listPreference = (ListPreference) preference;
            preference.setSummary(listPreference.getEntry());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences()
                .registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences()
                .unregisterOnSharedPreferenceChangeListener(this);
    }
}
