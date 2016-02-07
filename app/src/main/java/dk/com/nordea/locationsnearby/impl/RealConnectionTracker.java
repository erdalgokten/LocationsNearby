package dk.com.nordea.locationsnearby.impl;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import dk.com.nordea.locationsnearby.ConnectionTracker;

/**
 * Created by erdal on 31.1.2016.
 */
public class RealConnectionTracker extends BroadcastReceiver implements ConnectionTracker {

    private static final String TAG = RealConnectionTracker.class.getName();

    private Activity activity;
    private boolean connected;

    public RealConnectionTracker(Activity activity){
        this.activity = activity;
        this.activity.registerReceiver(this, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        this.connected = assignConnection();
    }

    private boolean assignConnection(){
        ConnectivityManager cm = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

        Log.v(TAG, "Is Connected = " + isConnected);

        return isConnected;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        this.connected = assignConnection();
    }

    @Override
    public boolean isConnected() {
        return connected;
    }
}
