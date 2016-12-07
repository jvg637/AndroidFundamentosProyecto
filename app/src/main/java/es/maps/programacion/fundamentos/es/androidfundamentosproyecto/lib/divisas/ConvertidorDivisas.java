package es.maps.programacion.fundamentos.es.androidfundamentosproyecto.lib.divisas;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import es.maps.programacion.fundamentos.androidfundamentosproyecto.R;

public class ConvertidorDivisas extends AppCompatActivity {

    // private Spinner divisaOrigen, divisaDestino;
    private EditText txtImporteOrigen;
    TextView txtImporteDestino;
    private ListView lvOrigen;
    private EditText etOrigen;
    private ListView lvDestino;
    private EditText etDestino;
    // private String listview_array[] = { "ONE", "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN", "EIGHT", "NINE", "TEN" };
    private ArrayList<String> array_sortOrigen = new ArrayList<String>();
    private ArrayList<String> array_sortDestino = new ArrayList<String>();
    int textlength = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convertidor_divisas);

        Button btnCalcularTipoCambio = (Button) findViewById(R.id.btnCalcularTipoCambio);
        btnCalcularTipoCambio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tipoCambio(null);
            }
        });

        txtImporteOrigen = (EditText) findViewById(R.id.editTextImporteOrigen);
        txtImporteDestino = (TextView) findViewById(R.id.editTextImporteDestino);

        lvOrigen = (ListView) findViewById(R.id.lvDvisaOrigen);
        lvDestino = (ListView) findViewById(R.id.lvDvisaDestino);

        etOrigen = (EditText) findViewById(R.id.etDivisaOrigen);
        etDestino = (EditText) findViewById(R.id.etDivisaDestino);

        lvOrigen.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, Divisas.items));
        lvOrigen.setClickable(true);
        lvOrigen.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                etOrigen.setText(lvOrigen.getItemAtPosition(position).toString().substring(0,4));
                view.setSelected(true);
            }

        });

        lvDestino.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, Divisas.items));
        lvDestino.setClickable(true);
        lvDestino.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                etDestino.setText(lvDestino.getItemAtPosition(position).toString().substring(0,4));
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

                for (int i = 0; i < Divisas.items.length; i++) {
                    if (textlength <= Divisas.items[i].length()) {
                        if (Divisas.items[i].toUpperCase().contains(etOrigen.getText().toString().toUpperCase())) {
                            array_sortOrigen.add(Divisas.items[i]);
                        }
                    }
                }

                lvOrigen.setAdapter(new ArrayAdapter<String>(ConvertidorDivisas.this, android.R.layout.simple_list_item_1, array_sortOrigen));

            }
        });

        etDestino.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                // Abstract Method of TextWatcher Interface.
            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // Abstract Method of TextWatcher Interface.
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                textlength = etDestino.getText().length();
                array_sortDestino.clear();

                for (int i = 0; i < Divisas.items.length; i++) {
                    if (textlength <= Divisas.items[i].length()) {
                        if (Divisas.items[i].toUpperCase().contains(etDestino.getText().toString().toUpperCase())) {
                            array_sortDestino.add(Divisas.items[i]);
                        }
                    }
                }

                lvDestino.setAdapter(new ArrayAdapter<String>(ConvertidorDivisas.this, android.R.layout.simple_list_item_1, array_sortDestino));

            }
        });
    }



    private void tipoCambio(View view) {
        String monedaO;
        String monedaD;
        if (array_sortOrigen != null && array_sortOrigen.size() == 1)
            monedaO = lvOrigen.getItemAtPosition(0).toString().split("-")[0];

        else {
            Toast.makeText(this, "Moneda Origen no seleccionada!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (array_sortDestino != null && array_sortDestino.size() == 1)
            monedaD = lvDestino.getItemAtPosition(0).toString().split("-")[0];

        else {
            Toast.makeText(this, "Moneda Destino no seleccionada!", Toast.LENGTH_SHORT).show();
            return;
        }


        if (monedaO.equals(monedaD)) {
            Toast.makeText(this, "Las monedas deben ser distintas!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (txtImporteOrigen.getText().toString().isEmpty()) {
            Toast.makeText(this, "Importe Origen no Seleccionado!", Toast.LENGTH_SHORT).show();
            return;
        }



        try {
            float importe1 = new DivisasSW().tipoCambio(monedaO, monedaD);

            txtImporteDestino.setText("");

            if (importe1<0) {
                Toast.makeText(this, "Ha ocurrido un error en el acceso al tipo de cambio!", Toast.LENGTH_SHORT).show();
                return;
            }

            //Toast.makeText(this, "" + importe1, Toast.LENGTH_SHORT).show();

            txtImporteDestino.setText(String.valueOf(importe1 * Float.parseFloat(txtImporteOrigen.getText().toString().replace(",", "."))));
        } catch (Exception ex) {
            Toast.makeText(this, "Ha ocurrido un error! Inténtelo de nuevo", Toast.LENGTH_SHORT).show();

        }

    }
}
