package dk.com.nordea.locationsnearby.impl.foursquare;

import android.support.v4.util.Pair;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import dk.com.nordea.locationsnearby.DataWorker;
import dk.com.nordea.locationsnearby.JsonHelper;
import dk.com.nordea.locationsnearby.VenueItem;

/**
 * Created by erdal on 31.1.2016.
 */
public class RealDataWorker implements DataWorker {

    private static final String TAG = RealDataWorker.class.getName();

    @Override
    public boolean loadData(String query, double latitude, double longitude, List<VenueItem> venueItems) throws Exception {

        String json = JsonHelper.GET(Constants.FOURSQUARE_BASE_URL
                , Pair.create("v", Constants.FOURSQUARE_VERSION)
                , Pair.create("client_id", Constants.FOURSQUARE_CLIENT_ID)
                , Pair.create("client_secret", Constants.FOURSQUARE_CLIENT_SECRET)
                , Pair.create("ll", latitude + "," + longitude)
                , Pair.create("query", query));

        JSONObject joRaw = new JSONObject(json);
        JSONObject joResponse = joRaw.getJSONObject("response");
        JSONArray ja = joResponse.getJSONArray("venues");
        for (int i = 0; i < ja.length(); i++) {
            JSONObject jo = ja.getJSONObject(i);
            JSONObject joLocation = jo.getJSONObject("location");
            VenueItem venueItem = new VenueItem(
                    jo.getString("id"),
                    jo.getString("name"),
                    joLocation.has("address") && !joLocation.isNull("address") ? joLocation.getString("address") : "No Address Available",
                    joLocation.has("distance") && !joLocation.isNull("distance") ? joLocation.getString("distance") + " meters far" : "No Distance Available");
            venueItems.add(venueItem);
        }

        return true;
    }
}
