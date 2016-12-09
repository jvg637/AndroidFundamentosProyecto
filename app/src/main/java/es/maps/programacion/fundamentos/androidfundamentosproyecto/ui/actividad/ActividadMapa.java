package es.maps.programacion.fundamentos.androidfundamentosproyecto.ui.actividad;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import es.maps.programacion.fundamentos.androidfundamentosproyecto.R;

/**
 * Created by jvg63 on 08/12/2016.
 */
public class ActividadMapa extends AppCompatActivity {

    private String idPais;

    public String getIdPais() {
        return idPais;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getIntent() != null)
            idPais = getIntent().getExtras().getString("idPais", "");
        setContentView(R.layout.actividad_mapa);
    }

}
