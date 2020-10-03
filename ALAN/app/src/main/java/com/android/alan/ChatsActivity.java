package com.android.alan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ChatsActivity extends AppCompatActivity {

    //
    ImageButton imageButton_chats_back, imageButton_chats_ok;
    RecyclerView recyclerView_chats;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Chats> chatsArrayList;
    ChatsAdapter chatsAdapter;
    String name;
    EditText editText_chats_chats;

    //
    private DatabaseReference databaseReference;

    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chats);
        //
        databaseReference = FirebaseDatabase.getInstance().getReference();
        //
        name = getIntent().getStringExtra("Name");
        //
        imageButton_chats_back = findViewById(R.id.imageButton_chats_back);
        imageButton_chats_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //
                finish();
            }
        });
        recyclerView_chats = findViewById(R.id.recyclerView_chats);
        recyclerView_chats.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(ChatsActivity.this);
        recyclerView_chats.setLayoutManager(layoutManager);
        chatsArrayList = new ArrayList<>();
        //
        databaseReference.child("Chats").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //
                for (DataSnapshot dataSnapshot_: dataSnapshot.getChildren()) {
                    //
                    chatsArrayList.add(dataSnapshot_.getValue(Chats.class));
                }
                chatsAdapter = new ChatsAdapter(chatsArrayList);
                recyclerView_chats.setAdapter(chatsAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //
            }
        });
        //
        editText_chats_chats = findViewById(R.id.editText_Chats_chats);
        imageButton_chats_ok = findViewById(R.id.imageButton_chats_ok);
        imageButton_chats_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //
                databaseReference.child("Chats").push().setValue(new Chats(name, editText_chats_chats.getText().toString()));
            }
        });
    }
}
