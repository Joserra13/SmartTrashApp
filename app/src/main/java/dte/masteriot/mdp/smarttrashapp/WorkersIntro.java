package dte.masteriot.mdp.smarttrashapp;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WorkersIntro extends AppCompatActivity implements ItemViewHolder.ItemClickListener{

    ArrayList<String> containerNames_ArrayList = new ArrayList<String>();

    private static final List<Item> listOfItems = new ArrayList<>();

    private RecyclerView myRecycleView;
    private ItemAdapter myAdapter;

    Button bParse;

    TextView parseo;

    Call<JsonObject> containerData;

    int organic, plastic, paper, glass;
    float temp, hum, xAxis, yAxis, zAxis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workers_intro);

        myRecycleView = findViewById(R.id.recyclerView);
        myAdapter = new ItemAdapter(listOfItems, this);
        myRecycleView.setAdapter(myAdapter);
        myRecycleView.setLayoutManager(new LinearLayoutManager(this));

        bParse = findViewById(R.id.button3);
        parseo = findViewById(R.id.textView3);

        parseJSON();

    }

    public void parseJSON() {

        listOfItems.clear();

        String containerNames = "";

        try {
            // get JSONObject from JSON file
            JSONObject json_obj = new JSONObject(loadJSONFromAsset());
            // fetch JSONObject named employee
            JSONArray graph = json_obj.getJSONArray("@graph");

            int nContainers = graph.length();

            for(int i = 0; i < nContainers; i++) {

                JSONObject eachContainer = graph.getJSONObject(i);
                LatLng location = null;
                // get container's name
                containerNames = eachContainer.getString("title");
                int punto = containerNames.indexOf(".");
                containerNames = containerNames.substring(punto + 2);
                containerNames_ArrayList.add(containerNames);

                try {
                    // get container's location
                    JSONObject locationNode = eachContainer.getJSONObject("location");
                    location = new LatLng(
                            locationNode.getDouble("latitude"),
                            locationNode.getDouble("longitude"));
                }catch (JSONException ignored){

                }

                //Plastic
                //getStreetContainersData(0);
                //Paper
                //getStreetContainersData(1);
                //Organic
                //getStreetContainersData(2);
                //Glass
                //getStreetContainersData(3);
                //Temp
                //getStreetContainersData(4);
                //Hum
                //getStreetContainersData(5);


                listOfItems.add(new Item(containerNames, location, 0, 0, 0, 0, 0, 0, 0, 0, 0));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        myAdapter.notifyDataSetChanged();
    }

    private void getStreetContainersData(int choice) {

        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        String token = sharedPref.getString("token", "defToken");
        token = "Bearer " + token;
        ThingsboardService tbs = ServiceGenerator.createService(ThingsboardService.class);

        //Llamada get the info
        if(choice == 0){
            //Plastic
            //containerData = tbs.getPlasticStreetContainersLatestTelemetry(token);
        }else if(choice == 1){
            //Paper
            //containerData = tbs.getPaperStreetContainersLatestTelemetry(token);
        }else if(choice == 2){
            //Organic
            //containerData = tbs.getOrganicStreetContainersLatestTelemetry(token);
        }else if(choice == 3){
            //Glass
            //containerData = tbs.getGlassStreetContainersLatestTelemetry(token);
        }else if(choice == 4){
            //Temp
            //containerData = tbs.getTempStreetContainersLatestTelemetry(token);
        }else if(choice == 5){
            //Hum
            //containerData = tbs.getHumStreetContainersLatestTelemetry(token);
        }

        //This enqueues of the Callback means we are making an asynchronous request (which won't block the UI-Thread)
        containerData.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if(response.code() == 200){
                    //Parse de JSON to obtain the container's telemetry
                    if(choice == 0){
                        //Plastic
                    }else if(choice == 1){
                        //Paper
                    }else if(choice == 2){
                        //Organic
                    }else if(choice == 3){
                        //Glass
                    }else if(choice == 4){
                        //Temp
                    }else if(choice == 5){
                        //Hum
                    }

                } else{
                    Log.d("ERROR with code: ", String.valueOf(response.code()));
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.d("RESPONSE::ERROR", "It did not work");
            }
        });
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("MapaContenedores.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    @Override
    public void onItemClick(int position, View v) {

        Item container = listOfItems.get(position);

        //Toast.makeText(this, String.valueOf(item.getLocation()), Toast.LENGTH_SHORT).show();
        Intent i = new Intent(WorkersIntro.this,MapsActivity.class);

        i.putExtra("containerName", String.valueOf(container.getDisplayText()));
        i.putExtra("location", String.valueOf(container.getLocation()));
        i.putExtra("orgLevel", String.valueOf(container.getOrgLevel()));
        i.putExtra("plasLevel", String.valueOf(container.getPlaLevel()));
        i.putExtra("papLevel", String.valueOf(container.getPapLevel()));
        i.putExtra("glaLevel", String.valueOf(container.getGlaLevel()));
        i.putExtra("temp", String.valueOf(container.getTemp()));
        i.putExtra("hum", String.valueOf(container.getHum()));
        i.putExtra("xAxis", String.valueOf(container.getX()));
        i.putExtra("yAxis", String.valueOf(container.getY()));
        i.putExtra("zAxis", String.valueOf(container.getZ()));

        startActivity(i);
    }

    public void goTo(View view){

        Intent i = new Intent(WorkersIntro.this,MapsActivity.class);
        startActivity(i);
    }
}