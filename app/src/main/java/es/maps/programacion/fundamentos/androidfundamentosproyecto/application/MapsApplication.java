package es.maps.programacion.fundamentos.androidfundamentosproyecto.application;

import android.app.Application;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import es.maps.programacion.fundamentos.androidfundamentosproyecto.lib.paises.LatLngToPais;

/**
 * Created by jvg63 on 03/12/2016.
 */

public class MapsApplication extends Application {
    private String ITEMS_CHILD_NAME = "items";
    private DatabaseReference itemsReference;
    private LatLngToPais moduloPais;


    private FirebaseAuth auth;

    public LatLngToPais getModuloPais() {
        return moduloPais;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        database.setPersistenceEnabled(true);
        itemsReference = database.getReference(ITEMS_CHILD_NAME);
        auth = FirebaseAuth.getInstance();
        moduloPais = new LatLngToPais(this);

    }

    public DatabaseReference getItemsReference() {
        return itemsReference;
    }

    public FirebaseAuth getAuth() {
        return auth;
    }

}
