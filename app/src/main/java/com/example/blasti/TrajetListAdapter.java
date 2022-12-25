package com.example.blasti;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blasti.Model.Trajet;

import java.lang.reflect.Field;
import java.util.HashMap;

public class TrajetListAdapter extends RecyclerView.Adapter<TrajetListAdapter.TrajetViewHolder> {
    private final Object[] mTrajetList;

    public TrajetListAdapter(AccueilClient accueilClient, Object[] mTrajetList) {
        this.mTrajetList = mTrajetList;
    }

    @NonNull
    @Override
    public TrajetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.trajettistitem, parent, false);


        return new TrajetViewHolder(mItemView, this);
    }


    @Override
    public void onBindViewHolder(@NonNull TrajetViewHolder holder, int position) {
        Object mCurrent = mTrajetList[position];
        Trajet trajet = new Trajet();
        Field[] fields = trajet.getClass().getDeclaredFields();
        System.out.println("fields"+fields.toString());
         for ( int i= 0 ; i < fields.length ; i++){

             Object[] values;
            
            values = ((HashMap<String, String>) mCurrent).values().toArray();
            Object value = values[i];
            holder.mTextViews[i].setText(value.toString());

        }



    }

    @Override
    public int getItemCount() {
       return mTrajetList.length;
    }

    class TrajetViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView trajetDate;
        public final TextView trajetHeure;
        public final TextView trajetPrix;
        public final TextView trajetChauffeur;
        public final TextView trajetDepart;
        public final TextView trajetArrivee;

        final TrajetListAdapter mAdapter;
        public TextView[] mTextViews;


        public TrajetViewHolder(View itemView, TrajetListAdapter adapter) {
            super(itemView);
            trajetDate = itemView.findViewById(R.id.trajetDate);
            trajetHeure = itemView.findViewById(R.id.trajetHeure);
            trajetPrix = itemView.findViewById(R.id.trajetPrix);
            trajetChauffeur = itemView.findViewById(R.id.trajetChauffeur);
            trajetDepart = itemView.findViewById(R.id.trajetDepart);
            trajetArrivee = itemView.findViewById(R.id.trajetArrivee);

            mTextViews = new TextView[]{trajetDate,trajetHeure,trajetArrivee,trajetPrix,trajetChauffeur,trajetDepart};
            this.mAdapter = adapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }
}

