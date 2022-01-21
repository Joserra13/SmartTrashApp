package dte.masteriot.mdp.smarttrashapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ContainerActivity extends AppCompatActivity {

    Intent inputIntent;
    String name, orgLevel, plasLevel, papLevel, glaLevel, temp, hum, xAxis, yAxis, zAxis;
    TextView containerName, organicLevel, plasticLevel, paperLevel, glassLevel, temperature, humidity, x_Axis, y_Axis, z_Axis, orientation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);

        //Getting the Intent
        inputIntent = getIntent();

        //Getting the Values coming from First Activity extracting them from the Intent received
        name = inputIntent.getStringExtra("containerName");
        orgLevel = inputIntent.getStringExtra("orgLevel");
        plasLevel = inputIntent.getStringExtra("plasLevel");
        papLevel = inputIntent.getStringExtra("papLevel");
        glaLevel = inputIntent.getStringExtra("glaLevel");

        temp = inputIntent.getStringExtra("temp");
        hum = inputIntent.getStringExtra("hum");

        xAxis = inputIntent.getStringExtra("xAxis");
        yAxis = inputIntent.getStringExtra("yAxis");
        zAxis = inputIntent.getStringExtra("zAxis");

        containerName = findViewById(R.id.textView1);
        organicLevel = findViewById(R.id.textView7);
        plasticLevel = findViewById(R.id.textView3);
        paperLevel = findViewById(R.id.textView5);
        glassLevel = findViewById(R.id.textView9);

        temperature = findViewById(R.id.textView11);
        humidity = findViewById(R.id.textView13);

        x_Axis = findViewById(R.id.textView15);
        y_Axis = findViewById(R.id.textView17);
        z_Axis = findViewById(R.id.textView19);

        orientation = findViewById(R.id.textView21);

        containerName.setText(name);
        organicLevel.setText(orgLevel);
        plasticLevel.setText(plasLevel);
        paperLevel.setText(papLevel);
        glassLevel.setText(glaLevel);

        temperature.setText(temp);
        humidity.setText(hum);

        x_Axis.setText(xAxis);
        y_Axis.setText(yAxis);
        z_Axis.setText(zAxis);

        if((Float.parseFloat(xAxis) < 1 && Float.parseFloat(xAxis) > -1) && (Float.parseFloat(zAxis) < 1 && Float.parseFloat(zAxis) > -1)){
            //Vertical
            orientation.setText("Vertical");
        }else if((Float.parseFloat(yAxis) < 1 && Float.parseFloat(yAxis) > -1) && (Float.parseFloat(zAxis) < 1 && Float.parseFloat(zAxis) > -1)){
            //Hacia un lado
            orientation.setText("Sideways");
        }else if((Float.parseFloat(yAxis) < 1 && Float.parseFloat(yAxis) > -1) && (Float.parseFloat(xAxis) < 1 && Float.parseFloat(xAxis) > -1)){
            //Hacia alante o hacia atrás
            orientation.setText("Forwards/Backwards");
        }
    }
}