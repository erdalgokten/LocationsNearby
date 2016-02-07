package dk.com.nordea.locationsnearby;

/**
 * Created by erdal on 7.2.2016.
 */
public abstract class VenueItem {
    protected String venueID;
    protected String venueName;
    protected String venueAddress;
    protected String venueDistance;

    public VenueItem(String id, String name, String address, String distance){
        venueID = id;
        venueName = name;
        venueAddress = address;
        venueDistance = distance;
    }

    public String getVenueID(){
        return venueID;
    }
    public String getVenueName(){
        return venueName;
    }
    public String getVenueAddress(){
        return venueAddress;
    }
    public String getVenueDistance(){
        return venueDistance;
    }
}
