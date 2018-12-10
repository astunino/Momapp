package ar.com.astun.momapp.Vista;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

import ar.com.astun.momapp.DAO.DaoDBArtist;
import ar.com.astun.momapp.DAO.DaoDBPaint;
import ar.com.astun.momapp.DB.DatabaseHelper;
import ar.com.astun.momapp.Modelo.Artist;
import ar.com.astun.momapp.Modelo.Paint;
import ar.com.astun.momapp.R;
import ar.com.astun.momapp.Util.GlideApp;
import ar.com.astun.momapp.Util.Util;

public class PaintDetalleActivity extends AppCompatActivity {

    public static final String KEY_NAME = "name";
    public static final String KEY_ARTISTID = "artistid";
    public static final String KEY_IMAGEN = "imagen";

    private TextView textViewName,textViewArtistName,textViewInfluecedBy,textViewArtistNationality;
    private ImageView imageViewImagen;
    private FirebaseStorage firebaseStorage;
    private FirebaseDatabase mDatabase;
    private String artistid;
    private ArrayList<Artist> listadoArtists =new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paint_detalle);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        String name = bundle.getString(KEY_NAME);
        artistid = bundle.getString(KEY_ARTISTID);
        String imagen = bundle.getString(KEY_IMAGEN);

        textViewName = findViewById(R.id.textViewName);
        textViewArtistName = findViewById(R.id.textViewArtistName);
        textViewArtistNationality = findViewById(R.id.textViewArtistNationality);
        textViewInfluecedBy = findViewById(R.id.textViewInfluecedBy);
        imageViewImagen = findViewById(R.id.imageViewImagen);

        firebaseStorage = FirebaseStorage.getInstance();

        StorageReference imagenes = firebaseStorage.getReference();

        if(Util.isOnline(this)){

            mDatabase = FirebaseDatabase.getInstance();
            DatabaseReference artists = mDatabase.getReference("artists");

            artists.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot childSnapShot : dataSnapshot.getChildren()){

                        Artist artist = childSnapShot.getValue(Artist.class);
                        listadoArtists.add(artist);

                    }

                    DaoDBArtist daoDBArtist = DatabaseHelper.
                            getInstance(getApplication())
                            .daoDBArtist();

                    daoDBArtist.insertarArtists(listadoArtists);
                    cargarArtist(listadoArtists);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else{

            DaoDBArtist daoDBArtist = DatabaseHelper.
                    getInstance(getApplication())
                    .daoDBArtist();

            List<Artist> artists=daoDBArtist.getArtists();
            cargarArtist(artists);

        }

        GlideApp.with(getApplicationContext())
                .load(imagenes.child(imagen))
                .into(imageViewImagen);

        textViewName.setText(name);

    }

    public void cargarArtist(List<Artist> artists){
        for(int i=0;i<listadoArtists.size();i++){
            if(listadoArtists.get(i).getArtistId().equals(artistid)){
                textViewArtistName.setText(listadoArtists.get(i).getName());
                textViewInfluecedBy.setText(listadoArtists.get(i).getInfluencedBy());
                textViewArtistNationality.setText(listadoArtists.get(i).getNationality());
            }
        }
    }
}
