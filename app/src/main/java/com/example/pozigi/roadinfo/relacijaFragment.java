package com.example.pozigi.roadinfo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lib_data.potniNalog;

import static com.example.pozigi.roadinfo.MainActivity.ars;
import static com.example.pozigi.roadinfo.MainActivity.ars1;

/**
 * Created by pozigi on 5. 12. 2017.
 */

public class relacijaFragment  extends Fragment{
    ApplicationMy app;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private FloatingActionButton fb;

    public relacijaFragment(){

    }
    @Override
    public void onResume() {
        super.onResume();
        mAdapter.notifyDataSetChanged();
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_blank, container, false);

        RecyclerView rv = (RecyclerView) rootView.findViewById(R.id.rv_recycler_view);
        rv.setHasFixedSize(true);
        app = (ApplicationMy) this.getActivity().getApplication();
       // potniNalog pn = app.getAll().getPotniS(ars.getInt("pozicija")).getPotneNaloge().get(ars1.getInt("pozicija1"));
        mAdapter = new adapterOseba(app, getActivity());
        rv.setAdapter(mAdapter);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);



        //Tu mo novo osebo našopali notri !

        fb =(FloatingActionButton) rootView.findViewById(R.id.floatingActionButton);
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               nastavi();
            }
        });


        return rootView;
    }
    void nastavi(){
        Intent i = new Intent(getContext(),urediOsebo.class);
      //  i.putExtra("fragmentOseba", (Parcelable) this);
        getActivity().startActivity(i);
    }
}
