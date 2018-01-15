package com.example.pozigi.roadinfo;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
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

public class adapterOseba extends RecyclerView.Adapter<adapterOseba.ViewHolder>{
    List<Oseba> all;
    Activity ac;
    ApplicationMy app;

    public adapterOseba(ApplicationMy all, Activity ac){
        super();
        this.all = all.getAll().getVseOsebe();
        this.ac = ac;
        app = all;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowosebe, parent, false);
        // set the view's size, margins, paddings and layout parameters
        adapterOseba.ViewHolder vh = new adapterOseba.ViewHolder(v);
        return vh;
    }



    @Override
    public void onBindViewHolder(final adapterOseba.ViewHolder holder, int position) {
        final Oseba trenutni = all.get(position);

        holder.ime.setText(trenutni.getIme());
        holder.priimek.setText(trenutni.getPriimek());
        holder.naslov.setText(trenutni.getNaslov());
        holder.posta.setText(trenutni.getPosta());
        holder.kraj.setText(trenutni.getKraj());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ofnemo totega v noven aktivitije
                Intent i = new Intent(v.getContext(),urediOsebo.class);
                i.putExtra("id",holder.getAdapterPosition());
                ac.startActivity(i);

            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                app.pobrisiOseboPozicija(holder.getAdapterPosition());
                                app.save();
                                notifyDataSetChanged();

                                // app.removeLocationByPosition(viewHolder.getAdapterPosition());
                                // app.removeLocation(viewHolder.getAdapterPosition());
                                // app.save();
                                // mAdapter.notifyDataSetChanged();
                                // mRecyclerView.getAdapter().notifyDataSetChanged();
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                //mRecyclerView.getAdapter().notifyDataSetChanged();
                                //mAdapter.notifyDataSetChanged();
                                // mAdapter.notifyDataSetChanged();
                                break;
                        }
                        // mRecyclerView.getAdapter().notifyDataSetChanged();
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Oseba");
                builder.setMessage("Si prepričan da želiš zbrisati?").setPositiveButton("Da", dialogClickListener)
                        .setNegativeButton("Ne", dialogClickListener);

                builder.show();
                return true;
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

