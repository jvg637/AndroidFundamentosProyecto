package es.maps.programacion.fundamentos.androidfundamentosproyecto.lib.divisas;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Vector;

import es.maps.programacion.fundamentos.androidfundamentosproyecto.R;
import es.maps.programacion.fundamentos.androidfundamentosproyecto.application.MapsApplication;
import es.maps.programacion.fundamentos.androidfundamentosproyecto.lib.paises.pojo.Divisa;
import es.maps.programacion.fundamentos.androidfundamentosproyecto.lib.paises.pojo.Pais;
import es.maps.programacion.fundamentos.androidfundamentosproyecto.sqlite.PaisesDivisasSQLite;

public class ConvertidorDivisas extends AppCompatActivity {

    public static final int MODO_PAIS = 1;
    // private Spinner divisaOrigen, divisaDestino;

    private ListView lvOrigen;
    private EditText etOrigen;
    private Button btnCancelar;

    private int modo;

    // private String listview_array[] = { "ONE", "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN", "EIGHT", "NINE", "TEN" };
    private ArrayList<String> array_sortOrigen = new ArrayList<String>();
    int textlength = 0;
    private String[] items;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convertidor_divisas);


        modo = (getIntent().getAction() != null && getIntent().getAction().equals(getResources().getString(R.string.accion_pais_origen))) ? 1 : 0;

        etOrigen = (EditText) findViewById(R.id.etDivisaOrigen);
        //etDestino = (EditText) findViewById(R.id.etDivisaDestino);

        obtieneDivisas();

        Button btnAceptar = (Button) findViewById(R.id.btnCalcularTipoCambio);
        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                validarSeleccionar(v);

            }
        });
        Button btnCancelar = (Button) findViewById(R.id.btnCancelar);
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });

        //txtImporteOrigen = (EditText) findViewById(R.id.editTextImporteOrigen);
        //txtImporteDestino = (TextView) findViewById(R.id.editTextImporteDestino);

        lvOrigen = (ListView) findViewById(R.id.lvDvisaOrigen);
        //lvDestino = (ListView) findViewById(R.id.lvDvisaDestino);

        lvOrigen.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, items));
        lvOrigen.setClickable(true);
        lvOrigen.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                etOrigen.setText(lvOrigen.getItemAtPosition(position).toString().split("-")[0] + "-");
                view.setSelected(true);

                ocutalEntradaTeclado();

            }

        });


        etOrigen.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                // Abstract Method of TextWatcher Interface.
            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // Abstract Method of TextWatcher Interface.
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                textlength = etOrigen.getText().length();
                array_sortOrigen.clear();

                for (int i = 0; i < items.length; i++) {
                    if (textlength <= items[i].length()) {
                        if (items[i].toUpperCase().contains(etOrigen.getText().toString().toUpperCase())) {
                            array_sortOrigen.add(items[i]);
                        }
                    }
                }

                lvOrigen.setAdapter(new ArrayAdapter<String>(ConvertidorDivisas.this, android.R.layout.simple_list_item_1, array_sortOrigen));

            }
        });


        if (modo == MODO_PAIS) {
            SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
            String paisDefault = pref.getString(getString(R.string.pref_pais_origen), "");

            if (!paisDefault.isEmpty()) {
                etOrigen.setText(paisDefault + "-");

            }
        }


    }

    private void obtieneDivisas() {
        PaisesDivisasSQLite db = new PaisesDivisasSQLite(this);

        boolean espanyol = ((MapsApplication) getApplication()).esIdiomaEspanyol();


        if (modo == MODO_PAIS) {

            Vector<Pais> auxVector = null;
            auxVector = db.listarPaises();


            items = new String[auxVector.size()];

            int i = 0;
            for (Pais divisa : auxVector) {
                if (espanyol) {
                    items[i] = divisa.getIdPais() + "-" + divisa.getPaisES();
                } else {
                    items[i] = divisa.getIdPais() + "-" + divisa.getPaisEN();
                }
                i++;
            }

        } else {
            Vector<Divisa> divisas = null;
            divisas = db.listarDivisas();


            items = new String[divisas.size()];

            int i = 0;
            for (Divisa divisa : divisas) {
                if (espanyol) {
                    items[i] = divisa.getIdDivisa() + "-" + divisa.getDivisaES();
                } else {
                    items[i] = divisa.getIdDivisa() + "-" + divisa.getDivisaEN();
                }
                i++;
            }

        }

    }

    private void ocutalEntradaTeclado() {
        InputMethodManager inputMethodManager =
                (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
    }

    private void validarSeleccionar(View view) {
        String monedaO;
        if (array_sortOrigen != null && array_sortOrigen.size() == 1) {

            if (modo == MODO_PAIS) {
                SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                SharedPreferences.Editor editor = pref.edit();
                editor.putString(getString(R.string.pref_pais_origen), lvOrigen.getItemAtPosition(0).toString().split("-")[0]);
                editor.commit();

            } else {
                setResult(RESULT_OK, new Intent().putExtra("moneda", lvOrigen.getItemAtPosition(0).toString().split("-")[0]));
            }

            finish();

        } else

        {
            Toast.makeText(this, "Opción no seleccionada!", Toast.LENGTH_SHORT).show();

        }
    }
}
