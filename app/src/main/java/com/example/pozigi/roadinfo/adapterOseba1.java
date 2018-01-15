package com.example.pozigi.roadinfo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lib_data.Koren;
import com.example.lib_data.Oseba;

import java.util.List;

/**
 * Created by pozigi on 6. 01. 2018.
 */

public class adapterOseba1 extends RecyclerView.Adapter<adapterOseba1.ViewHolder>{
    List<Oseba> all;
    Activity ac;
    Context mC;
    private myInterface listener;


    public adapterOseba1(Koren all, Activity ac, Context context){
        super();
        this.all = all.getVseOsebe();
        this.ac = ac;
        mC = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowosebe, parent, false);
        // set the view's size, margins, paddings and layout parameters
        adapterOseba1.ViewHolder vh = new adapterOseba1.ViewHolder(v);
        return vh;
    }



    @Override
    public void onBindViewHolder(final adapterOseba1.ViewHolder holder, int position) {
        final Oseba trenutni = all.get(position);

        holder.ime.setText(trenutni.getIme());
        holder.priimek.setText(trenutni.getPriimek());
        holder.naslov.setText(trenutni.getNaslov());
        holder.posta.setText(trenutni.getPosta());
        holder.kraj.setText(trenutni.getKraj());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               noviPotniNalog.oseb.putInt("stOseba",holder.getAdapterPosition());
                ((noviPotniNalog) mC).osvezi1();
            }
        });
    }

    @Override
    public int getItemCount() {
        return all.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView ime;
        public TextView priimek;
        public TextView naslov;
        public TextView posta;
        public TextView kraj;


        public ViewHolder(View itemView) {
            super(itemView);

            ime = (TextView) itemView.findViewById(R.id.ime);
            priimek = (TextView) itemView.findViewById(R.id.priimek);
            naslov = (TextView) itemView.findViewById(R.id.naslov);
            posta = (TextView) itemView.findViewById(R.id.posta);
            kraj = (TextView) itemView.findViewById(R.id.kraj);
        }
    }
}

