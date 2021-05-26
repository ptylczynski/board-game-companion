package cloud.ptl.boardgamecollector.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

import cloud.ptl.boardgamecollector.db.entity.Location;

@Dao
public interface LocationDAO {
    @Insert
    long add(Location location);

    @Delete
    void delete(Location location);

    @Update
    void update(Location location);
}
