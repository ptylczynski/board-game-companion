package cloud.ptl.boardgamecollector.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;

import cloud.ptl.boardgamecollector.entity.Game;

@Dao
public interface GameDAO {
    @Insert
    void insertAll(Game... games);
}
