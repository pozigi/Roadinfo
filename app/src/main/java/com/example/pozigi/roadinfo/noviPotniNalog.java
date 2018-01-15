package com.example.pozigi.roadinfo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lib_data.Oseba;
import com.example.lib_data.Vozilo;
import com.example.lib_data.potniNalog;

/**
 * Created by pozigi on 6. 01. 2018.
 */

public class noviPotniNalog extends AppCompatActivity implements myInterface {
    ApplicationMy app;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public static Bundle vozi ;
    public static Bundle oseb ;
    Context con;
    int idPotnegaVKeragaVstavimo;

    ImageView imOseba,imKolendar,imVozilo;
    TextView os, datum, vozilo;
    Vozilo vo;
    Oseba o;
    Button shrani;


    @Override
    protected void onResume() {
        super.onResume();


    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app = (ApplicationMy) getApplication();
        setContentView(R.layout.dodajpotni);

        con = this;
        //   i.putExtra("stPotnega",potniS);
       // i.putExtra("stPotnega",potniN);
        vozi = new Bundle();
        oseb = new Bundle();
        os = (TextView) findViewById(R.id.oseba);
        vozilo = (TextView) findViewById(R.id.vozilo);

        imOseba = (ImageView) findViewById(R.id.imageView9);
        imKolendar = (ImageView) findViewById(R.id.imageView10);
        imVozilo = (ImageView) findViewById(R.id.imageView12);

        mRecyclerView = (RecyclerView) findViewById(R.id.rvv);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        shrani = (Button) findViewById(R.id.shrani);

        Intent i = getIntent();
        idPotnegaVKeragaVstavimo = i.getIntExtra("potniS",-1);


        imOseba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //tu mo vse osebe lepo filale
               // oseb=null;
                // specify an adapter (see also next example)
                mAdapter = new adapterOseba1(app.getAll(),noviPotniNalog.this,con);
                mRecyclerView.setAdapter(mAdapter);
            }
        });
        imKolendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        imVozilo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //vozi = null;
               mAdapter = new adapterVozilo1(app.getAll(),noviPotniNalog.this,con);
                mRecyclerView.setAdapter(mAdapter);
            }
        });
        shrani.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View v) {
                    potniNalog potniNalog = new potniNalog(o,System.currentTimeMillis(),System.currentTimeMillis(),vo);
                    app.dodajvStrosek(idPotnegaVKeragaVstavimo,potniNalog);
                    app.save();
                    mRecyclerView.getAdapter().notifyDataSetChanged();
                    onBackPressed();
                }
        });




    }
   public void osvezi(){
       if(vozi!=null){
           vo = (Vozilo) app.dobiVozilo(vozi.getInt("stVozila"));
            vozilo.setText(vo.getRegistrska().toString());
             mRecyclerView.removeAllViewsInLayout();//removes all the views
       }


    }
    public void osvezi1(){
        if(oseb!=null){
            o = (Oseba) app.dobiOsebo(oseb.getInt("stOseba"));
            os.setText(o.getIme().toString() + " " + o.getPriimek().toString());
            mRecyclerView.removeAllViewsInLayout();//removes all the views
        }
    }

}
