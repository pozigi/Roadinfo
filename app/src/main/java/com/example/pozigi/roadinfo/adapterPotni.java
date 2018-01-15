package com.example.pozigi.roadinfo;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lib_data.Koren;
import com.example.lib_data.potniNalog;
import com.example.lib_data.potniStroski;

import java.util.Date;
import java.util.List;

/**
 * Created by pozigi on 6. 01. 2018.
 */

public class adapterPotni extends RecyclerView.Adapter<adapterPotni.ViewHolder> {
    List<potniNalog> all;
    Activity ac;
    int stPotnega;
    ApplicationMy app;

    public adapterPotni(potniStroski all, Activity ac, int stPotnega,ApplicationMy app){
        super();
        this.all = all.getPotneNaloge();
        this.ac=ac;
        this.stPotnega = stPotnega;
        this.app = app;

    }

    @Override
    public adapterPotni.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowpotni, parent, false);
        // set the view's size, margins, paddings and layout parameters
        adapterPotni.ViewHolder vh = new adapterPotni.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final adapterPotni.ViewHolder holder, final int position) {
        final potniNalog trenutni = all.get(position);

        //stevilo potnih nalogov
        //za keri mesec se gre pa kero leto
        int steviloR = trenutni.getRelacije().size();
        String dan = (String) DateFormat.format("dd", trenutni.getDanOd());
        String mesec = (String) DateFormat.format("MMM", trenutni.getDanOd() );

       /* String mesec = (String) DateFormat.format("MMM", trenutni.getDatum() );
        String leto = (String) DateFormat.format("yyyy",trenutni.getDatum()); */
        Date d = new Date(trenutni.getDanOd());
        holder.relacije.setText(String.valueOf(steviloR)); //oseba
        holder.datum.setText(d.toString());
        holder.vozilo.setText(trenutni.getVozilo().getRegistrska());
        holder.relacije.setText(String.valueOf(trenutni.getRelacije().size()));
        holder.oseba.setText(trenutni.getOseba().getIme() + " " + trenutni.getOseba().getPriimek());

       // holder.datum.setText(mesec + " " + leto); //st nalogov


        //ka mo označli ke smo zbrali tepa mo lehko svipnoli na drugi fragment
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //adapterStroski.startDView(Integer.toString(trenutni.getId()),ac);

                // ars.putInt("pozicija",position);
                Intent i = new Intent(ac.getBaseContext(), pregledPotnega.class);
                i.putExtra("stPotnega",stPotnega); //potniStrosek
                i.putExtra("idPotnega",  holder.getAdapterPosition()); // potniNaalog
                ac.startActivity(i);

                /*
                *
                *  Intent i = getIntent();
         potniS = i.getIntExtra("stPotnega",0);
        potniN = i.getIntExtra("idPotnega",0);
                * */




            }
        });

        //on long ka se spremenimo
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                app.pobrisiPotniPozicija(stPotnega, holder.getAdapterPosition());
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

    private static void startDView(String PotID, Activity ac) {
        //  System.out.println(name+":"+position);
      /*  Intent i = new Intent(ac.getBaseContext(), pregled_posamezna.class);
        i.putExtra("id",  PotID);
        ac.startActivity(i); */

    }


    @Override
    public int getItemCount() {
        return all.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView oseba;
        public TextView datum;
        public TextView relacije;
        public TextView vozilo;


        public ViewHolder(View v){
            super(v);
           // steviloRelacij = (TextView) v.findViewById(R.id.steviloPotnih); // oseba
            //datum = (TextView) v.findViewById(R.id.datum); //st nalogov
            oseba = (TextView) v.findViewById(R.id.oseba);
            datum = (TextView) v.findViewById(R.id.datum);
            relacije = (TextView) v.findViewById(R.id.relacij);
            vozilo = (TextView) v.findViewById(R.id.vozilo);



        }
    }
}
