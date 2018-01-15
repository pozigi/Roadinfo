package com.example.pozigi.roadinfo;

import android.app.Application;
import android.os.AsyncTask;
import android.os.Environment;
import android.preference.PreferenceActivity;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import com.example.lib_data.Koren;
import com.example.lib_data.Mymain;
import com.example.lib_data.Oseba;
import com.example.lib_data.Vozilo;
import com.example.lib_data.potniNalog;
import com.example.lib_data.potniStroski;
import com.example.pozigi.roadinfo.*;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;

/**
 * Created by pozigi on 3. 12. 2017.
 */

public class ApplicationMy  extends Application{

    Koren tmp = new Koren();
    private static final String DATA_MAP = "roadinfo";
    private static final String FILE_NAME = "roadinfo2.json";

    @Override
    public void onCreate() {
        super.onCreate();

        if(!load()){
            tmp.scenarijA();
            save();
        }
    }
    public boolean load(){

        File file = new File(this.getExternalFilesDir(DATA_MAP), ""
                + FILE_NAME);
        Koren tmp1 = ApplicationJson.load(file);
        if (tmp1!=null) {
            tmp = tmp1;
            tmp.setLoginan(false);
        }
        else return false;
        return true;
    }
    public boolean save() {

        File file = new File(this.getExternalFilesDir(DATA_MAP), ""
                + FILE_NAME);

        return ApplicationJson.save(tmp,file);
    }
    public Koren getAll(){
       return tmp;
    }
    public void dodajVozilo(Vozilo v){ tmp.dodajVozilo(v); }
    public void dodajOsebo(Oseba o){tmp.dodajOsebo(o);}
    public void dodajPotni(){tmp.dodajPotniStrosek();}
    public Vozilo dobiVozilo(int tmo){ return tmp.getVoziloNaMestu(tmo); }
    public Oseba dobiOsebo(int tmo){ return tmp.getOsebaNaMestu(tmo); }
    public void dodajvStrosek(int stroska,potniNalog pn){ tmp.getPotniS(stroska).dodajNalog(pn); }
    public void pobrisiStrosekPozicija(int pozicija){ tmp.pobrisiStrosekPozicija(pozicija);}
    public void pobrisiVozilo(int pozicija){tmp.pobrisiVozilo(pozicija);}
    public void pobrisiOseboPozicija(int pozicija){tmp.pobrisiOseboPozicija(pozicija);}
    public void pobrisiPotniPozicija(int pS, int pN){tmp.pobrisiNalog(pS,pN);}
    public void dodajRelacijo(int potniS,int potniN) {tmp.dodajRelacijo(potniS,potniN);}
    public void pobrisiRelacijo(int ps,int pn,int pozicija){tmp.pobrisiRelacijo(ps,pn,pozicija);}
    public void nastaviUporabnika (String up){
        tmp.setUporabnik(up);
        save();

    }
    public void nastaviLoginanTrue(){
        tmp.setLoginan(true);
        save();
    }
    public void nastaviLoginanFalse(){tmp.setLoginan(false);}
    public boolean dobiLogin(){ return tmp.getLoginan();}


   public void uploadServer(){
       new UploadFileAsync().execute();
   }

    private class UploadFileAsync extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... params) {
            try {
                String sourceFileUri = Environment.getExternalStorageDirectory().getPath() +"/Android/data/com.example.pozigi.roadinfo/files/roadinfo/" + FILE_NAME;

                HttpURLConnection conn = null;
                DataOutputStream dos = null;
                String lineEnd = "\r\n";
                String twoHyphens = "--";
                String boundary = "*****";
                int bytesRead, bytesAvailable, bufferSize;
                byte[] buffer;
                int maxBufferSize = 1 * 1024 * 1024;
                File sourceFile = new File(sourceFileUri);

                if (sourceFile.isFile()) {

                    try {
                        String upLoadServerUri = "http://192.168.1.7/root/UEA/upload.php?user=" + tmp.getUporabnik();

                        // open a URL connection to the Servlet
                        FileInputStream fileInputStream = new FileInputStream(
                                sourceFile);
                        URL url = new URL(upLoadServerUri);

                        // Open a HTTP connection to the URL
                        conn = (HttpURLConnection) url.openConnection();
                        conn.setDoInput(true); // Allow Inputs
                        conn.setDoOutput(true); // Allow Outputs
                        conn.setUseCaches(false); // Don't use a Cached Copy
                        conn.setRequestMethod("POST");
                        conn.setRequestProperty("Connection", "Keep-Alive");
                        conn.setRequestProperty("ENCTYPE",
                                "multipart/form-data");
                        conn.setRequestProperty("Content-Type",
                                "multipart/form-data;boundary=" + boundary);
                        conn.setRequestProperty("bill", sourceFileUri);

                        dos = new DataOutputStream(conn.getOutputStream());

                        dos.writeBytes(twoHyphens + boundary + lineEnd);
                        dos.writeBytes("Content-Disposition: form-data; name=\"bill\";filename=\""
                                + sourceFileUri + "\"" + lineEnd);

                        dos.writeBytes(lineEnd);

                        // create a buffer of maximum size
                        bytesAvailable = fileInputStream.available();

                        bufferSize = Math.min(bytesAvailable, maxBufferSize);
                        buffer = new byte[bufferSize];

                        // read file and write it into form...
                        bytesRead = fileInputStream.read(buffer, 0, bufferSize);

                        while (bytesRead > 0) {

                            dos.write(buffer, 0, bufferSize);
                            bytesAvailable = fileInputStream.available();
                            bufferSize = Math
                                    .min(bytesAvailable, maxBufferSize);
                            bytesRead = fileInputStream.read(buffer, 0,
                                    bufferSize);

                        }

                        // send multipart form data necesssary after file
                        // data...
                        dos.writeBytes(lineEnd);
                        dos.writeBytes(twoHyphens + boundary + twoHyphens
                                + lineEnd);

                        // Responses from the server (code and message)
                        int serverResponseCode = conn.getResponseCode();
                        String serverResponseMessage = conn
                                .getResponseMessage();

                        if (serverResponseCode == 200) {

                            // messageText.setText(msg);
                            //Toast.makeText(ctx, "File Upload Complete.",
                            //      Toast.LENGTH_SHORT).show();

                            // recursiveDelete(mDirectory1);

                        }

                        // close the streams //
                        fileInputStream.close();
                        dos.flush();
                        dos.close();

                    } catch (Exception e) {

                        // dialog.dismiss();
                        e.printStackTrace();

                    }
                    // dialog.dismiss();

                } // End else block


            } catch (Exception ex) {
                // dialog.dismiss();

                ex.printStackTrace();
            }
            return "Executed";
        }

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected void onPostExecute(String s) {

        }

        @Override
        protected void onProgressUpdate(Void... values) {

        }
    }
    public void potegniServer(){
        String file_url = "http://192.168.1.7/root/UEA/" + tmp.getUporabnik() + "/roadinfo2.json" ;
        new Download().execute(file_url);
    }
    class Download extends AsyncTask<String,String,String>{

        @Override
        protected String doInBackground(String... params) {
            int count;
            try {
                URL url = new URL(params[0]);
                URLConnection conection = url.openConnection();
                conection.connect();

                // this will be useful so that you can show a tipical 0-100%
                // progress bar
                int lenghtOfFile = conection.getContentLength();

                // download the file
                InputStream input = new BufferedInputStream(url.openStream(),
                        8192);

                // Output stream

                OutputStream output = new FileOutputStream(new File(getApplicationContext().getExternalFilesDir(DATA_MAP), ""
                        + FILE_NAME));

                byte data[] = new byte[1024];

                long total = 0;

                while ((count = input.read(data)) != -1) {
                    total += count;
                    // publishing the progress....
                    // After this onProgressUpdate will be called
                    publishProgress("" + (int) ((total * 100) / lenghtOfFile));

                    // writing data to file
                    output.write(data, 0, count);
                }

                // flushing output
                output.flush();

                // closing streams
                output.close();
                input.close();

            } catch (Exception e) {
                Log.e("Error: ", e.getMessage());
            }
            return null;
        }
        @Override
        protected void onPostExecute(String url){
            MainActivity.refresh();
        }
    }
}
