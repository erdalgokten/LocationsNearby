package dk.com.nordea.locationsnearby.impl;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import dk.com.nordea.locationsnearby.Constants;
import dk.com.nordea.locationsnearby.LocationTracker;

/**
 * Created by erdal on 30.1.2016.
 */
public class RealLocationTracker implements LocationTracker, LocationListener {

    private static final String TAG = RealLocationTracker.class.getName();

    private Activity activity;
    private LocationManager locationManager;
    private Location latestLocation;

    public RealLocationTracker(Activity activity) {
        this.activity = activity;
        locationManager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);

        if (!assignLocationProvider()){
            Toast.makeText(activity, "Please check your location provider", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean assignLocationProvider(){
        if (!tryNetworkProvider() && !tryPassiveProvider() && !tryGPSProvider()){
            // no provider could be set
            return false;
        }
        return true;
    }

    private boolean tryNetworkProvider(){
        boolean isSet = false;
        if(locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            Log.v(TAG, "isNetworkEnabled = true");
            if (ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
            }
            if (ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
                    ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                latestLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, Constants.LOC_MIN_TIME_BW_UPDATES, Constants.LOC_MIN_DIST_BW_UPDATES, this);
                Log.v(TAG, "Network Provider is set for location manager");
                isSet = true;
            }
        }
        return isSet;
    }

    private boolean tryPassiveProvider(){
        boolean isSet = false;
        if (locationManager.isProviderEnabled(LocationManager.PASSIVE_PROVIDER)){
            Log.v(TAG, "isPassiveEnabled = true");
            if (ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 2);
            }
            if (ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                latestLocation = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
                locationManager.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER, Constants.LOC_MIN_TIME_BW_UPDATES, Constants.LOC_MIN_DIST_BW_UPDATES, this);
                Log.v(TAG, "Passive Provider is set for location manager");
                isSet = true;
            }
        }
        return isSet;
    }

    private boolean tryGPSProvider(){
        boolean isSet = false;
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            Log.v(TAG, "isGPSEnabled = = true");
            if (ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 999);
            }
            if (ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                latestLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, Constants.LOC_MIN_TIME_BW_UPDATES, Constants.LOC_MIN_DIST_BW_UPDATES, this);
                Log.v(TAG, "GPS Provider is set for location manager");
                isSet = true;
            }
        }
        return isSet;
    }

    @Override
    public void onLocationChanged(Location location) {
        this.latestLocation = location;
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {
        if (!assignLocationProvider()){
            latestLocation = null;
            Toast.makeText(activity, "Please check your location provider", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onProviderDisabled(String provider) {
        if (!assignLocationProvider()){
            latestLocation = null;
            Toast.makeText(activity, "Please check your location provider", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean canGetLocation(){
        return latestLocation != null;
    }

    @Override
    public double getLatitude() {
        return latestLocation.getLatitude();
    }

    @Override
    public double getLongitude() {
        return latestLocation.getLongitude();
    }
}
