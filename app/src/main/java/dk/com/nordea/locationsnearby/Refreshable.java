package dk.com.nordea.locationsnearby;

import java.util.List;

/**
 * Created by erdal on 30.1.2016.
 */
public interface Refreshable {
    void refreshContents(boolean isSuccessful, List<VenueItem> venueItems);
}
