package es.maps.programacion.fundamentos.androidfundamentosproyecto.ui.tab;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Vector;

import es.maps.programacion.fundamentos.androidfundamentosproyecto.R;
import es.maps.programacion.fundamentos.androidfundamentosproyecto.sqlite.PaisesDivisasSQLite;
import es.maps.programacion.fundamentos.androidfundamentosproyecto.sqlite.pojo.Pais;
import es.maps.programacion.fundamentos.androidfundamentosproyecto.ui.actividad.ActividadMapa;
import es.maps.programacion.fundamentos.androidfundamentosproyecto.ui.tab.paises.adapter.PaisesAdapter;

/**
 * Created by jvg63 on 21/09/2016.
 */
public class TabListPaises extends Fragment {

    private Vector<Pais> paises;
    private RecyclerView recyclerView;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Obtiene Paises
        View view = inflater.inflate(R.layout.lista_paises, container, false);
        inicializaRecyclerView(view);

        return view;

    }

    private void inicializaRecyclerView(View view ) {

        if (paises == null) {
            PaisesDivisasSQLite pd = new PaisesDivisasSQLite(getContext());
            paises = pd.listarPaises();
        }

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        PaisesAdapter adapter = new PaisesAdapter(getContext(), paises);


        adapter.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int pos = recyclerView.getChildAdapterPosition(v);

                Pais pais = paises.get(pos);

                Intent intent = new Intent(getContext(), ActividadMapa.class);
                intent.putExtra("idPais", pais.getIdPais());
                startActivity(intent);
            }
        });

        recyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
    }

}