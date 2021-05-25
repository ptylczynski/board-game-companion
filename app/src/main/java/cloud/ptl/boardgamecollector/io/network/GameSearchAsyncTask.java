package cloud.ptl.boardgamecollector.io.network;

import android.os.AsyncTask;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import cloud.ptl.boardgamecollector.Config;

public class GameSearchAsyncTask extends AsyncTask<String, Integer, GameSearchResult> {
    @Override
    protected GameSearchResult doInBackground(String... strings) {
        try {
            URL url = new URL(
                    String.format(
                            "%s/search?query=%s&type=boardgame",
                            Config.BGG_API_URL,
                            strings[0]
                    )
            );
            HttpURLConnection httpURLConnection =
                    (HttpURLConnection) url.openConnection();
            httpURLConnection.setReadTimeout(3000);
            httpURLConnection.setConnectTimeout(3000);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setDoInput(true);
            httpURLConnection.connect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
