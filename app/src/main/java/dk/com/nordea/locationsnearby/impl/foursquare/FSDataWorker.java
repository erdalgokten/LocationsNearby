package dk.com.nordea.locationsnearby.impl.foursquare;

import android.support.v4.util.Pair;

import org.json.JSONObject;

import java.net.URL;
import java.util.List;

import dk.com.nordea.locationsnearby.Constants;
import dk.com.nordea.locationsnearby.DataWorker;
import dk.com.nordea.locationsnearby.JsonHelper;
import dk.com.nordea.locationsnearby.URLConstructor;
import dk.com.nordea.locationsnearby.VenueItem;
import dk.com.nordea.locationsnearby.VenueItemFactory;

/**
 * Created by erdal on 31.1.2016.
 */
public class FSDataWorker implements DataWorker {

    private static final String TAG = FSDataWorker.class.getName();

    @Override
    public boolean loadData(String query, double latitude, double longitude, List<VenueItem> venueItems) throws Exception {

        URL url = URLConstructor.fromParameters(Constants.FOURSQUARE_BASE_URL
                , Pair.create("v", Constants.FOURSQUARE_VERSION)
                , Pair.create("client_id", Constants.FOURSQUARE_CLIENT_ID)
                , Pair.create("client_secret", Constants.FOURSQUARE_CLIENT_SECRET)
                , Pair.create("ll", latitude + "," + longitude)
                , Pair.create("query", query));

        String json = JsonHelper.GET(url);

        JSONObject joRaw = new JSONObject(json);

        venueItems.addAll(VenueItemFactory.fromJSONObjectToList(FSVenueItem.class, joRaw));

        return true;
    }
}
