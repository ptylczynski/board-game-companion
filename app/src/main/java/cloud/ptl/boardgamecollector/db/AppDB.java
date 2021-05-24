package cloud.ptl.boardgamecollector.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import cloud.ptl.boardgamecollector.db.dao.GameAuthorArtistLocationRankingDAO;
import cloud.ptl.boardgamecollector.db.entity.Artist;
import cloud.ptl.boardgamecollector.db.entity.ArtistGameCrossRef;
import cloud.ptl.boardgamecollector.db.entity.Author;
import cloud.ptl.boardgamecollector.db.entity.AuthorGameCrossRef;
import cloud.ptl.boardgamecollector.db.entity.Game;
import cloud.ptl.boardgamecollector.db.entity.Location;
import cloud.ptl.boardgamecollector.db.entity.RankingEntry;
import cloud.ptl.boardgamecollector.db.entity.RankingGameCrossRef;

@Database(
        entities = {
                Artist.class,
                ArtistGameCrossRef.class,
                Author.class,
                AuthorGameCrossRef.class,
                Game.class,
                Location.class,
                RankingEntry.class,
                RankingGameCrossRef.class
        },
        version = 1
)
public abstract class AppDB extends RoomDatabase {
    public abstract GameAuthorArtistLocationRankingDAO gameAuthorArtistLocationRankingDAO();
}
