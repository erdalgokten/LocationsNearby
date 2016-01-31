package dk.com.nordea.locationsnearby;

import java.util.List;

/**
 * Created by erdal on 31.1.2016.
 */
public interface DataWorker {
    boolean loadData(String query, double latitude, double longitude, List<VenueItem> venueItems) throws Exception;
}
