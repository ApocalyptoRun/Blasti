package com.example.blasti;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AccueilInvite extends AppCompatActivity {

    Button btnSeConnecter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil_invite);

        btnSeConnecter = (Button) findViewById(R.id.buttonSeConnecter);

        btnSeConnecter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AccueilInvite.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }
}