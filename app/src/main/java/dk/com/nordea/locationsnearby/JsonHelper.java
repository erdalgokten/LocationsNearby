package dk.com.nordea.locationsnearby;

import android.support.v4.util.Pair;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by erdal on 30.1.2016.
 */
public final class JsonHelper {

    private static final String TAG = JsonHelper.class.getName();

    public static String GET(String url, Pair<String, String>... params) throws Exception {
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
        return sendAndReceive(sb.toString());
    }

    private static String sendAndReceive(String uri) throws Exception {
        StringBuilder sb = new StringBuilder();
        InputStreamReader ir = null;
        BufferedReader reader = null;
        try {
            URL url = new URL(uri);
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);

            ir = new InputStreamReader(conn.getInputStream());
            reader = new BufferedReader(ir);
            sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null)
                sb.append(line).append(Constants.URL_WHITE_SPACE);

        } catch (Exception ex) {
            throw ex;
        } finally {
            if (ir != null)
                ir.close();
            if (reader != null)
                reader.close();
        }
        return sb.toString();
    }
}
