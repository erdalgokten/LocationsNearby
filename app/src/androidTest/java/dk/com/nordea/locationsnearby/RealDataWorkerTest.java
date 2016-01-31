package dk.com.nordea.locationsnearby;

import android.support.v4.util.Pair;

import junit.framework.Assert;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import dk.com.nordea.locationsnearby.VenueItem;
import dk.com.nordea.locationsnearby.impl.foursquare.RealDataWorker;

/**
 * Created by erdal on 31.1.2016.
 */
public class RealDataWorkerTest {

    @Test
    public void MakeJsonRequest(){

        List<VenueItem> venueItems = new ArrayList<VenueItem>();
        RealDataWorker worker = new RealDataWorker();

        try{
            worker.loadData("sushi", 40.7, -74, venueItems);

            Assert.assertTrue(venueItems.size() > 0);
        } catch (Exception ex){
            Assert.fail("Unexpected exception thrown");
        }
    }
}
