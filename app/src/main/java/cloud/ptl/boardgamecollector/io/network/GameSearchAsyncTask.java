package cloud.ptl.boardgamecollector.io.network;

import android.os.AsyncTask;

import java.io.InputStream;

import cloud.ptl.boardgamecollector.Config;
import cloud.ptl.boardgamecollector.io.dto.GameSearchResult;

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
