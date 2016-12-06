package es.maps.programacion.fundamentos.es.androidfundamentosproyecto.ui;


import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import es.maps.programacion.fundamentos.androidfundamentosproyecto.R;
import es.maps.programacion.fundamentos.es.androidfundamentosproyecto.application.MapsApplication;
import es.maps.programacion.fundamentos.es.androidfundamentosproyecto.sqlite.pojo.Pais;

import static android.content.Context.LOCATION_SERVICE;

/**
 * Created by jvg63 on 21/09/2016.
 */
public class TabMapa extends Fragment implements  OnMapReadyCallback, LocationListener {

    private static final long DOS_MINUTOS = 120 * 1000;
    private View layout;
    private GoogleMap map;
    private LocationManager manejador;
    private Location mejorLocaliz = null;

    private String telefono, mensaje;
    private int tipoNotificacion = 0;

    private Marker posicionActual = null;
    private BitmapDescriptor marcadorColor = null;

    private FragmentActivity myContext;
    private View rootView;

    private MapsApplication app;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)  {
            if(rootView==null){
                rootView = inflater.inflate(R.layout.mapa_tab, container, false);

                manejador = (LocationManager) getActivity().getSystemService(LOCATION_SERVICE);

                layout=rootView.findViewById(R.id.layout);

                app =(MapsApplication) getContext().getApplicationContext();

            }
            return rootView;

        }

    @Override
    public void onMapReady(GoogleMap retMap) {

        map = retMap;

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

        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)

        {
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

        {
            pos = new LatLng(posicion1.getLatitude(), posicion1.getLongitude());
        } else if (posicion1 == null && posicion2 != null)

        {
            pos = new LatLng(posicion2.getLatitude(), posicion2.getLongitude());

        } else

        {

            if (posicion1 != null && posicion2 != null) {

                if (posicion1.getAccuracy() < posicion2.getAccuracy() || posicion1.getTime() > posicion2.getTime()) {
                    pos = new LatLng(posicion1.getLatitude(), posicion1.getLongitude());

                } else {
                    pos = new LatLng(posicion2.getLatitude(), posicion2.getLongitude());
                }
            }
        }

        if (pos != null)

        {

            posicionActual = map.addMarker(new MarkerOptions().position(pos).icon(marcadorColor));


        /*if (tipoNotificacion == TipoNotificaciones.ID_NOTIFICACION_CONTROL_REPRODUCTOR && telefono != null && mensaje != null) {
            posicionActual.setTitle(mensaje);

        } else if (tipoNotificacion == TipoNotificaciones.ID_NOTIFICACION_ABRIR_MAPA && telefono != null) {
            posicionActual.setTitle(telefono);
        } else if (tipoNotificacion == TipoNotificaciones.ID_NOTIFICACION_GPS_CIRCULO_POLAR_ANTARTICO) {
            posicionActual.setTitle("Circulo Polar Artico");
        } else {

            posicionActual.setTitle("Actividad Principal");

        }*/
            posicionActual.setTitle("Actividad Principal");

            Pais pais = app.getModuloPais().leerJSon( pos );

            posicionActual.setSnippet("(" + pos.latitude + "," + pos.longitude + ")" + (pais!=null?"-"+pais.getPaisES():""));

            map.moveCamera(CameraUpdateFactory.newLatLngZoom(pos, 10));

        }

        setUpMap();

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
            requestPermissions( new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, SOLICITUD_PERMISO_GPS);
        }

    }


    @Override
    public void onLocationChanged(Location localiz) {
        if (mejorLocaliz == null || localiz.getAccuracy() < 2 * mejorLocaliz.getAccuracy() || localiz.getTime() - mejorLocaliz.getTime() > DOS_MINUTOS) {
            mejorLocaliz = localiz;


            if (map != null) {
                LatLng posicion = new LatLng(mejorLocaliz.getLatitude(), localiz.getLongitude());

                if (posicionActual != null) {
                    posicionActual.remove();
                }

                //Log.d("posicionActual=", posicionActual.toString());

                posicionActual = map.addMarker(new MarkerOptions().position(posicion).icon(marcadorColor));

                /*if (tipoNotificacion == TipoNotificaciones.ID_NOTIFICACION_CONTROL_REPRODUCTOR && telefono != null && mensaje != null) {
                    posicionActual.setTitle(mensaje);

                } else if (tipoNotificacion == TipoNotificaciones.ID_NOTIFICACION_ABRIR_MAPA && telefono != null) {
                    posicionActual.setTitle(telefono);
                } else if (tipoNotificacion == TipoNotificaciones.ID_NOTIFICACION_GPS_CIRCULO_POLAR_ANTARTICO) {
                    posicionActual.setTitle("Circulo Polar Artico");
                } else {

                    posicionActual.setTitle("Actividad Principal");

                }*/
                posicionActual.setTitle("Actividad Principal");


                posicionActual.setSnippet("(" + posicion.latitude + "," + posicion.longitude + ")");

                map.moveCamera(CameraUpdateFactory.newLatLngZoom(posicion, 10));


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
            manejador.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5 * 1000, 5, this);
        }
        if (manejador.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            manejador.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1 * 1000, 10, this);
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