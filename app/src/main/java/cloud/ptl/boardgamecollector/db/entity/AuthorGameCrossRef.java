package cloud.ptl.boardgamecollector.db.entity;

import androidx.room.Entity;

@Entity(primaryKeys = {"gameId", "authorId"})
public class AuthorGameCrossRef {
    public int gameId;
    public int authorId;
}
