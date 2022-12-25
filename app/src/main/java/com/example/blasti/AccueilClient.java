package com.example.blasti;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil_client);
        AccueilClientHeader = findViewById(R.id.AccueilClientHeader);

        db.collection("Trajets").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                mTrajetList = new Object[task.getResult().size()];
                int i = 0;
                for (QueryDocumentSnapshot document : task.getResult()) {

                    Object trajet = document.getData();
                    System.out.println("rajet"+trajet);
                    mTrajetList[i] = new HashMap<String, String>();
                    mTrajetList[i] = trajet;
                    System.out.println("trajetlist" +mTrajetList[i]);
                    i++;
                    AccueilClientHeader.setText("Liste des offres " );



                }
                mRecyclerView = findViewById(R.id.recyclerViewTrajets);
                mAdapter = new TrajetListAdapter(this, mTrajetList);
                mRecyclerView.setAdapter(mAdapter);
                LinearLayoutManager layoutManager = new LinearLayoutManager(this);
                mRecyclerView.setLayoutManager(layoutManager);

            }
            else {
                AccueilClientHeader.setText("Il n'y a pas des offres pour le moment " );
            }
        });


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);






    }
}