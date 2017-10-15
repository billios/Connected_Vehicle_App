package com.example.root.car_control;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class ServerCommunicationService extends Service {

    private final String LiveDataUrl="http://billioserver.dvrdns.org:500/livedata";//192.168.1.180/livedata
    public static ArrayList<Measurement> livedata;
    public static ArrayList<Vehicle> car;
    private String data;
    private Timer timer=new Timer();

    public ServerCommunicationService() {
    }

    public static ArrayList<Measurement> getLivedata() {
        return livedata;
    }

    public String getServiceData() {
        return data;
    }

    public String getLiveDataUrl() {
        return LiveDataUrl;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public String getContent(URL url){
        StringBuffer data=new StringBuffer(1024);
        try {
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            BufferedReader reader=new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String line = "";
            while ((line = reader.readLine()) != null) data.append(line).append("\n");
            reader.close();                       //close the reader

            return data.toString();
        }

        catch (Exception e) {
            e.printStackTrace();
            return null;

    }

}

    @Override
    public void onCreate() {
        super.onCreate();
        livedata=new ArrayList<>();
        car=new ArrayList<>();
        final String  urlString= new String(getLiveDataUrl());
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    URL url = new URL(urlString);
                     data = getContent(url);
                     insertLiveDataToMeasurement(data);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        },0,1000); //5 seconds delay time


    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
    }

    public void insertLiveDataToMeasurement(String data){

        try{
            JSONObject json=new JSONObject(data);

            String vehicleId=json.getString("v_id");
            String routeId=json.getString("r_id");

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date dateTime = simpleDateFormat.parse(json.getString("date_time"));


            Integer coolanTemperature=json.getInt("cool_temp");
            Integer mafPressure=json.getInt("maf_pre");
            Integer rpm=json.getInt("rpm");
            Integer speed=json.getInt("speed");
            Integer throttle=json.getInt("thr");

            Double lat=json.getDouble("lat");
            Double lon=json.getDouble("lon");

            LatLng coordinates=new LatLng(lat,lon);//coordinates.

            livedata.add(new Measurement(vehicleId,routeId,dateTime,coolanTemperature,mafPressure,rpm,speed,throttle,coordinates));

        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
