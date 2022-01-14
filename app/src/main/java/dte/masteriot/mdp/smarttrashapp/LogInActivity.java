package dte.masteriot.mdp.smarttrashapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.JsonObject;

import org.json.JSONObject;

import okhttp3.OkHttpClient;
import retrofit2.Call;
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
        Call<JsonObject> resp = tbs.getToken(new Usuario(Username.getText().toString(), Password.getText().toString()));
        //This enqueues of the Callback means we are making
    }

    public interface ThingsboardService{

        @Headers({"Accept: application/json", "Content-Type: application/json"})
        @POST("auth/login")
        Call<JsonObject> getToken (@Body Usuario user);

        //Paper BIN
        @Headers({"Accept: application/json"})
        @GET("plugins/telemetry/DEVICE/5c0bf7a0-730d-11ec-9a04-591db17ccd5b/values/timeseries?keys=capacity")
        Call<JsonObject> getLatestTel (@Header("X-Authorization") String token,
                                       @Path("id") String id);
//
//        //Glass
//        @Headers({"Accept: application/json"})
//        @GET ("plugins/telemetry/DEVICE/41ff1d10-730d-11ec-9a04-591db17ccd5b/values/timeseries?keys=capacity")
//        Call<JsonObject> getLatestTel (@Header("X-Authorization") String token,
//                                       @Path ("id") String id);
//
//        //Plastic
//        @Headers({"Accept: application/json"})
//        @GET ("plugins/telemetry/DEVICE/2e4cd8c0-730d-11ec-9a04-591db17ccd5b/values/timeseries?keys=capacity")
//        Call<JsonObject> getLatestTel (@Header("X-Authorization") String token,
//                                       @Path ("id") String id);
//
//        //Organic
//        @Headers({"Accept: application/json"})
//        @GET ("plugins/telemetry/DEVICE/f9419f10-7309-11ec-9a04-591db17ccd5b/values/timeseries?keys=capacity")
//        Call<JsonObject> getLatestTel (@Header("X-Authorization") String token,
//                                       @Path ("id") String id);
    }

    public static class ServiceGenerator {
        private static final String BASE_URI = "https://srv-iot.diatel.upm.es/api/"; // "http://192.168.1.92:8080/api/"; //
        private static Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(BASE_URI)
                .client(new OkHttpClient()) //.Builder().addInterceptor((new HttpLoggingInterceptor()).setLevel(HttpLoggingInterceptor.Level.BODY)).build())
                .addConverterFactory(GsonConverterFactory.create());
        public static <S> S createService(Class <S> serviceClass) {
            Retrofit adapter = builder.build();
            return adapter.create(serviceClass);
        }
    }
}