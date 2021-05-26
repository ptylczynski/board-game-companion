package cloud.ptl.boardgamecollector.io.parser;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import cloud.ptl.boardgamecollector.db.entity.Artist;
import cloud.ptl.boardgamecollector.db.entity.Author;
import cloud.ptl.boardgamecollector.db.entity.Game;
import cloud.ptl.boardgamecollector.db.entity.Location;
import cloud.ptl.boardgamecollector.io.dto.GameDetailsDTO;

public class GameDataParser extends AbstractParser<GameDetailsDTO> {
    @Override
    public GameDetailsDTO parse(InputStream is) throws XmlPullParserException, IOException {
        List<Artist> artists = new ArrayList<>();
        List<Author> authors = new ArrayList<>();
        Game game = new Game();
        Location location = new Location();

        game.isAddon = false;
        game.isStandalone = false;
        game.description = "";

        XmlPullParser parser = Xml.newPullParser();
        parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
        parser.setInput(is, null);
        while(parser.next() != XmlPullParser.END_DOCUMENT){
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("name")) {
                if (parser.getAttributeValue(null, "type").equals("primary")) {
                    game.title = parser.getAttributeValue(null, "value");
                    game.orginalTitle = parser.getAttributeValue(null, "value");
                }
            }
            if (name.equals("description")) {
                if (parser.next() == XmlPullParser.TEXT)
                    game.description = parser.getText();
            }
            if (name.equals("yearpublished")) {
                game.productionDate = parser.getAttributeValue(null, "value");
            }
            if (name.equals("link")) {
                if (parser.getAttributeValue(null, "type").equals("boardgameartist")) {
                    Artist artist = new Artist();
                    artist.name = parser.getAttributeValue(null, "value").split(" ")[0];
                    artist.surname = parser.getAttributeValue(null, "value").split(" ")[1];
                    artists.add(artist);
                }
                if (parser.getAttributeValue(null, "type").equals("boardgamedesigner")) {
                    Author author = new Author();
                    author.name = parser.getAttributeValue(null, "value").split(" ")[0];
                    author.surname = parser.getAttributeValue(null, "value").split(" ")[1];
                    authors.add(author);
                }
                if (parser.getAttributeValue(null, "type").equals("boardgameexpansion")){
                    try {
                        if (parser.getAttributeValue(null, "inbound").equals("true")) {
                            game.isAddon = true;
                        }
                    }
                    catch (NullPointerException ex){
                        ex.printStackTrace();
                    }
                }
            }


        }
        if (!game.isAddon) game.isStandalone = true;
        GameDetailsDTO gameDetailsDTO = new GameDetailsDTO();
        gameDetailsDTO.setArtists(artists);
        gameDetailsDTO.setAuthors(authors);
        gameDetailsDTO.setGame(game);
        gameDetailsDTO.setLocation(location);
        return gameDetailsDTO;
    }
}
