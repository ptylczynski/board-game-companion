package cloud.ptl.boardgamecollector.io.network;

import android.os.AsyncTask;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;

import cloud.ptl.boardgamecollector.Config;
import cloud.ptl.boardgamecollector.io.dto.UserGamesDTO;
import cloud.ptl.boardgamecollector.io.parser.UserGamesParser;

public class UserGamesFetchAsyncTask extends AsyncTask<String, Integer, UserGamesDTO> {
    @Override
    protected UserGamesDTO doInBackground(String... strings) {
        InputStream is = Connector.fetch(
                String.format(
                        "%s/collection?username=%s&stats=1",
                        Config.BGG_API_URL,
                        strings[0]
                )
        );
        try {
            return new UserGamesParser().parse(is);
        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
