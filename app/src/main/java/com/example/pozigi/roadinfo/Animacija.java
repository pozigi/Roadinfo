package com.example.pozigi.roadinfo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by pozigi on 18. 01. 2018.
 */

public class Animacija extends Activity {

    ImageView gif;
    private static int TIME = 8000;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.animacija);

        gif = (ImageView) findViewById(R.id.animacijaGif);
        Glide.with(this).load(R.drawable.animacija).asGif().placeholder(R.drawable.animacija).crossFade().into(gif);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(Animacija.this,MainActivity.class);
                startActivity(i);
               finish();
            }
        },4400);
    }

}
