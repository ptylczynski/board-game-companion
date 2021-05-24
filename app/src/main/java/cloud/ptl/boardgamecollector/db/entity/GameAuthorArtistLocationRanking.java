package cloud.ptl.boardgamecollector.db.entity;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

public class GameAuthorArtistLocationRanking {
    @Embedded public Game game;

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

    @Relation(
            parentColumn = "gameId",
            entityColumn = "rankingEntryId",
            associateBy = @Junction(RankingGameCrossRef.class)
    )
    public List<RankingEntry> ranking;

    @Relation(
            parentColumn = "gameId",
            entityColumn = "gameLocatedId"
    )
    public Location location;
}
