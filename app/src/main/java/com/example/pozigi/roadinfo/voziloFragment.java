package com.example.pozigi.roadinfo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by pozigi on 4. 12. 2017.
 */

public class voziloFragment extends Fragment{
 ApplicationMy app;
private RecyclerView mRecyclerView;
private RecyclerView.Adapter mAdapter;
private FloatingActionButton fb;

    public voziloFragment(){}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        mAdapter.notifyDataSetChanged();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_blank, container, false);

        RecyclerView rv = (RecyclerView) rootView.findViewById(R.id.rv_recycler_view);
        rv.setHasFixedSize(true);
        app = (ApplicationMy) this.getActivity().getApplication();
        //mAdapter = new adapterVozilo(app.getAll().getPotniS(ars.getInt("pozicija")), getActivity());
        mAdapter = new adapterVozilo(app, getActivity());
        rv.setAdapter(mAdapter);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);


        //nasopali novo vozilo notri
        fb =(FloatingActionButton) rootView.findViewById(R.id.floatingActionButton);
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(),urediVozilo.class);
                getActivity().startActivity(i);
            }
        });


        return rootView;
    }
    public void refresh(){
        mAdapter.notifyDataSetChanged();
    }

}
