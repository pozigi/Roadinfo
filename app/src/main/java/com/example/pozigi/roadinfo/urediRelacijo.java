package com.example.pozigi.roadinfo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Date;
import java.util.Map;

/**
 * Created by pozigi on 8. 01. 2018.
 */
//API_KEY: AIzaSyB6Fq4yEOiu7IrvR2_ebGQqvXNCDw-uvVc
public class urediRelacijo extends AppCompatActivity{
    ApplicationMy app;
    private TextView oseba;
    private TextView datum;
    private TextView vozilo;
    private EditText dokazilo;
    private EditText namen;
    private ImageView mapa;
    private Button shrani;
    int id;
    int potniS;
    int potniN;
    /*
    *
    *  i.putExtra("id",holder.getAdapterPosition());
                i.putExtra("potniS",potniS);
                i.putExtra("potniN",potniN);
    *
    * */

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.uredirelacijo);

        app = (ApplicationMy) getApplication();

        Intent i = getIntent();
        id = i.getIntExtra("id",-1);
        potniS = i.getIntExtra("potniS",-1);
        potniN = i.getIntExtra("potniN",-1);


        oseba = (TextView) findViewById(R.id.oseba);
        datum = (TextView) findViewById(R.id.datum);
        vozilo = (TextView) findViewById(R.id.vozilo);

        dokazilo = (EditText) findViewById(R.id.editText10);
        namen = (EditText)findViewById(R.id.editText11);

        mapa = (ImageView) findViewById(R.id.imageView17);

        shrani = (Button) findViewById(R.id.Shrani);


        oseba.setText(app.getAll().getPotneStroske().get(potniS).getPotneNaloge().get(potniN).getOseba().getIme());
        vozilo.setText(app.getAll().getPotneStroske().get(potniS).getPotneNaloge().get(potniN).getVozilo().getRegistrska());
        long dat = app.getAll().getPotneStroske().get(potniS).getPotneNaloge().get(potniN).getRelacije().get(id).getOdhodDatum();
        Date d = new Date(dat);
        datum.setText(d.toString());
        if(!app.getAll().getPotneStroske().get(potniS).getPotneNaloge().get(potniN).getRelacije().get(id).getStranka().equals("")){
            namen.setText(app.getAll().getPotneStroske().get(potniS).getPotneNaloge().get(potniN).getRelacije().get(id).getStranka());
            dokazilo.setText(app.getAll().getPotneStroske().get(potniS).getPotneNaloge().get(potniN).getRelacije().get(id).getDokazilo());
        }



        mapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(),MapsActivity.class);
                i.putExtra("id",id);
                i.putExtra("potniS",potniS);
                i.putExtra("potniN",potniN);
                startActivity(i);
            }
        });

        shrani.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //nasoplemo dokazilo pa namen pa dujaaa!
                if(!dokazilo.getText().toString().equals("") && !namen.getText().toString().equals("")){
                    app.getAll().getPotneStroske().get(potniS).getPotneNaloge().get(potniN).getRelacije().get(id).setDokazilo(dokazilo.getText().toString());
                    app.getAll().getPotneStroske().get(potniS).getPotneNaloge().get(potniN).getRelacije().get(id).setStranka(namen.getText().toString());
                    app.save();
                }
                onBackPressed();

            }
        });

    }
}
