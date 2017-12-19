package martin.com.findpizzaapp.loaders;


import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.google.android.gms.common.api.Api;
//import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import martin.com.findpizzaapp.R;
import martin.com.findpizzaapp.model.ApiSearchResult;
import martin.com.findpizzaapp.model.Place;
import martin.com.findpizzaapp.web.GooglePlacesApi;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NearbyPlacesLoader extends AsyncTaskLoader<List<Place>> {

    private String placeType;
    private String location;
    private long radius;

    public NearbyPlacesLoader(Context context, String placeType, LatLng latLng, long radius) {
        super(context);
        this.placeType = placeType;
        this.location = String.valueOf(latLng.latitude) + "," + String.valueOf(latLng.longitude);
        this.radius = radius;
    }

    @Override
    public List<Place> loadInBackground() {
        String apiBaseUrl = getContext().getString(R.string.google_places_api_base_url);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(apiBaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GooglePlacesApi webApi = retrofit.create(GooglePlacesApi.class);
        String apiKey = getContext().getString(R.string.google_maps_key);
        Call<ApiSearchResult> call = webApi.getNearbyPlaces(placeType, String.valueOf(radius), location, apiKey);
        List<Place> places = Collections.emptyList();
        try {
            ApiSearchResult result = call.execute().body();
            String status = result.getStatusMsg();
            places = result.getPlaces();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return places;
    }
}
