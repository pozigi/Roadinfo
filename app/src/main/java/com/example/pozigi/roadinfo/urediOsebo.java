package com.example.pozigi.roadinfo;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.lib_data.Oseba;

import java.util.List;

/**
 * Created by pozigi on 6. 01. 2018.
 */

public class urediOsebo extends AppCompatActivity{
    ApplicationMy app;
    Oseba tmp;
    EditText et,et1,et2,et3,et4;
    Button potrdi;
    Fragment ft;
    int id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app = (ApplicationMy) getApplication();
        setContentView(R.layout.urediosebo);

        Intent intent = getIntent();
        id = intent.getIntExtra("id",-1);


        if(id!=-1)
        tmp = app.getAll().getVseOsebe().get(id);

        et = (EditText) findViewById(R.id.ime);
        et1 = (EditText) findViewById(R.id.priimek);
        et2 = (EditText) findViewById(R.id.naslov);
        et3 = (EditText) findViewById(R.id.posta);
        et4 = (EditText) findViewById(R.id.kraj);

        potrdi = (Button) findViewById(R.id.button);

        if(tmp!= null){
            et.setText(tmp.getIme());
            et1.setText(tmp.getPriimek());
            et2.setText(tmp.getNaslov());
            et3.setText(tmp.getPosta());
            et4.setText(tmp.getKraj());
        }

        potrdi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(id!=-1)
                 shraniPodatke();
                else
                    vnosPodatkov();
            }
        });



    }
    void shraniPodatke(){
        if(tmp!=null){
            tmp.setIme(et.getText().toString());
            tmp.setPriimek(et1.getText().toString());
            tmp.setNaslov(et2.getText().toString());
            tmp.setPosta(et3.getText().toString());
            tmp.setKraj(et4.getText().toString());

        }



      // MORE OSVEŽITE FRAGMENT



       onBackPressed();
    }
    void vnosPodatkov(){
        Oseba o = new Oseba(et.getText().toString(),et1.getText().toString(),et2.getText().toString(),et3.getText().toString(),et4.getText().toString());
        app.dodajOsebo(o);
        app.save();
      // List<Fragment> allFragment = getSupportFragmentManager().getFragments();
       /* ft = getSupportFragmentManager().findFragmentById(R.id.frag);
        FragmentTransaction ft1 = getSupportFragmentManager().beginTransaction();
        ft.deta
        ft.detach(YourFragment.this).attach(YourFragment.this).commit();*/
        onBackPressed();
    }
}
