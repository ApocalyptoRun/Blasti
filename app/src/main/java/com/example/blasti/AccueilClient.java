package com.example.blasti;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.HashMap;

public class AccueilClient extends AppCompatActivity {
    //private final LinkedList<String> mTrajetList = new LinkedList<>();
    private Object[] mTrajetList;
    private RecyclerView mRecyclerView;
    private TrajetListAdapter mAdapter;

    private TextView AccueilClientHeader;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil_client);
        AccueilClientHeader = findViewById(R.id.AccueilClientHeader);

        getTrajet();


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }

    private void getTrajet() {
        db.collection("Trajets").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                mTrajetList = new Object[task.getResult().size()];
                int i = 0;
                for (QueryDocumentSnapshot document : task.getResult()) {

                    Object trajet = document.getData();
                    mTrajetList[i] = new HashMap<String, String>();
                    mTrajetList[i] = trajet;
                    i++;
                    AccueilClientHeader.setText("Liste des trajets ");


                }
                mRecyclerView = findViewById(R.id.recyclerViewTrajets);
                mAdapter = new TrajetListAdapter(this, mTrajetList);
                mRecyclerView.setAdapter(mAdapter);
                LinearLayoutManager layoutManager = new LinearLayoutManager(this);
                mRecyclerView.setLayoutManager(layoutManager);

            } else {
                AccueilClientHeader.setText("Il n'y a pas des trajets pour le moment ");
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_accueil, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.actualiser) {
            getTrajet();
            return true;

        } else if (id == R.id.logout) {
            mAuth.signOut();
            Intent intent = new Intent(AccueilClient.this, MainActivity.class);
            startActivity(intent);
            return true;

        }

        return super.onOptionsItemSelected(item);
    }
}