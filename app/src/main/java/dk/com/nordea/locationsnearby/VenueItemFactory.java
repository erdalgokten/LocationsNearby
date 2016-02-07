package dk.com.nordea.locationsnearby;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by erdal on 7.2.2016.
 */
public final class VenueItemFactory {
    public static <T extends VenueItem> VenueItem fromJSONObject(Class<T> cls, JSONObject jo) throws Exception {
        JSONObject joLocation = jo.getJSONObject("location");

        T venueItem = (T)cls.getDeclaredConstructors()[0].newInstance(
                jo.getString("id"),
                jo.getString("name"),
                joLocation.has("address") && !joLocation.isNull("address") ? joLocation.getString("address") : "No Address Available",
                joLocation.has("distance") && !joLocation.isNull("distance") ? joLocation.getString("distance") + " meters far" : "No Distance Available");

        return venueItem;
    }

    public static <T extends VenueItem> List<VenueItem> fromJSONObjectToList(Class<T> cls, JSONObject jos) throws Exception {
        List<VenueItem> venueItems = new ArrayList<VenueItem>();
        JSONObject joResponse = jos.getJSONObject("response");
        JSONArray ja = joResponse.getJSONArray("venues");
        for (int i = 0; i < ja.length(); i++) {
            JSONObject jo = ja.getJSONObject(i);
            VenueItem venueItem = fromJSONObject(cls, jo);
            venueItems.add(venueItem);
        }
        return venueItems;
    }
}
