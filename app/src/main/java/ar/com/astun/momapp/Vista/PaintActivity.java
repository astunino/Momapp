package ar.com.astun.momapp.Vista;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

import ar.com.astun.momapp.Controlador.ControladorPaint;
import ar.com.astun.momapp.Modelo.Paint;
import ar.com.astun.momapp.R;
import ar.com.astun.momapp.Util.ResultListener;


public class PaintActivity extends AppCompatActivity {

    private List<Paint> datos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paint);

        final AdaptadorMoma adaptadorPaint = new AdaptadorMoma(datos);
        final ControladorPaint controllerPaint = new ControladorPaint();


        controllerPaint.traerPaints(this, new ResultListener<List<Paint>>() {
            @Override
            public void finish(List<Paint> resultado) {
                // DATOS
                datos = resultado;
                adaptadorPaint.setPaintList(datos);
            }
        });

        FloatingActionButton fab = findViewById(R.id.fabPaint);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                LoginManager.getInstance().logOut();

                Intent intent = new Intent(PaintActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adaptadorPaint);
    }
}
