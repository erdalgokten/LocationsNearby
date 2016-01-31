package dk.com.nordea.locationsnearby.impl.mock;

import dk.com.nordea.locationsnearby.ConnectionTracker;

/**
 * Created by erdal on 31.1.2016.
 */
public class MockConnectionTracker implements ConnectionTracker {

    private static final String TAG = MockConnectionTracker.class.getName();

    private boolean connected;

    public MockConnectionTracker(boolean connected){
        this.connected = connected;
    }

    @Override
    public boolean isConnected() {
        return connected;
    }
}
