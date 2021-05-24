package cloud.ptl.boardgamecollector.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

import cloud.ptl.boardgamecollector.entity.Game;

@Dao
public interface GameDAO {
    @Transaction
    @Insert
    void insertAll(Game... games);

    @Transaction
    @Update
    void updateAll(Game... games);

    @Transaction
    @Delete
    void delete(Game game);

    @Transaction
    @Query("SELECT * FROM Game")
    List<Game> selectAll();

    @Transaction
    @Query("SELECT * FROM Game WHERE gameId=:id")
    Game findById(int id);
}
