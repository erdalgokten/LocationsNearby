package dk.com.nordea.locationsnearby;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import dk.com.nordea.locationsnearby.impl.foursquare.RealConnectionTracker;
import dk.com.nordea.locationsnearby.impl.foursquare.RealDataWorker;
import dk.com.nordea.locationsnearby.impl.foursquare.RealLocationTracker;

/**
 * Created by erdal on 30.1.2016.
 */
public class SearchActivity extends AppCompatActivity implements SearchView.OnQueryTextListener,
        SearchView.OnCloseListener, Refreshable {

    private static final String TAG = SearchActivity.class.getName();

    private SearchView searchView;
    private ListView listView;
    private VenueAdapter venueAdapter;
    private List<VenueItem> venueItems;
    private AsyncHandler asyncHandler = null;
    private LocationTracker locTracker = null;
    private ConnectionTracker connTracker = null;
    private DataWorker dataWorker = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_search);

        locTracker = new RealLocationTracker(this);
        connTracker = new RealConnectionTracker(this);
        dataWorker = new RealDataWorker();

        searchView = (SearchView) findViewById(R.id.searchView);
        listView = (ListView) findViewById(R.id.listView);

        venueItems = new ArrayList<VenueItem>();
        venueAdapter = new VenueAdapter(SearchActivity.this, venueItems);
        listView.setAdapter(venueAdapter);

        searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener(this);
        searchView.setOnCloseListener(this);
        searchView.setQueryHint(getResources().getString(R.string.search_hint));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onClose() {
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        Log.v(TAG, "Query text submitted");
        searchView.clearFocus();
        boolean result = false;
        if (ValidateResources())
            result = performSearch(query);

        return result;
    }

    private boolean ValidateResources(){
        boolean isValidated = true;
        if (!connTracker.isConnected() && !locTracker.canGetLocation()){
            isValidated = false;
            Log.v(TAG, "Connection and location error message will be shown to the user");
            Toast.makeText(this, "Please check your network connection and location provider settings", Toast.LENGTH_SHORT).show();
        } else if (!connTracker.isConnected()){
            isValidated = false;
            Log.v(TAG, "Connection error message will be shown to the user");
            Toast.makeText(this, "Please check your network connection", Toast.LENGTH_SHORT).show();
        } else if (!locTracker.canGetLocation()){
            isValidated = false;
            Log.v(TAG, "Location error message will be shown to the user");
            Toast.makeText(this, "Please check your location provider settings", Toast.LENGTH_SHORT).show();
        }
        return isValidated;
    }

    @Override
    public boolean onQueryTextChange(String query) {
        return performSearch(query);
    }

    private boolean performSearch(String query){
        if (query.trim().length() > Constants.MIN_REQUIRED_CHAR_COUNT) {
            if (asyncHandler != null)
                asyncHandler.cancel(true);

            if (connTracker.isConnected() && locTracker.canGetLocation())
                asyncHandler = (AsyncHandler) new AsyncHandler(query, this, locTracker, dataWorker).execute();
        } else {
            venueItems.clear();
            venueAdapter.notifyDataSetChanged();
        }
        return false;
    }

    @Override
    public void refreshContents(List<VenueItem> venueItems) {
        this.venueItems.clear();
        this.venueItems.addAll(venueItems);
        venueAdapter.notifyDataSetChanged();
    }
}
