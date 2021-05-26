package cloud.ptl.boardgamecollector.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import cloud.ptl.boardgamecollector.db.entity.Author;

@Dao
public interface AuthorDAO {
    @Insert
    long add(Author author);

    @Delete
    void delete(Author author);

    @Update
    void update(Author author);

    @Query("SELECT * FROM Author")
    List<Author> getAll();

    @Query("SELECT * FROM Author WHERE authorId=:id")
    Author findById(Long id);
}
