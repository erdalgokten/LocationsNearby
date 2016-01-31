package dk.com.nordea.locationsnearby;

import android.app.Activity;

/**
 * Created by erdal on 30.1.2016.
 */
public interface LocationTracker {
    boolean canGetLocation();
    double getLatitude();
    double getLongitude();
}
