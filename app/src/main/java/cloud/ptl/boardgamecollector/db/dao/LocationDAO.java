package cloud.ptl.boardgamecollector.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import cloud.ptl.boardgamecollector.db.entity.Location;

@Dao
public interface LocationDAO {
    @Insert
    long add(Location location);

    @Delete
    void delete(Location location);

    @Update
    void update(Location location);

    @Query("SELECT * FROM Location")
    List<Location> getAll();
}
