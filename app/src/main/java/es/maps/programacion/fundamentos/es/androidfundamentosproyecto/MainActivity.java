package es.maps.programacion.fundamentos.es.androidfundamentosproyecto;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;

import java.util.Vector;

import es.maps.programacion.fundamentos.androidfundamentosproyecto.R;
import es.maps.programacion.fundamentos.es.androidfundamentosproyecto.sqlite.pojo.Divisa;
import es.maps.programacion.fundamentos.es.androidfundamentosproyecto.sqlite.pojo.Pais;
import es.maps.programacion.fundamentos.es.androidfundamentosproyecto.sqlite.PaisesDivisasSQLite;
import es.maps.programacion.fundamentos.es.androidfundamentosproyecto.lib.LatLngToPais;
import es.maps.programacion.fundamentos.es.androidfundamentosproyecto.ui.TabCalculadora;
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


        /*tabHost.addTab(tabHost.newTabSpec("asteroides").
                        setIndicator(getResources().getString(R.string.main_activity_titulo_pestaña_asteroides)),
                TabMapa.class, null);*/
        tabHost.addTab(tabHost.newTabSpec("boton").
                        setIndicator(getResources().getString(R.string.main_activity_titulo_pestaña_mapa)),
                TabMapa.class, null);
        tabHost.addTab(tabHost.newTabSpec("calculadora").
                        setIndicator(getResources().getString(R.string.main_activity_titulo_pestaña_calculadora)),
                TabCalculadora.class, null);

        /*tabHost.addTab(tabHost.newTabSpec("otras").
                setIndicator(saveView), TabOtra.class, null);*/


        PaisesDivisasSQLite sql = new PaisesDivisasSQLite(this);
        Vector<Pais> paises = sql.listarPaises();
        Vector<Divisa> divisas = sql.listarDivisas();

        for (Pais p : paises) {
            System.out.println(p.getPaisES());
        }
        for (Divisa p : divisas) {
            System.out.println(p.getDivisaES());
        }


        new LatLngToPais(this).leerJSon("");
    }


}
