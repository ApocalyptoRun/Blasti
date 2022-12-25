package com.example.blasti;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Confirmer extends AppCompatActivity {
    TextView rolezone;
    Button buttonContinuer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmer);
        Intent intent = getIntent();
        String role = intent.getStringExtra("role");
        String nom = intent.getStringExtra("nom");
        String prenom = intent.getStringExtra("prenom");
        rolezone = (TextView) findViewById(R.id.rolezone);
        rolezone.setText("Notre chers " + role + " " + nom + " " + prenom + " vous êtes bien connecter");

        buttonContinuer = (Button) findViewById(R.id.buttonContinuer);
        buttonContinuer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (role.equals("CLIENT")) {
                    Intent intent = new Intent(Confirmer.this, AccueilClient.class);
                    startActivity(intent);
                }
                 else if (role.equals("CHAUFFEUR")) {
                    Intent intent = new Intent(Confirmer.this, AccueilChauffeur.class);
                    startActivity(intent);
                }
            }
        });

    }

}