package com.example.blasti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class Signupchoix extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signupchoix);
    }

    public void chauffeurSignUp(View view) {
        Intent inscritChauff=new Intent(Signupchoix.this,InscriptionChauffeur.class);
        startActivity(inscritChauff);

    }

    public void clientSignUp(View view) {
        Intent inscritClient=new Intent(Signupchoix.this,InscriptionClient.class);
        startActivity(inscritClient);
    }
}