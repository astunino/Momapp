package ar.com.astun.momapp.Vista;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

import ar.com.astun.momapp.Modelo.Chat;
import ar.com.astun.momapp.R;

public class AdaptadorChat extends RecyclerView.Adapter<AdaptadorChat.ChatHolder>  {
    // Atributo
    private List<Chat> chatList;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;


    // Constructor
    public AdaptadorChat(List<Chat> chatList) {
        this.chatList = chatList;
    }

    public void setChatList(List<Chat> chatList) {
        this.chatList = chatList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ChatHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_layout_chat, viewGroup, false);
        ChatHolder chatHolder = new ChatHolder(view);

        return chatHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ChatHolder chatHolder, int i) {
        Chat chat = chatList.get(i);
        chatHolder.cargar(chat);
    }

    @Override
    public int getItemCount() {
        return chatList.size();
    }

    public class ChatHolder extends RecyclerView.ViewHolder {
        // ATRIBUTOS
        private TextView textViewNombre;
        private TextView textViewChat;
        private LinearLayout linearLayoutChat;
        private CardView cardViewChat;


        public ChatHolder(@NonNull View itemView) {
            super(itemView);
            textViewNombre = itemView.findViewById(R.id.textViewNombre);
            textViewChat = itemView.findViewById(R.id.textViewChat);
            linearLayoutChat = itemView.findViewById(R.id.linearLayoutChat);
            cardViewChat = itemView.findViewById(R.id.cardViewChat);

            mAuth = FirebaseAuth.getInstance();
            currentUser = mAuth.getCurrentUser();


        }

        public void cargar(final Chat chat) {
            textViewNombre.setText(chat.getNombre());
            textViewChat.setText(chat.getChat());

            if (currentUser.getDisplayName().equals(chat.getNombre())){

                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        RecyclerView.LayoutParams.WRAP_CONTENT, RecyclerView.LayoutParams.WRAP_CONTENT);
                params.gravity = Gravity.END;
                linearLayoutChat.setLayoutParams(params);
                cardViewChat.setCardBackgroundColor(Color.parseColor("#A9F5BC"));

            }else{


                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        RecyclerView.LayoutParams.WRAP_CONTENT, RecyclerView.LayoutParams.WRAP_CONTENT);
                params.gravity = Gravity.START;
                linearLayoutChat.setLayoutParams(params);
                cardViewChat.setCardBackgroundColor(Color.parseColor("#CED8F6"));
            }
        }
    }

}
