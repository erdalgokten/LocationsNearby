package dk.com.nordea.locationsnearby;

import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by erdal on 30.1.2016.
 */
public class AsyncHandler extends AsyncTask<Void, Void, Integer> {

    private static final String TAG = AsyncHandler.class.getName();

    private String query;
    private Refreshable refreshable;
    private List<VenueItem> venueItems = new ArrayList<VenueItem>();
    private LocationTracker locTracker;
    private DataWorker dataWorker;

    public AsyncHandler(String query, Refreshable callableContext, LocationTracker locTracker, DataWorker dataWorker){
        this.query = query;
        this.refreshable = callableContext;
        this.locTracker = locTracker;
        this.dataWorker = dataWorker;
    }

    protected void onPreExecute() {
        // do nothing
    }

    protected Integer doInBackground(Void... unused) {
        int result = 0;
        try {
            if (locTracker.canGetLocation())
                dataWorker.loadData(query, locTracker.getLatitude(), locTracker.getLongitude(), venueItems);
            else
                throw new Exception("Not able to get the location currently");
        } catch (Exception ex){
            result = 1;
            Log.e(TAG, "Load data error. Message: " + ex.getMessage() + ". StackTrace: " + ex.getStackTrace().toString());
        }

        return result;
    }

    protected void onPostExecute(Integer result) {
        if (isCancelled()){
            Log.v(TAG, "No need to refresh search results");
        } else {
            refreshable.refreshContents(result == 0, venueItems);
        }
    }
}
