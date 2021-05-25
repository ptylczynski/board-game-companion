package cloud.ptl.boardgamecollector.io.network;

import android.os.AsyncTask;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;

import cloud.ptl.boardgamecollector.Config;
import cloud.ptl.boardgamecollector.io.dto.GameSearchResult;
import cloud.ptl.boardgamecollector.io.parser.GameSearchResultParser;

public class GameSearchAsyncTask extends AsyncTask<String, Integer, GameSearchResult> {
    @Override
    protected GameSearchResult doInBackground(String... strings) {
        InputStream is = Connector.fetch(
                String.format(
                        "%s/search?query=%s&type=boardgame",
                        Config.BGG_API_URL,
                        strings[0]
                )
        );
        try {
            return new GameSearchResultParser().parse(is);
        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
