package cloud.ptl.boardgamecollector.entity;

import androidx.room.Entity;

@Entity(primaryKeys = {"gameId", "authorId"})
public class AuthorGameCrossRef {
    public int gameId;
    public int authorId;
}
