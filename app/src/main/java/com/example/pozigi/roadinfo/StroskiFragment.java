package com.example.pozigi.roadinfo;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import static android.support.design.widget.Snackbar.LENGTH_SHORT;

/**
 * Created by pozigi on 3. 12. 2017.
 */

public class StroskiFragment extends Fragment{
    ApplicationMy app;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private FloatingActionButton fb;
   // public static Bundle bundle = new Bundle();

    public StroskiFragment(){}



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_blank, container, false);

        RecyclerView rv = (RecyclerView) rootView.findViewById(R.id.rv_recycler_view);
        rv.setHasFixedSize(true);
        app = (ApplicationMy) this.getActivity().getApplication();
        mAdapter = new adapterStroski(app, getActivity());
        rv.setAdapter(mAdapter);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);


        //Tu mo dodali novi potni strosek !!!
        fb =(FloatingActionButton) rootView.findViewById(R.id.floatingActionButton);
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                app.dodajPotni();
                Snackbar mySnackbar = Snackbar.make(v, "Dodan potni strošek", LENGTH_SHORT);
                mySnackbar.show();
                app.save();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.detach(StroskiFragment.this).attach(StroskiFragment.this).commit();
            }
        });


        return rootView;
    }

}
