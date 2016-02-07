package dk.com.nordea.locationsnearby;

import android.content.Context;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import dk.com.nordea.locationsnearby.impl.foursquare.FSVenueItem;

import static org.hamcrest.CoreMatchers.is;

/**
 * Created by erdal on 1.2.2016.
 */
public class VenueAdapterTest {

    @Test
    public void GetElementCount(){
        Context context = Mockito.mock(Context.class);
        List<VenueItem> venueItems = new ArrayList<VenueItem>();
        venueItems.add(new FSVenueItem("1", "Sushico", "San Fransisco", "130000"));
        VenueAdapter venueAdapter = new VenueAdapter(context, venueItems);

        Assert.assertNotNull(venueAdapter);

        int count = venueAdapter.getCount();

        Assert.assertThat(count, is(1));
    }
}
