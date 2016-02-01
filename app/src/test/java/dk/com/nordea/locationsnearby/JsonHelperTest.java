package dk.com.nordea.locationsnearby;

import android.support.v4.util.Pair;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by erdal on 1.2.2016.
 */
public class JsonHelperTest {

    @Test
    public void MakeJsonRequest(){

        try{
            String json = JsonHelper.GET(Constants.FOURSQUARE_BASE_URL
                    , Pair.create("v", Constants.FOURSQUARE_VERSION)
                    , Pair.create("client_id", Constants.FOURSQUARE_CLIENT_ID)
                    , Pair.create("client_secret", Constants.FOURSQUARE_CLIENT_SECRET)
                    , Pair.create("ll", "40.7" + "," + "-74")
                    , Pair.create("query", "sushi"));

            Assert.assertFalse(json.isEmpty());
        } catch (Exception ex){
            Assert.fail("Unexpected exception thrown");
        }
    }
}
