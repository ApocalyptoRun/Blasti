package com.example.blasti;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void choixMode(View view) {
        Intent inscrit=new Intent(MainActivity.this, Signupchoix.class);
        startActivity(inscrit);
    }


    public void signIn(View view) {
        Intent connct=new Intent(MainActivity.this,SignIn.class);
        startActivity(connct);
    }
    public void btnFacebook(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Pas encore implémenté");
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    public void goToAccueilInvite(View view) {
        Intent accueilInvite=new Intent(MainActivity.this,AccueilInvite.class);
        startActivity(accueilInvite);
    }
}