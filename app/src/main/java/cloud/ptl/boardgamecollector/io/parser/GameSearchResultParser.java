package cloud.ptl.boardgamecollector.io.parser;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import cloud.ptl.boardgamecollector.io.dto.GameSearchResult;


public class GameSearchResultParser extends AbstractParser<GameSearchResult> {
    @Override
    public GameSearchResult parse(InputStream is) throws XmlPullParserException, IOException {
        GameSearchResult gameSearchResult = new GameSearchResult();
        gameSearchResult.setGameNames(new ArrayList<>());
        gameSearchResult.setIds(new ArrayList<>());
        XmlPullParser parser = Xml.newPullParser();
        parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
        parser.setInput(is, null);
        while(parser.next() != XmlPullParser.END_TAG){
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("name"))
                gameSearchResult.getGameNames().add(parser.getAttributeValue(null, "value"));
            if (name.equals("item"))
                gameSearchResult.getIds().add(
                        Integer.valueOf(
                                parser.getAttributeValue(null, "id")
                        )
                );
        }
        return gameSearchResult;
    }
}
