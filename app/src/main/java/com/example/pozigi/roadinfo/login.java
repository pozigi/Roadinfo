package com.example.pozigi.roadinfo;

import android.app.ProgressDialog;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static android.support.design.widget.Snackbar.LENGTH_SHORT;

/**
 * Created by pozigi on 7. 01. 2018.
 */

public class login extends AppCompatActivity{
    ApplicationMy app;
    public static final int CONNECTION_TIMEOUT=10000;
    public static final int READ_TIMEOUT=15000;
    private EditText user;
    private EditText pass;
    private Button prijava;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        app = (ApplicationMy) getApplication();

        user = (EditText)findViewById(R.id.editText7);
        pass =(EditText) findViewById(R.id.editText9);
        prijava = (Button) findViewById(R.id.button2);

        prijava.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(preveriPolja())
                    new AsyncLogin().execute(user.getText().toString(),pass.getText().toString());
            }
        });
    }
   /* public void checkLogin(){
        new AsyncLogin().execute(user.getText().toString(),pass.getText().toString());
    } */
    boolean preveriPolja(){

        if(!user.getText().toString().equals(""))
            if(!pass.getText().toString().equals(""))
                return true;
        return false;
    }

    private class AsyncLogin extends AsyncTask<String,String,String>{

        ProgressDialog pdLoading = new ProgressDialog(login.this);
        HttpURLConnection conn;
        URL url = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //this method will be running on UI thread
            pdLoading.setMessage("\tLoading...");
            pdLoading.setCancelable(false);
            pdLoading.show();

        }

        @Override
        protected String doInBackground(String... params) {
            try{
                url = new URL("http://164.8.204.80/root/UEA/login.inc.php");
            } catch (MalformedURLException e){
                e.printStackTrace();
                return "exception";
            }
            try{
                // Setup HttpURLConnection class to send and receive data from php and mysql
                conn = (HttpURLConnection)url.openConnection();
                conn.setReadTimeout(READ_TIMEOUT);
                conn.setConnectTimeout(CONNECTION_TIMEOUT);
                conn.setRequestMethod("POST");

                // setDoInput and setDoOutput method depict handling of both send and receive
                conn.setDoInput(true);
                conn.setDoOutput(true);

                // Append parameters to URL
                Uri.Builder builder = new Uri.Builder()
                        .appendQueryParameter("username", params[0])
                        .appendQueryParameter("password", params[1]);
                String query = builder.build().getEncodedQuery();

                // Open connection for sending data
                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(query);
                writer.flush();
                writer.close();
                os.close();
                conn.connect();
            } catch (IOException e1){
                e1.printStackTrace();
                return "exception";
            }
            try {

                int response_code = conn.getResponseCode();

                // Check if successful connection made
                if (response_code == HttpURLConnection.HTTP_OK) {

                    // Read data sent from server
                    InputStream input = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    StringBuilder result = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }

                    // Pass data to onPostExecute method
                    return(result.toString());

                }else{

                    return("unsuccessful");
                }

            } catch (IOException e) {
                e.printStackTrace();
                return "exception";
            } finally {
                conn.disconnect();
            }
        }

        @Override
        protected void onPostExecute(String s) {
            pdLoading.dismiss();
            if(s.equalsIgnoreCase("true")){
                    //vrnile se bomo nazej na Fragment glovni
               // app.PosljiNaServer();
                app.nastaviLoginanTrue();
             //   onBackPressed();
            }else if(s.equalsIgnoreCase("false")){
                Snackbar mySnackbar = Snackbar.make(findViewById(R.id.cl), "Napacno uporabniško/geslo", LENGTH_SHORT);
                mySnackbar.show();
            }else if(s.equalsIgnoreCase("exception") || s.equalsIgnoreCase("unsuccessful")){
                Snackbar mySnackbar = Snackbar.make(findViewById(R.id.cl), "Napaka, nekaj je šlo narobe", LENGTH_SHORT);
                mySnackbar.show();
            }
        }
    }
}
