package com.example.pozigi.roadinfo;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.lib_data.Oseba;
import com.example.lib_data.Vozilo;

/**
 * Created by pozigi on 6. 01. 2018.
 */

public class urediVozilo extends AppCompatActivity {
    ApplicationMy app;
    Vozilo tmp;
    EditText et,et1,et2;
    Button potrdi;
    int id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app = (ApplicationMy) getApplication();
        setContentView(R.layout.uredivozilo);

        Intent intent = getIntent();

        id = intent.getIntExtra("id",-1);

        if(id!=-1)
         tmp = app.getAll().getVsaVozila().get(id);

        et = (EditText) findViewById(R.id.naziv);
        et1 = (EditText) findViewById(R.id.registrska);
        et2 = (EditText) findViewById(R.id.prevozeno);

        potrdi = (Button) findViewById(R.id.potrdi);

        if(tmp!= null){
            et.setText(tmp.getNaziv());
            et1.setText(tmp.getRegistrska());
            et2.setText(String.valueOf(tmp.getPrevozeno()));

        }

        potrdi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(id!=-1)
                 shraniPodatke();
                else
                    vnesiPodatke();
            }
        });

    }
    void shraniPodatke(){
        if(tmp!=null){
            app.getAll().getVsaVozila().get(id).setNaziv(et.getText().toString());
            app.getAll().getVsaVozila().get(id).setRegistrska(et1.getText().toString());
            app.getAll().getVsaVozila().get(id).setPrevozeno(Integer.parseInt(et2.getText().toString()));

           app.save();


        }



        // MORE OSVEŽITE FRAGMENT



        onBackPressed();
    }
    void vnesiPodatke(){
        Vozilo tmp = new Vozilo(et.getText().toString(),et1.getText().toString());
        app.dodajVozilo(tmp);
        app.save();


       /* FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.detach(adapterStroski.this).attach(pregledStroski.this).commit(); */

        onBackPressed();
    }
}
