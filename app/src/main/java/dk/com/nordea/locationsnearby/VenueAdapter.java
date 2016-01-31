package dk.com.nordea.locationsnearby;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by erdal on 30.1.2016.
 */
public class VenueAdapter extends BaseAdapter {

    private static final String TAG = VenueAdapter.class.getName();

    private List<VenueItem> venueItems;
    private LayoutInflater layoutInflater;

    public VenueAdapter(Context context, List<VenueItem> venueItems){
        this.venueItems = venueItems;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return this.venueItems.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        ViewHolder holder;

        if (view == null) {
            view = layoutInflater.inflate(R.layout.venue_item, null);

            holder = new ViewHolder();
            holder.txtVenueName = (TextView) view.findViewById(R.id.txtVenueName);
            holder.txtVenueAddress = (TextView) view.findViewById(R.id.txtVenueAddress);
            holder.txtVenueDistance = (TextView) view.findViewById(R.id.txtVenueDistance);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        VenueItem venueItem = this.venueItems.get(position);
        if (venueItem != null) {
            if (holder.txtVenueName != null) {
                holder.txtVenueName.setText(venueItem.getVenueName());
            }
            if (holder.txtVenueAddress != null) {
                holder.txtVenueAddress.setText(venueItem.getVenueAddress());
            }
            if (holder.txtVenueDistance != null) {
                holder.txtVenueDistance.setText(venueItem.getVenueDistance());
            }
        }

        return view;
    }

    /**
     * Static class used to avoid the calling of "findViewById" every time the getView() method is called,
     * because this can impact to your application performance when your list is too big. The class is static so it
     * cache all the things inside once it's created.
     */
    private static class ViewHolder {
        protected TextView txtVenueName;
        protected TextView txtVenueAddress;
        protected TextView txtVenueDistance;
    }
}