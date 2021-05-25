package cloud.ptl.boardgamecollector.io.dto;

import java.util.List;

import cloud.ptl.boardgamecollector.db.entity.Artist;
import cloud.ptl.boardgamecollector.db.entity.Author;
import cloud.ptl.boardgamecollector.db.entity.Game;
import cloud.ptl.boardgamecollector.db.entity.Location;
import lombok.Data;

@Data
public class GameDetailsDTO extends AbstractDTO {
    private Location location;
    private Game game;
    private List<Artist> artists;
    private List<Author> authors;
}
