package ar.com.astun.momapp.Vista;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

import ar.com.astun.momapp.Modelo.Paint;
import ar.com.astun.momapp.R;

public class AdaptadorMoma extends RecyclerView.Adapter<AdaptadorMoma.PaintHolder> {
    // Atributo
    private List<Paint> paintList;
    private FirebaseStorage firebaseStorage;

    // Constructor
    public AdaptadorMoma(List<Paint> amiiboList) {
        this.paintList = amiiboList;
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

    public class PaintHolder extends RecyclerView.ViewHolder {
        // ATRIBUTOS
        private TextView textViewName;
        private TextView textViewArtistId;
        private ImageView imageView;

        public PaintHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewArtistId = itemView.findViewById(R.id.textViewArtistId);
            imageView = itemView.findViewById(R.id.imageView);

            firebaseStorage = FirebaseStorage.getInstance();
        }

        public void cargar(Paint paint) {
            textViewName.setText(paint.getName());
            textViewArtistId.setText(paint.getArtistId());

            StorageReference imagenes = firebaseStorage.getReference();


            GlideApp.with(itemView.getContext())
                    .load(imagenes.child(paint.getImage()))
                    .into(imageView);

            //Glide.with(itemView.getContext()).load(imagenes.child(paint.getImage())).into(imageView);
            
        }
    }
}
