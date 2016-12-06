package es.maps.programacion.fundamentos.es.androidfundamentosproyecto.lib;

import android.content.Context;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import es.maps.programacion.fundamentos.androidfundamentosproyecto.R;
import es.maps.programacion.fundamentos.es.androidfundamentosproyecto.sqlite.pojo.Pais;
import es.maps.programacion.fundamentos.es.androidfundamentosproyecto.sqlite.PaisesDivisasSQLite;

/**
 * Created by jvg63 on 20/11/2016.
 */

public class LatLngToPais {
    private static final String FICHERO = "C:\\Users\\jvg63\\Desktop\\googlemaps.json2";
    private final Context context;
    private String string; //Almacena puntuaciones en formato JSON

    public LatLngToPais(Context context) {
        this.context = context;
    }

    public Pais leerJSon(LatLng latLng) {
        Pais pais = null;
        String paisId="";

        String jsonString = new String();

        try {
            InputStream f = context.getResources().openRawResource(R.raw.googlemaps);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(f));

            String linea;
            String json = new String();


            while ((linea = entrada.readLine()) != null) {
                json += linea;
            }

            f.close();

            JSONObject raiz = new JSONObject(json);

            if (raiz != null && raiz.getString("status").equals("OK")) {

                JSONArray ubicaciones = raiz.getJSONArray("results");

                if (ubicaciones != null && ubicaciones.length() > 0) {
                    JSONArray direcciones = ubicaciones.getJSONObject(0).getJSONArray("address_components");

                    for (int i = 0; i < direcciones.length() && paisId.isEmpty(); i++) {
                        JSONObject objeto = direcciones.getJSONObject(i);

                        JSONArray tipo = objeto.getJSONArray("types");


                        for (int j = 0; j < tipo.length(); j++) {
                            if (tipo.getString(j).equals("country")) {
                                paisId = objeto.getString("short_name");
                                Log.d("PAIS", paisId);

                                PaisesDivisasSQLite pd = new PaisesDivisasSQLite(context);
                                pais = pd.getPais(paisId);

                                break;
                            }
                        }
                    }

                }
            }


        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println(ex.getCause());

        }


        return pais;
    }


}
