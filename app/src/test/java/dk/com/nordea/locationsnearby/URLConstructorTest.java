package dk.com.nordea.locationsnearby;

import android.support.v4.util.Pair;

import org.junit.Assert;
import org.junit.Test;

import java.net.URL;

/**
 * Created by erdal on 7.2.2016.
 */
public class URLConstructorTest {
    @Test
    public void MakeJsonRequest(){

        String expected = String.format("%s?v=20130815&ll=40.7,-74&query=sushi", Constants.FOURSQUARE_BASE_URL);
        try{
            URL url = URLConstructor.fromParameters(Constants.FOURSQUARE_BASE_URL
                    , Pair.create("v", Constants.FOURSQUARE_VERSION)
                    , Pair.create("ll", "40.7" + "," + "-74")
                    , Pair.create("query", "sushi"));

            Assert.assertEquals(expected, url.toString());
        } catch (Exception ex){
            Assert.fail("Unexpected exception thrown");
        }
    }
}
