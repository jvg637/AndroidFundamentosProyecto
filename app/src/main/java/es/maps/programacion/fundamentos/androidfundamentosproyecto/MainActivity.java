package es.maps.programacion.fundamentos.androidfundamentosproyecto;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

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
        Drawable icono=ContextCompat.getDrawable(this, R.drawable.tab_mapa);
        icono.setBounds(0,0, 80, 80);
        ImageView view = new ImageView(this);
        view.setImageDrawable(icono);
        tabHost.addTab(tabHost.newTabSpec("mapa").
                        setIndicator(view),
                TabMapa.class, null);

        // Tab lista banderas
        Drawable icono2=ContextCompat.getDrawable(this, R.drawable.tab_banderas);
        icono2.setBounds(0,0, 80, 80);
        ImageView view2 = new ImageView(this);
        view2.setImageDrawable(icono2);
        tabHost.addTab(tabHost.newTabSpec("paises   ").
                        setIndicator("dos"),
                TabListPaises.class, null);

        // Tab lista calculadora
        Drawable icono3=ContextCompat.getDrawable(this, R.drawable.tab_calculadora);
        icono3.setBounds(0,0, 80, 80);
        ImageView view3 = new ImageView(this);
        view3.setImageDrawable(icono3);

        tabHost.addTab(tabHost.newTabSpec("calculadora").
                        setIndicator(view3),
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
