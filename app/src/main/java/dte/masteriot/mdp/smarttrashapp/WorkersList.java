package dte.masteriot.mdp.smarttrashapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class WorkersList extends AppCompatActivity{

    Button bParse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workers_list);

        bParse = findViewById(R.id.button3);

    }

    public void goTo(View view){

        Intent i = new Intent(WorkersList.this, WorkersIntro.class);
        startActivity(i);
    }


}