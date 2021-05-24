package cloud.ptl.boardgamecollector.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import cloud.ptl.boardgamecollector.db.entity.GameAuthorArtistLocationRanking;

@Dao
public interface GameAuthorArtistLocationRankingDAO {
    @Query("SELECT * FROM Game WHERE gameId=:id")
    @Transaction
    GameAuthorArtistLocationRanking findById(int id);

    @Query("SELECT * FROM Game")
    @Transaction
    GameAuthorArtistLocationRanking[] findAll();
}
