package com.example.blasti;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.blasti.Model.Trajet;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.LinkedList;

public class AccueilChauffeur extends AppCompatActivity {
    EditText depart, arrivee, date, heure, prix;
    Button btnAjouter;

    FirebaseAuth auth;
    FirebaseFirestore firebaseFirestore;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil_chauffeur);

        depart = (EditText) findViewById(R.id.depart);
        arrivee = (EditText) findViewById(R.id.arrivee);
        date = (EditText) findViewById(R.id.dateDepart);
        heure = (EditText) findViewById(R.id.heureArrivée);
        prix = (EditText) findViewById(R.id.prix);
        btnAjouter = (Button) findViewById(R.id.buttonAjouter);

        btnAjouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ajouterTrajet();
            }
        });

        auth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();



    }

    private void ajouterTrajet() {
        String departText = this.depart.getText().toString().trim();
        String arriveeText = this.arrivee.getText().toString().trim();
        String dateText = this.date.getText().toString().trim();
        String heureText = this.heure.getText().toString().trim();
        String prixText = this.prix.getText().toString().trim();

        if (departText.isEmpty()) {
            this.depart.setError("Depart obligatoire");
            this.depart.requestFocus();
            return;
        }
        if (arriveeText.isEmpty()) {
            this.arrivee.setError("Arrivée obligatoire");
            this.arrivee.requestFocus();
            return;
        }
        if (dateText.isEmpty()) {
            this.date.setError("Date obligatoire");
            this.date.requestFocus();
            return;
        }
        if (heureText.isEmpty()) {
            this.heure.setError("Heure obligatoire");
            this.heure.requestFocus();
            return;
        }
        if (prixText.isEmpty()) {
            this.prix.setError("Prix obligatoire");
            this.prix.requestFocus();
            return;
        }

        String nomChauffeur = auth.getCurrentUser().getDisplayName();
        firebaseFirestore.collection("Trajets").add(new Trajet(departText, arriveeText, dateText, heureText, prixText , nomChauffeur)).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(AccueilChauffeur.this, "Trajet ajouté avec succées", Toast.LENGTH_SHORT).show();
               depart.setText("");
                arrivee.setText("");
                date.setText("");
                heure.setText("");
                prix.setText("");
            } else {
                Toast.makeText(AccueilChauffeur.this, "Erreur", Toast.LENGTH_SHORT).show();
            }
        });

    }
}