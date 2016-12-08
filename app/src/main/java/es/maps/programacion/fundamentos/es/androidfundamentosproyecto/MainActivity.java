package es.maps.programacion.fundamentos.es.androidfundamentosproyecto;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import es.maps.programacion.fundamentos.androidfundamentosproyecto.R;
import es.maps.programacion.fundamentos.es.androidfundamentosproyecto.preferencias.PreferencesActividad;
import es.maps.programacion.fundamentos.es.androidfundamentosproyecto.ui.TabCalculadora;
import es.maps.programacion.fundamentos.es.androidfundamentosproyecto.ui.TabListPaises;
import es.maps.programacion.fundamentos.es.androidfundamentosproyecto.ui.TabMapa;


public class MainActivity extends FragmentActivity {

    private FragmentTabHost tabHost;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //View saveView = LayoutInflater.from(MainActivity.this).inflate(R.layout.otra_tab_icon, null);

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

        /*tabHost.addTab(tabHost.newTabSpec("otras").
                setIndicator(saveView), TabOtra.class, null);*/


        /*PaisesDivisasSQLite sql = new PaisesDivisasSQLite(this);
        Vector<Pais> paises = sql.listarPaises();
        Vector<Divisa> divisas = sql.listarDivisas();

        for (Pais p : paises) {
            System.out.println(p.getPaisES());
        }
        for (Divisa p : divisas) {
            System.out.println(p.getDivisaES());
        }*/

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
