package dte.masteriot.mdp.smarttrashapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomerActivity extends AppCompatActivity {

    Button bPlas;
    Button bPaper;
    Button bOrga;
    Button bGlass;

    ImageView cStorage;

    TextView serviceToday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);

        bPlas = findViewById(R.id.bPlastic);
        bPaper = findViewById(R.id.bPaper);
        bOrga = findViewById(R.id.bOrganic);
        bGlass = findViewById(R.id.bGlass);
        cStorage = findViewById(R.id.imageView);
        serviceToday = findViewById(R.id.serviceToday);

    }

    public void showPlastic(View view) {

        cStorage.setImageResource(R.drawable.c25);
    }

    public void showPaper(View view) {

        cStorage.setImageResource(R.drawable.c50);
    }

    public void showOrganic(View view) {

        cStorage.setImageResource(R.drawable.c75);
    }

    public void showGlass(View view) {

        cStorage.setImageResource(R.drawable.c100);
    }
}