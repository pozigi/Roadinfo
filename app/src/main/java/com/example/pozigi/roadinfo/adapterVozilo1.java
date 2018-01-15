package com.example.pozigi.roadinfo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lib_data.Koren;
import com.example.lib_data.Vozilo;

import java.util.List;

import static com.example.pozigi.roadinfo.R.id.rvv;

/**
 * Created by pozigi on 4. 12. 2017.
 */

public class adapterVozilo1 extends RecyclerView.Adapter<adapterVozilo1.ViewHolder>{
    List<Vozilo> all;
    Koren koren;
    Activity ac;
    Context mC;
    Vozilo vo;

    public adapterVozilo1(Koren all, Activity ac,Context c){
        super();
        this.koren = all;
        this.all = all.getVsaVozila();
        this.ac=ac;
        this.mC=c;

    }

    @Override
    public adapterVozilo1.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowvozila, parent, false); //rowvozila
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Vozilo trenutni = all.get(position);

        //našoplemo podatke naloga
        holder.naziv.setText(trenutni.getNaziv().toString());
        holder.registerska.setText(trenutni.getRegistrska().toString());
        holder.prevozeno.setText(String.valueOf(trenutni.getPrevozeno()) + " km ");

       //podatki avta

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //   i.putExtra("stPotnega",potniS);
                // i.putExtra("stPotnega",potniN);
               /* Intent i = ac.getIntent();
                i.putExtra("vozilo",holder.getAdapterPosition());
                ac.notifyAll(); */
               // vo = koren.getVoziloNaMestu(holder.getAdapterPosition());
                noviPotniNalog.vozi.putInt("stVozila",holder.getAdapterPosition());
                ((noviPotniNalog) mC).osvezi();

            }
        });

    }

    @Override
    public int getItemCount() {
        return all.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView naziv;
        public TextView registerska;
        public  TextView prevozeno;

        public ViewHolder(View itemView) {
            super(itemView);
            naziv = (TextView) itemView.findViewById(R.id.naziv);
            registerska = (TextView) itemView.findViewById(R.id.registrska);
            prevozeno = (TextView) itemView.findViewById(R.id.prevozeno);
        }

    }
}
