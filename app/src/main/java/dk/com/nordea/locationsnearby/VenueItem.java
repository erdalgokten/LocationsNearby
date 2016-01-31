package dk.com.nordea.locationsnearby;

/**
 * Created by erdal on 30.1.2016.
 */
public class VenueItem {
    private String venueId;
    private String venueName;
    private String venueAddress;
    private String venueDistance;

    public VenueItem(String id, String name, String address, String distance){
        venueId = id;
        venueName = name;
        venueAddress = address;
        venueDistance = distance;
    }

    public String getVenueId(){
        return venueId;
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
