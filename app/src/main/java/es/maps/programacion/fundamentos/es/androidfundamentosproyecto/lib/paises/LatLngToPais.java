package es.maps.programacion.fundamentos.es.androidfundamentosproyecto.lib.paises;

import android.app.ProgressDialog;
import android.content.Context;
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

import javax.net.ssl.HttpsURLConnection;

import es.maps.programacion.fundamentos.androidfundamentosproyecto.R;
import es.maps.programacion.fundamentos.es.androidfundamentosproyecto.sqlite.PaisesDivisasSQLite;
import es.maps.programacion.fundamentos.es.androidfundamentosproyecto.sqlite.pojo.Pais;
import es.maps.programacion.fundamentos.es.androidfundamentosproyecto.ui.TabMapa;

/**
 * Created by jvg63 on 20/11/2016.
 */

public class LatLngToPais {
    //private static final String FICHERO = "C:\\Users\\jvg63\\Desktop\\googlemaps.json2";
    private final Context context;
    private String string; //Almacena puntuaciones en formato JSON
    private Pais pais =null;
    private LatLng pos =null;


    public LatLngToPais(Context context) {
        //StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitNetwork().build());
        this.context = context;

    }


    // private String URL_GOOGLE_MAPS = "https://maps.googleapis.com/maps/api/geocode/json?latlng=%param1,%param2&key=AIzaSyAUFDzgL5HvzzCxu7CUrYETEa7KRyxLkgQ";
    private String URL_GOOGLE_MAPS = "https://maps.googleapis.com/maps/api/geocode/json?latlng=%param1,%param2";

    private final String TAG = LatLngToPais.class.getSimpleName();

    private TabMapa tabMapa;

    private void showMessage(String msg) {
        //Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
        Log.d(TAG, "Value is: " + msg);
    }

    public void getPais(LatLng pos, TabMapa tabMapa) {

        this.pos = pos;
        this.tabMapa = tabMapa;

        new MiTarea().execute(pos);




    }

    class MiTarea extends AsyncTask<LatLng, Integer, Pais> {
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
                                                 MiTarea.this.cancel(true);
                                             }
                                         }

            );
            progreso.setMax(100);
            progreso.setProgress(0);
            progreso.show();*/
        }

        @Override
        protected Pais doInBackground(LatLng... n) {
            return getPais2(n[0]);
        }

        @Override
        protected void onProgressUpdate(Integer... porc) {
            progreso.setProgress(porc[0]);
        }

        @Override
        protected void onPostExecute(Pais res) {
            //progreso.dismiss();
            pais = res;
            //salida.append(res + "\n");

            /*if (pais != null && pais.getIdPais().equals(tabMapa.paisActual))
                return;*/

            // Detiene la

            showMessage((pais!=null?pais.getIdPais():"")+ " " + tabMapa.paisActual);
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

                tabMapa.posicionActual = tabMapa.map.addMarker(new MarkerOptions().position(pos).icon(tabMapa.marcadorColor));
                tabMapa.posicionActual.setTitle(pais.getPaisES() + "-" + pais.getIdPais());
                tabMapa.posicionActual.setSnippet("(" + pos.latitude + "," + pos.longitude + ")");

                tabMapa.url = pais.getUrlES();

                tabMapa.map.moveCamera(CameraUpdateFactory.newLatLng(pos));

                tabMapa.asignaImagen(pais.getIcono(), tabMapa.bandera);

                int tamanyo = pais.getPaisES().length();
                float fontSize = 0.0f;
                if (tamanyo >= 30)
                    fontSize = 10;
                else if (tamanyo >= 20)
                    fontSize = 12;
                else if (tamanyo >= 10)
                    fontSize = 15;
                else
                    fontSize = 25;

                tabMapa.txtPais.setTextSize(TypedValue.COMPLEX_UNIT_SP, (fontSize));
                tabMapa.txtPais.setText(pais.getPaisES());
                String divisas[] = pais.getDivisas().split("-");

                String txtAuxDiv = "";

                if (pais.getDivisas() != null && !pais.getDivisas().isEmpty()) {
                    PaisesDivisasSQLite pd = new PaisesDivisasSQLite(context);


                    for (int i = 0; i < divisas.length; i++) {

                        //txtAuxDiv+=pd.getDivisa(divisas[i]).getDivisaES()+"\n";
                        txtAuxDiv += "\t" + divisas[i] + "\n";
                    }
                    tabMapa.txtMoneda.setText("Moneda/s:\n" + txtAuxDiv);

                } else {
                    tabMapa.txtMoneda.setText(context.getString(R.string.sinmoneda));

                }


            } else {
                tabMapa.posicionActual = tabMapa.map.addMarker(new MarkerOptions().position(pos).icon(tabMapa.marcadorColor));
                tabMapa.posicionActual.setTitle("Unnamed");
                tabMapa.posicionActual.setSnippet("(" + pos.latitude + "," + pos.longitude + ")");
                tabMapa.map.moveCamera(CameraUpdateFactory.newLatLngZoom(pos, 10));

                tabMapa.bandera.setImageDrawable(ContextCompat.getDrawable(tabMapa.getActivity(), R.drawable.no_icon));
                tabMapa.hidePlayer();
                tabMapa.path = "";
                tabMapa.txtPais.setText(context.getResources().getString(R.string.sinpais));
                tabMapa.txtMoneda.setText(context.getResources().getString(R.string.sinmoneda));
            }
        }


        @Override
        protected void onCancelled() {
            //salida.append("cancelado\n");
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
