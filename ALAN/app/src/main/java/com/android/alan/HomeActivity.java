package com.android.alan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    //
    ConstraintLayout constraintLayout_home_menu;
    Button button_home_chats, button_home_movies, button_home_sign_out;
    ImageButton imageButton_home_analytics, imageButton_home_menu;
    RecyclerView recyclerView_home;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Details> detailsArrayList;
    HomeAdapter homeAdapter;
    RequestQueue requestQueue;

    //
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private FirebaseFirestore firebaseFirestore;

    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        //
        requestQueue = Volley.newRequestQueue(HomeActivity.this);
        //
        constraintLayout_home_menu = findViewById(R.id.constraintLayout_home_menu);
        constraintLayout_home_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //
                constraintLayout_home_menu.setVisibility(View.GONE);
            }
        });
        button_home_chats = findViewById(R.id.button_home_chats);
        button_home_chats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //
                startActivity(new Intent(HomeActivity.this, ChatsActivity.class).putExtra("Name", firebaseUser.getEmail()));
            }
        });
        button_home_movies = findViewById(R.id.button_home_movies);
        button_home_movies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //
                startActivity(new Intent(HomeActivity.this, MoviesActivity.class));
            }
        });
        button_home_sign_out = findViewById(R.id.button_home_sign_out);
        button_home_sign_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //
                firebaseAuth.signOut();
                recreate();
            }
        });
        imageButton_home_analytics = findViewById(R.id.imageButton_home_analytics);
        imageButton_home_analytics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //
                startActivity(new Intent(HomeActivity.this, AnalyticsActivity.class));
            }
        });
        imageButton_home_menu = findViewById(R.id.imageButton_home_menu);
        imageButton_home_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //
                constraintLayout_home_menu.setVisibility(View.VISIBLE);
            }
        });
        recyclerView_home = findViewById(R.id.recyclerView_home);
        recyclerView_home.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(HomeActivity.this);
        recyclerView_home.setLayoutManager(layoutManager);
        detailsArrayList = new ArrayList<>();
        //
        firebaseFirestore.collection("Details").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                //
                if (task.isSuccessful()) {
                    //
                    for (QueryDocumentSnapshot queryDocumentSnapshot : task.getResult()) {
                        //
                        detailsArrayList.add(queryDocumentSnapshot.toObject(Details.class));
                    }
                    homeAdapter = new HomeAdapter(detailsArrayList, requestQueue);
                    recyclerView_home.setAdapter(homeAdapter);
                } else {
                    //
                }
            }
        });
        //
    }

    @Override
    protected void onStart() {
        super.onStart();
        //
        firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser == null) {
            //
            startActivity(new Intent(HomeActivity.this, SignInActivity.class));
        }
    }
}
