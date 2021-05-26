package cloud.ptl.boardgamecollector.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

import cloud.ptl.boardgamecollector.db.entity.Artist;

@Dao
public interface ArtistDAO {
    @Insert
    long add(Artist artist);

    @Delete
    void delete(Artist artist);

    @Update
    void update(Artist artist);
}
