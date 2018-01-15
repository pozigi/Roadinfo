package com.example.pozigi.roadinfo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by pozigi on 6. 01. 2018.
 */

public class potniFragment extends AppCompatActivity {
    ApplicationMy app;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private FloatingActionButton fb;
    int idPotnega;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app = (ApplicationMy) getApplication();
        setContentView(R.layout.pregledpotni);

        fb = (FloatingActionButton) findViewById(R.id.floatingActionButton4);

        Intent intent = getIntent();
        idPotnega = intent.getIntExtra("idPotnega",-1); //st stroška



        mRecyclerView = (RecyclerView) findViewById(R.id.rv);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        // specify an adapter (see also next example)
        app = (ApplicationMy) getApplication();

        //lista potnih nalogov
        //idPotnega
        mAdapter = new adapterPotni(app.getAll().getPotneStroske().get(idPotnega), this,idPotnega,app);
        mRecyclerView.setAdapter(mAdapter);

        //TU DODAMO NOVI POTNI NALOG

        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(),noviPotniNalog.class);
                i.putExtra("potniS",idPotnega);
                startActivity(i);
            }
        });
    }
    @Override
    public void onResume() {
        super.onResume();
        mAdapter.notifyDataSetChanged();
    }

}
