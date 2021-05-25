package cloud.ptl.boardgamecollector.io.network;

import android.os.AsyncTask;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;

import cloud.ptl.boardgamecollector.Config;
import cloud.ptl.boardgamecollector.io.dto.GameDetailsDTO;
import cloud.ptl.boardgamecollector.io.parser.GameDataParser;
import cloud.ptl.boardgamecollector.io.parser.GameSearchResultParser;

public class GameDetailsFetchAsyncTask extends AsyncTask<String, Integer, GameDetailsDTO> {
    @Override
    protected GameDetailsDTO doInBackground(String... strings) {
        InputStream is = Connector.fetch(
                String.format(
                        "%s/thing?id=%s&stats=1",
                        Config.BGG_API_URL,
                        strings[0]
                )
        );
        try {
            return new GameDataParser().parse(is);
        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
