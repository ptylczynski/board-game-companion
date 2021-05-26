package cloud.ptl.boardgamecollector.io.parser;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import cloud.ptl.boardgamecollector.io.dto.GameDetailsDTO;
import cloud.ptl.boardgamecollector.io.dto.UserGamesDTO;
import cloud.ptl.boardgamecollector.io.network.GameDetailsFetchAsyncTask;
import lombok.SneakyThrows;

public class UserGamesParser extends AbstractParser<UserGamesDTO> {
    @SneakyThrows
    @Override
    public UserGamesDTO parse(InputStream is) throws XmlPullParserException, IOException {
        UserGamesDTO userGamesDTO = new UserGamesDTO();
        userGamesDTO.setGamesID(new ArrayList<>());
        userGamesDTO.setNames(new ArrayList<>());
        XmlPullParser parser = Xml.newPullParser();
        parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
        parser.setInput(is, null);
        while(parser.next() != XmlPullParser.END_DOCUMENT) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("item"))
                if(parser.getAttributeValue(null, "subtype").equals("boardgame")){
                    Long id = Long.valueOf(parser.getAttributeValue(null, "objectid"));
                    userGamesDTO.getGamesID().add(id);
                }
            if (name.equals("name")){
                if (parser.next() == XmlPullParser.TEXT)
                    userGamesDTO.getNames().add(parser.getText());
            }
        }
        return userGamesDTO;
    }
}
