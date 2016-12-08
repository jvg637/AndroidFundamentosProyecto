package es.maps.programacion.fundamentos.es.androidfundamentosproyecto.ui;


import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.PictureDrawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.caverock.androidsvg.SVG;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import es.maps.programacion.fundamentos.androidfundamentosproyecto.R;
import es.maps.programacion.fundamentos.es.androidfundamentosproyecto.application.MapsApplication;

import static android.content.Context.LOCATION_SERVICE;

/**
 * Created by jvg63 on 21/09/2016.
 */
public class TabMapa extends Fragment implements OnMapReadyCallback, LocationListener, MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener {

    private static final long DOS_MINUTOS = 120 * 1000;
    public final int ESTADO_PLAY = 0;
    private final int ESTADO_STOP = 1;
    private final int ESTADO_PAUSE = 2;
    public final int ESTADO_NO_INICIADO = 3;
    private final int ESTADO_FINALIZADO = 4;

    private View layout;
    public GoogleMap map;
    private LocationManager manejador;
    private Location mejorLocaliz = null;

    private String telefono, mensaje;
    private int tipoNotificacion = 0;

    public Marker posicionActual = null;
    public BitmapDescriptor marcadorColor = null;

    private FragmentActivity myContext;
    private View rootView;

    private MapsApplication app;


    // Reproductor
    public MediaPlayer mediaPlayer;


    private ImageButton bPlay, bPause, bStop, bLog;
    private TextView logTextView;
    private boolean pause, stop;
    public String path = "";
    private int savePos = 0;
    public int estado;
    public ImageView bandera;
    public TextView txtPais;
    public TextView txtMoneda;

    public String paisActual = "";
    public String url = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.mapa_tab, container, false);

            manejador = (LocationManager) getActivity().getSystemService(LOCATION_SERVICE);

            layout = rootView.findViewById(R.id.layout);

            app = (MapsApplication) getContext().getApplicationContext();

            bandera = (ImageView) rootView.findViewById(R.id.bandera);
            bandera.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
            txtPais = (TextView) rootView.findViewById(R.id.pais);

            txtMoneda = (TextView) rootView.findViewById(R.id.divisas);

            //asignaImagen("vacio.svg", bandera);


            logTextView = (TextView) rootView.findViewById(R.id.Log);
            bPlay = (ImageButton) rootView.findViewById(R.id.play);
            bPlay.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    //path = "https://upload.wikimedia.org/wikipedia/commons/c/c8/Marcha_Real-Royal_March_by_US_Navy_Band.ogg";
                    if (mediaPlayer != null && (estado == ESTADO_PAUSE || estado == ESTADO_FINALIZADO)) {
                        mediaPlayer.start();
                        estado = ESTADO_PLAY;


                    } else {
                        if (estado == ESTADO_STOP || estado == ESTADO_NO_INICIADO || estado == ESTADO_FINALIZADO) {
                            playVideo();

                        }
                    }
                }
            });
            bPause = (ImageButton)

                    rootView.findViewById(R.id.pause);

            bPause.setOnClickListener(new View.OnClickListener()

                                      {
                                          public void onClick(View view) {
                                              if (mediaPlayer != null && estado == ESTADO_PLAY) {
                                                  estado = ESTADO_PAUSE;
                                                  mediaPlayer.pause();
                                                  savePos = mediaPlayer.getCurrentPosition();
                                              }

                                          }
                                      }

            );
            bStop = (ImageButton)

                    rootView.findViewById(R.id.stop);

            bStop.setOnClickListener(new View.OnClickListener()

                                     {
                                         public void onClick(View view) {
                                             if (mediaPlayer != null && (estado == ESTADO_PAUSE || estado == ESTADO_PLAY) || estado == ESTADO_FINALIZADO) {
                                                 savePos = 0;
                                                 estado = ESTADO_STOP;
                                                 //path="";
                                                 mediaPlayer.stop();
                                             }
                                         }
                                     }

            );

            showMessage("");

            estado = ESTADO_NO_INICIADO;

        }
        return rootView;

    }


    public void asignaImagen(String imagen, ImageView bandera) {
        // Load and parse a SVG
        SVG svg = null;
        Drawable drawable = null;
        try {
             svg = SVG.getFromAsset(rootView.getContext().getAssets(), "flags/" + imagen);
             drawable = new PictureDrawable(svg.renderToPicture());

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (drawable != null)
            bandera.setImageDrawable(drawable);
        else {
            bandera.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.no_icon));
        }
    }

    private void showMessage(String msg) {
        //Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
        Log.d("REPRODUCTOR", "Value is: " + msg);

        logTextView.setText(msg);
    }


    public void onBufferingUpdate(MediaPlayer arg0, int percent) {
        showMessage("Bufferening:" + percent);
    }

    public void onCompletion(MediaPlayer arg0) {
        showMessage("onCompletion called");
        estado = ESTADO_FINALIZADO;
        savePos = 0;
    }

    public void onPrepared(MediaPlayer mediaplayer) {
        showMessage("onPrepared called");

        logTextView.setVisibility(View.GONE);

        mediaPlayer.seekTo(savePos);
        if (estado != ESTADO_PAUSE && estado != ESTADO_FINALIZADO) {
            mediaPlayer.start();
            estado = ESTADO_PLAY;
        }

    }

    private void playVideo() {
        try {
            /*pause = false;
            stop = false;*/


            //path = editText.getText().toString();
            if (mediaPlayer != null) mediaPlayer.release();
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(path);
            //mediaPlayer.prepare();
            mediaPlayer.prepareAsync(); //Si streaming
            mediaPlayer.setOnBufferingUpdateListener(this);
            mediaPlayer.setOnCompletionListener(this);
            mediaPlayer.setOnPreparedListener(this);
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            //mediaPlayer.seekTo(savePos);
            Log.d("POSICION", "posicion: " + savePos);

            logTextView.setVisibility(View.VISIBLE);
        } catch (Exception e) {
            showMessage("ERROR: " + e.getMessage());
        }
    }


    @Override
    public void onMapReady(GoogleMap retMap) {

        map = retMap;
        map.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {

                if (url != null && !url.isEmpty()) {
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    getActivity().startActivity(i);
                }

            }
        });

        /*if (marcadorColor == null) {
            switch (tipoNotificacion) {
                // DESDE LLAMADA
                case TipoNotificaciones.ID_NOTIFICACION_ABRIR_MAPA:
                    marcadorColor = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN);
                    break;
                // DESDE SMS
                case TipoNotificaciones.ID_NOTIFICACION_CONTROL_REPRODUCTOR:
                    marcadorColor = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN);
                    break;
                // DESDE GPS-CIRCULO POLAR
                case TipoNotificaciones.ID_NOTIFICACION_GPS_CIRCULO_POLAR_ANTARTICO:
                    marcadorColor = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET);
                    break;
                default:
                    marcadorColor = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED);

            }

    }*/


        marcadorColor = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED);

        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location posicion1 = manejador.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        Location posicion2 = manejador.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

        LatLng pos = null;
        if (posicion1 != null && posicion2 == null)
            pos = new LatLng(posicion1.getLatitude(), posicion1.getLongitude());
        else if (posicion1 == null && posicion2 != null)
            pos = new LatLng(posicion2.getLatitude(), posicion2.getLongitude());
        else if (posicion1 != null && posicion2 != null)
            if (posicion1.getAccuracy() < posicion2.getAccuracy() || posicion1.getTime() > posicion2.getTime())
                pos = new LatLng(posicion1.getLatitude(), posicion1.getLongitude());
            else pos = new LatLng(posicion2.getLatitude(), posicion2.getLongitude());
        if (pos != null) {

            datosPais(pos);
        }
        setUpMap();
    }

    private void datosPais(LatLng pos) {


        app.getModuloPais().getPais(pos, this);


    }

    public void showPlayer() {
        bPause.setVisibility(View.VISIBLE);
        bPlay.setVisibility(View.VISIBLE);
        bStop.setVisibility(View.VISIBLE);
    }

    public void hidePlayer() {
        bPause.setVisibility(View.GONE);
        bPlay.setVisibility(View.GONE);
        bStop.setVisibility(View.GONE);
    }

    public void setUpMap() {

        map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        map.getUiSettings().setCompassEnabled(true);
        map.getUiSettings().setZoomControlsEnabled(true);
        map.getUiSettings().setCompassEnabled(true);
        map.getUiSettings().setZoomGesturesEnabled(true);

        /*if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        map.setMyLocationEnabled(true);*/
    }

    @Override
    public void onResume() {
        super.onResume();

        if (mediaPlayer != null) {
            if (estado == ESTADO_PLAY)
                mediaPlayer.start();
        } else

        {
            if (estado == ESTADO_PLAY || estado == ESTADO_PAUSE || estado == ESTADO_FINALIZADO) {
                playVideo();

            }
        }

    }

    @Override
    public void onPause() {

        if (mediaPlayer != null && (estado == ESTADO_PLAY || estado == ESTADO_PAUSE)) {

            if (estado == ESTADO_PLAY) {
                savePos = mediaPlayer.getCurrentPosition();
                mediaPlayer.pause();
            }
        } else
            savePos = 0;

        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();

        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        manejador.removeUpdates(this);
    }


    @Override
    public void onSaveInstanceState(Bundle guardarEstado) {
        super.onSaveInstanceState(guardarEstado);
        if (mediaPlayer != null) {
            //int pos = mediaPlayer.getCurrentPosition();
            guardarEstado.putString("ruta", path);
            guardarEstado.putInt("posicion", savePos);
            guardarEstado.putInt("estado", estado);
            guardarEstado.putString("pais", paisActual);

        }
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle recEstado) {

        Log.d("RESTORE", "ONRESTORE");
        super.onViewStateRestored(recEstado);
        if (recEstado != null) {
            path = recEstado.getString("ruta", "");
            savePos = recEstado.getInt("posicion", 0);
            estado = recEstado.getInt("estado", ESTADO_NO_INICIADO);
            paisActual = recEstado.getString("pais","");

            Log.d("RESTORE", "VALORES_RECUPERADOS" + path + " " + savePos + " " + paisActual + " " + estado);

        }
    }


    // Validación de permisos
    private static final int SOLICITUD_PERMISO_GPS = 0;


    private void verificarPermisosGPS() {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                ) {
            registraSensoresGPS_RED();
            // Snackbar.make(layout, "PERMISOS GPS OK", Snackbar.LENGTH_SHORT).show();
        } else {
            solicitarPermisosGPS();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        if (requestCode == SOLICITUD_PERMISO_GPS) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Snackbar.make(layout, "PERMISOS GPS OK", Snackbar.LENGTH_SHORT).show();

                registraSensoresGPS_RED();
            }

        }
    }

    void solicitarPermisosGPS() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)) {
            Snackbar.make(layout, getResources().getString(R.string.permiso_posicionamiento_info), Snackbar.LENGTH_INDEFINITE).setAction(R.string.permiso_OK, new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, SOLICITUD_PERMISO_GPS);
                    //   ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.RECEIVE_SMS}, SOLICITUD_PERMISO_GPS);

                }
            }).show();
        } else {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, SOLICITUD_PERMISO_GPS);
        }

    }


    @Override
    public void onLocationChanged(Location localiz) {
        if (mejorLocaliz == null || localiz.getAccuracy() < 2 * mejorLocaliz.getAccuracy() || localiz.getTime() - mejorLocaliz.getTime() > DOS_MINUTOS) {
            mejorLocaliz = localiz;


            if (map != null) {
                LatLng posicion = new LatLng(mejorLocaliz.getLatitude(), localiz.getLongitude());



                //Log.d("posicionActual=", posicionActual.toString());

                ;

                /*if (tipoNotificacion == TipoNotificaciones.ID_NOTIFICACION_CONTROL_REPRODUCTOR && telefono != null && mensaje != null) {
                    posicionActual.setTitle(mensaje);

                } else if (tipoNotificacion == TipoNotificaciones.ID_NOTIFICACION_ABRIR_MAPA && telefono != null) {
                    posicionActual.setTitle(telefono);
                } else if (tipoNotificacion == TipoNotificaciones.ID_NOTIFICACION_GPS_CIRCULO_POLAR_ANTARTICO) {
                    posicionActual.setTitle("Circulo Polar Artico");
                } else {

                    posicionActual.setTitle("Actividad Principal");

                }*/


                datosPais(posicion);

            } else {
                Log.e("NO HAY MAPA", "NO HAY MAPA");

            }
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onStart() {
        super.onStart();

        if (!manejador.isProviderEnabled(LocationManager.GPS_PROVIDER) && !manejador.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            mostrarAlertaConfiguracion();
        }

        verificarPermisosGPS();
        //verificarPermisosSMS();

    }

    private void registraSensoresGPS_RED() {
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        if (manejador.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            manejador.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5 * 1000, 50, this);
        }
        if (manejador.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            manejador.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5 * 1000, 100, this);
        }

        if (map == null) {
            // Obtain the SupportMapFragment and get notified when the map is ready to be used.
            SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        SupportMapFragment f = (SupportMapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        if (f != null)
            getFragmentManager().beginTransaction().remove(f).commit();

        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    public void mostrarAlertaConfiguracion() {
        new AlertDialog.Builder(getContext()).setTitle("Configuración de localización").setMessage("Ningún proveedor de localización activo. ¿Quiere…").setPositiveButton("Configuración", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int boton) {
                Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(i);
            }
        }).setNegativeButton("Cancelar", null).show();
    }


}