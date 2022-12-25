package com.example.blasti;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.LinkedList;

public class AccueilClient extends AppCompatActivity {
    private final LinkedList<String> mWordList = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil_client);

        for (int i = 0; i < 20; i++) {
            mWordList.addLast("Element " + i);
        }

    }
}