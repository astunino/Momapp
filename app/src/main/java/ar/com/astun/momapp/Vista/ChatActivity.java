package ar.com.astun.momapp.Vista;

import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import ar.com.astun.momapp.Controlador.ControladorPaint;
import ar.com.astun.momapp.Modelo.Chat;
import ar.com.astun.momapp.R;

public class ChatActivity extends AppCompatActivity {

    private List<Chat> datos = new ArrayList<>();
    private FirebaseDatabase mDatabase;
    private ArrayList<Chat> listadoChats =new ArrayList<>();
    private AdaptadorChat adaptadorChat;
    private RecyclerView recyclerView;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        adaptadorChat = new AdaptadorChat(datos);
        final ControladorPaint controllerPaint = new ControladorPaint();

        final EditText editTextChat = findViewById(R.id.editTextChat);
        editTextChat.setInputType(InputType.TYPE_NULL);
        FloatingActionButton fabEnviar = findViewById(R.id.fabEnviar);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        mDatabase = FirebaseDatabase.getInstance();
        final DatabaseReference chats = mDatabase.getReference("chats");

        editTextChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new Handler().postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        recyclerView.scrollToPosition(adaptadorChat.getItemCount() - 1);
                    }
                }, 400);

            }
        });

        chats.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                listadoChats = new ArrayList<>();

                for (DataSnapshot childSnapShot : dataSnapshot.getChildren()){

                    Chat chat = childSnapShot.getValue(Chat.class);
                    listadoChats.add(chat);

                }

                editTextChat.setInputType(InputType.TYPE_CLASS_TEXT);
                adaptadorChat = new AdaptadorChat(listadoChats);

                recyclerView.setAdapter(adaptadorChat);
                recyclerView.scrollToPosition(adaptadorChat.getItemCount() - 1);

            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        fabEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = currentUser.getDisplayName();
                String texto = editTextChat.getText().toString();

                chats.push().setValue(new Chat(nombre,texto));

                editTextChat.setText("");
            }
        });


        recyclerView = findViewById(R.id.recyclerViewChat);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adaptadorChat);
        recyclerView.scrollToPosition(adaptadorChat.getItemCount() - 1);

    }

}
