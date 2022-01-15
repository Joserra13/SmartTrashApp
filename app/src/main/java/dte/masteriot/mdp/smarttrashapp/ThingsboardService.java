package dte.masteriot.mdp.smarttrashapp;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ThingsboardService {

    @Headers({"Accept: application/json", "Content-Type: application/json"})
    @POST("auth/login")
    Call<JsonObject> getToken (@Body Usuario user);

    //Paper BIN
    @Headers({"Accept: application/json"})
    @GET("plugins/telemetry/DEVICE/5c0bf7a0-730d-11ec-9a04-591db17ccd5b/values/timeseries?keys=capacity")
    Call<JsonObject> getLatestPaperTel (@Header("X-Authorization") String token);

    //Glass
    @Headers({"Accept: application/json"})
    @GET ("plugins/telemetry/DEVICE/41ff1d10-730d-11ec-9a04-591db17ccd5b/values/timeseries?keys=capacity")
    Call<JsonObject> getLatestGlassTel (@Header("X-Authorization") String token);

    //Plastic
    @Headers({"Accept: application/json"})
    @GET ("plugins/telemetry/DEVICE/2e4cd8c0-730d-11ec-9a04-591db17ccd5b/values/timeseries?keys=capacity")
    Call<JsonObject> getLatestPlasticTel (@Header("X-Authorization") String token);

    //Organic
    @Headers({"Accept: application/json"})
    @GET ("plugins/telemetry/DEVICE/f9419f10-7309-11ec-9a04-591db17ccd5b/values/timeseries?keys=capacity")
    Call<JsonObject> getLatestOrganicTel (@Header("X-Authorization") String token);
}
