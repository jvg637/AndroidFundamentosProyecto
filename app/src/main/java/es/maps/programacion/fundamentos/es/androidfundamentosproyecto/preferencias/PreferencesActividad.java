package es.maps.programacion.fundamentos.es.androidfundamentosproyecto.preferencias;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.EditTextPreference;
import android.support.v7.preference.Preference;
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


    public static class PreferenciasFragment extends PreferenceFragmentCompat implements PreferenceFragmentCompat.OnPreferenceStartScreenCallback, Preference.OnPreferenceChangeListener {

        private final String SDCARD = "sdcard";
        private EditTextPreference paisOrigen;


        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {

            addPreferencesFromResource(R.xml.preferencias);

            //numAsteroides = (EditTextPreference) getPreferenceScreen().findPreference(getResources().getString(R.string.pref_num_asteroides));

            // Valida los campos numéricos
            //numAsteroides.setOnPreferenceChangeListener(this);
            paisOrigen .setOnPreferenceChangeListener(this);

            /*tipoAlmacenamiento.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        return seleccionaMemoriaExterna(newValue.toString());
                    } else
                        return true;


                }


            });*/

        }



        @Override
        public boolean onPreferenceChange(Preference preference, Object newValue) {

            if (preference == paisOrigen) {
                return (newValue!=null && !((String)newValue).isEmpty());
            }

            /*else if (preference == numeroJugadores) {
                return validaCampoNumerico(newValue.toString(), 1, 2);
            }*/
            return true;
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