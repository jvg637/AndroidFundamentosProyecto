package es.maps.programacion.fundamentos.es.androidfundamentosproyecto.lib.paises;

import android.content.Context;
import android.os.StrictMode;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import es.maps.programacion.fundamentos.es.androidfundamentosproyecto.sqlite.PaisesDivisasSQLite;
import es.maps.programacion.fundamentos.es.androidfundamentosproyecto.sqlite.pojo.Pais;

/**
 * Created by jvg63 on 20/11/2016.
 */

public class LatLngToPais {
    private static final String FICHERO = "C:\\Users\\jvg63\\Desktop\\googlemaps.json2";
    private final Context context;
    private String string; //Almacena puntuaciones en formato JSON

    public LatLngToPais(Context context) {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitNetwork().build());
        this.context = context;

    }


   // private String URL_GOOGLE_MAPS = "https://maps.googleapis.com/maps/api/geocode/json?latlng=%param1,%param2&key=AIzaSyAUFDzgL5HvzzCxu7CUrYETEa7KRyxLkgQ";
    private String URL_GOOGLE_MAPS = "https://maps.googleapis.com/maps/api/geocode/json?latlng=%param1,%param2";

    private final String TAG = LatLngToPais.class.getSimpleName();

    private void showMessage(String msg) {
        //Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
        Log.d(TAG, "Value is: " + msg);
    }

    public Pais getPais(LatLng latLng) {

        URL url = null;
        Pais pais = null;
        HttpsURLConnection conexion = null;
        String json = "";
        try {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitNetwork().build());
            url = new URL(URL_GOOGLE_MAPS.replace("%param1", "" + latLng.latitude).replace("%param2", "" + latLng.longitude));


            showMessage(url.toString());

            conexion = (HttpsURLConnection) url.openConnection();
            //conexion.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 6.1)");


            if (conexion.getResponseCode() == HttpsURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
                String linea;
                while ((linea = reader.readLine()) != null) {
                    showMessage(linea);
                    json += linea;
                }
                reader.close();

                pais = leerJSon(json);
            } else {
                showMessage("Error obteniendo el pais " + conexion.getResponseMessage());

            }
        } catch (Exception e) {
            showMessage("Error obteniendo el pais " + conexion.getResponseMessage());


        } finally {
            if (conexion != null)
                conexion.disconnect();
            return pais;
        }
    }


    private Pais leerJSon(String json) {
        Pais pais = null;
        String paisId = "";

        String jsonString = new String();

        try {
            /*InputStream f = context.getResources().openRawResource(R.raw.googlemaps);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(f));

            String linea;
            String json = new String();


            while ((linea = entrada.readLine()) != null) {
                json += linea;
            }

            f.close();*/

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
                                showMessage("PAIS:" + paisId);

                                PaisesDivisasSQLite pd = new PaisesDivisasSQLite(context);
                                pais = pd.getPais(paisId);

                                break;
                            }
                        }
                    }

                }
            }


        } catch (Exception ex) {
            showMessage("Error decodificando el json de GOOGLEMAPS " + ex.getMessage());

        }


        return pais;
    }


}
