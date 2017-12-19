package martin.com.findpizzaapp.model;

//import com.google.android.gms.location.places.Place;

import java.util.List;

public class ApiSearchResult {

    private List<Place> results;
    private String statusMsg;
    private String error_message;

    public List<Place> getPlaces() {
        return this.results;
    }

    public String getStatusMsg() {
        return this.statusMsg;
    }

    public String getErrorMsg() {
        return this.error_message;
    }
}
