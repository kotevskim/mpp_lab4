package martin.com.findpizzaapp.web;

import com.google.android.gms.location.places.PlaceBuffer;

import martin.com.findpizzaapp.model.ApiSearchResult;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by martin on 12/17/17.
 */

public interface GooglePlacesApi {

    @GET("json")
    Call<ApiSearchResult> getNearbyPlaces(@Query("type") String placeType,
                                      @Query("radius") String radius,
                                      @Query("location") String location,
                                      @Query("key") String apiKey);
}
