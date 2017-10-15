package com.example.root.car_control;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

import static com.example.root.car_control.R.drawable.connected_car;
import static com.example.root.car_control.R.drawable.cross_out_mark;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

       ImageView Status;
       TextView SpeedTextView;
       TextView RpmTextView;
       TextView ThrottleTextView;
       TextView CoolTempTextView;
       TextView MafPreTextView;

       private final Handler mHandler = new Handler();
       private Runnable mTimer2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setImageResource(R.drawable.return_main);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Intent i=new Intent(MainActivity.this,ServerCommunicationService.class);//start Communication with Service
        MainActivity.this.startService(i);

        Status= (ImageView)findViewById(R.id.imageView);
        Status.setImageResource(connected_car);

        SpeedTextView = (TextView)findViewById(R.id.textViewSpeed);
        RpmTextView=(TextView)findViewById(R.id.textViewRpm);
        ThrottleTextView=(TextView)findViewById(R.id.textViewΤhrottle);
        CoolTempTextView=(TextView)findViewById(R.id.textViewCoolantTemp);
        MafPreTextView=(TextView)findViewById(R.id.textViewPressure);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_map) {
            MapFragment mapFragment= new MapFragment();
            FragmentManager manager= getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.relative_layout_for_fragment,mapFragment,mapFragment.getTag()).commit();
            Toast.makeText(this,"Map",Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_speedometer) {
            SpeedometerFragment speedometerFragment=SpeedometerFragment.newInstance("some1","some2");
            FragmentManager manager= getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.relative_layout_for_fragment,speedometerFragment,speedometerFragment.getTag()).commit();
            Toast.makeText(this,"Speedometer",Toast.LENGTH_SHORT).show();}
        else if (id == R.id.nav_speed) {
            SpeedFragment speedFragment= SpeedFragment.newInstance("some1","some2");
            FragmentManager manager= getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.relative_layout_for_fragment,speedFragment,speedFragment.getTag()).commit();
            Toast.makeText(this,"Speed",Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_rpm) {
            RpmFragment rpmFragment= RpmFragment.newInstance("data1","data2");
            FragmentManager manager= getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.relative_layout_for_fragment,rpmFragment,rpmFragment.getTag()).commit();
            Toast.makeText(this,"RPM",Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_coolant_temperature) {
            TemperatureFragment temperatureFragment= TemperatureFragment.newInstance("data1","data2");
            FragmentManager manager= getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.relative_layout_for_fragment,temperatureFragment,temperatureFragment.getTag()).commit();
            Toast.makeText(this,"Coolant Temperature",Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_mafpressure) {
            PressureFragment pressureFragment= PressureFragment.newInstance("data1","data2");
            FragmentManager manager= getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.relative_layout_for_fragment,pressureFragment,pressureFragment.getTag()).commit();
            Toast.makeText(this,"MAF Pressure",Toast.LENGTH_SHORT).show();

        }else if (id == R.id.nav_throttle) {
            ThrottleFragment throttleFragment= ThrottleFragment.newInstance("data1","data2");
            FragmentManager manager= getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.relative_layout_for_fragment,throttleFragment,throttleFragment.getTag()).commit();
            Toast.makeText(this,"Throttle",Toast.LENGTH_SHORT).show();

        }else if (id == R.id.nav_share) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onResume() {
        super.onResume();
        mTimer2 = new Runnable() {
            @Override
            public void run() {

                Date currentDate = new Date(System.currentTimeMillis()+25200000); //+7 hours to fix timezone
                long current_time=currentDate.getTime();

                long lastMeasuremenent=getLastMeasurementDateTime().getTime();

                long diff= Math.abs(current_time-lastMeasuremenent);


                String temp;
                //Present Data on textviews
                Integer Cooltemp=getLastMeasurementTemp();
                Integer Speed=getLastMeasurementSpeed();
                Integer Rpm=getLastMeasurementRpm();
                Integer Throttle=getLastMeasurementThrottle();
                Integer MafPre=getLastMeasurementPressure();

                temp=String.valueOf(Speed);
                SpeedTextView.setTypeface(null,Typeface.BOLD);
                SpeedTextView.setText(temp);

                temp=String.valueOf(Rpm);
                RpmTextView.setTypeface(null,Typeface.BOLD);
                RpmTextView.setText(temp);

                temp=String.valueOf(Throttle);
                ThrottleTextView.setTypeface(null,Typeface.BOLD);
                ThrottleTextView.setText(temp);

                temp=String.valueOf(Cooltemp);
                CoolTempTextView.setTypeface(null,Typeface.BOLD);
                CoolTempTextView.setText(temp);

                temp=String.valueOf(MafPre);
                MafPreTextView.setTypeface(null,Typeface.BOLD);
                MafPreTextView.setText(temp);

               // Log.e("diff",Long.toString(diff));

                if(diff<=20000){
                Status.setImageResource(connected_car);}
                else
                {Status.setImageResource(cross_out_mark);}

                mHandler.postDelayed(this, 1000);
            }
        };
        mHandler.postDelayed(mTimer2, 1000); //every 1 sec
    }


    @Override
    public void onPause() {
        //mHandler.removeCallbacks(mTimer1);
        mHandler.removeCallbacks(mTimer2);
        super.onPause();
    }

    private Date getLastMeasurementDateTime() {
        ArrayList<Measurement> test = new ArrayList<>();
        test = ServerCommunicationService.getLivedata();
        Date i = test.get(test.size() - 1).getDateTime();
        return i;
    }

    private Integer getLastMeasurementSpeed(){
        ArrayList<Measurement> test = new ArrayList<>();
        test = ServerCommunicationService.getLivedata();
        Integer i=test.get(test.size() - 1).getSpeed();
        return i;
    }

    private Integer getLastMeasurementRpm(){
        ArrayList<Measurement> test = new ArrayList<>();
        test = ServerCommunicationService.getLivedata();
        Integer i=test.get(test.size() - 1).getRpm();
        return i;
    }

    private Integer getLastMeasurementThrottle(){
        ArrayList<Measurement> test = new ArrayList<>();
        test = ServerCommunicationService.getLivedata();
        Integer i=test.get(test.size() - 1).getThrottle();
        return i;
    }

    private Integer getLastMeasurementTemp(){
        ArrayList<Measurement> test = new ArrayList<>();
        test = ServerCommunicationService.getLivedata();
        Integer i=test.get(test.size() - 1).getCoolanTemperature();
        return i;
    }

    private Integer getLastMeasurementPressure(){
        ArrayList<Measurement> test = new ArrayList<>();
        test = ServerCommunicationService.getLivedata();
        Integer i=test.get(test.size() - 1).getMafPressure();
        return i;
    }
}





