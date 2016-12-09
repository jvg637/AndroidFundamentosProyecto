package es.maps.programacion.fundamentos.androidfundamentosproyecto.ui.tab;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import es.maps.programacion.fundamentos.androidfundamentosproyecto.R;

/**
 * Created by jvg63 on 21/09/2016.
 */
public class TabOtra extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.otra_tab, container, false);
    }
}