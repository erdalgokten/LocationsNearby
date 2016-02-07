package dk.com.nordea.locationsnearby;

import android.support.v4.util.Pair;

import java.net.URL;

/**
 * Created by erdal on 7.2.2016.
 */
public final class URLConstructor {
    public static URL fromParameters(String url, Pair<String, String>... params) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append(url);
        sb.append("?");
        for(int i = 0; i < params.length; i++){
            if (i != 0)
                sb.append(Constants.URL_PARAM_DELIMITER);
            sb.append(params[i].first.replace(Constants.URL_WHITE_SPACE, Constants.URL_WHITE_SPACE_REPLACEMENT));
            sb.append(Constants.URL_KEY_VALUE_DELIMITER);
            sb.append(params[i].second.replace(Constants.URL_WHITE_SPACE, Constants.URL_WHITE_SPACE_REPLACEMENT));
        }
        return new URL(sb.toString());
    }
}
