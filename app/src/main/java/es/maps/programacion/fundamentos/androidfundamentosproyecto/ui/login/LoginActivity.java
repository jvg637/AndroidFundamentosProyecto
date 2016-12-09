package es.maps.programacion.fundamentos.androidfundamentosproyecto.ui.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;

import es.maps.programacion.fundamentos.androidfundamentosproyecto.R;
import es.maps.programacion.fundamentos.androidfundamentosproyecto.MainActivity;
import es.maps.programacion.fundamentos.androidfundamentosproyecto.application.MapsApplication;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {
        // Choose an arbitrary request code value
        private static final int RC_SIGN_IN = 123;

        private FirebaseAuth auth;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);
            MapsApplication app = (MapsApplication) getApplicationContext();
            auth = app.getAuth();
            doLogin();
        }

        private void doLogin() {
            FirebaseUser currentUser = auth.getCurrentUser();

            if (currentUser != null) {
                String username = currentUser.getDisplayName();
                SharedPreferences prefs = getApplication().getSharedPreferences("ToDoPrefs", 0);
                prefs.edit().putString("username", username).commit();
                Intent i = new Intent(this, MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                        | Intent.FLAG_ACTIVITY_NEW_TASK
                        | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            } else {
                startActivityForResult(
                        AuthUI.getInstance()
                                .createSignInIntentBuilder()
                                .setProviders(Arrays.asList(new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build(),
                                        new AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build()
                                        /*,new AuthUI.IdpConfig.Builder(AuthUI.FACEBOOK_PROVIDER).build(),
                                        new AuthUI.IdpConfig.Builder(AuthUI.TWITTER_PROVIDER).build()*/
                                        )
                                )
                                .build(),

                        RC_SIGN_IN);
            }

        }

    @Override
    public void onActivityResult(int requestCode,
                                 int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            if (resultCode == RESULT_OK) {
                doLogin();
                finish();
            }
        }
    }
}
