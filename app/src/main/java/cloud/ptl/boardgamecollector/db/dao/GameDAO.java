package cloud.ptl.boardgamecollector.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import cloud.ptl.boardgamecollector.db.entity.Game;

@Dao
public interface GameDAO {
    @Insert
    void addAll(Game... games);

    @Delete
    void delete(Game game);

    @Update
    void update(Game game);

    @Query("SELECT * FROM Game")
    List<Game> findAllGames();

    @Query("SELECT * FROM Game WHERE gameId=:id")
    Game findById(int id);
}
