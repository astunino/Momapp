package ar.com.astun.momapp.Vista;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import ar.com.astun.momapp.R;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView textViewNombrre = findViewById(R.id.textViewNombre);
        ImageView imageViewFoto = findViewById(R.id.imageViewFoto);
        ImageButton imageButton =findViewById(R.id.imageButtonCerrarSesion);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        textViewNombrre.setText(currentUser.getDisplayName());

        Profile profile = Profile.getCurrentProfile();

        Uri uri = currentUser.getPhotoUrl();
        Glide.with(this).load(uri).into(imageViewFoto);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                LoginManager.getInstance().logOut();

                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }


}
