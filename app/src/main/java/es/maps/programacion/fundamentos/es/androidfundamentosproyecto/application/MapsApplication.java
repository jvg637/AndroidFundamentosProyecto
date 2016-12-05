package es.maps.programacion.fundamentos.es.androidfundamentosproyecto.application;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by jvg63 on 03/12/2016.
 */

public class MapsApplication extends android.app.Application {
    private String ITEMS_CHILD_NAME = "items";
    private DatabaseReference itemsReference;

    public FirebaseAuth getAuth() {
        return auth;
    }

    private FirebaseAuth auth;

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        database.setPersistenceEnabled(true);
        itemsReference = database.getReference(ITEMS_CHILD_NAME);
        auth = FirebaseAuth.getInstance();

    }

    public DatabaseReference getItemsReference() {
        return itemsReference;
    }
}
