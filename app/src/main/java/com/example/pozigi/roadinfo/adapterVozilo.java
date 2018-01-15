package com.example.pozigi.roadinfo;

import android.app.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.LayoutInflater;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lib_data.Koren;
import com.example.lib_data.Vozilo;

import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * Created by pozigi on 4. 12. 2017.
 */

public class adapterVozilo extends RecyclerView.Adapter<adapterVozilo.ViewHolder>{
    List<Vozilo> all;
    Activity ac;
    ApplicationMy app;

    public adapterVozilo(ApplicationMy all, Activity ac){
        super();
        this.all = all.getAll().getVsaVozila();
        this.ac=ac;
        app = all;
    }

    @Override
    public adapterVozilo.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
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
                Intent i = new Intent(v.getContext(),urediVozilo.class);
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
                                app.pobrisiVozilo(holder.getAdapterPosition());
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
                builder.setTitle("Vozilo");
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
