package com.example.pozigi.roadinfo;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.text.format.DateFormat;

import com.example.lib_data.Koren;
import com.example.lib_data.potniNalog;
import com.example.lib_data.potniStroski;

import java.util.List;

import static com.example.pozigi.roadinfo.MainActivity.ars;


/**
 * Created by pozigi on 3. 12. 2017.
 */

public class adapterStroski extends RecyclerView.Adapter<adapterStroski.ViewHolder> {
    List<potniStroski> all;
    Activity ac;
     ApplicationMy app;





    public adapterStroski(ApplicationMy all, Activity ac){
        super();
        this.all = all.getAll().getPotneStroske();
        this.ac=ac;
        this.app = all;


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowstrosek, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

    final potniStroski trenutni = all.get(position);

        //stevilo potnih nalogov
        //za keri mesec se gre pa kero leto
        int steviloP = trenutni.getPotneNaloge().size();
        String mesec = (String) DateFormat.format("MMM", trenutni.getDatum() );
        String leto = (String) DateFormat.format("yyyy",trenutni.getDatum());

        holder.steviloPotnih.setText("Stevilo potnih stroškov: " + String.valueOf(steviloP)); //oseba
        holder.datum.setText(mesec + " " + leto); //st nalogov


        //ka mo označli ke smo zbrali tepa mo lehko svipnoli na drugi fragment
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //adapterStroski.startDView(Integer.toString(trenutni.getId()),ac);

               // ars.putInt("pozicija",position);
                Intent i = new Intent(ac.getBaseContext(), potniFragment.class);
                i.putExtra("idPotnega",  holder.getAdapterPosition());

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
                                    app.pobrisiStrosekPozicija(holder.getAdapterPosition());
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
                builder.setTitle("Potni strošek");
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
        public TextView steviloPotnih;
        public TextView datum;


        public ViewHolder(View v){
            super(v);
            steviloPotnih = (TextView) v.findViewById(R.id.steviloPotnih); // oseba
            datum = (TextView) v.findViewById(R.id.datum); //st nalogov


        }
    }
}
