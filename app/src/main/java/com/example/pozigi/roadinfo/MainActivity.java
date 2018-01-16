package com.example.pozigi.roadinfo;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import static android.support.design.widget.Snackbar.LENGTH_SHORT;
import static android.support.v4.view.PagerAdapter.POSITION_NONE;

public class MainActivity extends AppCompatActivity {
    ApplicationMy app;
    ViewPager viewPager;
    static PagerAdapter pagerAdapter;
    TabLayout tabLayout;
    Handler handler = new Handler();
    final int TWO = 2000;
    public StroskiFragment s;

   public static Bundle ars = new Bundle();
    public static Bundle ars1 = new Bundle();

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.meni,menu);
        MenuItem item = menu.findItem(R.id.signal);
        item.setVisible(false);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       switch (item.getItemId()){
           case R.id.login:
              // Toast.makeText(this, "EMail", Toast.LENGTH_LONG).show();

               Intent i = new Intent(findViewById(R.id.ClaM).getContext(),login.class);
               startActivity(i);
               break;
           case R.id.register:
               Intent in = new Intent(findViewById(R.id.ClaM).getContext(),Registracija.class);
               startActivity(in);
               break;
           case R.id.naloziS:
                if(app.dobiLogin()){
                    app.uploadServer();

                }else{
                    Snackbar mySnackbar = Snackbar.make(findViewById(R.id.ClaM), "Ne moreš izvršiti tega dejanja (loginaj)", LENGTH_SHORT);
                    mySnackbar.show();
                }
               //naloži na splet zadnji fajl
               break;
           case R.id.Sinhronizacija:
               if(app.dobiLogin()){
                   app.potegniServer();

                   scheduleSendLocation();
                   handler.removeCallbacksAndMessages(null);
               }else{
                   Snackbar mySnackbar = Snackbar.make(findViewById(R.id.ClaM), "Ne moreš izvršiti tega dejanja (loginaj)", LENGTH_SHORT);
                   mySnackbar.show();
               }
               break;
           default:
               break;
       }
       return true;
    }

     public void refresh(){

    }
    public void scheduleSendLocation(){
        handler.postDelayed(new Runnable(){
            public void run(){
                refresh();
                handler.postDelayed(this,3000);
            }
        },3000);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        app = (ApplicationMy) getApplication();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
      //  setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);



        // Get the ViewPager and set it's PagerAdapter so that it can display items
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        pagerAdapter =
                new PagerAdapter(getSupportFragmentManager(), MainActivity.this);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOffscreenPageLimit(0);

        // Give the TabLayout the ViewPager
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

        // Iterate over all tabs and set the custom view
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            tab.setCustomView(pagerAdapter.getTabView(i));
            tab.setTag(pagerAdapter.tabTitles[i]);
        }


    }

    @Override
    public void onResume() {
        super.onResume();
        viewPager.getAdapter().notifyDataSetChanged();


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    /*  @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    } */

    class PagerAdapter extends FragmentPagerAdapter{
        String tabTitles[] = new String[] { "Potni stroški", "Vozila", "Osebe" };
        Context context;

        public PagerAdapter(FragmentManager fm, Context context) {
            super(fm);
            this.context = context;
        }

        @Override
        public int getCount() {
            return tabTitles.length;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    s = new StroskiFragment();
                    app.setListener(s);
                    return s;
                case 1:
                        //tu mamo zej vsa vozila
                        voziloFragment p = new voziloFragment();
                        return p;


                case 2:
                        // tu mo meli vse osebe
                        relacijaFragment r = new relacijaFragment();
                        return r;



            }

            return null;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            // Generate title based on item position
            return tabTitles[position];
        }

        public View getTabView(int position) {
            View tab = LayoutInflater.from(MainActivity.this).inflate(R.layout.custom_tab, null);
            TextView tv = (TextView) tab.findViewById(R.id.custom_text);
            tv.setText(tabTitles[position]);
            return tab;
        }
        @Override
        public int getItemPosition(Object object){
            return POSITION_NONE;
        }
    }
}
