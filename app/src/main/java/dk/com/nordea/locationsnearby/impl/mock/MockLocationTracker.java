package dk.com.nordea.locationsnearby.impl.mock;

import dk.com.nordea.locationsnearby.LocationTracker;

/**
 * Created by erdal on 30.1.2016.
 */
public class MockLocationTracker implements LocationTracker {

    private static final String TAG = MockLocationTracker.class.getName();

    private boolean locationAvailable;
    private double latitude;
    private double longitude;

    public MockLocationTracker(boolean locationAvailable, double latitude, double longitude){
        this.locationAvailable = locationAvailable;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public boolean canGetLocation(){
        return locationAvailable;
    }

    @Override
    public double getLatitude() {
        return latitude;
    }

    @Override
    public double getLongitude() {
        return longitude;
    }
}
