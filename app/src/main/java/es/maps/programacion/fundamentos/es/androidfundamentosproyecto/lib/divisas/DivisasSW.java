package es.maps.programacion.fundamentos.es.androidfundamentosproyecto.lib.divisas;

import android.os.StrictMode;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by jvg63 on 20/11/2016.
 */

public class DivisasSW  {

    public DivisasSW() {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitNetwork().build());
    }

    public float  tipoCambio(String divisaOrigen, String divisaDestino) {

        URL url = null;
        float importe=-1;
        HttpURLConnection conexion = null;
        try {
            url = new URL(Divisas.urlCambio.replace("%1%", divisaOrigen+ divisaDestino));
            conexion = (HttpURLConnection) url.openConnection();
            if (conexion.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
                String linea = reader.readLine();
                while (linea!=null) {
                    Log.d("LINEA:", linea);
                    importe= Float.parseFloat(linea);
                    linea = reader.readLine();
                }
                reader.close();
            } else {
                Log.e("Asteroides", conexion.getResponseMessage());

            }
        } catch (Exception e) {
            Log.e("Asteroides", e.getMessage(), e);


        } finally {
            if (conexion != null)
                conexion.disconnect();
            return importe;
        }
    }
}