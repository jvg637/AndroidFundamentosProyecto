package es.maps.programacion.fundamentos.androidfundamentosproyecto;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

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


        tabHost.addTab(tabHost.newTabSpec("mapa").
                        setIndicator(getResources().getString(R.string.main_activity_titulo_pestaña_mapa)),
                TabMapa.class, null);
        tabHost.addTab(tabHost.newTabSpec("paises   ").
                        setIndicator(getResources().getString(R.string.main_activity_titulo_pestaña_lista_paises)),
                TabListPaises.class, null);
        tabHost.addTab(tabHost.newTabSpec("calculadora").
                        setIndicator(getResources().getString(R.string.main_activity_titulo_pestaña_calculadora)),
                TabCalculadora.class, null);

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

            default:
                return super.onOptionsItemSelected(item);
        }
        return true;

    }

    private void lanzarPreferencias(View view) {
        Intent i = new Intent(this, PreferencesActividad.class);
        startActivity(i);
    }
}
