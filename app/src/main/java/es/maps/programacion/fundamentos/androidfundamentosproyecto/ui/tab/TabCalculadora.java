package es.maps.programacion.fundamentos.androidfundamentosproyecto.ui.tab;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.preference.PreferenceManager;
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
import es.maps.programacion.fundamentos.androidfundamentosproyecto.lib.divisas.ConvertidorDivisas;
import es.maps.programacion.fundamentos.androidfundamentosproyecto.lib.divisas.DivisasSW;
import es.maps.programacion.fundamentos.androidfundamentosproyecto.lib.paises.pojo.Pais;
import es.maps.programacion.fundamentos.androidfundamentosproyecto.sqlite.PaisesDivisasSQLite;

import static android.app.Activity.RESULT_OK;

/**
 * Created by jvg63 on 21/09/2016.
 */
public class TabCalculadora extends Fragment {

    private static final int NUEVA_DIVISA_ORI = 0;
    private static final int NUEVA_DIVISA_DES = 1;
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
    @BindView(R.id.btnOri)
    Button btnOri;
    @BindView(R.id.btnDes)
    Button btnDes;
    @BindView(R.id.btnMenos)
    Button btnMenos;
    @BindView(R.id.btnCNV)
    Button btnCNV;
    @BindView(R.id.btnCNV2)
    Button btnCNV2;

    /*private TextView pantallaCalculadora;*/


    private Double acumulador = 0d;
    private boolean flagNuevoNumero = true;
    private String tagPrevia = "";


    private boolean flagDecimalIntroducido = false;


    private void inicializaComponentes() {

        obtenerDivisaPaisPreferencias();
    }

    private void obtenerDivisaPaisPreferencias() {

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getContext());
        String paisDefault = pref.getString(getString(R.string.pref_pais_origen), "");

        if (!paisDefault.isEmpty()) {
            PaisesDivisasSQLite db = new PaisesDivisasSQLite(getContext());

            Pais pais = db.getPais(paisDefault);

            String primeraDivsa = pais.getDivisas().split("-")[0];

            btnOri.setText(primeraDivsa);

            if (primeraDivsa.equals("USD")) {
                btnDes.setText("EUR");
            }

            btnCNV.setText(primeraDivsa + btnCNV.getText().toString().substring(3));
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.calculadora_tab, container, false);
        ButterKnife.bind(this, view);

        inicializaComponentes();


        return view;
    }

    void pulsaDigitoCalculadora(View view) {

        String tag = view.getTag().toString();

        //pantallaCalculadora = (TextView) getActivity().findViewById(R.id.pantalla_calculadora);

        double sumando = 0;

        if (flagNuevoNumero && !tag.equals(getResources().getString(R.string.digito_IGUAL_calculadora)) &&
                !tag.equals(getResources().getString(R.string.digito_MAS_calculadora)) &&
                !tag.equals(getResources().getString(R.string.digito_moneda_origen_calculadora)) &&
                !tag.equals(getResources().getString(R.string.digito_moneda_destino_calculadora)) &&
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

            } else if (tag.equals(getResources().getString(R.string.digito_moneda_origen_calculadora))) {
                sumando = pantallaCalculadora.getText().toString().length() > 0 ? Double.parseDouble(pantallaCalculadora.getText().toString()) : 0;
                actualizaPantalla(tag, String.format("%d", (Math.round(sumando * 166.386))));

                flagNuevoNumero = true;
                flagDecimalIntroducido = false;
                tagPrevia = "";

            } else if (tag.equals(getResources().getString(R.string.digito_moneda_destino_calculadora))) {
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

    @OnClick({R.id.btnCNV})
    void convierteDivisa() {

        convierteDivisa(1);
    }

    private void convierteDivisa(int opcion) {
        try {
            double tipoDeCambio=-1;
            if (opcion == 1)
                 tipoDeCambio = new DivisasSW().tipoCambio(btnOri.getText().toString(), btnDes.getText().toString());
            else
                 tipoDeCambio = new DivisasSW().tipoCambio( btnDes.getText().toString(),btnOri.getText().toString());

            if (tipoDeCambio < 0) {
                Toast.makeText(getContext(), "Ha ocurrido un error en el acceso al tipo de cambio!", Toast.LENGTH_SHORT).show();
                return;
            }

            //Toast.makeText(this, "" + importe1, Toast.LENGTH_SHORT).show();

            pantallaCalculadora.setText(String.valueOf(tipoDeCambio * Double.parseDouble(pantallaCalculadora.getText().toString())));
        } catch (Exception ex) {
            Toast.makeText(getContext(), "Ha ocurrido un error! Inténtelo de nuevo", Toast.LENGTH_SHORT).show();

        }
    }

    @OnClick({R.id.btnOri, R.id.btnDes})
    public void seleccionaDivisa(View view) {

        Intent intent = new Intent(getContext(), ConvertidorDivisas.class);

        intent.putExtra("divisa", ((Button) view).getText());
        if (view == btnOri)
            startActivityForResult(intent, NUEVA_DIVISA_ORI);
        else
            startActivityForResult(intent, NUEVA_DIVISA_DES);
    }

    @OnClick({R.id.pantalla_calculadora, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9, R.id.btn0, R.id.btnMas, R.id.btnIgual, R.id.btnClear, R.id.btnPunto, R.id.btnMenos})
    public void pulsaBotonCalculadora(View view) {
        pulsaDigitoCalculadora(view);

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == NUEVA_DIVISA_ORI && resultCode == RESULT_OK) {
            String divisaRes = data.getExtras().getString("moneda");

            if (!btnDes.getText().toString().isEmpty() && btnDes.getText().toString().equals(divisaRes)) {
                Toast.makeText(getContext(), getString(R.string.error_divisas_iguales), Toast.LENGTH_SHORT).show();
            } else {
                btnOri.setText(divisaRes);
                btnCNV.setText(divisaRes + btnCNV.getText().toString().substring(3));
                btnCNV2.setText(btnCNV.getText().toString().substring(0, 8) + divisaRes);

            }
        } else if (requestCode == NUEVA_DIVISA_DES && resultCode == RESULT_OK)

        {
            String divisaRes = data.getExtras().getString("moneda");

            if (!btnOri.getText().toString().isEmpty() && btnOri.getText().toString().equals(divisaRes)) {
                Toast.makeText(getContext(), getString(R.string.error_divisas_iguales), Toast.LENGTH_SHORT).show();

            } else {
                btnCNV.setText(btnCNV.getText().toString().substring(0, 8) + divisaRes);
                btnDes.setText(divisaRes);
                btnCNV2.setText(divisaRes + btnCNV.getText().toString().substring(3));
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}