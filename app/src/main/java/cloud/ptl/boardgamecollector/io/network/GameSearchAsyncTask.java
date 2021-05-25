package cloud.ptl.boardgamecollector.io.network;

import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import cloud.ptl.boardgamecollector.Config;

public class GameSearchAsyncTask extends AsyncTask<String, Integer, GameSearchResult> {
    @Override
    protected GameSearchResult doInBackground(String... strings) {
        InputStream is = Connector.fetch(
                String.format(
                        "%s/search?query=%s&type=boardgame",
                        Config.BGG_API_URL,
                        strings[0]
        );
        return null;
    }
}
