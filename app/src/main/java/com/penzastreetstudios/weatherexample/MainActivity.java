package com.penzastreetstudios.weatherexample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.gson.reflect.TypeToken;
import com.penzastreetstudios.weatherexample.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private TextView tempText;
    public Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //tempText = (TextView) findViewById(R.id.temp_txt);
        registerReceiver(receiver, new IntentFilter(GisService.CHANNEL));
        Intent intent = new Intent(this, GisService.class);
        startService(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent intent = new Intent(this, GisService.class);
        stopService(intent);
    }


    protected BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            try {
                JSONObject json = new JSONObject(intent.getStringExtra(GisService.INFO));
                JSONArray gisArray = json.getJSONArray("gis");

                //Type type = new Collection<Weather>();

                Type listOfWeather = new TypeToken<ArrayList<Weather>>() {}.getType();

                Gson gson = new Gson();
                List<Weather> list = gson.fromJson(gisArray.toString(), listOfWeather);

                ArrayList<WeatherFragment> fragments = new ArrayList<>();
                fragments.add(new WeatherFragment(list.get(0)));
                for (int i = 0; i < 4; i++) {
                    fragments.add(new WeatherFragment(list.get(i)));
                }

                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();

                transaction.replace(R.id.fullDayFragment, fragments.get(0));
                transaction.replace(R.id.morningFragment, fragments.get(1));
                transaction.replace(R.id.dayFragment, fragments.get(2));
                transaction.replace(R.id.eveningFragment, fragments.get(3));
                transaction.replace(R.id.nightFragment, fragments.get(4));

                transaction.commit();

            } catch (JSONException e) {
                Toast.makeText(MainActivity.this, "Wring JSON format", Toast.LENGTH_LONG).show();
            }
        }
    };
}