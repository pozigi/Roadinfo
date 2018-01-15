package com.example.pozigi.roadinfo;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.lib_data.Lokacija;
import com.example.lib_data.Relacija;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import static com.example.pozigi.roadinfo.MainActivity.ars;
import static com.example.pozigi.roadinfo.MainActivity.ars1;

/**
 * Created by pozigi on 9. 12. 2017.
 */

public class posameznaRelacija extends AppCompatActivity {
    ApplicationMy app;
    Relacija tmp;
    EditText et;
    EditText et2;
    EditText et3;
    EditText et4,et5,et6,et8;
    TextView tv12;
    Button mapa,shrani;
   // Pot tmp;
    int id;
    String XY;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app = (ApplicationMy) getApplication();
        setContentView(R.layout.pregled_posamezna);

        et = (EditText) findViewById(R.id.editText); //zacetek poti
        et2 = (EditText) findViewById(R.id.editText2); //konec poti
        et3 = (EditText) findViewById(R.id.editText3); //oseba ime
        et5 = (EditText) findViewById(R.id.editText5); //oseba priimek
        et6 = (EditText) findViewById(R.id.editText6); //registrska
        et8 = (EditText) findViewById(R.id.editText8); //namen tekst
        et4 = (EditText) findViewById(R.id.editText4); //prevozeno kilometrov
        tv12 = (TextView) findViewById(R.id.textView12); //izpis povracila stroškov
        mapa = (Button) findViewById(R.id.button6); //odpre aktiviti z mapo
        shrani = (Button) findViewById(R.id.button7); //shranimo te ali ponastavlene nastavitve

     /*   mapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Intent i = new Intent(getBaseContext(),Mapa.class ); //
               // i.putExtra("id",tmp.GetID());
               // startActivity(i);
            }
        }); */

    }
    public class DobiLokacijo extends AsyncTask<Void,Void,String> {
        Lokacija tmp;
        EditText e;
        public DobiLokacijo(Lokacija t,EditText et){
            this.tmp=t;
            this.e=et;
        }
        @Override
        protected void onPreExecute(){
            //v et.je lehko napisemo ka pridobiva lokacijo ali pa ke drugega
        }
        @Override
        protected String doInBackground(Void... params){
            Geocoder geocoder;
            List<Address> naslovi;
            geocoder = new Geocoder(getApplication(), Locale.getDefault());
            try {
                naslovi = geocoder.getFromLocation(tmp.getLang(), tmp.getLatt(),1);
                String naslov = naslovi.get(0).getLocality(); //recimo dobis nazej MB
                return naslov;
            } catch (IOException e) {
                e.printStackTrace();
            }

            return "Vkljuci podatke/wifi/gps";
        }
        @Override
        protected void onPostExecute(String vrni){
            super.onPostExecute(vrni);
            setXY(vrni,e);
        }
    }
    public void setXY(String lok,EditText edit){
        edit.setText(lok);
    }
    public String xyToAdress(Lokacija tmp,EditText edit){
      /*  Geocoder geocoder;
        List<Address> naslovi;
        geocoder = new Geocoder(this, Locale.getDefault());
        try {
            naslovi = geocoder.getFromLocation(tmp.getLang(), tmp.getLatt(),1);
            String naslov = naslovi.get(0).getLocality(); //recimo dobis nazej MB
            return naslov;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "Vkljuci podatke/wifi/gps";*/

        new DobiLokacijo(tmp,edit).execute();
        // db.cancel(true);
        return "Procesira ...";
    }
    public void update(Relacija t){
        et4.setText(Double.toString(t.getRazdalja()));
        tv12.setText(Double.toString(t.getStrosek()));
        // db.execute(t.prva_lok1(),t.zadnja_lok1());
        et.setText(xyToAdress(t.getGPS().get(0),et)); //string z zacetkom lokacije
        et2.setText(xyToAdress(t.getGPS().get(t.getGPS().size()),et2)); //string zadnje lokacije
      //  et3.setText(t.getIzbrano_vozilo().getVoznik().getIme()); //ime
       // et5.setText(t.getIzbrano_vozilo().getVoznik().getPriimek()); //priimek
        //et6.setText(t.getIzbrano_vozilo().getRegistrska());

        // et8 - namen izvoza
        et8.setText(t.getDokazilo());

    }
    void pridobi(int ID){
        ID = ID -1;
        tmp=app.getAll().getPotniS(ars.getInt("pozicija")).getPotneNaloge().get(ars1.getInt("pozicija1")).getRelacije().get(ID);
        izracunej_prevozeno(tmp);
        izracunaj_strosek(tmp);
        update(tmp);
    }

    @Override
    protected void onResume(){
        super.onResume();

        Bundle extras = getIntent().getExtras();
        if(extras!=null){
            // pridobi(extras.getString(""));
            id = extras.getInt("id");
            if(id!=0){
                pridobi(id);
            }



        }else{
            System.out.print("Nič ni v extras");
        }
    }
    void izracunej_prevozeno(Relacija t){
        float prevozeno=0f;
        Location one = new Location("one");
        Location two = new Location("two");
        for(int i=0;i<t.getGPS().size()-1;i++){
            one.setLatitude(t.getGPS().get(i).getLatt());
            one.setLongitude(t.getGPS().get(i).getLang());

            two.setLatitude(t.getGPS().get(i+1).getLatt());
            two.setLongitude(t.getGPS().get(i+1).getLang());
            prevozeno += one.distanceTo(two);
        }
        prevozeno = prevozeno / 1000;
        t.setRazdalja(prevozeno);

    }
    void izracunaj_strosek(Relacija t){
        double strosek=0;
        strosek=t.getRazdalja()*0.37;
        t.setStrosek(strosek);
    }

}
