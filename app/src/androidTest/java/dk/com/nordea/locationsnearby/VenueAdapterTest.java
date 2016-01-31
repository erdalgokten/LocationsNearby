package dk.com.nordea.locationsnearby;

import android.content.Context;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import dk.com.nordea.locationsnearby.VenueAdapter;
import dk.com.nordea.locationsnearby.VenueItem;

import static org.mockito.Mockito.when;

/**
 * Created by erdal on 31.1.2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class VenueAdapterTest {

    @Mock
    Context mockContext;

    @Test
    public void GetElementCount(){
        List<VenueItem> venueItems = new ArrayList<VenueItem>();
        venueItems.add(new VenueItem("1", "Sushico", "San Fransisco", "130000"));
        VenueAdapter venueAdapter = new VenueAdapter(mockContext, venueItems);
        int count = venueAdapter.getCount();

        Assert.assertTrue(count == 1);
    }
}
