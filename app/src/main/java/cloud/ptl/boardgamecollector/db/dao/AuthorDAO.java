package cloud.ptl.boardgamecollector.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

import cloud.ptl.boardgamecollector.db.entity.Author;

@Dao
public class AuthorDAO {
    @Insert
    long add(Author author);

    @Delete
    void delete(Author author);

    @Update
    void update(Author author);
}
