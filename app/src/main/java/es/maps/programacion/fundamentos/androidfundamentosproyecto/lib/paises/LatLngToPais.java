package es.maps.programacion.fundamentos.androidfundamentosproyecto.lib.paises;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.util.TypedValue;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;

import es.maps.programacion.fundamentos.androidfundamentosproyecto.R;
import es.maps.programacion.fundamentos.androidfundamentosproyecto.application.MapsApplication;
import es.maps.programacion.fundamentos.androidfundamentosproyecto.lib.paises.pojo.Divisa;
import es.maps.programacion.fundamentos.androidfundamentosproyecto.lib.paises.pojo.Pais;
import es.maps.programacion.fundamentos.androidfundamentosproyecto.sqlite.PaisesDivisasSQLite;
import es.maps.programacion.fundamentos.androidfundamentosproyecto.ui.tab.TabMapa;

/**
 * Created by jvg63 on 20/11/2016.
 */

public class LatLngToPais {
    //private static final String FICHERO = "C:\\Users\\jvg63\\Desktop\\googlemaps.json2";
    private final Context context;
    private String string; //Almacena puntuaciones en formato JSON
    private Pais pais = null;
    private LatLng posIni = null;
    private String paisIntencion;
    private LatLng latLngOut;
    private Pais paisLatLng;
    private Drawable banderaCargada = null;


    public LatLngToPais(Context context) {
        //StrictMode.se
        this.context = context;

    }


    // private String URL_GOOGLE_MAPS = "https://maps.googleapis.com/maps/api/geocode/json?latlng=%param1,%param2&key=AIzaSyAUFDzgL5HvzzCxu7CUrYETEa7KRyxLkgQ";
    private String URL_GOOGLE_MAPS = "https://maps.googleapis.com/maps/api/geocode/json?latlng=%param1,%param2";
    private String URL_GOOGLE_MAPS_LAT_LNG = "https://maps.googleapis.com/maps/api/geocode/json?address=";

    private final String TAG = LatLngToPais.class.getSimpleName();

    private TabMapa tabMapa;

    private void showMessage(String msg) {
        //Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
        Log.d(TAG, "Value is: " + msg);
    }

    public void getPais(LatLng pos, TabMapa tabMapa) {

        this.posIni = pos;
        this.tabMapa = tabMapa;

        new TareaObtenerPais().execute(pos);


    }

    public LatLng getLatLng(String pais, TabMapa tabMapa) {

        this.paisIntencion = pais;
        this.tabMapa = tabMapa;

        showMessage("paisIntencion=" + pais);

        new TareaObtenerLatLongDePais().execute(pais);

        return latLngOut;
    }

    class TareaObtenerLatLongDePais extends AsyncTask<String, Integer, LatLng> {
        private ProgressDialog progreso;

        @Override
        protected void onPreExecute() {
            /*progreso = new ProgressDialog(MainActivity);
            progreso.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progreso.setMessage("Calculando...");
            progreso.setCancelable(true);
            progreso.setOnCancelListener(new DialogInterface.OnCancelListener() {
                                             @Override
                                             public void onCancel(DialogInterface dialog) {
                                                 TareaObtenerPais.this.cancel(true);
                                             }
                                         }

            );
            progreso.setMax(100);
            progreso.setProgress(0);
            progreso.show();*/
        }

        @Override
        protected LatLng doInBackground(String... n) {
            LatLng auxLng = getLatLng2(n[0]);
            if (pais != null)
                banderaCargada = tabMapa.asignaImagen(paisLatLng.getIcono());

            return auxLng;
        }

        @Override
        protected void onProgressUpdate(Integer... porc) {
            progreso.setProgress(porc[0]);
        }

        @Override
        protected void onPostExecute(LatLng res) {
            posIni = res;

            pais = paisLatLng;

            muestraPaisEnMapa();
        }


        @Override
        protected void onCancelled() {
            //salida.append("cancelado\n");
        }
    }

    private String getUrl(Pais pais) {
        return (((MapsApplication) context.getApplicationContext()).esIdiomaEspanyol()) ? pais.getUrlES() : pais.getUrlEN();
    }


    private String getPais(Pais pais) {
        return (((MapsApplication) context.getApplicationContext()).esIdiomaEspanyol()) ? pais.getPaisES() : pais.getPaisEN();


    }


    private String getDivisa(Divisa divisa) {
        return (((MapsApplication) context.getApplicationContext()).esIdiomaEspanyol()) ? divisa.getDivisaES() : divisa.getDivisaEN();

    }

    class TareaObtenerPais extends AsyncTask<LatLng, Integer, Pais> {
        private ProgressDialog progreso;

        @Override
        protected void onPreExecute() {
            /*progreso = new ProgressDialog(MainActivity);
            progreso.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progreso.setMessage("Calculando...");
            progreso.setCancelable(true);
            progreso.setOnCancelListener(new DialogInterface.OnCancelListener() {
                                             @Override
                                             public void onCancel(DialogInterface dialog) {
                                                 TareaObtenerPais.this.cancel(true);
                                             }
                                         }

            );
            progreso.setMax(100);
            progreso.setProgress(0);
            progreso.show();*/
        }

        @Override
        protected Pais doInBackground(LatLng... n) {
            Pais auxPais = getPais2(n[0]);

            if (auxPais != null)
                banderaCargada = tabMapa.asignaImagen(auxPais.getIcono());

            return auxPais;
        }

        @Override
        protected void onProgressUpdate(Integer... porc) {
            progreso.setProgress(porc[0]);
        }

        @Override
        protected void onPostExecute(Pais res) {
            //progreso.dismiss();
            pais = res;

            muestraPaisEnMapa();
        }


        @Override
        protected void onCancelled() {
            //salida.append("cancelado\n");
        }
    }

    private void muestraPaisEnMapa() {
        showMessage((pais != null ? pais.getIdPais() : "") + " " + tabMapa.paisActual);
        if (pais != null && !pais.getIdPais().equals(tabMapa.paisActual) && tabMapa.mediaPlayer != null) {
            if (tabMapa.estado == tabMapa.ESTADO_PLAY)
                tabMapa.mediaPlayer.stop();

            tabMapa.mediaPlayer.release();

            tabMapa.estado = tabMapa.ESTADO_NO_INICIADO;
        }

        if (pais != null)
            tabMapa.paisActual = pais.getIdPais();
        else
            tabMapa.paisActual = null;

        if (tabMapa.posicionActual != null) {
            tabMapa.posicionActual.remove();
        }

        if (pais != null) {
            if (pais.getHimno() != null && !pais.getHimno().isEmpty()) {


                tabMapa.showPlayer();
                tabMapa.path = pais.getHimno();
            } else {
                tabMapa.hidePlayer();
                tabMapa.path = "";
            }

            if (posIni != null) {
                tabMapa.posicionActual = tabMapa.map.addMarker(new MarkerOptions().position(posIni).icon(tabMapa.marcadorColor));
                tabMapa.posicionActual.setTitle(getPais(pais) + "-" + pais.getIdPais());
                tabMapa.posicionActual.setSnippet("(" + posIni.latitude + "," + posIni.longitude + ")");
            }

            tabMapa.url = getUrl(pais);


            if (banderaCargada != null) {
                tabMapa.bandera.setImageDrawable(banderaCargada);
            } else {
                tabMapa.bandera.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.no_icon));
            }

            int tamanyo = getPais(pais).length();
            float fontSize = 0.0f;
            if (tamanyo >= 30) {
                fontSize = 10;
            } else if (tamanyo >= 20) {
                fontSize = 12;
            } else if (tamanyo >= 10) {
                fontSize = 15;
            } else {
                fontSize = 25;
            }

            tabMapa.txtPais.setTextSize(TypedValue.COMPLEX_UNIT_SP, (fontSize));
            tabMapa.txtPais.setText(getPais(pais));


            if (pais.getDivisas() != null && !pais.getDivisas().isEmpty()) {
                PaisesDivisasSQLite pd = new PaisesDivisasSQLite(context);

                String divisas[] = pais.getDivisas().split("-");

                String txtAuxDiv = "";

                for (int i = 0; i < divisas.length; i++) {
                    Divisa textoDivisa = pd.getDivisa(divisas[i]);
                    txtAuxDiv += "\t" + getDivisa(textoDivisa).substring(0, Math.min(36, getDivisa(textoDivisa).length())) + "\n";
                    //txtAuxDiv += "\t" + divisas[i] + "\n";
                }
                tabMapa.txtMoneda.setText("Moneda/s:\n" + txtAuxDiv);

            } else {
                tabMapa.txtMoneda.setText(context.getString(R.string.sinmoneda));

            }

            if (posIni != null)
                tabMapa.map.moveCamera(CameraUpdateFactory.newLatLng(posIni));

        } else {


            tabMapa.bandera.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.no_icon));
            tabMapa.hidePlayer();
            tabMapa.path = "";
            tabMapa.txtPais.setText(context.getResources().getString(R.string.sinpais));
            tabMapa.txtMoneda.setText(context.getResources().getString(R.string.sinmoneda));

            if (posIni != null) {
                tabMapa.posicionActual = tabMapa.map.addMarker(new MarkerOptions().position(posIni).icon(tabMapa.marcadorColor));
                tabMapa.posicionActual.setTitle("Unnamed");
                tabMapa.map.moveCamera(CameraUpdateFactory.newLatLngZoom(posIni, 10));
                tabMapa.posicionActual.setSnippet("(" + posIni.latitude + "," + posIni.longitude + ")");
            }
        }
    }

    private Pais getPais2(LatLng latLng) {

        URL url = null;
        Pais pais = null;
        HttpsURLConnection conexion = null;
        String json = "";
        try {

            url = new URL(URL_GOOGLE_MAPS.replace("%param1", "" + latLng.latitude).replace("%param2", "" + latLng.longitude));


            showMessage(url.toString());

            conexion = (HttpsURLConnection) url.openConnection();
            //conexion.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 6.1)");


            if (conexion.getResponseCode() == HttpsURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
                String linea;
                while ((linea = reader.readLine()) != null) {
                    //showMessage(linea);
                    json += linea;
                }
                reader.close();

                pais = leerJSONPais(json);
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


    private LatLng getLatLng2(String pais) {

        URL url = null;
        LatLng latLng = null;
        HttpsURLConnection conexion = null;
        String json = "";
        try {

            paisLatLng = new PaisesDivisasSQLite(context).getPais(paisIntencion);

            url = new URL(URL_GOOGLE_MAPS_LAT_LNG + URLEncoder.encode(paisLatLng.getPaisEN(), "UTF-8"));


            showMessage(url.toString());

            conexion = (HttpsURLConnection) url.openConnection();
            //conexion.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 6.1)");


            if (conexion.getResponseCode() == HttpsURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
                String linea;
                while ((linea = reader.readLine()) != null) {
                    //showMessage(linea);
                    json += linea;
                }
                reader.close();

                latLng = leerJSONLatLng(json);
            } else {
                showMessage("Error obteniendo la posición " + conexion.getResponseMessage());

            }
        } catch (Exception e) {
            showMessage("Error obteniendo la posición " + conexion.getResponseMessage());


        } finally {
            if (conexion != null)
                conexion.disconnect();
            return latLng;
        }
    }

    private LatLng leerJSONLatLng(String json) {
        LatLng latLng = null;

        String jsonString = new String();


        showMessage(json);
        try {


            JSONObject raiz = new JSONObject(json);

            if (raiz != null && raiz.getString("status").equals("OK")) {

                JSONArray ubicaciones = raiz.getJSONArray("results");


                JSONObject ubicacion = ubicaciones.getJSONObject(0).getJSONObject("geometry").getJSONObject("location");

                float latitud = Float.parseFloat(ubicacion.getString("lat"));
                float longitud = Float.parseFloat(ubicacion.getString("lng"));

                latLng = new LatLng(latitud, longitud);

            }
        } catch (Exception ex) {
            showMessage("Error decodificando el json de GOOGLEMAPS " + ex.getMessage());
        }


        return latLng;
    }

    private Pais leerJSONPais(String json) {
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
