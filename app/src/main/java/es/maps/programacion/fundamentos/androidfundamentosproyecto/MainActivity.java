package es.maps.programacion.fundamentos.androidfundamentosproyecto;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import es.maps.programacion.fundamentos.androidfundamentosproyecto.application.MapsApplication;
import es.maps.programacion.fundamentos.androidfundamentosproyecto.preferencias.PreferencesActividad;
import es.maps.programacion.fundamentos.androidfundamentosproyecto.ui.tab.TabCalculadora;
import es.maps.programacion.fundamentos.androidfundamentosproyecto.ui.tab.TabListPaises;
import es.maps.programacion.fundamentos.androidfundamentosproyecto.ui.tab.TabMapa;


public class MainActivity extends AppCompatActivity {

    private FragmentTabHost tabHost;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabHost = (FragmentTabHost) findViewById(R.id.mitabhost);
        tabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);

        // Tab mapa
        LayoutInflater inflater = (LayoutInflater) getBaseContext().getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);

        tabHost.addTab(tabHost.newTabSpec("mapa").
                        setIndicator("", ContextCompat.getDrawable(this, R.drawable.tab_mapa_icon)),
                TabMapa.class, null);


        // Tab lista banderas
        tabHost.addTab(tabHost.newTabSpec("banderas").
                        setIndicator("", ContextCompat.getDrawable(this, R.drawable.tab_banderas_icon)),
                TabListPaises.class, null);


        //Tab lista calculadora

        tabHost.addTab(tabHost.newTabSpec("calculadora").
                        setIndicator("", ContextCompat.getDrawable(this, R.drawable.tab_calculadora_icon)),
                TabCalculadora.class, null);

        SharedPreferences sp = getApplication().getSharedPreferences("datos_usuario", 0);

        String usuario = sp.getString("username", "");

        setTitle(getTitle() + " - " + usuario);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            case R.id.action_settings: {
                lanzarPreferencias(null);

                break;
            }
            case R.id.action_logoff: {
                logonOff();

                break;
            }

            default:
                return super.onOptionsItemSelected(item);
        }
        return true;

    }

    private void logonOff() {

        MapsApplication app = (MapsApplication) getApplicationContext();
        FirebaseAuth auth = app.getAuth();
        FirebaseUser currentUser = auth.getCurrentUser();
        if (currentUser != null) {
            auth.signOut();
        }
        // Elimina el usuario de la sesión
        SharedPreferences prefs = getApplication().getSharedPreferences("datos_usuario", 0);
        prefs.edit().putString("username", "").commit();
        finish();
    }

    private void lanzarPreferencias(View view) {
        Intent i = new Intent(this, PreferencesActividad.class);
        startActivity(i);
    }
}
