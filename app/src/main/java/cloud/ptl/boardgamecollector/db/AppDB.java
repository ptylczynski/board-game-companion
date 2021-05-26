package cloud.ptl.boardgamecollector.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import cloud.ptl.boardgamecollector.db.dao.ArtistDAO;
import cloud.ptl.boardgamecollector.db.dao.AuthorDAO;
import cloud.ptl.boardgamecollector.db.dao.GameDAO;
import cloud.ptl.boardgamecollector.db.dao.LocationDAO;
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
    public abstract GameDAO gameDAO();
    public abstract LocationDAO locationDAO();
    public abstract AuthorDAO authorDAO();
    public abstract ArtistDAO artistDAO();
}
