package cloud.ptl.boardgamecollector.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import cloud.ptl.boardgamecollector.db.entity.Artist;

@Dao
public interface ArtistDAO {
    @Insert
    long add(Artist artist);

    @Delete
    void delete(Artist artist);

    @Update
    void update(Artist artist);

    @Query("SELECT * FROM Artist")
    List<Artist> getAll();

    @Query("SELECT * FROM Artist WHERE artistId=:id")
    Artist findById(Long id);
}
