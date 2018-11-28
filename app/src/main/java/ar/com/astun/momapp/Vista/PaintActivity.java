package ar.com.astun.momapp.Vista;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

import ar.com.astun.momapp.Controlador.ControladorPaint;
import ar.com.astun.momapp.Modelo.Paint;
import ar.com.astun.momapp.R;
import ar.com.astun.momapp.Util.ResultListener;


public class PaintActivity extends AppCompatActivity implements AdaptadorMoma.AdapterListener{

    private List<Paint> datos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paint);

        final AdaptadorMoma adaptadorPaint = new AdaptadorMoma(this,datos);
        final ControladorPaint controllerPaint = new ControladorPaint();

        TextView textViewUser = findViewById(R.id.textViewUser);

        controllerPaint.traerPaints(this, new ResultListener<List<Paint>>() {
            @Override
            public void finish(List<Paint> resultado) {
                // DATOS
                datos = resultado;
                adaptadorPaint.setPaintList(datos);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adaptadorPaint);

        textViewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PaintActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void irDetalle(String nombre, String artistId,String imagen) {

        Intent intent = new Intent(PaintActivity.this, PaintDetalleActivity.class);

        Bundle bundle = new Bundle();
        bundle.putString(PaintDetalleActivity.KEY_ARTISTID, artistId);
        bundle.putString(PaintDetalleActivity.KEY_NAME, nombre);
        bundle.putString(PaintDetalleActivity.KEY_IMAGEN, imagen);
        // CHECK IN
        intent.putExtras(bundle);

        startActivity(intent);
    }
}
