package es.maps.programacion.fundamentos.es.androidfundamentosproyecto.ui;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.maps.programacion.fundamentos.androidfundamentosproyecto.R;

/**
 * Created by jvg63 on 21/09/2016.
 */
public class TabCalculadora extends Fragment {

    @BindView(R.id.pantalla_calculadora)
    TextView pantallaCalculadora;
    @BindView(R.id.btn1)
    Button btn1;
    @BindView(R.id.btn2)
    Button btn2;
    @BindView(R.id.btn3)
    Button btn3;
    @BindView(R.id.btn4)
    Button btn4;
    @BindView(R.id.btn5)
    Button btn5;
    @BindView(R.id.btn6)
    Button btn6;
    @BindView(R.id.btn7)
    Button btn7;
    @BindView(R.id.btn8)
    Button btn8;
    @BindView(R.id.btn9)
    Button btn9;
    @BindView(R.id.btn0)
    Button btn0;
    @BindView(R.id.btnMas)
    Button btnMas;
    @BindView(R.id.btnIgual)
    Button btnIgual;
    @BindView(R.id.btnClear)
    Button btnClear;
    @BindView(R.id.btnPunto)
    Button btnPunto;
    @BindView(R.id.btnPTS)
    Button btnPTS;
    @BindView(R.id.btnEUR)
    Button btnEUR;
    @BindView(R.id.btnMenos)
    Button btnMenos;

    /*private TextView pantallaCalculadora;*/


    private Double acumulador = 0d;
    private boolean flagNuevoNumero = true;
    private String tagPrevia = "";


    private boolean flagDecimalIntroducido = false;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        inicializaComponentes();
    }

    private void inicializaComponentes() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.calculadora_tab, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    void pulsaDigitoCalculadora(View view) {

        String tag = view.getTag().toString();

        //pantallaCalculadora = (TextView) getActivity().findViewById(R.id.pantalla_calculadora);

        double sumando = 0;

        if (flagNuevoNumero && !tag.equals(getResources().getString(R.string.digito_IGUAL_calculadora)) &&
                !tag.equals(getResources().getString(R.string.digito_MAS_calculadora)) &&
                !tag.equals(getResources().getString(R.string.digito_PTS_calculadora)) &&
                !tag.equals(getResources().getString(R.string.digito_EUR_calculadora)) &&
                !tag.equals(getResources().getString(R.string.digito_MENOS_calculadora))) {
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
                tagPrevia = "";

            } else if (tag.equals(getResources().getString(R.string.digito_MAS_calculadora))) {
                sumando = pantallaCalculadora.getText().toString().length() > 0d ? Double.parseDouble(pantallaCalculadora.getText().toString()) : 0d;
                //Log.d("PANTALLA_ACUMULADOR", String.valueOf(sumando));

                flagNuevoNumero = true;
                flagDecimalIntroducido = false;

                if (tagPrevia.equals(getResources().getString(R.string.digito_MAS_calculadora)))
                    acumulador += Double.parseDouble(pantallaCalculadora.getText().toString());
                else if (tagPrevia.equals(getResources().getString(R.string.digito_MENOS_calculadora)))
                    acumulador -= Double.parseDouble(pantallaCalculadora.getText().toString());
                else
                    acumulador = sumando;

                tagPrevia = tag;

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
                else if (tagPrevia.equals(getResources().getString(R.string.digito_MENOS_calculadora)))
                    acumulador -= Double.parseDouble(pantallaCalculadora.getText().toString());
                else
                    acumulador = sumando;


                flagNuevoNumero = true;
                flagDecimalIntroducido = false;


                if (acumulador % 1f == 0f) {
                    actualizaPantalla(tag, String.format("%d", (acumulador.longValue())));
                } else {
                    actualizaPantalla(tag, String.format("%.2f", (acumulador)));
                }


                tagPrevia = tag;

                //Log.d("PANTALLA_ACUMULADOR", String.valueOf(sumando));

            } else if (tag.equals(getResources().getString(R.string.digito_IGUAL_calculadora))) {

                if (tagPrevia.equals(getResources().getString(R.string.digito_MAS_calculadora)))
                    acumulador += Double.parseDouble(pantallaCalculadora.getText().toString());
                else if (tagPrevia.equals(getResources().getString(R.string.digito_MENOS_calculadora)))
                    acumulador -= Double.parseDouble(pantallaCalculadora.getText().toString());
                else acumulador = Double.parseDouble(pantallaCalculadora.getText().toString());

                if (acumulador % 1f == 0f) {
                    actualizaPantalla(tag, String.format("%d", (acumulador.longValue())));
                } else {
                    actualizaPantalla(tag, String.format("%.2f", (acumulador)));
                }
                flagNuevoNumero = true;
                flagDecimalIntroducido = false;

                //acumulador = 0d;
                tagPrevia = "";

            } else if (tag.equals(getResources().getString(R.string.digito_PTS_calculadora))) {
                sumando = pantallaCalculadora.getText().toString().length() > 0 ? Double.parseDouble(pantallaCalculadora.getText().toString()) : 0;
                actualizaPantalla(tag, String.format("%d", (Math.round(sumando * 166.386))));

                flagNuevoNumero = true;
                flagDecimalIntroducido = false;
                tagPrevia = "";

            } else if (tag.equals(getResources().getString(R.string.digito_EUR_calculadora))) {
                sumando = pantallaCalculadora.getText().toString().length() > 0 ? Double.parseDouble(pantallaCalculadora.getText().toString()) : 0;
                actualizaPantalla(tag, String.format("%.2f", (sumando / 166.386f)));

                flagNuevoNumero = true;
                flagDecimalIntroducido = false;
                tagPrevia = "";

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


    @OnClick({R.id.pantalla_calculadora, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9, R.id.btn0, R.id.btnMas, R.id.btnIgual, R.id.btnClear, R.id.btnPunto, R.id.btnPTS, R.id.btnEUR, R.id.btnMenos})
    public void onClick(View view) {
        pulsaDigitoCalculadora(view);

    }
}