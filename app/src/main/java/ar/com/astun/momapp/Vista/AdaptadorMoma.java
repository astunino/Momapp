package ar.com.astun.momapp.Vista;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import ar.com.astun.momapp.Modelo.Artist;
import ar.com.astun.momapp.Modelo.Paint;
import ar.com.astun.momapp.R;
import ar.com.astun.momapp.Util.GlideApp;

public class AdaptadorMoma extends RecyclerView.Adapter<AdaptadorMoma.PaintHolder> {
    // Atributo
    private List<Paint> paintList;
    private AdapterListener listener;

    // Constructor
    public AdaptadorMoma(AdapterListener listener, List<Paint> paintList) {
        this.paintList = paintList;
        this.listener = listener;
    }


    public void setPaintList(List<Paint> paintList) {
        this.paintList = paintList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PaintHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_layout, viewGroup, false);
        PaintHolder paintHolder = new PaintHolder(view);

        return paintHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PaintHolder paintHolder, int i) {
        Paint paint = paintList.get(i);
        paintHolder.cargar(paint);
    }

    @Override
    public int getItemCount() {
        return paintList.size();
    }

    public interface AdapterListener{
        void irDetalle(String nombre,String artistId,String imagen);
    }

    public class PaintHolder extends RecyclerView.ViewHolder {
        // ATRIBUTOS
        private TextView textViewName;
        private ImageView imageView;
        private FirebaseStorage firebaseStorage;

        public PaintHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            imageView = itemView.findViewById(R.id.imageView);

            firebaseStorage = FirebaseStorage.getInstance();

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.irDetalle(paintList.get(getAdapterPosition()).getName(),paintList.get(getAdapterPosition()).getArtistId(),paintList.get(getAdapterPosition()).getImage());
                }
            });
        }

        public void cargar(final Paint paint) {
            textViewName.setText(paint.getName());

            StorageReference imagenes = firebaseStorage.getReference();

            GlideApp.with(itemView.getContext())
                   .load(imagenes.child(paint.getImage()))
                    .into(imageView);

        }
    }

}
