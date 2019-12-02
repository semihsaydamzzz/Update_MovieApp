package com.semihsaydam.movieapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

import static com.semihsaydam.movieapp.R.menu.movie_options_menu;

public class MoviesActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;
    ArrayList<String> userEmailFromFB;
    ArrayList<String> userMovieFromFB;
    ArrayList<String> userImageFromFB;
    MoviesRecyclerAdapter moviesRecyclerAdapter;


    @Override
    public boolean onCreateOptionsMenu(Menu menu){ /////________Connect Menu and MoviesActivity.java
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.movie_options_menu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) { ////_____If select something in Menu
        if(item.getItemId() == R.id.add_movie){ /////_____if click Add Movie menu item
            Intent intentToUpload = new Intent(MoviesActivity.this,UploadActivity.class);////____Go to Upload Activity
            startActivity(intentToUpload);
            overridePendingTransition(R.anim.anim_in, R.anim.anim_out);
            finish();

        } else if(item.getItemId()==R.id.signout){////_____if click Sign Out menu item
            firebaseAuth.signOut(); /// SignOut on Firebase

            Intent intentToLogIn = new Intent(MoviesActivity.this,LogInActivity.class);
            startActivity(intentToLogIn);
            overridePendingTransition(R.anim.anim_in, R.anim.anim_out);
            finish();

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);

        userMovieFromFB = new ArrayList<>();
        userEmailFromFB = new ArrayList<>();
        userImageFromFB = new ArrayList<>();

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        getDataFromFireStore();
        ///Recycler View
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        moviesRecyclerAdapter = new MoviesRecyclerAdapter(userEmailFromFB,userMovieFromFB,userImageFromFB);
        recyclerView.setAdapter(moviesRecyclerAdapter);
        ////_____________we sent inputs arrays to MoviesRecyclerAdapter______________________________

    }

    public void getDataFromFireStore(){
        CollectionReference collectionReference = firebaseFirestore.collection("Movies");

        ///whereEqualTo using for filter and _____orderBy using for aligning and descending
        collectionReference.orderBy("date", Query.Direction.DESCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                if(e != null){
                    Toast.makeText(MoviesActivity.this,e.getLocalizedMessage().toString(),Toast.LENGTH_LONG).show();
                }

                if(queryDocumentSnapshots != null) {

                    for(DocumentSnapshot snapshot : queryDocumentSnapshots.getDocuments()){
                        Map<String,Object> data = snapshot.getData();
                        String movie = (String) data.get("movie");
                        String userEmail = (String) data.get("useremail");
                        String downloadUrl = (String) data.get("downloadurl");  ////Get the HashMap datas
                        userMovieFromFB.add(movie);
                        userEmailFromFB.add(userEmail);
                        userImageFromFB.add(downloadUrl);

                        moviesRecyclerAdapter.notifyDataSetChanged();
                    }

                }

            }
        });

    }
}
