package es.maps.programacion.fundamentos.androidfundamentosproyecto.ui.tab;


import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
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
    @BindView(R.id.btnPor)
    Button btnPor;
    @BindView(R.id.btnDiv)
    Button btnDiv;

    SharedPreferences pref = null;

    private String paisDefault = "";

    private void inicializaComponentes() {

        pref = PreferenceManager.getDefaultSharedPreferences(getContext());

        obtenerDivisaPaisPreferencias();

    }

    private void obtenerDivisaPaisPreferencias() {

        pref = PreferenceManager.getDefaultSharedPreferences(getContext());

        cambiaDivisas();


    }

    private void cambiaDivisas() {
        paisDefault = pref.getString(getString(R.string.pref_pais_origen), "");

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
    public void onPause() {
        super.onPause();
    }


    @Override
    public void onResume() {
        super.onResume();


        String paisDefaultAux = pref.getString(getString(R.string.pref_pais_origen), "");
        if (paisDefaultAux != null && !paisDefaultAux.isEmpty() && !paisDefault.equals(paisDefaultAux))
            cambiaDivisas();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.calculadora_tab, container, false);
        ButterKnife.bind(this, view);

        inicializaComponentes();


        return view;
    }

    void pulsaDigitoCalculadora(View view) {

        tag = view.getTag().toString();
        sumando = 0d;
        // Inicializa
        //sumando = acumulador = 0d;
        //tagPrevia = "";
        //flagNuevoNumero = flagDecimalIntroducido = false;


        //pantallaCalculadora = (TextView) getActivity().findViewById(R.id.pantalla_calculadora);

        if (flagNuevoNumero && !tag.equals(getResources().getString(R.string.digito_IGUAL_calculadora)) &&
                !tag.equals(getResources().getString(R.string.digito_MAS_calculadora)) &&
                !tag.equals(getResources().getString(R.string.digito_MENOS_calculadora)) &&
                !tag.equals(getResources().getString(R.string.digito_POR_calculadora)) &&
                !tag.equals(getResources().getString(R.string.digito_DIV_calculadora))) {
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

            } else if (tag.equals(getResources().getString(R.string.digito_MAS_calculadora)) ||
                    tag.equals(getResources().getString(R.string.digito_MENOS_calculadora)) ||
                    tag.equals(getResources().getString(R.string.digito_POR_calculadora)) ||
                    tag.equals(getResources().getString(R.string.digito_DIV_calculadora)))
                {
                sumando = pantallaCalculadora.getText().toString().length() > 0d ? Double.parseDouble(pantallaCalculadora.getText().toString()) : 0d;
                formateaPantalla();
            } else if (tag.equals(getResources().getString(R.string.digito_IGUAL_calculadora))) {

                formateaPantalla();

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

    // Variables calculadora
    private Double sumando = 0d, acumulador = 0d;
    String tag = "", tagPrevia = "";
    private boolean flagNuevoNumero = true, flagDecimalIntroducido = false;


    private void formateaPantalla() {

        flagNuevoNumero = true;
        flagDecimalIntroducido = false;

        if (tagPrevia.equals(getResources().getString(R.string.digito_MAS_calculadora)))
            acumulador += Double.parseDouble(pantallaCalculadora.getText().toString());
        else if (tagPrevia.equals(getResources().getString(R.string.digito_MENOS_calculadora)))
            acumulador -= Double.parseDouble(pantallaCalculadora.getText().toString());
        else if (tagPrevia.equals(getResources().getString(R.string.digito_POR_calculadora)))
            acumulador *= Double.parseDouble(pantallaCalculadora.getText().toString());
        else if (tagPrevia.equals(getResources().getString(R.string.digito_DIV_calculadora)))
            acumulador /= Double.parseDouble(pantallaCalculadora.getText().toString());
        else
            acumulador = sumando;

        tagPrevia = tag;

        if (this.acumulador % 1f == 0f) {
            actualizaPantalla(tagPrevia, String.format("%d", (this.acumulador.longValue())).replace(",","."));
        } else {
            actualizaPantalla(tagPrevia, String.format("%.2f", (this.acumulador)).replace(",","."));
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
    void convierteDivisa1() {

        convierteDivisa(1);
    }

    @OnClick({R.id.btnCNV2})
    void convierteDivisa2() {

        convierteDivisa(2);
    }

    private void convierteDivisa(int opcion) {
        try {
            double tipoDeCambio = -1;

            new TareaTipoCambio().execute(String.valueOf(opcion),btnDes.getText().toString(), btnOri.getText().toString());

            //Toast.makeText(this, "" + importe1, Toast.LENGTH_SHORT).show();


        } catch (Exception ex) {
            Toast.makeText(getContext(), "Ha ocurrido un error! Inténtelo de nuevo", Toast.LENGTH_SHORT).show();

        }
    }


    private void actualizaTipoCambio(double tipoDeCambio) {
        pantallaCalculadora.setText(String.valueOf(tipoDeCambio * Double.parseDouble(pantallaCalculadora.getText().toString())));

        flagNuevoNumero=true;
        flagDecimalIntroducido=false;
    }



    class TareaTipoCambio extends AsyncTask<String, Integer, Double> {
        private ProgressDialog progreso;

        @Override
        protected void onPreExecute() {
            progreso = new ProgressDialog(getActivity());
            progreso.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progreso.setMessage("Calculando...");
            progreso.setCancelable(true);
            progreso.setOnCancelListener(new DialogInterface.OnCancelListener() {
                                             @Override
                                             public void onCancel(DialogInterface dialog) {
                                                 cancel(true);
                                             }
                                         }

            );
            progreso.setMax(100);
            progreso.setProgress(0);
            progreso.show();
        }

        @Override
        protected Double doInBackground(String... n) {
            double tipoDeCambio=-1;

            publishProgress(5);
            if (n[0].equals("1")) {

                tipoDeCambio = new DivisasSW().tipoCambio(n[1], n[2]);

            }
            else {
                tipoDeCambio = new DivisasSW().tipoCambio(n[2], n[1]);


            }
            publishProgress(95);

            return tipoDeCambio;
        }

        @Override
        protected void onProgressUpdate(Integer... porc) {
            progreso.setProgress(porc[0]);
        }

        @Override
        protected void onPostExecute(Double res) {
            progreso.dismiss();
            actualizaTipoCambio(res);
        }


        @Override
        protected void onCancelled() {
            //salida.append("cancelado\n");
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

    @OnClick({R.id.btnPor, R.id.btnDiv, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9, R.id.btn0, R.id.btnMas, R.id.btnIgual, R.id.btnClear, R.id.btnPunto, R.id.btnMenos})
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
                btnCNV2.setText(btnCNV2.getText().toString().substring(0, 8) + divisaRes);

            }
        } else if (requestCode == NUEVA_DIVISA_DES && resultCode == RESULT_OK)

        {
            String divisaRes = data.getExtras().getString("moneda");

            if (!btnOri.getText().toString().isEmpty() && btnOri.getText().toString().equals(divisaRes)) {
                Toast.makeText(getContext(), getString(R.string.error_divisas_iguales), Toast.LENGTH_SHORT).show();

            } else {
                btnCNV.setText(btnCNV.getText().toString().substring(0, 8) + divisaRes);
                btnDes.setText(divisaRes);
                btnCNV2.setText(divisaRes + btnCNV2.getText().toString().substring(3));
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

}