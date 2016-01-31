package dk.com.nordea.locationsnearby.impl.mock;

import java.util.List;

import dk.com.nordea.locationsnearby.DataWorker;
import dk.com.nordea.locationsnearby.VenueItem;

/**
 * Created by erdal on 31.1.2016.
 */
public class MockDataWorker implements DataWorker {

    private static final String TAG = MockDataWorker.class.getName();

    @Override
    public boolean loadData(String query, double latitude, double longitude, List<VenueItem> venueItems) throws Exception {
        return true;
    }
}
