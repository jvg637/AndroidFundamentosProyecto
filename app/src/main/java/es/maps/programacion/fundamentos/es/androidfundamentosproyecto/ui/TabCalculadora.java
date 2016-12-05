package es.maps.programacion.fundamentos.es.androidfundamentosproyecto.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import es.maps.programacion.fundamentos.androidfundamentosproyecto.R;

/**
 * Created by jvg63 on 21/09/2016.
 */
public class TabCalculadora extends Fragment {

    private TextView pantallaCalculadora;


    private Double acumulador = 0d;
    private boolean flagNuevoNumero = true;
    private String tagPrevia = "";


    private boolean flagDecimalIntroducido = false;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.calculadora_tab, container, false);
    }

    void pulsaDigitoCalculadora(View view) {

        String tag = view.getTag().toString();

        pantallaCalculadora = (TextView) getActivity().findViewById(R.id.pantalla_calculadora);

        double sumando = 0;

        if (flagNuevoNumero && !tag.equals(getResources().getString(R.string.digito_IGUAL_calculadora)) &&
                !tag.equals(getResources().getString(R.string.digito_MAS_calculadora)) &&
                !tag.equals(getResources().getString(R.string.digito_PTS_calculadora)) &&
                !tag.equals(getResources().getString(R.string.digito_EUR_calculadora)) &&
                !tag.equals(getResources().getString(R.string.digito_MENOS_calculadora)))
        {
            actualizaPantalla(tag, "");
        }

        try {
            if (tag.matches("[0-9]")) {
                flagNuevoNumero = false;

                actualizaPantalla(tag, pantallaCalculadora.getText().toString() + tag);

            } else if (tag.equals(getResources().getString(R.string.digito_C_calculadora))) {
                pantallaCalculadora.setText(getResources().getString(R.string.digito_0_calculadora));
                acumulador = 0d;
                flagNuevoNumero = true;
                flagDecimalIntroducido = false;
                tagPrevia="";

            } else if (tag.equals(getResources().getString(R.string.digito_MAS_calculadora))) {
                sumando = pantallaCalculadora.getText().toString().length() > 0d ? Double.parseDouble(pantallaCalculadora.getText().toString()) : 0d;
                //Log.d("PANTALLA_ACUMULADOR", String.valueOf(sumando));

                flagNuevoNumero = true;
                flagDecimalIntroducido = false;

                if (tagPrevia.equals(getResources().getString(R.string.digito_MAS_calculadora)))
                    acumulador += Double.parseDouble(pantallaCalculadora.getText().toString());
                else  if (tagPrevia.equals(getResources().getString(R.string.digito_MENOS_calculadora)))
                    acumulador -= Double.parseDouble(pantallaCalculadora.getText().toString());
                else
                    acumulador=sumando;

                tagPrevia=tag;

                if (acumulador % 1f == 0f) {
                    actualizaPantalla(tag, String.format("%d", (acumulador.longValue())));
                } else {
                    actualizaPantalla(tag, String.format("%.2f", (acumulador)));
                }


            } else if (tag.equals(getResources().getString(R.string.digito_MENOS_calculadora))) {
                sumando = pantallaCalculadora.getText().toString().length() > 0d ? Double.parseDouble(pantallaCalculadora.getText().toString()) : 0d;
                //Log.d("PANTALLA_ACUMULADOR", String.valueOf(sumando));


                if (tagPrevia.equals(getResources().getString(R.string.digito_MAS_calculadora)))
                    acumulador += Double.parseDouble(pantallaCalculadora.getText().toString());
                else  if (tagPrevia.equals(getResources().getString(R.string.digito_MENOS_calculadora)))
                    acumulador -= Double.parseDouble(pantallaCalculadora.getText().toString());
                else
                    acumulador=sumando;




                flagNuevoNumero = true;
                flagDecimalIntroducido = false;


                if (acumulador % 1f == 0f) {
                    actualizaPantalla(tag, String.format("%d", (acumulador.longValue())));
                } else {
                    actualizaPantalla(tag, String.format("%.2f", (acumulador)));
                }


                tagPrevia=tag;

                //Log.d("PANTALLA_ACUMULADOR", String.valueOf(sumando));

            } else if (tag.equals(getResources().getString(R.string.digito_IGUAL_calculadora))) {

                if (tagPrevia.equals(getResources().getString(R.string.digito_MAS_calculadora)))
                    acumulador += Double.parseDouble(pantallaCalculadora.getText().toString());
                else  if (tagPrevia.equals(getResources().getString(R.string.digito_MENOS_calculadora)))
                    acumulador -= Double.parseDouble(pantallaCalculadora.getText().toString());
                else acumulador= Double.parseDouble(pantallaCalculadora.getText().toString());

                if (acumulador % 1f == 0f) {
                    actualizaPantalla(tag, String.format("%d", (acumulador.longValue())));
                } else {
                    actualizaPantalla(tag, String.format("%.2f", (acumulador)));
                }
                flagNuevoNumero = true;
                flagDecimalIntroducido = false;

                //acumulador = 0d;
                tagPrevia="";

            } else if (tag.equals(getResources().getString(R.string.digito_PTS_calculadora))) {
                sumando = pantallaCalculadora.getText().toString().length() > 0 ? Double.parseDouble(pantallaCalculadora.getText().toString()) : 0;
                actualizaPantalla(tag, String.format("%d", (Math.round(sumando * 166.386))));

                flagNuevoNumero = true;
                flagDecimalIntroducido = false;
                tagPrevia="";

            } else if (tag.equals(getResources().getString(R.string.digito_EUR_calculadora))) {
                sumando = pantallaCalculadora.getText().toString().length() > 0 ? Double.parseDouble(pantallaCalculadora.getText().toString()) : 0;
                actualizaPantalla(tag, String.format("%.2f", (sumando / 166.386f)));

                flagNuevoNumero = true;
                flagDecimalIntroducido = false;
                tagPrevia="";

            } else if (tag.equals(getResources().getString(R.string.digito_PUNTO_calculadora))) {
                if (!flagDecimalIntroducido) {


                    if (pantallaCalculadora.getText().toString().length() > 0)
                        actualizaPantalla(tag, pantallaCalculadora.getText().toString() + tag);
                    else
                        actualizaPantalla(tag, "0" + pantallaCalculadora.getText().toString() + tag);

                    flagDecimalIntroducido = true;
                    flagNuevoNumero = false;
                }
            } else {
                Toast.makeText(getContext(), "Operación no implementada", Toast.LENGTH_SHORT).show();

            }


        } catch (NumberFormatException ex) {
            Toast.makeText(getContext(), "Pulse " + getResources().getString(R.string.digito_C_calculadora), Toast.LENGTH_SHORT).show();
        }



    }

    void actualizaPantalla(String tag, String valor) {
        //Log.d("PANTALLA_TOTAL", valor);
        if ((tag.matches("[0-9]") || tag.indexOf(".") >= 0) && valor.length() > getResources().getInteger(R.integer.maxPosiciones)) {
            Toast.makeText(getContext(), "No se permiten más digitos", Toast.LENGTH_SHORT).show();
        } else {
            if (valor.length() <= getResources().getInteger(R.integer.maxPosiciones)) {
                pantallaCalculadora.setText(valor);
            } else {
                pantallaCalculadora.setText(getResources().getString(R.string.calculadora_overflow));
                flagNuevoNumero = true;
                flagDecimalIntroducido = true;
            }
        }
    }
}