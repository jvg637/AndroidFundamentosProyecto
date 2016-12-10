package es.maps.programacion.fundamentos.androidfundamentosproyecto.preferencias;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.EditTextPreference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceScreen;

import es.maps.programacion.fundamentos.androidfundamentosproyecto.R;


/**
 * Created by jvg63 on 08/10/2016.
 */

public class PreferencesActividad extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        getSupportFragmentManager().beginTransaction().replace(android.R.id.content,
                new PreferenciasFragment()).commit();
    }


    public static class PreferenciasFragment extends PreferenceFragmentCompat implements PreferenceFragmentCompat.OnPreferenceStartScreenCallback {

        private final String SDCARD = "sdcard";
        private EditTextPreference paisOrigen;


        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {

            addPreferencesFromResource(R.xml.preferencias);

        }

        @Override
        public Fragment getCallbackFragment() {
            return this;
        }

        @Override
        public boolean onPreferenceStartScreen(PreferenceFragmentCompat preferenceFragmentCompat, PreferenceScreen preferenceScreen) {
            preferenceFragmentCompat.setPreferenceScreen(preferenceScreen);
            return true;
        }


    }

}