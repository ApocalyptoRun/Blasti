package com.example.blasti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void choixMode(View view) {
        Intent inscrit=new Intent(MainActivity.this,signinchoix.class);
        startActivity(inscrit);
    }


    public void signIn(View view) {
        Intent connct=new Intent(MainActivity.this,SignIn.class);
        startActivity(connct);
    }
}