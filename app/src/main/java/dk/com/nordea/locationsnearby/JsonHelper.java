package dk.com.nordea.locationsnearby;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by erdal on 30.1.2016.
 */
public final class JsonHelper {

    private static final String TAG = JsonHelper.class.getName();

    public static String GET(URL url) throws Exception {
        StringBuilder sb = new StringBuilder();
        InputStreamReader ir = null;
        BufferedReader reader = null;
        try {
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
