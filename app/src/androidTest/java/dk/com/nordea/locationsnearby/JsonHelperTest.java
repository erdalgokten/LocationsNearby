package dk.com.nordea.locationsnearby;

import android.support.v4.util.Pair;

import junit.framework.Assert;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

/**
 * Created by erdal on 31.1.2016.
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
