package dk.com.nordea.locationsnearby;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import dk.com.nordea.locationsnearby.impl.foursquare.RealDataWorker;
import dk.com.nordea.locationsnearby.impl.mock.MockLocationTracker;

/**
 * Created by erdal on 1.2.2016.
 */
public class AsyncHandlerTest {

    private int count = -1;

    @Test
    public void getLocations(){
        String query = "sushi";
        Refreshable refreshable = new Refreshable() {
            @Override
            public void refreshContents(boolean isSuccessful, List<VenueItem> venueItems) {
                count = venueItems.size();
            }
        };
        LocationTracker mockLocationTracker = new MockLocationTracker(true, 40.7, -74);
        DataWorker realDataWorker = new RealDataWorker();
        AsyncHandler handler = new AsyncHandler(query, refreshable, mockLocationTracker, realDataWorker);
        handler.execute();
        try {
            handler.wait();
            Assert.assertTrue(count == -1);
        } catch (Exception ex){
            Assert.fail("Unexpected exception thrown");
        }
    }
}
