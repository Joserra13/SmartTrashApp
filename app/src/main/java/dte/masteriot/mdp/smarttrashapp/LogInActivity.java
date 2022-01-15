package dte.masteriot.mdp.smarttrashapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public class LogInActivity extends AppCompatActivity {

    Button bLogIn;

    EditText Username;
    EditText Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        bLogIn = findViewById(R.id.bLogIn);
        Username = findViewById(R.id.etUsername);
        Password = findViewById(R.id.etPassword);
    }

    public void loginFunction(View view) {

        ThingsboardService tbs = ServiceGenerator.createService(ThingsboardService.class);
        Call<JsonObject> resp = tbs.getToken(new Usuario(/*Username.getText().toString()*/"j.hoz@alumnos.upm.es", "9797979713Jrh#"/*Password.getText().toString()*/));
        //This enqueues of the Callback means we are making an asynchronous request (which won't block the UI-Thread)
        resp.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if(response.code() == 200){
                    try{
                        //Here we get the token from the response
                        String token = (new JSONObject(response.body().toString())).getString("token");
                        Log.d("RESPONSE::", "Starting activity... with token: " + token);

                        //Save the username and password for the current session

                        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("MyPref", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putString("user", Username.getText().toString());
                        editor.putString("pass", Password.getText().toString());
                        editor.putString("token", token);
                        editor.commit();

                        startActivity(new Intent(LogInActivity.this, MainActivity.class));

                    } catch (JSONException e) {
                        e.printStackTrace();
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
}