package com.example.pozigi.roadinfo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by pozigi on 3. 12. 2017.
 */

public class pregledStroski extends AppCompatActivity{
    ApplicationMy app;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private FloatingActionButton fb;

    @Override
    protected void onCreate(Bundle savedInstanceBundle){
        super.onCreate(savedInstanceBundle);
        app = (ApplicationMy) getApplication();

        setContentView(R.layout.fragment_blank);
        //  lv = (ListView) findViewById(R.id.listview);
        mRecyclerView =(RecyclerView) findViewById(R.id.rv_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        // specify an adapter (see also next example)
        app = (ApplicationMy) getApplication();
        mAdapter = new adapterStroski(app, this);
        mRecyclerView.setAdapter(mAdapter);


    }
    @Override
    protected void onResume(){
        super.onResume();
        // SetListView();
        //  app.sortPodatke();
       // mAdapter = new adapterStroski(app, this);
        int i=0;
        mAdapter.notifyDataSetChanged();
    }
}
