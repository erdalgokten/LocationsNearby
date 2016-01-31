package dk.com.nordea.locationsnearby;

/**
 * Created by erdal on 31.1.2016.
 */
public final class Constants {
    // Foursquare constants
    public static final String FOURSQUARE_CLIENT_ID = "<ClientID>";
    public static final String FOURSQUARE_CLIENT_SECRET = "<ClientSecret>";
    public static final String FOURSQUARE_BASE_URL = "https://api.foursquare.com/v2/venues/search";
    public static final String FOURSQUARE_VERSION = "20130815";

    // Constants to be used in JsonHelper class
    public static final char URL_WHITE_SPACE = ' ';
    public static final char URL_WHITE_SPACE_REPLACEMENT = '+';
    public static final char URL_KEY_VALUE_DELIMITER = '=';
    public static final char URL_PARAM_DELIMITER = '&';

    // The minimum distance to change Updates in meters
    public static final float LOC_MIN_DIST_BW_UPDATES = 1.0f; // 1 meter
    // The minimum time between updates in milliseconds
    public static final long LOC_MIN_TIME_BW_UPDATES = 10000; // 10 seconds

    // Minimum character count required to perform the search
    public static final int MIN_REQUIRED_CHAR_COUNT = 2;
}
