package cloud.ptl.boardgamecollector.io.parser;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;

import cloud.ptl.boardgamecollector.db.entity.Game;
import cloud.ptl.boardgamecollector.io.dto.AbstractDTO;
import cloud.ptl.boardgamecollector.io.dto.GameSearchResult;


public class GameSearchResultParser extends AbstractParser<GameSearchResult> {
    @Override
    public GameSearchResult parse(InputStream is) throws XmlPullParserException, IOException {
        GameSearchResult gameSearchResult = new GameSearchResult();
        XmlPullParser parser = Xml.newPullParser();
        parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
        parser.setInput(is, null);
        while(parser.next() != XmlPullParser.END_TAG){
            String name = parser.getName();
            if (name.equals("name"))
                gameSearchResult.getGameName().add(parser.getText());
        }
        return gameSearchResult;
    }
}
