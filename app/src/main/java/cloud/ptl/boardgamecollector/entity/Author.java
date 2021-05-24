package cloud.ptl.boardgamecollector.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Author {
    @PrimaryKey
    public int authorId;

    public String name;
    public String surname;
}
