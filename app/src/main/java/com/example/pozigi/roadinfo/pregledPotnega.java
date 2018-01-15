package com.example.pozigi.roadinfo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

/**
 * Created by pozigi on 6. 01. 2018.
 */

public class pregledPotnega extends AppCompatActivity {

    ApplicationMy app;
    // ListView lv;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private FloatingActionButton fb;
    int potniS;
    int potniN;



    @Override
    protected void onCreate(Bundle savedInstanceBundle){
        super.onCreate(savedInstanceBundle);
        app = (ApplicationMy) getApplication();
        setContentView(R.layout.pregledpotni);

        mRecyclerView =(RecyclerView) findViewById(R.id.rv);
        mRecyclerView.setHasFixedSize(true);



        Intent i = getIntent();
         potniS = i.getIntExtra("stPotnega",0);
        potniN = i.getIntExtra("idPotnega",0);

      //  fb = (Button) findViewById(R.id.floatingActionButton);
      /*  fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(),noviPotniNalog.class);
                i.putExtra("stPotnega",potniS);
                i.putExtra("stPotnega",potniN);
                startActivity(i);

            }
        }); */
        fb = (FloatingActionButton) findViewById(R.id.floatingActionButton4);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        // specify an adapter (see also next example)
        app = (ApplicationMy) getApplication();
        mAdapter = new adapterRelacija(app.getAll().getPotneStroske().get(potniS).getPotneNaloge().get(potniN), this, potniS, potniN,app);
        mRecyclerView.setAdapter(mAdapter);



        //TU DODAMO NOVO RELACIJO!!!!!!

        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //app.getAll().getPotneStroske().get(potniS).getPotneNaloge().get(potniN).setDanOd(app.getAll().getPotneStroske().get(potniS).getPotneNaloge().get(potniN).getRelacije().get(0).getPrihodDatum());
                app.dodajRelacijo(potniS,potniN);
                app.save();
                mAdapter.notifyDataSetChanged();


            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        mAdapter.notifyDataSetChanged();
    }
}
