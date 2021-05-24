package cloud.ptl.boardgamecollector.db.entity;

import androidx.room.Entity;
import androidx.room.Junction;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Game {
    @PrimaryKey
    public int gameId;

    public String title;
    public String orginalTitle;
    public Integer productionDate;

    @Relation(
            parentColumn = "gameId",
            entityColumn = "authorId",
            associateBy = @Junction(AuthorGameCrossRef.class)
    )
    public List<Author> authors;

    @Relation(
            parentColumn = "gameId",
            entityColumn = "artistId",
            associateBy = @Junction(ArtistGameCrossRef.class)
    )
    public List<Artist> artists;

    public String description;
    public LocalDate orderDate;
    public LocalDate toCollectionAddDate;
    public Double buyPrice;
    public Double MSRP;
    public String EAN;
    public String BGGIdentifier;
    public String productionCode;

    @Relation(
            parentColumn = "gameId",
            entityColumn = "rankingEntryId",
            associateBy = @Junction(RankingGameCrossRef.class)
    )
    public List<RankingEntry> ranking;

    public Boolean isStandalone;
    public Boolean isAddon;
    public String comment;

    @Relation(
            parentColumn = "gameId",
            entityColumn = "gameLocatedId"
    )
    public Location location;
}
