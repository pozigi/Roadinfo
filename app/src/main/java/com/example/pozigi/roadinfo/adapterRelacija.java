package com.example.pozigi.roadinfo;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lib_data.Koren;
import com.example.lib_data.Oseba;
import com.example.lib_data.Relacija;
import com.example.lib_data.potniNalog;

import java.util.Date;
import java.util.List;

import static com.google.gson.internal.bind.util.ISO8601Utils.format;

/**
 * Created by pozigi on 5. 12. 2017.
 */

public class adapterRelacija extends RecyclerView.Adapter<adapterRelacija.ViewHolder>{
    List<Relacija> all;
    Activity ac;
    int potniS;
    int potniN;
    ApplicationMy app;



    public adapterRelacija(potniNalog all, Activity ac, int potniS, int potniN,ApplicationMy app){
        super();
        this.all = all.getRelacije();
        this.ac = ac;
        this.potniS = potniS;
        this.potniN = potniN;
        this.app = app;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowrelacije, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Relacija trenutni = all.get(position);

       // holder.stranka.setText(trenutni.getStranka());
        holder.razdalja.setText(Double.toString(trenutni.getRazdalja()));
        holder.strosek.setText(Double.toString(trenutni.getStrosek()));

        int hOd = (int) ((trenutni.getOdhodDatum() / 1000) / 3600);
        int moD = (int) (((trenutni.getOdhodDatum() / 1000) / 60) % 60);

        int hDo = (int) ((trenutni.getOdhodDatum() / 1000) / 3600);
        int mDo = (int) (((trenutni.getOdhodDatum() / 1000) / 60) % 60);

        //holder.datumdo.setText(Integer.toString(hOd) + ":" + Integer.toString(moD));
        holder.namen.setText(trenutni.getStranka());
        Date d = new Date(trenutni.getOdhodDatum());
        holder.datumod.setText(d.toString());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //ofnemo totega v noven aktivitije
                Intent i = new Intent(v.getContext(),urediRelacijo.class);
                i.putExtra("id",holder.getAdapterPosition());
                i.putExtra("potniS",potniS);
                i.putExtra("potniN",potniN);
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
                               // app.pobrisiPotniPozicija(stPotnega, holder.getAdapterPosition());
                                app.pobrisiRelacijo(potniS,potniN,holder.getAdapterPosition());
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
        /*holder.fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(),noviPotniNalog.class);
               ac.startActivity(i);
            }
        }); */


    }

    @Override
    public int getItemCount() {
       return all.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView razdalja;
        public TextView strosek;
        public TextView datumod;
        public TextView namen;
       // public FloatingActionButton fb;


         public ViewHolder(View itemView) {
             super(itemView);
          //   fb = (FloatingActionButton) itemView.findViewById(R.id.floatingActionButton4);
             razdalja = (TextView) itemView.findViewById(R.id.razdalja);
             strosek = (TextView) itemView.findViewById(R.id.strosek);
             datumod = (TextView) itemView.findViewById(R.id.datumod);
             namen = (TextView) itemView.findViewById(R.id.namen);

         }
     }
}
